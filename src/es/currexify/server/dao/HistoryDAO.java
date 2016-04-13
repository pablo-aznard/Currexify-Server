package es.currexify.server.dao;

import java.util.List;
import es.currexify.server.model.HistoryModel;

public interface HistoryDAO {

	public HistoryModel createHistory(String cardN,	String coin,
			double amount, String type, String date);
	public List<HistoryModel> readHistory();
	public boolean updateHistory(HistoryModel hm);
	public boolean deleteHistoryById(Long id);
	
}
