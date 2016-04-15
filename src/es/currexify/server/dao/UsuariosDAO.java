package es.currexify.server.dao;

import java.util.List;

import javax.persistence.EntityManager;

import es.currexify.server.model.CurrencyBudgetModel;
import es.currexify.server.model.HistoryModel;
import es.currexify.server.model.UsuariosModel;

public interface UsuariosDAO {
	
	public UsuariosModel createUser(EntityManager em, UsuariosModel um);
	public UsuariosModel createUser(EntityManager em, String name, String password, String email,
			String address, String phone, String cardN);
	public List<UsuariosModel> readUsers(EntityManager em);
	public UsuariosModel readUserById(EntityManager em, Long id);
	public UsuariosModel readUserByName(EntityManager em, String name);
	public boolean updateUsuario(EntityManager em, UsuariosModel um);
	public boolean deleteUsuarioById(EntityManager em, Long id);
	public boolean addHistoryToUser(EntityManager em, HistoryModel hm, UsuariosModel um);
	public boolean addCurrencyBudgetToUser(EntityManager em, CurrencyBudgetModel cbm, UsuariosModel um);
	
}
