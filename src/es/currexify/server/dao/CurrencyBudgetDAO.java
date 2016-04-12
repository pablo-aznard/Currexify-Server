package es.currexify.server.dao;

import es.currexify.server.model.CurrencyBudgetModel;

public interface CurrencyBudgetDAO {
	public boolean createEuroBudget(int cardN, String currency, double budget);
	public CurrencyBudgetModel readEuroBudgetById(int cardN);
	public boolean updateCurrencyBudget(int cardN, String currency, double budget);
	public boolean deleteCurrencyBudgetByCardN(int cardN);
}