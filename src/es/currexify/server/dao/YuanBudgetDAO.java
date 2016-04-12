package es.currexify.server.dao;

import es.currexify.server.model.YuanBudgetModel;

public interface YuanBudgetDAO {
	public boolean createYuanBudget(int cardN, double yuanBudget);
	public YuanBudgetModel readYuanBudgetById(int cardN);
	public boolean updateYuanBudget(int cardN, double yuanBudget);
	public boolean deleteYuanBudgetById(int cardN);
}
