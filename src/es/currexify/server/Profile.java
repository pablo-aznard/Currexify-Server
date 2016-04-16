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
		UserService userService = UserServiceFactory.getUserService();
		String url = userService.createLoginURL(req.getRequestURI());
		String urlLinktext = "Login";
		String user = "";
		JSONObject json = new JSONObject();
		List<String> jray = new ArrayList<String>();

		if (req.getUserPrincipal() != null) {
			user = req.getUserPrincipal().getName();
			url = userService.createLogoutURL(req.getRequestURI());
			urlLinktext = "Logout";
		}

		try {
			EntityManager em = EMFService.get().createEntityManager();
			UsuariosDAOImpl udao = UsuariosDAOImpl.getInstance();
			this.usuario = udao.readUserByEmail(em, user);
			List<CurrencyBudgetModel> cbm = usuario.getUserCurrencies();
			for (CurrencyBudgetModel cb : cbm) {
				json.put("currency", cb.getCurrency());
				json.put("quantity", cb.getBudget());
				jray.add(json.toString());
			}
			
			em.close();

			String jsonText = jray.toString();
			req.getSession().setAttribute("currencies", jsonText);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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