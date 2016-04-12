package es.currexify.server.dao;

import java.util.List;

import javax.persistence.*;

import es.currexify.server.model.CurrencyBudgetModel;

public class CurrencyBudgetDAOImpl implements CurrencyBudgetDAO {

	@Override
	public boolean createCurrencyBudget(int cardN, String currency, double budget) {
		CurrencyBudgetModel cbm = null;
		EntityManager em = EMFService.get().createEntityManager();
		cbm = new CurrencyBudgetModel(cardN, currency, budget);
		em.persist(cbm);
		em.close();
		return true;
	}

	@Override
	public List<CurrencyBudgetModel> readCurrencyBudgetByCardN(int cardN) {
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("select m from CurrencyBudgetModel m");
		List<CurrencyBudgetModel> res = q.getResultList();
		em.close();
		return res;
	}

	@Override
	public boolean updateCurrencyBudget(CurrencyBudgetModel cbm) {

		EntityManager em = EMFService.get().createEntityManager();
		em.merge(cbm);
		em.close();
		return true;
		
	}

	@Override
	public boolean deleteCurrencyBudgetById(int id) {
		EntityManager em = EMFService.get().createEntityManager();
		try {
			CurrencyBudgetModel todo = em.find(CurrencyBudgetModel.class, id);
		 	em.remove(todo);
		} finally {
			 em.close();
		}
		return true;
	}
	
}
