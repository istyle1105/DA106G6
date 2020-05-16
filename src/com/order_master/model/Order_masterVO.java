package com.order_master.model;
import java.sql.Timestamp;

public class Order_masterVO implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	private String p_order_id;
	private String mem_no;
	private Timestamp om_time;
	private Integer om_status;
	private Timestamp om_dlvr;
	private Integer om_tpr;

	public Order_masterVO() {
		
	}

	public Order_masterVO(String p_order_id, String mem_no, Timestamp om_time, Integer om_status, Timestamp om_dlvr,
			Integer om_tpr) {
		super();
		this.p_order_id = p_order_id;
		this.mem_no = mem_no;
		this.om_time = om_time;
		this.om_status = om_status;
		this.om_dlvr = om_dlvr;
		this.om_tpr = om_tpr;
	}

	public String getP_order_id() {
		return p_order_id;
	}

	public void setP_order_id(String p_order_id) {
		this.p_order_id = p_order_id;
	}

	public String getMem_no() {
		return mem_no;
	}

	public void setMem_no(String mem_no) {
		this.mem_no = mem_no;
	}

	public Timestamp getOm_time() {
		return om_time;
	}

	public void setOm_time(Timestamp om_time) {
		this.om_time = om_time;
	}

	public Integer getOm_status() {
		return om_status;
	}

	public void setOm_status(Integer om_status) {
		this.om_status = om_status;
	}

	public Timestamp getOm_dlvr() {
		return om_dlvr;
	}

	public void setOm_dlvr(Timestamp om_dlvr) {
		this.om_dlvr = om_dlvr;
	}

	public Integer getOm_tpr() {
		return om_tpr;
	}

	public void setOm_tpr(Integer om_tpr) {
		this.om_tpr = om_tpr;
	}
	
}
