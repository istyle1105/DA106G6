package com.trip_list.model;

import java.sql.Timestamp;
import java.util.List;



public class Trip_listService {
	private Trip_listDAO_interface dao;
	
	public Trip_listService() {
		dao = new Trip_listJNDIDAO();
	}

	public Trip_listVO addTrip_list(String trip_id,String mem_no) {
		Trip_listVO trip_listVO = new Trip_listVO();
		
		trip_listVO.setTrip_id(trip_id);
		trip_listVO.setMem_no(mem_no);
		
		dao.insert(trip_listVO);
		
		return trip_listVO;
	}
	
	public Trip_listVO updateTrip_list(String trip_id,String mem_no,Integer check_status
			,Integer rate,String comment_content,Timestamp comment_time) {
		Trip_listVO trip_listVO = new Trip_listVO();
		
		trip_listVO.setCheck_status(check_status);
		trip_listVO.setRate(rate);
		trip_listVO.setComment_content(comment_content);
		trip_listVO.setComment_time(comment_time);
		trip_listVO.setTrip_id(trip_id);
		trip_listVO.setMem_no(mem_no);
		
		dao.update(trip_listVO);
		
		return trip_listVO;
	}
	
	public void deleteTrip_list(String trip_id,String mem_no) {
		dao.delete(trip_id,mem_no);
	}
	
	public Trip_listVO getOneTrip_list(String trip_id,String mem_no) {
		return dao.findByPrimaryKey(trip_id,mem_no);
	}
	
	public List<Trip_listVO> getAll(){
		return dao.getAll();
	}
	public List<Trip_listVO> getAllMyMember(String trip_id){
		return dao.getAllByTrip_id(trip_id);
	}
	public List<Trip_listVO> getAllMyTrip(String mem_no){
		return dao.getAllByMem_no(mem_no);
	}
}
