package com.p_message.model;
import java.util.List;

public interface P_MessageDAO_interface {
	
	void add(P_MessageVO p_messageVO);
	void update(P_MessageVO p_messageVO);
	void delete(String p_msg_no);
	P_MessageVO findByPK(String p_msg_no);
	List<P_MessageVO> findByDe_no(String de_no);
	List<P_MessageVO> getAll();

}
