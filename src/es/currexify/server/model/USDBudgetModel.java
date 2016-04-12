package es.currexify.server.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
@Entity
public class USDBudgetModel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5595039122184805252L;
	@Id
	@GeneratedValue
	private int id;
	private int cardN;
	private double usdBudget;
	
	public USDBudgetModel(int id, int cardN, double usdBudget) {
		this.id = id;
		this.cardN = cardN;
		this.usdBudget = usdBudget;
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
	public double getUsdBudget() {
		return usdBudget;
	}
	public void setUsdBudget(double usdBudget) {
		this.usdBudget = usdBudget;
	}
	
	
	

}
