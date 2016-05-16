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
public class NewTransactionFriendServlet extends HttpServlet {

	String[] currencies = { "EUR", "USD", "GBP" };
	private UsuariosModel usuario;
	private UsuariosModel amigo;
	Calendar cal = Calendar.getInstance();
	String friend = "";

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
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
		req.getSession().setAttribute("friend", friend);
		RequestDispatcher view = req.getRequestDispatcher("newTransactionFriend.jsp");
		view.forward(req, resp);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		String amount = req.getParameter("amount");
		String from = req.getParameter("currST");
		String to = req.getParameter("currND");
		String friend1 = req.getParameter("friend");
		String url = "";
		String urlLinktext = "Login";
		String user = "";

		String email = (String) req.getSession().getAttribute("login");
		EntityManager em = EMFService.get().createEntityManager();
		UsuariosDAOImpl udao = UsuariosDAOImpl.getInstance();
		if (email != null) {
			usuario = udao.readUserByEmail(em, email);
			amigo = udao.readUserByEmail(em, friend1);
			user = usuario.getName();
			url = "/logout";
			urlLinktext = "Logout";

		}

		if (from.equals(to) || amount.trim() == "") {
			resp.sendRedirect("transaction?equal=true");// mensaje esto no
														// se
														// puede hacer
			return;
		}

		
		if (!hasMoney(from, Double.valueOf(amount))) {
			resp.sendRedirect("transaction?error=true");// mensaje de no hay
														// money
			return;
		}

		HistoryModel fromHm = new HistoryModel(usuario.getCardN(), from, Double.parseDouble(amount), "bloqueado",
				new Date());
		udao.addHistoryToUser(em, fromHm, udao.readUserByEmail(em, usuario.getEmail()));
		
		TransactionModel tm = new TransactionModel(usuario.getCardN(), from, to, Double.parseDouble(amount),
				new Date(), amigo.getId());
		udao.addTransactionToUser(em, tm, udao.readUserByEmail(em, usuario.getEmail()));

		usuario = udao.readUserByEmail(em, usuario.getEmail());

		List<CurrencyBudgetModel> cbml = usuario.getUserCurrencies();
		for (CurrencyBudgetModel a : cbml) {
			if (a.getCurrency().equals(from)) {
				double newBudget = a.getBudget() - Double.valueOf(amount);
				double newBlocked = a.getBlocked() + Double.valueOf(amount);
				a.setBudget(newBudget);
				a.setBlocked(newBlocked);
			}
		}
		usuario.setUserCurrencies(cbml);
		udao.updateUsuario(em, usuario);

		usuario = udao.readUserByEmail(em, usuario.getEmail());

		req.getSession().setAttribute("user", user);
		req.getSession().setAttribute("url", url);
		req.getSession().setAttribute("urlLinktext", urlLinktext);
		req.getSession().setAttribute("currencies", currencies);
		req.getSession().setAttribute("friend", friend);

		em.close();

		resp.sendRedirect("transaction");
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
}