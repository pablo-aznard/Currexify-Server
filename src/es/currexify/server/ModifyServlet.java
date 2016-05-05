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
public class ModifyServlet extends HttpServlet {
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
			req.getSession().setAttribute("name", usuario.getName());
			req.getSession().setAttribute("pass", usuario.getPassword());
			req.getSession().setAttribute("email", usuario.getEmail());
			req.getSession().setAttribute("address", usuario.getAddress());
			req.getSession().setAttribute("phone", usuario.getPhone());
		}

		req.getSession().setAttribute("user", user);
		req.getSession().setAttribute("url", url);
		req.getSession().setAttribute("urlLinktext", urlLinktext);
		RequestDispatcher view = req.getRequestDispatcher("modProfile.jsp");
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
		System.out.println(udao.isEmailUnique(em, email));
		if(udao.isEmailUnique(em, email)){
			UsuariosModel um = udao.readUserByEmail(em, (String)request.getSession().getAttribute("email"));
			um.setName(user);
			um.setPassword(pass);
			um.setEmail(email);
			um.setAddress(address);
			um.setPhone(phone);
			udao.updateUsuario(em, um);
			request.getSession().setAttribute("login", um.getEmail());
			response.sendRedirect("profile");
		}
		else{
			response.sendRedirect("modificar?error=true");
		}
		em.close();
		
		
	}
  }