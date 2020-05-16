package com.chat.model;

import java.util.*;

public interface ChatDAO_interface {
	
    public void insert(ChatVO chatVO);
//    public void update(ChatVO chatVO);聊天不能修改
//    public void delete(String chat_id);聊天不能刪除
    public ChatVO findByPrimaryKey(String chat_id);
    public List<ChatVO> getAll();
    //萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<ChatVO> getAll(Map<String, String[]> map); 

}
