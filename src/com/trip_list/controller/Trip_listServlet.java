package com.trip_list.controller;

import java.io.IOException;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.model.MemberService;
import com.notification.model.NotificationService;
import com.tour.model.TourService;
import com.trip.model.TripService;
import com.trip.model.TripVO;
import com.trip_list.model.*;



public class Trip_listServlet extends HttpServlet {
	
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
				}else if(trip_id.charAt(0)!='T'||trip_id.trim().length()!=5) {
					errorMsgs.add("揪團編號格式不對");
				}
				
				String mem_no = req.getParameter("mem_no");
				if(mem_no == null||(mem_no.trim().length()==0)) {
					errorMsgs.add("請輸入會員編號");
				}else if(mem_no.charAt(0)!='M'||mem_no.trim().length()!=5) {
					errorMsgs.add("會員編號格式不對");
				}
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/trip_list/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				Trip_listService trip_listSvc = new Trip_listService();
				Trip_listVO trip_listVO = trip_listSvc.getOneTrip_list(trip_id,mem_no);
				if(trip_listVO ==null) {
					errorMsgs.add("查無資料");
				}
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/trip_list/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				req.setAttribute("trip_listVO",trip_listVO);
				String url = "/front-end/trip_list/listOneTrip_list.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
			}catch(Exception e) {
				errorMsgs.add("無法取得資料"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/trip_list/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if("getOne_For_Insert".equals(action)) {
			List<String>errorMsgs = new LinkedList<>();
			req.setAttribute("errorMsgs",errorMsgs);
			try {
				String trip_id = req.getParameter("trip_id");
				if(trip_id == null||(trip_id.trim().length()==0)) {
					errorMsgs.add("請輸入揪團編號");
				}else if(trip_id.charAt(0)!='T'||trip_id.trim().length()!=5) {
					errorMsgs.add("揪團編號格式不對");
				}
				
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/trip/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				req.getSession().setAttribute("trip_id",trip_id);
				String url = "/front-end/trip_list/addTrip_list.jsp";
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
				/***************************1.接收請求參數****************************************/
				String trip_id = req.getParameter("trip_id");
				if(trip_id == null||(trip_id.trim().length()==0)) {
					errorMsgs.add("請輸入揪團編號");
				}else if(trip_id.charAt(0)!='T'||trip_id.trim().length()!=5) {
					errorMsgs.add("揪團編號格式不對");
				}
				
				String mem_no = req.getParameter("mem_no");
				if(mem_no == null||(mem_no.trim().length()==0)) {
					errorMsgs.add("請輸入會員編號");
				}else if(mem_no.charAt(0)!='M'||mem_no.trim().length()!=5) {
					errorMsgs.add("會員編號格式不對");
				}
				
				/***************************1.5.檢查揪團人數是否已滿****************************************/
				TripService tripSvc = new TripService();
				TripVO tripVO = tripSvc.getOneTrip(trip_id);
				if(tripVO.getMem_no().equals(mem_no)) {
					errorMsgs.add("團主別來亂");
				}
				
				Trip_listService trip_listSvc = new Trip_listService();
				
				if(trip_listSvc.getOneTrip_list(trip_id, mem_no)!=null) {
					errorMsgs.add("已經加入本團");
				};
				
				if(tripVO.getCurrent_mem()>=tripVO.getMem_limited()) {
					errorMsgs.add("該團人數已滿");
				}
				
				
				//判斷該會員已參加的揪團有無時間相衝
				
				Date first = tripVO.getFirst_date();//取得該團開始時間
				Date last = tripVO.getLast_date();//取得該團結束時間
				
				List<Trip_listVO> my_list = trip_listSvc.getAllMyTrip(mem_no);//取得該用戶參加的所有揪團
				if(my_list.size()!=0) {//沒參過團直接跳過
					for(Trip_listVO list :my_list ) {
						TripVO mytripVO = tripSvc.getOneTrip(list.getTrip_id());
						Date first_date = mytripVO.getFirst_date();//取得已參團開始時間
						Date last_date = mytripVO.getLast_date();//取得已參團結束時間
						if(first_date.after(first)&&first_date.before(last)) {
							errorMsgs.add("與已經報名的團時間相衝");
							break;
						}
						if(last_date.after(first)&&last_date.before(last)) {
							errorMsgs.add("與已經報名的團時間相衝");
							break;
						}
						if(first.after(first_date)&&first.before(last_date)) {
							errorMsgs.add("與已經報名的團時間相衝");
							break;
						}
						if(last.after(first_date)&&last.before(last_date)) {
							errorMsgs.add("與已經報名的團時間相衝");
							break;
						}
					}
				}
				
				Trip_listVO trip_listVO = new Trip_listVO();
				trip_listVO.setMem_no(mem_no);
				trip_listVO.setTrip_id(trip_id);
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("trip_listVO", trip_listVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/trip_list/addTrip_list.jsp");
					failureView.forward(req, res);
					return;
				}
				/***************************2.開始新增資料****************************************/
				
				trip_listVO = trip_listSvc.addTrip_list(trip_id,mem_no);
				
				synchronized (this) {
					
					/***************************2.5.並同時在trip表格的現在人數加一****************************************/
					Integer count = tripVO.getCurrent_mem();
					if(tripVO.getMem_amount() <= ++count) {//如果現在人數大於等於成團人數的時候,把揪團狀態改為1
						tripVO.setTrip_status(new Integer(1));
						//寄會員通知
						Trip_listService tlSvc=new Trip_listService();
						List<Trip_listVO> tlList=new ArrayList();
						tlList=tlSvc.getAllMyMember(trip_id);
						
						TourService tourSvc=new TourService();
						
						
						NotificationService notificationSvc=new NotificationService();
						String note_content="行程名稱："+tourSvc.getOneTour(tripVO.getTour_id()).getTour_name()
								+"\n" +"，已開團";
						
						for(Trip_listVO  tlVO:tlList) {
							notificationSvc.addNote(tlVO.getMem_no(), note_content);
						}
						
					}
					tripVO.setCurrent_mem(count);
					tripSvc.updateTrip(tripVO);//在人數部分加一
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				String url = "/front-end/trip_list/listAllMyTrip_list.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);	
				/***************************其他可能的錯誤處理**********************************/
			}catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/trip_list/addTrip_list.jsp");
				failureView.forward(req, res);
			}
		}
		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String trip_id = req.getParameter("trip_id");//自己改成字串
				String mem_no = req.getParameter("mem_no");//自己改成字串
				
				/***************************2.開始查詢資料****************************************/
				Trip_listService trip_listSvc = new Trip_listService();
				Trip_listVO trip_listVO = trip_listSvc.getOneTrip_list(trip_id,mem_no);
				
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("trip_listVO", trip_listVO);         // 資料庫取出的empVO物件,存入req
				String url = "/front-end/trip_list/update_trip_list_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/trip_list/listAllTrip_list.jsp");
				failureView.forward(req, res);
			}
		}
		

		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			String requestURL = req.getParameter("requestURL"); // 送出修改的來源網頁路徑:
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				
				String trip_id = req.getParameter("trip_id");//自己改成字串
				if (trip_id == null || trip_id.trim().length() == 0) {
					errorMsgs.add("揪團編號: 請勿空白");
				}
				
				String mem_no = req.getParameter("mem_no");//自己改成字串
				if (mem_no == null || mem_no.trim().length() == 0) {
					errorMsgs.add("會員編號請勿空白");
				}	
				
				Integer check_status = null;
				try {
					check_status =new Integer(req.getParameter("check_status").trim());
					if (check_status >1||check_status<0) {
						throw new NumberFormatException();
					}
				}catch(NumberFormatException e) {
					check_status =0;
					errorMsgs.add("狀態碼介於零與壹之間");
				}
				
				Integer rate = null;
				try {
					rate =new Integer(req.getParameter("rate").trim());		
					if (rate >5||rate<1) {
						throw new NumberFormatException();
					}
				}catch(NumberFormatException e) {
					rate =3;
					errorMsgs.add("評價請輸入1到5之間的數字");
				}
				
				String comment_content = req.getParameter("comment_content");
				if (comment_content == null || comment_content.trim().length() == 0) {
					errorMsgs.add("評價內容請勿空白");
				}
				
				java.sql.Timestamp reg_time = null;
				try {
					reg_time = java.sql.Timestamp.valueOf(req.getParameter("reg_time").trim());
				} catch (IllegalArgumentException e) {
					errorMsgs.add("請輸入報名日期!");
				}
				
				java.sql.Timestamp comment_time = null;
				try {
					comment_time = java.sql.Timestamp.valueOf(req.getParameter("comment_time").trim());
				} catch (IllegalArgumentException e) {
					errorMsgs.add("請輸入評價日期!");
				}
				

				Trip_listVO trip_listVO = new Trip_listVO();
				trip_listVO.setMem_no(mem_no);
				trip_listVO.setTrip_id(trip_id);
				trip_listVO.setCheck_status(check_status);
				trip_listVO.setComment_time(comment_time);
				trip_listVO.setRate(rate);
				trip_listVO.setReg_time(reg_time);
				trip_listVO.setComment_content(comment_content);


				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("trip_listVO", trip_listVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/trip_list/update_trip_list_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				Trip_listService trip_listSvc = new Trip_listService();
				trip_listVO = trip_listSvc.updateTrip_list(trip_id, mem_no, check_status, rate, comment_content, comment_time);
				trip_listVO.setReg_time(reg_time);//上面沒寫到的更新的部分回傳的VO沒寫到的部分就是空值,所以這裡把缺的資料補回去,讓他回傳前端頁面時可以有完整資料.
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("trip_listVO", trip_listVO); // 資料庫update成功後,正確的的empVO物件,存入req
//				String url = "/front-end/trip_list/listOneTrip_list.jsp";
				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/trip_list/update_trip_list_input.jsp");
				failureView.forward(req, res);
			}
		}
		if ("delete".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL"); // 送出修改的來源網頁路徑:
			
			try {
				/***************************1.接收請求參數***************************************/
				String trip_id = req.getParameter("trip_id");
				String mem_no = req.getParameter("mem_no");
				TripService tripSvc = new TripService();
				TripVO tripVO = tripSvc.getOneTrip(trip_id);
				Date Reg_deadline = tripVO.getReg_deadline();
				if (new Date().after(Reg_deadline)) {
					errorMsgs.add("揪團截止日以後不得直接退團");
				}
				if (tripVO.getMem_no().equals(mem_no)) {
					errorMsgs.add("團長不得直接退團,請取消揪團");
				}
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("trip_id", trip_id); // 含有輸入格式錯誤的empVO物件,也存入req
					req.setAttribute("mem_no", mem_no);
					RequestDispatcher failureView = req
							.getRequestDispatcher(requestURL);
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				
				/***************************2.開始刪除資料***************************************/
				Trip_listService trip_listSvc = new Trip_listService();
				trip_listSvc.deleteTrip_list(trip_id,mem_no);
				synchronized (this) {
					int Current_mem = tripVO.getCurrent_mem();//取出現有人數
					if(--Current_mem<tripVO.getMem_amount()){//現有人數先-1然後比較是否小於成團人數
						tripVO.setTrip_status(new Integer(0));//更改狀態為未成團的0
					}
					tripVO.setCurrent_mem(new Integer(Current_mem));//現有人數-1存回去
					tripSvc.updateTrip(tripVO);//更新到資料庫
				}

				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
//				String url = "/front-end/trip_list/listAllTrip_list.jsp";
				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/trip_list/listAllTrip_list.jsp");
				failureView.forward(req, res);
			}
		}
		
//		if ("getAllMyTrip".equals(action)) {
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//			try {
//				/***************************1.接收請求參數***************************************/
//				String mem_no = req.getParameter("mem_no");
//				
//				/***************************2.開始查詢資料***************************************/
//				Trip_listService trip_listSvc = new Trip_listService();
//				List<Trip_listVO> list = trip_listSvc.getAllMyTrip(mem_no);
//				
//				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
//				req.setAttribute("list", list);
//				String url = "/front-end/trip_list/listAllMyTrip_list.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);// 查詢成功後,轉交回送出請求的來源網頁
//				successView.forward(req, res);
//				
//				/***************************其他可能的錯誤處理**********************************/
//			} catch (Exception e) {
//				errorMsgs.add("查詢資料失敗:"+e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/front-end/trip_list/listAllTrip_list.jsp");
//				failureView.forward(req, res);
//			}
//		}
	}

}
