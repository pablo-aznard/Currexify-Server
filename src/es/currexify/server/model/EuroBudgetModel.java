package es.currexify.server.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class EuroBudgetModel implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4257261149199513139L;
	@Id
	@GeneratedValue
	private int id;
	private int cardN;
	private double euroBudget;
		
	public EuroBudgetModel(int id, int cardN, double euroBudget) {
		super();
		this.id = id;
		this.cardN = cardN;
		this.euroBudget = euroBudget;
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
	public double getEuroBudget() {
		return euroBudget;
	}
	public void setEuroBudget(double euroBudget) {
		this.euroBudget = euroBudget;
	}
	
}
