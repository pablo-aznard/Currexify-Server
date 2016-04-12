package es.currexify.server.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
@Entity
public class UsuariosModel implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5022988561549940818L;

	@Id
	@GeneratedValue
	private int id;
	
	private String name;
	private String password;
	private String email;
	private String address;
	private int phone;
	private int cardN;
	
	public UsuariosModel(int id, String name, String password, String email, String address, int phone, int cardN) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.email = email;
		this.address = address;
		this.phone = phone;
		this.cardN = cardN;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getPhone() {
		return phone;
	}
	public void setPhone(int phone) {
		this.phone = phone;
	}
	public int getCardN() {
		return cardN;
	}
	public void setCardN(int cardN) {
		this.cardN = cardN;
	}
	

}
