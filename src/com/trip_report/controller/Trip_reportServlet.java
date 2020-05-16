package com.trip_report.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.trip.model.TripService;
import com.trip.model.TripVO;
import com.trip_report.model.Trip_reportService;
import com.trip_report.model.Trip_reportVO;


public class Trip_reportServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
//		if("getOne_For_Display".equals(action)) {
//			List<String>errorMsgs = new LinkedList<>();
//			req.setAttribute("errorMsgs",errorMsgs);
//			try {
//				String trip_id = req.getParameter("trip_id");
//				if(trip_id == null||(trip_id.trim().length()==0)) {
//					errorMsgs.add("請輸入揪團編號");
//				}else if(trip_id.charAt(0)!='T'||trip_id.trim().length()!=5) {
//					errorMsgs.add("揪團編號格式不對");
//				}
//				
//				String mem_no = req.getParameter("mem_no");
//				if(mem_no == null||(mem_no.trim().length()==0)) {
//					errorMsgs.add("請輸入會員編號");
//				}else if(mem_no.charAt(0)!='M'||mem_no.trim().length()!=5) {
//					errorMsgs.add("會員編號格式不對");
//				}
//				if(!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/front-end/trip_list/select_page.jsp");
//					failureView.forward(req, res);
//					return;
//				}
//				Trip_reportServlet trip_listSvc = new Trip_reportServlet();
//				Trip_listVO trip_listVO = trip_listSvc.getOneTrip_list(trip_id,mem_no);
//				if(trip_listVO ==null) {
//					errorMsgs.add("查無資料");
//				}
//				if(!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/front-end/trip_list/select_page.jsp");
//					failureView.forward(req, res);
//					return;
//				}
//				req.setAttribute("trip_listVO",trip_listVO);
//				String url = "/front-end/trip_list/listOneTrip_list.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);
//				successView.forward(req, res);
//				
//			}catch(Exception e) {
//				errorMsgs.add("無法取得資料"+e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/front-end/trip_list/select_page.jsp");
//				failureView.forward(req, res);
//			}
//		}
		
		if("insert".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL");
			
			try {
				String trip_id = req.getParameter("trip_id");
				if(trip_id == null||(trip_id.trim().length()==0)) {
					errorMsgs.add("請輸入揪團編號");
				}else if(trip_id.charAt(0)!='T'||trip_id.trim().length()!=5) {
					errorMsgs.add("揪團編號格式不對");
				}
				
				String mem_no = req.getParameter("mem_no");
				if(mem_no == null||(mem_no.trim().length()==0)) {
					errorMsgs.add("請登入會員才能檢舉");
				}else if(mem_no.charAt(0)!='M'||mem_no.trim().length()!=5) {
					errorMsgs.add("會員編號格式不對");
				}
				String t_rep_content = req.getParameter("t_rep_content");
				if(t_rep_content == null||(t_rep_content.trim().length()==0)) {
					errorMsgs.add("請輸入檢舉內容");
				}
				
				
				Trip_reportVO trip_reportVO = new Trip_reportVO();
				trip_reportVO.setMem_no(mem_no);
				trip_reportVO.setT_rep_content(t_rep_content);
				trip_reportVO.setTrip_id(trip_id);
				
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("trip_reportVO", trip_reportVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/trip_report/addTrip_report.jsp");
					failureView.forward(req, res);
					return;
				}
				/***************************2.開始修改資料*****************************************/
				Trip_reportService trip_reportSvc = new Trip_reportService();
				trip_reportVO = trip_reportSvc.addTrip_report(trip_id, mem_no, t_rep_content);
				
//				/***************************3.修改完成,準備轉交(Send the Success view)*************/				
//				String url = "/front-end/trip/select_page.jsp";
				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);	
				/***************************3.修改完成,準備轉交(Send the Success view)*************/				
//				TripService tripSvc = new TripService();
//				
//				if(requestURL.equals("/front-end/trip/listTrips_ByCompositeQuery.jsp")){
//					HttpSession session = req.getSession();
//					Map<String, String[]> map = (Map<String, String[]>)session.getAttribute("map");
//					List<TripVO> list  = tripSvc.getAll(map);
//					req.setAttribute("listTrips_ByCompositeQuery",list); //  複合查詢, 資料庫取出的list物件,存入request
//				}
//                
//				String url = requestURL;
//				RequestDispatcher successView = req.getRequestDispatcher(url);   // 修改成功後,轉交回送出修改的來源網頁
//				successView.forward(req, res);
			}catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/trip_report/addTrip_report.jsp");
				failureView.forward(req, res);
			}
		}
	}

}
