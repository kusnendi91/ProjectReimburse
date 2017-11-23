package service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.DetailReimbursementDao;
import entity.DetailReimbursement;
import service.DetailReimbursementSvc;

@Service("detailReimbursementSvc")
public class DetailReimbursementSvcImpl implements DetailReimbursementSvc
{

	@Autowired
	DetailReimbursementDao detailReimbursementDao;
	
	@Override
	public void save(DetailReimbursement detailReimbursement) {
		detailReimbursementDao.save(detailReimbursement);
	}

	@Override
	public void update(DetailReimbursement detailReimbursement) {
		detailReimbursementDao.update(detailReimbursement);
	}

	@Override
	public void delete(int kodeDetail) {
		detailReimbursementDao.delete(kodeDetail);
	}

	@Override
	public List<DetailReimbursement> findAll() {
		return detailReimbursementDao.findAll();
	}

	@Override
	public DetailReimbursement findOne(int kodeDetail) {
		return detailReimbursementDao.findOne(kodeDetail);
	}

	@Override
	public List<DetailReimbursement> searchData(String key) {
		return detailReimbursementDao.searchData(key);
	}

}
