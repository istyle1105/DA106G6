<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.sql.*"%>
    
<%
	Class.forName("oracle.jdbc.driver.OracleDriver");
	String connUrl="jdbc:oracle:thin:@localhost:1521:XE";
	Connection conn=DriverManager.getConnection(connUrl,"DA106G6","123456");

	String emp_no=request.getParameter("test");
	String qryStmt="Select fun_no from Authority where emp_no="+ emp_no;
	
	PreparedStatement stmt=conn.prepareStatement(qryStmt);
	ResultSet rs=stmt.executeQuery();
	
	String str="";
	
	while(rs.next())
		str+=(str=="")? rs.getString("fun_no") : ","+rs.getString("fun_no");
	System.out.println(str);
	out.print(str);
	conn.close();		
%>
