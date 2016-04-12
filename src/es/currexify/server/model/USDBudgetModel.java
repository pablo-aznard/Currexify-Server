package es.currexify.server.model;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name="USD_BUDGET_MODEL")
public class USDBudgetModel implements Serializable {

	@Id @GeneratedValue
	@Column(name="ID")
	private int id;
	@Column(name="CARD_N")
	private int cardN;
	@Column(name="USD_BUDGET")
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
