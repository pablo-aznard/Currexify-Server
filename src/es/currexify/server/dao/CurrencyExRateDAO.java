package es.currexify.server.dao;

import java.util.List;

import javax.persistence.EntityManager;

import es.currexify.server.model.CurrencyExRateModel;

public interface CurrencyExRateDAO {
	
	public CurrencyExRateModel createCurrencyExRate(EntityManager em, CurrencyExRateModel cer);
	public CurrencyExRateModel createCurrencyExRate(EntityManager em, double euroEx, String currency);
	public List<CurrencyExRateModel> readCurrencyExRates(EntityManager em);
	public CurrencyExRateModel readCurrencyExRatesByCurrency(EntityManager em, String currency);
	public boolean updateCurrencyExRate(EntityManager em, CurrencyExRateModel cerm);
	public boolean deleteCurrencyExRate(EntityManager em, CurrencyExRateModel cbm);

}