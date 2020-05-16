package com.demand.model;
import java.sql.Date;
import java.sql.Timestamp;

public class DemandVO implements java.io.Serializable {

	private String de_no;
	private String de_item_name;
	private String de_photo;
	private Integer price;
	private String de_mem_no;
	private String pur_mem_no;
	private String area;
	private Timestamp de_time;
	private Integer status;
	private String de_comment;
	private Integer de_score;
	private String pur_comment;
	private Integer pur_score;

	public String getDe_no() {
		return de_no;
	}

	public void setDe_no(String de_no) {
		this.de_no = de_no;
	}

	public String getDe_item_name() {
		return de_item_name;
	}

	public void setDe_item_name(String de_item_name) {
		this.de_item_name = de_item_name;
	}

	public String getDe_photo() {
		return de_photo;
	}

	public void setDe_photo(String de_photo) {
		this.de_photo = de_photo;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public String getDe_mem_no() {
		return de_mem_no;
	}

	public void setDe_mem_no(String de_mem_no) {
		this.de_mem_no = de_mem_no;
	}

	public String getPur_mem_no() {
		return pur_mem_no;
	}

	public void setPur_mem_no(String pur_mem_no) {
		this.pur_mem_no = pur_mem_no;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public Timestamp getDe_time() {
		return de_time;
	}

	public void setDe_time(Timestamp de_time) {
		this.de_time = de_time;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getDe_comment() {
		return de_comment;
	}

	public void setDe_comment(String de_comment) {
		this.de_comment = de_comment;
	}

	public Integer getDe_score() {
		return de_score;
	}

	public void setDe_score(Integer de_score) {
		this.de_score = de_score;
	}

	public String getPur_comment() {
		return pur_comment;
	}

	public void setPur_comment(String pur_comment) {
		this.pur_comment = pur_comment;
	}

	public Integer getPur_score() {
		return pur_score;
	}

	public void setPur_score(Integer pur_score) {
		this.pur_score = pur_score;
	}
}
