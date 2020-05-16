package com.demand.model;
import java.util.List;

import com.member.model.MemberVO;
import com.wallet.model.WalletVO;

public interface DemandDAO_interface {
	
	void add(DemandVO demandVO);
	void update(DemandVO demandVO);
	void update_purmem(DemandVO demandVO);
	void updateStatus(DemandVO demandVO);
	void delete(String de_no);
	DemandVO findByPK(String de_no);
	DemandVO findByAvailablePK(String de_no);
	List<DemandVO> findMyDemand(String de_mem_no);
	List<DemandVO> findMyOffer(String de_mem_no);
	List<DemandVO> getAll();
	List<DemandVO> getAvailable();
	void returnWallet(WalletVO walletVO);
	void addScore(DemandVO demandVO);
	void addScore2(DemandVO demandVO);
	void addScoreInMem(MemberVO memberVO);
	void addScore2InMem(MemberVO memberVO);

}
