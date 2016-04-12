package es.currexify.server.model;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name="EURO_BUDGET_MODEL")
public class EuroBudgetModel implements Serializable {
	
	@Id @GeneratedValue
	@Column(name="ID")
	private int id;
	@Column(name="CARD_N")
	private int cardN;
	@Column(name="EURO_BUDGET")
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
