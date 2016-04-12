package es.currexify.server.dao;

import java.util.List;

import es.currexify.server.model.UsuariosModel;

public interface UsuariosDAO {
	
	public boolean createUser(String name, String password, String email,
			String address, int phone, int cardN);
	public List<UsuariosModel> readUsers();
	public UsuariosModel readUserById(int id);
	public UsuariosModel readUserByName(String name);
	public boolean updateUsuario(String name, String password, String email,
			String address, int phone, int cardN);
	public boolean deleteUsuarioById(int id);
	
}
