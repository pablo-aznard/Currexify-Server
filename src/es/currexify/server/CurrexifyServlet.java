package es.currexify.server;

import java.io.IOException;
import java.util.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import es.currexify.server.dao.*;
import es.currexify.server.model.*;

@SuppressWarnings("serial")
public class CurrexifyServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		resp.setContentType("text/plain");
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
		RequestDispatcher view = req.getRequestDispatcher("index.jsp");
		view.forward(req, resp);
	}
  }