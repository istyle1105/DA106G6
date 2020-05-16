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

<h2  align="center"><b>日誌詳細資料</b> <span class="badge badge-secondary">管理者頁面</span></h2>

<div style="display: flex; flex-direction: row; justify-content: center;">
<table class="table table-hover table-dark col-8">
  <thead>
    <tr>
      <th scope="col">#</th>
      <th scope="col">日誌編號</th>
      <th scope="col">會員編號</th>
      <th scope="col">日誌標題</th>
      <th scope="col">更新時間</th>
      <th scope="col">日誌狀態</th>
      <th scope="col">被收藏數</th>
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
  	  <tr><th scope="col" >#</th><th scope="col">日誌內容</th></tr>
  </thead>
  <tbody>
  	  <tr><th scope="row">1</th><td>${logvo.log_con}</td></tr>
  </tbody>
</table>
</div>

<div style="display: flex; flex-direction: row; justify-content: center;">
<a href="newback.jsp"><button id="back" class="btn btn-dark btn-sm">Back</button></a>
</div>

<!--   留空隔開 -->
<div style="height:30px"></div> 
<!--   留空隔開 -->





<%@ include file="/back-end/backendBottom.file" %>

</body>
</html>