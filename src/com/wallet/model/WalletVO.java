package com.wallet.model;

import java.sql.Timestamp;

public class WalletVO implements java.io.Serializable {
	private String w_rec_id;
	private String mem_no;
	private Integer w_amount;
	private Timestamp w_txn_time;
	private String w_txn_note;
	private Integer w_txn_status;
	
	public WalletVO() {
		
	}

	public WalletVO(String w_rec_id, String mem_no, Integer w_amount, Timestamp w_txn_time, String w_txn_note,
			Integer w_txn_status) {
		super();
		this.w_rec_id = w_rec_id;
		this.mem_no = mem_no;
		this.w_amount = w_amount;
		this.w_txn_time = w_txn_time;
		this.w_txn_note = w_txn_note;
		this.w_txn_status = w_txn_status;
	}

	public String getW_rec_id() {
		return w_rec_id;
	}

	public void setW_rec_id(String w_rec_id) {
		this.w_rec_id = w_rec_id;
	}

	public String getMem_no() {
		return mem_no;
	}

	public void setMem_no(String mem_no) {
		this.mem_no = mem_no;
	}

	public Integer getW_amount() {
		return w_amount;
	}

	public void setW_amount(Integer w_amount) {
		this.w_amount = w_amount;
	}

	public Timestamp getW_txn_time() {
		return w_txn_time;
	}

	public void setW_txn_time(Timestamp w_txn_time) {
		this.w_txn_time = w_txn_time;
	}

	public String getW_txn_note() {
		return w_txn_note;
	}

	public void setW_txn_note(String w_txn_note) {
		this.w_txn_note = w_txn_note;
	}

	public Integer getW_txn_status() {
		return w_txn_status;
	}

	public void setW_txn_status(Integer w_txn_status) {
		this.w_txn_status = w_txn_status;
	}

}
