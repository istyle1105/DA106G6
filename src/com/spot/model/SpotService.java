package com.spot.model;

import java.util.List;

public class SpotService {
	private SpotDAO_interface dao;
	
	public SpotService() {
		dao = new Spot_JNDIDAO();
	}

	public SpotVO addSpot(Integer spot_type,String spot_name,String spot_intro,byte[] spot_pic,String address,String lng,String lat) {
		SpotVO spotVO = new SpotVO();
		
		spotVO.setSpot_type(spot_type);
		spotVO.setSpot_name(spot_name);
		spotVO.setSpot_intro(spot_intro);
		spotVO.setSpot_pic(spot_pic);
		spotVO.setAddress(address);
		spotVO.setLng(lng);
		spotVO.setLat(lat);
		SpotVO spotVO2 = dao.insert2(spotVO);
		return spotVO2;
	}
	
	public SpotVO updateSpot(Integer spot_status,String spot_id,Integer spot_type,String spot_name,String spot_intro,byte[] spot_pic,String address,String lng,String lat) {
		SpotVO spotVO = new SpotVO();
		
		spotVO.setSpot_status(spot_status);
		spotVO.setSpot_id(spot_id);
		spotVO.setSpot_type(spot_type);
		spotVO.setSpot_name(spot_name);
		spotVO.setSpot_intro(spot_intro);
		spotVO.setSpot_pic(spot_pic);
		spotVO.setAddress(address);
		spotVO.setLng(lng);
		spotVO.setLat(lat);
		dao.update(spotVO);
		
		return spotVO;
	}
	
	public SpotVO getOneSpot(String spot_id) {
		return dao.findByPrimaryKey(spot_id);
	}
	
	public List<SpotVO> getAll(){
		return dao.getAll();
	}
	
	public List<SpotVO> getAllActive(){
		return dao.getAllActive();
	}
}
