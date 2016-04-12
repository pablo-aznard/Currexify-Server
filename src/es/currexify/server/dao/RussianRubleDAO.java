package es.currexify.server.dao;

import es.currexify.server.model.RussianRubleBudgetModel;

public interface RussianRubleDAO {
	public boolean createRussianRubleBudget(int cardN, double rubleBudget);
	public RussianRubleBudgetModel readRussianRubleBudgetById(int cardN);
	public boolean updateRussianRubleBudget(int cardN, double rubleBudget);
	public boolean deleteRussianRubleBudgetById(int cardN);
}