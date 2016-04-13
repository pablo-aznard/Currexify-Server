package es.currexify.server.model;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name="CURRENCY_EXCHANGE_RATE_MODEL", uniqueConstraints={@UniqueConstraint(columnNames={"ID"})})
public class CurrencyExRateModel implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue
	@Column(name="ID")
	private int id;
	@Column(name="EURO_EX")
	private double euroEx;
	@Column(name="CURRENCY")
	private String currency;
	
	public CurrencyExRateModel(double euroEx, String currency) {
		this.euroEx = euroEx;
		this.currency = currency;
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
