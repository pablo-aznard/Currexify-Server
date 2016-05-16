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
public class CancelTransactionServlet extends HttpServlet {

	private UsuariosModel usuario;
	private UsuariosModel amigo;
	String friend = "";

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		String amount = req.getParameter("amount");
		String to = req.getParameter("currND");
		String friend1 = req.getParameter("friend");
		String id = req.getParameter("id");

		String email = (String) req.getSession().getAttribute("login");

		EntityManager em = EMFService.get().createEntityManager();
		UsuariosDAOImpl udao = UsuariosDAOImpl.getInstance();
		usuario = udao.readUserByEmail(em, email);
		amigo = udao.readUserByEmail(em, friend1);

		List<TransactionModel> tml = amigo.getUserTransactions();
		for (TransactionModel a : tml) {
			if (a.getId().toString().equals(id)) {
				udao.deleteUserTransaction(em, a, amigo);
			}
		}

		List<HistoryModel> hml = amigo.getHistories();
		for (HistoryModel h : hml) {
			if (h.getType().equals("bloqueado") && h.getAmount() == Double.valueOf(amount)) {
				udao.deleteUserHistory(em, h, amigo);
			}
		}

		List<CurrencyBudgetModel> cbml = amigo.getUserCurrencies();
		for (CurrencyBudgetModel a : cbml) {
			if (a.getCurrency().equals(to)) {
				double newBudget = a.getBudget() + Double.valueOf(amount);
				a.setBudget(newBudget);
			}
		}
		amigo.setUserCurrencies(cbml);
		udao.updateUsuario(em,amigo);

		em.close();
		resp.sendRedirect("/transaction");
	}
}
