package es.currexify.server.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class RussianRubleBudgetModel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3883157755597152513L;
	@Id
	@GeneratedValue
	private int id;
	private int cardN;
	private double rubleBudget;
	
	public RussianRubleBudgetModel(int id, int cardN, double rubleBudget) {
		this.id = id;
		this.cardN = cardN;
		this.rubleBudget = rubleBudget;
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
	public double getRubleBudget() {
		return rubleBudget;
	}
	public void setRubleBudget(double rubleBudget) {
		this.rubleBudget = rubleBudget;
	}
	
	

}
