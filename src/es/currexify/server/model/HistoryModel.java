package es.currexify.server.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class HistoryModel implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4433150505590981000L;
	@Id
	@GeneratedValue
	private int id;
	private int cardN;
	private String coin;
	private double amount;
	private String type;
	private String date;
		
	public HistoryModel(int id, int cardN, String coin, double amount, String type, String date) {
		super();
		this.id = id;
		this.cardN = cardN;
		this.coin = coin;
		this.amount = amount;
		this.type = type;
		this.date = date;
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
	public String getCoin() {
		return coin;
	}
	public void setCoin(String coin) {
		this.coin = coin;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	

}
