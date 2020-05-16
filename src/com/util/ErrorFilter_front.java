package com.util;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.wallet.model.*;
import com.member.model.*;

public class ErrorFilter_front implements Filter {

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
		
		System.out.println("error濾器");
		System.out.println("Status"+res.getStatus());
	    if (res.getStatus() == 404) {
	    	res.sendRedirect(req.getContextPath() + "/front-end/homepage.jsp");
	    	return;
	    }else if (res.getStatus() == 500) {
	    	res.sendRedirect(req.getContextPath() + "/front-end/homepage.jsp");
	    	return;
	    }else {
			chain.doFilter(request, response);
		}
	}
}