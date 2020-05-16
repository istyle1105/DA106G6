package com.trip_report.model;

import java.util.List;


public class Trip_reportService {
	private Trip_reportDAO_interface dao;

	public Trip_reportService() {
		dao = new Trip_reportJNDIDAO();
	}
	
	public Trip_reportVO addTrip_report(String trip_id,String mem_no,String t_rep_content) {
		Trip_reportVO trip_reportVO = new Trip_reportVO();
		
		trip_reportVO.setMem_no(mem_no);
		trip_reportVO.setT_rep_content(t_rep_content);
		trip_reportVO.setTrip_id(trip_id);
		
		dao.insert(trip_reportVO);
		
		return 	trip_reportVO;
	}
	
	public Trip_reportVO updateTrip_report(String t_rep_content,String t_rep_status,String t_rep_id) {
		Trip_reportVO trip_reportVO = new Trip_reportVO();
		
		trip_reportVO.setT_rep_id(t_rep_id);
		trip_reportVO.setT_rep_content(t_rep_content);
		trip_reportVO.setT_rep_status(new Integer(t_rep_status));
		
		dao.insert(trip_reportVO);
		
		return 	trip_reportVO;
	}
	
	public Trip_reportVO getOneTrip_report(String t_rep_id) {
		return dao.findByPrimaryKey(t_rep_id);
	}
	
	public List<Trip_reportVO> getAll(){
		return dao.getAll();
	}
}
