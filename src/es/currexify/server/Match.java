package es.currexify.server;

import java.io.IOException;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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

		Comparator<TransactionModel> tmComparator = new Comparator<TransactionModel>() {
			@Override
			public int compare(TransactionModel o1, TransactionModel o2) {
				return o1.getEDate().compareTo(o2.getEDate());
			}
		}; 
		
		Collections.sort(eur_gbp, tmComparator);
		Collections.sort(gbp_eur, tmComparator);
		Collections.sort(eur_usd, tmComparator);
		Collections.sort(usd_eur, tmComparator);
		Collections.sort(gbp_usd, tmComparator);
		Collections.sort(usd_gbp, tmComparator);
		
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
						for(TransactionModel trans2 : list2) {
							double destAmount = trans2.getAmountLeft();
							double destAmountConverted = getConverted(trans2.getSCoin(), trans2.getDCoin(), destAmount);
							if (left == destAmountConverted) {
								//TODO casarlos
								EntityManager em = EMFService.get().createEntityManager();
								
								UsuariosDAOImpl udao = UsuariosDAOImpl.getInstance();
								UsuariosModel um1 = udao.readUserById(em, trans.getId().getParent().getId());
								UsuariosModel um2 = udao.readUserById(em, trans2.getId().getParent().getId());
								
								HistoryModel hm1s = new HistoryModel(
										um1.getCardN(), trans.getSCoin(), left, "Saliente", new Date());
								HistoryModel hm1e = new HistoryModel(
										um1.getCardN(), trans.getDCoin(), destAmount, "Entrante", new Date());
								udao.addHistoryToUser(em, hm1s, um1);
								udao.addHistoryToUser(em, hm1e, um1);
								
								HistoryModel hm2s = new HistoryModel(
										um2.getCardN(), trans2.getSCoin(), destAmount, "Saliente", new Date());
								HistoryModel hm2e = new HistoryModel(
										um2.getCardN(), trans2.getDCoin(), left, "Entrante", new Date());
								udao.addHistoryToUser(em, hm2s, um2);
								udao.addHistoryToUser(em, hm2e, um2);
								
								CurrencyBudgetModel cbm1 = new CurrencyBudgetModel(
										trans.getCardN(), trans.getDCoin(), destAmount);
								udao.updateCurrency(em, um1, cbm1);
								/*List<CurrencyBudgetModel> cbml1 = new ArrayList<CurrencyBudgetModel>();
								cbml1.add(cbm1);
								um1.setUserCurrencies(cbml1);
								*/
								CurrencyBudgetModel cbm2 = new CurrencyBudgetModel(
										trans2.getCardN(), trans.getDCoin(), left);
								udao.updateCurrency(em, um2, cbm2);
								/*List<CurrencyBudgetModel> cbml2 = new ArrayList<CurrencyBudgetModel>();
								cbml2.add(cbm2);
								um1.setUserCurrencies(cbml2);
								*/
								em.close();
								
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
	
	private String getCurrencySymbol(String currencyName) {
		switch (currencyName) {
		case "EUR":
			return "€";
		case "USD":
			return "$";
		case "GBP":
			return "£";
		default:
			return "";
		}
	}

	private double getConverted(String from, String to, double oAmount) {
		EntityManager em = EMFService.get().createEntityManager();
		CurrencyExRateDAOImpl cerdao = CurrencyExRateDAOImpl.getInstance();
		double cAmount = 0.0;

		if (from.equals("EUR")) {
			CurrencyExRateModel cer = cerdao.readCurrencyExRatesByCurrency(em, to);
			cAmount = oAmount * cer.getEuroEx();
		} else if (to.equals("EUR")) {
			CurrencyExRateModel cer = cerdao.readCurrencyExRatesByCurrency(em, from);
			cAmount = oAmount / cer.getEuroEx();
		} else if (from.equals("USD")) {
			CurrencyExRateModel cer1 = cerdao.readCurrencyExRatesByCurrency(em, from);
			CurrencyExRateModel cer2 = cerdao.readCurrencyExRatesByCurrency(em, to);
			cAmount = oAmount / cer1.getEuroEx() * cer2.getEuroEx();
		} else if (from.equals("GBP")) {
			CurrencyExRateModel cer1 = cerdao.readCurrencyExRatesByCurrency(em, from);
			CurrencyExRateModel cer2 = cerdao.readCurrencyExRatesByCurrency(em, to);
			cAmount = oAmount / cer1.getEuroEx() * cer2.getEuroEx();
		}
		return cAmount;
	}

	
}