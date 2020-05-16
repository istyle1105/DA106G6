package com.order_master.model;

import java.util.List;
import java.util.Set;

import com.order_detail.model.Order_detailVO;

public interface Order_masterDAO_interface {
	public void insert(Order_masterVO order_masterVO);
	public void update(Order_masterVO order_masterVO);
	public Order_masterVO findByPrimaryKey(String p_order_id);
	public List<Order_masterVO> findByMemno(String mem_no);
	public List<Order_masterVO> getAll();

    //同時新增訂單主檔與訂單明細
	public void insertWithOrder_detailsAndwallet(Order_masterVO order_masterVO , List<Order_detailVO> list);
	
	public List<Order_masterVO> getP_order_idByMem_no(String mem_no);
	
}
