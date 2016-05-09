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
public class TakeMoneyOutServlet extends HttpServlet {
	
	String[] currencies = { "EUR", "USD", "GBP" };
	
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
		req.getSession().setAttribute("currencies", currencies);
		req.getSession().setAttribute("urlLinktext", urlLinktext);
		RequestDispatcher view = req.getRequestDispatcher("takeMoneyOut.jsp");
		view.forward(req, resp);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user = (String) request.getSession().getAttribute("user");
		String name = request.getParameter("name");
	    String cardType = request.getParameter("iban");
	    String currency = request.getParameter("currency");
	    String quantity = request.getParameter("quantity");

		EntityManager em = EMFService.get().createEntityManager();
		UsuariosDAOImpl udao = UsuariosDAOImpl.getInstance();
		UsuariosModel um = udao.readUserByName(em, user);
		List<CurrencyBudgetModel> cbms = um.getUserCurrencies();
		for (CurrencyBudgetModel cbm : cbms) {
			if (cbm.getCurrency().equals(currency)) {
				if(cbm.getBudget() > Double.parseDouble(quantity))
					cbm.setBudget(cbm.getBudget() - Double.parseDouble(quantity));
				else {
					response.sendRedirect("takeMoneyOut?error=true");
					em.close();
					return;
				}
			}
		}
		um.setUserCurrencies(cbms);
		udao.updateUsuario(em, um);
		response.sendRedirect("profile");

		em.close();
		
		
	}
  }