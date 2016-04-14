package es.currexify.server.dao;

import java.util.Date;
import java.util.List;
import es.currexify.server.model.HistoryModel;

public interface HistoryDAO {

	public HistoryModel createHistory(String cardN,	String coin,
			double amount, String type, Date date);
	public List<HistoryModel> readHistory();
	public HistoryModel readHistoryById(Long Id);
	public boolean updateHistory(HistoryModel hm);
	public boolean deleteHistoryById(Long id);
	
}
