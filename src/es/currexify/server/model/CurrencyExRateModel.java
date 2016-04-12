package es.currexify.server.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class CurrencyExRateModel implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3430242647280169365L;
	@Id
	@GeneratedValue
	private int id;
	private double euroEx;
	
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
	
	
}
