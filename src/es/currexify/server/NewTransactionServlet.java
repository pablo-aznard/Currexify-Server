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
public class NewTransactionServlet extends HttpServlet {

	String[] currencies = { "EUR", "USD", "GBP" };
	String[] times = { "Instantánea -- 1.5%", "3 días -- 1.3%", "7 días -- 1%"};
	private UsuariosModel usuario;
	Calendar cal = Calendar.getInstance();
	String friend = "";

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		String url = "";
		String urlLinktext = "Login";
		String user = "";

		String email = (String) req.getSession().getAttribute("login");

		if (email != null) {
			EntityManager em = EMFService.get().createEntityManager();
			UsuariosDAOImpl udao = UsuariosDAOImpl.getInstance();
			usuario = udao.readUserByEmail(em, email);

			em.close();
			user = usuario.getName();
			url = "/logout";
			urlLinktext = "Logout";

			if (req.getParameter("friend") != null) {
				friend = req.getParameter("friend");
			}
		}

		req.getSession().setAttribute("user", user);
		req.getSession().setAttribute("url", url);
		req.getSession().setAttribute("urlLinktext", urlLinktext);
		req.getSession().setAttribute("currencies", currencies);
		req.getSession().setAttribute("times", times);
		RequestDispatcher view = req.getRequestDispatcher("newTransaction.jsp");
		view.forward(req, resp);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		String amount = req.getParameter("amount");
		String amountConverted = req.getParameter("amountND");
		String from = req.getParameter("currST");
		String to = req.getParameter("currND");
		String time = req.getParameter("time");
		String amigo = req.getParameter("amigo");
		String friend1 = req.getParameter("friend");
		String id = req.getParameter("id");
		String url = "";
		String urlLinktext = "Login";
		String user = "";
		String action = req.getParameter("action");
		
		String email = (String) req.getSession().getAttribute("login");

