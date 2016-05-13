package es.currexify.server.dao;

import java.util.List;

import javax.persistence.EntityManager;

import es.currexify.server.model.*;
public interface UsuariosDAO {
	
	public UsuariosModel createUser(EntityManager em, UsuariosModel um);
	public List<UsuariosModel> readUsers(EntityManager em);
	public UsuariosModel readUserById(EntityManager em, Long id);
	public UsuariosModel readUserByCardN(EntityManager em, String cardN);
	public UsuariosModel readUserByName(EntityManager em, String name);
	public UsuariosModel readUserByEmail(EntityManager em, String email);
	public boolean updateUsuario(EntityManager em, UsuariosModel um);
	public boolean updateCurrency(EntityManager em, UsuariosModel um, CurrencyBudgetModel cbm);
	public boolean deleteUsuario(EntityManager em, UsuariosModel um);
	public boolean addFriend(EntityManager em, UsuariosModel um, String friend);
	public boolean deleteFriend(EntityManager em, UsuariosModel um, String friend);
	public boolean addHistoryToUser(EntityManager em, HistoryModel hm, UsuariosModel um);
	public boolean addCurrencyBudgetToUser(EntityManager em, CurrencyBudgetModel cbm, UsuariosModel um);
	public boolean addTransactionToUser(EntityManager em, TransactionModel tm, UsuariosModel um);
	public boolean deleteUserTransaction(EntityManager em, TransactionModel tm, UsuariosModel um);
	public boolean isUserUnique(EntityManager em, String user);
	public boolean isEmailUnique(EntityManager em, String email);
	boolean updateTransaction(EntityManager em, UsuariosModel um,
			TransactionModel tm);
	boolean deleteUserHistory(EntityManager em, HistoryModel hm,
			UsuariosModel um);
	
}
