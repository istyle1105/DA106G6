package com.log.controller;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.log.model.LogService;
import com.log.model.LogVO;
import com.logreport.model.Log_reportService;
import com.logreport.model.Log_reportVO;

public class Log_reportServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		
		if ("getOne_Log".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("log_id");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入日誌編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/log/newback.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				String log_id = null;
				try {
					log_id = new String(str);
				} catch (Exception e) {
					errorMsgs.add("日誌編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/log/newback.jsp");
					failureView.forward(req, res);
					
				}
				
				/***************************2.開始查詢資料*****************************************/
				LogService logSvc = new LogService();
				LogVO logvo = logSvc.getOneLog(log_id);
				if (logvo == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/log/newback.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("logvo", logvo); // 資料庫取出的empVO物件,存入req
				String url = "/back-end/log/oneLog.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);
				
				
				
				
				
				

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/log/newback.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		
		
		
		
		
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("l_re_id");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入檢舉編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/log/newback.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				String l_re_id = null;
				try {
					l_re_id = new String(str);
				} catch (Exception e) {
					errorMsgs.add("檢舉編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/log/newback.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				Log_reportService log_reportSvc = new Log_reportService();
				Log_reportVO log_reportvo = log_reportSvc.getOneLog_report(l_re_id);
				
				if (log_reportvo == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/log/newback.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("log_reportVO", log_reportvo); // 資料庫取出的empVO物件,存入req
				String url = "/back-end/log/oneReport.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/log/newback.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String l_re_id = new String(req.getParameter("l_re_id"));
				
				/***************************2.開始查詢資料****************************************/
				Log_reportService log_reportSvc = new Log_reportService();
				Log_reportVO log_reportvo = log_reportSvc.getOneLog_report(l_re_id);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("log_reportvo", log_reportvo);         // 資料庫取出的empVO物件,存入req
				String url = "/back-end/log/reportUpdate.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/log/newback.jsp");
				failureView.forward(req, res);
			}
		}
		
		

		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String l_re_id = req.getParameter("l_re_id");
				String mem_no = req.getParameter("mem_no");
				String log_id = req.getParameter("log_id");
				String l_re_reason = req.getParameter("l_re_reason");
				
				
				Integer l_re_status = null;
				try {
					l_re_status = new Integer(req.getParameter("l_re_status").trim());
				} catch (Exception e) {
					errorMsgs.add("請選狀態!");
					
				}

				Log_reportVO log_reportvo = new Log_reportVO();
				log_reportvo.setL_re_id(l_re_id);
				log_reportvo.setMem_no(mem_no);
				log_reportvo.setLog_id(log_id);
				log_reportvo.setL_re_reason(l_re_reason);
				log_reportvo.setL_re_status(l_re_status);
				

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("log_reportvo", log_reportvo); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/log/reportUpdate.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				Log_reportService log_reportSvc = new Log_reportService();
				log_reportvo = log_reportSvc.updateLog_report(l_re_id, mem_no, log_id, l_re_reason, 1);
				
				LogService logsvc = new LogService();
				LogVO logvo = new LogVO();
				logvo = logsvc.getOneLog(log_id);
				logsvc.updateLog(logvo.getLog_id(), logvo.getMem_no(), logvo.getLog_title(), logvo.getLog_con(), l_re_status, logvo.getL_favorited(), logvo.getLog_pic());
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				Log_reportVO log_reportvo2 = log_reportSvc.getOneLog_report(l_re_id);
				req.setAttribute("log_reportVO", log_reportvo2); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/back-end/log/oneReport.jsp?update=true";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);
				

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/log/reportUpdate.jsp");
				failureView.forward(req, res);
			}
		}

		
		
		if ("insert_Log_report".equals(action)) { // 來自addEmp.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String mem_no = req.getParameter("mem_no");
				
				if(mem_no == null || mem_no.trim().length() == 0) {
					res.sendRedirect(req.getContextPath()+"/front-end/log/logHome.jsp");
					return;
				}
				
				String log_id = req.getParameter("log_id");
				
				String l_re_reason = req.getParameter("l_re_reason");
				if (l_re_reason == null || l_re_reason.trim().length() == 0) {
					errorMsgs.add("檢舉原因: 請勿空白");
				} 
				
				Log_reportVO log_revo = new Log_reportVO();
				
				/***************************日誌物件返回***************************************/
				LogService logSvc = new LogService();
				LogVO logvo = logSvc.getOneLog(log_id);
				req.setAttribute("logvo", logvo);
				
				

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/log/oneLog.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				Log_reportService log_reSvc = new Log_reportService();
				log_revo = log_reSvc.addLog_report(mem_no, log_id, l_re_reason);
				
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				
				String url = "/front-end/log/oneLog.jsp?report=true";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/log/oneLog.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		
		
		
		
		
		
	}
	

}
