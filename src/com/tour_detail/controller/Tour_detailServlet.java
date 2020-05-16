package com.tour_detail.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.json.JSONArray;
import org.json.JSONObject;

import com.spot.model.SpotService;
import com.spot.model.SpotVO;
import com.tour_detail.model.*;


@MultipartConfig
public class Tour_detailServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
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
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String tour_detail_id = req.getParameter("tour_detail_id");
				if (tour_detail_id == null || (tour_detail_id.trim()).length() == 0) {
					errorMsgs.add("請輸入行程細節編號");
				}else if(tour_detail_id.indexOf("RD",0)==-1  || (tour_detail_id.trim()).length() !=6){
					errorMsgs.add("行程細節編號格式不對");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/tour_detail/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}				
				
				
				/***************************2.開始查詢資料*****************************************/
				Tour_detailService tour_detailSvc = new Tour_detailService();
				Tour_detailVO tour_detailVO = tour_detailSvc.getOneTour_detail(tour_detail_id);
				if (tour_detailVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/tour_detail/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("tour_detailVO", tour_detailVO); // 資料庫取出的empVO物件,存入req
				String url = "/front-end/tour_detail/listOneTour_detail.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/tour_detail/select_page.jsp");
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
				/***************************1.接收請求參數****************************************/
				String tour_detail_id = req.getParameter("tour_detail_id");
				if (tour_detail_id == null || (tour_detail_id.trim()).length() == 0) {
					errorMsgs.add("請輸入行程細節編號");
				}else if(tour_detail_id.indexOf("RD")==-1  || (tour_detail_id.trim()).length() !=6){
					errorMsgs.add("行程細節編號格式不對");
				}
				
				/***************************2.開始查詢資料****************************************/
				Tour_detailService tour_detailSvc = new Tour_detailService();
				Tour_detailVO tour_detailVO = tour_detailSvc.getOneTour_detail(tour_detail_id);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("tour_detailVO", tour_detailVO);         // 資料庫取出的empVO物件,存入req
				String url = "/front-end/tour_detail/update_tour_detail_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher(requestURL);
