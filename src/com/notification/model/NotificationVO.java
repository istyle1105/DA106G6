package com.notification.model;

import java.sql.*;

public class NotificationVO implements java.io.Serializable{
	
	private String note_no;
	private String mem_no;
	private String note_content;
	private Timestamp note_date;
	private Integer note_status;

	public NotificationVO() {
	}

	public NotificationVO(String note_no, String mem_no, String note_content, Timestamp note_date,
			Integer note_status) {
		super();
		this.note_no = note_no;
		this.mem_no = mem_no;
		this.note_content = note_content;
		this.note_date = note_date;
		this.note_status = note_status;
	}

	public String getNote_no() {
		return note_no;
	}

	public void setNote_no(String note_no) {
		this.note_no = note_no;
	}

	public String getMem_no() {
		return mem_no;
	}

	public void setMem_no(String mem_no) {
		this.mem_no = mem_no;
	}

	public String getNote_content() {
		return note_content;
	}

	public void setNote_content(String note_content) {
		this.note_content = note_content;
	}

	public Timestamp getNote_date() {
		return note_date;
	}

	public void setNote_date(Timestamp note_date) {
		this.note_date = note_date;
	}

	public Integer getNote_status() {
		return note_status;
	}

	public void setNote_status(Integer note_status) {
		this.note_status = note_status;
	}



}
