package dao;

import java.util.List;

import entity.DetailReimbursement;

public interface DetailReimbursementDao {
	public void save(DetailReimbursement detailReimbursement);
	public void update(DetailReimbursement detailReimbursement);
	public void delete(int kodeDetail);
	public List<DetailReimbursement> findAll();
	public DetailReimbursement findOne(int kodeDetail);
	public List<DetailReimbursement> searchData(String key);
}
