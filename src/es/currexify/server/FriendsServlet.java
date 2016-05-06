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
import com.google.appengine.labs.repackaged.org.json.JSONObject;

import es.currexify.server.dao.*;
import es.currexify.server.model.*;

@SuppressWarnings("serial")
public class FriendsServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		String url = "";
		String urlLinktext = "Login";
		String user = "";		
		String email = (String) req.getSession().getAttribute("login");
		
		JSONObject json = new JSONObject();
		List<String> jray = new ArrayList<String>();
		EntityManager em = EMFService.get().createEntityManager();
		UsuariosDAOImpl udao = UsuariosDAOImpl.getInstance();
		UsuariosModel usuario = udao.readUserByEmail(em, email);
		Set<String> friends = usuario.getFriends();
		em.close();
		if (email != null) {
			try {	
				for (String friend : friends) {
					json.put("friend", friend);
					jray.add(json.toString());
				}
				req.getSession().setAttribute("friends", jray);
			} catch (JSONException e) {
				e.printStackTrace();
			}
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
		String add = (String) request.getParameter("add");
		
		EntityManager em = EMFService.get().createEntityManager();
		UsuariosDAOImpl udao = UsuariosDAOImpl.getInstance();
		UsuariosModel um = udao.readUserByName(em, user);
		if(add.equals("yes"))
			udao.addFriend(em, um, friend);
		else
			udao.deleteFriend(em, um, friend);
		response.sendRedirect("friends");
	}
  }