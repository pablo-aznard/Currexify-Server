package es.currexify.server.model;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name="RUSSIAN_RUBLE_BUDGET_MODEL")
public class RussianRubleBudgetModel implements Serializable {
	
	@Id @GeneratedValue
	@Column(name="ID")
	private int id;
	@Column(name="CARD_N")
	private int cardN;
	@Column(name="RUBLE_BUDGET")
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
