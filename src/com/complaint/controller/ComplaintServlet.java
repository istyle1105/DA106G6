package com.complaint.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.complaint.model.ComplaintService;


public class ComplaintServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ComplaintServlet() {
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
		if("compUpdateStatus".equals(action)) {
			String comp_no=req.getParameter("comp_no");
			String requestURL = req.getParameter("requestURL"); 
			req.setAttribute("requestURL", requestURL); 
			System.out.println("requestURL"+requestURL);
			
			String whichPage = req.getParameter("whichPage");
			req.setAttribute("whichPage", whichPage);
			
			System.out.println("whichPage"+whichPage);
			ComplaintService complaintSvc=new ComplaintService();
			complaintSvc.updateCompSta(1, comp_no);
			
			String url = requestURL+"?whichPage="+whichPage+"&comp_noUpdata="+comp_no;
			System.out.println(url);
			res.sendRedirect(req.getContextPath()+url);	
			
		}
		
		
		
		if("insert".equals(action)) {
			List<String> errorMsgs=new LinkedList<String>();
			req.setAttribute("errorMsgs",errorMsgs);
			String comp_content=req.getParameter("comp_content");
			String mem_no=req.getParameter("mem_no");
			System.out.println(mem_no);
			
			if(comp_content==null || comp_content.trim().length()==0) {
				errorMsgs.add("申訴內容：請勿空白");
			}
			
			if(!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/complaint/addComp.jsp");
				failureView.forward(req, res);
				return;
			}
			ComplaintService complaintSvc=new ComplaintService();
			complaintSvc.addComp(mem_no, comp_content);
			res.sendRedirect(req.getContextPath()+"/front-end/complaint/listAllComp.jsp"); 
			
			
		}
	}

}
