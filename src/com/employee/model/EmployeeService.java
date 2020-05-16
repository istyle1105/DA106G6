package com.employee.model;

import java.util.List;

import com.authority.model.AuthorityVO;


public class EmployeeService {
	
	private EmployeeDAO_interface dao;
	
	public EmployeeService() {
		dao = new EmployeeDAO();
	}
	
	
	public EmployeeVO addEmp(String emp_id,String emp_psw,
			String emp_name,String emp_cellphone,String emp_email,String emp_photo) {
		EmployeeVO employeeVO=new EmployeeVO();
		employeeVO.setEmp_id(emp_id);
		employeeVO.setEmp_psw(emp_psw);
		employeeVO.setEmp_name(emp_name);
		employeeVO.setEmp_cellphone(emp_cellphone);
		employeeVO.setEmp_email(emp_email);
		employeeVO.setEmp_photo(emp_photo);
		dao.insert(employeeVO);
		return employeeVO;
	}
	public EmployeeVO addEmp2(String emp_id,String emp_psw,
			String emp_name,String emp_cellphone,String emp_email,String emp_photo,List<AuthorityVO> authlist) {
		EmployeeVO employeeVO=new EmployeeVO();
		employeeVO.setEmp_id(emp_id);
		employeeVO.setEmp_psw(emp_psw);
		employeeVO.setEmp_name(emp_name);
		employeeVO.setEmp_cellphone(emp_cellphone);
		employeeVO.setEmp_email(emp_email);
		employeeVO.setEmp_photo(emp_photo);
		dao.insertAutoKey(employeeVO, authlist);
		return employeeVO;
	}
	

	
	public EmployeeVO updateEmpALL(String emp_no,Integer emp_status,String emp_psw,
			String emp_name,String emp_cellphone,String emp_email,String emp_photo) {
//		UPDATE employee SET EMP_STATUS=?,EMP_PSW=?, EMP_NAME=?, EMP_CELLPHONE=?, EMP_EMAIL=? WHERE EMP_NO=		
		EmployeeVO employeeVO=new EmployeeVO();
		employeeVO.setEmp_no(emp_no);
		employeeVO.setEmp_status(emp_status);
		employeeVO.setEmp_psw(emp_psw);
		employeeVO.setEmp_name(emp_name);
		employeeVO.setEmp_cellphone(emp_cellphone);
		employeeVO.setEmp_email(emp_email);
		employeeVO.setEmp_photo(emp_photo);
		dao.update(employeeVO);
		return employeeVO;
	}
	
	public EmployeeVO updateEmp(String emp_no,String emp_psw,String emp_name,String emp_cellphone,String emp_email,String emp_photo) {
	
		EmployeeVO employeeVO=new EmployeeVO();

		employeeVO.setEmp_no(emp_no);
		employeeVO.setEmp_psw(emp_psw);
		employeeVO.setEmp_name(emp_name);
		employeeVO.setEmp_cellphone(emp_cellphone);
		employeeVO.setEmp_email(emp_email);
		employeeVO.setEmp_photo(emp_photo);
		dao.updateEmp(employeeVO);
		return employeeVO;
	}
	
	
	public void deleteEmp(String emp_no) {
		dao.delete(emp_no);
	}
	public EmployeeVO getOneEmp(String emp_no) {
		return dao.findByPrimaryKey(emp_no);		
	}
	public EmployeeVO getOneEmpById(String emp_id) {
		return dao.findByIdKey(emp_id);		
	}
	
	public List<EmployeeVO> getAll() {
		return dao.getAll();
	}


	
	
	
}
