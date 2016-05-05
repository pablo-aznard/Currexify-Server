package es.currexify.server;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import org.datanucleus.transaction.TransactionManager;

import es.currexify.server.dao.*;
import es.currexify.server.model.*;

public class Match extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		EntityManager em = EMFService.get().createEntityManager();
		TransactionDAOImpl transdao = TransactionDAOImpl.getInstance();
		List<TransactionModel> eur_gbp = transdao.readByCurrency(em, "EUR", "GBP");
		List<TransactionModel> gbp_eur = transdao.readByCurrency(em, "GBP", "EUR");
		
		List<TransactionModel> eur_usd = transdao.readByCurrency(em, "USD", "EUR");
		List<TransactionModel> usd_eur = transdao.readByCurrency(em, "EUR", "USD");
		
		List<TransactionModel> gbp_usd = transdao.readByCurrency(em, "GBP", "USD");
		List<TransactionModel> usd_gbp = transdao.readByCurrency(em, "USD", "GBP");
		
		if(eur_gbp.size() > 0) {
			if(gbp_eur.size() > 0) {
				for(TransactionModel trans : eur_gbp) {
					if(trans.getEDate().after(new Date())){
						for(TransactionModel trans2 : gbp_eur) {
							//todo
						}
					}
				}
			}
			else {
				//todo
			}
		}
		else {
			if(gbp_eur.size() > 0) {
				//todo
			}
		}
	}
}