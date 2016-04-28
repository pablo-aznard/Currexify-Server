package es.currexify.server;

import java.io.IOException;
import java.util.*;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.appengine.labs.repackaged.org.json.JSONException;

import es.currexify.server.dao.*;
import es.currexify.server.model.*;

@SuppressWarnings("serial")
public class Login extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		EntityManager em = EMFService.get().createEntityManager();
		CurrencyExRateDAOImpl cerdao = CurrencyExRateDAOImpl.getInstance();
		String url = "";
		String urlLinktext = "Login";
		String user = "";
		
		if(cerdao.readCurrencyExRates(em).size() == 0) {
			CurrencyExRateModel cer1 = new CurrencyExRateModel(1.128, "USD");
			CurrencyExRateModel cer2 = new CurrencyExRateModel(0.796, "GBP");
			cerdao.createCurrencyExRate(em, cer1);
			cerdao.createCurrencyExRate(em, cer2);
		}
		
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
		RequestDispatcher view = req.getRequestDispatcher("login.jsp");
		view.forward(req, resp);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String user = request.getParameter("user");
	    String pass = request.getParameter("pass");
		//response.getWriter().println("<p>Pulsa " + user + " y además " + pass);
		EntityManager em = EMFService.get().createEntityManager();
		UsuariosDAOImpl udao = UsuariosDAOImpl.getInstance();
		UsuariosModel um1 = udao.readUserByEmail(em, user);
		if(um1 != null){
			if(um1.getPassword().equals(pass)){
				request.getSession().setAttribute("login", um1.getEmail());
				response.sendRedirect("profile");	
			} else {
				response.sendRedirect("profile?error=true");	
			}
		} 
		em.close();
		
	}
  }