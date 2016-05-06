package es.currexify.server;

import java.io.IOException;
import java.util.*;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import es.currexify.server.dao.*;
import es.currexify.server.model.*;

@SuppressWarnings("serial")
public class FriendsServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		String url = "";
		String urlLinktext = "Login";
		String user = "";		
		String email = (String) req.getSession().getAttribute("login");
		
		EntityManager em = EMFService.get().createEntityManager();
		UsuariosDAOImpl udao = UsuariosDAOImpl.getInstance();
		UsuariosModel usuario = udao.readUserByEmail(em, email);
		Set<String> friends = usuario.getFriends();
		em.close();
		if (email != null && friends != null) {

			req.getSession().setAttribute("friends", friends);
			user = usuario.getName();
			url = "/logout";
			urlLinktext = "Logout";
		}
		req.getSession().setAttribute("user", user);
		req.getSession().setAttribute("url", url);
		req.getSession().setAttribute("urlLinktext", urlLinktext);
		RequestDispatcher view = req.getRequestDispatcher("friends.jsp");
		view.forward(req, resp);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user = (String) request.getSession().getAttribute("user");
		String friend = (String) request.getParameter("friend");
		String type = (String) request.getParameter("type");
		
		EntityManager em = EMFService.get().createEntityManager();
		UsuariosDAOImpl udao = UsuariosDAOImpl.getInstance();
		UsuariosModel um = udao.readUserByName(em, user);
		if(type.equals("add"))
			udao.addFriend(em, um, friend);
		else
			udao.deleteFriend(em, um, friend);
		response.sendRedirect("friends");
	}
  }