package com.notification.model;

import java.util.ArrayList;
import java.util.List;

public class NotificationService {
	private NotificationDAO_interface dao;
	public NotificationService() {
		dao=new NotificationDAO();
	}
	
	
	public NotificationVO addNote(String mem_no, String note_content) {
		NotificationVO notificationVO =new NotificationVO();
		notificationVO.setMem_no(mem_no);
		notificationVO.setNote_content(note_content);
		dao.insert(notificationVO);
		return notificationVO;
	}
	
	public NotificationVO updateStatus(String note_no,Integer note_status) {
		NotificationVO notificationVO =new NotificationVO();
		notificationVO.setNote_no(note_no);
		notificationVO.setNote_status(note_status);
		dao.updateStatus(notificationVO);
		return notificationVO;
	}
	
	public NotificationVO oneNote(String note_no) {
		NotificationVO notificationVO =new NotificationVO();
		notificationVO.setNote_no(note_no);
		
		return dao.findByPrimaryKey(note_no);		
	}
	
	public List<NotificationVO> getMemAllNote(String mem_no){
		List<NotificationVO> list = new ArrayList<NotificationVO>();
		NotificationVO notificationVO =new NotificationVO();
		notificationVO.setMem_no(mem_no);
		list=dao.getOneAll(mem_no);
		return list;
	}
	
	public List<NotificationVO> getAll(){
		return dao.getAll();
	}

}
