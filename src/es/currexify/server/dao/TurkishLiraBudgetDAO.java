package es.currexify.server.dao;

import es.currexify.server.model.TurkishLiraBudgetModel;

public interface TurkishLiraBudgetDAO {
	public boolean createTurkishLiraBudget(int cardN, double liraBudget);
	public TurkishLiraBudgetModel readTurkishLiraBudgetById(int cardN);
	public boolean updateTurkishLiraBudget(int cardN, double liraBudget);
	public boolean deleteTurkishLiraBudgetById(int cardN);
}
