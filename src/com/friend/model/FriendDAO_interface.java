package com.friend.model;

import java.util.*;

public interface FriendDAO_interface {
    public void insert(FriendVO friendVO);
    public void updateStatus(FriendVO friendVO);
    public FriendVO findByPrimaryKey(String mem_no, String friend_no);
    public List<FriendVO> getAll();
    //萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<ComplaintVO> getAll(Map<String, String[]> map); 

}
