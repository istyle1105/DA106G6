package com.trip.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import com.trip.model.TripVO;

public class TripService {
	private TripDAO_interface dao;
	
	public TripService() {
		dao = new TripJNDIDAO();
	}

	public TripVO addTrip(String mem_no,String tour_id,Integer mem_amount
			,Integer days,Date first_date,Date last_date,Integer trip_price,
			Date reg_start,Date reg_deadline,Integer mem_limited) {
		TripVO tripVO = new TripVO();
		
		tripVO.setMem_no(mem_no);
		tripVO.setTour_id(tour_id);
		tripVO.setMem_amount(mem_amount);
		tripVO.setDays(days);
		tripVO.setFirst_date(first_date);
		tripVO.setLast_date(last_date);
		tripVO.setReg_start(reg_start);
		tripVO.setReg_deadline(reg_deadline);
		tripVO.setMem_limited(mem_limited);
		tripVO.setTrip_price(trip_price);
		dao.insert(tripVO);
		
		return tripVO;
	}
	
	public TripVO updateTrip(String mem_no,String tour_id,Integer mem_amount
			,Integer days,Date first_date,Date last_date,Integer trip_price,
			Date reg_start,Date reg_deadline,Integer mem_limited,Integer current_mem
			,String trip_id,Timestamp last_mod_time,Integer trip_status,Integer tour_status) {
		TripVO tripVO = new TripVO();
		
		tripVO.setMem_no(mem_no);
		tripVO.setTour_id(tour_id);
		tripVO.setMem_amount(mem_amount);
		tripVO.setDays(days);
		tripVO.setFirst_date(first_date);
		tripVO.setLast_date(last_date);
		tripVO.setReg_start(reg_start);
		tripVO.setReg_deadline(reg_deadline);
		tripVO.setMem_limited(mem_limited);
		tripVO.setCurrent_mem(current_mem);
		tripVO.setTrip_price(trip_price);
		tripVO.setTrip_id(trip_id);
		tripVO.setLast_mod_time(last_mod_time);
		tripVO.setTrip_status(trip_status);
		tripVO.setTour_status(tour_status);
		
		dao.update(tripVO);
		
		return tripVO;
	}
	public TripVO updateTrip(TripVO tripVO) {		
		dao.update(tripVO);
		
		return tripVO;
	}
	
	public TripVO getOneTrip(String trip_id) {
		return dao.findByPrimaryKey(trip_id);
	}
	
	public List<TripVO> getAll(){
		return dao.getAll();
	}
	
	public List<TripVO> getAll(Map<String, String[]> map){
		return dao.getAll(map);
	}
	
	public List<TripVO> getAllMyTrip(String mem_no){
		return dao.getAllMyTrip(mem_no);
	}
	
}
