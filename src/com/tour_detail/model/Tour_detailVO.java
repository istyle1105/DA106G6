package com.tour_detail.model;

import java.sql.Timestamp;

public class Tour_detailVO implements java.io.Serializable{
	private String tour_detail_id;
	private String tour_id;
	private String spot_id;
	private Integer stay_time;
	private String act_descrip;
	private byte[] act_pic;
	private Timestamp start_time;
	
	public Tour_detailVO() {
		super();
	}

	public String getTour_detail_id() {
		return tour_detail_id;
	}

	public void setTour_detail_id(String tour_detail_id) {
		this.tour_detail_id = tour_detail_id;
	}

	public String getTour_id() {
		return tour_id;
	}

	public void setTour_id(String tour_id) {
		this.tour_id = tour_id;
	}

	public String getSpot_id() {
		return spot_id;
	}

	public void setSpot_id(String spot_id) {
		this.spot_id = spot_id;
	}

	public Integer getStay_time() {
		return stay_time;
	}

	public void setStay_time(Integer stay_time) {
		this.stay_time = stay_time;
	}

	public String getAct_descrip() {
		return act_descrip;
	}

	public void setAct_descrip(String act_descrip) {
		this.act_descrip = act_descrip;
	}

	public byte[] getAct_pic() {
		return act_pic;
	}

	public void setAct_pic(byte[] act_pic) {
		this.act_pic = act_pic;
	}

	public Timestamp getStart_time() {
		return start_time;
	}

	public void setStart_time(Timestamp start_time) {
		this.start_time = start_time;
	}
	
	
	
}
