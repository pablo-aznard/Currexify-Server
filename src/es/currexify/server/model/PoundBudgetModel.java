package es.currexify.server.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class PoundBudgetModel implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8439350929727381517L;
	@Id
	@GeneratedValue
	private int id;
	private int cardN;
	private double poundBudget;
	
	public PoundBudgetModel(int id, int cardN, double poundBudget) {
		this.id = id;
		this.cardN = cardN;
		this.poundBudget = poundBudget;
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
	public double getPoundBudget() {
		return poundBudget;
	}
	public void setPoundBudget(double poundBudget) {
		this.poundBudget = poundBudget;
	}
	
	

}
