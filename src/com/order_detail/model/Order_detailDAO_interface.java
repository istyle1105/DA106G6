package com.order_detail.model;

import java.util.List;
import java.util.Set;

public interface Order_detailDAO_interface {
    public void insert(Order_detailVO order_detailVO);
    public void update(Order_detailVO order_detailVO);
    public Order_detailVO findByPrimaryKey(String p_order_id,String p_id);
    public List<Order_detailVO> getAll();
    
    //同時新增訂單主檔與訂單明細
    public void insertWhenOrder_masterInsert (Order_detailVO order_detailVO, java.sql.Connection con);
	//查詢某訂單主檔的訂單明細(一對多)(回傳 Set)
	public Set<Order_detailVO> getOrder_detailByP_order_id_inSet(String p_order_id);
	public List<Order_detailVO> getOrder_detailByP_order_id(String p_order_id);
}
