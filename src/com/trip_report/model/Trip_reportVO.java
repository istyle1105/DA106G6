package com.trip_report.model;

import java.sql.Timestamp;

public class Trip_reportVO implements java.io.Serializable{
	private String t_rep_id;
	private String trip_id;
	private String mem_no;
	private String t_rep_content;
	private Integer t_rep_status;
	private Timestamp t_rep_time;
	
	
	public String getT_rep_id() {
		return t_rep_id;
	}
	public void setT_rep_id(String t_rep_id) {
		this.t_rep_id = t_rep_id;
	}
	public String getTrip_id() {
		return trip_id;
	}
	public void setTrip_id(String trip_id) {
		this.trip_id = trip_id;
	}
	public String getMem_no() {
		return mem_no;
	}
	public void setMem_no(String mem_no) {
		this.mem_no = mem_no;
	}
	public String getT_rep_content() {
		return t_rep_content;
	}
	public void setT_rep_content(String t_rep_content) {
		this.t_rep_content = t_rep_content;
	}
	public Integer getT_rep_status() {
		return t_rep_status;
	}
	public void setT_rep_status(Integer t_rep_status) {
		this.t_rep_status = t_rep_status;
	}
	public Timestamp getT_rep_time() {
		return t_rep_time;
	}
	public void setT_rep_time(Timestamp t_rep_time) {
		this.t_rep_time = t_rep_time;
	}


}
