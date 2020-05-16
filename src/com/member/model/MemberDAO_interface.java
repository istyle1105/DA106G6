package com.member.model;

import java.util.*;

import com.employee.model.EmployeeVO;

public interface MemberDAO_interface {
    public void insert(MemberVO memberVO);
    public void update_wallet(MemberVO memberVO);
    public void update_front(MemberVO memberVO);
    public void update_back(MemberVO memberVO);
    public void update_mem_verify(MemberVO memberVO);
    public void delete(String mem_no);
    public MemberVO findPK(String mem_id);
    public MemberVO findByPrimaryKey(String mem_no);
    public List<MemberVO> getAll();
    //萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<MemberVO> getAll(Map<String, String[]> map); 
//    public List<MemberVO> getCompound(Map<String, String[]> map);

}
