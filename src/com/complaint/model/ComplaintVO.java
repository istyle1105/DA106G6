package com.complaint.model;

import java.sql.*;

public class ComplaintVO implements java.io.Serializable{
	
	private String comp_no;
	private String mem_no;
	private String comp_content;
	private Timestamp comp_date;
	private Integer comp_status;
	
	public ComplaintVO() {
		
	}

	public ComplaintVO(String comp_no, String mem_no, String comp_content, Timestamp comp_date, Integer comp_status) {
		super();
		this.comp_no = comp_no;
		this.mem_no = mem_no;
		this.comp_content = comp_content;
		this.comp_date = comp_date;
		this.comp_status = comp_status;
	}

	public String getComp_no() {
		return comp_no;
	}

	public void setComp_no(String comp_no) {
		this.comp_no = comp_no;
	}

	public String getMem_no() {
		return mem_no;
	}

	public void setMem_no(String mem_no) {
		this.mem_no = mem_no;
	}

	public String getComp_content() {
		return comp_content;
	}

	public void setComp_content(String comp_content) {
		this.comp_content = comp_content;
	}

	public Timestamp getComp_date() {
		return comp_date;
	}

	public void setComp_date(Timestamp comp_date) {
		this.comp_date = comp_date;
	}

	public Integer getComp_status() {
		return comp_status;
	}

	public void setComp_status(Integer comp_status) {
		this.comp_status = comp_status;
	}


	
}
