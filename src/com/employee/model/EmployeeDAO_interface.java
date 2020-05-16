package com.employee.model;

import java.util.*;

import com.authority.model.AuthorityVO;

public interface EmployeeDAO_interface {
	public void insertAutoKey(EmployeeVO employeeVO, List<AuthorityVO> list);
    public void insert(EmployeeVO employeeVO);
    public void update(EmployeeVO employeeVO);
    public void updateEmp(EmployeeVO employeeVO);
//    public void updateStatus(EmployeeVO employeeVO);
    public void delete(String emp_no);
    public EmployeeVO findByPrimaryKey(String emp_no);
    public EmployeeVO findByIdKey(String emp_id);
    public List<EmployeeVO> getAll();
    //萬用複合查詢(傳入參數型態Map)(回傳 List)
//    public List<EmployeeVO> getCompound(Map<String, String[]> map); 

}
