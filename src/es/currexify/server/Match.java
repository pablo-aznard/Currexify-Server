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
		
		doMatch(eur_gbp, gbp_eur);
	}
	
	private void doMatch (List<TransactionModel> list1, List<TransactionModel> list2) {
		TransactionModel bestTrans = null; 
		TransactionModel ndTrans = null;
		
		if(list1.size() > 0) {
			if(list2.size() > 0) {
				for(TransactionModel trans : list1) {
					double originAmount = trans.getAmountLeft();
					double left = originAmount;
					if(trans.getEDate().after(new Date())){                     	 // SE TIENE QUE HACER AHORA
						// TODO RIGHT NOW
						for(TransactionModel trans2 : list2) {
							double destAmount = trans2.getAmountLeft();
							if (left == destAmount) {
								//TODO casarlos
							} else {
								if (left > destAmount) {
									left -= destAmount;
									// TODO completar trans2 y actualizar trans
								}
							}							
						}
						// TODO comleta sistema
					} else { 		           								 // TENEMOS MAZO TIEMPO PARA HACERLO
						left = originAmount;
						for (TransactionModel trans2 : list2) {
							double destAmount = trans2.getAmountLeft();
							
							if (originAmount == destAmount) {
								// TODO casarlos
							} else if ((originAmount - destAmount) < left) {
								ndTrans = bestTrans;
								bestTrans = trans2;
								left = originAmount - destAmount;
							} else {
								continue;
							}
						}
						// TODO casar bestTrans con trans y completar bestTrans
					}
				}
			} else { 																	// LIST2 ESTA TOPE VACIA
				for (TransactionModel trans : list1) {
					if (trans.getEDate().after(new Date())) {
						// TODO completar con el sistema
					}
				}
			}
		} else { 																		// LIST1 ESTA TOPE VACIA
			if (list2.size() > 0) {
				for (TransactionModel trans : list2) {
					if (trans.getEDate().after(new Date())) {
						// TODO completar con el sistema
					}
				}
			}
		}
	}
	
}