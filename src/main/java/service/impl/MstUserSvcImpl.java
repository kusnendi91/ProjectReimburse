package service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.MstUserDao;
import entity.MstUser;
import service.MstUserSvc;

@Service("mstUserSvc")
public class MstUserSvcImpl implements MstUserSvc{

	@Autowired
	MstUserDao mstUserDao;
	
	@Override
	public void save(MstUser mstUser) {
		mstUserDao.save(mstUser);
	}

	@Override
	public void update(MstUser mstUser) {
		mstUserDao.update(mstUser);
	}

	@Override
	public void delete(int kodeUser) {
		mstUserDao.delete(kodeUser);
	}

	@Override
	public List<MstUser> findAll() {
		return mstUserDao.findAll();
	}

	@Override
	public MstUser findOne(int kodeUser) {
		return mstUserDao.findOne(kodeUser);
	}

	@Override
	public List<MstUser> searchData(String key) {
		return mstUserDao.searchData(key);
	}

}
