package com.logreport.model;

import java.io.Serializable;

public class Log_reportVO implements Serializable {
	private String l_re_id;
	private String mem_no;
	private String log_id;
	private String l_re_reason;
	private Integer l_re_status;
	
	public String getL_re_id() {
		return l_re_id;
	}
	public void setL_re_id(String l_re_id) {
		this.l_re_id = l_re_id;
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
	public String getL_re_reason() {
		return l_re_reason;
	}
	public void setL_re_reason(String l_re_reason) {
		this.l_re_reason = l_re_reason;
	}
	public Integer getL_re_status() {
		return l_re_status;
	}
	public void setL_re_status(Integer l_re_status) {
		this.l_re_status = l_re_status;
	}

}
