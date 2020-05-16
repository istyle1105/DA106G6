package com.cart.model;

import java.util.*;
import com.member.model.MemberVO;
import com.product.model.ProductVO;

public interface CartDAO_interface {
	public void insert(MemberVO memberVO,ProductVO productVO);
	public void update(MemberVO memberVO,ProductVO productVO);
	public void replace(MemberVO memberVO,ProductVO productVO);
	public void delete(MemberVO memberVO,ProductVO productVO);
	public List<String> getAllP_idByMem_no(String mem_no);
	public Integer getValueByP_id(String mem_no,String p_id);
}
