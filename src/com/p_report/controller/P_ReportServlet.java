package com.p_report.controller;

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

import com.demand.model.DemandService;
import com.demand.model.DemandVO;
import com.member.model.MemberService;
import com.p_report.model.P_ReportService;
import com.p_report.model.P_ReportVO;
import com.wallet.model.WalletService;

@MultipartConfig
public class P_ReportServlet extends HttpServlet {

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
				String p_re_no = req.getParameter("p_re_no");
				if (p_re_no == null || (p_re_no.trim()).length() == 0) {
					errorMsgs.add("請輸入檢舉編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/p_report/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				P_ReportService p_reportSvc = new P_ReportService();
				P_ReportVO p_reportVO = p_reportSvc.findByPK(p_re_no);
				if (p_reportVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/p_report/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("p_reportVO", p_reportVO); // 資料庫取出的demandVO物件,存入req
				String url = "/p_report/listOneP_Report.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneDemand.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/p_report/select_page.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String p_re_no = req.getParameter("p_re_no");

				/*************************** 2.開始查詢資料 ****************************************/
				P_ReportService p_reportSvc = new P_ReportService();
				P_ReportVO p_reportVO = p_reportSvc.findByPK(p_re_no);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("p_reportVO", p_reportVO); // 資料庫取出的demandVO物件,存入req
				String url = "/p_report/update_p_report_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_demand_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/p_report/listAllP_Report.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) { // 來自update_demand_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String p_re_no = req.getParameter("p_re_no");
				Integer p_re_status = new Integer(req.getParameter("p_re_status"));

				P_ReportVO p_reportVO = new P_ReportVO();
				p_reportVO.setP_re_no(p_re_no);
				p_reportVO.setP_re_status(p_re_status);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("p_reportVO", p_reportVO); // 含有輸入格式錯誤的demandVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/p_report/listAllP_Report.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				P_ReportService p_reportSvc = new P_ReportService();
				p_reportVO = p_reportSvc.update(p_re_no, p_re_status);

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("p_reportVO", p_reportVO); // 資料庫update成功後,正確的的demandVO物件,存入req
				String url = "/back-end/p_report/listAllP_Report.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneDemand.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/p_report/listAllP_Report.jsp");
				failureView.forward(req, res);
			}
		}

		if ("add".equals(action)) { // 來自addDemand.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL");
			
			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				String mem_no = req.getParameter("mem_no");
				String de_no = req.getParameter("de_no");
				
				String p_re_reason = req.getParameter("p_re_reason");
				if (p_re_reason == null || p_re_reason.trim().length() == 0) {
					errorMsgs.add("檢舉內容請勿留白");
				}
				
				P_ReportVO p_reportVO = new P_ReportVO();
				p_reportVO.setDe_no(de_no);
				p_reportVO.setMem_no(mem_no);
				p_reportVO.setP_re_reason(p_re_reason);
				
				DemandVO demandVO = new DemandVO();
				demandVO.setDe_no(de_no);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("p_reportVO", p_reportVO); // 含有輸入格式錯誤的demandVO物件,也存入req
					req.setAttribute("demandVO", demandVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/p_report/addP_Report.jsp?state=fail");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				P_ReportService p_reportSvc = new P_ReportService();
				p_reportVO = p_reportSvc.add(mem_no, de_no, p_re_reason);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/front-end/demand/listAllDemand.jsp?state=ReportSuccess";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllDemand.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		}

		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ***************************************/
				String p_re_no = req.getParameter("p_re_no");

				/*************************** 2.開始刪除資料 ***************************************/
				P_ReportService p_reportSvc = new P_ReportService();
				p_reportSvc.delete(p_re_no);

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = "/back-end/p_report/listAllP_Report.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/p_report/listAllP_Report.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("listOneInFrontOnly".equals(action)) {

			try {
				// Retrieve form parameters.
				String de_no = req.getParameter("de_no");

				DemandService demadnSvc = new DemandService();
				DemandVO demandVO = demadnSvc.findByPK(de_no);

				req.setAttribute("demandVO", demandVO); // 資料庫取出的empVO物件,存入req

				// 取出的demandVO送給listOneEmp.jsp
				RequestDispatcher successView = req.getRequestDispatcher("/back-end/p_report/listOne.jsp");
				successView.forward(req, res);
				return;

				// Handle any unusual exceptions
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}
		
		if ("deleteInBack".equals(action)) { // 來自listAllEmp.jsp

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
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/p_report/listAllP_Report.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始刪除資料 ***************************************/
				DemandService demandSvc = new DemandService();
				demandSvc.delete(de_no);
				
				demandSvc.returnWallet(de_mem_no, price, de_no+"已成功取消需求, 退回代購預扣");
				
				WalletService walletSVC = new WalletService();
				Integer acc_balance = walletSVC.getTotalByMen_no(de_mem_no);				
				MemberService memberSvc = new MemberService();
				memberSvc.updateAccBalance(acc_balance, de_mem_no);

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = "/back-end/p_report/listAllP_Report.jsp?state=deleteSuccess";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/p_report/listAllP_Report.jsp");
				failureView.forward(req, res);
			}
		}
	}

}
