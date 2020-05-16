package com.member.controller;

import java.io.*;
import java.sql.Timestamp;
import java.util.*;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.json.JSONException;
import org.json.JSONObject;

import com.employee.model.*;
import com.member.model.*;
import com.notification.model.*;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)

public class MemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
	
	public static String genAuthCode() {
		long startTime=System.nanoTime();
		boolean tmp=true;
		StringBuilder strArr=null;
		String str="";

		while(tmp) {
			boolean flag1=false;
			boolean flag2=false;
			boolean flag3=false;
			int Arr[]=new int[8];
			for(int i=0;i<8;i++) {
				int r=(int)(Math.random()*62+1);
				//介於0-9
				if((1<=r) && (r<=10)) {
					Arr[i]=r+47;
					flag1=true;
				} 				
				//介於A-Z
				if((11<=r) && (r<=36)) {
					Arr[i]=r+54;
					flag2=true;
				} 				
				//介於a-z
				if((37<=r) && (r<=62)) {
					Arr[i]=r+60;
					flag3=true;
				}		
			}
			if(flag1 && flag2 && flag3) {
				for(int i=0;i<8;i++) {
//					System.out.print((char)Arr[i]);
					str+=String.valueOf(((char)Arr[i]));
					
				}							
				tmp=false;
			}			
		}
		System.out.println("str"+str);
		long endTime=System.nanoTime(); //獲取結束時間  
		System.out.println("程式執行時間： "+(endTime-startTime)/Math.pow(10, 9)+ "s"); 
		return str;
	}
	
	protected int allowUser(String mem_id,String psw) {
    	
    	MemberVO memberVO=null;
    	MemberService memberSvc=new MemberService();
    	
    	if(memberSvc.getfindOnePK(mem_id)==null) {
    		System.out.println("沒有此帳號");
    		return 1;
    	}else {
    		memberVO=memberSvc.getfindOnePK(mem_id);
    		System.out.println("2");
    	}  
    	
        if (memberVO.getMem_id().equals(mem_id) && memberVO.getPsw().equals(psw)) {
        	System.out.println("成功登入");
        	return 3;
          
        }else {
        	System.out.println("密碼錯誤");
        	return 4; 
        }
    }
	protected int checkUser(String mem_id,String captcha) {
    	
    	MemberVO memberVO=null;
    	MemberService memberSvc=new MemberService();
    	
    	if(memberSvc.getfindOnePK(mem_id)==null) {
    		System.out.println("沒有此帳號");
    		return 1;
    	}else {
    		memberVO=memberSvc.getfindOnePK(mem_id);
    		System.out.println("2");
    	}  
    	
        if (memberVO.getMem_id().equals(mem_id) && memberVO.getCaptcha().equals(captcha)) {
        	System.out.println("成功登入");
        	return 3;
        }else {
        	System.out.println("驗證碼錯誤");
        	return 4; 
        }
    }	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action=req.getParameter("action");
		String action2=req.getParameter("action2");
		System.out.println(action);
		
		if("checkIfIdExist".equals(action)) {
			
			JSONObject output = new JSONObject();
			String inputId=req.getParameter("inputId");
			MemberService memberSvc=new MemberService();
			MemberVO memberVO=null;
			memberVO=memberSvc.getfindOnePK(inputId);
			
			try {
		        output.put("available", ((memberVO==null)?"Y":"N"));
		    } catch (JSONException e) {
		        // TODO Auto-generated catch block
		        e.printStackTrace();
		    }
		    res.setContentType("text/plain");
		    PrintWriter out = res.getWriter();
		    System.out.println(output);
		    out.write(output.toString());
		    out.flush();
		    out.close();
		}
		
		if("logout".equals(action)) {
			HttpSession session=req.getSession();
			session.removeAttribute("mem_no");
			res.sendRedirect(req.getContextPath()+"/front-end/homepage.jsp?state=logout");
			
		}
		
		
		if("checkMem".equals(action)) {
			List<String> errorMsgs=new LinkedList<String>();
			req.setAttribute("errorMsgs",errorMsgs);			
			
			String mem_id=req.getParameter("mem_id");
			if(mem_id==null || mem_id.trim().length()==0) {
				errorMsgs.add("會員帳號：請勿空白");
			}
			String captcha=req.getParameter("captcha");
			System.out.println(captcha);
			if(captcha==null || captcha.trim().length()==0) {
				errorMsgs.add("會員驗證碼：請勿空白");
			}
			
			if(!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/member/MemberCaptcha.jsp?error=space");
				failureView.forward(req, res);
				return;
			}
			
			
			if(checkUser(mem_id,captcha)==1) {
				//登入不成功
				System.out.println("沒有此帳號");
				String URL=req.getContextPath()+"/front-end/member/MemberCaptcha.jsp?error=noId";
				res.sendRedirect(URL);
				return;
			}else if(checkUser(mem_id,captcha)==4) {
				//登入不成功
				System.out.println("驗證碼錯誤");
				String URL=req.getContextPath()+"/front-end/member/MemberCaptcha.jsp?error=wrongNum";
				res.sendRedirect(URL);
				return;	
			}else {
				MemberService memberSvc=new MemberService();
				MemberVO memberVO=null;
				memberVO=memberSvc.getfindOnePK(mem_id);
				
				memberSvc.update_memVerify(memberVO.getMem_no(),1);
//				HttpSession session=req.getSession();
//				session.setAttribute("mem_no", memberVO.getMem_no());
				res.sendRedirect(req.getContextPath()+"/front-end/member/MemberCaptcha.jsp?error=success"); 
			}
			
		}
		
		
		if("login".equals(action)) {
			String mem_id=req.getParameter("mem_id").trim();
			String psw=req.getParameter("psw").trim();
			
			String loginLocation =req.getParameter("loginLocation");
			System.out.println("loginLocation="+loginLocation);
//			HttpSession session2=req.getSession();
//			session2.setAttribute("loginLocation", loginLocation);
			
			if(allowUser(mem_id,psw)==1) {
				//登入不成功
				System.out.println("沒有此帳號");
				String URL=req.getContextPath()+"/front-end/member/Login.jsp?error=false&requestURI="+loginLocation;
				res.sendRedirect(URL);
				return;
			}else if(allowUser(mem_id,psw)==4) {
				//登入不成功
				System.out.println("密碼錯誤");
				String URL=req.getContextPath()+"/front-end/member/Login.jsp?error=true&requestURI="+loginLocation;
				res.sendRedirect(URL);
				return;	
			}else {
				MemberService memberSvc=new MemberService();
				MemberVO memberVO=null;
				memberVO=memberSvc.getfindOnePK(mem_id);
				if(memberVO.getMem_verify()==0) {
					res.sendRedirect(req.getContextPath()+"/front-end/member/Login.jsp?state=noVerify&requestURI="+loginLocation); 
					return;
				}
				else if(memberVO.getMem_verify()==2) {
					res.sendRedirect(req.getContextPath()+"/front-end/member/Login.jsp?state=noAuth&requestURI="+loginLocation); 
					return;
				} 
				HttpSession session=req.getSession();
				session.setAttribute("mem_no", memberVO.getMem_no());
				System.out.println("mem_no存session");
				System.out.println(loginLocation);
				if(loginLocation!="") {
					System.out.println(loginLocation);
					String shoppingCart=req.getContextPath()+"/front-end/shop/shoppingCart.jsp";
					if(loginLocation.equals(shoppingCart)) {
						System.out.println("shoppingCart="+shoppingCart);
						res.sendRedirect(req.getContextPath()+"/shop/ShoppingRedis.do?action=toMemCart");
						return;
					}
					res.sendRedirect(req.getContextPath()+"/front-end/homepage.jsp");
					return;
				}
				try {
					String location=(String)session.getAttribute("location");
					if (location != null) {
						System.out.println("location="+location);
						session.removeAttribute("location");  
				        res.sendRedirect(location);            
				        return;
				    }
					
				}catch(Exception ignored) {
					System.out.println(ignored.getMessage());
					
				}
//				res.sendRedirect(req.getContextPath()+"/front-end/homepage.jsp"); 
//				System.out.println("loginLocation"+loginLocation);
//				if(loginLocation=="") {
//					res.sendRedirect(req.getContextPath()+"/front-end/homepage.jsp");
//					return;
//				}
				res.sendRedirect(req.getContextPath()+"/front-end/homepage.jsp"); 
//				session2.removeAttribute("loginLocation");
			}
			
			
			
		}
		if("backGetOne".equals(action)) {
			
			try {
				String mem_no=req.getParameter("mem_no");
				MemberService memberSvc=new MemberService();
				MemberVO memberVO=new MemberVO();
				memberVO=memberSvc.getOneMember(mem_no);
				
			
				
				req.setAttribute("memberVO",memberVO );
				//Bootstrap_modal
				boolean openModal=true;
				
				req.setAttribute("openModal",openModal );
				
				RequestDispatcher successView = req
						.getRequestDispatcher("/back-end/member/listAllMember.jsp");
				successView.forward(req, res);
				System.out.println(memberVO.getMem_no());
				System.out.println(openModal);
				return;
				}catch (Exception e) {
					throw new ServletException(e);
				}
		}
		
