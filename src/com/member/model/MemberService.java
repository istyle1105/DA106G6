package com.member.model;

import java.sql.Timestamp;
import java.util.List;

public class MemberService {
	
	private  MemberDAO_interface dao;
	
	public MemberService() {
		dao=new MemberDAO();
	}

	public MemberVO addMember(String mem_id,String psw,String m_name,Integer gender, String address,
			String cellphone, String email, String m_photo, byte[] id_card,String captcha) {
		
		MemberVO memberVO=new MemberVO();

		memberVO.setMem_id(mem_id);
		memberVO.setPsw(psw);
		memberVO.setM_name(m_name);
		memberVO.setGender(gender);
		memberVO.setCellphone(cellphone);
		memberVO.setEmail(email);
		memberVO.setAddress(address);
		memberVO.setM_photo(m_photo);
		memberVO.setId_card(id_card);
		memberVO.setCaptcha(captcha);
		dao.insert(memberVO);
		return memberVO;
	}
	
	public MemberVO update_back(String mem_no,
			Integer mem_verify,Integer leader_verify,Integer pur_verify) {
		
		MemberVO memberVO=new MemberVO();
		memberVO.setMem_no(mem_no);
		memberVO.setMem_verify(mem_verify);
		memberVO.setLeader_verify(leader_verify);
		memberVO.setPur_verify(pur_verify);
		dao.update_back(memberVO);

		return memberVO;
	}
	
	public MemberVO update_front(String mem_no,String psw,String m_name,
			Integer gender, String cellphone, String email,String address,String m_photo,byte[] id_card) {
		
		MemberVO memberVO=new MemberVO();
		memberVO.setMem_no(mem_no);
		memberVO.setPsw(psw);
		memberVO.setM_name(m_name);
		memberVO.setGender(gender);
		memberVO.setCellphone(cellphone);
		memberVO.setEmail(email);
		memberVO.setAddress(address);
		memberVO.setM_photo(m_photo);
		memberVO.setId_card(id_card);

		dao.update_front(memberVO);

		return memberVO;
	}

	public MemberVO update_memVerify(String mem_no,Integer mem_verify) {
		
		MemberVO memberVO=new MemberVO();
		memberVO.setMem_no(mem_no);
		memberVO.setMem_verify(mem_verify);

		dao.update_mem_verify(memberVO);

		return memberVO;
	}
	
	public MemberVO update_Status(String mem_no,Integer mem_verify,Integer leader_verify,Integer pur_verify) {
		
		MemberVO memberVO=new MemberVO();
		memberVO.setMem_no(mem_no);
		memberVO.setMem_verify(mem_verify);
		memberVO.setLeader_verify(leader_verify);
		memberVO.setPur_verify(pur_verify);
		dao.update_back(memberVO);
		return memberVO;
	}
		
	public MemberVO updateAccBalance(Integer acc_balance,String mem_no) {
		MemberVO memberVO=new MemberVO();
		
		memberVO.setAcc_balance(acc_balance);
		memberVO.setMem_no(mem_no);
		dao.update_wallet(memberVO);
		return memberVO;
	}

	
	public void deleteMember(String mem_no) {
		dao.delete(mem_no);
	}
	
	public MemberVO getOneMember(String mem_no) {
		return dao.findByPrimaryKey(mem_no);
	}
	
	public List<MemberVO> getAll(){
		return dao.getAll();
	}
	
	public MemberVO getfindOnePK(String mem_id) {
		return dao.findPK(mem_id);
	}

	

}
