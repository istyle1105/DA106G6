package com.chat.model;

import java.sql.*;

public class ChatVO implements java.io.Serializable{
	
	private String chat_id;
	private String mem_no1;
	private String mem_no2;
	private String chat_content;
	private Timestamp chat_time;
	
	public ChatVO() {
	}

	public ChatVO(String chat_id, String mem_no1, String mem_no2, String chat_content, Timestamp chat_time) {
		super();
		this.chat_id = chat_id;
		this.mem_no1 = mem_no1;
		this.mem_no2 = mem_no2;
		this.chat_content = chat_content;
		this.chat_time = chat_time;
	}

	public String getChat_id() {
		return chat_id;
	}

	public void setChat_id(String chat_id) {
		this.chat_id = chat_id;
	}

	public String getMem_no1() {
		return mem_no1;
	}

	public void setMem_no1(String mem_no1) {
		this.mem_no1 = mem_no1;
	}

	public String getMem_no2() {
		return mem_no2;
	}

	public void setMem_no2(String mem_no2) {
		this.mem_no2 = mem_no2;
	}

	public String getChat_content() {
		return chat_content;
	}

	public void setChat_content(String chat_content) {
		this.chat_content = chat_content;
	}

	public Timestamp getChat_time() {
		return chat_time;
	}

	public void setChat_time(Timestamp chat_time) {
		this.chat_time = chat_time;
	}
	



}
