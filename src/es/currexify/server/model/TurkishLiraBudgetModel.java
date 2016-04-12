package es.currexify.server.model;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name="TURKISH_LIRA_BUDGET_MODEL")
public class TurkishLiraBudgetModel implements Serializable {
	
	@Id @GeneratedValue
	@Column(name="ID")
	private int id;
	@Column(name="CARD_N")
	private int cardN;
	@Column(name="LIRA_BUDGET")
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
