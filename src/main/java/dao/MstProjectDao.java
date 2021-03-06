package dao;

import java.util.List;

import entity.MstProject;

public interface MstProjectDao {
	public void save(MstProject mstProject);
	public void update(MstProject mstProject);
	public void delete(int kodeProject);
	public List<MstProject> findAll();
	public MstProject findOne(int kodeProject);
	public List<MstProject> searchData(String key);
}
