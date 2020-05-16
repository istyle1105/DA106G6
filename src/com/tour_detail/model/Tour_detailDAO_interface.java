package com.tour_detail.model;

import java.util.List;

public interface Tour_detailDAO_interface {
	public void insert(Tour_detailVO tour_detailVO);
	public void update(Tour_detailVO tour_detailVO);
	public void delete(String tour_detail_id);
	public int deleteByTour_id(String tour_id);
	public Tour_detailVO findByPrimaryKey(String tour_detail_id);
	public List<Tour_detailVO> findByTour_id(String tour_id);
	public List<Tour_detailVO> getAll();
}
