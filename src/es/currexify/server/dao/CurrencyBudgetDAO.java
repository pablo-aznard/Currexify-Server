package es.currexify.server.dao;

import java.util.List;

import es.currexify.server.model.CurrencyBudgetModel;

public interface CurrencyBudgetDAO {
	public CurrencyBudgetModel createCurrencyBudget(String cardN, String currency, double budget);
	public List<CurrencyBudgetModel> readCurrencyBudgetByCardN(String cardN);
	public boolean updateCurrencyBudget(CurrencyBudgetModel cbm);
	public boolean deleteCurrencyBudgetById(Long id);
}