package es.currexify.server;

import java.io.IOException;
import java.util.*;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import es.currexify.server.dao.*;
import es.currexify.server.model.*;

@SuppressWarnings("serial")
public class Login extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		EntityManager em = EMFService.get().createEntityManager();
		CurrencyExRateDAOImpl cerdao = CurrencyExRateDAOImpl.getInstance();
		if(cerdao.readCurrencyExRates(em).size() == 0) {
			CurrencyExRateModel cer1 = new CurrencyExRateModel(1.128, "USD");
			CurrencyExRateModel cer2 = new CurrencyExRateModel(0.796, "GBP");
			cerdao.createCurrencyExRate(em, cer1);
			cerdao.createCurrencyExRate(em, cer2);
		}
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
		
		UsuariosDAOImpl udao = UsuariosDAOImpl.getInstance();
		if(user!="" && udao.isEmailUnique(em, user)){
			UsuariosModel um = new UsuariosModel("","",user,"","669598554");
			udao.createUser(em, um);
			CurrencyBudgetModel cbm1 = new CurrencyBudgetModel(um.getCardN(), "EUR", 1000.0);
			CurrencyBudgetModel cbm2 = new CurrencyBudgetModel(um.getCardN(), "USD", 0.0);
			CurrencyBudgetModel cbm3 = new CurrencyBudgetModel(um.getCardN(), "GBP", 0.0);
			udao.addCurrencyBudgetToUser(em, cbm1, um);
			udao.addCurrencyBudgetToUser(em, cbm2, um);
			udao.addCurrencyBudgetToUser(em, cbm3, um);
		}
		RequestDispatcher view = req.getRequestDispatcher("login.jsp");
		view.forward(req, resp);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String user = request.getParameter("user");
	    String pass = request.getParameter("pass");
		//response.getWriter().println("<p>Pulsa " + user + " y además " + pass);
		EntityManager em = EMFService.get().createEntityManager();
		UsuariosDAOImpl udao = UsuariosDAOImpl.getInstance();
		response.getWriter().println(udao.readUserByName(em, user).getPassword().equals(pass));
		if(udao.readUserByName(em, user).getPassword().equals(pass)){
			response.sendRedirect("profile.jsp");			
		}
		em.close();
		
	}
  }