package com.cart.controller;
import java.util.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;
import com.product.model.*;


@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 500 * 1024 * 1024, maxRequestSize = 500 * 5 * 1024 * 1024)
public class ShoppingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException,IOException {
		
		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();
		
		@SuppressWarnings("unchecked")
		List<ProductVO> buylist = (Vector<ProductVO>) session.getAttribute("shoppingcart");
		String action = req.getParameter("action");
		
		if (!action.equals("CHECKOUT")) {
				if(action.equals("deleteSelected")&&req.getParameterValues("checkact")!=null) {
				
				System.out.println("進deleteSelected");
				
				String [] delArray = req.getParameterValues("checkact");
				System.out.println(delArray.length);
				
				for(int i=0; i<delArray.length;i++) {	
					int d = Integer.parseInt(delArray[i]);
					buylist.remove(d);
					System.out.println("刪除(項)=" + d);		
				}
			}
			// 刪除購物車中的商品
			if(action.equals("DELETE")) {
				String del = req.getParameter("del");
				int d = Integer.parseInt(del);
				buylist.remove(d);
			}
			
			//新增商品至購物車中
			else if (action.equals("ADD")) {
				//取得後來新增的商品
				ProductVO aproduct = getProduct(req);
				
				if (buylist == null) {
					buylist = new Vector<ProductVO>(); 
					buylist.add(aproduct);
					
				} else {
					if(buylist.contains(aproduct)){
						ProductVO innerProduct = buylist.get(buylist.indexOf(aproduct));
						innerProduct.setQuantity(innerProduct.getQuantity() + aproduct.getQuantity());
					} else {
						buylist.add(aproduct);
					}
				}
			}
//			for (ProductVO ans : buylist)
//				System.out.println(ans);
			session.setAttribute("shoppingcart", buylist);
			String url = "/front-end/shop/shoppingCart.jsp";
			RequestDispatcher rd = req.getRequestDispatcher(url);
			rd.forward(req, res);
		}
		// 結帳，計算購物車商品價錢總數
		else if (action.equals("CHECKOUT")) {
//			double total = 0;
//			for(int i = 0; i < buylist.size(); i++) {
//				ProductVO order = buylist.get(i);
//				Integer p_pr = order.getP_pr();
//				Integer quantity = order.getQuantity();
//				total += (Math.round(p_pr * quantity));
//			}
			
			double total = buylist.stream()
					.mapToDouble(p -> p.getP_pr()*p.getQuantity())
					.sum();
			
			Integer amount = (int)total;
			System.out.println("amount"+amount);
			req.setAttribute("amount", amount);
			String url = "/front-end/shop/shopCheckout.jsp";
			RequestDispatcher rd = req.getRequestDispatcher(url);
			rd.forward(req, res);
		}
	}
	
	private ProductVO getProduct(HttpServletRequest req) throws IOException, ServletException {
		
		byte[] p_pic = null;
		Part part2 = req.getPart("p_pic");
		InputStream is2 = part2.getInputStream();
		p_pic = new byte[is2.available()];
		is2.read(p_pic);
		is2.close();
		String p_id = req.getParameter("p_id");
		String p_name = req.getParameter("p_name");
		String p_cat = req.getParameter("p_cat");
		Integer p_pr = new Integer(req.getParameter("p_pr").trim());
		String p_spec = req.getParameter("p_spec");
		String p_detail = req.getParameter("p_detail");
		Integer quantity = new Integer(req.getParameter("quantity").trim());
		System.out.println(p_name+p_detail+quantity);
		
		ProductVO product = new ProductVO();
		product.setP_pic(p_pic);
		product.setP_id(p_id);
		product.setP_name(p_name);
		product.setP_cat(p_cat);
		product.setP_pr(p_pr);
		product.setP_spec(p_spec);
		product.setP_detail(p_detail);
		product.setQuantity(quantity);
		return product;
	}
}
