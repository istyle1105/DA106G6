package com.tour_detail.model;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.spot.model.SpotService;
import com.spot.model.SpotVO;

import my_util.TimeDiff;


public class Tour_detailService {
private Tour_detailDAO_interface dao;
	
	public Tour_detailService() {
		dao = new Tour_detail_JNDIDAO();
	}
	
	public Tour_detailVO addTour_detail(String tour_id,String spot_id,Integer stay_time,String act_descrip,byte[] act_pic,Timestamp start_time) {
		Tour_detailVO tour_detailVO = new Tour_detailVO();
		
		tour_detailVO.setTour_id(tour_id);
		tour_detailVO.setSpot_id(spot_id);
		tour_detailVO.setStay_time(stay_time);
		tour_detailVO.setAct_descrip(act_descrip);
		tour_detailVO.setAct_pic(act_pic);
		tour_detailVO.setStart_time(start_time);
		dao.insert(tour_detailVO);
		
		return tour_detailVO;
	}
	
	public Tour_detailVO updateTour_detail(String tour_detail_id,Integer stay_time,String act_descrip,byte[] act_pic,Timestamp start_time) {
		Tour_detailVO tour_detailVO = new Tour_detailVO();
		
		tour_detailVO.setTour_detail_id(tour_detail_id);
		tour_detailVO.setStay_time(stay_time);
		tour_detailVO.setAct_descrip(act_descrip);
		tour_detailVO.setAct_pic(act_pic);
		tour_detailVO.setStart_time(start_time);
		dao.update(tour_detailVO);
		
		return tour_detailVO;
	}
	
	public void deleteTour_detail(String tour_detail_id) {
		dao.delete(tour_detail_id);
	}
	
	public Tour_detailVO getOneTour_detail(String tour_detail_id) {
		return dao.findByPrimaryKey(tour_detail_id);
	}
	
	public List<Tour_detailVO> getAll(){
		return dao.getAll();
	}
	
	public List<Tour_detailVO> getOneTourShowDetail(String tour_id){
		return dao.findByTour_id(tour_id);
	}
	
	public List<List<SpotVO>> getOneTourShowOverview(String tour_id){//依天數擺放的景點VO集合  雙層集合
		List<Tour_detailVO> list =dao.findByTour_id(tour_id);
		Timestamp start = list.get(0).getStart_time();
		Timestamp end = list.get(list.size()-1).getStart_time();
		int days = TimeDiff.getTimeDiffToDays(start, end);//取得天數
		List<List<SpotVO>> spotOverview = new ArrayList<>();
		for(int i =0;i<days;i++) {
			spotOverview.add(new ArrayList<SpotVO>());//按天數產生的list
		}
		SpotService spotSvc = new SpotService();
		for(Tour_detailVO tdVO :list) {
			LocalDate first = start.toLocalDateTime().toLocalDate();
			LocalDate thisday = tdVO.getStart_time().toLocalDateTime().toLocalDate();//轉成JAVA 8 的TIME方便操作 ,將日期以後的時間去除
			int index = 0;
			while(!thisday.equals(first)) {
				first = first.plusDays(1);//回傳值要存回去不然會無窮迴圈
				index++;
			}
			SpotVO spotVO = spotSvc.getOneSpot(tdVO.getSpot_id());
			spotOverview.get(index).add(spotVO);
		}
		return spotOverview;
	}
	
	public void addManyT_detailAndDropOld(List<Tour_detailVO> list,String tour_id) {
		dao.deleteByTour_id(tour_id);//先清掉所有舊資料
		for(int i=0;i<list.size();i++) {
			dao.insert(list.get(i));//新增多筆資料
		}
		
	}

}
