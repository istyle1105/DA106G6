package com.util;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class BackLoginFilter implements Filter {

	private FilterConfig config;

	public void init(FilterConfig config) {
		this.config = config;
	}

	public void destroy() {
		config = null;
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws ServletException, IOException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		// 【取得 session】
		HttpSession session = req.getSession();
		// 【從 session 判斷此user是否登入過】
		Object emp_no = session.getAttribute("emp_no");
		if (emp_no == null) {
			session.setAttribute("backLocation", req.getRequestURI());
			System.out.println("backLocation"+req.getRequestURI());
			res.sendRedirect(req.getContextPath() + "/back-end/AdminLogin.jsp");
			return;
		} else {
			chain.doFilter(request, response);
		}
	}
}