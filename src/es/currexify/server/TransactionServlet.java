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

	String[] currencies = { "EUR", "USD", "GBP" };
	private UsuariosModel usuario;

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		UserService userService = UserServiceFactory.getUserService();
		String url = userService.createLoginURL(req.getRequestURI());
		String urlLinktext = "Login";
		String user = "";
		if (req.getUserPrincipal() != null) {
			user = req.getUserPrincipal().getName();
			url = userService.createLogoutURL("https://isst-grupo06-socialex.appspot.com");
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
			for (HistoryModel hm : umh) {
				double finalValue = Math.round(hm.getAmount() * 100.0) / 100.0;
				json.put("quantity", finalValue + this.getCurrencySymbol(hm.getCoin()));
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

}
