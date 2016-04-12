package es.currexify.server.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import es.currexify.server.model.CurrencyBudgetModel;
import es.currexify.server.model.UsuariosModel;

public class UsuariosDAOImpl implements UsuariosDAO {

	@Override
	public boolean createUser(String name, String password, String email,
			String address, int phone, int cardN) {
		UsuariosModel um = null;
		EntityManager em = EMFService.get().createEntityManager();
		um = new UsuariosModel(name, password, email, address,
				phone, cardN);
		em.persist(um);
		em.close();
		return true;
	}

	@Override
	public List<UsuariosModel> readUsers() {
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("select m from CurrencyBudgetModel m");
		List<UsuariosModel> res = q.getResultList();
		em.close();
		return res;
	}

	@Override
	public UsuariosModel readUserById(int id) {
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("select u from TFG u where u.id = :id");
		q.setParameter("ID", id);
		UsuariosModel res = null;
		List<UsuariosModel> ums= q.getResultList();
		if (ums.size() > 0)
			res = (UsuariosModel) (q.getResultList().get(0));
		em.close();
		return res; 
	}

	@Override
	public UsuariosModel readUserByName(String name) {
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("select u from TFG u where u.name = :name");
		q.setParameter("NAME", name);
		UsuariosModel res = null;
		List<UsuariosModel> ums= q.getResultList();
		if (ums.size() > 0)
			res = (UsuariosModel) (q.getResultList().get(0));
		em.close();
		return res; 
	}

	@Override
	public boolean updateUsuario(UsuariosModel um) {

		EntityManager em = EMFService.get().createEntityManager();
		em.merge(um);
		em.close();
		return true;

	}

	@Override
	public boolean deleteUsuarioById(int id) {
		EntityManager em = EMFService.get().createEntityManager();
		try {
			UsuariosModel todo = em.find(UsuariosModel.class, id);
		 	em.remove(todo);
		} finally {
			 em.close();
		}
		return true;
	}

}
