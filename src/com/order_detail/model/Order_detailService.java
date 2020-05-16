package com.order_detail.model;

import java.util.List;
import java.util.Set;

public class Order_detailService {

	private Order_detailDAO_interface dao;

	public Order_detailService() {
		dao = new Order_detailDAO();
	}

	public Order_detailVO addOrder_detail(
			String p_order_id, 
			String p_id, 
			Integer od_qty,
			Integer od_pr) {

		Order_detailVO order_detailVO = new Order_detailVO();
		order_detailVO.setP_order_id(p_order_id);
		order_detailVO.setP_id(p_id);
		order_detailVO.setOd_qty(od_qty);
		order_detailVO.setOd_pr(od_pr);
		dao.insert(order_detailVO);

		return order_detailVO;
	}

	public Order_detailVO updateOrder_detail(Integer od_qty) {

		Order_detailVO order_detailVO = new Order_detailVO();
		order_detailVO.setOd_qty(od_qty);
		dao.update(order_detailVO);

		return order_detailVO;
	}

	public Order_detailVO getOneOrder_detail(String p_order_id,String p_id) {
		return dao.findByPrimaryKey(p_order_id,p_id);
	}

	public List<Order_detailVO> getAll() {
		return dao.getAll();
	}
	public List<Order_detailVO> getOrder_detailByP_order_id(String p_order_id){
		return dao.getOrder_detailByP_order_id(p_order_id);
	}
	
	public Set<Order_detailVO> getOrder_detailByP_order_id_inSet(String p_order_id){
		return dao.getOrder_detailByP_order_id_inSet(p_order_id);
	}
}
