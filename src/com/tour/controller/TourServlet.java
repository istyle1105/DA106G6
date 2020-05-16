package com.tour.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.json.JSONArray;

import com.spot.model.SpotService;
import com.spot.model.SpotVO;
import com.tour.model.TourService;
import com.tour.model.TourVO;
import com.tour_detail.model.Tour_detailService;
import com.tour_detail.model.Tour_detailVO;

@MultipartConfig
public class TourServlet extends HttpServlet {

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
				String tour_id = req.getParameter("tour_id");
				if (tour_id == null || (tour_id.trim()).length() == 0) {
					errorMsgs.add("請輸入行程編號");
				}else if(tour_id.charAt(0)!='R'  || (tour_id.trim()).length() !=5){
					errorMsgs.add("行程編號格式不對");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/tour/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
//				try {
////					empno = new Integer(str);//這裡要修改成自己要的格式邏輯
//				} catch (Exception e) {
//					errorMsgs.add("員工編號格式不正確");
//				}
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/emp/select_page.jsp");
//					failureView.forward(req, res);
//					return;//程式中斷
//				}
				
				/***************************2.開始查詢資料*****************************************/
				TourService tourSvc = new TourService();
				TourVO tourVO = tourSvc.getOneTour(tour_id);
				if (tourVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/tour/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("tourVO", tourVO); // 資料庫取出的empVO物件,存入req
				String url = "/front-end/tour/listOneTour.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/tour/select_page.jsp");
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
				String tour_id = req.getParameter("tour_id");
				if (tour_id == null || (tour_id.trim()).length() == 0) {
					errorMsgs.add("請輸入行程編號");
				}else if(tour_id.charAt(0)!='R'  || (tour_id.trim()).length() !=5){
					errorMsgs.add("行程編號格式不對");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher(requestURL);
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料****************************************/
				TourService tourSvc = new TourService();
				TourVO tourVO = tourSvc.getOneTour(tour_id);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("tourVO", tourVO);         // 資料庫取出的empVO物件,存入req
				String url = "/front-end/tour/update_tour_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher(requestURL);
//				.getRequestDispatcher("/front-end/tour/listAllTour.jsp");
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
				
				String tour_id = req.getParameter("tour_id");
				if (tour_id == null || tour_id.trim().length() == 0) {
					errorMsgs.add("行程編號: 請勿空白");
				}
				String mem_no = req.getParameter("mem_no");
				if (mem_no == null || mem_no.trim().length() == 0) {
					errorMsgs.add("會員編號: 請勿空白");
				}
				String tour_name = req.getParameter("tour_name");
				if (tour_name == null || tour_name.trim().length() == 0) {
					errorMsgs.add("行程名稱: 請勿空白");
				}
				
				String tour_detail = req.getParameter("tour_detail").trim();
				if (tour_detail == null || tour_detail.trim().length() == 0) {
					errorMsgs.add("行程描述請勿空白");
				}	
				
				
				Integer display = null;
				try {
					display = new Integer(req.getParameter("display").trim());
					if (display!=0 && display!=1) {
						errorMsgs.add("上下架狀態只能為0或者1");
					}
						
				} catch (NumberFormatException e) {
					display = 0;
					errorMsgs.add("上下架狀態為數字0或者1.");
				}
				
				
				//上傳圖片處理
				Part part = req.getPart("tour_pic");
				InputStream in = part.getInputStream();
				byte[] tour_pic = new byte[in.available()];
				in.read(tour_pic);

				TourVO tourVO = new TourVO();
				tourVO.setTour_id(tour_id);
				tourVO.setMem_no(mem_no);
				tourVO.setTour_name(tour_name);
				tourVO.setTour_detail(tour_detail);
				tourVO.setTour_pic(tour_pic);
				tourVO.setDisplay(display);


				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("tourVO", tourVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/tour/update_tour_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				TourService tourSvc = new TourService();
				tourVO = tourSvc.updateTour(tour_id, tour_name, tour_detail, tour_pic,display);
				List<TourVO> list = tourSvc.getAllMyTour(mem_no);
				tourVO.setMem_no(mem_no);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("tourVO", tourVO); // 資料庫update成功後,正確的的empVO物件,存入req
				req.setAttribute("list", list); // 資料庫update成功後,正確的的empVO物件,存入req
				req.setAttribute("mem_no", mem_no); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = requestURL;
//				String url = "/front-end/tour/listOneTour.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/tour/update_tour_input.jsp");
				failureView.forward(req, res);
			}
		}
		
        if ("insert".equals(action)) { // 來自addEmp.jsp的請求  
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
		
			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String mem_no = req.getParameter("mem_no").trim();//之後要修改成自動抓取會員編號不給使用者輸入
				if (mem_no == null || mem_no.trim().length() == 0) {
					errorMsgs.add("會員編號請勿空白");
				}
				
				String tour_name = req.getParameter("tour_name").trim();
				if (tour_name == null || tour_name.trim().length() == 0) {
					errorMsgs.add("行程名稱請勿空白");
				}
				
				String tour_detail = req.getParameter("tour_detail").trim();
				if (tour_detail == null || tour_detail.trim().length() == 0) {
					errorMsgs.add("行程描述請勿空白");
				}
				
				//上傳圖片處理
				
				Part part = req.getPart("tour_pic");
				InputStream in = part.getInputStream();
				byte[] tour_pic = new byte[in.available()];
				in.read(tour_pic);
				
				TourVO tourVO = new TourVO();
				tourVO.setMem_no(mem_no);
				tourVO.setTour_name(tour_name);
				tourVO.setTour_detail(tour_detail);
				tourVO.setTour_pic(tour_pic);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("tourVO", tourVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/tour/addTour.jsp");
					failureView.forward(req, res);
					return;
				}

				/***************************2.開始新增資料***************************************/
				TourService tourSvc = new TourService();
				tourVO = tourSvc.addTour(mem_no,tour_name, tour_detail, tour_pic);
				List<TourVO> list = (List<TourVO>)tourSvc.getAllMyTour(mem_no);
				/***************************2.5.將景點資料打包出來用於規劃路線***********/
//				SpotService spotSvc = new SpotService();
//				List<SpotVO> list = spotSvc.getAll();
//				JSONArray result = new JSONArray(list);
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				req.setAttribute("tourVO",tourVO);
				req.setAttribute("list",list);//回歸我的揪團所要有的表單
//				req.setAttribute("result",result);
				String url = "/front-end/tour/listAllMyTour.jsp";
//				String url = "/front-end/tour_detail/planningMyTour.jsp";//轉到地圖規劃用頁面
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/tour/addTour.jsp");
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
				String tour_id = req.getParameter("tour_id");
				
				/***************************2.開始刪除資料***************************************/
				TourService tourSvc = new TourService();
				tourSvc.deleteTour(tour_id);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/front-end/tour/listAllTour.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/tour/listAllTour.jsp");
				failureView.forward(req, res);
			}
		}
    
