package com.complaint.model;

import java.util.List;

public class ComplaintService {
	private ComplaintDAO_interface dao;
	public ComplaintService() {
		dao=new ComplaintDAO();
	}
	
	public ComplaintVO addComp(String mem_no,String comp_content) {
		ComplaintVO complaintVO=new ComplaintVO();
		complaintVO.setMem_no(mem_no);
		complaintVO.setComp_content(comp_content);
		dao.insert(complaintVO);
		return complaintVO;
	}
	
	public ComplaintVO updateCompSta(Integer comp_status,String comp_no) {
		ComplaintVO complaintVO=new ComplaintVO();
		complaintVO.setComp_status(comp_status);
		complaintVO.setComp_no(comp_no);
		dao.updateStatus(complaintVO);
		return complaintVO;
	}
	public ComplaintVO getOneComp(String comp_no) {
		return dao.findByPrimaryKey(comp_no);
	}
	public List<ComplaintVO> getAll(){
		return dao.getAll();
	}
	public List<ComplaintVO> getMemAll(String mem_no){
		return dao.getMemAll(mem_no);
	}
	public List<ComplaintVO> getStatusAll(Integer comp_status){
		return dao.getStatusAll(comp_status);
	}
	
}