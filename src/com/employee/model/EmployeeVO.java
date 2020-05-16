package com.employee.model;

public class EmployeeVO implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	private String emp_no;
	private Integer emp_status;
	private String emp_id;
	private String emp_psw;
	private String emp_name;
	private String emp_cellphone;
	private String emp_email;
	private String emp_photo;
	
	public EmployeeVO() {
		
	}
	public EmployeeVO(String emp_no, Integer emp_status, String emp_id, String emp_psw, String emp_name,
			String emp_cellphone, String emp_email, String emp_photo) {
		super();
		this.emp_no = emp_no;
		this.emp_status = emp_status;
		this.emp_id = emp_id;
		this.emp_psw = emp_psw;
		this.emp_name = emp_name;
		this.emp_cellphone = emp_cellphone;
		this.emp_email = emp_email;
		this.emp_photo = emp_photo;
	}

	public String getEmp_no() {
		return emp_no;
	}

	public void setEmp_no(String emp_no) {
		this.emp_no = emp_no;
	}

	public Integer getEmp_status() {
		return emp_status;
	}

	public void setEmp_status(Integer emp_status) {
		this.emp_status = emp_status;
	}

	public String getEmp_id() {
		return emp_id;
	}

	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
	}

	public String getEmp_psw() {
		return emp_psw;
	}

	public void setEmp_psw(String emp_psw) {
		this.emp_psw = emp_psw;
	}

	public String getEmp_name() {
		return emp_name;
	}

	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}

	public String getEmp_cellphone() {
		return emp_cellphone;
	}

	public void setEmp_cellphone(String emp_cellphone) {
		this.emp_cellphone = emp_cellphone;
	}

	public String getEmp_email() {
		return emp_email;
	}

	public void setEmp_email(String emp_email) {
		this.emp_email = emp_email;
	}

	public String getEmp_photo() {
		return emp_photo;
	}

	public void setEmp_photo(String emp_photo) {
		this.emp_photo = emp_photo;
	}

	
}
