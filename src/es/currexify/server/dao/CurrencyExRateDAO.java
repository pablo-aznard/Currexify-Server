package es.currexify.server.dao;

import java.util.List;

import javax.persistence.EntityManager;

import es.currexify.server.model.CurrencyExRateModel;

public interface CurrencyExRateDAO {
	
	public CurrencyExRateModel createCurrencyExRate(EntityManager em, CurrencyExRateModel cer);
	public CurrencyExRateModel createCurrencyExRate(EntityManager em, double euroEx, String currency);
	public List<CurrencyExRateModel> readCurrencyExRates(EntityManager em);
	public boolean updateCurrencyExRate(EntityManager em, CurrencyExRateModel cerm);
	public boolean deleteCurrencyExRateById(EntityManager em, Long id);

}