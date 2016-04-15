package es.currexify.server.dao;

import java.util.List;

import javax.persistence.EntityManager;

import es.currexify.server.model.CurrencyBudgetModel;

public interface CurrencyBudgetDAO {
	public CurrencyBudgetModel createCurrencyBudget(EntityManager em, CurrencyBudgetModel cbm);
	public CurrencyBudgetModel createCurrencyBudget(EntityManager em, String cardN, String currency, double budget);
	public List<CurrencyBudgetModel> readCurrencyBudgetByCardN(EntityManager em, String cardN);
	public boolean updateCurrencyBudget(EntityManager em, CurrencyBudgetModel cbm);
	public boolean deleteCurrencyBudgetById(EntityManager em, Long id);
	
}