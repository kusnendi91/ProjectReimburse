package service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.MstProjectDao;
import entity.MstProject;
import service.MstProjectSvc;

@Service("mstProjectSvc")
public class MstProjectSvcImpl implements MstProjectSvc{

	@Autowired
	MstProjectDao mstProjectDao;
	
	@Override
	public void save(MstProject mstProject) {
		mstProjectDao.save(mstProject);
	}

	@Override
	public void update(MstProject mstProject) {
		mstProjectDao.update(mstProject);
	}

	@Override
	public void delete(int kodeProject) {
		mstProjectDao.delete(kodeProject);
	}

	@Override
	public List<MstProject> findAll() {
		return mstProjectDao.findAll();
	}

	@Override
	public MstProject findOne(int kodeProject) {
		return mstProjectDao.findOne(kodeProject);
	}

	@Override
	public List<MstProject> searchData(String key) {
		return mstProjectDao.searchData(key);
	}

	@Override
	public MstProject findNama(String namaProject) {
		return mstProjectDao.findNama(namaProject);
	}

}
