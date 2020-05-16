package com.util;

import java.io.IOException;
import java.util.*;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class AllStatus extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public AllStatus() {
        super();
        // TODO Auto-generated constructor stub
    }
    public void init() {
    	ServletContext context=getServletContext();
    	System.out.println("Status---------------------------");
    	
    	HashMap<String, String> empStatus=new HashMap<>();
    	empStatus.put("0", "在職");
    	empStatus.put("1", "離職");
    	context.setAttribute("empStatus", empStatus);
    	
    	HashMap memStatus=new HashMap();
    	memStatus.put("0", "未驗證");
    	memStatus.put("1", "已驗證");
    	memStatus.put("2", "停權");
    	context.setAttribute("memStatus", memStatus);
    	
    	
    	HashMap compStatus=new HashMap();
    	compStatus.put("0", "未處理");
    	compStatus.put("1", "已處理");
    	context.setAttribute("compStatus", compStatus);
    	
    	HashMap noteStatus=new HashMap();
    	noteStatus.put("0", "未讀");
    	noteStatus.put("1", "已讀");
    	context.setAttribute("noteStatus", noteStatus);
    	
    	HashMap leaderStatus=new HashMap();
    	leaderStatus.put("0", "未驗證");
    	leaderStatus.put("1", "已驗證");
    	leaderStatus.put("2", "停權");
    	context.setAttribute("leaderStatus", leaderStatus);
    	
    	HashMap purStatus=new HashMap();
    	purStatus.put("0", "未驗證");
    	purStatus.put("1", "已驗證");
    	purStatus.put("2", "停權");
    	context.setAttribute("purStatus", purStatus);
    	
	
    	HashMap<Integer, String> Status = new HashMap<>();
    	Status.put(0, "上架中");
    	Status.put(1, "已下架");
    	Status.put(2, "已下標");
    	Status.put(3, "已購買");
    	Status.put(4, "運送中");
    	Status.put(5, "已送達");
    	Status.put(6, "交易完成");
    	context.setAttribute("Status", Status);
    	
    	
    	HashMap<Integer,String> om_status = new HashMap<>();
    	om_status.put(0, "待出貨");
    	om_status.put(1, "已出貨");
    	om_status.put(2, "交易完成");
    	om_status.put(3, "換貨中");
    	om_status.put(4, "退貨");
    	om_status.put(5, "已取消");	
    	context.setAttribute("om_status", om_status);
    	
    	Map l_re_status = new HashMap(); 
    	l_re_status.put(0, "待處理");
    	l_re_status.put(1, "已處理");
    	context.setAttribute("l_re_status", l_re_status);
    	
  
    	Map l_status = new HashMap();
    	l_status.put(0, "上架中");
    	l_status.put(1, "下架中");
    	context.setAttribute("l_status", l_status);
    	
    	HashMap tripStatus=new HashMap();
    	tripStatus.put(0, "未成團");
    	tripStatus.put(1, "成團");
    	tripStatus.put(2, "已取消");
    	
    	context.setAttribute("tripStatus", tripStatus);
    	
    	HashMap checkStatus=new HashMap();
    	checkStatus.put(0, "未報到");
    	checkStatus.put(1, "已報到");
    	
    	context.setAttribute("checkStatus", checkStatus);
    	
    	HashMap spotType=new HashMap();
    	spotType.put(0, "景點");
    	spotType.put(1, "住宿");
    	spotType.put(2, "美食");
    	
    	context.setAttribute("spotType", spotType);
    	
    	
    }


}
