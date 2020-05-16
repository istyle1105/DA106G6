package com.employee.controller;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.json.JSONException;
import org.json.JSONObject;

import com.authority.model.AuthorityService;
import com.authority.model.AuthorityVO;
import com.employee.model.*;
import com.member.model.MemberService;
import com.member.model.MemberVO;


@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)

public class EmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public EmployeeServlet() {
        super();
    }
    

    public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}
    
    protected boolean allowUser(String emp_id, String emp_psw) {
    	
    	EmployeeVO employeeVO=null;
    	EmployeeService employeeSvc=new EmployeeService();
    	if(employeeSvc.getOneEmpById(emp_id)==null) {
    		return false;
    	}else
    		employeeVO=employeeSvc.getOneEmpById(emp_id);
    	    	
        if (employeeVO.getEmp_id().equals(emp_id) && employeeVO.getEmp_psw().equals(emp_psw))
          return true;
        else
          return false;
    }
    
    public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
    	req.setCharacterEncoding("UTF-8");
    	res.setContentType("text/html; charset=UTF-8");
		String action = req.getParameter("action");

		System.out.println("action="+action);
		
		if("checkIfIdExist".equals(action)) {
			
			JSONObject output = new JSONObject();
			String inputEmpId=req.getParameter("inputEmpId");
			EmployeeService empSvc=new EmployeeService();
			EmployeeVO empVO=null;
			empVO=empSvc.getOneEmpById(inputEmpId);
			
			try {
		        output.put("available", ((empVO==null)?"Y":"N"));
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
			session.removeAttribute("emp_no");
			res.sendRedirect(req.getContextPath()+"/back-end/AdminLogin.jsp?state=logout");
			
		}
		
		
		if("adminLogin".equals(action)) {
			String emp_id=req.getParameter("emp_id").trim();
			String emp_psw=req.getParameter("emp_psw").trim();
			System.out.println(emp_id+emp_psw);
			
			if(!allowUser(emp_id,emp_psw)) {
				//登入不成功
				System.out.println("error");
				String URL=req.getContextPath()+"/back-end/AdminLogin.jsp?error=false";
				res.sendRedirect(URL);
				return;
			}
			else {
				EmployeeService employeeSvc=new EmployeeService();
				EmployeeVO employeeVO=null;
				employeeVO=employeeSvc.getOneEmpById(emp_id);
				if(employeeVO.getEmp_status()==1) {
					res.sendRedirect(req.getContextPath()+"/back-end/AdminLogin.jsp?state=noAuth");
					return;
				}
				
				HttpSession session=req.getSession();
				session.setAttribute("emp_no", employeeVO.getEmp_no());
				try {
					String backLocation=(String)session.getAttribute("backLocation");
					if (backLocation != null) {
						System.out.println("controller backLocation="+backLocation);
						session.removeAttribute("backLocation");  
				        res.sendRedirect(backLocation);            
				        return;
				    }
					
				}catch(Exception ignored) { }
				res.sendRedirect(req.getContextPath()+"/back-end/index.jsp"); 
			}
			
		}
		
		
		if("get_One_Auth".equals(action)) {
			
			List<String> errorMsgs=new LinkedList<String>();
			req.setAttribute("errorMsgs",errorMsgs);
			try {
				String emp_no=req.getParameter("emp_no");
				List<AuthorityVO> listAuth= new ArrayList();
				AuthorityService authoritySvc= new AuthorityService();
				listAuth=authoritySvc.getOneEmp(emp_no);
				
				
				req.setAttribute("listAuth", listAuth);
				String url ="/employee/listAllEmployee.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);
				
				
			}catch(Exception e) {
				
			}
			
		}
		
		if("getOne_For_Display".equals(action)) {
			List<String> errorMsgs=new LinkedList<String>();
			req.setAttribute("errorMsgs",errorMsgs);
			try {
				String emp_no=req.getParameter("emp_no");
				String emp_noReg="^E[0-9]{4}$";
				if(emp_no == null || emp_no.trim().length()==0) {
					errorMsgs.add("請輸入員工編號請勿空白");
				}else if(!emp_no.trim().matches(emp_noReg)){
					errorMsgs.add("員工編號必須是E開頭，且後面加4位數字");	
				}
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/employee/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				//查資料
				EmployeeService employeeSvc=new EmployeeService();
				EmployeeVO employeeVO=employeeSvc.getOneEmp(emp_no);
				if(employeeVO == null) {
					errorMsgs.add("查無資料");
				}
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/employee/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				//查詢完成
				req.setAttribute("employeeVO", employeeVO);
				String url ="/employee/listOneEmployee.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);
				
				
			}catch(Exception e) {
				errorMsgs.add("無法取得資料:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/employee/select_page.jsp");
				failureView.forward(req, res);		
			}
		}
		if("getIndi_Update".equals(action)) {
			//員工個人頁面的修改
			List<String> errorMsgs=new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				
				String emp_no=req.getParameter("emp_no");
				//查詢資料
				EmployeeService employeeSvc=new EmployeeService();
				EmployeeVO employeeVO2=employeeSvc.getOneEmp(emp_no);
				//轉交
				req.setAttribute("employeeVO2",employeeVO2);
				String url="/back-end/employee/updateEmp.jsp";
				RequestDispatcher successView=req.getRequestDispatcher(url);
				successView.forward(req, res);
				
				
			}catch(Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/employee/listOneEmployee.jsp");
				failureView.forward(req, res);
				
			}
			
		}

		if("updateEmp".equals(action)) {
			List<String> errorMsgs=new LinkedList<String>();
			req.setAttribute("errorMsgs",errorMsgs);
			try {
				String emp_no=req.getParameter("emp_no");
				System.out.println(emp_no);
				
				String emp_psw=req.getParameter("emp_psw");
				String pswReg="^(?=.*[a-z])(?=.*[A-Z])[a-zA-Z0-9_]{6,20}$";
				if(emp_psw==null || emp_psw.trim().length()==0) {
					errorMsgs.add("密碼：請勿空白");
				}else if(!emp_psw.trim().matches(pswReg)) {
					errorMsgs.add("密碼只能包含大小寫英文字母及數字和底線，必須包含至少一個小寫字母與一個大寫字母，並且密碼長度必須有6-15碼");
				}
				System.out.println(emp_psw);
				//m_name
				String emp_name = req.getParameter("emp_name");
				String nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (emp_name == null || emp_name.trim().length() == 0) {
					errorMsgs.add("姓名: 請勿空白");
				} else if(!emp_name.trim().matches(nameReg)) { 
					errorMsgs.add("姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }
				System.out.println(emp_name);
				//手機
				String emp_cellphone=req.getParameter("emp_cellphone");
				String cellphoneReg="^09[0-9]{8}$";
				if(emp_cellphone==null || emp_cellphone.trim().length()==0) {
					errorMsgs.add("手機: 請勿空白");
				} else if(!emp_cellphone.trim().matches(cellphoneReg)) { 
					errorMsgs.add("手機必須是10個數字，且09開頭");
	            }
				//email //在前端已作錯誤驗證
				String emp_email=req.getParameter("emp_email");
				if(emp_email==null || emp_email.trim().length()==0) {
					errorMsgs.add("email: 請勿空白");
				}
				
				//m_photo用base64
				String emp_photo=null;
				
				Part part=req.getPart("emp_photo");
				InputStream is =part.getInputStream();	
				byte [] b_emp_photo=new byte[is.available()];
				is.read(b_emp_photo);
				is.close();
				Base64.Encoder encoder=Base64.getEncoder();
				emp_photo=encoder.encodeToString(b_emp_photo);

				

				EmployeeVO employeeVO=new EmployeeVO();
				employeeVO.setEmp_no(emp_no);
				employeeVO.setEmp_psw(emp_psw);
				employeeVO.setEmp_name(emp_name);
				employeeVO.setEmp_cellphone(emp_cellphone);
				employeeVO.setEmp_email(emp_email);
				employeeVO.setEmp_photo(emp_photo);
				System.out.println(employeeVO.getEmp_no());

				if(!errorMsgs.isEmpty()) {
					req.setAttribute("employeeVO", employeeVO);
					RequestDispatcher failureView=req.getRequestDispatcher("/back-end/employee/updateEmp.jsp");
					failureView.forward(req, res);
					return; 
				}
				
				
				//修改資料
				EmployeeService employeeSvc=new EmployeeService();
				employeeVO=employeeSvc.updateEmp(emp_no, emp_psw, emp_name, emp_cellphone, emp_email, emp_photo);
			
				//轉交
//				req.setAttribute("employeeVO", employeeVO);
//				String url="/employee/listOneEmployee.jsp";
//				RequestDispatcher successView=req.getRequestDispatcher(url);
//				successView.forward(req, res);
				res.sendRedirect(req.getContextPath()+"/back-end/employee/listOneEmployee.jsp");
				
			
			}catch(Exception e){
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView=req.getRequestDispatcher("/back-end/employee/updateEmp.jsp");
				failureView.forward(req, res);

				
			}
			
		}
		if("getOne_For_Update".equals(action)) {
			List<String> errorMsgs=new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			

			String requestURL = req.getParameter("requestURL"); // 送出修改的來源網頁路徑: 可能為【/emp/listAllEmp.jsp】 或  【/dept/listEmps_ByDeptno.jsp】 或 【 /dept/listAllDept.jsp】
			req.setAttribute("requestURL", requestURL); // 送出修改的來源網頁路徑, 存入req (是為了給update_emp_input.jsp)
			System.out.println("requestURL"+requestURL);
			
			String whichPage = req.getParameter("whichPage");
			req.setAttribute("whichPage", whichPage);   // 送出修改的來源網頁的第幾頁, 存入req(只用於:istAllEmp.jsp)
			System.out.println("whichPage"+whichPage);
			
			try {
				
				String emp_no=req.getParameter("emp_no");
				//查詢資料
				EmployeeService employeeSvc=new EmployeeService();
				EmployeeVO employeeVO2=employeeSvc.getOneEmp(emp_no);
				//轉交
				req.setAttribute("employeeVO2",employeeVO2);
				String url="/back-end/employee/updateEmpAuth.jsp";
				RequestDispatcher successView=req.getRequestDispatcher(url);
				successView.forward(req, res);
				
				
			}catch(Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/employee/listAllEmployee.jsp");
				failureView.forward(req, res);
				
			}
			
		}
		
		if("updateEmpAuth".equals(action)) {
			List<String> errorMsgs=new LinkedList<String>();
			req.setAttribute("errorMsgs",errorMsgs);
			
			String requestURL = req.getParameter("requestURL"); // 送出修改的來源網頁路徑: 可能為【/emp/listAllEmp.jsp】 或  【/dept/listEmps_ByDeptno.jsp】 或 【 /dept/listAllDept.jsp】
			req.setAttribute("requestURL", requestURL); // 送出修改的來源網頁路徑, 存入req
			System.out.println("requestURL"+requestURL);
			
			
			String whichPage = req.getParameter("whichPage"); // 送出修改的來源網頁的第幾頁(只用於:istAllEmp.jsp)
			req.setAttribute("whichPage", whichPage);   // 送出修改的來源網頁的第幾頁, 存入req(只用於:istAllEmp.jsp)
			System.out.println("whichPage:"+whichPage);
			
			try {
				String emp_no=req.getParameter("emp_no");
							
				Integer emp_status = Integer.parseInt(req.getParameter("emp_status").trim());
				
				String emp_psw=req.getParameter("emp_psw");
				String pswReg="^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,30}$";
				if(emp_psw==null || emp_psw.trim().length()==0) {
					errorMsgs.add("密碼：請勿空白");
				}else if(!emp_psw.trim().matches(pswReg)) {
					errorMsgs.add("密碼只能是中、英文字母、數字和_ , 且長度必需在2到30之間");
				}
				//m_name
				String emp_name = req.getParameter("emp_name");
				String nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (emp_name == null || emp_name.trim().length() == 0) {
					errorMsgs.add("姓名: 請勿空白");
				} else if(!emp_name.trim().matches(nameReg)) { 
					errorMsgs.add("姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }
				//手機
				String emp_cellphone=req.getParameter("emp_cellphone");
				String cellphoneReg="^09[0-9]{8}$";
				if(emp_cellphone==null || emp_cellphone.trim().length()==0) {
					errorMsgs.add("手機: 請勿空白");
				} else if(!emp_cellphone.trim().matches(cellphoneReg)) { 
					errorMsgs.add("手機必須是10個數字，且09開頭");
	            }
				//email //在前端已作錯誤驗證
				String emp_email=req.getParameter("emp_email");
				if(emp_email==null || emp_email.trim().length()==0) {
					errorMsgs.add("email: 請勿空白");
				}
				
				//m_photo用base64
				String emp_photo=null;
				Part part=req.getPart("emp_photo");
				InputStream is =part.getInputStream();	
				byte [] b_emp_photo=new byte[is.available()];
				is.read(b_emp_photo);
				is.close();
				Base64.Encoder encoder=Base64.getEncoder();
				emp_photo=encoder.encodeToString(b_emp_photo);

				
			
				EmployeeVO employeeVO2=new EmployeeVO();
				employeeVO2.setEmp_no(emp_no);
				employeeVO2.setEmp_status(emp_status);
				employeeVO2.setEmp_psw(emp_psw);
				employeeVO2.setEmp_name(emp_name);
				employeeVO2.setEmp_cellphone(emp_cellphone);
				employeeVO2.setEmp_email(emp_email);
				employeeVO2.setEmp_photo(emp_photo);
				System.out.println("修改的員工編號為"+employeeVO2.getEmp_no());
				
				
				
				if(!errorMsgs.isEmpty()) {
					req.setAttribute("employeeVO2", employeeVO2);
					RequestDispatcher failureView=req.getRequestDispatcher("/back-end/employee/updateEmpAuth.jsp");
					failureView.forward(req, res);
					return; 
				}
				
				
				//修改資料
				EmployeeService employeeSvc=new EmployeeService();
				employeeSvc.updateEmpALL(emp_no, emp_status, emp_psw, emp_name, emp_cellphone, emp_email, emp_photo);
				System.out.println("update done");
				System.out.println(req.getParameterValues("fun_no"));
				if(req.getParameterValues("fun_no")!=null) {
					System.out.println(req.getParameterValues("fun_no"));
					List<AuthorityVO> Authlist=new ArrayList<AuthorityVO>();
					String [] fun_no=req.getParameterValues("fun_no");
					
					
					AuthorityService AuthoritySvc=new AuthorityService();
					AuthoritySvc.deleteAuth(emp_no);
					
					
						for(int i=0; i<fun_no.length;i++) {			
							AuthorityVO authorityVO=new AuthorityVO();
							authorityVO.setFun_no(fun_no[i]);
							System.out.println(fun_no[i]);
							Authlist.add(authorityVO);
						}
					
					
						for(AuthorityVO authorityVO:Authlist) {
							AuthorityService AuthoritySvc2=new AuthorityService();
							AuthoritySvc2.addAuth(emp_no, authorityVO.getFun_no());		
						}
					
					
				}else {
					System.out.println("delete");
					AuthorityService AuthoritySvc=new AuthorityService();
					AuthoritySvc.deleteAuth(emp_no);
					
				}
				
				//轉交
//				req.setAttribute("employeeVO2", employeeVO2);
//				String url="/employee/listOneEmployee.jsp";
				//requestURL從listAllEmployee來的
				String url = requestURL+"?whichPage="+whichPage+"&emp_noUpdata="+emp_no;
				System.out.println(url);
//				RequestDispatcher successView=req.getRequestDispatcher(url);
//				successView.forward(req, res);
				res.sendRedirect(req.getContextPath()+url);
				
			
			}catch(Exception e){
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView=req.getRequestDispatcher("/employee/update_employee_input.jsp");
				failureView.forward(req, res);

				
			}
			
		}

		
		
		if("insert".equals(action)) {
			List<String> errorMsgs=new LinkedList<String>();
			req.setAttribute("errorMsgs",errorMsgs);
			try {
				//emp_id
				String emp_id=req.getParameter("emp_id");
				String idReg="^[a-zA-Z0-9_]{5,10}$";
				if(emp_id==null || emp_id.trim().length()==0) {
					errorMsgs.add("員工帳號：請勿空白");
				}else if(!emp_id.trim().matches(idReg)) {
					errorMsgs.add("員工帳號只能是英文字母、數字和_ , 且長度必需在5到10之間");
				}
				//emp_psw
				String emp_psw=genAuthCode();
//				String emp_psw=req.getParameter("emp_psw");
//				String pswReg="^(?=.*[a-z])(?=.*[A-Z])[a-zA-Z0-9_]{6,20}$";
//				if(emp_psw==null || emp_psw.trim().length()==0) {
//					errorMsgs.add("密碼：請勿空白");
//				}else if(!emp_psw.trim().matches(pswReg)) {
//					errorMsgs.add("密碼只能包含大小寫英文字母及數字和底線，必須包含至少一個小寫字母與一個大寫字母，並且密碼長度必須有6-15碼");
//				}
				//emp_name
				String emp_name = req.getParameter("emp_name");
				String emp_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (emp_name == null || emp_name.trim().length() == 0) {
					errorMsgs.add("姓名: 請勿空白");
				} else if(!emp_name.trim().matches(emp_nameReg)) { 
					errorMsgs.add("姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }
				//手機
				String emp_cellphone=req.getParameter("emp_cellphone");
				String cellphoneReg="^09[0-9]{8}$";
				if(emp_cellphone==null || emp_cellphone.trim().length()==0) {
					errorMsgs.add("手機: 請勿空白");
				} else if(!emp_cellphone.trim().matches(cellphoneReg)) { 
					errorMsgs.add("手機必須是10個數字，且09開頭");
	            }
				//email //在前端已作錯誤驗證
				String emp_email=req.getParameter("emp_email");
				if(emp_email==null || emp_email.trim().length()==0) {
					errorMsgs.add("email: 請勿空白");
				}
				
				Part part=req.getPart("emp_photo");
				String header=part.getHeader("content-disposition");
				System.out.println("header"+header);
				String filename =new File(header.substring(header.lastIndexOf("=") + 2, header.length() - 1)).getName();
				System.out.println("filename=" + filename); //沒有上傳的話 這裡會是空的
				
				String emp_photo=null;
				InputStream is =part.getInputStream();	
				byte [] b_emp_photo=new byte[is.available()];
				is.read(b_emp_photo);
				Base64.Encoder encoder=Base64.getEncoder();
				emp_photo=encoder.encodeToString(b_emp_photo);
				is.close();
				

				


				EmployeeVO employeeVO2=new EmployeeVO();
				employeeVO2.setEmp_id(emp_id);
				employeeVO2.setEmp_psw(emp_psw);
				employeeVO2.setEmp_name(emp_name);
				employeeVO2.setEmp_cellphone(emp_cellphone);
				employeeVO2.setEmp_email(emp_email);
				employeeVO2.setEmp_photo(emp_photo);
//				//有寫錯誤 就會回到新增的頁面
				if(!errorMsgs.isEmpty()) {
					req.setAttribute("employeeVO2", employeeVO2);
					RequestDispatcher failureView= req.getRequestDispatcher("/back-end/employee/addEmployee.jsp");
					failureView.forward(req, res);
					return;
				}
				
				List<AuthorityVO> Authlist=new ArrayList<AuthorityVO>();
				AuthorityVO authorityVO=null;
				
				if(req.getParameterValues("fun_no")!=null) {
					String [] fun_no=req.getParameterValues("fun_no");
					
					for(String str: fun_no) {
						authorityVO=new AuthorityVO();
						authorityVO.setFun_no(str);
						Authlist.add(authorityVO);
					}
				}
				
				
				//新增資料
				try {
				EmployeeService employeeSvc=new EmployeeService();
//				employeeVO=employeeSvc.addEmp(emp_id, emp_psw, emp_name, emp_cellphone, emp_email, emp_photo);
				employeeVO2=employeeSvc.addEmp2(emp_id, emp_psw, emp_name, emp_cellphone, emp_email, emp_photo, Authlist);
				}catch(RuntimeException re) {
					errorMsgs.add("此帳號已重複使用");
				}
				if(!errorMsgs.isEmpty()) {
					req.setAttribute("employeeVO2", employeeVO2);
					RequestDispatcher failureView= req.getRequestDispatcher("/back-end/employee/addEmployee.jsp");
					failureView.forward(req, res);
					return;
				}
				

//				for(AuthorityVO authorityVO:Authlist) {
//					AuthorityService AuthoritySvc=new AuthorityService();
//					AuthoritySvc.addAuth(emp_no, authorityVO.getFun_no());		
//				}
				String subject = "員工帳號開通通知";
			    String ch_name = employeeVO2.getEmp_name();
			    String passRandom = employeeVO2.getEmp_psw();
			    String messageText = "Hello! " + ch_name+ "\n" +"員工帳號：" + emp_id +"，請謹記此密碼: " + passRandom + " (已經啟用)"+
			    		"\n"+"http://localhost:8081/"+req.getContextPath()+"/back-end/AdminLogin.jsp"; 
				
				sendMail(employeeVO2.getEmp_email(),subject,messageText);
				
				//新增完成 轉交頁面
				String url="/back-end/employee/listAllEmployee.jsp";
				RequestDispatcher successView=req.getRequestDispatcher(url);
				successView.forward(req, res);
				
				
			}catch(Exception e) {
				errorMsgs.add(e.getMessage()+e.getClass());
				RequestDispatcher failureView= req.getRequestDispatcher("/back-end/employee/addEmployee.jsp");
				failureView.forward(req, res);
				
			}
		}
		if("delete".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				String emp_no=req.getParameter("emp_no");
				EmployeeService EmployeeSvc=new EmployeeService();
				EmployeeSvc.deleteEmp(emp_no);
				
				String url="/employee/listAllEmployee.jsp";
				RequestDispatcher successView =req.getRequestDispatcher(url);
				successView.forward(req, res);
				
			}catch(Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/employee/listAllEmployee.jsp");
				failureView.forward(req, res);
				
			}
			
		}
		
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
