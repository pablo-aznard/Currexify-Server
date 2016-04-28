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
public class LogoutServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		req.getSession().removeAttribute("login");
		resp.sendRedirect("/login");	
	}
  }