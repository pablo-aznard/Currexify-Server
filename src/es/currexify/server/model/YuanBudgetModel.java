package es.currexify.server.model;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name="YUAN_BUDGET_MODEL")
public class YuanBudgetModel implements Serializable {
	
	@Id @GeneratedValue
	@Column(name="ID")
	private int id;
	@Column(name="CARD_N")
	private int cardN;
	@Column(name="YUAN_BUDGET")
	private double yuanBudget;
	
	public YuanBudgetModel(int id, int cardN, double yuanBudget) {
		this.id = id;
		this.cardN = cardN;
		this.yuanBudget = yuanBudget;
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
	public double getYuanBudget() {
		return yuanBudget;
	}
	public void setYuanBudget(double yuanBudget) {
		this.yuanBudget = yuanBudget;
	}
	
	

}
