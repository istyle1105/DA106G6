package com.p_message.model;
import java.sql.Timestamp;

public class P_MessageVO implements java.io.Serializable {

	private String p_msg_no;
	private String de_no;
	private String mem_no;
	private String p_msg;
	private Timestamp p_msg_time;

	public String getP_msg_no() {
		return p_msg_no;
	}

	public void setP_msg_no(String p_msg_no) {
		this.p_msg_no = p_msg_no;
	}

	public String getDe_no() {
		return de_no;
	}

	public void setDe_no(String de_no) {
		this.de_no = de_no;
	}

	public String getMem_no() {
		return mem_no;
	}

	public void setMem_no(String mem_no) {
		this.mem_no = mem_no;
	}

	public String getP_msg() {
		return p_msg;
	}

	public void setP_msg(String p_msg) {
		this.p_msg = p_msg;
	}

	public Timestamp getP_msg_time() {
		return p_msg_time;
	}

	public void setP_msg_time(Timestamp p_msg_time) {
		this.p_msg_time = p_msg_time;
	}

}
