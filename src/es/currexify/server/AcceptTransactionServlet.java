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
public class AcceptTransactionServlet extends HttpServlet {

	String[] currencies = { "EUR", "USD", "GBP" };
	String[] times = { "Instantánea -- 1.5%", "3 días -- 1.3%", "7 días -- 1%" };
	private UsuariosModel usuario;
	Calendar cal = Calendar.getInstance();
	String friend = "";

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		String amount = req.getParameter("amount");
		String amountConverted = req.getParameter("amountND");
		String from = req.getParameter("currST");
		String to = req.getParameter("currND");
		String friend1 = req.getParameter("friend");
		String id = req.getParameter("id");

		String email = (String) req.getSession().getAttribute("login");

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
		udao.addHistoryToUser(em, toHm, udao.readUserByEmail(em, usuario.getEmail()));
		HistoryModel fromHm = new HistoryModel(usuario.getCardN(), to, Double.parseDouble(amount), "entrante",
				new Date());
		udao.addHistoryToUser(em, fromHm, udao.readUserByEmail(em, usuario.getEmail()));

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

		// TRANSACCION DEL COLEGA
		UsuariosModel uFriend = udao.readUserByEmail(em, friend1);
		HistoryModel fromHmFriend = new HistoryModel(uFriend.getCardN(), from, dAmountConverted, "entrante",
				new Date());
		udao.addHistoryToUser(em, fromHmFriend, udao.readUserByEmail(em, uFriend.getEmail()));
		// HistoryModel toHmFriend = new HistoryModel(uFriend.getCardN(), from,
		// amountConverted, "saliente", new Date());
		// udao.addHistoryToUser(em, toHmFriend,
		// udao.readUserByEmail(em, uFriend.getEmail()));

		List<HistoryModel> hml = uFriend.getHistories();
		for (HistoryModel h : hml) {
			if (h.getType().equals("bloqueado") && h.getAmount() == Double.valueOf(amount)) {
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
			if (a.getId().toString().equals(id)) {
				udao.deleteUserTransaction(em, a, usuario);
			}
		}

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
