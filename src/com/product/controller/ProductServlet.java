package com.product.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import com.product.model.ProductService;
import com.product.model.ProductVO;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 500 * 1024 * 1024, maxRequestSize = 500 * 5 * 1024 * 1024)
public class ProductServlet extends HttpServlet{
	
	public void doGet(HttpServletRequest req,HttpServletResponse res) 
			throws ServletException, IOException{
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req,HttpServletResponse res) 
			throws ServletException, IOException{
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("p_id");
				if(str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入商品編號");
				}
				// Send the use back to the form, if there were errors
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/product/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				String p_id = null;
				try {
					p_id = new String(str);
				}catch(Exception e) {
					errorMsgs.add("商品編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/product/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				/***************************2.開始查詢資料*****************************************/
				ProductService 	productSvc = new ProductService();
				ProductVO productVO = productSvc.getOneProduct(p_id);
				if(productVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/product/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("productVO", productVO); // 資料庫取出的productVO物件,存入req
				String url = "/back-end/product/listOneProduct.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneProduct.jsp
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理*************************************/
			}catch(Exception e) {
				errorMsgs.add("無法取得資料"+e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/product/select_page.jsp");
				failureView.forward(req, res);
			}
			
		}
		if ("getOne_For_Update".equals(action)) { // 來自listAllProduct.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL");
			req.setAttribute("requestURL", requestURL);
			String whichPage = req.getParameter("whichPage");
			req.setAttribute("whichPage", whichPage);
			
			try {
				/***************************1.接收請求參數****************************************/
				String p_id = req.getParameter("p_id");
				
				/***************************2.開始查詢資料****************************************/
				ProductService productSvc = new ProductService();
				ProductVO productVO = productSvc.getOneProduct(p_id);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("productVO", productVO);         // 資料庫取出的productVO物件,存入req
				String url = "/back-end/product/update_product_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_product_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				e.getStackTrace();
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/product/listAllProduct.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { 
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL");
			req.setAttribute("requestURL", requestURL);
			String whichPage = req.getParameter("whichPage");
			req.setAttribute("whichPage", whichPage);
			
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String p_name = req.getParameter("p_name");
				String p_nameReg = "^.{1,200}$";
				if (p_name == null || p_name.trim().length() == 0) {
					errorMsgs.add("商品名稱: 請勿空白");
				} else if(!p_name.trim().matches(p_nameReg)) { 
					errorMsgs.add("類別: 請輸入中、英文字母、數字, 且長度必需在1到60字之間");
	            }
				
				String p_cat = req.getParameter("p_cat");
				String p_catReg = "^[(\\u4e00-\\u9fa5)(a-zA-Z0-9)]{1,20}$";
				if (p_cat == null || p_name.trim().length() == 0) {
					errorMsgs.add("類別: 請勿空白");
				} else if(!p_cat.trim().matches(p_catReg)) { 
					errorMsgs.add("類別: 請輸入中、英文字母、數字, 且長度必需在1到6字之間");
	            }

				Integer p_pr = null;
				try {
					p_pr = new Integer(req.getParameter("p_pr").trim());
				} catch (NumberFormatException e) {
					p_pr = 0;
					errorMsgs.add("請填數字.");
				}
				
				String p_spec = req.getParameter("p_spec");
				
				String p_detail = req.getParameter("p_detail");;
//				Part part=req.getPart("p_detail");
//				InputStream is = part.getInputStream();	
//				byte [] byte_p_detail = new byte[is.available()];
//				is.read(byte_p_detail);
//				is.close();
//				
//				Base64.Encoder encoder=Base64.getEncoder();
//				p_detail=encoder.encodeToString(byte_p_detail);
				
				
				byte[] p_pic = null;
				Part part2 = req.getPart("p_pic");
				InputStream is2 = part2.getInputStream();
				p_pic = new byte[is2.available()];
				is2.read(p_pic);
				is2.close();

				Integer p_status = new Integer(req.getParameter("p_status").trim());
	
				String p_id = req.getParameter("p_id");
				
				ProductVO productVO = new ProductVO();
				productVO.setP_name(p_name);
				productVO.setP_cat(p_cat);
				productVO.setP_pr(p_pr);
				productVO.setP_spec(p_spec);
				productVO.setP_detail(p_detail);
				productVO.setP_pic(p_pic);
				productVO.setP_status(p_status);
				productVO.setP_id(p_id);
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("productVO", productVO); // 含有輸入格式錯誤的productVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/product/update_product_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				ProductService productSvc = new ProductService();
				productVO = productSvc.updateProduct(p_name, p_cat, p_pr, p_spec, p_detail, p_pic, p_status, p_id);
				productVO = productSvc.getOneProduct(p_id);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("productVO", productVO); // 資料庫update成功後,正確的的productVO物件,存入req
				String url = requestURL + "?whichPage=" + whichPage;
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneProduct.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				e.getStackTrace();
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/product/update_product_input.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { // 來自addProduct.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String p_name = req.getParameter("p_name");
				String p_nameReg = "^.{1,200}$";
				if (p_name == null || p_name.trim().length() == 0) { 
					errorMsgs.add("商品名稱: 請勿空白");
				}else if(!p_name.trim().matches(p_nameReg)) { 
					errorMsgs.add("類別: 請輸入中、英文字母、數字 , 且長度必需在1到60字之間");
			    }
				
				String p_cat = req.getParameter("p_cat");

				Integer p_pr = null;
				try {
					p_pr = new Integer(req.getParameter("p_pr").trim());
				} catch (NumberFormatException e) {
					p_pr = 0;
					errorMsgs.add("請填數字.");
				}
				
				String p_spec = req.getParameter("p_spec");
				
				String p_detail = req.getParameter("p_detail");;
//				Part part=req.getPart("p_detail");
//				InputStream is = part.getInputStream();	
//				byte [] byte_p_detail = new byte[is.available()];
//				is.read(byte_p_detail);
//				is.close();
//				
//				Base64.Encoder encoder=Base64.getEncoder();
//				p_detail=encoder.encodeToString(byte_p_detail);
								
				byte[] p_pic = null;
				Part part2 = req.getPart("p_pic");
				InputStream is2 = part2.getInputStream();
				p_pic = new byte[is2.available()];
				is2.read(p_pic);
				is2.close();

				Integer p_status = new Integer(req.getParameter("p_status").trim());

				ProductVO productVO = new ProductVO();
				productVO.setP_name(p_name);
				productVO.setP_cat(p_cat);
				productVO.setP_pr(p_pr);
				productVO.setP_spec(p_spec);
				productVO.setP_detail(p_detail);
				productVO.setP_pic(p_pic);
				productVO.setP_status(p_status);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("productVO", productVO); // 含有輸入格式錯誤的productVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/product/addProduct.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				ProductService productSvc = new ProductService();
				productVO = productSvc.addProduct(p_name, p_cat, p_pr, p_spec, p_detail, p_pic, p_status);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/back-end/product/listAllProduct.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllProduct.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/product/addProduct.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if("detailview".equals(action)) { 
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String p_id = req.getParameter("p_id");
				
				/***************************2.開始查詢資料*****************************************/
				ProductService 	productSvc = new ProductService();
				ProductVO productVO = productSvc.getOneProduct(p_id);
	
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("productVO", productVO); // 資料庫取出的productVO物件,存入req
				String url = "/front-end/shop/goodsDetail.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交goodsDetail.jsp
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理*************************************/
			}catch(Exception e) {
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/shop/shopHome.jsp");
				failureView.forward(req, res);
			}
		}
		
		if("catchoice".equals(action)) { // 來自shopHome.jsp的請求
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				System.out.println(req.getParameter("p_cat"));
				String p_cat = req.getParameter("p_cat");
				// Send the use back to the form, if there were errors
				/***************************2.開始查詢資料*****************************************/
				ProductService 	productSvc = new ProductService();
				List<ProductVO> productVO = productSvc.getAllByCat(p_cat);
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("productVO", productVO); // 資料庫取出的productVO物件,存入req
				String url = req.getContextPath() + "/front-end/shop/shopHome.jsp?p_cat=" + p_cat;
				res.sendRedirect(url);
				
				/***************************其他可能的錯誤處理*************************************/
			}catch(Exception e) {
				String url = req.getContextPath() + "/front-end/shop/shopHome.jsp";
				res.sendRedirect(url);
			}
		}
	}
}
