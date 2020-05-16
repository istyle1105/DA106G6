package com.friend.model;

public class FriendVO implements java.io.Serializable{
	
	private String mem_no;
	private String fr_no;
	private Integer fr_status;
	public FriendVO() {}
	public FriendVO(String mem_no, String fr_no, Integer fr_status) {
		super();
		this.mem_no = mem_no;
		this.fr_no = fr_no;
		this.fr_status = fr_status;
	}
	public String getMem_no() {
		return mem_no;
	}
	public void setMem_no(String mem_no) {
		this.mem_no = mem_no;
	}
	public String getFr_no() {
		return fr_no;
	}
	public void setFr_no(String fr_no) {
		this.fr_no = fr_no;
	}
	public Integer getFr_status() {
		return fr_status;
	}
	public void setFr_status(Integer fr_status) {
		this.fr_status = fr_status;
	}
	

	
}
