package es.currexify.server.dao;

import java.util.List;

import es.currexify.server.model.CurrencyExRateModel;

public interface CurrencyExRateDAO {

	public boolean createCurrencyExRate(double euroEx, String currency);
	public List<CurrencyExRateModel> readCurrencyExRates();
	public boolean updateCurrencyExRate(String currency);
	public boolean deleteCurrencyExRateById(int id);
	public boolean deleteCurrencyExRateByCurrency(String currency);

}