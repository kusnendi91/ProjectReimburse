package service.impl;

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
	public void update(History history) {
		historyDao.update(history);
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

}
