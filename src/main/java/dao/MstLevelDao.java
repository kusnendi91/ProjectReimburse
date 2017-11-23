package dao;

import java.util.List;

import entity.MstLevel;

public interface MstLevelDao {
	public void save(MstLevel mstLevel);
	public void update(MstLevel mstLevel);
	public void delete(int kodeLevel);
	public List<MstLevel> findAll();
	public MstLevel findOne(int kodeLevel);
}
