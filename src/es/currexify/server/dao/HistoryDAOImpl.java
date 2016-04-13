package es.currexify.server.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import es.currexify.server.model.HistoryModel;

public class HistoryDAOImpl implements HistoryDAO {

	@Override
	public boolean createHistory(int id, int cardN, String coin, double amount, String type, String date) {
		HistoryModel hm = null;
		EntityManager em = EMFService.get().createEntityManager();
		hm = new HistoryModel(cardN, coin, amount, type, date);
		em.persist(hm);
		em.close();
		return true;
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
	public boolean updateHistory(HistoryModel hm) {
		EntityManager em = EMFService.get().createEntityManager();
		em.merge(hm);
		em.close();
		return true;
	}

	@Override
	public boolean deleteHistoryById(int id) {
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
