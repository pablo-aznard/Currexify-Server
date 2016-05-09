package es.currexify.server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.currexify.server.dao.CurrencyExRateDAOImpl;
import es.currexify.server.dao.EMFService;
import es.currexify.server.dao.HistoryDAOImpl;
import es.currexify.server.dao.UsuariosDAOImpl;
import es.currexify.server.model.CurrencyBudgetModel;
import es.currexify.server.model.CurrencyExRateModel;
import es.currexify.server.model.HistoryModel;
import es.currexify.server.model.TransactionModel;
import es.currexify.server.model.UsuariosModel;

@SuppressWarnings("serial")
public class PruebaMatch extends HttpServlet {
	
	Calendar cal = Calendar.getInstance();
	private static ArrayList tml = new ArrayList<>();
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		resp.setContentType("text/plain");
		EntityManager em = EMFService.get().createEntityManager(); 
		UsuariosDAOImpl udao = UsuariosDAOImpl.getInstance();
		TransactionModel tm1 = new TransactionModel("1", "EUR", "GBP", 10.0, new Date());
		cal.add(cal.DATE, -1);
		TransactionModel tm2 = new TransactionModel("1", "GBP", "EUR", 10.0, cal.getTime());
		cal.add(cal.DATE, 4);
		TransactionModel tm3 = new TransactionModel("1", "EUR", "GBP", 10.0, cal.getTime());
		cal.add(cal.DATE, 2);
		TransactionModel tm4 = new TransactionModel("1", "EUR", "GBP", 10.0, cal.getTime());
		cal.add(cal.DATE, -20);
		TransactionModel tm5 = new TransactionModel("1", "EUR", "GBP", 10.0, cal.getTime());
		UsuariosModel um1 = new UsuariosModel("fist", "first", "first@first.com", "first street", "111111111");
		UsuariosModel um2 = new UsuariosModel("second", "second", "second@second.com", "second street", "222222222");
		UsuariosModel um3 = new UsuariosModel("third", "third", "third@third.com", "third street", "333333333");
		UsuariosModel um4 = new UsuariosModel("fourth", "fourth", "fourth@fourth.com", "fourth street", "444444444");
		
		udao.addTransactionToUser(em, tm1, um1);
		udao.addTransactionToUser(em, tm2, um2);
		System.out.println(um1.getUserTransactions().size());
		
		em.close();
	}
 }