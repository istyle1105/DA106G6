package com.trip_message.model;

import java.sql.Timestamp;

public class Trip_messageVO implements java.io.Serializable{
	private String t_msg_id;
	private String mem_no;
	private String trip_id;
	private String t_msg_content;
	private Timestamp t_msg_time;
	
	
	public String getT_msg_id() {
		return t_msg_id;
	}
	public void setT_msg_id(String t_msg_id) {
		this.t_msg_id = t_msg_id;
	}
	public String getMem_no() {
		return mem_no;
	}
	public void setMem_no(String mem_no) {
		this.mem_no = mem_no;
	}
	public String getTrip_id() {
		return trip_id;
	}
	public void setTrip_id(String trip_id) {
		this.trip_id = trip_id;
	}
	public String getT_msg_content() {
		return t_msg_content;
	}
	public void setT_msg_content(String t_msg_content) {
		this.t_msg_content = t_msg_content;
	}
	public Timestamp getT_msg_time() {
		return t_msg_time;
	}
	public void setT_msg_time(Timestamp t_msg_time) {
		this.t_msg_time = t_msg_time;
	}


}
