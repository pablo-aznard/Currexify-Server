package es.currexify.server.model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name="POUND_BUDGET_MODEL")
public class PoundBudgetModel implements Serializable {
	
	@Id @GeneratedValue
	@Column(name="ID")
	private int id;
	@Column(name="CARD_N")
	private int cardN;
	@Column(name="POUND_BUDGET")
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
