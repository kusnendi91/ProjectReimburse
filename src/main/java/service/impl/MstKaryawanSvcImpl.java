package service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.MstKaryawanDao;
import entity.MstKaryawan;
import service.MstKaryawanSvc;

@Service("mstKaryawanSvc")
public class MstKaryawanSvcImpl implements MstKaryawanSvc{

	@Autowired
	MstKaryawanDao mstKaryawanDao;
	
	@Override
	public List<MstKaryawan> findAllData() {
		return mstKaryawanDao.findAll();
	}

	@Override
	public void save(MstKaryawan mstKaryawan) {
		mstKaryawanDao.save(mstKaryawan);
	}

	@Override
	public void update(MstKaryawan mstKaryawan) {
		mstKaryawanDao.update(mstKaryawan);
	}

	@Override
	public void delete(String nikKaryawan) {
		mstKaryawanDao.delete(nikKaryawan);
	}

	@Override
	public MstKaryawan findOne(String nikKaryawan) {
		return mstKaryawanDao.findOne(nikKaryawan);
	}

	@Override
	public List<MstKaryawan> searchData(String key) {
		return mstKaryawanDao.searchData(key);
	}

}
