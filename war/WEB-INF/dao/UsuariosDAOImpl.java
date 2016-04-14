package es.currexify.server.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import es.currexify.server.model.UsuariosModel;

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
	public UsuariosModel createUser(String name, String password, String email,
			String address, String phone, String cardN) {
		UsuariosModel um = null;
		EntityManager em = EMFService.get().createEntityManager();
		um = new UsuariosModel(name, password, email, address,
				phone, cardN);
		em.persist(um);
		em.close();
		return um;
	}

	@Override
	public List<UsuariosModel> readUsers() {
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("select u from UsuariosModel u");
		List<UsuariosModel> res = q.getResultList();
		em.close();
		return res;
	}

	@Override
	public UsuariosModel readUserById(Long id) {
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("select u from UsuariosModel u where u.id = :id");
		q.setParameter("id", id);
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
		Query q = em.createQuery("select u from UsuariosModel u where u.name = :name");
		q.setParameter("name", name);
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
	public boolean deleteUsuarioById(Long id) {
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