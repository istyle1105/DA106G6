<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.employee.model.*"%>
<%@ page import="com.authority.model.*"%>
<%@ page import="com.member.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
    
<%
	String emp_no=(String)session.getAttribute("emp_no");
	EmployeeService employeeSvc=new EmployeeService();
	EmployeeVO employeeVO=employeeSvc.getOneEmp(emp_no);
	pageContext.setAttribute("employeeVO", employeeVO);	
	
%> 
<%
	AuthorityService authSvc= new AuthorityService();
	List<AuthorityVO> listEmp=authSvc.getOneEmp(emp_no);
	List<String> listtmp=new ArrayList();
	for(AuthorityVO authorityVO : listEmp){
		String fun_no=authorityVO.getFun_no();
		listtmp.add(fun_no);
	}
%>
    
<!DOCTYPE html>
<html>
<head>
	
	<meta charset="UTF-8">
	<title>後台管理頁面</title>
	<link rel="icon" href="<%=request.getContextPath()%>/back-end/img/favicon.png">		
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/back-end/css/bootstrap4_3_1.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/back-end/css/BackEndPageStyle.css">

</head>


<body>

	<%@ include file="/back-end/backendTop.file" %>
	<style>
	    #h1css{
	    	    font-size: 80px;
    			line-height: 1;
    			text-transform: capitalize;
    			color: black;
    			font-weight: 900;
    			margin-bottom: 22px;
    			-webkit-text-stroke: 1px black;
	    }
	    
	    
	</style>	
		<div>
			<h1 id="h1css" style="font-family: '微軟正黑體'; padding-top:150px; ">歡迎登入 Let's Go Trip後台</h1>
		</div>

	<%@ include file="/back-end/backendBottom.file"%>





</body>
</html>