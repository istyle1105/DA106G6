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




<%Log_reportService log_reportSvc = new Log_reportService();
List<Log_reportVO> list = log_reportSvc.getAll();
pageContext.setAttribute("list",list); %>

<%LogVO logvo = (LogVO) request.getAttribute("logvo"); %>





<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<script src="<%=request.getContextPath()%>/front-end/js/jquery-1.12.4.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/back-end/css/bootstrap4_3_1.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/back-end/css/BackEndPageStyle.css">


<title>Let's Go Trip - BACK</title>
<style>
.swal2-html-container img{
max-width:450px;

}
</style>
</head>
<body style="background-color:#5B5B5B">

<h1 id ="customerserviceid">Notice</h1>
<h2 id ="memberno">${employeeVO.emp_name}</h2>

<%@ include file="/back-end/backendTop.file" %>

<h2  align="center"><b>檢舉日誌管理</b><span class="badge badge-secondary">管理者頁面</span></h2>

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
	

	
	
	
<div style="display: flex; flex-direction: row; justify-content: center;">
	<div style="width:400px;text-align:center;">
	<form style="justify-content: center;"  class="form-inline" METHOD="post" ACTION="log_report.do" >
	  <div class="form-group mx-sm-3 mb-2">
	    <input type="text" class="form-control" id="inputPassword2"  name="l_re_id" placeholder="檢舉編號">
	    <input type="hidden" name="action" value="getOne_For_Display">
	  </div>
	  <button  type="submit" class="btn btn-dark btn-sm">Send</button>
	</form>
	</div>

	<div style="width:400px">
	<form  class="form-inline" METHOD="post" ACTION="log_report.do" >
	  <div class="form-group mx-sm-3 mb-2">
	    <input type="text" class="form-control" id="inputPassword2"  name="log_id" placeholder="日誌編號">
	    <input type="hidden" name="action" value="getOne_Log">
	  </div>
	  <button type="submit" class="btn btn-dark btn-sm">Send</button>
	</form>
	</div>
</div>

<div style="margin:auto 215px">
<%@ include file="page1.file" %>
</div>

<div style="display: flex;  justify-content: center;">
<table class="table table-hover table-dark col-8">
  <thead>
    <tr>
      <th scope="col" >#</th>
      <th scope="col">檢舉編號</th>
      <th scope="col">會員編號</th>
      <th scope="col">日誌編號</th>
      <th scope="col">檢舉原因</th>
      <th scope="col">檢舉狀態</th>
      <th scope="col">更新</th>
    </tr>
  </thead>
  <tbody>
 <%!int i=1 ;%>
 <c:forEach var="log_reportVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
    <tr>
      <th scope="row"><b>*</b></th>
      <td>${log_reportVO.l_re_id}</td>
      <td>${log_reportVO.mem_no}</td>
      <td><a href="#" onclick="init('${log_reportVO.log_id}')">${log_reportVO.log_id}</a></td>
      <td>${log_reportVO.l_re_reason}</td>
      <c:set var="val" value="${log_reportVO.l_re_status}"/>
			<td>${l_re_status[val]}</td>
	  <td>
			  <FORM METHOD="post" ACTION="log_report.do" style="margin-bottom: 0px;">
			     <input class="btn btn-light btn-sm" type="submit" value="更新">
			     <input type="hidden" name="l_re_id"  value="${log_reportVO.l_re_id}">
			     <input type="hidden" name="action" value="getOne_For_Update"></FORM>
	  </td>
    </tr>
</c:forEach>
  </tbody>
</table>
</div>


<hr>

	
<div style="margin:auto 215px">
<%@ include file="page2.file" %>
</div>


<script type="text/javascript">

var memberno =$("#memberno").text();
var customerserviceid = $("#customerserviceid").text();
var MyPoint = "/oneonone";
var host = window.location.host;
var path = window.location.pathname;
var webCtx = path.substring(0, path.indexOf('/', 1));
var endPointURL = "ws://" + window.location.host + webCtx + MyPoint+"/"+memberno+"/"+customerserviceid;
var webSocket;

window.onload=function (){
	// 建立 websocket 物件
	webSocket = new WebSocket(endPointURL);
	
	webSocket.onopen = function(event) {
		console.log("成功連線");
	};

	webSocket.onmessage = function(event) {
		Swal.fire({
			   icon: 'warning',
			   text: '您一則有新檢舉!',
			 })
	};

	webSocket.onclose = function(event) {
		console.log("＝＝");
	};
}

window.onunload=function(){
	webSocket.close();	
}
window.onclose=function(){
	webSocket.close();	
}


function disconnect () {
	webSocket.close();
	
}






function init(data) {
	  $.ajax({
	   type : "GET",
	   url : "log.do",
	   data : {
	    "action" : "getOne_For_Display_Ajax","log_id" : data
	   },
	   dataType : "json",
	   success : function(logVO) {
		   Swal.fire(
					  '日誌標題 :'+logVO.log_title,
					  logVO.log_con
					)
	   },
	   error : function() {
	    alert('Ajax連線異常');
	   }
	  })
	}

</script>



<%@ include file="/back-end/backendBottom.file" %>

</body>
</html>