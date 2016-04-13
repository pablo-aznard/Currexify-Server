package es.currexify.server.dao;

import java.util.List;

import es.currexify.server.model.CurrencyBudgetModel;
import es.currexify.server.model.UsuariosModel;

public interface UsuariosDAO {
	
	public UsuariosModel createUser(String name, String password, String email,
			String address, String phone, String cardN);
	public List<UsuariosModel> readUsers();
	public UsuariosModel readUserById(long id);
	public UsuariosModel readUserByName(String name);
	public boolean updateUsuario(UsuariosModel um);
	public boolean deleteUsuarioById(long id);
	
}
