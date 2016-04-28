package es.currexify.server;

import java.io.IOException;
import java.util.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import es.currexify.server.dao.*;
import es.currexify.server.model.*;

@SuppressWarnings("serial")
public class PruebaUsuario extends HttpServlet {
	
	Calendar cal = Calendar.getInstance();
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		resp.setContentType("text/plain");
		
		//CREACIÓN
		EntityManager em = EMFService.get().createEntityManager(); 
		CurrencyExRateDAOImpl cerdao = CurrencyExRateDAOImpl.getInstance();
		CurrencyExRateModel cer1 = new CurrencyExRateModel(1.28, "USDTEST");
		CurrencyExRateModel cer2 = new CurrencyExRateModel(0.96, "GBPTEST");
		cerdao.createCurrencyExRate(em, cer1);
		cerdao.createCurrencyExRate(em, cer2);
		
		UsuariosDAOImpl umdao = UsuariosDAOImpl.getInstance();
		UsuariosModel um = new UsuariosModel("Perico", "qwerasdf", "qwer@asdf.com", "Calle Pinos", "123456789");
		umdao.createUser(em, um);
		UsuariosModel um1 = umdao.readUserById(em, um.getId());
		resp.getWriter().println("");
		

		HistoryModel hm = new HistoryModel(um.getCardN(), "EUR", "GBP", 10.0, "entrante", "prendiente", new Date(), new Date());
		umdao.addHistoryToUser(em, hm, um1);
		
		CurrencyBudgetModel cbm = new CurrencyBudgetModel(um1.getCardN(), "USDTEST", 50.0);
		umdao.addCurrencyBudgetToUser(em, cbm, um1);
		
		resp.getWriter().println("---CREACIÓN---");
		resp.getWriter().println("User ID: "+um1.getId());
		resp.getWriter().println("User Name: "+um1.getName());
		resp.getWriter().println("User Password: "+um1.getPassword());
		resp.getWriter().println("User Email: "+um1.getEmail());
		resp.getWriter().println("User Address: "+um1.getAddress());
		resp.getWriter().println("User Card Number "+um1.getCardN());
		resp.getWriter().println("----------");
		resp.getWriter().println("History IDs: "+hm.getId());
		resp.getWriter().println("History User Card Number: "+hm.getCardN());
		resp.getWriter().println("History Coin: "+hm.getSCoin());
		resp.getWriter().println("History Coin: "+hm.getDCoin());
		resp.getWriter().println("History Amount: "+hm.getAmount());
		resp.getWriter().println("History Type: "+hm.getType());
		resp.getWriter().println("History Origin Date: "+hm.getODate());
		resp.getWriter().println("History End Date: "+hm.getEDate());
		resp.getWriter().println("----------");
		resp.getWriter().println("Currency Budget IDs: "+cbm.getId());
		resp.getWriter().println("Currency Budget Card Number: "+cbm.getCardN());
		resp.getWriter().println("Currency Budget Currency: "+cbm.getCurrency());
		resp.getWriter().println("Currency Budget Amount: "+cbm.getBudget());
		
		//ACTUALIZACIÓN
		
		um1.setName("Perico Actualizado");
		umdao.updateUsuario(em, um1);
		
		HistoryModel hm1 = new HistoryModel(um.getCardN(), "GBP", "EUR", 20.0, "entrante", "completada", new Date(), new Date());
		umdao.addHistoryToUser(em, hm1, um1);
		
		CurrencyBudgetModel cbm1 = new CurrencyBudgetModel(um1.getCardN(), "GBPTEST", 20.0);
		umdao.addCurrencyBudgetToUser(em, cbm1, um1);
		
		resp.getWriter().println(" ");
		resp.getWriter().println("---ACTUALIZACIÓN---");
		resp.getWriter().println("User Name Updated: "+um1.getName());
		resp.getWriter().println("----------");
		resp.getWriter().println("New History IDs: "+hm1.getId());
		resp.getWriter().println("New History User Card Number: "+hm1.getCardN());
		resp.getWriter().println("New History Coin: "+hm1.getSCoin());
		resp.getWriter().println("New History Coin: "+hm1.getDCoin());
		resp.getWriter().println("New History Amount: "+hm1.getAmount());
		resp.getWriter().println("New History Type: "+hm1.getType());
		resp.getWriter().println("New History Date: "+hm1.getODate());
		resp.getWriter().println("New History Date: "+hm1.getEDate());
		resp.getWriter().println("----------");
		resp.getWriter().println("New Currency Budget IDs: "+cbm1.getId());
		resp.getWriter().println("New Currency Budget Card Number: "+cbm1.getCardN());
		resp.getWriter().println("New Currency Budget Currency: "+cbm1.getCurrency());
		resp.getWriter().println("New Currency Budget Amount: "+cbm1.getBudget());
		
		//BORRADO
		resp.getWriter().println(" ");
		resp.getWriter().println("---BORRADO---");
		um1 = umdao.readUserByEmail(em, um.getEmail());
		umdao.deleteUsuario(em, um1);
		HistoryDAOImpl hmdao = HistoryDAOImpl.getInstance();
		resp.getWriter().println("Users Table Size: "+umdao.readUsers(em).size());
		resp.getWriter().println("Histories Table Size: "+hmdao.readHistory(em).size());
		if(umdao.readUserByEmail(em, um.getEmail()) == null) 
			resp.getWriter().println("USUARIO BORRADO CORRECTAMENTE");
		
		cer1 = cerdao.readCurrencyExRatesByCurrency(em, "USDTEST");
		cer2 = cerdao.readCurrencyExRatesByCurrency(em, "GBPTEST");
		
		cerdao.deleteCurrencyExRate(em, cer1);
		cerdao.deleteCurrencyExRate(em, cer2);
		resp.getWriter().println("Currency Rates Table Size: "+cerdao.readCurrencyExRates(em).size());
		if(cerdao.readCurrencyExRates(em).size() == 2)
			resp.getWriter().println("RATIOS DE CAMBIO BORRADOS CORRECTAMENTE");
		em.close();
	}
  }