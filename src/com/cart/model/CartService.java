package com.cart.model;

import java.util.*;
import com.member.model.MemberVO;
import com.product.model.ProductVO;

public class CartService {

	private CartDAO_interface dao;

	public CartService() {
		dao = new CartRedisDAO();
	}

	public void insertCart(String mem_no,String p_id,Integer quantity) {

		ProductVO productVO = new ProductVO();
		productVO.setP_id(p_id);
		productVO.setQuantity(quantity);
		MemberVO memberVO = new MemberVO();
		memberVO.setMem_no(mem_no);
		
		dao.insert(memberVO,productVO);
	}
	
	public void updateCart(String mem_no,String p_id,Integer quantity) {

		ProductVO productVO = new ProductVO();
		productVO.setP_id(p_id);
		productVO.setQuantity(quantity);
		MemberVO memberVO = new MemberVO();
		memberVO.setMem_no(mem_no);

		dao.update(memberVO,productVO);
	}
	
	public void replace(String mem_no,String p_id,Integer quantity) {
		ProductVO productVO = new ProductVO();
		productVO.setP_id(p_id);
		productVO.setQuantity(quantity);
		MemberVO memberVO = new MemberVO();
		memberVO.setMem_no(mem_no);
		dao.replace(memberVO,productVO);
	}
	
	public void deleteCart(String mem_no,String p_id,Integer quantity) {
		
		ProductVO productVO = new ProductVO();
		productVO.setP_id(p_id);
		productVO.setQuantity(quantity);
		MemberVO memberVO = new MemberVO();
		memberVO.setMem_no(mem_no);
		
		dao.delete(memberVO,productVO);
	}
	
	public List<ProductVO> getAllP_idByMem_no(String mem_no){
		
		List<ProductVO> list = new ArrayList<>();
		System.out.println("getAllP_idByMem_no" + dao.getAllP_idByMem_no(mem_no));
		
		for (String  i : dao.getAllP_idByMem_no(mem_no)) {
			System.out.println(i);
			ProductVO aproduct = new ProductVO();
			aproduct.setP_id(i);
			list.add(aproduct);
		}
		System.out.println(list.toString());
		return list;
	}
	
	public Integer getValueByP_id(String mem_no,String p_id) {
		return dao.getValueByP_id(mem_no,p_id);
	}
}
