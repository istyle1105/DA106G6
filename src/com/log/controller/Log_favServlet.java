package com.log.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.log.model.LogService;
import com.log.model.LogVO;
import com.logfav.model.Log_favService;
import com.logfav.model.Log_favVO;

public class Log_favServlet extends HttpServlet{
	
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		
		
		
		
		
		
		
		
		
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

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
							.getRequestDispatcher("/log/select_page.jsp");
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
							.getRequestDispatcher("/log/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
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
							.getRequestDispatcher("/log/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("logvo", logvo); // 資料庫取出的empVO物件,存入req
				String url = "/log/listOneLog.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/log/select_page.jsp");
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
				String log_id = new String(req.getParameter("log_id"));
				
				/***************************2.開始查詢資料****************************************/
				LogService logSvc = new LogService();
				LogVO logvo = logSvc.getOneLog(log_id);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("logvo", logvo);         // 資料庫取出的empVO物件,存入req
				String url = "/log/update_log_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/log/listAllLog.jsp");
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
				String log_id = req.getParameter("log_id");
				String mem_no = req.getParameter("mem_no");
				
				
				
				Log_favVO logfavvo = new Log_favVO();
				logfavvo.setLog_id(log_id);
				logfavvo.setMem_no(mem_no);

				
				
				/***************************2.開始修改資料*****************************************/
				Log_favService logfavSvc = new Log_favService();
				logfavSvc.delete(log_id, mem_no);
//				logvo = logSvc.updateLog(log_id,mem_no, log_title, log_con, l_status, l_favorited);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				String url = "/front-end/log/oneLog.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/log/oneLog.jsp");
				failureView.forward(req, res);
			}
		}

		
	
		

        if ("insert".equals(action)) { // 來自addEmp.jsp的請求  

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			System.out.println("666");
			
			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String mem_no = req.getParameter("mem_no");
				
				String log_id = req.getParameter("log_id");
				
				Log_favVO log_favvo = new Log_favVO();
				log_favvo.setLog_id(log_id);
				log_favvo.setMem_no(mem_no);
				
				
				/***************************2.開始新增資料***************************************/
				Log_favService log_favSVC = new Log_favService();
				log_favvo = log_favSVC.addLog_fav(log_id, mem_no);
				
				LogService logsvc = new LogService();
				LogVO logvo = new LogVO();
				logvo = logsvc.getOneLog(log_id);
				logsvc.updateLog(log_id, logvo.getMem_no(), logvo.getLog_title(), logvo.getLog_con(), logvo.getL_status(), logvo.getL_favorited() + 1, logvo.getLog_pic());

				
				
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
//				String url = "/front-end/log/oneLog.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
//				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/log/addLog.jsp");
				failureView.forward(req, res);
			}
		}
		
        
        
        
		
		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				String log_id = new String(req.getParameter("log_id"));
				String mem_no = new String(req.getParameter("mem_no"));
				
				if(mem_no == null || mem_no.trim().length() == 0) {
					res.sendRedirect(req.getContextPath()+"/front-end/log/logHome.jsp");
					return;
				}
				Log_favVO log_favvo = new Log_favVO();
				log_favvo.setLog_id(log_id);
				log_favvo.setMem_no(mem_no);
				/***************************2.開始刪除資料***************************************/
				Log_favService logfavSvc = new Log_favService();
				logfavSvc.delete(log_id,mem_no);
				
				LogService logsvc = new LogService();
				LogVO logvo = new LogVO();
				logvo = logsvc.getOneLog(log_id);
				logsvc.updateLog(log_id, logvo.getMem_no(), logvo.getLog_title(), logvo.getLog_con(), logvo.getL_status(), logvo.getL_favorited() - 1, logvo.getLog_pic());

				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/front-end/log/favList.jsp?cancel=true";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/log/favList.jsp");
			 	failureView.forward(req, res);
			}
		}
	}




}
