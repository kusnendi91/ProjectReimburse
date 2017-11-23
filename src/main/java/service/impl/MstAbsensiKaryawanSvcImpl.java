package service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.MstAbsensiKaryawanDao;
import entity.MstAbsensiKaryawan;
import service.MstAbsensiKaryawanSvc;

@Service("mstAbsensiKaryawanSvc")
public class MstAbsensiKaryawanSvcImpl implements MstAbsensiKaryawanSvc{

	@Autowired
	MstAbsensiKaryawanDao mstAbsensiKaryawanDao;
	
	@Override
	public void save(MstAbsensiKaryawan mstAbsensiKaryawan) {
		mstAbsensiKaryawanDao.save(mstAbsensiKaryawan);
		
	}

	@Override
	public void update(MstAbsensiKaryawan mstAbsensiKaryawan) {
		mstAbsensiKaryawanDao.update(mstAbsensiKaryawan);
	}

	@Override
	public void delete(int kodeAbsen) {
		mstAbsensiKaryawanDao.delete(kodeAbsen);
	}

	@Override
	public List<MstAbsensiKaryawan> findAll() {
		return mstAbsensiKaryawanDao.findAll();
	}

	@Override
	public MstAbsensiKaryawan findOne(int kodeAbsen) {
		return mstAbsensiKaryawanDao.findOne(kodeAbsen);
	}

	@Override
	public List<MstAbsensiKaryawan> searchData(String key) {
		return mstAbsensiKaryawanDao.searchData(key);
	}

}
