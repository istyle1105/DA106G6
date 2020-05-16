package com.spot.model;

public class SpotVO implements java.io.Serializable{
	private String spot_id;
	private Integer spot_type;
	private String spot_name;
	private String spot_intro;
	private byte[] spot_pic;
	private String address;
	private String lng;
	private String lat;
	private Integer spot_status;
	
	public SpotVO() {
		super();
	}

	public String getSpot_id() {
		return spot_id;
	}

	public void setSpot_id(String spot_id) {
		this.spot_id = spot_id;
	}

	public Integer getSpot_type() {
		return spot_type;
	}

	public void setSpot_type(Integer spot_type) {
		this.spot_type = spot_type;
	}

	public String getSpot_name() {
		return spot_name;
	}

	public void setSpot_name(String spot_name) {
		this.spot_name = spot_name;
	}

	public String getSpot_intro() {
		return spot_intro;
	}

	public void setSpot_intro(String spot_intro) {
		this.spot_intro = spot_intro;
	}

	public byte[] getSpot_pic() {
		return spot_pic;
	}

	public void setSpot_pic(byte[] spot_pic) {
		this.spot_pic = spot_pic;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public Integer getSpot_status() {
		return spot_status;
	}

	public void setSpot_status(Integer spot_status) {
		this.spot_status = spot_status;
	}
	
	
	
	

}
