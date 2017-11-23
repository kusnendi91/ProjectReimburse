package service;

import java.util.List;

import entity.MstLevel;

public interface MstLevelSvc {
	public void save(MstLevel mstLevel);
	public void update(MstLevel mstLevel);
	public void delete(int kodeLevel);
	public List<MstLevel> findAll();
	public MstLevel findOne(int kodeLevel);
}
