package es.currexify.server;

import java.io.IOException;
import java.util.*;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;

import es.currexify.server.dao.*;
import es.currexify.server.model.*;

@SuppressWarnings("serial")
public class TransactionServlet extends HttpServlet {

	String[] times = {"Instant -- 1.5%", "3 days -- 1.3%", "7 days -- 1%"};
	String[] currencies = { "EUR", "USD", "GBP" };
	private UsuariosModel usuario;

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		String url = "";
		String urlLinktext = "Login";
		String user = "";
		
		String email = (String) req.getSession().getAttribute("login");

		JSONObject json = new JSONObject();
		List<String> jray = new ArrayList<String>();
		JSONObject json2 = new JSONObject();
		List<String> jray2 = new ArrayList<String>();
		
		Comparator<HistoryModel> tmComparator = new Comparator<HistoryModel>() {
			@Override
			public int compare(HistoryModel o1, HistoryModel o2) {
				return o2.getDate().compareTo(o1.getDate());
			}
			
		};

		if (email != null) {
			try {
				EntityManager em = EMFService.get().createEntityManager();
				UsuariosDAOImpl udao = UsuariosDAOImpl.getInstance();
				TransactionDAOImpl tdao = TransactionDAOImpl.getInstance();
				usuario = udao.readUserByEmail(em, email);
				List<HistoryModel> umh = usuario.getHistories();
				double finalValue = 0.0;
				Collections.sort(umh, tmComparator);
				for (HistoryModel hm : umh) {
					finalValue = Math.round(hm.getAmount() * 100.0) / 100.0;
					json.put("quantity", finalValue + this.getCurrencySymbol(hm.getCoin()));
					json.put("type", hm.getType());
					json.put("date", hm.getDate().toLocaleString().replace("-", " "));
					json.put("user", usuario.getEmail());
					jray.add(json.toString());
				}
				List<TransactionModel> tml = tdao.readTransactions(em);
				for (TransactionModel tm: tml) {
					if(tm.getFriendId() != null) {
						if(tm.getFriendId().longValue() == usuario.getId().longValue()){
							json2.put("friend", udao.readUserByCardN(em, tm.getCardN()).getEmail());
							json2.put("coinD", tm.getDCoin());
							json2.put("quantityS", tm.getAmount());
							json2.put("coinS", tm.getSCoin());
							json2.put("quantityD", getConverted(tm.getSCoin(), tm.getDCoin(), tm.getAmount(), times[2]));
							json2.put("id", tm.getId());
							jray2.add(json2.toString());
						}
					}
				}
				
	
				em.close();
				String jsonText = jray.toString();
				String jsonText2 = jray2.toString();
				req.getSession().setAttribute("history", jsonText);
				req.getSession().setAttribute("friends", jsonText2);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			user = usuario.getName();
			url = "/logout";
			urlLinktext = "Logout";
		}

		req.getSession().setAttribute("user", user);
		req.getSession().setAttribute("url", url);
		req.getSession().setAttribute("urlLinktext", urlLinktext);
		req.getSession().setAttribute("currencies", currencies);
		RequestDispatcher view = req.getRequestDispatcher("transaction.jsp");
		view.forward(req, resp);
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
	
	private double getConverted(String from, String to, double oAmount, String time) {
		EntityManager em = EMFService.get().createEntityManager();
		CurrencyExRateDAOImpl cerdao = CurrencyExRateDAOImpl.getInstance();
		double cAmount = 0.0;
		double percent = 1.0;
		
		if(time == times[0])
			percent = 1.5;
		else if(time == times[1])
			percent = 1.3;
		else 
			percent = 1.0;

		if (from.equals("EUR")) {
			CurrencyExRateModel cer = cerdao.readCurrencyExRatesByCurrency(em, to);
			cAmount = oAmount * cer.getEuroEx();
		} else if (to.equals("EUR")) {
			CurrencyExRateModel cer = cerdao.readCurrencyExRatesByCurrency(em, from);
			cAmount = oAmount / cer.getEuroEx();
		} else if (from.equals("USD")) {
			CurrencyExRateModel cer1 = cerdao.readCurrencyExRatesByCurrency(em, from);
			CurrencyExRateModel cer2 = cerdao.readCurrencyExRatesByCurrency(em, to);
			cAmount = oAmount / cer1.getEuroEx() * cer2.getEuroEx();
		} else if (from.equals("GBP")) {
			CurrencyExRateModel cer1 = cerdao.readCurrencyExRatesByCurrency(em, from);
			CurrencyExRateModel cer2 = cerdao.readCurrencyExRatesByCurrency(em, to);
			cAmount = oAmount / cer1.getEuroEx() * cer2.getEuroEx();
		}
		return cAmount*((100-percent)/100);
	}

}
