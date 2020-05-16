package com.member.controller;

import java.io.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;

import javax.servlet.http.*;
import javax.sql.DataSource;


public class MemeberId_cardReader extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection con;	
			
	public void doGet(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException {
		
	
		res.setContentType("image/jpg");
		req.setCharacterEncoding("UTF-8");
		ServletOutputStream out=res.getOutputStream();

		
		try {

			Statement stmt=con.createStatement();

			String mem_no=req.getParameter("mem_no").trim();
			ResultSet rs=stmt.executeQuery("Select ID_CARD from MEMBER WHERE MEM_NO='"+mem_no+"'");
			
			
			if(rs.next()) {
				BufferedInputStream in=new BufferedInputStream(rs.getBinaryStream("ID_CARD"));
				byte[] buf=new byte[4*1024];
				int len;
				while((len=in.read(buf))!=-1) {
					out.write(buf, 0, len);
				}
				in.close();
			}else {
				InputStream in=getServletContext().getResourceAsStream("/NoData/no_picture.jpg");
				byte[] b=new byte[in.available()];
				in.read(b);
				out.write(b);
				in.close();				
			}
			
			rs.close();
			stmt.close();
			
			
		} catch (Exception e) {
			InputStream in=getServletContext().getResourceAsStream("/NoData/no_picture.jpg");
			byte[] b=new byte[in.available()];
			in.read(b);
			out.write(b);
			in.close();		
		}
		
	}
	
	public void init() throws ServletException {
		
		
    	try {
			Context ctx = new javax.naming.InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Team6DB");
			con = ds.getConnection();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void destroy() {
		try {
			if (con != null) con.close();
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
	

}
