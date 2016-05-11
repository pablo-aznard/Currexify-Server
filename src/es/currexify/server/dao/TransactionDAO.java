package es.currexify.server.dao;

import java.util.List;

import javax.persistence.EntityManager;
import es.currexify.server.model.TransactionModel;

public interface TransactionDAO {
	
	public TransactionModel createTransaction(EntityManager em, TransactionModel tm);
	public List<TransactionModel> readTransactions(EntityManager em);
	public List<TransactionModel> readByCurrency(EntityManager em, String sCoin, String dCoin);
	public boolean updateTransaction(EntityManager em, TransactionModel tm);

}
