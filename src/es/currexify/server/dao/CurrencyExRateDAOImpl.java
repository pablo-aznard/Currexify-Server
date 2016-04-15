package es.currexify.server.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import es.currexify.server.model.CurrencyExRateModel;

public class CurrencyExRateDAOImpl implements CurrencyExRateDAO {

	private static CurrencyExRateDAOImpl instance;
	private CurrencyExRateDAOImpl () {
	}
	public static CurrencyExRateDAOImpl getInstance() {
		if (instance == null)
			instance = new CurrencyExRateDAOImpl();
		return instance;
	}
	@Override
	public CurrencyExRateModel createCurrencyExRate(EntityManager em, CurrencyExRateModel cer) {
		em.getTransaction().begin();
		em.persist(cer);
		em.getTransaction().commit();
		return cer;
	}
	
	@Override
	public CurrencyExRateModel createCurrencyExRate(EntityManager em, double euroEx, String currency) {
		CurrencyExRateModel cer = null;
		em.getTransaction().begin();
		cer = new CurrencyExRateModel(euroEx, currency);
		em.persist(cer);
		em.getTransaction().commit();
		return cer;
	}

	@Override
	public List<CurrencyExRateModel> readCurrencyExRates(EntityManager em) {
		Query q = em.createQuery("select c from CurrencyExRateModel c");
		List<CurrencyExRateModel> cerms = q.getResultList();
		return cerms;
	}

	@Override
	public boolean updateCurrencyExRate(EntityManager em, CurrencyExRateModel cerm) {
		em.getTransaction().begin();
		em.merge(cerm);
		em.getTransaction().commit();
		return true;
	}

	@Override
	public boolean deleteCurrencyExRateById(EntityManager em, Long id) {
		em.getTransaction().begin();
		try {
			CurrencyExRateModel all = em.find(CurrencyExRateModel.class, id);
			em.remove(all);
			} finally {
				em.getTransaction().commit();
			}
		return true;
	}
	
}