//				.getRequestDispatcher("/front-end/tour_detail/listAllTour_detail.jsp");
				failureView.forward(req, res);
			}
		}
		

		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL");
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				
				String tour_detail_id = req.getParameter("tour_detail_id");
				if (tour_detail_id == null || (tour_detail_id.trim()).length() == 0) {
					errorMsgs.add("請輸入行程細節編號");
				}else if(tour_detail_id.indexOf("RD",0)==-1  || (tour_detail_id.trim()).length() !=6){
					errorMsgs.add("行程細節編號格式不對");
				}
				String tour_id = req.getParameter("tour_id");
				if (tour_id == null || (tour_id.trim()).length() == 0) {
					errorMsgs.add("請輸入行程編號");
				}else if(tour_id.charAt(0)!='R'  || (tour_id.trim()).length() !=5){
					errorMsgs.add("行程編號格式不對");
				}
				String spot_id = req.getParameter("spot_id");
				if (spot_id == null || (spot_id.trim()).length() == 0) {
					errorMsgs.add("請輸入景點編號");
				}else if(spot_id.charAt(0)!='S' || (spot_id.trim()).length() !=5){
					errorMsgs.add("景點編號格式不對");
				}
				
				String act_descrip = req.getParameter("act_descrip");
				if (act_descrip == null || act_descrip.trim().length() == 0) {
					errorMsgs.add("行程細節描述: 請勿空白");
				}
				
				java.sql.Timestamp start_time = null;
				try {
					start_time = java.sql.Timestamp.valueOf(req.getParameter("start_time").trim()+":00");
				} catch (IllegalArgumentException e) {
					start_time=new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				
				Integer stay_time = null;
				try {
					stay_time = new Integer(req.getParameter("stay_time").trim());
				} catch (NumberFormatException e) {
					stay_time = 0;
					errorMsgs.add("停留時間請填數字.");
				}
				
				//上傳圖片處理
				Part part = req.getPart("act_pic");
				InputStream in = part.getInputStream();
				byte[] act_pic = new byte[in.available()];
				in.read(act_pic);

				Tour_detailVO tour_detailVO = new Tour_detailVO();
				tour_detailVO.setTour_detail_id(tour_detail_id);
				tour_detailVO.setAct_descrip(act_descrip);
				tour_detailVO.setStart_time(start_time);
				tour_detailVO.setStay_time(stay_time);
				tour_detailVO.setAct_pic(act_pic);
				tour_detailVO.setSpot_id(spot_id);
				tour_detailVO.setTour_id(tour_id);


				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("tour_detailVO", tour_detailVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
//							.getRequestDispatcher(requestURL);
					.getRequestDispatcher("/front-end/tour_detail/update_tour_detail_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				Tour_detailService tour_detailSvc = new Tour_detailService();
				tour_detailVO = tour_detailSvc.updateTour_detail(tour_detail_id, stay_time, act_descrip,act_pic, start_time);
				tour_detailVO.setSpot_id(spot_id);//要把沒用到的參數補回去,不然後面會取不到值
				tour_detailVO.setTour_id(tour_id);
				List<Tour_detailVO> list = tour_detailSvc.getOneTourShowDetail(tour_id);
	
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("list", list); // 資料庫取出更新後的資料清單,讓前端頁面資料為最新的
				req.setAttribute("tour_id", tour_id); // 資料庫取出的empVO物件,存入req
				req.setAttribute("tour_detailVO", tour_detailVO); // 資料庫update成功後,正確的的empVO物件,存入req
//				String url = "/front-end/tour_detail/listOneTour_detail.jsp";
				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
//						.getRequestDispatcher(requestURL);
				.getRequestDispatcher("/front-end/tour_detail/update_tour_detail_input.jsp");
				failureView.forward(req, res);
			}
		}
		
        if ("insert".equals(action)) { // 來自addEmp.jsp的請求  
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			String requestURL = req.getParameter("requestURL"); // 送出修改的來源網頁路徑: 可能為addTour_detailWithMap.jsp
			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String tour_id = req.getParameter("tour_id").trim();//之後要修改成自動抓取會員編號不給使用者輸入
				if (tour_id == null || tour_id.trim().length() == 0) {
					errorMsgs.add("行程編號請勿空白");
				}
				
				String spot_id = req.getParameter("spot_id").trim();
				if (spot_id == null || spot_id.trim().length() == 0) {
					errorMsgs.add("景點編號請勿空白");
				}
				
				String act_descrip = req.getParameter("act_descrip").trim();
				if (act_descrip == null || act_descrip.trim().length() == 0) {
					errorMsgs.add("行程細節描述請勿空白");
				}
				
				java.sql.Timestamp start_time = null;
				try {
					start_time = java.sql.Timestamp.valueOf(req.getParameter("start_time").trim()+":00");
				} catch (IllegalArgumentException e) {
					start_time=new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				
				Integer stay_time = null;
				try {
					stay_time = new Integer(req.getParameter("stay_time").trim());
				} catch (NumberFormatException e) {
					stay_time = 0;
					errorMsgs.add("停留時間請填數字.");
				}
				
				//上傳圖片處理
				Part part = req.getPart("act_pic");
				InputStream in = part.getInputStream();
				byte[] act_pic = new byte[in.available()];
				in.read(act_pic);
				
				Tour_detailVO tour_detailVO = new Tour_detailVO();
				tour_detailVO.setAct_descrip(act_descrip);
				tour_detailVO.setStart_time(start_time);
				tour_detailVO.setStay_time(stay_time);
				tour_detailVO.setAct_pic(act_pic);
				tour_detailVO.setSpot_id(spot_id);
				tour_detailVO.setTour_id(tour_id);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					if(requestURL.equals("/front-end/tour_detail/addTour_detailWithMap.jsp")) {
						req.setAttribute("tour_detailVO", tour_detailVO); // 含有輸入格式錯誤的empVO物件,也存入req
						RequestDispatcher failureView = req
								.getRequestDispatcher("/front-end/tour_detail/addTour_detailWithMap.jsp");
						failureView.forward(req, res);
						return;
					}
					
					req.setAttribute("tour_detailVO", tour_detailVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/tour_detail/addTour_detail.jsp");
					failureView.forward(req, res);
					return;
				}

				/***************************2.開始新增資料***************************************/
				Tour_detailService tour_detailSvc = new Tour_detailService();
				tour_detailVO = tour_detailSvc.addTour_detail(tour_id,spot_id, stay_time, act_descrip,act_pic,start_time);

				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				if(requestURL.equals("/front-end/tour_detail/addTour_detailWithMap.jsp")) {
					res.setCharacterEncoding("big5");
					PrintWriter out =res.getWriter();
					out.print("新增成功");
					return;//來自地圖行程規劃的請求就不再轉跳,直接結束
				}
					
				String url = "/front-end/tour_detail/listAllTour_detail.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/tour_detail/addTour_detail.jsp");
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
				/***************************1.接收請求參數***************************************/
				String tour_detail_id = req.getParameter("tour_detail_id");
				String tour_id = (String)req.getSession().getAttribute("tour_id");
				
				/***************************2.開始刪除資料***************************************/
				Tour_detailService tour_detailSvc = new Tour_detailService();
				tour_detailSvc.deleteTour_detail(tour_detail_id);
				List<Tour_detailVO> list = tour_detailSvc.getOneTourShowDetail(tour_id);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				req.setAttribute("list", list); // 資料庫取出的empVO物件,存入req
				req.setAttribute("tour_id", tour_id); // 資料庫取出的empVO物件,存入req
				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher(requestURL);
//				.getRequestDispatcher("/front-end/tour_detail/listAllTour_detail.jsp");
				failureView.forward(req, res);
			}
		}
    
		
		if ("getOneTour_Show_Detail".equals(action)) { // 來自select_page.jsp的請求 及listTrips_ByCompositeQuery.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String tour_id = req.getParameter("tour_id");
				if (tour_id == null || (tour_id.trim()).length() == 0) {
					errorMsgs.add("請輸入行程編號");
				}else if(tour_id.charAt(0)!='R'  || (tour_id.trim()).length() !=5){
					errorMsgs.add("行程編號格式不對");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/trip/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}				
				
				
				/***************************2.開始查詢資料*****************************************/
				Tour_detailService tour_detailSvc = new Tour_detailService();
				List<Tour_detailVO> list = tour_detailSvc.getOneTourShowDetail(tour_id);
				if (list.size() == 0) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/trip/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("list", list); // 資料庫取出的empVO物件,存入req
				req.setAttribute("tour_id", tour_id); // 資料庫取出的empVO物件,存入req
				String url = "/front-end/tour_detail/listOneTourAllTour_detail.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/trip/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		if("planningTour_Detail".equals(action)) {
        	List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String tour_id = req.getParameter("tour_id");
				String tour_name = req.getParameter("tour_name");
				
				/***************************2.開始查出資料***************************************/
				SpotService spotSvc = new SpotService();
				List<SpotVO> list = spotSvc.getAll();
				JSONArray result = new JSONArray(list);
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				req.setAttribute("result",result);
				req.setAttribute("tour_id",tour_id);
				req.setAttribute("tour_name",tour_name);
				String url = "/front-end/tour_detail/planningMyTour.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/spot/getSpotApi.jsp");
				failureView.forward(req, res);
			}
        }
		
		
		if ("InsertOrUpdate".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

//			String requestURL = req.getParameter("requestURL"); // 送出修改的來源網頁路徑: 可能為addTour_detailWithMap.jsp
			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String tour_id = req.getParameter("tour_id").trim();//之後要修改成自動抓取會員編號不給使用者輸入
				if (tour_id == null || tour_id.trim().length() == 0) {
					errorMsgs.add("行程編號請勿空白");
				}
				
				String spot_idJSON = req.getParameter("spot_id").trim();
				if (spot_idJSON == null || spot_idJSON.trim().length() == 0) {
					errorMsgs.add("景點編號請勿空白");
				}
				JSONArray spot_idArr = new JSONArray(spot_idJSON);
				
				String stay_timeJSON = req.getParameter("stay_time").trim();
				if (stay_timeJSON == null || stay_timeJSON.trim().length() == 0) {
					errorMsgs.add("停留時間請勿空白");
				}
				JSONArray stay_timeArr = new JSONArray(stay_timeJSON);
				
				String start_timeJSON = req.getParameter("start_time").trim();
				if (start_timeJSON == null || start_timeJSON.trim().length() == 0) {
					errorMsgs.add("起始時間請勿空白");
				}
				JSONArray start_timeArr = new JSONArray(start_timeJSON);
				
				List<Tour_detailVO> tour_detailList = new ArrayList<>();
				
				for(int i =0;i<spot_idArr.length();i++) {//包裝成VO集合
					Tour_detailVO tour_detailVO = new Tour_detailVO();
					tour_detailVO.setSpot_id(spot_idArr.getString(i));
					tour_detailVO.setTour_id(tour_id);
					
					java.sql.Timestamp start_time = null;
					try {
						start_time = new java.sql.Timestamp(start_timeArr.getLong(i));
					} catch (IllegalArgumentException e) {
						start_time=new java.sql.Timestamp(System.currentTimeMillis());
						errorMsgs.add("請輸入日期!");
					}
					tour_detailVO.setStart_time(start_time);
					
					Integer stay_time = null;
					try {
						stay_time = new Integer(stay_timeArr.getInt(i));
					} catch (NumberFormatException e) {
						stay_time = 0;
						errorMsgs.add("停留時間請填數字.");
					}
					tour_detailVO.setStay_time(stay_time);
					
//					//上傳圖片處理
//					Part part = req.getPart("act_pic");
//					InputStream in = part.getInputStream();
//					byte[] act_pic = new byte[in.available()];
//					in.read(act_pic);
//					tour_detailVO.setAct_pic(act_pic);
					
					tour_detailList.add(tour_detailVO);
				}
				
//				String act_descrip = req.getParameter("act_descrip").trim();
//				if (act_descrip == null || act_descrip.trim().length() == 0) {
//					errorMsgs.add("行程細節描述請勿空白");
//				}
				
				
				
				
//				Tour_detailVO tour_detailVO = new Tour_detailVO();
//				tour_detailVO.setAct_descrip(act_descrip);
//				tour_detailVO.setStart_time(start_time);
//				tour_detailVO.setStay_time(stay_time);
//				tour_detailVO.setAct_pic(act_pic);
//				tour_detailVO.setSpot_id(spot_id);
//				tour_detailVO.setTour_id(tour_id);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					System.out.println(errorMsgs);
//					if(requestURL.equals("/front-end/tour_detail/addTour_detailWithMap.jsp")) {
//						req.setAttribute("tour_detailVO", tour_detailVO); // 含有輸入格式錯誤的empVO物件,也存入req
//						RequestDispatcher failureView = req
//								.getRequestDispatcher("/front-end/tour_detail/addTour_detailWithMap.jsp");
//						failureView.forward(req, res);
//						return;
//					}
//					
//					req.setAttribute("tour_detailVO", tour_detailVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/tour_detail/addTour_detail.jsp");
					failureView.forward(req, res);
					return;
				}

				/***************************2.先刪掉舊的資料再開始新增資料***************************************/
				Tour_detailService tour_detailSvc = new Tour_detailService();
				tour_detailSvc.addManyT_detailAndDropOld(tour_detailList, tour_id);
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
//				if(requestURL.equals("/front-end/tour_detail/addTour_detailWithMap.jsp")) {
//					res.setCharacterEncoding("big5");
//					PrintWriter out =res.getWriter();
//					out.print("新增成功");
//					return;//來自地圖行程規劃的請求就不再轉跳,直接結束
//				}
					
