package es.currexify.server.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class TurkishLiraBudgetModel implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1472215383120815596L;
	@Id
	@GeneratedValue
	private int id;
	private int cardN;
	private double liraBudget;
	
	public TurkishLiraBudgetModel(int id, int cardN, double liraBudget) {
		this.id = id;
		this.cardN = cardN;
		this.liraBudget = liraBudget;
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
	public double getLiraBudget() {
		return liraBudget;
	}
	public void setLiraBudget(double liraBudget) {
		this.liraBudget = liraBudget;
	}

}
