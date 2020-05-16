package com.authority.model;

import java.util.List;

import com.employee.model.EmployeeVO;

public class AuthorityService {

	private AuthorityDAO_interface dao;
	public AuthorityService() {
		dao = new AuthorityDAO();
	}
	
	public AuthorityVO addAuth(String emp_no, String fun_no) {
		AuthorityVO authorityVO=new AuthorityVO();
		authorityVO.setEmp_no(emp_no);
		authorityVO.setFun_no(fun_no);
		dao.insert(authorityVO);
		return authorityVO;
	}
	
//	public AuthorityVO addAuth2(String emp_no, String fun_no) {
//		AuthorityVO authorityVO=new AuthorityVO();
//		authorityVO.setEmp_no(emp_no);
//		authorityVO.setFun_no(fun_no);
//		dao.insert2(authorityVO, con);
//		return authorityVO;
//	}
	
	public void deleteAuth(String emp_no) {
		dao.delete(emp_no);
	}
	public List<AuthorityVO> getOneEmp(String emp_no) {
		return dao.findByPrimaryKey(emp_no);		
	}
	public List<AuthorityVO> getAll() {
		return dao.getAll();
	}
	
	
}
