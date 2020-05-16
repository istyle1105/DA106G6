package com.logfav.model;

import java.io.Serializable;

public class Log_favVO implements Serializable{
	
	private String log_id;
	private String mem_no;
	private Integer fav_status;
	
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
	public Integer getFav_status() {
		return fav_status;
	}
	public void setFav_status(Integer fav_status) {
		this.fav_status = fav_status;
	}

}
