package com.product.model;

import java.sql.Timestamp;

public class ProductVO implements java.io.Serializable {

	private String p_id;
	private String p_name;
	private String p_cat;
	private Integer p_pr;
	private String p_spec;
	private String p_detail;
	private byte[] p_pic;
	private Timestamp p_renew;
	private Integer p_status;
	private Integer quantity;
	
	public ProductVO() {
		
	}

	public ProductVO(String p_id, String p_name, String p_cat, Integer p_pr, String p_spec, String p_detail,
			byte[] p_pic, Timestamp p_renew, Integer p_status, Integer quantity) {
		super();
		this.p_id = p_id;
		this.p_name = p_name;
		this.p_cat = p_cat;
		this.p_pr = p_pr;
		this.p_spec = p_spec;
		this.p_detail = p_detail;
		this.p_pic = p_pic;
		this.p_renew = p_renew;
		this.p_status = p_status;
		this.quantity = quantity;
	}

	public String getP_id() {
		return p_id;
	}

	public void setP_id(String p_id) {
		this.p_id = p_id;
	}

	public String getP_name() {
		return p_name;
	}

	public void setP_name(String p_name) {
		this.p_name = p_name;
	}

	public String getP_cat() {
		return p_cat;
	}

	public void setP_cat(String p_cat) {
		this.p_cat = p_cat;
	}

	public Integer getP_pr() {
		return p_pr;
	}

	public void setP_pr(Integer p_pr) {
		this.p_pr = p_pr;
	}

	public String getP_spec() {
		return p_spec;
	}

	public void setP_spec(String p_spec) {
		this.p_spec = p_spec;
	}

	public String getP_detail() {
		return p_detail;
	}

	public void setP_detail(String p_detail) {
		this.p_detail = p_detail;
	}

	public byte[] getP_pic() {
		return p_pic;
	}

	public void setP_pic(byte[] p_pic) {
		this.p_pic = p_pic;
	}

	public Timestamp getP_renew() {
		return p_renew;
	}

	public void setP_renew(Timestamp p_renew) {
		this.p_renew = p_renew;
	}

	public Integer getP_status() {
		return p_status;
	}

	public void setP_status(Integer p_status) {
		this.p_status = p_status;
	}
	

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "[ID=" + p_id + ",name=" + p_name + ", category=" + p_cat + ",price=" + p_pr + ", specification=" + p_spec + ", detail=" + p_detail
				+ ", quantity=" + quantity + "]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((p_id == null) ? 0 : p_id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductVO other = (ProductVO) obj;
		if (p_id == null) {
			if (other.p_id != null)
				return false;
		} else if (!p_id.equals(other.p_id))
			return false;
		return true;
	}
}
