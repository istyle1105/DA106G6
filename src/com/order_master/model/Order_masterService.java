package com.order_master.model;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

import com.order_detail.model.Order_detailVO;

public class Order_masterService {

	private Order_masterDAO_interface dao;

	public Order_masterService() {
		dao = new Order_masterDAO();
	}

	public Order_masterVO addOrder_master(
			String mem_no,
			Integer om_tpr) {

		Order_masterVO order_masterVO = new Order_masterVO();
		order_masterVO.setMem_no(mem_no);
		order_masterVO.setOm_tpr(om_tpr);
		dao.insert(order_masterVO);

		return order_masterVO;
	}

	public Order_masterVO updateOrder_master(
			Integer om_status,
			Timestamp om_dlvr,
			String p_order_id) {

		Order_masterVO order_masterVO = new Order_masterVO();
		order_masterVO.setOm_status(om_status);
		order_masterVO.setOm_dlvr(om_dlvr);
		order_masterVO.setP_order_id(p_order_id);
		dao.update(order_masterVO);

		return order_masterVO;
	}

	public Order_masterVO getOneOrder_master(String p_order_id) {
		return dao.findByPrimaryKey(p_order_id);
	}

	public List<Order_masterVO> getAll() {
		return dao.getAll();
	}
	
	synchronized public void addOrder_masterWithOrder_detail(
			Order_masterVO order_masterVO ,
			List<Order_detailVO> list) {
		dao.insertWithOrder_detailsAndwallet(order_masterVO, list);
	}
	public List<Order_masterVO> getP_order_idByMem_no(String mem_no){
		return dao.getP_order_idByMem_no(mem_no);
	}
	public List<Order_masterVO> findByMemno(String mem_no) {
		return dao.findByMemno(mem_no);
	}
}
