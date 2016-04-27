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
	public List<HistoryModel> readPendingHistory(EntityManager em) {
		Query q = em.createQuery("select h from HistoryModel h where h.state = :state");
		q.setParameter("state", "pendiente");
		List<HistoryModel> res = q.getResultList();
		return res; 
	}
	
	@Override
	public HistoryModel readHistoryById(EntityManager em, Long id) {
		Query q = em.createQuery("select h from HistoryModel h where h.id = :id");
		q.setParameter("id", id);
		HistoryModel res = null;
		List<HistoryModel> hms= q.getResultList();
		if (hms.size() > 0)
			res = (HistoryModel) (q.getResultList().get(0));
		return res; 
	}

	@Override
	public boolean updateHistory(EntityManager em, HistoryModel hm) {
		em.getTransaction().begin();
		em.merge(hm);
		em.getTransaction().commit();
		return true;
	}

	@Override
	public boolean deleteHistoryById(EntityManager em, Long id) {
		em.getTransaction().begin();
		try {
			HistoryModel all = em.find(HistoryModel.class, id);
			em.remove(all);
			} finally {
				em.getTransaction().commit();
			}
		return true;
	}

}
