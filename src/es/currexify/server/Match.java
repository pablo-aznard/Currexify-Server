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
		
		doBetterMatch(eur_gbp, gbp_eur);
	}
	
	private void doBetterMatch (List<TransactionModel> list1, List<TransactionModel> list2) {
		double totalAmount1 = 0;
		double totalAmount2 = 0;
		
		for (TransactionModel tm : list1) {
//			if (tm.getEDate().getTime() > new Date().getTime()+90000)
				totalAmount1 += tm.getAmountLeft();
		}
		for (TransactionModel tm : list2) {
//			if (tm.getEDate().getTime() > new Date().getTime()+90000)
			totalAmount2 += tm.getAmountLeft();
		}
		
		double totalAmount1Conv = getConverted(list1.get(0).getSCoin(), list1.get(0).getDCoin(), totalAmount1);
		double totalAmount2Conv = getConverted(list2.get(0).getSCoin(), list2.get(0).getDCoin(), totalAmount2);
		if (totalAmount1 < totalAmount2Conv){
			for (TransactionModel tm : list1) {
				// TODO completar todas
			}
			for (TransactionModel tm : list2) {
				if (totalAmount1Conv < tm.getAmountLeft()) {
					// TODO completar tm hasta donde se pueda
					
					totalAmount1Conv = 0;
					break;
				}
				totalAmount1Conv -= tm.getAmountLeft();
				// TODO completar tm
			}
		}
		else if (totalAmount1 > totalAmount2Conv) {
			for (TransactionModel tm : list2) {
				// TODO completar todas
			}
			for (TransactionModel tm : list1) {
				if (totalAmount2Conv < tm.getAmountLeft()) {
					// TODO completar tm hasta donde se pueda
					
					totalAmount2Conv = 0;
					break;
				}
				totalAmount2Conv -= tm.getAmountLeft();
				// TODO completar tm
			}
		}
		else {
			for (TransactionModel tm : list1) {
				// TODO completar todas
			}
			for (TransactionModel tm : list2) {
				// TODO completar todas
			}
		}
		
		return;	
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