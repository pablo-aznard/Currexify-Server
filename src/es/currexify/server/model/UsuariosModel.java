package es.currexify.server.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.*;

@Entity
@Table(name="USUARIOS_MODEL")
public class UsuariosModel implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private Long id;
	@Column(name="NAME")
	private String name;
	@Column(name="PASSWORD")
	private String password;
	@Column(name="EMAIL")
	private String email;
	@Column(name="ADDRESS")
	private String address;
	@Column(name="PHONE")
	private String phone;
	@Column(name="CARD_N")
	private String cardN;
	@Column(name="FRIENDS")
	private Set<String> friends;
	@OneToMany
	@JoinColumn(name="USER_HISTORY")
	private List<HistoryModel> histories;
	@OneToMany
	@JoinColumn(name="USER_CURRENCIES")
	private List<CurrencyBudgetModel> userCurrencies;
	@OneToMany(orphanRemoval=true)
	@JoinColumn(name="USER_TRANSACTIONS")
	private List<TransactionModel> userTransactions;
		
	public UsuariosModel(String name, String password, 
			String email, String address, String phone) {
		this.name = name;
		this.password = password;
		this.email = email;
		this.address = address;
		this.phone = phone;
		this.cardN = generateCardN();
		this.friends = new HashSet<String>();
	}
	
	private String generateCardN(){
		Random rand = new Random();
		Long n = rand.nextLong();
		String str = String.valueOf(n);
		return str.substring(1,17);
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
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
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getCardN() {
		return cardN;
	}
	public void setCardN(String cardN) {
		this.cardN = cardN;
	}
	public Set<String> getFriends() {
		return friends;
	}
	public void setFriends(Set<String> friends) {
		this.friends = friends;
	}
	public List<HistoryModel> getHistories(){
		return histories;
	}
	public void setHistories(List<HistoryModel> histories){
		this.histories = histories;
	}
	public List<CurrencyBudgetModel> getUserCurrencies(){
		return userCurrencies;
	}
	public void setUserCurrencies(List<CurrencyBudgetModel> userCurrencies){
		this.userCurrencies = userCurrencies;
	}
	public List<TransactionModel> getUserTransactions() {
		return userTransactions;
	}
	public void setUserTransactions(List<TransactionModel> userTransactions) {
		this.userTransactions = userTransactions;
	}
	
}
