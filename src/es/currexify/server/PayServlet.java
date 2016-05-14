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
public class PayServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		String url = "";
		String urlLinktext = "Login";
		String user = "";
		EntityManager em = EMFService.get().createEntityManager();
		
		String email = (String) req.getSession().getAttribute("login");
		String amount = req.getParameter("amount");
	    String curr = req.getParameter("curr");
		UsuariosModel usuario;

		if (email != null) {
			UsuariosDAOImpl udao = UsuariosDAOImpl.getInstance();
			usuario = udao.readUserByEmail(em, email);
				
			user = usuario.getName();
			url = "/logout";
			urlLinktext = "Logout";
			List<CurrencyBudgetModel> cbms = usuario.getUserCurrencies();
			for (CurrencyBudgetModel cbm : cbms) {
				if (cbm.getCurrency().equals(curr)) {
					cbm.setBudget(cbm.getBudget() - Double.parseDouble(amount));
				}
			}
			usuario.setUserCurrencies(cbms);
			udao.updateUsuario(em, usuario);
			em.close();
		}

		req.getSession().setAttribute("user", user);
		req.getSession().setAttribute("url", url);
		req.getSession().setAttribute("urlLinktext", urlLinktext);
		RequestDispatcher view = req.getRequestDispatcher("profile");
		view.forward(req, resp);
	}
  }