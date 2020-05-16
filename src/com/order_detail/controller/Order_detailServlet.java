package com.order_detail.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.order_detail.model.Order_detailService;
import com.order_detail.model.Order_detailVO;
import com.order_master.model.Order_masterService;

public class Order_detailServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req,HttpServletResponse res) 
			throws ServletException, IOException{
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req,HttpServletResponse res) 
			throws ServletException, IOException{
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("p_order_id");
				String str2 = req.getParameter("p_id");
				if(str == null || (str.trim()).length() == 0 &&
						str2 == null || (str.trim()).length() == 0	
						) {
					errorMsgs.add("請輸入訂單編號及商品編號");
				}
				// Send the use back to the form, if there were errors
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/order_detail/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				String p_order_id = null;
				String p_id = null;
				try {
					p_order_id = new String(str);
					p_id = new String(str2);
				}catch(Exception e) {
					errorMsgs.add("商品編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/order_detail/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				/***************************2.開始查詢資料*****************************************/
				Order_detailService order_detailSvc = new Order_detailService();
				Order_detailVO order_detailVO = order_detailSvc.getOneOrder_detail(p_order_id,p_id);
				if(order_detailVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/order_detail/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("order_detailVO", order_detailVO); // 資料庫取出的order_detailVO物件,存入req
				String url = "/back-end/order_detail/listOneOrder_detail.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneOrder_detail.jsp
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理*************************************/
			}catch(Exception e) {
				errorMsgs.add("無法取得資料"+e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/order_detail/select_page.jsp");
				failureView.forward(req, res);
			}
			
		}
		
		if ("getOne_For_Update".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL");
			req.setAttribute("requestURL", requestURL);
			String whichPage = req.getParameter("whichPage");
			req.setAttribute("whichPage", whichPage);
			
			try {
				/***************************1.接收請求參數****************************************/
				String p_order_id = req.getParameter("p_order_id");
				String p_id = req.getParameter("p_id");
				
				/***************************2.開始查詢資料****************************************/
				Order_detailService order_detailSvc = new Order_detailService();
				Order_detailVO order_detailVO = order_detailSvc.getOneOrder_detail(p_order_id,p_id);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("order_detailVO", order_detailVO);         // 資料庫取出的order_detailVO物件,存入req
				String url = "/back-end/order_detail/update_order_detail_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_order_detail_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/order_detail/listAllOrder_detail.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { 
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL");
			req.setAttribute("requestURL", requestURL);
			String whichPage = req.getParameter("whichPage");
			req.setAttribute("whichPage", whichPage);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer od_qty = null;
				try {
					od_qty = new Integer(req.getParameter("od_qty").trim());
				} catch (NumberFormatException e) {
					od_qty = 0;
					errorMsgs.add("數量請填數字.");
				}
								
				String p_order_id = req.getParameter("p_order_id");
				String p_id = req.getParameter("p_id");
				
				Order_detailVO order_detailVO = new Order_detailVO();
				order_detailVO.setOd_qty(od_qty);
				order_detailVO.setP_order_id(p_order_id);
				order_detailVO.setP_id(p_id);
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("order_detailVO", order_detailVO); // 含有輸入格式錯誤的order_detailVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/order_detail/update_order_detail_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				Order_detailService order_detailSvc = new Order_detailService();
				order_detailVO = order_detailSvc.updateOrder_detail(od_qty);
				order_detailVO = order_detailSvc.getOneOrder_detail(p_order_id, p_id);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("order_detailVO", order_detailVO); // 資料庫update成功後,正確的的order_detailVO物件,存入req
				String url = requestURL + "?whichPage=" + whichPage;
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneOrder_detail.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/order_detail/update_order_detail_input.jsp");
				failureView.forward(req, res);
			}
		}


	}
}
