package es.currexify.server.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import es.currexify.server.model.TransactionModel;
import es.currexify.server.model.UsuariosModel;

public class TransactionDAOImpl implements TransactionDAO {
	
	private static TransactionDAOImpl instance;
	private TransactionDAOImpl () {
	}
	public static TransactionDAOImpl getInstance() {
		if (instance == null)
			instance = new TransactionDAOImpl();
		return instance;
	}
	@Override
	public TransactionModel createTransaction(EntityManager em,
			TransactionModel tm) {
		em.getTransaction().begin();
		em.persist(tm);
		em.getTransaction().commit();
		return tm;
	}

	@Override
	public List<TransactionModel> readByCurrency(EntityManager em,
			String sCoin, String dCoin) {
		Query q = em.createQuery("select u from TransactionModel u where u.sCoin=:sCoin and u.dCoin=:dCoin and u.friendId=0");
		q.setParameter("sCoin", sCoin);
		q.setParameter("dCoin", dCoin);
		List<TransactionModel> res = q.getResultList();
		return res;
	}

	@Override
	public List<TransactionModel> readByFriendId(EntityManager em, Long friendId) {
		Query q = em.createQuery("select u from TransactionModel u where u.friendId=:friendId");
		q.setParameter("friendId", friendId);
		List<TransactionModel> res = q.getResultList();
		return res;
	}

	@Override
	public boolean updateTransaction(EntityManager em, TransactionModel tm) {
		em.getTransaction().begin();
		em.merge(tm);
		em.getTransaction().commit();
		return true;
	}

	@Override
	public boolean deleteTransaction(EntityManager em, TransactionModel tm) {
		em.getTransaction().begin();
		em.remove(tm);
		em.getTransaction().commit();
		return true;
	}

}
