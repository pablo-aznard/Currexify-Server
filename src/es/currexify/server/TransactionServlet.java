package es.currexify.server;

import java.io.IOException;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import es.currexify.server.dao.*;
import es.currexify.server.model.*;

@SuppressWarnings("serial")
public class TransactionServlet extends HttpServlet {

	String[] currencies = { "EUR", "USD" };

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		 UserService userService = UserServiceFactory.getUserService();
		 String url = userService.createLoginURL(req.getRequestURI());
		 String urlLinktext = "Login";
		 String user = "";
		 if (req.getUserPrincipal() != null) {
		 user = req.getUserPrincipal().getName();
		 url = userService.createLogoutURL(req.getRequestURI());
		 urlLinktext = "Logout";
		 }
		
		 req.getSession().setAttribute("user", user);
		 req.getSession().setAttribute("url", url);
		 req.getSession().setAttribute("urlLinktext", urlLinktext);
		req.getSession().setAttribute("currencies", currencies);
		RequestDispatcher view = req.getRequestDispatcher("transaction.jsp");
		view.forward(req, resp);
	}
	
	public void doPost (HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		String amount = req.getParameter("amount");
		resp.getWriter().println(amount);
	}
  }
