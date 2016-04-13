package es.currexify.server.model;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name="CURRENCY_EXCHANGE_RATE_MODEL")
public class CurrencyExRateModel implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private Long id;
	@Column(name="EURO_EX")
	private double euroEx;
	@Column(name="CURRENCY")
	private String currency;
	
	public CurrencyExRateModel(double euroEx, String currency) {
		this.euroEx = euroEx;
		this.currency = currency;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
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
