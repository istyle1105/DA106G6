package com.p_report.model;
import java.sql.Timestamp;

public class P_ReportVO {

	private String p_re_no;
	private String de_no;
	private String mem_no;
	private String p_re_reason;
	private Integer p_re_status;
	private Timestamp p_re_time;

	public String getP_re_no() {
		return p_re_no;
	}

	public void setP_re_no(String p_re_no) {
		this.p_re_no = p_re_no;
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

	public String getP_re_reason() {
		return p_re_reason;
	}

	public void setP_re_reason(String p_re_reason) {
		this.p_re_reason = p_re_reason;
	}

	public Integer getP_re_status() {
		return p_re_status;
	}

	public void setP_re_status(Integer p_re_status) {
		this.p_re_status = p_re_status;
	}

	public Timestamp getP_re_time() {
		return p_re_time;
	}

	public void setP_re_time(Timestamp p_re_time) {
		this.p_re_time = p_re_time;
	}

}
