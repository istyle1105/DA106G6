package com.function.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.employee.model.EmployeeService;
import com.employee.model.EmployeeVO;
import com.function.model.FunctionService;
import com.function.model.FunctionVO;

public class FunctionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public FunctionServlet() {
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
		
		if("getOne_For_Display".equals(action)) {
			List<String> errorMsgs=new LinkedList<String>();
			req.setAttribute("errorMsgs",errorMsgs);
			try {
				String fun_no=req.getParameter("fun_no");
				String fun_noReg="^EF[0-9]{4}$";
				if(fun_no == null || fun_no.trim().length()==0) {
					errorMsgs.add("請輸入後台功能編號請勿空白");
				}else if(!fun_no.trim().matches(fun_noReg)){
					errorMsgs.add("後台功能編號必須是EF開頭，且後面加4位數字");	
				}
				
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/function/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				//查資料
				FunctionService functionSvc=new FunctionService();
				FunctionVO functionVO=functionSvc.getOneFun(fun_no);
				if(functionVO == null) {
					errorMsgs.add("查無資料");
				}
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/function/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				//查詢完成
				req.setAttribute("functionVO", functionVO);
				String url ="/function/listOneFunction.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);
			}catch(Exception e) {
				errorMsgs.add("無法取得資料:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/function/select_page.jsp");
				failureView.forward(req, res);
				}
		}
		
		if("getOne_For_Update".equals(action)) {
			List<String> errorMsgs=new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				String fun_no=req.getParameter("fun_no");
				//查詢資料
				FunctionService functionSvc=new FunctionService();
				FunctionVO functionVO=functionSvc.getOneFun(fun_no);
				//轉交
				req.setAttribute("functionVO",functionVO);
				String url="/function/update_function_input.jsp";
				RequestDispatcher successView=req.getRequestDispatcher(url);
				successView.forward(req, res);	
			}catch(Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/function/listAllFunction.jsp");
				failureView.forward(req, res);
				
				
			}
		}
		if("update".equals(action)) {
			List<String> errorMsgs=new LinkedList<String>();
			req.setAttribute("errorMsgs",errorMsgs);
			try {
				String fun_no=req.getParameter("fun_no");
				
				//fun_name
				String fun_name = req.getParameter("fun_name");
				if (fun_name == null || fun_name.trim().length() == 0) {
					errorMsgs.add("功能名稱: 請勿空白");
				}
				FunctionVO functionVO=new FunctionVO();
				functionVO.setFun_no(fun_no);
				functionVO.setFun_name(fun_name);
				
				if(!errorMsgs.isEmpty()) {
					req.setAttribute("functionVO", functionVO);
					RequestDispatcher failureView=req.getRequestDispatcher("/function/update_function_input.jsp");
					failureView.forward(req, res);
					return; 
				}
				//修改資料
				FunctionService functionService=new FunctionService();
				functionVO=functionService.updateFun(fun_no, fun_name);
				//轉交
				req.setAttribute("functionVO", functionVO);
				String url="/function/listOneFunction.jsp";
				RequestDispatcher successView=req.getRequestDispatcher(url);
				successView.forward(req, res);
				
			}catch(Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView=req.getRequestDispatcher("/function/update_function_input.jsp");
				failureView.forward(req, res);
			}
	
		}
		
		
		
		
		
		
		if("insert".equals(action)) {
			List<String> errorMsgs=new LinkedList<String>();
			req.setAttribute("errorMsgs",errorMsgs);
			try {
				
				//fun_name
				String fun_name = req.getParameter("fun_name");
				if (fun_name == null || fun_name.trim().length() == 0) {
					errorMsgs.add("功能名稱: 請勿空白");
				}
				FunctionVO functionVO=new FunctionVO();
				functionVO.setFun_name(fun_name);
				
				if(!errorMsgs.isEmpty()) {
					req.setAttribute("functionVO", functionVO);
					RequestDispatcher failureView= req.getRequestDispatcher("/function/addFunction.jsp");
					failureView.forward(req, res);
					return;
				}
				FunctionService functionService=new FunctionService();
				functionVO=functionService.addFun(fun_name);
				
				
				//新增完成 轉交頁面
				String url="/function/listAllFunction.jsp";
				RequestDispatcher successView=req.getRequestDispatcher(url);
				successView.forward(req, res);
				
			}catch(Exception e) {
				errorMsgs.add(e.getMessage()+e.getClass());
				RequestDispatcher failureView= req.getRequestDispatcher("/function/addFunction.jsp");
				failureView.forward(req, res);
			}

		}
		if("delete".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				
				String fun_no=req.getParameter("fun_no");
				FunctionService functionService=new FunctionService();
				functionService.deleteFun(fun_no);
				
				String url="/function/listAllFunction.jsp";
				RequestDispatcher successView =req.getRequestDispatcher(url);
				successView.forward(req, res);
				
			}catch(Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/function/listAllFunction.jsp");
				failureView.forward(req, res);
				
			}
			
		}
		
    }

}
