package com.p_message.model;

import java.sql.Timestamp;
import java.util.List;

public class P_MessageService {
	
	private P_MessageDAO_interface dao;

	public P_MessageService() {
		dao = new P_MessageJNDIDAO();
	}
	
	public P_MessageVO add(String de_no, String mem_no, String p_msg) {

		P_MessageVO p_messageVO = new P_MessageVO();

		p_messageVO.setDe_no(de_no);
		p_messageVO.setMem_no(mem_no);
		p_messageVO.setP_msg(p_msg);
		
		dao.add(p_messageVO);

		return p_messageVO;
	}
	
	public P_MessageVO update(String p_msg_no, String p_msg) {

		P_MessageVO p_messageVO = new P_MessageVO();

		p_messageVO.setP_msg_no(p_msg_no);
		p_messageVO.setP_msg(p_msg);
		
		dao.update(p_messageVO);

		return p_messageVO;
	}
	
	public void delete(String p_msg_no) {
		dao.delete(p_msg_no);
	}

	public P_MessageVO findByPK(String p_msg_no) {
		return dao.findByPK(p_msg_no);
	}
	
	public List<P_MessageVO> findByDe_no(String de_no) {
		return dao.findByDe_no(de_no);
	}

	public List<P_MessageVO> getAll() {
		return dao.getAll();
	}

}
