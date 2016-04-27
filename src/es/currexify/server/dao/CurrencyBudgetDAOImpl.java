package es.currexify.server.dao;

import java.util.List;

import javax.persistence.*;

import es.currexify.server.model.CurrencyBudgetModel;

public class CurrencyBudgetDAOImpl implements CurrencyBudgetDAO {

	private static CurrencyBudgetDAOImpl instance;
	private CurrencyBudgetDAOImpl () {
	}
	public static CurrencyBudgetDAOImpl getInstance() {
		if (instance == null)
			instance = new CurrencyBudgetDAOImpl();
		return instance;
	}

	@Override
	public CurrencyBudgetModel createCurrencyBudget(EntityManager em, CurrencyBudgetModel cbm){
		em.getTransaction().begin();
		em.persist(cbm);
		em.getTransaction().commit();
		return cbm;
	}

	@Override
	public List<CurrencyBudgetModel> readCurrencyBudgetByCardN(EntityManager em, String cardN) {
		Query q = em.createQuery("select m from CurrencyBudgetModel m where m.cardN = :cardN");
		q.setParameter("cardN", cardN);
		List<CurrencyBudgetModel> res = q.getResultList();	
		return res;
	}

	@Override
	public boolean updateCurrencyBudget(EntityManager em, CurrencyBudgetModel cbm) {
		
		em.getTransaction().begin();
		em.merge(cbm);
		em.getTransaction().commit();
		return true;
		
	}

	@Override
	public boolean deleteCurrencyBudgetById(EntityManager em, Long id) {
		em.getTransaction().begin();
		try {
			CurrencyBudgetModel todo = em.find(CurrencyBudgetModel.class, id);
		 	em.remove(todo);
		} finally {
			em.getTransaction().commit();
			 em.close();
		}
		return true;
	}
	
}
