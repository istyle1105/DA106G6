package com.wallet.model;

import java.util.*;

public class WalletService {

	private WalletDAO_interface dao;

	public WalletService() {
		dao = new WalletDAO();
	}

	synchronized public WalletVO addWallet(
		String mem_no, 
		Integer w_amount,
		String w_txn_note, 
		Integer w_txn_status) {

		WalletVO walletVO = new WalletVO();
		walletVO.setMem_no(mem_no);
		walletVO.setW_amount(w_amount);
		walletVO.setW_txn_note(w_txn_note);
		walletVO.setW_txn_status(w_txn_status);
		dao.insert(walletVO);

		return walletVO;
	}

	public List<WalletVO> getAll() {
		return dao.getAll();
	}
	
	public List<WalletVO> getByMen_no(String mem_no) {
		return dao.getByMemno(mem_no);
	}

	public Integer getTotalByMen_no(String mem_no) {
		List<WalletVO> list = dao.getByMemno(mem_no);
		Integer TotalW_amount = 0;
		
		for(WalletVO wallVO : list) {
			int amountadd = 0;
			int amountminus = 0;
			
			if(wallVO.getW_txn_status()==0) {
				amountadd=wallVO.getW_amount();
			}else if(wallVO.getW_txn_status()==1) {
				amountminus=wallVO.getW_amount();
				System.out.println("扣款錢為"+amountminus);
			}
			TotalW_amount =TotalW_amount + amountadd - amountminus;
		}	
		return TotalW_amount;
	}
}
