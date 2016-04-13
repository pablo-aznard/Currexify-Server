package es.currexify.server.model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name="HISTORY_MODEL", uniqueConstraints={@UniqueConstraint(columnNames={"ID"})})
public class HistoryModel implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue
	@Column(name="ID")
	private int id;
	@Column(name="CARD_N")
	private int cardN;
	@Column(name="COIN")
	private String coin;
	@Column(name="AMOUNT")
	private double amount;
	@Column(name="TYPE")
	private String type;
	@Column(name="DATE")
	private String date;
		
	public HistoryModel(int cardN, String coin, double amount, String type, String date) {
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
