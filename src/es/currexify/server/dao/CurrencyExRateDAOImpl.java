package es.currexify.server.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import es.currexify.server.model.CurrencyExRateModel;

public class CurrencyExRateDAOImpl implements CurrencyExRateDAO {

	@Override
	public boolean createCurrencyExRate(double euroEx, String currency) {
		CurrencyExRateModel cer = null;
		EntityManager em = EMFService.get().createEntityManager();
		cer = new CurrencyExRateModel(euroEx, currency);
		em.persist(cer);
		em.close();
		return true;
	}

	@Override
	public List<CurrencyExRateModel> readCurrencyExRates() {
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("select m from CurrencyExRateModel m");
		List<CurrencyExRateModel> cerms = q.getResultList();
		em.close();
		return cerms;
	}

	@Override
	public boolean updateCurrencyExRate(CurrencyExRateModel cerm) {
		EntityManager em = EMFService.get().createEntityManager();
		em.merge(cerm);
		em.close();
		return true;
	}

	@Override
	public boolean deleteCurrencyExRateById(int id) {
		EntityManager em = EMFService.get().createEntityManager();
		try {
			CurrencyExRateModel all = em.find(CurrencyExRateModel.class, id);
			em.remove(all);
			} finally {
			em.close();
			}
		return true;
	}
	
}



