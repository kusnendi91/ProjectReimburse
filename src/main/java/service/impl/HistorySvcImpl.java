package service.impl;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.HistoryDao;
import entity.History;
import service.HistorySvc;

@Service("historySvc")
public class HistorySvcImpl implements HistorySvc{

	@Autowired
	HistoryDao historyDao;
	
	@Override
	public void save(History history) {
		historyDao.save(history);
	}

	@Override
	public void delete(int kodeHistory) {
		historyDao.delete(kodeHistory);
	}

	@Override
	public List<History> findAll() {
		return historyDao.findAll();
	}

	@Override
	public History findOne(int kodeHistory) {
		return historyDao.findOne(kodeHistory);
	}

	@Override
	public List<History> searchData(String key) {
		return historyDao.searchData(key);
	}

	@Override
	public List<History> findUpdate(String nik, String namaProject, Date periode, Timestamp historyDate) {
		return historyDao.findUpdate(nik, namaProject, periode, historyDate);
	}

}
