package com.authority.model;

public class AuthorityVO implements java.io.Serializable{
	
	private String emp_no;
	private String fun_no;
	private Integer fun_status;
	
	public AuthorityVO() {
		
	}
	
	

	@Override
	public String toString() {
		return "AuthorityVO [emp_no=" + emp_no + ", fun_no=" + fun_no + ", fun_status=" + fun_status + "]";
	}



	public AuthorityVO(String emp_no, String fun_no, Integer fun_status) {
		super();
		this.emp_no = emp_no;
		this.fun_no = fun_no;
		this.fun_status = fun_status;
	}

	public String getEmp_no() {
		return emp_no;
	}

	public void setEmp_no(String emp_no) {
		this.emp_no = emp_no;
	}

	public String getFun_no() {
		return fun_no;
	}

	public void setFun_no(String fun_no) {
		this.fun_no = fun_no;
	}

	public Integer getFun_status() {
		return fun_status;
	}

	public void setFun_status(Integer fun_status) {
		this.fun_status = fun_status;
	}


}
