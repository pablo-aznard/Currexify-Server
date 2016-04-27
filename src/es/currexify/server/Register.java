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
public class Register extends HttpServlet {
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
		RequestDispatcher view = req.getRequestDispatcher("register.jsp");
		view.forward(req, resp);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("ENTRA");
		String user = request.getParameter("name");
	    String pass = request.getParameter("pass");
	    String email = request.getParameter("email");
	    String address = request.getParameter("address");
	    String phone = request.getParameter("phone");
		
		EntityManager em = EMFService.get().createEntityManager();
		UsuariosDAOImpl udao = UsuariosDAOImpl.getInstance();
		if(udao.isEmailUnique(em, email)){
			UsuariosModel um = new UsuariosModel(user, pass, email, address, phone);
			udao.createUser(em, um);
			CurrencyBudgetModel cbm = new CurrencyBudgetModel(um.getCardN(), "EUR", 1000.0);
			udao.addCurrencyBudgetToUser(em, cbm, um);
			System.out.println("ENTRA AL UNIQUE");
			response.sendRedirect("login");
		}
		else{
			System.out.println("NO ENTRA AL UNIQUE");
			response.sendRedirect("register?error=true");
		}
		em.close();
		
		
	}
  }