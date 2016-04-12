package es.currexify.server.dao;

import es.currexify.server.model.EuroBudgetModel;

public interface EuroBudgetDAO {
	public boolean createEuroBudget(int cardN, double euroBudget);
	public EuroBudgetModel readEuroBudgetById(int cardN);
	public boolean updateEuroBudget(int cardN, double euroBudget);
	public boolean deleteEuroBudgetById(int cardN);
}