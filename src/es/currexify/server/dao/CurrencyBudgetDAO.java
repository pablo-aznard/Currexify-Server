package es.currexify.server.dao;

import java.util.List;

import es.currexify.server.model.CurrencyBudgetModel;

public interface CurrencyBudgetDAO {
	public CurrencyBudgetModel createCurrencyBudget(int cardN, String currency, double budget);
	public List<CurrencyBudgetModel> readCurrencyBudgetByCardN(int cardN);
	public boolean updateCurrencyBudget(CurrencyBudgetModel cbm);
	public boolean deleteCurrencyBudgetById(int cardN);
}