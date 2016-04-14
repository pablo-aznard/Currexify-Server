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
public class Register extends HttpServlet {
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
		RequestDispatcher view = req.getRequestDispatcher("register.jsp");
		view.forward(req, resp);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String user = request.getParameter("user");
	    String pass = request.getParameter("pass");
	    String email = request.getParameter("email");
	    email += "@gmail.com";
	    String address = request.getParameter("address");
	    String phone = request.getParameter("phone");
	    String cardnum = request.getParameter("cardnum");
		response.getWriter().println("<p>Pulsa " + user + " y además " + pass + " mi correo es " + email + " vivo en " + address + " llámame al " + phone + " y mi Credit card es " + cardnum);
	}
  }