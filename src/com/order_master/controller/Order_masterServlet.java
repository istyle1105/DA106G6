package com.order_master.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.cart.model.CartService;
import com.member.model.MemberService;
import com.order_detail.model.Order_detailVO;
import com.order_master.model.Order_masterService;
import com.order_master.model.Order_masterVO;
import com.product.model.ProductVO;
import com.wallet.model.WalletService;

public class Order_masterServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req,HttpServletResponse res) 
			throws ServletException, IOException{
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req,HttpServletResponse res) 
			throws ServletException, IOException{
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		HttpSession session = req.getSession();
		
		if("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("p_order_id");
				if(str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入訂單編號");
				}
				// Send the use back to the form, if there were errors
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/order_master/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				String p_order_id = null;
				try {
					p_order_id = new String(str);
				}catch(Exception e) {
					errorMsgs.add("訂單編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/order_master/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				/***************************2.開始查詢資料*****************************************/
				Order_masterService order_masterSvc = new Order_masterService();
				Order_masterVO order_masterVO = order_masterSvc.getOneOrder_master(p_order_id);
				if(order_masterVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/order_master/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("order_masterVO", order_masterVO); // 資料庫取出的order_masterVO物件,存入req
				String url = "/back-end/order_master/listOneOrder_master.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneOrder_master.jsp
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理*************************************/
			}catch(Exception e) {
				errorMsgs.add("無法取得資料"+e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/order_master/select_page.jsp");
				failureView.forward(req, res);
			}
			
		}

		if ("getOne_For_Update".equals(action)) { // 來自listAllOrder_master.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL");
			req.setAttribute("requestURL", requestURL);
			String whichPage = req.getParameter("whichPage");
			req.setAttribute("whichPage", whichPage);
			
			try {
				/***************************1.接收請求參數****************************************/
				String p_order_id = req.getParameter("p_order_id");
				
				/***************************2.開始查詢資料****************************************/
				Order_masterService order_masterSvc = new Order_masterService();
				Order_masterVO order_masterVO = order_masterSvc.getOneOrder_master(p_order_id);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("order_masterVO", order_masterVO);         // 資料庫取出的order_masterVO物件,存入req
				String url = "/back-end/order_master/update_order_master_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_order_master_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/order_master/listAllOrder_master.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // 來自update_order_master_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL");
			req.setAttribute("requestURL", requestURL);
			String whichPage = req.getParameter("whichPage");
			req.setAttribute("whichPage", whichPage);
			
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String p_order_id = req.getParameter("p_order_id");
				
				Integer om_status = new Integer(req.getParameter("om_status").trim());

				java.sql.Timestamp om_dlvr = java.sql.Timestamp.valueOf(req.getParameter("om_dlvr").trim());

				Order_masterVO order_masterVO = new Order_masterVO();
				order_masterVO.setOm_status(om_status);
				order_masterVO.setOm_dlvr(om_dlvr);
				order_masterVO.setP_order_id(p_order_id);
				
				System.out.println("訂單編號=" + p_order_id + " ,訂單狀態=" +om_status + " ,出貨時間=" + om_dlvr);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("order_masterVO", order_masterVO); // 含有輸入格式錯誤的order_masterVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/order_master/update_order_master_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				Order_masterService order_masterSvc = new Order_masterService();
				order_masterVO = order_masterSvc.updateOrder_master(om_status, om_dlvr,p_order_id);
				order_masterVO = order_masterSvc.getOneOrder_master(p_order_id);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("order_masterVO", order_masterVO); // 資料庫update成功後,正確的的order_masterVO物件,存入req
				String url = requestURL + "?whichPage=" + whichPage;
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneOrder_master.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/order_master/update_order_master_input.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { // 來自addOrder_master.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String mem_no = req.getParameter("mem_no");
				Integer om_tpr = new Integer(req.getParameter("om_tpr").trim());
				
				Order_masterVO order_masterVO = new Order_masterVO();
				order_masterVO.setMem_no(mem_no);
				order_masterVO.setOm_tpr(om_tpr);
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("order_masterVO", order_masterVO); // 含有輸入格式錯誤的order_masterVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/order_master/addOrder_master.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				Order_masterService order_masterSvc = new Order_masterService();
				order_masterVO = order_masterSvc.addOrder_master(mem_no, om_tpr);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/back-end/order_master/listAllOrder_master.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllOrder_master.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/order_master/addOrder_master.jsp");
				failureView.forward(req, res);
			}
		}
        
		if ("insertWithOrder_detailsAndwallet".equals(action)) {
			 List<String> errorMsgs = new LinkedList<String>();
		 	String requestURL = req.getParameter("requestURL");
			req.setAttribute("requestURL", requestURL);
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				@SuppressWarnings("unchecked")
				List<ProductVO> buylist = (List<ProductVO>) session.getAttribute("buylist");
				System.out.println(buylist.size());
				
				String mem_no = req.getParameter("mem_no");
				Integer om_tpr = new Integer(req.getParameter("om_tpr"));
				WalletService walletSVC = new WalletService();
				
				System.out.println("總金額" + om_tpr);
				System.out.println("餘額不足回去的網頁" + requestURL);
				System.out.println("會員編號" + walletSVC.getTotalByMen_no(mem_no));
				
				if(om_tpr > walletSVC.getTotalByMen_no(mem_no)) {
				     req.setAttribute("buylist", buylist);
				     req.setAttribute("amount",om_tpr);
				     String url="/front-end/shop/shopCheckout.jsp?state=enoughmoney";
				     RequestDispatcher notenoughView = req.getRequestDispatcher(url);
				     notenoughView.forward(req, res);
				     return;
				}
				
				List<Order_detailVO> list = new ArrayList<Order_detailVO>(); // 準備置入訂單明細
				
				for(int i = 0; i < buylist.size(); i++) {
					Order_detailVO order_detail = new Order_detailVO();   // 訂單明細
					ProductVO order = buylist.get(i);
					String p_id = order.getP_id();
					Integer od_pr = order.getP_pr();
					Integer od_qty = order.getQuantity();
					System.out.println("商品名稱=" + p_id + " ,金額=" + od_pr + " ,數量=" + od_qty);
					order_detail.setP_id(p_id);
					order_detail.setOd_qty(od_qty);
					order_detail.setOd_pr((int)Math.round(od_pr*od_qty));
					list.add(order_detail);
				}
				
				Order_masterVO order_masterVO = new Order_masterVO();
				order_masterVO.setMem_no(mem_no);
				order_masterVO.setOm_tpr(om_tpr);
				
				/***************************2.開始新增訂單***************************************/
				Order_masterService order_masterSvc = new Order_masterService();
				order_masterSvc.addOrder_masterWithOrder_detail(order_masterVO, list);
				
				/**連動會員資料**/
				Integer acc_balance=walletSVC.getTotalByMen_no(mem_no);				
				MemberService memberSvc=new MemberService();
				memberSvc.updateAccBalance(acc_balance, mem_no);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/								
				String url = "/front-end/shop/shopAllOrder.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				CartService cartSVC = new CartService();
				for(int i = 0; i < buylist.size(); i++) {
					ProductVO order = buylist.get(i);
					String p_id = order.getP_id();
					Integer quantity = order.getQuantity();
					cartSVC.deleteCart(mem_no, p_id, quantity);
				}
				session.removeAttribute("buylist");
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("增加訂單資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/shop/shopCheckout.jsp");
				failureView.forward(req, res);
			}
		 }		
	}
}

