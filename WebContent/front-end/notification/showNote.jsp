<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.notification.model.*"%>
<%
	String note_no=request.getParameter("note_no");
	NotificationService notificationSvc=new NotificationService();
	NotificationVO notificationVO=null;
	notificationSvc.updateStatus(note_no,1);
	
	notificationVO=notificationSvc.oneNote(note_no);	
	out.print("會員通知：" + notificationVO.getNote_content());


%>