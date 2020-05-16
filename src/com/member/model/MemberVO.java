package com.member.model;

import java.sql.*;

public class MemberVO implements java.io.Serializable{

	private String mem_no;
	private String mem_id;
	private String psw;
	private String m_name;
	private Integer gender;
	private String cellphone;
	private String email;
	private String address;
	private String m_photo;
	private byte[] id_card;
	private Integer mem_verify;
	private Integer leader_verify;
	private Integer pur_verify;
	private Timestamp reg_time;
	private Integer acc_balance;
	private Integer de_score_amount;
	private Integer de_total_score;
	private Integer pur_score_amount;
	private Integer pur_total_score;
	private Integer leader_score_amount;
	private Integer leader_total_score;
	private String captcha;

	public MemberVO() {
	}

	public MemberVO(String mem_no, String mem_id, String psw, String m_name, Integer gender, String cellphone,
			String email, String address, String m_photo, byte[] id_card, Integer mem_verify, Integer leader_verify,
			Integer pur_verify, Timestamp reg_time, Integer acc_balance, Integer de_score_amount,
			Integer de_total_score, Integer pur_score_amount, Integer pur_total_score, Integer leader_score_amount,
			Integer leader_total_score, String captcha) {
		super();
		this.mem_no = mem_no;
		this.mem_id = mem_id;
		this.psw = psw;
		this.m_name = m_name;
		this.gender = gender;
		this.cellphone = cellphone;
		this.email = email;
		this.address = address;
		this.m_photo = m_photo;
		this.id_card = id_card;
		this.mem_verify = mem_verify;
		this.leader_verify = leader_verify;
		this.pur_verify = pur_verify;
		this.reg_time = reg_time;
		this.acc_balance = acc_balance;
		this.de_score_amount = de_score_amount;
		this.de_total_score = de_total_score;
		this.pur_score_amount = pur_score_amount;
		this.pur_total_score = pur_total_score;
		this.leader_score_amount = leader_score_amount;
		this.leader_total_score = leader_total_score;
		this.captcha = captcha;
	}

	public String getMem_no() {
		return mem_no;
	}

	public void setMem_no(String mem_no) {
		this.mem_no = mem_no;
	}

	public String getMem_id() {
		return mem_id;
	}

	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}

	public String getPsw() {
		return psw;
	}

	public void setPsw(String psw) {
		this.psw = psw;
	}

	public String getM_name() {
		return m_name;
	}

	public void setM_name(String m_name) {
		this.m_name = m_name;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getM_photo() {
		return m_photo;
	}

	public void setM_photo(String m_photo) {
		this.m_photo = m_photo;
	}

	public byte[] getId_card() {
		return id_card;
	}

	public void setId_card(byte[] id_card) {
		this.id_card = id_card;
	}

	public Integer getMem_verify() {
		return mem_verify;
	}

	public void setMem_verify(Integer mem_verify) {
		this.mem_verify = mem_verify;
	}

	public Integer getLeader_verify() {
		return leader_verify;
	}

	public void setLeader_verify(Integer leader_verify) {
		this.leader_verify = leader_verify;
	}

	public Integer getPur_verify() {
		return pur_verify;
	}

	public void setPur_verify(Integer pur_verify) {
		this.pur_verify = pur_verify;
	}

	public Timestamp getReg_time() {
		return reg_time;
	}

	public void setReg_time(Timestamp reg_time) {
		this.reg_time = reg_time;
	}

	public Integer getAcc_balance() {
		return acc_balance;
	}

	public void setAcc_balance(Integer acc_balance) {
		this.acc_balance = acc_balance;
	}

	public Integer getDe_score_amount() {
		return de_score_amount;
	}

	public void setDe_score_amount(Integer de_score_amount) {
		this.de_score_amount = de_score_amount;
	}

	public Integer getDe_total_score() {
		return de_total_score;
	}

	public void setDe_total_score(Integer de_total_score) {
		this.de_total_score = de_total_score;
	}

	public Integer getPur_score_amount() {
		return pur_score_amount;
	}

	public void setPur_score_amount(Integer pur_score_amount) {
		this.pur_score_amount = pur_score_amount;
	}

	public Integer getPur_total_score() {
		return pur_total_score;
	}

	public void setPur_total_score(Integer pur_total_score) {
		this.pur_total_score = pur_total_score;
	}

	public Integer getLeader_score_amount() {
		return leader_score_amount;
	}

	public void setLeader_score_amount(Integer leader_score_amount) {
		this.leader_score_amount = leader_score_amount;
	}

	public Integer getLeader_total_score() {
		return leader_total_score;
	}

	public void setLeader_total_score(Integer leader_total_score) {
		this.leader_total_score = leader_total_score;
	}

	public String getCaptcha() {
		return captcha;
	}

	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}
	
	
}