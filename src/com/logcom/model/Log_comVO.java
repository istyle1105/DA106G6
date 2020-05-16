package com.logcom.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

public class Log_comVO implements Serializable {
	
	private String com_id;
	private String mem_no;
	private String log_id;
	private String log_com;
	private Timestamp com_time;
	private String log_com_status;
	
	
	public String getLog_com_status() {
		return log_com_status;
	}
	public void setLog_com_status(String log_com_status) {
		this.log_com_status = log_com_status;
	}
	
	public String getCom_id() {
		return com_id;
	}
	public void setCom_id(String com_id) {
		this.com_id = com_id;
	}
	public String getMem_no() {
		return mem_no;
	}
	public void setMem_no(String mem_no) {
		this.mem_no = mem_no;
	}
	public String getLog_id() {
		return log_id;
	}
	public void setLog_id(String log_id) {
		this.log_id = log_id;
	}
	public String getLog_com() {
		return log_com;
	}
	public void setLog_com(String log_com) {
		this.log_com = log_com;
	}
	public Timestamp getCom_time() {
		return com_time;
	}
	public void setCom_time(Timestamp com_time) {
		this.com_time = com_time;
	}
	

}
