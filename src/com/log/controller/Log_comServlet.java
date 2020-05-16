package com.log.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.log.model.LogService;
import com.log.model.LogVO;

public class Log_comServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		
		
		if ("getLog_Title".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("log_title");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入日誌標題");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/log/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				String log_title = null;
				try {
					log_title = new String(str);
				} catch (Exception e) {
					errorMsgs.add("日誌標題格式不正確");
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
				List<LogVO> list = logSvc.getLogTitle(log_title);
				if (list == null) {
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
				req.setAttribute("list", list); // 資料庫取出的empVO物件,存入req
				String url = "/log/listLogTitle.jsp";
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
		
		
		
		
			if ("getMem_For_Display".equals(action)) { // 來自select_page.jsp的請求

				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("errorMsgs", errorMsgs);

				try {
					/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
					String str = req.getParameter("mem_no");
					String mem_no = null;
					try {
						mem_no = new String(str);
					} catch (Exception e) {
						errorMsgs.add("會員格式不正確");
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
					List<LogVO> list = logSvc.getMemAll(mem_no);
					if (list == null) {
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
					req.setAttribute("list", list); // 資料庫取出的empVO物件,存入req
					String url = "/log/listMemId.jsp";
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
				String log_title = req.getParameter("log_title");
				String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				
				if (log_title == null || log_title.trim().length() == 0) {
					errorMsgs.add("日誌標題: 請勿空白");
				} else if(!log_title.trim().matches(enameReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("日誌標題: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }
				
				String log_con = req.getParameter("content");
				if (log_con == null || log_con.trim().length() == 0) {
					errorMsgs.add("日誌內容: 請勿空白");
				} 
				
				Integer l_status = new Integer(req.getParameter("l_status").trim());
				Integer l_favorited = new Integer(req.getParameter("l_favorited").trim());
				
				
				
				
				
				

				LogVO logvo = new LogVO();
				logvo.setLog_id(log_id);
				logvo.setMem_no(mem_no);
				logvo.setLog_title(log_title);
				logvo.setLog_con(log_con);
				logvo.setL_status(l_status);
				logvo.setL_favorited(l_favorited);
				

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("logvo", logvo); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/log/update_log_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				LogService logSvc = new LogService();
//				logvo = logSvc.updateLog(log_id,mem_no, log_title, log_con, l_status, l_favorited);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				LogVO logvo2 = logSvc.getOneLog(log_id);
				req.setAttribute("logvo", logvo2); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/log/listOneLog.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/log/update_log_input.jsp");
				failureView.forward(req, res);
			}
		}

		
	
		

//        if ("insert".equals(action)) { // 來自addEmp.jsp的請求  
//			
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			try {
//				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
//				String mem_no = req.getParameter("mem_no");
//				String mem_noReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
//				if (mem_no == null || mem_no.trim().length() == 0) {
//					errorMsgs.add("會員編號: 請勿空白");
//				} else if(!mem_no.trim().matches(mem_noReg)) { //以下練習正則(規)表示式(regular-expression)
//					errorMsgs.add("會員編號: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
//	            }
//				
//				String log_title = req.getParameter("log_title");
//				String log_titleReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
//				if (log_title == null || log_title.trim().length() == 0) {
//					errorMsgs.add("日誌標題: 請勿空白");
//				} else if(!log_title.trim().matches(log_titleReg)) { //以下練習正則(規)表示式(regular-expression)
//					errorMsgs.add("日誌標題: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
//	            }
//				
//				String log_con = req.getParameter("content");
//				if (log_con == null || log_con.trim().length() == 0) {
//					errorMsgs.add("日誌內容: 請勿空白");
//				} 
//				
//				
//				LogVO logvo = new LogVO();
//				logvo.setMem_no(mem_no);
//				logvo.setLog_title(log_title);
//				logvo.setLog_con(log_con);
////				logvo.setLog_pic(log_pic);
//				
//
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					req.setAttribute("logvo", logvo); // 含有輸入格式錯誤的empVO物件,也存入req
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/log/addLog.jsp");
//					failureView.forward(req, res);
//					return;
//				}
//				
//				/***************************2.開始新增資料***************************************/
//				LogService logSvc = new LogService();
//				logvo = logSvc.addLog(mem_no, log_title, log_con);
//				
//				/***************************3.新增完成,準備轉交(Send the Success view)***********/
//				String url = "/log/listAllLog.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
//				successView.forward(req, res);				
//				
//				/***************************其他可能的錯誤處理**********************************/
//			} catch (Exception e) {
//				errorMsgs.add(e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/log/addLog.jsp");
//				failureView.forward(req, res);
//			}
//		}
//		
		
		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				String log_id = new String(req.getParameter("log_id"));
				
				/***************************2.開始刪除資料***************************************/
				LogService logSvc = new LogService();
				logSvc.deleteLog(log_id);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/log/listAllLog.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/log/listAllLog.jsp");
			 	failureView.forward(req, res);
			}
		}
	}




}
