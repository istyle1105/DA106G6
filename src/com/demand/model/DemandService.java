package com.demand.model;

import java.sql.Timestamp;
import java.util.List;

import com.member.model.MemberVO;
import com.wallet.model.WalletVO;

public class DemandService {
	
	private DemandDAO_interface dao;

	public DemandService() {
		dao = new DemandJNDIDAO();
	}
	
	public DemandVO add(String de_item_name, String de_photo, Integer price, 
			String de_mem_no, String area) {

		DemandVO demandVO = new DemandVO();

		demandVO.setDe_item_name(de_item_name);
		demandVO.setDe_photo(de_photo);
		demandVO.setPrice(price);
		demandVO.setDe_mem_no(de_mem_no);
		demandVO.setArea(area);
		
		dao.add(demandVO);

		return demandVO;
	}
	
	public WalletVO returnWallet(String mem_no, Integer w_amount,  String w_txn_note) {
		
		WalletVO WalletVO = new WalletVO();

		WalletVO.setMem_no(mem_no);
		WalletVO.setW_amount(w_amount);
		WalletVO.setW_txn_note(w_txn_note);
		
		dao.returnWallet(WalletVO);

		return WalletVO;
	}
	
	
	public DemandVO update(String de_no, String de_item_name, String de_photo, Integer integer,  String area) { 

		DemandVO demandVO = new DemandVO();

		demandVO.setDe_no(de_no);
		demandVO.setDe_item_name(de_item_name);
		demandVO.setDe_photo(de_photo);
		demandVO.setPrice(integer);
		demandVO.setArea(area);
		
		dao.update(demandVO);

		return demandVO;
	}
	
	public DemandVO update(String de_comment, Integer de_score, String pur_comment, Integer pur_score) {

		DemandVO demandVO = new DemandVO();

		demandVO.setDe_comment(de_comment);
		demandVO.setDe_score(de_score);
		demandVO.setPur_comment(pur_comment);
		demandVO.setPur_score(pur_score);
		
		dao.update(demandVO);

		return demandVO;
	}
	
	public DemandVO update_purmem(String de_no, String pur_mem_no) {

		DemandVO demandVO = new DemandVO();
		
		demandVO.setDe_no(de_no);
		demandVO.setPur_mem_no(pur_mem_no);
		
		dao.update_purmem(demandVO);

		return demandVO;
	}
	public DemandVO updateStatus(String de_no, Integer status) {
		
		DemandVO demandVO = new DemandVO();
		
		demandVO.setDe_no(de_no);
		demandVO.setStatus(status);
		
		dao.updateStatus(demandVO);

		return demandVO;
	}
	
	public DemandVO addScore(String de_no, Integer pur_score, String pur_comment) {
		
		DemandVO demandVO = new DemandVO();
		
		demandVO.setDe_no(de_no);
		demandVO.setPur_score(pur_score);
		demandVO.setPur_comment(pur_comment);
		
		dao.addScore(demandVO);

		return demandVO;
	}
	
	public MemberVO addScoreInMem(String pur_mem_no, Integer pur_score_amount, Integer pur_total_score) {
		
		MemberVO memberVO = new MemberVO();
		
		memberVO.setMem_no(pur_mem_no);
		memberVO.setPur_score_amount(pur_score_amount);
		memberVO.setPur_total_score(pur_total_score);
		
		dao.addScoreInMem(memberVO);

		return memberVO;
	}
	
	public DemandVO addScore2(String de_no, Integer de_score, String de_comment) {
		
		DemandVO demandVO = new DemandVO();
		
		demandVO.setDe_no(de_no);
		demandVO.setDe_score(de_score);
		demandVO.setDe_comment(de_comment);
		
		dao.addScore2(demandVO);

		return demandVO;
	}
	
	public MemberVO addScore2InMem(String de_mem_no, Integer de_score_amount, Integer de_total_score) {
		
		MemberVO memberVO = new MemberVO();
		
		memberVO.setMem_no(de_mem_no);
		memberVO.setDe_score_amount(de_score_amount);
		memberVO.setDe_total_score(de_total_score);
		
		dao.addScore2InMem(memberVO);

		return memberVO;
	}
	
	public void delete(String de_no) {
		dao.delete(de_no);
	}

	public DemandVO findByPK(String de_no) {
		return dao.findByPK(de_no);
	}
	
	public DemandVO findByAvailablePK(String de_no) {
		return dao.findByAvailablePK(de_no);
	}
	
	public List<DemandVO> findMyDemand(String de_mem_no) {
		return dao.findMyDemand(de_mem_no);
	}
	
	public List<DemandVO> findMyOffer(String de_mem_no) {
		return dao.findMyOffer(de_mem_no);
	}

	public List<DemandVO> getAll() {
		return dao.getAll();
	}
	public List<DemandVO> getAvailable() {
		return dao.getAvailable();
	}

}
