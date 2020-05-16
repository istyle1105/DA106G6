package com.trip_report.model;

import java.util.List;

public interface Trip_reportDAO_interface {
	public void insert(Trip_reportVO trip_reportVO);
    public void update(Trip_reportVO trip_reportVO);
    public void delete(String t_rep_id);
    public Trip_reportVO findByPrimaryKey(String t_rep_id);
    public List<Trip_reportVO> getAll();
}
