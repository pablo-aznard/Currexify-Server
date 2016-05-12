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
public class Profile extends HttpServlet {

	private UsuariosModel usuario;
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		String url = "";
		String urlLinktext = "Login";
		String user = "";
		JSONObject json = new JSONObject();
		List<String> jray = new ArrayList<String>();
		
		String email = (String) req.getSession().getAttribute("login");

		if (email != null) {
			try {
				EntityManager em = EMFService.get().createEntityManager();
				UsuariosDAOImpl udao = UsuariosDAOImpl.getInstance();
				usuario = udao.readUserByEmail(em, email);
				List<CurrencyBudgetModel> ucbm = usuario.getUserCurrencies();
				for(CurrencyBudgetModel cbm : ucbm){
					double finalValue = Math.round(cbm.getBudget() * 100.0 ) / 100.0;
					json.put("quantity",finalValue);
					json.put("currency", String.valueOf(cbm.getCurrency()));
					jray.add(json.toString());
				}
				req.getSession().setAttribute("currencies", jray.toString());
				
				List<TransactionModel> tList = usuario.getUserTransactions();
				System.out.println(tList.get(0).getAmountLeft());
				json = new JSONObject();
				jray = new ArrayList<String>();
				for (TransactionModel tm : tList) {
					if (tm.getFriendId() == null || tm.getFriendId() == 0)
						continue;
					double finalValue = Math.round(tm.getAmountLeft() * 100.0) / 100.0;
					json.put("quantity", finalValue);
					json.put("type", tm.getDCoin());
					json.put("user", udao.readUserById(em, tm.getFriendId()).getEmail());
					json.put("friend", udao.readUserById(em, tm.getFriendId()).getEmail());
					jray.add(json.toString());
				}
				req.getSession().setAttribute("transactions", jray.toString());
				
				em.close();
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
		req.getSession().setAttribute("notificaciones", 3);
		RequestDispatcher view = req.getRequestDispatcher("profile.jsp");
		view.forward(req, resp);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String user = request.getParameter("user");
		String pass = request.getParameter("pass");
		response.getWriter().println("<p>Pulsa " + user + " y además " + pass);
	}
}