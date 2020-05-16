package com.notification.controller;
import java.io.*;
import java.sql.Timestamp;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.notification.model.NotificationService;
import com.notification.model.NotificationVO;


public class NotificationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action=req.getParameter("action");
		String action2=req.getParameter("action2");
		
		if("listOneNote".equals(action)) {
			try {
			String note_no=req.getParameter("note_no");
			NotificationService notificationSvc=new NotificationService();
			NotificationVO notificationVO=new NotificationVO();
			
			notificationSvc.updateStatus(note_no, 1);
			notificationVO=notificationSvc.oneNote(note_no);
			
			req.setAttribute("notificationVO",notificationVO );
			//Bootstrap_modal
			boolean openModal=true;
			req.setAttribute("openModal",openModal );
			
			RequestDispatcher successView = req
					.getRequestDispatcher("/front-end/notification/listMemNote.jsp");
			successView.forward(req, res);
			return;
			}catch (Exception e) {
				throw new ServletException(e);
			}
			
		}
		
		
		if("getOne_Mem_Display".equals(action)) {
			List<String> errorMsgs=new LinkedList<String>();
			req.setAttribute("errorMsgs",errorMsgs);
			
			try {
				String mem_no=req.getParameter("mem_no");
				NotificationService notificationSvc=new NotificationService();
				List<NotificationVO> list=notificationSvc.getMemAllNote(mem_no);
				
				req.setAttribute("list",list);
				String url ="/front-end/notification/listMemAllNote.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);
				
				
				
			}catch(Exception e) {
				errorMsgs.add("無法取得資料:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/notification/select_page.jsp");
				failureView.forward(req, res);			
			}		
		}

	}

}
