package com.cart.controller;
import java.util.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import com.authority.model.AuthorityVO;
import com.cart.model.CartService;
import com.product.model.*;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 500 * 1024 * 1024, maxRequestSize = 500 * 5 * 1024 * 1024)
public class ShoppingRedisServlet extends HttpServlet {
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

		if (!action.equals("CHECKOUT")) {

			//非會員(訪客)
			if(mem_no == null) {
				@SuppressWarnings("unchecked")
				List<ProductVO> buylist = (Vector<ProductVO>) session.getAttribute("buylist");
				// 刪除購物車中被選的商品
				if(action.equals("deleteSelected")&&req.getParameterValues("checkact")!=null) {
					
					System.out.println("進deleteSelected");
					
					String [] delArray = req.getParameterValues("checkact");
					for(int i= (delArray.length-1) ; i >= 0 ;i--) {
						int d = Integer.parseInt(delArray[i]);
						System.out.println("刪除(多項)=" + d);
						buylist.remove(d);
					}
				}
				// 刪除單個商品
				if(action.equals("DELETE")) {
					System.out.println("進單品刪除");
					String del = req.getParameter("del");
					int d = Integer.parseInt(del);
					buylist.remove(d);
					System.out.println("刪除(單項)=" + d);
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
				session.setAttribute("buylist", buylist);
				String url = "/front-end/shop/shoppingCart.jsp";
				RequestDispatcher rd = req.getRequestDispatcher(url);
				rd.forward(req, res);
				
			//會員	
			} else {
				
				//登入後將非會員購物車加入
				if(action.equals("toMemCart")) {
					System.out.println("非會員登入後轉入會員購物車");
					
					@SuppressWarnings("unchecked")
					List<ProductVO> buylist = (Vector<ProductVO>) session.getAttribute("buylist");
					List<ProductVO> RedisBuylist = (List<ProductVO>) cartSVC.getAllP_idByMem_no(mem_no);
					if(buylist==null) {
						res.sendRedirect(req.getContextPath()+"/front-end/shop/shoppingRedisCart.jsp");
						return;
					} else {
						for(int i = 0;i < buylist.size();i++) {
							
							if(RedisBuylist.contains(buylist.get(i))){
								System.out.println("ADD新增加量");
								String p_id = buylist.get(i).getP_id();
								Integer quantity = new Integer(buylist.get(i).getQuantity());
								System.out.println("會員編號=" + mem_no +",商品編號=" + p_id + ",數量=" + quantity);
								cartSVC.updateCart(mem_no, p_id, quantity);
							
							} else {
								System.out.println("ADD else新增商品");
								String p_id = buylist.get(i).getP_id();
								Integer quantity = new Integer(buylist.get(i).getQuantity());
								System.out.println("會員編號=" + mem_no +",商品編號=" + p_id + ",數量=" + quantity);
								cartSVC.insertCart(mem_no, p_id, quantity);
							}	
							
						}
						session.removeAttribute("buylist");
						res.sendRedirect(req.getContextPath()+"/front-end/shop/shoppingRedisCart.jsp");
						return;
					}
				}
				
				List<ProductVO> RedisBuylist = (List<ProductVO>) cartSVC.getAllP_idByMem_no(mem_no);
				// 刪除購物車中被選的商品
				if(action.equals("deleteSelected")&&req.getParameterValues("checkact")!=null) {
					
					System.out.println("進deleteSelected");
					
					String [] delArray = req.getParameterValues("checkact");
					String [] p_idArray = req.getParameterValues("p_id");
					String [] quantityArray = req.getParameterValues("quantity");
					System.out.println(delArray.length);
					System.out.println(p_idArray.length);
					System.out.println(quantityArray.length);
					
					for(int i=0; i<delArray.length;i++) {	
						
						int d = Integer.parseInt(delArray[i]);
						String p_id = p_idArray[d];
						int quantity = Integer.parseInt(quantityArray[d]);
						System.out.println("會員編號=" + mem_no +",商品編號=" + p_id + ",數量=" + quantity + ",刪除(多項)=" + d);
						cartSVC.deleteCart(mem_no, p_id, quantity);
						
					}
				}
				// 刪除單個商品
				else if(action.equals("DELETE")) {
					System.out.println("進delete");
					String del = req.getParameter("del");
					int d = Integer.parseInt(del);
					String p_id = req.getParameter("p_id");
					Integer quantity = new Integer(req.getParameter("quantity"));
					System.out.println("會員編號=" + mem_no +",商品編號=" + p_id + ",數量=" + quantity + ",刪除(單項)=" + d);
					cartSVC.deleteCart(mem_no, p_id, quantity);	
				}

				//新增商品至購物車中
				else if (action.equals("ADD")) {
					System.out.println("進ADD");
					ProductVO aproduct = getProduct(req);
					//取得後來新增的商品
					if (RedisBuylist == null) {
						System.out.println("ADD新增購物車");
						String p_id = aproduct.getP_id();
						Integer quantity = new Integer(aproduct.getQuantity());
						System.out.println("會員編號=" + mem_no +",商品編號=" + p_id + ",數量=" + quantity);
						cartSVC.insertCart(mem_no, p_id, quantity);
					
					} else {
						
						if(RedisBuylist.contains(aproduct)){
							System.out.println("ADD新增加量");
							String p_id = aproduct.getP_id();
							Integer quantity = new Integer(aproduct.getQuantity());
							System.out.println("會員編號=" + mem_no +",商品編號=" + p_id + ",數量=" + quantity);
							cartSVC.updateCart(mem_no, p_id, quantity);
						
						} else {
							System.out.println("ADD else新增商品");
							String p_id = aproduct.getP_id();
							Integer quantity = new Integer(aproduct.getQuantity());
							System.out.println("會員編號=" + mem_no +",商品編號=" + p_id + ",數量=" + quantity);
							cartSVC.insertCart(mem_no, p_id, quantity);
						}	
					}
				}
	
				req.setAttribute("buylist", (List<ProductVO>)cartSVC.getAllP_idByMem_no(mem_no));
				String url = "/front-end/shop/shoppingRedisCart.jsp";
				RequestDispatcher rd = req.getRequestDispatcher(url);
				rd.forward(req, res);
			}
		}
		// 結帳，計算購物車商品價錢總數
		else if (action.equals("CHECKOUT")) {
			System.out.println("進CHECKOUT");
			//非會員
			if(mem_no ==null) {
				
				System.out.println("非會員跳轉會員登入");
				String loginLocation=req.getParameter("loginLocation");
				System.out.println("loginLocation="+loginLocation);
				String URL = req.getContextPath()+"/front-end/member/Login.jsp?requestURI="+loginLocation;
				res.sendRedirect(URL);
				return;
				
			//會員
			} else if(req.getParameterValues("checkact")!=null) {
				System.out.println("會員進結帳");
				
				List<ProductVO> buylist = new ArrayList<ProductVO>();
				
				
				String [] delArray = req.getParameterValues("checkact");
				String [] p_idArray = req.getParameterValues("p_id");
				String [] quantityArray = req.getParameterValues("quantity");
				String [] p_prArray = req.getParameterValues("p_pr");
				System.out.println(delArray.length);
				System.out.println(p_idArray.length);
				System.out.println(quantityArray.length);
				System.out.println(p_prArray.length);
				
				double total = 0;

				for(int i=0; i<delArray.length;i++) {	
					ProductVO aproduct = new ProductVO();
					int d = Integer.parseInt(delArray[i]);
					String p_id = p_idArray[d];
					aproduct.setP_id(p_id);
					Integer quantity = cartSVC.getValueByP_id(mem_no, p_id);
					aproduct.setQuantity(quantity);
					Integer p_pr = Integer.parseInt(p_prArray[d]);
					aproduct.setP_pr(p_pr);
					buylist.add(aproduct);

					System.out.println("會員編號=" + mem_no +",商品編號=" + p_id + ",數量=" + quantity +",價格=" + p_pr + ",計算(多項)=" + d);

//					Integer p_pr = productSVC.getOneProduct(order.getP_id()).getP_pr();
//					Integer quantity = cartSVC.getValueByP_id(mem_no, order.getP_id());
					total += (Math.round(p_pr * quantity));
				}
				
				for(ProductVO proVO:buylist) {
					System.out.println(" P_id:"+proVO.getP_id());
				}
				
				Integer amount = (int)total;
				System.out.println("amount="+amount);
				req.setAttribute("amount", amount);
				session.setAttribute("buylist", buylist);
				String url = "/front-end/shop/shopCheckout.jsp";
				RequestDispatcher rd = req.getRequestDispatcher(url);
				rd.forward(req, res);
				
			} else {
				System.out.println("沒勾選，回原畫面");
				req.setAttribute("buylist", (List<ProductVO>)cartSVC.getAllP_idByMem_no(mem_no));
				String url = "/front-end/shop/shoppingRedisCart.jsp";
				RequestDispatcher rd = req.getRequestDispatcher(url);
				rd.forward(req, res);
				
			}
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
