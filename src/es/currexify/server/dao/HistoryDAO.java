package es.currexify.server.dao;

import java.util.List;
import es.currexify.server.model.HistoryModel;

public interface HistoryDAO {

	public boolean createHistory(int id, int cardN,	String coin,
			double amount, String type, String date);
	public List<HistoryModel> readHistory();
	public boolean updateHistory(int id, int cardN,	String coin,
			double amount, String type, String date);
	public boolean deleteHistoryById(int id);
	
}
