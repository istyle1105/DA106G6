package com.complaint.model;

import java.util.*;

public interface ComplaintDAO_interface {
    public void insert(ComplaintVO complaintVO);
    public void updateStatus(ComplaintVO complaintVO);
    public void delete(String comp_no);
    public ComplaintVO findByPrimaryKey(String comp_no);
    public List<ComplaintVO> getAll();
    public List<ComplaintVO> getMemAll(String mem_no);
    public List<ComplaintVO> getStatusAll(Integer comp_status);
    //萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<ComplaintVO> getAll(Map<String, String[]> map); 

}
