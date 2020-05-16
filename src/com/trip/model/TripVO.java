package com.trip.model;

import java.sql.Date;
import java.sql.Timestamp;

public class TripVO implements java.io.Serializable {
	private String trip_id;
	private String mem_no;
	private String tour_id;
	private Integer mem_amount;
	private Integer days;
	private Date first_date;
	private Date last_date;
	private Integer trip_price;
	private Date reg_start;
	private Date reg_deadline;
	private Integer mem_limited;
	private Integer current_mem;
	private Integer trip_status;
	private Integer tour_status;
	private Timestamp last_mod_time;
	
	
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
	public String getTour_id() {
		return tour_id;
	}
	public void setTour_id(String tour_id) {
		this.tour_id = tour_id;
	}
	public Integer getMem_amount() {
		return mem_amount;
	}
	public void setMem_amount(Integer mem_amount) {
		this.mem_amount = mem_amount;
	}
	public Integer getDays() {
		return days;
	}
	public void setDays(Integer days) {
		this.days = days;
	}
	public Integer getTrip_price() {
		return trip_price;
	}
	public void setTrip_price(Integer trip_price) {
		this.trip_price = trip_price;
	}
	public Integer getMem_limited() {
		return mem_limited;
	}
	public void setMem_limited(Integer mem_limited) {
		this.mem_limited = mem_limited;
	}
	public Integer getCurrent_mem() {
		return current_mem;
	}
	public void setCurrent_mem(Integer current_mem) {
		this.current_mem = current_mem;
	}
	public Integer getTrip_status() {
		return trip_status;
	}
	public void setTrip_status(Integer trip_status) {
		this.trip_status = trip_status;
	}
	public Integer getTour_status() {
		return tour_status;
	}
	public void setTour_status(Integer tour_status) {
		this.tour_status = tour_status;
	}
	public Date getFirst_date() {
		return first_date;
	}
	public void setFirst_date(Date first_date) {
		this.first_date = first_date;
	}
	public Date getLast_date() {
		return last_date;
	}
	public void setLast_date(Date last_date) {
		this.last_date = last_date;
	}
	public Date getReg_start() {
		return reg_start;
	}
	public void setReg_start(Date reg_start) {
		this.reg_start = reg_start;
	}
	public Date getReg_deadline() {
		return reg_deadline;
	}
	public void setReg_deadline(Date reg_deadline) {
		this.reg_deadline = reg_deadline;
	}
	public Timestamp getLast_mod_time() {
		return last_mod_time;
	}
	public void setLast_mod_time(Timestamp last_mod_time) {
		this.last_mod_time = last_mod_time;
	}
	
}
