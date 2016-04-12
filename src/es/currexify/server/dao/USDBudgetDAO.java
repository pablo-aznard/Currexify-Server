package es.currexify.server.dao;

import es.currexify.server.model.USDBudgetModel;

public interface USDBudgetDAO {
	public boolean createUSDBudget(int cardN, double usdBudget);
	public USDBudgetModel readUSDBudgetById(int cardN);
	public boolean updateUSDBudget(int cardN, double usdBudget);
	public boolean deleteUSDBudgetById(int cardN);
}
