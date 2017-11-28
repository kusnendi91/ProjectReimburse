package dao;

import java.util.List;

import entity.MstKaryawan;

public interface MstKaryawanDao {
	public void save(MstKaryawan mstKaryawan);
	public void update(MstKaryawan mstKaryawan);
	public void delete(String nikKaryawan);
	public List<MstKaryawan> findAll();
	public List<MstKaryawan> findAllNotUser();
	public MstKaryawan findOne(String nikKaryawan);
	public MstKaryawan findOneAbsen(String noAbsen);
	public List<MstKaryawan> searchData(String key);
}