//		if("back_One_Display".equals(action)) {
//			List<String> errorMsgs=new LinkedList<String>();
//			req.setAttribute("errorMsgs",errorMsgs);
//			
//			try {
//				
//				String mem_no=req.getParameter("mem_no");
//				String mem_noReg="^M[0-9]{4}$";
//				if(mem_no == null || mem_no.trim().length()==0) {
//					errorMsgs.add("請輸入會員編號請勿空白");
//				}else if(!mem_no.trim().matches(mem_noReg)){
//					errorMsgs.add("會員編號必須是M開頭，且後面加4位數字");	
//				}
//				if(!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/back-end/member/select_page.jsp");
//					failureView.forward(req, res);
//					return;
//				}
//				
//				//查資料
//				MemberService memberSvc=new MemberService();
//				MemberVO memberVO=memberSvc.getOneMember(mem_no);
//				if(memberVO == null) {
//					errorMsgs.add("查無資料");
//				}
//				if(!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/member/select_page.jsp");
//					failureView.forward(req, res);
//					return;
//				}
//				//查詢完成
//				req.setAttribute("memberVO", memberVO);
//				String url ="/back-end/member/listOneMember.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); 
//				successView.forward(req, res);
//				
//			
//				
//			}catch(Exception e) {
//				errorMsgs.add("無法取得資料:"+e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/back-end/member/select_page.jsp");
//				failureView.forward(req, res);			
//			}		
//		}
		
		if("front_One_Display".equals(action)) {
			List<String> errorMsgs=new LinkedList<String>();
			req.setAttribute("errorMsgs",errorMsgs);
			
			try {
				
				String mem_no=req.getParameter("mem_no");
				String mem_noReg="^M[0-9]{4}$";
				if(mem_no == null || mem_no.trim().length()==0) {
					errorMsgs.add("請輸入會員編號請勿空白");
				}else if(!mem_no.trim().matches(mem_noReg)){
					errorMsgs.add("會員編號必須是M開頭，且後面加4位數字");	
				}
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/member/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				//查資料
				MemberService memberSvc=new MemberService();
				MemberVO memberVO=memberSvc.getOneMember(mem_no);
				if(memberVO == null) {
					errorMsgs.add("查無資料");
				}
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/member/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				//查詢完成
				req.setAttribute("memberVO", memberVO);
				String url ="/front-end/member/listOneMember.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);
				
			
				
			}catch(Exception e) {
				errorMsgs.add("無法取得資料:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/member/select_page.jsp");
				failureView.forward(req, res);			
			}		
		}
		
		//來自查詢全部listAllMember.jsp的請求
		if("back_One_Update".equals(action)) {
			List<String> errorMsgs=new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL"); 
			req.setAttribute("requestURL", requestURL);
			System.out.println("requestURL"+requestURL);
			
			String whichPage = req.getParameter("whichPage");
			req.setAttribute("whichPage", whichPage);   
			System.out.println("whichPage"+whichPage);
			
			try {
				//來自listAllMember.jsp的請求
				String mem_no=req.getParameter("mem_no");
				//查詢資料
				MemberService memberSvc=new MemberService();
				MemberVO memberVO2=memberSvc.getOneMember(mem_no);
				//轉交
				req.setAttribute("memberVO2",memberVO2);
				boolean openModal=true;
				req.setAttribute("openModal",openModal );
				String url="/back-end/member/listAllMember.jsp";
				RequestDispatcher successView=req.getRequestDispatcher(url);
				successView.forward(req, res);
					
			}catch(Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/member/listAllMember.jsp");
				failureView.forward(req, res);
			}
		}
		if("back_Status_Update".equals(action)) {
			List<String> errorMsgs=new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL"); 
			req.setAttribute("requestURL", requestURL);
			System.out.println("requestURL"+requestURL);
			
			String whichPage = req.getParameter("whichPage");
			req.setAttribute("whichPage", whichPage);   
			System.out.println("whichPage"+whichPage);
			
			try {
				String mem_no=req.getParameter("mem_no");
				//查詢資料
				MemberService memberSvc=new MemberService();
				MemberVO memberVO2=memberSvc.getOneMember(mem_no);
				//轉交
				req.setAttribute("memberVO2",memberVO2);
				boolean openModal2=true;
				req.setAttribute("openModal2",openModal2 );
				String url="/back-end/member/listAllMember.jsp";
				RequestDispatcher successView=req.getRequestDispatcher(url);
				successView.forward(req, res);
					
			}catch(Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/member/listAllMember.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if("updateStatus".equals(action)) {
			List<String> errorMsgs=new LinkedList<String>();
			req.setAttribute("errorMsgs",errorMsgs);
			
			String requestURL = req.getParameter("requestURL"); 
			req.setAttribute("requestURL", requestURL); 
			System.out.println(requestURL);
			String whichPage = req.getParameter("whichPage"); 
			req.setAttribute("whichPage", whichPage);   
			System.out.println(whichPage);
			
			try {
				
				String mem_no=req.getParameter("mem_no");
				Integer mem_verify=Integer.parseInt(req.getParameter("mem_verify"));
				Integer leader_verify=Integer.parseInt(req.getParameter("leader_verify"));
				Integer pur_verify=Integer.parseInt(req.getParameter("pur_verify"));
				System.out.println("mem_verify"+mem_verify+"leader_verify"+leader_verify+"pur_verify"+pur_verify);
				MemberVO memberVO2=new MemberVO();
				memberVO2.setMem_no(mem_no);
				memberVO2.setMem_verify(mem_verify);
				memberVO2.setLeader_verify(leader_verify);
				memberVO2.setPur_verify(pur_verify);


				//修改資料
				MemberService memverSvc=new MemberService();
				memverSvc.update_back(mem_no, mem_verify, leader_verify, pur_verify);
				
				NotificationService notificationSvc=new NotificationService();
				String note_content="驗證狀態改變，請查看會員資料";
				notificationSvc.addNote(mem_no, note_content);
				
				
				//轉交
//				req.setAttribute("memberVO", memberVO2);
//				String url="/back-end/member/listAllMember.jsp";
//				RequestDispatcher successView=req.getRequestDispatcher(url);
//				successView.forward(req, res);
				String url = requestURL+"?whichPage="+whichPage+"&mem_noUpdata="+mem_no;
				System.out.println(url);
				res.sendRedirect(req.getContextPath()+url);
			}catch(Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView=req.getRequestDispatcher("/back-end/member/update_member_input.jsp");
				failureView.forward(req, res);
				
			}
		}
		
		
		//來自查詢全部listAllMember.jsp的請求
		if("front_One_Update".equals(action)) {
			List<String> errorMsgs=new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				//來自listAllMember.jsp的請求
				String mem_no=req.getParameter("mem_no");
				//查詢資料
				MemberService memberSvc=new MemberService();
				MemberVO memberVO=memberSvc.getOneMember(mem_no);
				//轉交
				req.setAttribute("memberVO2",memberVO);
				String url="/front-end/member/updateMember.jsp";
				RequestDispatcher successView=req.getRequestDispatcher(url);
				successView.forward(req, res);
					
			}catch(Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/member/listOneMember.jsp");
				failureView.forward(req, res);
			}
		}		
		
		
		if("back_update".equals(action)) {
			List<String> errorMsgs=new LinkedList<String>();
			req.setAttribute("errorMsgs",errorMsgs);
			
			String requestURL = req.getParameter("requestURL"); // 送出修改的來源網頁路徑: 可能為【/emp/listAllEmp.jsp】 或  【/dept/listEmps_ByDeptno.jsp】 或 【 /dept/listAllDept.jsp】
			req.setAttribute("requestURL", requestURL); // 送出修改的來源網頁路徑, 存入req
			System.out.println(requestURL);
			String whichPage = req.getParameter("whichPage"); // 送出修改的來源網頁的第幾頁(只用於:istAllEmp.jsp)
			req.setAttribute("whichPage", whichPage);   // 送出修改的來源網頁的第幾頁, 存入req(只用於:istAllEmp.jsp)
			System.out.println(whichPage);
			try {
				
				String mem_no=req.getParameter("mem_no");
				String mem_id=req.getParameter("mem_id");												
				//psw
				String psw=req.getParameter("psw");
				System.out.println("psw"+psw);
				String pswReg="^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,30}$";
				if(psw==null || psw.trim().length()==0) {
					errorMsgs.add("密碼：請勿空白");
				}else if(!psw.trim().matches(pswReg)) {
					errorMsgs.add("密碼只能是中、英文字母、數字和_ , 且長度必需在2到30之間");
				}
				
				//m_name
				String m_name = req.getParameter("m_name");
				System.out.println("m_name"+m_name);
				String m_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (m_name == null || m_name.trim().length() == 0) {
					errorMsgs.add("會員姓名: 請勿空白");
				} else if(!m_name.trim().matches(m_nameReg)) { 
					errorMsgs.add("會員姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }
				//gender
				Integer gender=null;
				gender=Integer.parseInt(req.getParameter("gender"));
				System.out.println("gender"+gender);
				

				//手機
				String cellphone=req.getParameter("cellphone");
				System.out.println("cellphone"+cellphone);
				String cellphoneReg="^09[0-9]{8}$";
				if(cellphone==null || cellphone.trim().length()==0) {
					errorMsgs.add("手機: 請勿空白");
				} else if(!cellphone.trim().matches(cellphoneReg)) { 
					errorMsgs.add("手機必須是10個數字，且09開頭");
	            }
				//email //在前端已作錯誤驗證
				String email=req.getParameter("email");
				System.out.println("email"+email);
				if(email==null || email.trim().length()==0) {
					errorMsgs.add("email: 請勿空白");
				}
				
				
				String address=req.getParameter("address");
				System.out.println("address"+address);
				String addrReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,30}$";
				if(address==null || address.trim().length()==0) {
					errorMsgs.add("address: 請勿空白");
				}else if(!m_name.trim().matches(addrReg)) { 
					errorMsgs.add("address: 只能是中、英文字母、數字和_ , 且長度必需在2到30之間");
	            }
				
							
				//m_photo用base64
				String m_photo=null;
				Part part=req.getPart("m_photo");
				InputStream is =part.getInputStream();	
				byte [] b_m_photo=new byte[is.available()];
				is.read(b_m_photo);
				is.close();
				Base64.Encoder encoder=Base64.getEncoder();
				m_photo=encoder.encodeToString(b_m_photo);
				System.out.println();
		
				//id_card
				byte [] id_card=null;
				Part part2=req.getPart("id_card");
				InputStream is2=part2.getInputStream();
				id_card=new byte[is2.available()];
				is2.read(id_card);
				is2.close();
				
					
				MemberVO memberVO2=new MemberVO();
				memberVO2.setMem_no(mem_no);
				memberVO2.setMem_id(mem_id);
				memberVO2.setPsw(psw);
				memberVO2.setM_name(m_name);
				memberVO2.setGender(gender);
				memberVO2.setCellphone(cellphone);
				memberVO2.setEmail(email);
				memberVO2.setAddress(address);
				memberVO2.setM_photo(m_photo);
				memberVO2.setId_card(id_card);

				
				if(!errorMsgs.isEmpty()) {
					req.setAttribute("memberVO2", memberVO2);
					RequestDispatcher failureView=req.getRequestDispatcher("/back-end/member/updateMem.jsp");
					failureView.forward(req, res);
					return; 
				}
				//修改資料
				MemberService memverSvc=new MemberService();
				memverSvc.update_front(mem_no, psw, m_name, gender, cellphone, email, address, m_photo, id_card);
				
				
				//轉交
//				req.setAttribute("memberVO", memberVO2);
//				String url="/back-end/member/listAllMember.jsp";
//				RequestDispatcher successView=req.getRequestDispatcher(url);
//				successView.forward(req, res);
				String url = requestURL+"?whichPage="+whichPage+"&mem_noUpdata="+mem_no;
				System.out.println(url);
				res.sendRedirect(req.getContextPath()+url);
			}catch(Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView=req.getRequestDispatcher("/back-end/member/update_member_input.jsp");
				failureView.forward(req, res);
				
			}
		}
		if("front_update".equals(action)) {
			List<String> errorMsgs=new LinkedList<String>();
			req.setAttribute("errorMsgs",errorMsgs);
			try {
				
				String mem_no=req.getParameter("mem_no");
				System.out.println(mem_no);
				String mem_id=req.getParameter("mem_id");	
				//psw
				String psw=req.getParameter("psw");
				System.out.println("psw"+psw);
				String pswReg="^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,30}$";
				if(psw==null || psw.trim().length()==0) {
					errorMsgs.add("密碼：請勿空白");
				}else if(!psw.trim().matches(pswReg)) {
					errorMsgs.add("密碼只能是中、英文字母、數字和_ , 且長度必需在2到30之間");
				}
				
				//m_name
				String m_name = req.getParameter("m_name");
//				System.out.println("m_name"+m_name);
				String m_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (m_name == null || m_name.trim().length() == 0) {
					errorMsgs.add("會員姓名: 請勿空白");
				} else if(!m_name.trim().matches(m_nameReg)) { 
					errorMsgs.add("會員姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }
				//gender
				Integer gender=null;
				gender=Integer.parseInt(req.getParameter("gender"));
				System.out.println("gender"+gender);
				

				//手機
				String cellphone=req.getParameter("cellphone");
				System.out.println("cellphone"+cellphone);
				String cellphoneReg="^09[0-9]{8}$";
				if(cellphone==null || cellphone.trim().length()==0) {
					errorMsgs.add("手機: 請勿空白");
				} else if(!cellphone.trim().matches(cellphoneReg)) { 
					errorMsgs.add("手機必須是10個數字，且09開頭");
	            }
				//email //在前端已作錯誤驗證
				String email=req.getParameter("email");
				System.out.println("email"+email);
				if(email==null || email.trim().length()==0) {
					errorMsgs.add("email: 請勿空白");
				}
				
				String address=req.getParameter("address");
				System.out.println("address"+address);
				String addrReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,30}$";
				if(address==null || address.trim().length()==0) {
					errorMsgs.add("address: 請勿空白");
				}
//				else if(!m_name.trim().matches(addrReg)) { 
//					errorMsgs.add("address: 只能是中、英文字母、數字和_ , 且長度必需在2到30之間");
//	            }
				
				
				//m_photo用base64
				String m_photo=null;
				Part part=req.getPart("m_photo");
				InputStream is =part.getInputStream();	
				byte [] b_m_photo=new byte[is.available()];
				is.read(b_m_photo);
				is.close();
				Base64.Encoder encoder=Base64.getEncoder();
				m_photo=encoder.encodeToString(b_m_photo);
				
		
				//id_card
				byte [] id_card=null;
				Part part2=req.getPart("id_card");
				InputStream is2=part2.getInputStream();
				id_card=new byte[is2.available()];
				is2.read(id_card);
				is2.close();
					
				MemberVO memberVO=new MemberVO();
				memberVO.setMem_no(mem_no);
				memberVO.setMem_id(mem_id);
				memberVO.setPsw(psw);
				memberVO.setM_name(m_name);
				memberVO.setGender(gender);
				memberVO.setCellphone(cellphone);
				memberVO.setEmail(email);
				memberVO.setAddress(address);
				memberVO.setM_photo(m_photo);
				memberVO.setId_card(id_card);

				
				if(!errorMsgs.isEmpty()) {
					req.setAttribute("memberVO2", memberVO);
					System.out.println(errorMsgs);
					RequestDispatcher failureView=req.getRequestDispatcher("/front-end/member/updateMember.jsp");
					failureView.forward(req, res);
					return; 
				}
				//修改資料
				MemberService memverSvc=new MemberService();
				memverSvc.update_front(mem_no, psw, m_name, gender, cellphone, email, address, m_photo, id_card);
				MemberVO memberVO2=new MemberVO();
				memberVO2=memverSvc.getOneMember(mem_no);
				
				//轉交
//				req.setAttribute("memberVO", memberVO2);
//				String url="/front-end/member/listOneMember.jsp";
//				RequestDispatcher successView=req.getRequestDispatcher(url);
//				successView.forward(req, res);
				String url=req.getContextPath()+"/front-end/member/listOneMemberSt.jsp?state=updatesuccess";
				res.sendRedirect(url);
				
			}catch(Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView=req.getRequestDispatcher("/front-end/member/update_member_input.jsp");
				failureView.forward(req, res);
				
			}
		}	
		
		if("insert".equals(action)) {
			List<String> errorMsgs=new LinkedList<String>();
			req.setAttribute("errorMsgs",errorMsgs);
			
			try {
				//mem_id
				String mem_id=req.getParameter("mem_id").trim();
				String idReg="^[a-zA-Z0-9_]{5,10}$";
				if(mem_id==null || mem_id.trim().length()==0) {
					errorMsgs.add("會員帳號：請勿空白");
				}else if(!mem_id.trim().matches(idReg)) {
					errorMsgs.add("會員帳號只能是英文字母、數字和_ , 且長度必需在5到10之間");
				}
				
				//psw
				String psw=req.getParameter("psw").trim();
				String pswReg="^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,30}$";
				if(psw==null || psw.trim().length()==0) {
					errorMsgs.add("密碼：請勿空白");
				}else if(!psw.trim().matches(pswReg)) {
					errorMsgs.add("密碼只能是中、英文字母、數字和_ , 且長度必需在2到30之間");
				}
				
				//m_name
				String m_name = req.getParameter("m_name").trim();
				String m_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (m_name == null || m_name.trim().length() == 0) {
					errorMsgs.add("會員姓名: 請勿空白");
				} else if(!m_name.trim().matches(m_nameReg)) { 
					errorMsgs.add("會員姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }
				//gender
				Integer gender=null;
				if(req.getParameter("gender")==null) {
					errorMsgs.add("性別請勿不點選");
				}else {		
					gender=Integer.parseInt(req.getParameter("gender"));
				}

				//手機
				String cellphone=req.getParameter("cellphone").trim();
				String cellphoneReg="^09[0-9]{8}$";
				if(cellphone==null || cellphone.trim().length()==0) {
					errorMsgs.add("手機: 請勿空白");
				} else if(!cellphone.trim().matches(cellphoneReg)) { 
					errorMsgs.add("手機必須是10個數字，且09開頭");
	            }
				//email //在前端已作錯誤驗證
				String email=req.getParameter("email").trim();
				if(email==null || email.trim().length()==0) {
					errorMsgs.add("email: 請勿空白");
				}
				String city=req.getParameter("city");
				String town=req.getParameter("town");
				String address=req.getParameter("address");
				if(city==null || city.trim().length()==0) {
					errorMsgs.add("城市請勿不選擇");
				}
				if(town==null || town.trim().length()==0) {
					errorMsgs.add("鄉鎮區請勿不選擇");
				}
				if(address==null || address.trim().length()==0) {
					errorMsgs.add("address: 請勿空白");
				}
				String totalAddr=city+town+address;
											
//				String address=req.getParameter("address").trim();
//				System.out.println("address"+address);
//				String addrReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,40}$";
//				if(address==null || address.trim().length()==0) {
//					errorMsgs.add("address: 請勿空白");
//				}else if(!address.trim().matches(addrReg)) { 
//					errorMsgs.add("address: 只能是中、英文字母、數字和_ , 且長度必需在2到50之間");
//	            }
				
							
				//m_photo用base64
				Part part=req.getPart("m_photo");
				String m_photo=null;
				InputStream is =part.getInputStream();	
				byte [] b_m_photo=new byte[is.available()];
				is.read(b_m_photo);
				
				Base64.Encoder encoder=Base64.getEncoder();
				m_photo=encoder.encodeToString(b_m_photo);
		
				
				//id_card
				byte [] id_card=null;
				Part part2=req.getPart("id_card");
				InputStream is2=part2.getInputStream();
				id_card=new byte[is2.available()];
				is2.read(id_card);
				
				String captcha=genAuthCode();
				
				
				MemberVO memberVO=new MemberVO();
				memberVO.setMem_id(mem_id);
				memberVO.setPsw(psw);
				memberVO.setM_name(m_name);
				memberVO.setGender(gender);
				memberVO.setCellphone(cellphone);
				memberVO.setEmail(email);
				memberVO.setM_photo(m_photo);
				memberVO.setId_card(id_card);
				memberVO.setCaptcha(captcha);
				memberVO.setAddress(totalAddr);
				is.close();
				is2.close();	
				//有寫錯誤 就會回到新增的頁面
//				if(!errorMsgs.isEmpty()) {
//					req.setAttribute("memberVO", memberVO);
//					RequestDispatcher failureView= req.getRequestDispatcher("/member/addMember.jsp");
//					failureView.forward(req, res);
//					return;
//				}
				if(!errorMsgs.isEmpty()) {
					req.setAttribute("memberVO2", memberVO);
					RequestDispatcher failureView= req.getRequestDispatcher("/front-end/member/addMember.jsp");
					failureView.forward(req, res);
					return;
				}
				
				MemberService memberSvc=new MemberService();
				//新增資料
				
				memberVO=memberSvc.addMember(mem_id, psw, m_name, gender, totalAddr, cellphone, email, m_photo, id_card,captcha);
				
				//新增完成 轉交頁面
				
//				MemberVO memberVO2=new MemberVO();
//				memberVO2=memberSvc.findOnePK(mem_id);
//				System.out.println(memberVO2.getMem_no());
//				memberVO2=memberSvc.getOneMember(memberVO.getMem_no());
//				
//				req.setAttribute("memberVO", memberVO2);
				
				String subject = "驗證碼通知";
			    String ch_name = memberVO.getM_name();
			    String passRandom = memberVO.getCaptcha();
			    String messageText = "Hello! " + ch_name + " 驗證碼: " + passRandom + 
			    		"\n"+"請至該網址輸入驗證碼 "+"\n"+"http://localhost:8081"+req.getContextPath()+"/front-end/member/MemberCaptcha.jsp"; 
				
				sendMail(memberVO.getEmail(),subject,messageText);
				
				
//				String url="/front-end/homepage.jsp";
//				RequestDispatcher successView=req.getRequestDispatcher(url);
//				successView.forward(req, res);
				String url=req.getContextPath()+"/front-end/member/addSuccess.jsp";
				res.sendRedirect(url);
				
			}catch(Exception e) {
				errorMsgs.add(e.getMessage()+e.getClass());
				RequestDispatcher failureView= req.getRequestDispatcher("/front-end/member/addMember.jsp");
				failureView.forward(req, res);				
			}
		}
		
		if("delete".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String mem_no=req.getParameter("mem_no");
				
				MemberService MemberSvc=new MemberService();
				MemberSvc.deleteMember(mem_no);
				
				String url="/member/listOneMember.jsp";
				RequestDispatcher successView =req.getRequestDispatcher(url);
				successView.forward(req, res);
				
			}catch(Exception e){
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/member/listOneMember.jsp");
				failureView.forward(req, res);
				
			}
			
		}
		
	}
	
	public static void sendMail(String to, String subject, String messageText) {
		
		   try {
			   // 設定使用SSL連線至 Gmail smtp Server
			   Properties props = new Properties();
			   props.put("mail.smtp.host", "smtp.gmail.com");
			   props.put("mail.smtp.socketFactory.port", "465");
			   props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
			   props.put("mail.smtp.auth", "true");
			   props.put("mail.smtp.port", "465");

	       // ●設定 gmail 的帳號 & 密碼 (將藉由你的Gmail來傳送Email)
	       // ●須將myGmail的【安全性較低的應用程式存取權】打開
		     final String myGmail = "da106g6@gmail.com";  //可以改成組內的email
		     final String myGmail_password = "da106g6123456";
			   Session session = Session.getInstance(props, new Authenticator() {
				   protected PasswordAuthentication getPasswordAuthentication() {
					   return new PasswordAuthentication(myGmail, myGmail_password);
				   }
			   });

			   Message message = new MimeMessage(session);
			   message.setFrom(new InternetAddress(myGmail));
			   message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(to));
			  
			   //設定信中的主旨  
			   message.setSubject(subject);
			   //設定信中的內容 
			   message.setText(messageText);

			   Transport.send(message);
			   System.out.println("傳送成功!");
	     }catch (MessagingException e){
		     System.out.println("傳送失敗!");
		     e.printStackTrace();
	     }
	   }
	


}
