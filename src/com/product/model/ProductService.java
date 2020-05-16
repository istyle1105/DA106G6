package com.product.model;

import java.util.*;

public class ProductService {

	private ProductDAO_interface dao;

	public ProductService() {
		dao = new ProductDAO();
	}

	public ProductVO addProduct(
			String p_name, 
			String p_cat, 
			Integer p_pr,
			String p_spec,
			String p_detail, 
			byte[] p_pic,
			Integer p_status) {

		ProductVO productVO = new ProductVO();

		productVO.setP_name(p_name);
		productVO.setP_cat(p_cat);
		productVO.setP_pr(p_pr);
		productVO.setP_spec(p_spec);
		productVO.setP_detail(p_detail);
		productVO.setP_pic(p_pic);
		productVO.setP_status(p_status);
		dao.insert(productVO);

		return productVO;
	}

	public ProductVO updateProduct(
			String p_name, 
			String p_cat, 
			Integer p_pr,
			String p_spec,
			String p_detail, 
			byte[] p_pic,
			Integer p_status,
			String p_id) {

		ProductVO productVO = new ProductVO();
		productVO.setP_name(p_name);
		productVO.setP_cat(p_cat);
		productVO.setP_pr(p_pr);
		productVO.setP_spec(p_spec);
		productVO.setP_detail(p_detail);
		productVO.setP_pic(p_pic);
		productVO.setP_status(p_status);
		productVO.setP_id(p_id);
		dao.update(productVO);

		return productVO;
	}

	public ProductVO getOneProduct(String p_id) {
		return dao.findByPrimaryKey(p_id);
	}

	public List<ProductVO> getAll() {
		return dao.getAll();
	}
	
	public List<ProductVO> get_ALLByP_status() {
		return dao.get_ALLByP_status();
	}
	
	public List<ProductVO> getAllByCat(String p_catNum) {
		
		Map<String,String> p_catMap = new TreeMap<String,String>();
		p_catMap.put("0", "行李箱");
		p_catMap.put("1", "旅行袋");
		p_catMap.put("2", "背包");
		p_catMap.put("3", "旅行配件");
		p_catMap.put("4", "3C配件");
		p_catMap.put("5", "書籍");
		
		String p_cat = p_catMap.get(p_catNum);
		
		return dao.getAllByCat(p_cat);	
	}
}
