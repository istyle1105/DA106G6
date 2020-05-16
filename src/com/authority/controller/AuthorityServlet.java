package com.authority.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.authority.model.AuthorityService;
import com.authority.model.AuthorityVO;
import com.employee.model.EmployeeService;

public class AuthorityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public AuthorityServlet() {
        super();
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

    public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
    	req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if("change".equals(action)) {
			List<String> errorMsgs=new LinkedList<String>();
			req.setAttribute("errorMsgs",errorMsgs);
			System.out.println("action="+action);
			
			try {
				List<AuthorityVO> Authlist=new ArrayList<AuthorityVO>();
				String emp_no=req.getParameter("emp_no");
				System.out.println(emp_no);

				if(req.getParameterValues("fun_no")!=null) {
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
					AuthorityService AuthoritySvc=new AuthorityService();
					AuthoritySvc.deleteAuth(emp_no);
				}

				//新增完成 轉交頁面
				String url="/back-end/employee/listAllEmployee.jsp";
				RequestDispatcher successView=req.getRequestDispatcher(url);
				successView.forward(req, res);

			}catch(Exception e) {
				errorMsgs.add(e.getMessage()+e.getClass());
				RequestDispatcher failureView= req.getRequestDispatcher("/back-end/employee/listAllEmployee.jsp");
				failureView.forward(req, res);			
			}

		}
		
		
		
		if("insert".equals(action)) {
			List<String> errorMsgs=new LinkedList<String>();
			req.setAttribute("errorMsgs",errorMsgs);
			
			try {
				List<AuthorityVO> list=new ArrayList<AuthorityVO>();
				String emp_no=req.getParameter("emp_no");
				System.out.println(emp_no);
				String [] fun_no=req.getParameterValues("fun_no");
				
				if(fun_no.length==0) {
					errorMsgs.add("功能請勿不勾選");
				}
				
				
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView= req.getRequestDispatcher("/authority/addAuthority.jsp");
					failureView.forward(req, res);
					return;
				}
				for(int i=0; i<fun_no.length;i++) {
					AuthorityVO authorityVO=new AuthorityVO();
					authorityVO.setFun_no(fun_no[i]);
					System.out.println(fun_no[i]);
					list.add(authorityVO);
				}
				
				
				for(AuthorityVO authorityVO:list) {
					AuthorityService AuthoritySvc=new AuthorityService();
					AuthoritySvc.addAuth(emp_no, authorityVO.getFun_no());		
				}
				
				
				
				
				//新增完成 轉交頁面
				String url="/authority/listAllAuthority.jsp";
				RequestDispatcher successView=req.getRequestDispatcher(url);
				successView.forward(req, res);

			}catch(Exception e) {
				errorMsgs.add(e.getMessage()+e.getClass());
				RequestDispatcher failureView= req.getRequestDispatcher("/authority/addAuthority.jsp");
				failureView.forward(req, res);			
			}

		}
		if("delete".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				String emp_no=req.getParameter("emp_no");
				String fun_no=req.getParameter("fun_no");
				AuthorityService AuthoritySvc=new AuthorityService();
				AuthoritySvc.deleteAuth(emp_no);
				
				String url="/authority/listAllAuthority.jsp";
				RequestDispatcher successView =req.getRequestDispatcher(url);
				successView.forward(req, res);
				
			}catch(Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/authority/listAllAuthority.jsp");
				failureView.forward(req, res);
				
			}
			
		}
		
		
		
	}

}
