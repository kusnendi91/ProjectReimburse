package dao;

import java.util.List;

import entity.MstAbsensiKaryawan;

public interface MstAbsensiKaryawanDao {
	public void save(MstAbsensiKaryawan mstAbsensiKaryawan);
	public void update(MstAbsensiKaryawan mstAbsensiKaryawan);
	public void delete(int kodeAbsen);
	public List<MstAbsensiKaryawan> findAll();
	public MstAbsensiKaryawan findOne(int kodeAbsen);
	public List<MstAbsensiKaryawan> searchData(String key);
}
