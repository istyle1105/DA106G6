<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.logreport.model.*"%>
<%@ page import="com.log.model.*"%>
<%@ page import="java.util.*"%>


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




<% Log_reportVO log_reportvo = (Log_reportVO) request.getAttribute("log_reportvo");%>




<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">

<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/back-end/css/bootstrap4_3_1.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/back-end/css/BackEndPageStyle.css">

<title>Insert title here</title>
<style type="text/css">
.form{
display:flex;
vertical-align:middle;

}
.form-check{
margin-right:100px;
padding-right:10px;
}
</style>
</head>
<body style="background-color:#5B5B5B">
<%@ include file="/back-end/backendTop.file" %>

<div style="width:100%;text-align:center">
  <%-- 錯誤表列 --%>
 <c:if test="${not empty errorMsgs}">
  <ul>
   <c:forEach var="message" items="${errorMsgs}">
    <c:set var="errors" value="${message}" scope="page"></c:set>
    <script >
     var message = "${errors}";
     alert(message);
    </script>
   </c:forEach>
  </ul>
 </c:if>
</div>


<h2  align="center"><b>檢舉修改管理</b><span class="badge badge-secondary">管理者頁面</span></h2>


<div style="display: flex; flex-direction: row; justify-content: center;">
<table class="table table-hover table-dark col-8">
  <thead>
    <tr>
      <th scope="col" 	width="150px" style="text-align:center;">審核狀態</th>
    </tr>
    <tr>
      <th scope="col" style="text-align:center;">檢舉編號</th>
      <td>${log_reportvo.l_re_id}</td>
    </tr>
    <tr>
      <th scope="col" style="text-align:center;">會員編號</th>
      <td>${log_reportvo.mem_no}</td>
    </tr>
    <tr>
      <th scope="col" style="text-align:center;">日誌編號</th>
      <td>${log_reportvo.log_id}</td>
    </tr>
    <tr>
      <th scope="col" style="text-align:center;" >檢舉原因</th>
      <td>${log_reportvo.l_re_reason}</td>
    </tr>
    <tr>
      <th scope="col"  style="text-align:center;">日誌狀態</th>
      <td>
      	  <FORM METHOD="post" ACTION="log_report.do" name="form1" class="form">
<!--       	  <input type="radio" name="l_re_status" value="1" class="form-check-input" type="radio" name="exampleRadios" id="exampleRadios1" >維持上架狀態<br> -->
<!-- 		  <input type="radio" name="l_re_status" value="2" class="form-check-input" type="radio" name="exampleRadios" id="exampleRadios2" >下架日誌<br> -->
		  <div class="form-check">
  			<input class="form-check-input" type="radio" name="l_re_status" id="exampleRadios1" value="0">
  			<label class="form-check-label" for="exampleRadios1">
    		上架日誌
  			</label>
		  </div>
		  <div class="form-check">
		   <input class="form-check-input" type="radio" name="l_re_status" id="exampleRadios2" value="1">
		   <label class="form-check-label" for="exampleRadios2">
		         下架日誌
		   </label>
		  </div>
		  
		  <input type="hidden" name="action" value="update">
		  <input type="hidden" name="l_re_id" value="${log_reportvo.l_re_id}">
		  <input type="hidden" name="mem_no" value="${log_reportvo.mem_no}">
		  <input type="hidden" name="log_id" value="${log_reportvo.log_id}">
		  <input type="hidden" name="l_re_reason" value="${log_reportvo.l_re_reason}">
		  <input type="submit" class="btn btn-light btn-sm" value="送出修改"></FORM>
      </td>
    </tr>
  </thead>
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