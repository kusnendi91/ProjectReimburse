package service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.MstLevelDao;
import entity.MstLevel;
import service.MstLevelSvc;

@Service("mstLevelSvc")
public class MstLevelSvcImpl implements MstLevelSvc{

	@Autowired
	MstLevelDao mstLevelDao;
	
	@Override
	public void save(MstLevel mstLevel) {
		mstLevelDao.save(mstLevel);
	}

	@Override
	public void update(MstLevel mstLevel) {
		mstLevelDao.update(mstLevel);
	}

	@Override
	public void delete(int kodeLevel) {
		mstLevelDao.delete(kodeLevel);
	}

	@Override
	public List<MstLevel> findAll() {
		return mstLevelDao.findAll();
	}

	@Override
	public MstLevel findOne(int kodeLevel) {
		return mstLevelDao.findOne(kodeLevel);
	}

}
