package com.trip.model;

import java.util.List;
import java.util.Map;

public interface TripDAO_interface {
	public void insert(TripVO tripVO);
    public void update(TripVO tripVO);
    public void delete(String trip_id);
    public TripVO findByPrimaryKey(String trip_id);
    public List<TripVO> getAll();
    //使用0403的萬用複合查詢
    public List<TripVO> getAll(Map<String,String[]>map);
    public List<TripVO> getAllMyTrip(String mem_no);
}
