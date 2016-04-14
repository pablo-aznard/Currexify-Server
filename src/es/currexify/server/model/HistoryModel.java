package es.currexify.server.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name="HISTORY_MODEL")
public class HistoryModel implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private Long id;
	@Column(name="CARD_N")
	private String cardN;
	@Column(name="COIN")
	private String coin;
	@Column(name="AMOUNT")
	private double amount;
	@Column(name="TYPE")
	private String type;
	@Column(name="DATE")
	private Date date;
		
	public HistoryModel(String cardN, String coin, double amount, String type, Date date) {
		this.cardN = cardN;
		this.coin = coin;
		this.amount = amount;
		this.type = type;
		this.date = date;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCardN() {
		return cardN;
	}
	public void setCardN(String cardN) {
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
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
}
