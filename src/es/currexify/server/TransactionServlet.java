package es.currexify.server;

import java.io.IOException;
import java.util.*;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.appengine.labs.repackaged.org.json.JSONArray;
import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;

import es.currexify.server.dao.*;
import es.currexify.server.model.*;

@SuppressWarnings("serial")
public class TransactionServlet extends HttpServlet {

	String[] currencies = { "EUR", "USD", "GBP"};
	private UsuariosModel usuario;

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		 UserService userService = UserServiceFactory.getUserService();
		 String url = userService.createLoginURL(req.getRequestURI());
		 String urlLinktext = "Login";
		 String user = "";
		 if (req.getUserPrincipal() != null) {
		 user = req.getUserPrincipal().getName();
		 url = userService.createLogoutURL("http://1-dot-isst-grupo06-socialex.appspot.com");
		 urlLinktext = "Logout";
		 }
		
		 req.getSession().setAttribute("user", user);
		 req.getSession().setAttribute("url", url);
		 req.getSession().setAttribute("urlLinktext", urlLinktext);
		req.getSession().setAttribute("currencies", currencies);

		JSONObject json = new JSONObject();
		List<String> jray = new ArrayList<String>();
		
		try {
			EntityManager em = EMFService.get().createEntityManager();
			UsuariosDAOImpl udao = UsuariosDAOImpl.getInstance();
			this.usuario = udao.readUserByEmail(em, user);
			List<HistoryModel> umh = usuario.getHistories();
			for(HistoryModel hm : umh){
				double finalValue = Math.round(hm.getAmount() * 100.0 ) / 100.0;
				json.put("quantity", finalValue+this.getCurrencySymbol(hm.getCoin()));
				json.put("type", hm.getType());
				json.put("user", usuario.getEmail());
				jray.add(json.toString());
			}
			
			em.close();
			String jsonText = jray.toString();
			req.getSession().setAttribute("history", jsonText);
		} catch (JSONException e) {
			e.printStackTrace();
		}		
		
		RequestDispatcher view = req.getRequestDispatcher("transaction.jsp");
		view.forward(req, resp);
	}
	
	public void doPost (HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		String amount = req.getParameter("amount");
		String from = req.getParameter("currST");
		String to = req.getParameter("currND");
		if(from.equals(to) || amount.trim() == "") {
			resp.sendRedirect("transaction");//mensaje esto no se puede hacer
			return;
		}
		EntityManager em = EMFService.get().createEntityManager();
		UsuariosDAOImpl udao = UsuariosDAOImpl.getInstance();
		List<HistoryModel> umh = usuario.getHistories();
		if(!hasMoney(from, Double.valueOf(amount))){
			resp.sendRedirect("transaction");//mensaje de no hay money
			return;
		}
			
		HistoryModel fromHm = new HistoryModel(usuario.getCardN(), from, Double.parseDouble(amount), "saliente", new Date());
		HistoryModel toHm = new HistoryModel(usuario.getCardN(), to, getConverted(from, to, Double.parseDouble(amount)), 
				"entrante", new Date());
		udao.addHistoryToUser(em, fromHm, udao.readUserByEmail(em, usuario.getEmail()));
		udao.addHistoryToUser(em, toHm, udao.readUserByEmail(em, usuario.getEmail()));
		
		UsuariosModel uTemp = udao.readUserByEmail(em, usuario.getEmail());
	
		List<CurrencyBudgetModel> cbml = uTemp.getUserCurrencies();
		for(CurrencyBudgetModel a: cbml){
			System.out.println(a.getBudget()+" "+a.getCurrency());
			if(a.getCurrency().equals(from)){
				double newBudget = a.getBudget()-Double.valueOf(amount);
				a.setBudget(newBudget);
			}
			else if(a.getCurrency().equals(to)){
				double newBudget = a.getBudget()+toHm.getAmount();
				a.setBudget(newBudget);
			}
		}
		uTemp.setUserCurrencies(cbml);
		udao.updateUsuario(em, uTemp);
		
		uTemp = udao.readUserByEmail(em, usuario.getEmail());
		cbml = uTemp.getUserCurrencies();
		for(CurrencyBudgetModel a: cbml){
			System.out.println(a.getBudget()+" "+a.getCurrency());
			
		}

		
		em.close();
		resp.sendRedirect("transaction");
		
	}
	
	private String getCurrencySymbol(String currencyName) {
		switch(currencyName){
		case "EUR":
			return "€";
		case "USD":
			return "$";
		case "GBP":
			return "£";
		default: 
			return "";
		}
	}
	
	private boolean hasMoney(String currency,double amount){
		EntityManager em = EMFService.get().createEntityManager();
		UsuariosDAOImpl udao = UsuariosDAOImpl.getInstance();
		UsuariosModel u1 = udao.readUserByEmail(em, usuario.getEmail());
		List<CurrencyBudgetModel> cbml = u1.getUserCurrencies();
		em.close();
		for(CurrencyBudgetModel a: cbml){
			if(a.getCurrency().equals(currency)){
				return a.getBudget()>=amount;
			}
		}
		return false;
	}
	
	private double getConverted(String from, String to, double oAmount){
		EntityManager em = EMFService.get().createEntityManager();
		CurrencyExRateDAOImpl cerdao = CurrencyExRateDAOImpl.getInstance();
		double cAmount = 0.0;
		
		if (from.equals("EUR")) {
			CurrencyExRateModel cer = cerdao.readCurrencyExRatesByCurrency(em, to);
			cAmount = oAmount*cer.getEuroEx();
		}
		else if (to.equals("EUR")) {
			CurrencyExRateModel cer = cerdao.readCurrencyExRatesByCurrency(em, from);
			cAmount = oAmount/cer.getEuroEx();
		}
		else if (from.equals("USD" )) {
			CurrencyExRateModel cer1 = cerdao.readCurrencyExRatesByCurrency(em, from);
			CurrencyExRateModel cer2 = cerdao.readCurrencyExRatesByCurrency(em, to);
			cAmount = oAmount/cer1.getEuroEx()*cer2.getEuroEx();
		}
		else if (from.equals("GBP")) {
			CurrencyExRateModel cer1 = cerdao.readCurrencyExRatesByCurrency(em, from);
			CurrencyExRateModel cer2 = cerdao.readCurrencyExRatesByCurrency(em, to);
			cAmount = oAmount/cer1.getEuroEx()*cer2.getEuroEx();
		}
		return cAmount;
	}
	
	
  }
