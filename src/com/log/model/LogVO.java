package com.log.model;

import java.io.InputStream;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

public class LogVO  implements Serializable{
	
	private String log_id;
	private String mem_no;
	private String log_title;
	private String log_con;
	private String log_pic;
	private Integer l_status;
	private Integer l_favorited;
	private Timestamp l_time;
	
	public Timestamp getL_time() {
		return l_time;
	}
	public void setL_time(Timestamp l_time) {
		this.l_time = l_time;
	}
	public String getLog_id() {
		return log_id;
	}
	public void setLog_id(String log_id) {
		this.log_id = log_id;
	}
	public String getMem_no() {
		return mem_no;
	}
	public void setMem_no(String mem_no) {
		this.mem_no = mem_no;
	}
	public String getLog_title() {
		return log_title;
	}
	public void setLog_title(String log_title) {
		this.log_title = log_title;
	}
	public String getLog_con() {
		return log_con;
	}
	public void setLog_con(String log_con) {
		this.log_con = log_con;
	}
	public String getLog_pic() {
		return log_pic;
	}
	public void setLog_pic(String log_pic) {
		this.log_pic = log_pic;
	}
	public Integer getL_status() {
		return l_status;
	}
	public void setL_status(Integer l_status) {
		this.l_status = l_status;
	}
	public Integer getL_favorited() {
		return l_favorited;
	}
	public void setL_favorited(Integer l_favorited) {
		this.l_favorited = l_favorited;
	}

}
