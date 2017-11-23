package service;

import java.util.List;

import entity.MstUser;

public interface MstUserSvc {
	public void save(MstUser mstUser);
	public void update(MstUser mstUser);
	public void delete(int kodeUser);
	public List<MstUser> findAll();
	public MstUser findOne(int kodeUser);
	public List<MstUser> searchData(String key);
}
