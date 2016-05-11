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
	public HistoryModel createHistory(EntityManager em, HistoryModel hm) {
		em.getTransaction().begin();
		em.persist(hm);
		em.getTransaction().commit();
		return hm;
	}

	@Override
	public List<HistoryModel> readHistory(EntityManager em) {
		Query q = em.createQuery("select m from HistoryModel m");
		List<HistoryModel> hm = q.getResultList();
		return hm;
	}	
	
	@Override
	public boolean updateHistory(EntityManager em, HistoryModel hm) {
		em.getTransaction().begin();
		em.merge(hm);
		em.getTransaction().commit();
		return true;
	}

}