		if ("getOneTour_Show_Detail".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL");

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
					if("/front-end/tour/listAllMyTour.jsp".equals(requestURL)){
						RequestDispatcher failureView = req
								.getRequestDispatcher("/front-end/tour/listAllMyTour.jsp");
						failureView.forward(req, res);
						return;//程式中斷
					}
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
					if("/front-end/tour/listAllMyTour.jsp".equals(requestURL)){
						RequestDispatcher failureView = req
								.getRequestDispatcher("/front-end/tour/listAllMyTour.jsp");
						failureView.forward(req, res);
						return;//程式中斷
					}
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/trip/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("list", list); // 資料庫取出的empVO物件,存入req
				req.setAttribute("tour_id", tour_id); // 資料庫取出的empVO物件,存入req
				String url = "/front-end/tour_detail/listOneTourAllTour_detailGUEST.jsp";
				if("/front-end/tour/listAllMyTour.jsp".equals(requestURL)) {//只有從我的行程頁面進來的請求會導到有修改權的頁面
					url = "/front-end/tour_detail/listOneTourAllTour_detail.jsp";
				}
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				if("/front-end/tour/listAllMyTour.jsp".equals(requestURL)){
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/tour/listAllMyTour.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/trip/select_page.jsp");
				failureView.forward(req, res);
				return;//程式中斷
			}
		}
		if ("getOnePerson_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL");

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String mem_no = req.getParameter("mem_no");
				if (mem_no == null || (mem_no.trim()).length() == 0) {
					errorMsgs.add("請輸入行程編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher(requestURL);
//					.getRequestDispatcher("/front-end/tour_detail/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}				
				
				
				/***************************2.開始查詢資料*****************************************/
				TourService tourSvc = new TourService();
				List<TourVO> list = (List<TourVO>)tourSvc.getAllMyTour(mem_no);
				if (list.size() == 0) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/tour/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("list", list); // 資料庫取出的empVO物件,存入req
				String url = "/front-end/tour/listAllMyTour.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher(requestURL);
//				.getRequestDispatcher("/front-end/tour/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		if ("tourDisable".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL");
	
			try {
				/***************************1.接收請求參數***************************************/
				String tour_id = req.getParameter("tour_id");
				System.out.println("1");
				/***************************2.開始刪除資料***************************************/
				TourService tourSvc = new TourService();
				TourVO tourVO = tourSvc.getOneTour(tour_id);
				tourSvc.disableTour(tourVO);
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/
				System.out.println("3");
				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		}
	}

}
