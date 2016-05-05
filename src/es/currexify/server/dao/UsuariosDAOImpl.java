package es.currexify.server.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import es.currexify.server.model.*;

public class UsuariosDAOImpl implements UsuariosDAO {

	private static UsuariosDAOImpl instance;
	private UsuariosDAOImpl () {
	}
	public static UsuariosDAOImpl getInstance() {
		if (instance == null)
			instance = new UsuariosDAOImpl();
		return instance;
	}
	
	@Override
	public UsuariosModel createUser(EntityManager em, UsuariosModel um) {
		
		em.getTransaction().begin();
		em.persist(um);
		em.getTransaction().commit();
		
		return um;
	}
	
	@Override
	public List<UsuariosModel> readUsers(EntityManager em) {
		Query q = em.createQuery("select u from UsuariosModel u");
		List<UsuariosModel> res = q.getResultList();
		return res;
	}

	@Override
	public UsuariosModel readUserById(EntityManager em, Long id) {
		
		Query q = em.createQuery("select u from UsuariosModel u where u.id = :id");
		q.setParameter("id", id);
		UsuariosModel res = null;
		List<UsuariosModel> ums= q.getResultList();
		if (ums.size() > 0)
			res = (UsuariosModel) (q.getResultList().get(0));
		
		return res; 
	}

	@Override
	public UsuariosModel readUserByName(EntityManager em, String name) {
		
		Query q = em.createQuery("select u from UsuariosModel u where u.name = :name");
		q.setParameter("name", name);
		UsuariosModel res = null;
		List<UsuariosModel> ums= q.getResultList();
		if (ums.size() > 0)
			res = (UsuariosModel) (q.getResultList().get(0));
		
		return res; 
	}
	
	@Override
	public UsuariosModel readUserByEmail(EntityManager em, String email) {
		
		Query q = em.createQuery("select u from UsuariosModel u where u.email = :email");
		q.setParameter("email", email);
		UsuariosModel res = null;
		List<UsuariosModel> ums= q.getResultList();
		if (ums.size() > 0)
			res = (UsuariosModel) (q.getResultList().get(0));
		
		return res; 
	}

	@Override
	public boolean updateUsuario(EntityManager em, UsuariosModel um) {

		
		em.getTransaction().begin();
		em.merge(um);
		em.getTransaction().commit();
		
		return true;

	}
	
	@Override
	public boolean addFriend(EntityManager em, UsuariosModel um, String friend) {
		
		em.getTransaction().begin();
		Set<String> userFriends = um.getFriends();
		userFriends.add(friend);
		em.merge(um);
		em.getTransaction().commit();
		
		return true;
	}
	
	@Override
	public boolean deleteFriend(EntityManager em, UsuariosModel um, String friend) {
		
		em.getTransaction().begin();
		Set<String> userFriends = um.getFriends();
		userFriends.remove(friend);
		em.merge(um);
		em.getTransaction().commit();
		
		return true;
	}
	
	@Override
	public boolean addHistoryToUser(EntityManager em, HistoryModel hm, UsuariosModel um) {

		em.getTransaction().begin();
		List<HistoryModel> hml = um.getHistories();
		hml.add(hm);
		um.setHistories(hml);
		em.merge(um);
		em.persist(hm);
		em.getTransaction().commit();
		return true;
	}
	
	@Override
	public boolean addCurrencyBudgetToUser(EntityManager em, CurrencyBudgetModel cbm, UsuariosModel um) {
		
		em.getTransaction().begin();
		List<CurrencyBudgetModel> cbml = um.getUserCurrencies();
		cbml.add(cbm);
		um.setUserCurrencies(cbml);
		em.merge(um);
		em.getTransaction().commit();
		
		return true;
	}
	
	public boolean addTransactionToUser(EntityManager em, TransactionModel tm, UsuariosModel um) {
		
		em.getTransaction().begin();
		List<TransactionModel> tml = um.getUserTransactions();
		if (tml == null) tml = new ArrayList<TransactionModel>();
		tml.add(tm);
		um.setUserTransactions(tml);
		em.merge(um);
		em.getTransaction().commit();
		
		return true;
	}
	
	public boolean deleteUserTransaction(EntityManager em, TransactionModel tm, UsuariosModel um) {
		
		em.getTransaction().begin();
		List<TransactionModel> tml = um.getUserTransactions();
		tml.remove(tm);
		um.setUserTransactions(tml);
		em.merge(um);
		em.getTransaction().commit();
		
		return true;
	}

	@Override
	public boolean deleteUsuario(EntityManager em, UsuariosModel um) {
		
		em.getTransaction().begin();
		em.remove(um);
		em.getTransaction().commit();
		return true;
	}
	
	@Override 
	public boolean isUserUnique(EntityManager em, String user) {
		Query q = em.createQuery("select u from UsuariosModel u where u.name = :name");
		q.setParameter("name", user);
		List<UsuariosModel> ums= q.getResultList();
		if (ums.size() > 0)
			return false;
		else
			return true;
	}
	
	@Override
	public boolean isEmailUnique(EntityManager em, String email) {
		Query q = em.createQuery("select u from UsuariosModel u where u.email = :email");
		q.setParameter("email", email);
		List<UsuariosModel> ums= q.getResultList();
		return !(ums.size() > 0);
		
	}

}
