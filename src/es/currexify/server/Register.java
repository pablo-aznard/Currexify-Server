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
		String url = "";
		String urlLinktext = "Login";
		String user = "";
		EntityManager em = EMFService.get().createEntityManager();
		
		String email = (String) req.getSession().getAttribute("login");
		UsuariosModel usuario;

		if (email != null) {
			UsuariosDAOImpl udao = UsuariosDAOImpl.getInstance();
			usuario = udao.readUserByEmail(em, email);
				
			em.close();
			user = usuario.getName();
			url = "/logout";
			urlLinktext = "Logout";
		}

		req.getSession().setAttribute("user", user);
		req.getSession().setAttribute("url", url);
		req.getSession().setAttribute("urlLinktext", urlLinktext);
		RequestDispatcher view = req.getRequestDispatcher("register.jsp");
		view.forward(req, resp);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user = request.getParameter("name");
	    String pass = request.getParameter("pass");
	    String email = request.getParameter("email");
	    String address = request.getParameter("address");
	    String phone = request.getParameter("phone");
		
		EntityManager em = EMFService.get().createEntityManager();
		UsuariosDAOImpl udao = UsuariosDAOImpl.getInstance();
		if(udao.isEmailUnique(em, email)){
			UsuariosModel um = new UsuariosModel(user, pass, email, address, phone);
			System.out.println(pass);
			udao.createUser(em, um);
			CurrencyBudgetModel cbm1 = new CurrencyBudgetModel(um.getCardN(), "EUR", 0.0);
			CurrencyBudgetModel cbm2 = new CurrencyBudgetModel(um.getCardN(), "USD", 0.0);
			CurrencyBudgetModel cbm3 = new CurrencyBudgetModel(um.getCardN(), "GBP", 0.0);
			udao.addCurrencyBudgetToUser(em, cbm1, um);
			udao.addCurrencyBudgetToUser(em, cbm2, um);
			udao.addCurrencyBudgetToUser(em, cbm3, um);
			response.sendRedirect("login");
		}
		else{
			response.sendRedirect("register?error=true");
		}
		em.close();
		
		
	}
  }