		if (amigo!= null && amigo.equals("true") && action.equals("accept") ) {
			EntityManager em = EMFService.get().createEntityManager();
			double dAmountConverted = Double.valueOf(amountConverted);
			UsuariosDAOImpl udao = UsuariosDAOImpl.getInstance();
			usuario = udao.readUserByEmail(em, email);
			
			if (!hasMoney(from, Double.valueOf(amount))) {
				resp.sendRedirect("transaction?error=true");// mensaje de no hay
															// money
				return;
			}
			
			HistoryModel toHm = new HistoryModel(usuario.getCardN(), from, dAmountConverted, "saliente", new Date());
			udao.addHistoryToUser(em, toHm,
					udao.readUserByEmail(em, usuario.getEmail()));
			HistoryModel fromHm = new HistoryModel(usuario.getCardN(), to, Double.parseDouble(amount), "entrante", new Date());
			udao.addHistoryToUser(em, fromHm,
					udao.readUserByEmail(em, usuario.getEmail()));
			
			List<CurrencyBudgetModel> cbml = usuario.getUserCurrencies();
			for (CurrencyBudgetModel a : cbml) {
				if (a.getCurrency().equals(to)) {
					double newBudget = a.getBudget() + Double.valueOf(amount);
					a.setBudget(newBudget);
				}
				if (a.getCurrency().equals(from)) {
					double newBudget = a.getBudget() - dAmountConverted;
					a.setBudget(newBudget);
				}
			}
			usuario.setUserCurrencies(cbml);
			udao.updateUsuario(em, usuario);
			
			//TRANSACCION DEL COLEGA
			UsuariosModel uFriend = udao.readUserByEmail(em, friend1);
			HistoryModel fromHmFriend = new HistoryModel(uFriend.getCardN(), from, dAmountConverted, "entrante", new Date());
			udao.addHistoryToUser(em, fromHmFriend,
					udao.readUserByEmail(em, uFriend.getEmail()));
			//HistoryModel toHmFriend = new HistoryModel(uFriend.getCardN(), from, amountConverted, "saliente", new Date());
			//udao.addHistoryToUser(em, toHmFriend,
			//		udao.readUserByEmail(em, uFriend.getEmail()));
			
			List<HistoryModel> hml =  uFriend.getHistories();
			for(HistoryModel h: hml){
				if(h.getType().equals("bloqueado") && h.getAmount()==Double.valueOf(amount)){
					HistoryDAOImpl hdao = HistoryDAOImpl.getInstance();
					h.setType("saliente");
					hdao.updateHistory(em, h);
					break;
				}
			}
			
			List<CurrencyBudgetModel> cbmlFriend = uFriend.getUserCurrencies();
			for (CurrencyBudgetModel a : cbmlFriend) {
				if (a.getCurrency().equals(from)) {
					double newBudget = a.getBudget() + dAmountConverted;
					a.setBudget(newBudget);
				}
			}
			
			uFriend.setUserCurrencies(cbmlFriend);
			udao.updateUsuario(em, uFriend);
			
			List<TransactionModel> tml = usuario.getUserTransactions();
			
			for (TransactionModel a : tml) {
				if(a.getId().toString().equals(id)){
					udao.deleteUserTransaction(em, a, usuario);
				}	
			}
			
			em.close();
			resp.sendRedirect("/transaction");
		}
		else if (amigo!= null && amigo.equals("true") && action.equals("cancel") ) {	
			EntityManager em = EMFService.get().createEntityManager();
			UsuariosDAOImpl udao = UsuariosDAOImpl.getInstance();
			UsuariosModel uFriend = udao.readUserByEmail(em, friend1);
			usuario = udao.readUserByEmail(em, email);
			
			List<TransactionModel> tml = usuario.getUserTransactions();
			for (TransactionModel a : tml) {
				if(a.getId().toString().equals(id)){
					udao.deleteUserTransaction(em, a, usuario);
				}	
			}
			
			List<HistoryModel> hml = uFriend.getHistories();
			for(HistoryModel h: hml){
				if(h.getType().equals("bloqueado") && h.getAmount()==Double.valueOf(amount)){
					udao.deleteUserHistory(em, h, uFriend);
				}
			}	
			
			List<CurrencyBudgetModel> cbml = uFriend.getUserCurrencies();
			for (CurrencyBudgetModel a : cbml) {
				if (a.getCurrency().equals(to)) {
					double newBudget = a.getBudget() + Double.valueOf(amount);
					a.setBudget(newBudget);
				}
			}
			uFriend.setUserCurrencies(cbml);
			udao.updateUsuario(em, uFriend);
			
			em.close();
			resp.sendRedirect("/transaction");
		}
		else {
			if (email != null) {
				EntityManager em = EMFService.get().createEntityManager();
				UsuariosDAOImpl udao = UsuariosDAOImpl.getInstance();
				usuario = udao.readUserByEmail(em, email);

				em.close();
				user = usuario.getName();
				url = "/logout";
				urlLinktext = "Logout";

			}

			System.out.println(from + " " + to + " " + amount);
			if (from.equals(to) || amount.trim() == "") {
				resp.sendRedirect("transaction?equal=true");// mensaje esto no
															// se
															// puede hacer
				return;
			}

			EntityManager em = EMFService.get().createEntityManager();
			UsuariosDAOImpl udao = UsuariosDAOImpl.getInstance();
			if (!hasMoney(from, Double.valueOf(amount))) {
				resp.sendRedirect("transaction?error=true");// mensaje de no hay
															// money
				return;
			}

			if (!friend.isEmpty()) {
				sendRequestToFriend(friend, from, to,
						Double.parseDouble(amount));
			}

			int days = 0;
			double charge = 0.015;
			if (time == times[1]) {
				days = 3;
				charge = 0.013;
			}
			if (time == times[2]) {
				days = 7;
				charge = 0.01;
			}
			cal.add(cal.MINUTE, days);
			HistoryModel fromHm = new HistoryModel(usuario.getCardN(), from,
					Double.parseDouble(amount), "bloqueado", new Date());
			udao.addHistoryToUser(em, fromHm,
					udao.readUserByEmail(em, usuario.getEmail()));

			TransactionModel tm = new TransactionModel(usuario.getCardN(),
					from, to, Double.parseDouble(amount), cal.getTime(), charge);
			udao.addTransactionToUser(em, tm,
					udao.readUserByEmail(em, usuario.getEmail()));

			cal.add(cal.MINUTE, days * -1);
			UsuariosModel uTemp = udao.readUserByEmail(em, usuario.getEmail());

			List<CurrencyBudgetModel> cbml = uTemp.getUserCurrencies();
			for (CurrencyBudgetModel a : cbml) {
				System.out.println(a.getBudget() + " " + a.getCurrency());
				if (a.getCurrency().equals(from)) {
					double newBudget = a.getBudget() - Double.valueOf(amount);
					double newBlocked = a.getBlocked() + Double.valueOf(amount);
					a.setBudget(newBudget);
					a.setBlocked(newBlocked);
				}
			}
			uTemp.setUserCurrencies(cbml);
			udao.updateUsuario(em, uTemp);

			uTemp = udao.readUserByEmail(em, usuario.getEmail());
			cbml = uTemp.getUserCurrencies();

			req.getSession().setAttribute("user", user);
			req.getSession().setAttribute("url", url);
			req.getSession().setAttribute("urlLinktext", urlLinktext);
			req.getSession().setAttribute("currencies", currencies);
			req.getSession().setAttribute("times", times);

			em.close();

			resp.sendRedirect("transaction");
		}
	}

	private String getCurrencySymbol(String currencyName) {
		switch (currencyName) {
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

	private boolean hasMoney(String currency, double amount) {
		EntityManager em = EMFService.get().createEntityManager();
		UsuariosDAOImpl udao = UsuariosDAOImpl.getInstance();
		UsuariosModel u1 = udao.readUserByEmail(em, usuario.getEmail());
		List<CurrencyBudgetModel> cbml = u1.getUserCurrencies();
		em.close();
		for (CurrencyBudgetModel a : cbml) {
			if (a.getCurrency().equals(currency)) {
				return a.getBudget() >= amount;
			}
		}
		return false;
	}

	private double getConverted(String from, String to, double oAmount,
			String time) {
		EntityManager em = EMFService.get().createEntityManager();
		CurrencyExRateDAOImpl cerdao = CurrencyExRateDAOImpl.getInstance();
		double cAmount = 0.0;
		double percent = 1.0;

		if (time == times[0])
			percent = 1.5;
		else if (time == times[1])
			percent = 1.3;
		else 
			percent = 1.0;
	
		if (from.equals("EUR")) {
			CurrencyExRateModel cer = cerdao.readCurrencyExRatesByCurrency(em,
					to);
			cAmount = oAmount * cer.getEuroEx();
		} else if (to.equals("EUR")) {
			CurrencyExRateModel cer = cerdao.readCurrencyExRatesByCurrency(em,
					from);
			cAmount = oAmount / cer.getEuroEx();
		} else if (from.equals("USD")) {
			CurrencyExRateModel cer1 = cerdao.readCurrencyExRatesByCurrency(em,
					from);
			CurrencyExRateModel cer2 = cerdao.readCurrencyExRatesByCurrency(em,
					to);
			cAmount = oAmount / cer1.getEuroEx() * cer2.getEuroEx();
		} else if (from.equals("GBP")) {
			CurrencyExRateModel cer1 = cerdao.readCurrencyExRatesByCurrency(em,
					from);
			CurrencyExRateModel cer2 = cerdao.readCurrencyExRatesByCurrency(em,
					to);
			cAmount = oAmount / cer1.getEuroEx() * cer2.getEuroEx();
		}
		return cAmount * ((100 - percent) / 100);
	}

	private void sendRequestToFriend(String friendName, String from, String to,
			double oAmount) {
		EntityManager em = EMFService.get().createEntityManager();
		UsuariosDAOImpl udao = UsuariosDAOImpl.getInstance();
		UsuariosModel friend = udao.readUserByEmail(em, friendName);
		TransactionModel tm = new TransactionModel(usuario.getCardN(), from,
				to, oAmount, new Date(), friend.getId());
		List<TransactionModel> tmList = friend.getUserTransactions();
		if (tmList == null)
			tmList = new ArrayList<TransactionModel>();
		tmList.add(tm);
		friend.setUserTransactions(tmList);

		em.close();
	}
}
