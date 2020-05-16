package com.wallet.controller;

import java.io.*;
import java.sql.Timestamp;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.wallet.model.WalletService;
import com.wallet.model.WalletVO;
import com.wallet.model.*;
import com.member.model.*;

public class WalletServlet extends HttpServlet{
	
	public void doGet(HttpServletRequest req,HttpServletResponse res) 
			throws ServletException, IOException{
		doPost(req, res);
	}
 
	public void doPost(HttpServletRequest req,HttpServletResponse res) 
			throws ServletException, IOException{
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if ("chargewallet".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				HttpSession session = req.getSession();
				String mem_no = (String) session.getAttribute("mem_no");

				Integer w_amount = null;
				try {
					w_amount = new Integer(req.getParameter("w_amount").trim());
				} catch (NumberFormatException e) {
					w_amount = 0;
					errorMsgs.add("金額請填數字.");
				}
				
				String w_txn_note = null;
				
				Integer w_txn_status = 0;

				WalletVO walletVO = new WalletVO();
				walletVO.setMem_no(mem_no);
				walletVO.setW_amount(w_amount);
				walletVO.setW_txn_note(w_txn_note);
				walletVO.setW_txn_status(w_txn_status);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("walletVO", walletVO); // 含有輸入格式錯誤的walletVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/member/transRecord.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				WalletService walletSvc = new WalletService();
				walletVO = walletSvc.addWallet(mem_no, w_amount, w_txn_note, w_txn_status);
				
//				改過的地方
				Integer acc_balance=walletSvc.getTotalByMen_no(mem_no);	
				System.out.println("acc_balance"+acc_balance);
				MemberService memberSvc=new MemberService();
				memberSvc.updateAccBalance(acc_balance, mem_no);
				
				
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/front-end/member/transRecord.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllWallet.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/member/transRecord.jsp");
				failureView.forward(req, res);
			}
		}
	}
}

