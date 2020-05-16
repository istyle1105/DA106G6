package com.tour.model;

public class TourVO implements java.io.Serializable{
	private String tour_id;
	private String mem_no;
	private String tour_name;
	private String tour_detail;
	private byte[] tour_pic;
	private Integer display;
	
	public Integer getDisplay() {
		return display;
	}

	public void setDisplay(Integer display) {
		this.display = display;
	}

	public TourVO() {
		super();
	}

	public String getTour_id() {
		return tour_id;
	}

	public void setTour_id(String tour_id) {
		this.tour_id = tour_id;
	}

	public String getMem_no() {
		return mem_no;
	}

	public void setMem_no(String mem_no) {
		this.mem_no = mem_no;
	}

	public String getTour_name() {
		return tour_name;
	}

	public void setTour_name(String tour_name) {
		this.tour_name = tour_name;
	}

	public String getTour_detail() {
		return tour_detail;
	}

	public void setTour_detail(String tour_detail) {
		this.tour_detail = tour_detail;
	}

	public byte[] getTour_pic() {
		return tour_pic;
	}

	public void setTour_pic(byte[] tour_pic) {
		this.tour_pic = tour_pic;
	}
	
	
	
	
}
