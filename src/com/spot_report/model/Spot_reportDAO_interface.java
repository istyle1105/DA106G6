package com.spot_report.model;

import java.util.List;

public interface Spot_reportDAO_interface {
	public void insert(Spot_reportVO spot_reportVO);
	public void update(Spot_reportVO spot_reportVO);
	public void delete(String spot_report_id);
	public Spot_reportVO findByPrimaryKey(String spot_report_id);
	public List<Spot_reportVO> getAll();
}
