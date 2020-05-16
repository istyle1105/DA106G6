package com.p_message.controller;

import java.io.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.p_message.model.P_MessageService;
import com.p_message.model.P_MessageVO;

@MultipartConfig
public class P_MessageServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("p_msg_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入MSG編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/p_message/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				P_MessageService p_messageSvc = new P_MessageService();
				P_MessageVO p_messageVO = p_messageSvc.findByPK(str);
				if (p_messageVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/p_message/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("p_messageVO", p_messageVO); // 資料庫取出的demandVO物件,存入req
				String url = "/p_message/listOneP_message.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneDemand.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/p_message/select_page.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOne_For_Update".equals(action)) { // 來自listAllMSG.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String p_msg_no = req.getParameter("p_msg_no");

				/*************************** 2.開始查詢資料 ****************************************/
				P_MessageService p_messageSvc = new P_MessageService();
				P_MessageVO p_messageVO = p_messageSvc.findByPK(p_msg_no);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("p_messageVO", p_messageVO); // 資料庫取出的demandVO物件,存入req
				String url = "/p_message/update_p_message_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_demand_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/p_message/listAllP_message.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) { // 來自update_msg_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/

				String p_msg = req.getParameter("p_msg");
				String p_msg2 = req.getParameter("p_msg2");
				String p_msg_no = req.getParameter("p_msg_no");
				String de_no = req.getParameter("de_no");
				String mem_no = req.getParameter("mem_no");
				Timestamp p_msg_time = java.sql.Timestamp.valueOf(req.getParameter("p_msg_time"));
				
				if (p_msg == null) {
					errorMsgs.add("請輸出內容");
				}

				P_MessageVO p_messageVO = new P_MessageVO();
				p_messageVO.setP_msg(p_msg2 + mem_no + " : " + p_msg + "<br>");
				p_messageVO.setP_msg_no(p_msg_no);
				

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("p_messageVO", p_messageVO); // 含有輸入格式錯誤的demandVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/p_message/update_p_message_input.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				P_MessageService p_messageSvc = new P_MessageService();
				p_messageVO = p_messageSvc.update(p_messageVO.getP_msg_no(), p_messageVO.getP_msg());
				
				p_messageVO.setDe_no(de_no);
				p_messageVO.setMem_no(mem_no);
				p_messageVO.setP_msg_time(p_msg_time);
				
				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("p_messageVO", p_messageVO); // 資料庫update成功後,正確的的demandVO物件,存入req
				String url = "/p_message/listOneP_message.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneDemand.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/p_message/update_p_message_input.jsp");
				failureView.forward(req, res);
			}
		}

		if ("add".equals(action)) { // 來自addDemand.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				String mem_no = req.getParameter("mem_no");
				String de_no = req.getParameter("de_no");
				
				String p_msg = req.getParameter("p_msg");
				if (p_msg == null) 
					errorMsgs.add("内容請勿空白");
				
				p_msg = mem_no + " : " + p_msg + "<br>";

				P_MessageVO p_messageVO = new P_MessageVO();
				p_messageVO.setMem_no(mem_no);
				p_messageVO.setDe_no(de_no);
				p_messageVO.setP_msg(p_msg);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("p_messageVO", p_messageVO); // 含有輸入格式錯誤的demandVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/p_message/addP_message.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				P_MessageService demandSvc = new P_MessageService();
				p_messageVO = demandSvc.add(de_no, mem_no, p_msg);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/p_message/listAllP_message.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllDemand.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/p_message/addP_message.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
