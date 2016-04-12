package es.currexify.server.model;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name="CURRENCY_EXCHANGE_RATE_MODEL")
public class CurrencyExRateModel implements Serializable {
	
	@Id @GeneratedValue
	@Column(name="ID")
	private int id;
	@Column(name="EURO_EX")
	private double euroEx;
	@Column(name="CURRENCY")
	private String currency;
	
	public CurrencyExRateModel(int id, double euroEx) {
		this.id = id;
		this.euroEx = euroEx;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getEuroEx() {
		return euroEx;
	}
	public void setEuroEx(double euroEx) {
		this.euroEx = euroEx;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	
	
}
