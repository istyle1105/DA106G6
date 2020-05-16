package com.function.model;

import java.util.List;


public class FunctionService {
	
	private FunctionDAO_interface dao;
	
	public FunctionService() {
		dao = new FunctionDAO();
	}
	
	public FunctionVO addFun(String fun_name) {
		
		FunctionVO functionVO=new FunctionVO();
		functionVO.setFun_name(fun_name);
		dao.insert(functionVO);
		return functionVO;
	}
	
	public FunctionVO updateFun(String fun_no,String fun_name) {
		FunctionVO functionVO=new FunctionVO();
		functionVO.setFun_no(fun_no);
		functionVO.setFun_name(fun_name);
		dao.update(functionVO);
		return functionVO;
	}
	
	public void deleteFun(String fun_no) {
		dao.delete(fun_no);
	}
	
	public FunctionVO getOneFun(String fun_no) {
		return dao.findByPrimaryKey(fun_no);
	}
	public List<FunctionVO> getAll() {
		return dao.getAll();
	}

}
