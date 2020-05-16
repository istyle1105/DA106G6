<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.sql.*"%>
    
 <% 
	 Class.forName("oracle.jdbc.driver.OracleDriver");
	 String connUrl = "jdbc:oracle:thin:@localhost:1521:ORCLCDB";
	 Connection conn = DriverManager.getConnection(connUrl, "C##DA106G6", "123456");
	
	 String qryStmt = "SELECT MEM_NO FROM MEMBER";
	 PreparedStatement stmt = conn.prepareStatement(qryStmt);
	 ResultSet rs = stmt.executeQuery();
	 
	 String str ="";
	 while(rs.next()){
	  str += (str == "") ? rs.getString("MEM_NO") : "," + rs.getString("MEM_NO");
	 }
	 out.print(str);
	 conn.close();
%>