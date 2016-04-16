package es.currexify.server.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import es.currexify.server.model.CurrencyExRateModel;
import es.currexify.server.model.UsuariosModel;

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
	public CurrencyExRateModel readCurrencyExRatesByCurrency(EntityManager em, String currency) {
		Query q = em.createQuery("select c from CurrencyExRateModel c where c.currency = :currency");
		q.setParameter("currency", currency);
		CurrencyExRateModel res = null;
		List<CurrencyExRateModel> cerml= q.getResultList();
		if (cerml.size() > 0)
			res = (CurrencyExRateModel) (q.getResultList().get(0));
		
		return res; 
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



