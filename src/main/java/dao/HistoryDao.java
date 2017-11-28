package dao;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import entity.History;

public interface HistoryDao {
	public void save(History history);
	//public void update(History history);
	public void delete(int kodeHistory);
	public List<History> findAll();
	public History findOne(int kodeHistory);
	public List<History> searchData(String key);
	public List<History> findUpdate(String nik, String namaProject, Date periode, Timestamp historyDate);
}
