package es.currexify.server.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import es.currexify.server.model.HistoryModel;

public class HistoryDAOImpl implements HistoryDAO {

	private static HistoryDAOImpl instance;
	private HistoryDAOImpl () {
	}
	public static HistoryDAOImpl getInstance() {
		if (instance == null)
			instance = new HistoryDAOImpl();
		return instance;
	}
	
	@Override
	public HistoryModel createHistory(String cardN, String coin, double amount, String type, Date date) {
		HistoryModel hm = null;
		EntityManager em = EMFService.get().createEntityManager();
		hm = new HistoryModel(cardN, coin, amount, type, date);
		em.persist(hm);
		em.close();
		return hm;
	}

	@Override
	public List<HistoryModel> readHistory() {
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("select m from HistoryModel m");
		List<HistoryModel> hm = q.getResultList();
		em.close();
		return hm;
	}
	
	@Override
	public HistoryModel readHistoryById(Long id) {
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("select h from HistoryModel h where h.id = :id");
		q.setParameter("id", id);
		HistoryModel res = null;
		List<HistoryModel> hms= q.getResultList();
		if (hms.size() > 0)
			res = (HistoryModel) (q.getResultList().get(0));
		em.close();
		return res; 
	}

	@Override
	public boolean updateHistory(HistoryModel hm) {
		EntityManager em = EMFService.get().createEntityManager();
		em.merge(hm);
		em.close();
		return true;
	}

	@Override
	public boolean deleteHistoryById(Long id) {
		EntityManager em = EMFService.get().createEntityManager();
		try {
			HistoryModel all = em.find(HistoryModel.class, id);
			em.remove(all);
			} finally {
			em.close();
			}
		return true;
	}

}
