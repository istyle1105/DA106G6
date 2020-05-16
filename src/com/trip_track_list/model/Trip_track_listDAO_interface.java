package com.trip_track_list.model;

import java.util.List;

public interface Trip_track_listDAO_interface {
	public void insert(Trip_track_listVO trip_track_listVO);
    public void update(Trip_track_listVO trip_track_listVO);
    public void delete(String trip_id,String mem_no);
    public Trip_track_listVO findByPrimaryKey(String trip_id);
    public List<Trip_track_listVO> getAll();
}
