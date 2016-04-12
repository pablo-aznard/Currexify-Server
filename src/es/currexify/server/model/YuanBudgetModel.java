package es.currexify.server.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class YuanBudgetModel implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8481607170111561719L;
	@Id
	@GeneratedValue
	private int id;
	private int cardN;
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