//				String url = "/front-end/tour_detail/listAllTour_detail.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
//				successView.forward(req, res);				

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/tour_detail/addTour_detail.jsp");
				failureView.forward(req, res);

			}
		}
		if("showTourDetailInMap".equals(action)) {
        	List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String tour_id = req.getParameter("tour_id");
				String tour_name = req.getParameter("tour_name");
				
				/***************************2.開始查出資料***************************************/
				Tour_detailService t_dSvc = new Tour_detailService();
				SpotService spotSvc = new SpotService();
				List<Tour_detailVO> list = t_dSvc.getOneTourShowDetail(tour_id);
				long startTime = list.get(0).getStart_time().getTime();//取得第一個開始時間的毫秒數
				List<Integer> staytimelist = new ArrayList<Integer>();
				List<SpotVO> spotlist = new ArrayList<SpotVO>();
				for(int i =0;i<list.size();i++) {
					Integer stay_time = list.get(i).getStay_time();
					staytimelist.add(stay_time);
					String spot_id = list.get(i).getSpot_id();
					SpotVO spotVO = spotSvc.getOneSpot(spot_id);
					spotlist.add(spotVO);
				}
				JSONArray myRoute = new JSONArray(spotlist);
				JSONArray stay = new JSONArray(staytimelist);
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				req.setAttribute("myRoute",myRoute);
				req.setAttribute("stay",stay);
				req.setAttribute("tour_name",tour_name);
				req.setAttribute("startTime",startTime);
				String url = "/front-end/tour_detail/showTourInMap.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/trip/select_page.jsp");
				failureView.forward(req, res);
			}
        }
	}

}
