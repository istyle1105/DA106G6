package com.tour.model;

import java.util.List;

public interface TourDAO_interface {
	public void insert(TourVO tourVO);
	public TourVO insertGetVO(TourVO tourVO);
	public void update(TourVO tourVO);
	public void delete(String tour_id);
	public TourVO findByPrimaryKey(String tour_id);
	public List<TourVO> getAll();
	public List<TourVO> getAllMyTour(String mem_no);

}
