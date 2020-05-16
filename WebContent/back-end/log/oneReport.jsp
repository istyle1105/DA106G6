<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.log.model.*"%>
<%@ page import="com.logreport.model.*"%>
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




<%Log_reportVO log_reportVO = (Log_reportVO) request.getAttribute("log_reportVO"); %>
<% String update = request.getParameter("update") ;%>
<%= update %>



<%String log_id = log_reportVO.getLog_id() ;%>
<%
    LogService logSvc = new LogService();
    LogVO logVO = logSvc.getOneLog(log_id);
    pageContext.setAttribute("logVO",logVO);
    
%>
    
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

<h2  align="center"><b>檢舉資料管理 </b><span class="badge badge-secondary">管理者頁面</span></h2>

<div style="display: flex; flex-direction: row; justify-content: center;">
<table class="table table-hover table-dark col-8" >
  <thead>
    <tr>
      <th scope="col">#</th>
      <th scope="col">檢舉編號</th>
      <th scope="col">會員編號</th>
      <th scope="col">日誌編號</th>
      <th scope="col">檢舉原因</th>
      <th scope="col">檢舉狀態</th>
      <th scope="col">修改</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <th scope="row">1</th>
      <td>${log_reportVO.l_re_id}</td>
      <td>${log_reportVO.mem_no}</td>
      <td>${log_reportVO.log_id}</td>
      <td>${log_reportVO.l_re_reason}</td>
      <c:set var="val" value="${log_reportVO.l_re_status}"/>
			<td>${l_re_status[val]}</td>
	  <td>
			  <FORM METHOD="post" ACTION="log_report.do" style="margin-bottom: 0px;">
			     <input class="btn btn-light btn-sm" type="submit" value="修改">
			     <input type="hidden" name="l_re_id"  value="${log_reportVO.l_re_id}">
			     <input type="hidden" name="action" value="getOne_For_Update"></FORM>
	  </td>
    </tr>
  </tbody>
</table>
</div>

<div class="content" style="display: flex; flex-direction: row; justify-content: center;">
<table class="table table-hover table-dark col-8">
  <thead>
    <tr>
      <th scope="col" width="50%">日誌內容</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>${logVO.log_con}</td>
    </tr>
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

<script>
var update='${param.update}';
if (update == 'true'){
	Swal.fire(
			'',  
			'修改成功!',
			'success'
			)
}
</script>

</body>
</html>