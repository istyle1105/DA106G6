<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.log.model.*"%>
<%@ page import="java.util.*" %>


<%@ page import="com.employee.model.*"%>
<%@ page import="com.authority.model.*"%>
    
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







<% LogVO logvo = (LogVO) request.getAttribute("logvo") ;%>


<!DOCTYPE html>
<html>
<head>

<style>
.content img{
width:750px;
}
</style>

<meta charset="BIG5">

<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/back-end/css/bootstrap4_3_1.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/back-end/css/BackEndPageStyle.css">

<title>Insert title here</title>
</head>
<body style="background-color:#5B5B5B">

<%@ include file="/back-end/backendTop.file" %>

<h2  align="center"><b>��x�ԲӸ��</b> <span class="badge badge-secondary">�޲z�̭���</span></h2>

<div style="display: flex; flex-direction: row; justify-content: center;">
<table class="table table-hover table-dark col-8">
  <thead>
    <tr>
      <th scope="col">#</th>
      <th scope="col">��x�s��</th>
      <th scope="col">�|���s��</th>
      <th scope="col">��x���D</th>
      <th scope="col">��s�ɶ�</th>
      <th scope="col">��x���A</th>
      <th scope="col">�Q���ü�</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <th scope="row">1</th>
      <td>${logvo.log_id}</td>
      <td>${logvo.mem_no}</td>
      <td>${logvo.log_title}</td>
      <td><fmt:formatDate value="${logvo.l_time}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
      <c:set var="val" value="${logvo.l_status}"/>
	  <td>${l_status[val]}</td>
	  <td>${logvo.l_favorited}</td>
    </tr>
  </tbody>
</table>
</div>

<div class="content" style="display: flex; flex-direction: row; justify-content: center;">
<table class="table table-hover table-dark col-8">
	<thead>
  	  <tr><th scope="col" >#</th><th scope="col">��x���e</th></tr>
  </thead>
  <tbody>
  	  <tr><th scope="row">1</th><td>${logvo.log_con}</td></tr>
  </tbody>
</table>
</div>

<div style="display: flex; flex-direction: row; justify-content: center;">
<a href="newback.jsp"><button id="back" class="btn btn-dark btn-sm">Back</button></a>
</div>

<!--   �d�Źj�} -->
<div style="height:30px"></div> 
<!--   �d�Źj�} -->





<%@ include file="/back-end/backendBottom.file" %>

</body>
</html>