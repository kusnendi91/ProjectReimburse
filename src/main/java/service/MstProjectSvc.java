package service;

import java.util.List;

import entity.MstProject;
import entity.MstUser;

public interface MstProjectSvc {
	public void save(MstProject mstProject);
	public void update(MstProject mstProject);
	public void delete(int kodeProject);
	public List<MstProject> findAll();
	public MstProject findOne(int kodeProject);
	public List<MstProject> searchData(String key);
}
