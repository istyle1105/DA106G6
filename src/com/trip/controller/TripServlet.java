package com.trip.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.notification.model.NotificationService;
import com.tour.model.TourService;
import com.tour.model.TourVO;
import com.tour_detail.model.*;
import com.trip.model.*;
import com.trip_list.model.Trip_listService;
import com.trip_list.model.Trip_listVO;

import my_util.TimeDiff;


public class TripServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if("getOne_For_Display".equals(action)) {
			List<String>errorMsgs = new LinkedList<>();
			req.setAttribute("errorMsgs",errorMsgs);
			try {
				String trip_id = req.getParameter("trip_id");
				if(trip_id == null||(trip_id.trim().length()==0)) {
					errorMsgs.add("請輸入揪團編號");
				}
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/trip/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				TripService tripSvc = new TripService();
				TripVO tripVO = tripSvc.getOneTrip(trip_id);
				if(tripVO ==null) {
					errorMsgs.add("查無資料");
				}
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/trip/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				req.setAttribute("tripVO",tripVO);
				String url = "/front-end/trip/listOneTrip.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
			}catch(Exception e) {
				errorMsgs.add("無法取得資料"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/trip/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		if("insert".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String mem_no = req.getParameter("mem_no").trim();
				if (mem_no == null || mem_no.trim().length() == 0) {
					errorMsgs.add("會員編號請勿空白");
				}
				
				String tour_id = req.getParameter("tour_id").trim();
				if (tour_id == null || tour_id.trim().length() == 0) {
					errorMsgs.add("行程編號請勿空白");
				}
				
				Integer days = null;
				try {
					days = new Integer(req.getParameter("days").trim());
				} catch (NumberFormatException e) {
					days = 0;
					errorMsgs.add("天數請填數字.");
				}
				
				if (days != null&& days<1) {
					errorMsgs.add("天數必須大於一天");
				}
				
				Integer trip_price = null;
				try {
					trip_price = new Integer(req.getParameter("trip_price").trim());
				} catch (NumberFormatException e) {
					trip_price = 0;
					errorMsgs.add("預估金額請填數字.");
				}
				if (trip_price != null&& trip_price<0) {
					errorMsgs.add("預估金額必須大於零");
				}
				
				java.sql.Date first_date = null;
				try {
					first_date = java.sql.Date.valueOf(req.getParameter("first_date").trim());
					
				} catch (IllegalArgumentException e) {
//					first_date=new java.sql.Date(System.currentTimeMillis());//將當下日期丟給前端網頁
					errorMsgs.add("請輸入開始日期!");
				}
				
				java.sql.Date last_date = null;
				try {
					last_date = java.sql.Date.valueOf(req.getParameter("last_date").trim());
				} catch (IllegalArgumentException e) {
//					last_date=new java.sql.Date(System.currentTimeMillis());//將當下日期丟給前端網頁
					errorMsgs.add("請輸入結束日期!");
				}
				
				java.sql.Date reg_start = null;
				try {
					reg_start = java.sql.Date.valueOf(req.getParameter("reg_start").trim());
				} catch (IllegalArgumentException e) {
//					reg_start=new java.sql.Date(System.currentTimeMillis());//將當下日期丟給前端網頁
					errorMsgs.add("請輸入報名開始日期!");
				}
				
				java.sql.Date reg_deadline = null;
				try {
					reg_deadline = java.sql.Date.valueOf(req.getParameter("reg_deadline").trim());
				} catch (IllegalArgumentException e) {
//					reg_deadline=new java.sql.Date(System.currentTimeMillis());//將當下日期丟給前端網頁
					errorMsgs.add("請輸入報名截止日期!");
				}
				
				if (first_date != null && last_date != null && reg_start != null && reg_deadline != null ) {//排除任一空值可能
//					比較各時間先後順序today<reg_start<reg_deadline<first_date<last_date
					if(new Date(System.currentTimeMillis()-1000*60*60*24).after(reg_start)){//將系統產生的今天往前推一天,讓今天也可以被選擇
						errorMsgs.add("報名開始日期必須在今日(含)以後");
					}
					if(reg_start.after(reg_deadline)){
						errorMsgs.add("報名開始日期必須在報名結束日期以前");
					}
					if(reg_deadline.after(first_date)){
						errorMsgs.add("報名結束日期必須在出發日期以前");
					}
					if(first_date.after(last_date)){
						errorMsgs.add("出發日期必須在結束日期以前");
					}
				}
				
				Integer mem_amount = null;
				try {
					mem_amount = new Integer(req.getParameter("mem_amount").trim());
				} catch (NumberFormatException e) {
					mem_amount = 1;
					errorMsgs.add("成團人數請填數字.");
				}
				
				
				Integer mem_limited = null;
				try {
					mem_limited = new Integer(req.getParameter("mem_limited").trim());
					if (mem_limited < mem_amount ) {
						errorMsgs.add("人數上限不可小於成團人數");
					}
				} catch (NumberFormatException e) {
					mem_limited = 1;
					errorMsgs.add("人數上限請填數字.");
				}
				
				TripService tripSvc = new TripService();
				List<TripVO> list = tripSvc.getAllMyTrip(mem_no);//取出團主的揪團清單
				for(TripVO t:list) {//檢查時間是否相衝
					if(t.getTour_status()==1) continue;//把下架狀態的團去掉
					Date first = t.getFirst_date();//取得已參團開始時間
					Date last = t.getLast_date();//取得已參團結束時間
					if(!(last.before(first_date)||first.after(last_date))) {//ok狀態的布林值反轉
						errorMsgs.add("本團時間與其他已開的揪團時間相衝");
						break;
					}
				}
				
				TripVO tripVO = new TripVO();
				tripVO.setMem_no(mem_no);
				tripVO.setTour_id(tour_id);
				tripVO.setMem_amount(mem_amount);
				tripVO.setDays(days);
				tripVO.setFirst_date(first_date);
				tripVO.setLast_date(last_date);
				tripVO.setReg_start(reg_start);
				tripVO.setReg_deadline(reg_deadline);
				tripVO.setMem_limited(mem_limited);
				tripVO.setTrip_price(trip_price);
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("tripVO", tripVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/trip/addTripByMyTour.jsp");
//					.getRequestDispatcher("/front-end/trip/addTrip.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增揪團***************************************/
				tripVO = tripSvc.addTrip(mem_no, tour_id, mem_amount, days, first_date, last_date, trip_price, reg_start, reg_deadline, mem_limited);
				TourService tourSvc = new TourService();
				tourSvc.tourInTrip(tour_id);//修改為行程不可更動狀態
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
//				String url = "/front-end/trip/listAllTrip.jsp";
				String url = "/front-end/trip/listAllMyTrip.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);	
				
			}catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/trip/addTripByMyTour.jsp");
//				.getRequestDispatcher("/front-end/trip/addTrip.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if("listTrips_ByCompositeQuery".equals(action)) {
			List<String>errorMsgs = new LinkedList<>();
			req.setAttribute("errorMsgs",errorMsgs);
			try {

				/***************************1.將輸入資料轉為Map**********************************/ 
				//採用Map<String,String[]> getParameterMap()的方法 
				//注意:an immutable java.util.Map 
				//Map<String, String[]> map = req.getParameterMap();
				HttpSession session = req.getSession();
				Map<String, String[]> map = (Map<String, String[]>)session.getAttribute("map");
				if (req.getParameter("whichPage") == null||"/front-end/trip/select_page.jsp".equals(req.getServletPath())){//避免第一次從首頁進來時按下一頁會沒有東西可以秀
					HashMap<String, String[]> map1 = new HashMap<String, String[]>(req.getParameterMap());//洗去session禁存該MAP的限制
					session.setAttribute("map",map1);//若是原本的MAP直接放會放不進去,因為資安問題所以系統會自動擋下來不讓你存,後面就是會發生空值
					map = map1;
				} 
				String trip_id = (String)req.getParameter("trip_id");
				
				/***************************2.開始複合查詢***************************************/
				TripService tripSvc = new TripService();
				List<TripVO> list = tripSvc.getAll(map);
				if(list ==null) {
					errorMsgs.add("查無資料");
				}
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/trip/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				if(trip_id!=null) {
					req.setAttribute("trip_id",trip_id);//讓加入揪團按鈕可以使用
				}
				req.setAttribute("listTrips_ByCompositeQuery",list);
				String url = "/front-end/trip/listTrips_ByCompositeQuery.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
			}catch(Exception e) {
				errorMsgs.add("無法取得資料"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/trip/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		if("getOneTourToInsert".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL");//來源網址
			/***************************1.取得參數**********************************/ 

			try {
				String mem_no = req.getParameter("mem_no").trim();
				if (mem_no == null || mem_no.trim().length() == 0) {
					errorMsgs.add("會員編號請勿空白");
				}
				
				String tour_id = req.getParameter("tour_id").trim();
				if (tour_id == null || tour_id.trim().length() == 0) {
					errorMsgs.add("行程編號請勿空白");
				}
				

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher(requestURL);
//					.getRequestDispatcher("/front-end/trip/addTrip.jsp");
					failureView.forward(req, res);
					return;
				}
				/***************************2.開始整理開團用資料***************************************/
				Tour_detailService tour_detailSvc = new Tour_detailService();
				List<Tour_detailVO> list = (List<Tour_detailVO>)tour_detailSvc.getOneTourShowDetail(tour_id);//取得該編號下的所有細節
				Timestamp start = list.get(0).getStart_time();//開始時間
				Timestamp end = list.get(list.size()-1).getStart_time();//結束時間
				int days = TimeDiff.getTimeDiffToDays(start, end);//天數
				TripVO tripVO = new TripVO();
				tripVO.setDays(days);
				tripVO.setFirst_date(new java.sql.Date(start.getTime()));//型態轉換成Date
				tripVO.setLast_date(new java.sql.Date(end.getTime()));
				tripVO.setTour_id(tour_id);
				tripVO.setMem_no(mem_no);
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("tripVO", tripVO);
				String url = "/front-end/trip/addTripByMyTour.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);	
				
			}catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher(requestURL);
//				.getRequestDispatcher("/front-end/trip/addTrip.jsp");
				failureView.forward(req, res);
			}
		}
		
		if("getOne_Show_Member".equals(action)) {
			List<String>errorMsgs = new LinkedList<>();
			req.setAttribute("errorMsgs",errorMsgs);
			/***************************1.取得參數**********************************/ 
			try {
				String trip_id = req.getParameter("trip_id");
				if(trip_id == null||(trip_id.trim().length()==0)) {
					errorMsgs.add("請輸入揪團編號");
				}
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/trip/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				/***************************2.開始整理開團用資料***************************************/
				Trip_listService t_listSvc = new Trip_listService();
				List<Trip_listVO> list = t_listSvc.getAllMyMember(trip_id);
				
				
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/trip_list/listAllMyMember.jsp");
					failureView.forward(req, res);
					return;
				}
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("list",list);
				req.setAttribute("trip_id",trip_id);
				String url = "/front-end/trip_list/listAllMyMember.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
			}catch(Exception e) {
				errorMsgs.add("無法取得資料"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/trip/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		if("cancelTrip".equals(action)) {
			List<String>errorMsgs = new LinkedList<>();
			req.setAttribute("errorMsgs",errorMsgs);
			String requestURL = req.getParameter("requestURL");//來源網址
			/***************************1.取得參數**********************************/ 
			try {
				String trip_id = req.getParameter("trip_id");
				if(trip_id == null||(trip_id.trim().length()==0)) {
					errorMsgs.add("請輸入揪團編號");
				}
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher(requestURL);
					failureView.forward(req, res);
					return;
				}
				/***************************2.開始整理開團用資料***************************************/
				TripService tripSvc = new TripService();
				TripVO tripVO = tripSvc.getOneTrip(trip_id);
				tripVO.setTour_status(1);//改下架狀態
				tripVO.setTrip_status(2);//改取消揪團狀態
				tripSvc.updateTrip(tripVO);//進行狀態修改
				TourService tourSvc = new TourService();
				tourSvc.tourInTripCancel(tripVO.getTour_id());//行程改回可以編輯開團的狀態
				
				//寄會員通知
				Trip_listService tlSvc=new Trip_listService();
				List<Trip_listVO> tlList=new ArrayList<>();
				tlList=tlSvc.getAllMyMember(trip_id);
				
				NotificationService notificationSvc=new NotificationService();
				String note_content="行程名稱："+tourSvc.getOneTour(tripVO.getTour_id()).getTour_name()
						+"\n" +"，團主已取消開團";
				
				for(Trip_listVO  tlVO:tlList) {
					notificationSvc.addNote(tlVO.getMem_no(), note_content);
				}
				
				
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher(requestURL);
					failureView.forward(req, res);
					return;
				}
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
			}catch(Exception e) {
				errorMsgs.add("無法取得資料"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		}
	}

}
