package es.currexify.server.model;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name="EURO_BUDGET_MODEL")
public class CurrencyBudgetModel implements Serializable {
	
	@Id @GeneratedValue
	@Column(name="ID")
	private int id;
	@Column(name="CARD_N")
	private int cardN;
	@Column(name="CURRENCY")
	private String currency;
	@Column(name="BUDGET")
	private double budget;
	
		
	public CurrencyBudgetModel(int cardN, String currency, double budget) {
		this.cardN = cardN;
		this.currency = currency;
		this.budget = budget;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getCardN() {
		return cardN;
	}


	public void setCardN(int cardN) {
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
		
}
