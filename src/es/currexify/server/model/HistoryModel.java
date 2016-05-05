package es.currexify.server.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import com.google.appengine.api.datastore.Key;

@Entity
@Table(name="HISTORY_MODEL")
public class HistoryModel implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private Key key;
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
	
	public Key getId() {
		return key;
	}
	public void setId(Key key) {
		this.key = key;
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
