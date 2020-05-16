package com.demand.controller;

import java.io.*;
import java.sql.Timestamp;
import java.util.Base64;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.demand.model.DemandService;
import com.demand.model.DemandVO;
import com.member.model.MemberService;
import com.member.model.MemberVO;
import com.p_message.model.P_MessageService;
import com.p_message.model.P_MessageVO;
import com.wallet.model.WalletService;

@MultipartConfig
public class DemandServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getOne_For_Display".equals(action)) { // 來自listAllDemand.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("de_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入需求編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/demand/listAllDemand.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				DemandService demandSvc = new DemandService();
				DemandVO demandVO = demandSvc.findByPK(str);
				if (demandVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {					
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/demand/listAllDemand.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("demandVO", demandVO); // 資料庫取出的demandVO物件,存入req
				String url = "/back-end/demand/listOneDemand.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneDemand.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/demand/listAllDemand.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			String requestURL = req.getParameter("requestURL");

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String de_no = req.getParameter("de_no");

				/*************************** 2.開始查詢資料 ****************************************/
				DemandService demandSvc = new DemandService();
				DemandVO demandVO = demandSvc.findByPK(de_no);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("demandVO", demandVO); // 資料庫取出的demandVO物件,存入req
				String url = "/back-end/demand/update_demand_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_demand_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		}
		
		if ("getOne_For_Update_Front".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			String requestURL = req.getParameter("requestURL");

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String de_no = req.getParameter("de_no");

				/*************************** 2.開始查詢資料 ****************************************/
				DemandService demandSvc = new DemandService();
				DemandVO demandVO = demandSvc.findByPK(de_no);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("demandVO", demandVO); // 資料庫取出的demandVO物件,存入req
				String url = "/front-end/demand/update_demand_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_demand_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		}
		
		if ("addReport".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			String requestURL = req.getParameter("requestURL");

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String de_no = req.getParameter("de_no");

				/*************************** 2.開始查詢資料 ****************************************/
				DemandService demandSvc = new DemandService();
				DemandVO demandVO = demandSvc.findByPK(de_no);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("demandVO", demandVO); // 資料庫取出的demandVO物件,存入req
				String url = "/front-end/p_report/addP_Report.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_demand_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) { // 來自update_demand_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			String requestURL = req.getParameter("requestURL");

			BufferedInputStream inputStream = null; // input stream of the upload file

			// obtains the upload file part in this multipart request
			Part filePart = req.getPart("de_photo");
			if (filePart != null) {
				// obtains input stream of the upload file
				inputStream = new BufferedInputStream(filePart.getInputStream());
			}

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String de_no = req.getParameter("de_no");
				String de_photo1 = Base64.getEncoder().encodeToString(req.getParameter("de_photo1").getBytes());
				String de_mem_no = req.getParameter("de_mem_no");
				Timestamp de_time = java.sql.Timestamp.valueOf(req.getParameter("de_time"));
				Integer status = new Integer(req.getParameter("status"));

				byte[] targetArray = new byte[inputStream.available()];
				inputStream.read(targetArray);

				String upload = Base64.getEncoder().encodeToString(targetArray);

				String de_item_name = req.getParameter("de_item_name");
				if (de_item_name == null || de_item_name.trim().length() == 0)
					errorMsgs.add("需求商品請勿空白");

				Integer price = null;
				try {
					price = new Integer(req.getParameter("price").trim());
				} catch (NumberFormatException e) {
					price = 0;
					errorMsgs.add("價格請填數字.");
				}

				String area = req.getParameter("area").trim();
				if (area == null || area.trim().length() == 0) {
					errorMsgs.add("地區請勿空白");
				}

				DemandVO demandVO = new DemandVO();
				demandVO.setDe_no(de_no);
				demandVO.setDe_item_name(de_item_name);

				if (!upload.isEmpty()) {
					demandVO.setDe_photo(upload);
				} else {
					demandVO.setDe_photo(new String(Base64.getDecoder().decode(de_photo1.getBytes())));
//					errorMsgs.add("請上傳圖片");
				}
				demandVO.setPrice(price);
				demandVO.setArea(area);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("demandVO", demandVO); // 含有輸入格式錯誤的demandVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/demand/update_demand_input.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				DemandService demandSvc = new DemandService();
				demandVO = demandSvc.update(demandVO.getDe_no(), demandVO.getDe_item_name(), demandVO.getDe_photo(),
						demandVO.getPrice(), demandVO.getArea());

				demandVO.setDe_mem_no(de_mem_no);
				demandVO.setDe_time(de_time);
				demandVO.setStatus(status);

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("demandVO", demandVO); // 資料庫update成功後,正確的的demandVO物件,存入req
				String url = "/back-end/demand/listAllDemand.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(requestURL); // 修改成功後,轉交listOneDemand.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/demand/update_demand_input.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("updateInFront".equals(action)) { // 來自update_demand_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			String requestURL = req.getParameter("requestURL");

			BufferedInputStream inputStream = null; // input stream of the upload file

			// obtains the upload file part in this multipart request
			Part filePart = req.getPart("de_photo");
			if (filePart != null) {
				// obtains input stream of the upload file
				inputStream = new BufferedInputStream(filePart.getInputStream());
			}

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String de_no = req.getParameter("de_no");
				String de_photo1 = Base64.getEncoder().encodeToString(req.getParameter("de_photo1").getBytes());
				String de_mem_no = req.getParameter("de_mem_no");
				Timestamp de_time = java.sql.Timestamp.valueOf(req.getParameter("de_time"));
				Integer status = new Integer(req.getParameter("status"));

				byte[] targetArray = new byte[inputStream.available()];
				inputStream.read(targetArray);

				String upload = Base64.getEncoder().encodeToString(targetArray);

				String de_item_name = req.getParameter("de_item_name");
				if (de_item_name == null || de_item_name.trim().length() == 0)
					errorMsgs.add("需求商品請勿空白");

				Integer price = null;
				try {
					price = new Integer(req.getParameter("price").trim());
				} catch (NumberFormatException e) {
					price = 0;
					errorMsgs.add("價格請填數字.");
				}

				String area = req.getParameter("area").trim();
				if (area == null || area.trim().length() == 0) {
					errorMsgs.add("地區請勿空白");
				}

				DemandVO demandVO = new DemandVO();
				demandVO.setDe_no(de_no);
				demandVO.setDe_item_name(de_item_name);

				if (!upload.isEmpty()) {
					demandVO.setDe_photo(upload);
				} else {
					demandVO.setDe_photo(new String(Base64.getDecoder().decode(de_photo1.getBytes())));
//					errorMsgs.add("請上傳圖片");
				}
				demandVO.setPrice(price);
				demandVO.setArea(area);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("demandVO", demandVO); // 含有輸入格式錯誤的demandVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/demand/update_demand_input.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				DemandService demandSvc = new DemandService();
				demandVO = demandSvc.update(demandVO.getDe_no(), demandVO.getDe_item_name(), demandVO.getDe_photo(),
						demandVO.getPrice(), demandVO.getArea());

				demandVO.setDe_mem_no(de_mem_no);
				demandVO.setDe_time(de_time);
				demandVO.setStatus(status);

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				res.sendRedirect(req.getContextPath()+"/front-end/demand/demand.do?de_no="+de_no+"&action=listOneInFrontOnly&state=updateSuccess");

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/demand/update_demand_input.jsp");
				failureView.forward(req, res);
			}
		}

		if ("add".equals(action)) { // 來自addDemand.jsp的請求
			System.out.println("action="+action);

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL");

			BufferedInputStream inputStream = null; // input stream of the upload file

			// obtains the upload file part in this multipart request
			Part filePart = req.getPart("de_photo");
			if (filePart != null) {
				// obtains input stream of the upload file
				inputStream = new BufferedInputStream(filePart.getInputStream());
			}

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				String de_mem_no = req.getParameter("mem_no");
				String de_item_name = req.getParameter("de_item_name");
				String de_photo1 = Base64.getEncoder().encodeToString(req.getParameter("de_photo1").getBytes());

				byte[] targetArray = new byte[inputStream.available()];
				inputStream.read(targetArray);

				String upload = Base64.getEncoder().encodeToString(targetArray);

				if (de_item_name == null || de_item_name.trim().length() == 0) {
					errorMsgs.add("商品名稱請勿空白");
				}

				Integer price = null;
				try {
					price = new Integer(req.getParameter("price").trim());
				} catch (NumberFormatException e) {
					price = 0;
					errorMsgs.add("價格請填整數");
				}

				String area = req.getParameter("area");
				if (area == null || area.trim().length() == 0)
					errorMsgs.add("地區請勿空白");

				DemandVO demandVO = new DemandVO();
				demandVO.setDe_item_name(de_item_name);
				if (!upload.isEmpty()) {
					demandVO.setDe_photo(upload);
				} else {
					demandVO.setDe_photo(de_photo1);
					errorMsgs.add("請上傳圖片");
				}
				demandVO.setPrice(price);
				demandVO.setArea(area);
				
				WalletService walletSVC = new WalletService();
				if(price > walletSVC.getTotalByMen_no(de_mem_no)) {
					String url = "/front-end/member/chargePage.jsp";	
					RequestDispatcher notenoughView = req.getRequestDispatcher(url);
					notenoughView.forward(req, res);
					System.out.println("餘額不足，跳轉頁面");
					return;
				}

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("demandVO", demandVO); // 含有輸入格式錯誤的demandVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/demand/addDemand.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				DemandService demandSvc = new DemandService();
				demandVO = demandSvc.add(demandVO.getDe_item_name(), demandVO.getDe_photo(), demandVO.getPrice(),
						de_mem_no, demandVO.getArea());
				
				WalletService wallestSvc= new WalletService();
				wallestSvc.addWallet(de_mem_no, price, "代購預扣", 1);
							
				Integer acc_balance=walletSVC.getTotalByMen_no(de_mem_no);				
				MemberService memberSvc=new MemberService();
				memberSvc.updateAccBalance(acc_balance, de_mem_no);
				
				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/front-end/demand/listAllDemand.jsp?state=addSuccess";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllDemand.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/demand/addDemand.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("addScore".equals(action)) { // 來自addDemand.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL");

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				String pur_mem_no = req.getParameter("pur_mem_no");
				String de_no = req.getParameter("de_no");
				Integer pur_score = new Integer(req.getParameter("pur_score"));
				String pur_comment = req.getParameter("pur_comment");

				DemandVO demandVO = new DemandVO();
				demandVO.setDe_no(de_no);
				demandVO.setPur_score(pur_score);
				demandVO.setPur_comment(pur_comment);
				demandVO.setPur_mem_no(pur_mem_no);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					res.sendRedirect(req.getContextPath()+"/front-end/demand/demand.do?de_no="+de_no+"&action=listOneInFrontOnly");
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				DemandService demandSvc = new DemandService();
				demandVO = demandSvc.addScore(de_no, pur_score, pur_comment);
				
				MemberService memberSvc = new MemberService();
				MemberVO memberVO = memberSvc.getOneMember(pur_mem_no);
				demandSvc.addScoreInMem(pur_mem_no,1 + memberVO.getPur_score_amount() , pur_score+memberVO.getPur_total_score());

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/front-end/demand/listMyDemand.jsp?state=scoreSuccess";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/demand/listMyDemand.jsp?state=scoreFail");
				failureView.forward(req, res);
			}
		}
		
		if ("addScore2".equals(action)) { // 來自addDemand.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL");

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				String de_no = req.getParameter("de_no");
				Integer de_score = new Integer(req.getParameter("de_score"));
				String de_comment = req.getParameter("de_comment");
				String de_mem_no = req.getParameter("de_mem_no");

				DemandVO demandVO = new DemandVO();
				demandVO.setDe_no(de_no);
				demandVO.setDe_score(de_score);
				demandVO.setDe_comment(de_comment);
				demandVO.setDe_mem_no(de_mem_no);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					res.sendRedirect(req.getContextPath()+"/front-end/demand/demand.do?de_no="+de_no+"&action=listOneInFrontOnly2");
				}

				/*************************** 2.開始新增資料 ***************************************/
				DemandService demandSvc = new DemandService();
				demandVO = demandSvc.addScore2(de_no, de_score, de_comment);
				
				MemberService memberSvc = new MemberService();
				MemberVO memberVO = memberSvc.getOneMember(de_mem_no);
				demandSvc.addScore2InMem(de_mem_no, 1 + memberVO.getDe_score_amount() , de_score+memberVO.getDe_total_score());

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				res.sendRedirect(req.getContextPath()+"/front-end/demand/listMyOffer.jsp?state=scoreSuccess");

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/demand/listMyOffer.jsp");
				failureView.forward(req, res);
			}
		}

		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL");

			try {
				/*************************** 1.接收請求參數 ***************************************/
				String de_no = req.getParameter("de_no");

				/*************************** 2.開始刪除資料 ***************************************/
				DemandService demandSvc = new DemandService();
				demandSvc.delete(de_no);

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = "/back-end/demand/listAllDemand.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(requestURL);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/demand/listAllDemand.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("updateStatus".equals(action)) { // 來自update_demand_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			String requestURL = req.getParameter("requestURL");

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String de_no = req.getParameter("de_no");
				String statusString = req.getParameter("status");
				if(statusString.equals(null) || statusString.trim().length() == 0){
					errorMsgs.add("請選擇正確的狀態");
				};
				
				Integer status = new Integer(statusString);

				DemandVO demandVO = new DemandVO();
				demandVO.setDe_no(de_no);
				demandVO.setStatus(status);
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					res.sendRedirect(req.getContextPath()+"/front-end/demand/demand.do?de_no="+de_no+"&action=listOneInFrontOnly2");
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				DemandService demandSvc = new DemandService();
				demandVO = demandSvc.updateStatus(de_no, status);

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				res.sendRedirect(req.getContextPath()+"/front-end/demand/demand.do?de_no="+de_no+"&action=listOneInFrontOnly2&state=updateSuccess");

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/demand/listMyOffer.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("addMSG".equals(action)) { // 來自update_demand_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			String requestURL = req.getParameter("requestURL");

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String de_no = req.getParameter("de_no");
				String mem_no = req.getParameter("mem_no");
				String p_msg = req.getParameter("p_msg");
				if(p_msg.equals(null) || p_msg.trim().length() == 0){
					errorMsgs.add("請輸入留言內容");
				};

				P_MessageVO p_messageVO = new P_MessageVO();
				p_messageVO.setDe_no(de_no);
				p_messageVO.setMem_no(mem_no);
				p_messageVO.setP_msg(p_msg);
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("p_messageVO", p_messageVO); // 含有輸入格式錯誤的demandVO物件,也存入req
					res.sendRedirect(req.getContextPath()+"/front-end/demand/demand.do?de_no="+de_no+"&action=listOneInFront");
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				P_MessageService p_messageSvc = new P_MessageService();
				p_messageVO = p_messageSvc.add(de_no, mem_no, p_msg);

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("p_messageVO", p_messageVO); // 資料庫update成功後,正確的的demandVO物件,存入req
//				String url = "/back-end/demand/listAllDemand.jsp";
				res.sendRedirect(req.getContextPath()+"/front-end/demand/demand.do?de_no="+de_no+"&action=listOneInFront"); 

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/demand/listAllDemand.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("addMSG_update".equals(action)) { // 來自update_demand_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			String requestURL = req.getParameter("requestURL");

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String de_no = req.getParameter("de_no");
				String mem_no = req.getParameter("mem_no");
				String p_msg = req.getParameter("p_msg");
				if(p_msg.equals(null) || p_msg.trim().length() == 0){
					errorMsgs.add("請輸入留言內容");
				};

				P_MessageVO p_messageVO = new P_MessageVO();
				p_messageVO.setDe_no(de_no);
				p_messageVO.setMem_no(mem_no);
				p_messageVO.setP_msg(p_msg);
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					res.sendRedirect(req.getContextPath()+"/front-end/demand/demand.do?de_no="+de_no+"&action=listOneInFrontOnly&state=msgFail");
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				P_MessageService p_messageSvc = new P_MessageService();
				p_messageVO = p_messageSvc.add(de_no, mem_no, p_msg);

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				res.sendRedirect(req.getContextPath()+"/front-end/demand/demand.do?de_no="+de_no+"&action=listOneInFrontOnly&state=msgSuccess");
				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/demand/listMyDemand.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("addMSG_listOnly".equals(action)) { // 來自update_demand_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			String requestURL = req.getParameter("requestURL");

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String de_no = req.getParameter("de_no");
				String mem_no = req.getParameter("mem_no");
				String p_msg = req.getParameter("p_msg");
				if(p_msg.equals(null) || p_msg.trim().length() == 0){
					errorMsgs.add("請輸入留言內容");
				};

				P_MessageVO p_messageVO = new P_MessageVO();
				p_messageVO.setDe_no(de_no);
				p_messageVO.setMem_no(mem_no);
				p_messageVO.setP_msg(p_msg);
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					res.sendRedirect(req.getContextPath()+"/front-end/demand/demand.do?de_no="+de_no+"&action=listOneInFrontOnly2&state=msgFail");
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				P_MessageService p_messageSvc = new P_MessageService();
				p_messageVO = p_messageSvc.add(de_no, mem_no, p_msg);

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				res.sendRedirect(req.getContextPath()+"/front-end/demand/demand.do?de_no="+de_no+"&action=listOneInFrontOnly2&state=msgSuccess");

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/demand/listMyOffer.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("confirmed".equals(action)) { // 來自update_demand_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			String requestURL = req.getParameter("requestURL");

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String de_no = req.getParameter("de_no");
				String statusString = req.getParameter("status");
				if(!statusString.equals("5")){
					errorMsgs.add("商品已送達才可確認收貨~");
				} else {
					statusString = "6";
				};
				Integer status = new Integer(statusString);
				
				String pur_mem_no = req.getParameter("pur_mem_no");
				Integer price = new Integer(req.getParameter("price"));				
						
				DemandVO demandVO = new DemandVO();
				demandVO.setDe_no(de_no);
				demandVO.setStatus(status);
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					res.sendRedirect(req.getContextPath()+"/front-end/demand/demand.do?de_no="+de_no+"&action=listOneInFrontOnly&state=confirmFail");
					return;
				}

				/*************************** 2.開始修改資料 *****************************************/
				DemandService demandSvc = new DemandService();
				demandVO = demandSvc.updateStatus(de_no, status);
				System.out.println(pur_mem_no);
				System.out.println(de_no);
				System.out.println(status);
//				demandSvc.returnWallet(pur_mem_no, price, de_no+"交易完成, 預付款已支付給代購者");
				
				WalletService walletSVC=new WalletService();
				walletSVC.addWallet(pur_mem_no, price, de_no+"交易完成, 預付款已支付給代購者", 0);
;				Integer acc_balance=walletSVC.getTotalByMen_no(pur_mem_no);				
				MemberService memberSvc=new MemberService();
				memberSvc.updateAccBalance(acc_balance, pur_mem_no);

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("demandVO", demandVO); // 資料庫update成功後,正確的的demandVO物件,存入req
//				String url = "/back-end/demand/listAllDemand.jsp";
				res.sendRedirect(req.getContextPath()+"/front-end/demand/demand.do?de_no="+de_no+"&action=listOneInFrontOnly&state=confirmSuccess");

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/demand/listMyDemand.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("deleteInFront".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL");

			try {
				/*************************** 1.接收請求參數 ***************************************/
				String de_no = req.getParameter("de_no");
				String statusString = req.getParameter("status");
				System.out.println("statusString:"+statusString);
				if(!statusString.equals("0")){
					errorMsgs.add("已下標之後無法下架!");
				};
				String de_mem_no = req.getParameter("de_mem_no");
				Integer price = new Integer(req.getParameter("price"));				

				WalletService walletSVC = new WalletService();
				if(price > walletSVC.getTotalByMen_no(de_mem_no)) {
					String url = "/front-end/member/chargePage.jsp";	
					RequestDispatcher notenoughView = req.getRequestDispatcher(url);
					notenoughView.forward(req, res);
					return;
				}
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					res.sendRedirect(req.getContextPath()+"/front-end/demand/demand.do?de_no="+de_no+"&action=listOneInFrontOnly&state=deleteFail");
					return; // 程式中斷
				}

				/*************************** 2.開始刪除資料 ***************************************/
				DemandService demandSvc = new DemandService();
				demandSvc.delete(de_no);
				
				demandSvc.returnWallet(de_mem_no, price, de_no+"已成功取消需求, 退回代購預扣");
				
				Integer acc_balance = walletSVC.getTotalByMen_no(de_mem_no);				
				MemberService memberSvc = new MemberService();
				memberSvc.updateAccBalance(acc_balance, de_mem_no);

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = "/front-end/demand/listMyDemand.jsp?state=deleteSuccess";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/demand/listMyDemand.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		
		if ("listOneInHome".equals(action)) {

			try {
				// Retrieve form parameters.
				String de_no = req.getParameter("de_no");

				DemandService demadnSvc = new DemandService();
				DemandVO demandVO = demadnSvc.findByPK(de_no);

				req.setAttribute("demandVO", demandVO); // 資料庫取出的empVO物件,存入req

				// Bootstrap_modal
				boolean openModal = true;
				req.setAttribute("openModal", openModal);

				// 取出的demandVO送給listOneEmp.jsp
				RequestDispatcher successView = req.getRequestDispatcher("/back-end/demand/listAllDemand.jsp");
				successView.forward(req, res);
				return;

				// Handle any unusual exceptions
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}
		
		if ("listOneInFront".equals(action)) {

			try {
				// Retrieve form parameters.
				String de_no = req.getParameter("de_no");

				DemandService demadnSvc = new DemandService();
				DemandVO demandVO = demadnSvc.findByPK(de_no);

				req.setAttribute("demandVO", demandVO); // 資料庫取出的empVO物件,存入req

				// 取出的demandVO送給listOneEmp.jsp
				RequestDispatcher successView = req.getRequestDispatcher("/front-end/demand/listOneDemand.jsp");
				successView.forward(req, res);
				return;

				// Handle any unusual exceptions
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}
		
		if ("listOneInFrontOnly".equals(action)) {

			try {
				// Retrieve form parameters.
				String de_no = req.getParameter("de_no");

				DemandService demadnSvc = new DemandService();
				DemandVO demandVO = demadnSvc.findByAvailablePK(de_no);

				req.setAttribute("demandVO", demandVO); // 資料庫取出的empVO物件,存入req

				// 取出的demandVO送給listOneEmp.jsp
				RequestDispatcher successView = req.getRequestDispatcher("/front-end/demand/listOneForUpdate.jsp");
				successView.forward(req, res);
				return;

				// Handle any unusual exceptions
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}
		
		if ("listOneInFrontOnly2".equals(action)) {

			try {
				// Retrieve form parameters.
				String de_no = req.getParameter("de_no");

				DemandService demadnSvc = new DemandService();
				DemandVO demandVO = demadnSvc.findByPK(de_no);

				req.setAttribute("demandVO", demandVO); // 資料庫取出的empVO物件,存入req


				// 取出的demandVO送給listOneEmp.jsp
				RequestDispatcher successView = req.getRequestDispatcher("/front-end/demand/listOneOnly.jsp");
				successView.forward(req, res);
				return;

				// Handle any unusual exceptions
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}

		if ("listOne".equals(action)) {

			try {
				// Retrieve form parameters.
				String de_no = req.getParameter("de_no");

				DemandService demadnSvc = new DemandService();
				DemandVO demandVO = demadnSvc.findByPK(de_no);

				req.setAttribute("demandVO", demandVO); // 資料庫取出的empVO物件,存入req

				// Bootstrap_modal
				boolean openModal = true;
				req.setAttribute("openModal", openModal);

				// 取出的demandVO送給listOneEmp.jsp
				RequestDispatcher successView = req.getRequestDispatcher("/back-end/demand/listAllDemand.jsp");
				successView.forward(req, res);
				return;

				// Handle any unusual exceptions
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}
		
		if ("updatePurMem".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
		try {
			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			String de_no = req.getParameter("de_no");
			String pur_mem_no = req.getParameter("pur_mem_no");
			String de_mem_no = req.getParameter("de_mem_no");
			
			DemandVO demandVO = new DemandVO();
			demandVO.setDe_no(de_no);
			
			
			if (!pur_mem_no.equals(de_mem_no)) {
				demandVO.setPur_mem_no(pur_mem_no);
			} else {
				de_no = "";
				pur_mem_no = "";
				errorMsgs.add("不可接受自己的訂單~");
			}
			
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				res.sendRedirect(req.getContextPath()+"/front-end/demand/listAllDemand.jsp?state=fail");
				return; // 程式中斷
			}
			
			/*************************** 2.開始修改資料 *****************************************/
			DemandService demandSvc = new DemandService();
			demandVO = demandSvc.update_purmem(de_no, pur_mem_no);


			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
			res.sendRedirect(req.getContextPath()+"/front-end/demand/listAllDemand.jsp?state=success");

			/*************************** 其他可能的錯誤處理 *************************************/
		} catch (Exception e) {
			errorMsgs.add("修改資料失敗:" + e.getMessage());
			RequestDispatcher failureView = req.getRequestDispatcher("/front-end/demand/listAllDemand.jsp");
			failureView.forward(req, res);
		}
		
		}

	}

}
