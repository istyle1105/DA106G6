package com.spot.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

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

@MultipartConfig
public class SpotServlet extends HttpServlet {

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
				String spot_id = req.getParameter("spot_id");
				if (spot_id == null || (spot_id.trim()).length() == 0) {
					errorMsgs.add("請輸入景點編號");
				}else if(spot_id.charAt(0)!='S' || (spot_id.trim()).length() !=5){
					errorMsgs.add("景點編號格式不對");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/spot/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				
				/***************************2.開始查詢資料*****************************************/
				SpotService spotSvc = new SpotService();
				SpotVO spotVO = spotSvc.getOneSpot(spot_id);
				if (spotVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/spot/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("spotVO", spotVO); // 資料庫取出的empVO物件,存入req
				String url = "/front-end/spot/listOneSpot.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/spot/select_page.jsp");
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
				String spot_name = req.getParameter("spot_name").trim();
				if (spot_name == null || spot_name.trim().length() == 0) {
					errorMsgs.add("景點名稱請勿空白");
				}
				
				String spot_intro = req.getParameter("spot_intro").trim();
				if (spot_intro == null || spot_intro.trim().length() == 0) {
					errorMsgs.add("描述請勿空白");
				}
				
				String address = req.getParameter("address").trim();
				System.out.println("3");
				if (address == null || address.trim().length() == 0) {
					errorMsgs.add("地址請勿空白");//之後要改成自動抓取地圖的地址並改成不顯示文字盒來處理,就不用再做驗證了
				}
				String lat = req.getParameter("lat").trim();
				if (lat == null || lat.trim().length() == 0) {
					errorMsgs.add("緯度請勿空白");//之後要改成自動抓取地圖的緯度並改成不顯示文字盒來處理,就不用再做驗證了
				}
				
				String lng = req.getParameter("lng").trim();
				if (lng == null || lng.trim().length() == 0) {
					errorMsgs.add("經度請勿空白");//之後要改成自動抓取地圖的經度並改成不顯示文字盒來處理,就不用再做驗證了
				}
				
				
				Integer spot_type = new Integer(req.getParameter("spot_type").trim());
				
				//上傳圖片處理
				
				
				
				Part part = req.getPart("spot_pic");
				InputStream in = part.getInputStream();
				byte[] spot_pic = new byte[in.available()];
				in.read(spot_pic);
				
				SpotVO spotVO = new SpotVO();
				spotVO.setSpot_name(spot_name);
				spotVO.setSpot_intro(spot_intro);
				spotVO.setAddress(address);
				spotVO.setLat(lat);
				spotVO.setLng(lng);
				spotVO.setSpot_type(spot_type);
				spotVO.setSpot_pic(spot_pic);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("spotVO", spotVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/spot/addSpotInMap.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				SpotService spotSvc = new SpotService();
				spotVO = spotSvc.addSpot(spot_type,spot_name, spot_intro, spot_pic, address, lng, lat);
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				req.setAttribute("spotVO", spotVO);
				String url = "/front-end/spot/listOneSpot.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/spot/addSpotInMap.jsp");
				failureView.forward(req, res);
			}
		}
		
        if("getSpotApi".equals(action)) {
        	List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				
				/***************************2.開始新增資料***************************************/
				SpotService spotSvc = new SpotService();
				List<SpotVO> list = spotSvc.getAll();
				JSONArray result = new JSONArray(list);
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				req.setAttribute("result",result);
				String url = "/front-end/spot/showSpotInMap.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/spot/getSpotApi.jsp");
				failureView.forward(req, res);
			}
        }
	}

}
