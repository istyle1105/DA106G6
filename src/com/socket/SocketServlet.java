package com.socket;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Servlet implementation class SocketServlet
 */
public class SocketServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SocketServlet() {
        super();
    }
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String action = req.getParameter("action");
		System.out.println(action);
		if("getHistoryMsgs".equals(action)) {
			String sender = req.getParameter("sender");
			String receiver = req.getParameter("receiver");
			List<String> historyMsgs = JedisHandleMessage.getHistoryMsg(sender, receiver);
			
			res.setContentType("text/plain");
			res.setCharacterEncoding("UTF-8");
			PrintWriter out = res.getWriter();
			out.write(new JSONArray(historyMsgs).toString());
			out.flush();
			out.close();
		}
		if("setListHtmlBackup".equals(action)) {
			String listHtmlBackup = req.getParameter("listHtmlBackup");
			req.getSession().setAttribute("listHtmlBackup", listHtmlBackup);
		}
		
	}

}
