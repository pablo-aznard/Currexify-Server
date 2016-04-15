package es.currexify.server.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import es.currexify.server.model.HistoryModel;

public interface HistoryDAO {

	public HistoryModel createHistory(EntityManager em, HistoryModel hm);
	public HistoryModel createHistory(EntityManager em, String cardN,	String coin,
			double amount, String type, Date date);
	public List<HistoryModel> readHistory(EntityManager em);
	public HistoryModel readHistoryById(EntityManager em, Long Id);
	public boolean updateHistory(EntityManager em, HistoryModel hm);
	public boolean deleteHistoryById(EntityManager em, Long id);
	
}
