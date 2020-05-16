package com.trip_list.model;

import java.util.List;

public interface Trip_listDAO_interface {
	public void insert(Trip_listVO trip_listVO);
    public void update(Trip_listVO trip_listVO);
    public void delete(String trip_id,String mem_no);
    public Trip_listVO findByPrimaryKey(String trip_id,String mem_no);
    public List<Trip_listVO> getAllByTrip_id(String trip_id);
    public List<Trip_listVO> getAllByMem_no(String mem_no);
    public List<Trip_listVO> getAll();
}
