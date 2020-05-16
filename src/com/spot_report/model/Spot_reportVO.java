package com.spot_report.model;

public class Spot_reportVO implements java.io.Serializable{
	private String spot_report_id;
	private String spot_id;
	private String mem_no;
	private String s_content;
	private Integer s_status;
	
	public Spot_reportVO() {
		super();
	}

	public String getSpot_report_id() {
		return spot_report_id;
	}

	public void setSpot_report_id(String spot_report_id) {
		this.spot_report_id = spot_report_id;
	}

	public String getSpot_id() {
		return spot_id;
	}

	public void setSpot_id(String spot_id) {
		this.spot_id = spot_id;
	}

	public String getMem_no() {
		return mem_no;
	}

	public void setMem_no(String mem_no) {
		this.mem_no = mem_no;
	}

	public String getS_content() {
		return s_content;
	}

	public void setS_content(String s_content) {
		this.s_content = s_content;
	}

	public Integer getS_status() {
		return s_status;
	}

	public void setS_status(Integer s_status) {
		this.s_status = s_status;
	}
	
	
}
