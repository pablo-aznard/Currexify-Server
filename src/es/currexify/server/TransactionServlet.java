package es.currexify.server;

import java.io.IOException;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.appengine.labs.repackaged.org.json.JSONArray;
import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;

import es.currexify.server.dao.*;
import es.currexify.server.model.*;

@SuppressWarnings("serial")
public class TransactionServlet extends HttpServlet {

	String[] currencies = { "EUR", "USD" };

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
		req.getSession().setAttribute("currencies", currencies);
		
		JSONArray jArray = new JSONArray();		
		JSONObject json = new JSONObject();
		
		try {
			json.put("quantity", "300€");
			json.put("type", "0.95");
			json.put("user", "Pablo Pavo");
			
			for (int i=0; i<3; i++) {
				jArray.put(json);
			}
			
			String jsonText = jArray.toString();
			req.getSession().setAttribute("history", jsonText);
		} catch (JSONException e) {
			e.printStackTrace();
		}		
		
		RequestDispatcher view = req.getRequestDispatcher("transaction.jsp");
		view.forward(req, resp);
	}
	
	public void doPost (HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		String amount = req.getParameter("amount");
		String from = req.getParameter("currST");
		String to = req.getParameter("currND");
		resp.getWriter().println(amount + " " + from + " " + to);
	}
  }
