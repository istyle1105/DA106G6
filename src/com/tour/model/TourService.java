package com.tour.model;

import java.util.List;


public class TourService {
	private TourDAO_interface dao;
	
	public TourService() {
		dao = new Tour_JNDIDAO();
	}
	
	public TourVO addTour(String mem_no,String tour_name,String tour_detail,byte[] tour_pic) {
		TourVO tourVO = new TourVO();
		
		tourVO.setMem_no(mem_no);
		tourVO.setTour_name(tour_name);
		tourVO.setTour_detail(tour_detail);
		tourVO.setTour_pic(tour_pic);
		TourVO tourVO2 = dao.insertGetVO(tourVO);
		
		return tourVO2;
	}
	
	public TourVO updateTour(String tour_id,String tour_name,String tour_detail,byte[] tour_pic ,Integer display) {
		TourVO tourVO = new TourVO();
		
		tourVO.setTour_id(tour_id);
		tourVO.setTour_name(tour_name);
		tourVO.setTour_detail(tour_detail);
		tourVO.setTour_pic(tour_pic);
		tourVO.setDisplay(display);
		dao.update(tourVO);
		
		return tourVO;
	}
	
	public TourVO disableTour(TourVO tourVO) {
		tourVO.setDisplay(new Integer(1));//改成下架狀態
		dao.update(tourVO);
		return tourVO;
	}
	
	public TourVO tourInTrip(String tour_id) {
		TourVO tourVO = dao.findByPrimaryKey(tour_id);
		tourVO.setDisplay(2);//改成不可修改刪除狀態
		dao.update(tourVO);
		return tourVO;
	}
	
	public TourVO tourInTripCancel(String tour_id) {
		TourVO tourVO = dao.findByPrimaryKey(tour_id);
		tourVO.setDisplay(0);//揪團取消時,改為可以編輯及再次開啟揪團的狀態
		dao.update(tourVO);
		return tourVO;
	}
	
	public void deleteTour(String tour_id) {
		dao.delete(tour_id);
	}
	
	public TourVO getOneTour(String tour_id) {
		return dao.findByPrimaryKey(tour_id);
	}
	
	public List<TourVO> getAll(){
		return dao.getAll();
	}
	public List<TourVO> getAllMyTour(String mem_no){
		return dao.getAllMyTour(mem_no);
	}
}
