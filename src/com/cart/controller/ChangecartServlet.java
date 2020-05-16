package com.cart.controller;
import java.util.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.authority.model.AuthorityVO;
import com.cart.model.CartService;
import com.product.model.*;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 500 * 1024 * 1024, maxRequestSize = 500 * 5 * 1024 * 1024)
public class ChangecartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}
    
	public void doPost(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException,IOException {
		
		req.setCharacterEncoding("UTF-8");

		CartService cartSVC = new CartService();
		ProductService productSVC = new ProductService();
		
		HttpSession session = req.getSession();
		String mem_no = (String) session.getAttribute("mem_no");
		String action = req.getParameter("action");

		JSONObject output = new JSONObject();		
		List<ProductVO> RedisBuylist = (List<ProductVO>) cartSVC.getAllP_idByMem_no(mem_no);

		//增加購物車商品
		if (action.equals("AddQUANTITY")) {
			System.out.println("進AddQUANTITY");

			String p_id = req.getParameter("p_id");
			int index = Integer.parseInt(req.getParameter("index"));
			int p_pr = Integer.parseInt(req.getParameter("p_pr"));
			
			CartService cartSvc=new CartService();
			int quantity=cartSvc.getValueByP_id(mem_no, p_id);
			
			
			System.out.println("會員編號=" + mem_no +",商品編號=" + p_id + ",數量=" + (quantity+1) + ",取得(單項)=" + index);
			cartSVC.replace(mem_no, p_id, quantity+1);
			
			try {
				output.put("amount",p_pr*(quantity+1));
			} catch (JSONException e) {
				e.printStackTrace();
			}

		    res.setContentType("text/plain");
		    PrintWriter out = res.getWriter();
		    System.out.println(output);
		    out.write(output.toString());
		    out.flush();
		    out.close();
		}
		
		//減少購物車商品
		else if (action.equals("MinusQUANTITY")) {
			System.out.println("進MinusQUANTITY");

			String p_id = req.getParameter("p_id");
			int index = Integer.parseInt(req.getParameter("index"));
			int p_pr = Integer.parseInt(req.getParameter("p_pr"));
			CartService cartSvc=new CartService();
			int quantity=cartSvc.getValueByP_id(mem_no, p_id);
			//刪除數量0的商品
			if(quantity==1) {
				System.out.println("delete");
				cartSVC.deleteCart(mem_no, p_id, quantity);	
				try {
					output.put("index", index);
				} catch (JSONException e) {
					e.printStackTrace();
				}
			//減少購物車商品
			}else {
				System.out.println("會員編號=" + mem_no +",商品編號=" + p_id + ",數量=" + (quantity-1) + ",取得(單項)=" + index);
				cartSVC.replace(mem_no, p_id, quantity-1);
				
				try {
					output.put("amount",p_pr*(quantity-1));
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
				
		    res.setContentType("text/plain");
		    PrintWriter out = res.getWriter();
		    System.out.println(output);
		    out.write(output.toString());
		    out.flush();
		    out.close();
	    
		} else if (action.equals("BOXCHECKED")) {
			System.out.println("進BOXCHECKED");
			
			String boxchecked = req.getParameter("boxchecked");
			System.out.println("boxchecked=" + boxchecked);
			int amount = Integer.parseInt(req.getParameter("amount"));
			System.out.println("amount=" + amount);

			if(boxchecked.equals("true")) {
				System.out.println("進true");

				try {
					output.put("amount", amount);
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}else {
				System.out.println("進false");
				try {
					output.put("amount", 0);
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}

		    res.setContentType("text/plain");
		    PrintWriter out = res.getWriter();
		    System.out.println(output);
		    out.write(output.toString());
		    out.flush();
		    out.close();
		}
	}	
}
