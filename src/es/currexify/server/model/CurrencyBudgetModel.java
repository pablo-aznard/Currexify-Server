package es.currexify.server.model;

import java.io.Serializable;

import javax.persistence.*;

import com.google.appengine.api.datastore.Key;

@Entity
@Table(name="EURO_BUDGET_MODEL")
public class CurrencyBudgetModel implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private Key key;
	@Column(name="CARD_N")
	private String cardN;
	@Column(name="CURRENCY")
	private String currency;
	@Column(name="BUDGET")
	private double budget;
	@Column(name="BLOCKED")
	private double blocked;
	
	public CurrencyBudgetModel(String cardN, String currency, double budget) {
		this.cardN = cardN;
		this.currency = currency;
		this.budget = budget;
		this.blocked = 0.0;
	}
	public Key getId() {
		return key;
	}
	public void setId(Key key) {
		this.key = key;
	}
	public String getCardN() {
		return cardN;
	}
	public void setCardN(String cardN) {
		this.cardN = cardN;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public double getBudget() {
		return budget;
	}
	public void setBudget(double budget) {
		this.budget = budget;
	}
	public double getBlocked() {
		return blocked;
	}
	public void setBlocked(double blocked) {
		this.blocked = blocked;
	}
}
