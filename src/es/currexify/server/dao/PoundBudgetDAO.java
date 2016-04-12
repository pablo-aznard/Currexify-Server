package es.currexify.server.dao;

import es.currexify.server.model.PoundBudgetModel;

public interface PoundBudgetDAO {
	public boolean createPoundBudget(int cardN, double poundBudget);
	public PoundBudgetModel readPoundBudgetById(int cardN);
	public boolean updatePoundBudget(int cardN, double poundBudget);
	public boolean deletePoundBudgetById(int cardN);
}
