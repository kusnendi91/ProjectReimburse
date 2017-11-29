package service;

import java.util.List;

import entity.MstKaryawan;

public interface MstKaryawanSvc {
	public List<MstKaryawan> findAllData();
	public List<MstKaryawan> findAllNotUser();
	public void save (MstKaryawan mstKaryawan);
	public void update (MstKaryawan mstKaryawan);
//	public void delete (String nikKaryawan);
	public MstKaryawan findOne (String nikKaryawan);
	public List<MstKaryawan> searchData(String key);
}
