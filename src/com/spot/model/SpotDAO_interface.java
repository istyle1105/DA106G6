package com.spot.model;

import java.util.List;

public interface SpotDAO_interface {
	public void insert(SpotVO spotVO);
	public SpotVO insert2(SpotVO spotVO);
	public void update(SpotVO spotVO);
	public void delete(String spot_id);
	public SpotVO findByPrimaryKey(String spot_id);
	public List<SpotVO> getAll();
	public List<SpotVO> getAllActive();
}
