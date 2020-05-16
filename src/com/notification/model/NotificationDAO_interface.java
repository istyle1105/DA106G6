package com.notification.model;

import java.util.*;

public interface NotificationDAO_interface {
    public void insert(NotificationVO notificationVO);
    public void updateStatus(NotificationVO notificationVO);
    public void delete(String note_no);
    public NotificationVO findByPrimaryKey(String note_no);
    public List<NotificationVO> getOneAll(String mem_no);
    public List<NotificationVO> getAll();
    //萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<NotificationVO> getAll(Map<String, String[]> map); 

}
