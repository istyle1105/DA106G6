package com.log.controller;

import java.io.*;
import java.sql.Timestamp;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import org.apache.catalina.Session;
import org.json.JSONException;
import org.json.JSONObject;

import com.log.model.*;
import com.logcom.model.Log_comService;
import com.logcom.model.Log_comVO;
import com.logreport.model.Log_reportService;
import com.logreport.model.Log_reportVO;


//@WebServlet("/log/LogServlet.do")
public class LogServlet extends HttpServlet {
	
	
	
	public static byte[] getPictureByteArray(String path) throws IOException {
		File file = new File(path);
		FileInputStream fis = new FileInputStream(file);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buffer = new byte[8192];
		int i;
		while ((i = fis.read(buffer)) != -1) {
			baos.write(buffer, 0, i);
		}
		baos.close();
		fis.close();

		return baos.toByteArray();
	}
	

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		
		if ("getOne_Report".equals(action)) {

			try {
				// Retrieve form parameters.
				String log_id = new String(req.getParameter("log_id"));

				LogService logSVC = new LogService();
				LogVO logvo = logSVC.getOneLog(log_id);

				req.setAttribute("logvo", logvo); // 資料庫取出的empVO物件,存入req
				
				
				
				// 取出的empVO送給listOneEmp.jsp
				RequestDispatcher successView = req
						.getRequestDispatcher("/back-end/log/newback.jsp");
				successView.forward(req, res);
				return;

				// Handle any unusual exceptions
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}
		
		
		
		
		
		
		
        if ("insert_Log_Com".equals(action)) { // 來自addEmp.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);


			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String mem_no = req.getParameter("mem_no");
				
				if(mem_no == null || mem_no.trim().length() == 0) {
					res.sendRedirect(req.getContextPath()+"/front-end/member/Login.jsp");
					return;
				}
				
				
				String log_id = req.getParameter("log_id");
				String log_com = req.getParameter("log_com");
				LogService logSvc = new LogService();
				LogVO logvo = logSvc.getOneLog(log_id);
				req.setAttribute("logvo", logvo);
				if (log_com == null || log_com.trim().length() == 0) {
					errorMsgs.add("留言內容: 請勿空白");
				} 
				
				
				Log_comVO log_comvo = new Log_comVO();
				log_comvo.setMem_no(mem_no);
				log_comvo.setLog_id(log_id);
				log_comvo.setLog_com(mem_no+":"+ log_com);
				
				

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("log_comvo", log_comvo); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/log/oneLog.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				Log_comService log_comSvc = new Log_comService();
				log_comvo = log_comSvc.addLog_com(mem_no, log_id, log_com,"1");
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				
				String url = "/front-end/log/oneLog.jsp";
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
							.getRequestDispatcher("/front-end/log/logHome.jsp");
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
							.getRequestDispatcher("/front-end/log/logHome.jsp");
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
							.getRequestDispatcher("/front-end/log/logHome.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				HttpSession session = req.getSession();// 資料庫取出的empVO物件,存入req
				session.setAttribute("list", list);
				String url = "/front-end/log/listAllLog.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/log/logHome.jsp");
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
					
					if (str == null || (str.trim()).length() == 0) {
						errorMsgs.add("請輸入會員編號");
					}
					
					try {
						mem_no = new String(str);
					} catch (Exception e) {
						errorMsgs.add("會員格式不正確");
					}
					// Send the use back to the form, if there were errors
					if (!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = req
								.getRequestDispatcher("/log/back.jsp");
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
								.getRequestDispatcher("/log/back.jsp");
						failureView.forward(req, res);
						return;//程式中斷
					}
					
					/***************************3.查詢完成,準備轉交(Send the Success view)*************/
//					req.setAttribute("list", list); // 資料庫取出的empVO物件,存入req
					HttpSession session = req.getSession();
					session.setAttribute("list", list);
					String url = "/log/listMemId.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
					successView.forward(req, res);

					/***************************其他可能的錯誤處理*************************************/
				} catch (Exception e) {
					errorMsgs.add("無法取得資料:" + e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher("/log/back.jsp");
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
							.getRequestDispatcher("/front-end/log/logHome.jsp");
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
							.getRequestDispatcher("/log/back.jsp");
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
							.getRequestDispatcher("/front-end/log/logHome.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("logvo", logvo); // 資料庫取出的empVO物件,存入req
				String url = "/front-end/log/oneLog.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);
				
				
				
				
				
				

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/log/logHome.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOne_For_Display_Ajax".equals(action)) { // 來自select_page.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);


				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String log_id = req.getParameter("log_id");
				
	/***************************2.開始查詢資料*****************************************/
				LogService logSvc = new LogService();
				LogVO logvo = logSvc.getOneLog(log_id);

				//這裡開始換成JSONObject
				JSONObject obj= new JSONObject();
				try {
					obj.put("log_id", logvo.getLog_id());
					obj.put("log_con", logvo.getLog_con());
					obj.put("log_title", logvo.getLog_title());
				} catch (JSONException e) {
	
					e.printStackTrace();
				}
				
				res.setCharacterEncoding("UTF-8");
				res.setContentType("text/plain");
				PrintWriter out =res.getWriter();
				out.write(obj.toString());
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
			
				
				/***************************其他可能的錯誤處理*************************************/
			
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
				String url = "/front-end/log/updateLog.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/log/myList.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		
		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			BufferedInputStream inputstream = null;
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String mem_no = req.getParameter("mem_no");
				
				String log_id = req.getParameter("log_id");
				
				Integer l_status = null;
				try {
					l_status = new Integer(req.getParameter("l_status").trim());
				} catch (Exception e) {
					errorMsgs.add("2!");
				}
				
				Integer l_favorited = null;
				try {
					l_favorited = new Integer(req.getParameter("l_favorited").trim());
				} catch (Exception e) {
					errorMsgs.add("1!");
				}
				
				String mem_noReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (mem_no == null || mem_no.trim().length() == 0) {
					errorMsgs.add("會員編號: 請勿空白");
				} else if(!mem_no.trim().matches(mem_noReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("會員編號: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }
				
				String log_title = req.getParameter("log_title");
				String log_titleReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,50}$";
				if (log_title == null || log_title.trim().length() == 0) {
					errorMsgs.add("日誌標題: 請勿空白");
				} 
				
				String log_con = req.getParameter("content");
				if (log_con == null || log_con.trim().length() == 0) {
					errorMsgs.add("日誌內容: 請勿空白");
				} 
				
				String upload = null ;
				Part filePart = req.getPart("log_pic");
				inputstream = new BufferedInputStream(filePart.getInputStream());
				byte[] targetArray = new byte[inputstream.available()];
				inputstream.read(targetArray);
				upload = Base64.getEncoder().encodeToString(targetArray);
				
				if(upload.isEmpty()) {
					LogService logSvc = new LogService();
					LogVO logvotmp = new LogVO();
					logvotmp = logSvc.getOneLog(log_id);
					upload = logvotmp.getLog_pic();
					
				}
				
				
				
//				byte[] targetArray = new byte[inputstream.available()];
//				inputstream.read(targetArray);
//				
//				 upload = Base64.getEncoder().encodeToString(targetArray);
				
				LogVO logvo = new LogVO();
				logvo.setMem_no(mem_no);
				logvo.setLog_title(log_title);
				logvo.setLog_con(log_con);
				logvo.setLog_pic(upload);
				logvo.setL_status(l_status);
				logvo.setL_favorited(l_favorited);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("logvo", logvo); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/log/updateLog.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				LogService logSvc = new LogService();
				logvo = logSvc.updateLog(log_id,mem_no, log_title, log_con, l_status, l_favorited ,logvo.getLog_pic());
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
//				LogVO logvo2 = logSvc.getOneLog(log_id);
//				req.setAttribute("logvo", logvo2); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/front-end/log/myList.jsp?update=true";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/log/updateLog.jsp");
				failureView.forward(req, res);
			}
		}

		
	
		

        if ("insert".equals(action)) { // 來自addEmp.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			BufferedInputStream inputstream = null;
			
			

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String mem_no = req.getParameter("mem_no");
				
				String mem_noReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (mem_no == null || mem_no.trim().length() == 0) {
					errorMsgs.add("會員編號: 請勿空白");
				} else if(!mem_no.trim().matches(mem_noReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("會員編號: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }
				
				String log_title = req.getParameter("log_title");
				String log_titleReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,50}$";
				if (log_title == null || log_title.trim().length() == 0) {
					errorMsgs.add("日誌標題: 請勿空白");
				} 
				
				String log_con = req.getParameter("content");
				if (log_con == null || log_con.trim().length() == 0) {
					errorMsgs.add("日誌內容: 請勿空白");
				} 
				
				Part filePart = req.getPart("log_pic");
				if(filePart != null) {
					inputstream = new BufferedInputStream(filePart.getInputStream());
				}
				
				
				
				byte[] targetArray = new byte[inputstream.available()];
				inputstream.read(targetArray);
				
				String upload = Base64.getEncoder().encodeToString(targetArray);
				
				LogVO logvo = new LogVO();
				logvo.setMem_no(mem_no);
				logvo.setLog_title(log_title);
				logvo.setLog_con(log_con);
				logvo.setLog_pic(upload);
				
				

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("logvo", logvo); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/log/addLog.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				LogService logSvc = new LogService();
				logvo = logSvc.addLog(mem_no, log_title, log_con, logvo.getLog_pic());

				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				req.setAttribute("logvo", logvo);
				String url = "/front-end/log/myList.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/log/addLog.jsp");
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

