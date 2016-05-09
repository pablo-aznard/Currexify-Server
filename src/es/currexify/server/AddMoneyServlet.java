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
public class AddMoneyServlet extends HttpServlet {
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
		RequestDispatcher view = req.getRequestDispatcher("addMoney.jsp");
		view.forward(req, resp);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user = (String) request.getSession().getAttribute("user");
		String name = request.getParameter("name");
	    String cardType = request.getParameter("card");
	    String cardNum = request.getParameter("cardNumber");
	    String expirate = request.getParameter("expirate");
	    String cvv = request.getParameter("cvv");
	    String quantity = request.getParameter("quantity");

		EntityManager em = EMFService.get().createEntityManager();
		UsuariosDAOImpl udao = UsuariosDAOImpl.getInstance();
		UsuariosModel um = udao.readUserByName(em, user);
		List<CurrencyBudgetModel> cbms = um.getUserCurrencies();
		for (CurrencyBudgetModel cbm : cbms) {
			if (cbm.getCurrency().equals("GBP")) {
				cbm.setBudget(cbm.getBudget() + Double.parseDouble(quantity));
			}
		}
		um.setUserCurrencies(cbms);
		udao.updateUsuario(em, um);
		response.sendRedirect("profile");

		em.close();
		
		
	}
  }