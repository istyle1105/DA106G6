package com.authority.model;

import java.sql.Connection;
import java.util.*;

public interface AuthorityDAO_interface {
	public void insert2(AuthorityVO authorityVO,Connection con);
    public void insert(AuthorityVO authorityVO);
    public void updateStatus(AuthorityVO authorityVO);
    public void delete(String emp_no);
    public List<AuthorityVO> findByPrimaryKey(String emp_no);
    public List<AuthorityVO> getAll();
    //萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<AuthorityVO> getAll(Map<String, String[]> map); 

}
