package es.currexify.server.dao;

import java.util.List;

import javax.persistence.EntityManager;
import es.currexify.server.model.TransactionModel;

public interface TransactionDAO {
	
	public TransactionModel createTransaction(EntityManager em, TransactionModel tm);
	public List<TransactionModel> readByCurrency(EntityManager em, String sCoin, String dCoin);
	public List<TransactionModel> readByFriendId(EntityManager em, Long friendId);
	public boolean updateTransaction(EntityManager em, TransactionModel tm);
	public boolean deleteTransaction(EntityManager em, TransactionModel tm);

}
