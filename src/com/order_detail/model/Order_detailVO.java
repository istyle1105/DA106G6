package com.order_detail.model;

public class Order_detailVO implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	private String p_order_id;
	private String p_id;
	private Integer od_qty;
	private Integer od_pr;
	
	public Order_detailVO() {
		
	}

	public Order_detailVO(String p_order_id, String p_id, Integer od_qty, Integer od_pr) {
		super();
		this.p_order_id = p_order_id;
		this.p_id = p_id;
		this.od_qty = od_qty;
		this.od_pr = od_pr;
	}

	public String getP_order_id() {
		return p_order_id;
	}

	public void setP_order_id(String p_order_id) {
		this.p_order_id = p_order_id;
	}

	public String getP_id() {
		return p_id;
	}

	public void setP_id(String p_id) {
		this.p_id = p_id;
	}

	public Integer getOd_qty() {
		return od_qty;
	}

	public void setOd_qty(Integer od_qty) {
		this.od_qty = od_qty;
	}

	public Integer getOd_pr() {
		return od_pr;
	}

	public void setOd_pr(Integer od_pr) {
		this.od_pr = od_pr;
	}
	
}
