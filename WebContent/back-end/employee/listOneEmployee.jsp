<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.employee.model.*"%>
<%@ page import="com.authority.model.*"%>
<%@ page import="com.function.model.*"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.util.*"%>
<%@ page import="javax.servlet.ServletContext"%>
 
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
<%
	request.setCharacterEncoding("UTF-8");
	//ServletContext context=getServletContext();
	//map =(HashMap)context.getAttribute("empStatus");
	HashMap empStatus=new HashMap();
	empStatus.put("0", "在職");
	empStatus.put("1", "離職");
	pageContext.setAttribute("empStatus", empStatus);	

%>

<html>
<head>
<title>員工資料</title>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/back-end/css/bootstrap4_3_1.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/back-end/css/BackEndPageStyle.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/back-end/css/bootstrap4-toggle.min.css" >
<script src="<%=request.getContextPath()%>/back-end/js/bootstrap4-toggle.min.js"></script>
<script src="<%=request.getContextPath()%>/back-end/js/popper.min.js"></script>
<script src="<%=request.getContextPath()%>/back-end/js/bootstrap.min.js"></script>
<script src="<%=request.getContextPath()%>/back-end/js/jquery-1.12.4.min.js"></script>
</head>
<style>
.adddiv{
position: absolute;
top: 5%;
left: 22%;
}

</style>

<body>
<%@ include file="/back-end/backendTop.file" %>

<div>
<div class="col-10 "style="display:flex; margin:10px 10px;">
	<div >
		<h3 style="display:inline-block;"><%=employeeVO.getEmp_name()%></h3><p style="display:inline-block;">員工基本資料</p>
	</div>
	<div style="margin-left:10px; ">
		<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/employee/employee.do">
			<button type="submit" class="btn btn-dark">修改個人資料</button>
			<input type="hidden" name="emp_no"  value="${employeeVO.emp_no}">
			<input type="hidden" name="action"	value="getIndi_Update">
		</FORM>	
	</div>
</div>
<div class="d-flex flex-row bd-highlight mb-3 mt-0 col-10">
<div class="p-2 bd-highlight">
	<img height="330" onerror="javascript:this.src='<%=request.getContextPath()%>/NoData/no_photo.jpg'" src="data:image/png;base64,${employeeVO.emp_photo}">
</div>
<div class="p-2 bd-highlight">
<table class="table table-hover table-dark col-12">
	<tr>
		<td>員工編號</td>
		<td><%=employeeVO.getEmp_no() %></td>
	</tr>
	<tr>
		<td>員工狀態</td>
		<td>
			<c:forEach var="status" items="${empStatus}">
			${employeeVO.emp_status==status.key ? status.value : ''}
			</c:forEach>
		</td>
		
	</tr>
	<tr>
		<td>員工帳號</td>
		<td><%=employeeVO.getEmp_id()%></td>
	</tr>
<%-- 	<tr>
		<td>員工密碼</td>
		<td><%=employeeVO.getEmp_psw()%></td>
	</tr>
--%>	
	<tr>
		<td>員工姓名</td>
		<td><%=employeeVO.getEmp_name()%></td>
	</tr>
	<tr>
		<td>員工手機</td>
		<td><%=employeeVO.getEmp_cellphone()%></td>
	</tr>
	<tr>
		<td>員工信箱</td>
		<td><%=employeeVO.getEmp_email()%></td>
	</tr>
<jsp:useBean id="authoritySvc" scope="page" class="com.authority.model.AuthorityService" />
<jsp:useBean id="functionSvc" scope="page" class="com.function.model.FunctionService" />
<%
	AuthorityVO authorityVO = new AuthorityVO();
	List<AuthorityVO> list = new ArrayList<AuthorityVO>();
	list = authoritySvc.getOneEmp(employeeVO.getEmp_no());
	pageContext.setAttribute("list",list);
	
%>			
	<tr>
		<td>員工權限</td>
		<td>
		<c:forEach var="functionVO" items="${functionSvc.all}" > 
			<label>
				<input type="checkbox" name="fun_no" value="${functionVO.fun_no}" 
					<c:forEach var="authorityVO" items="${list}"> 
						${(authorityVO.fun_no== functionVO.fun_no) ? 'checked' : ''}
					</c:forEach>
					 onclick="return false">${functionVO.fun_name}</label>

		</c:forEach> 	
		</td>
	</tr>
</table>
</div>
</div>
</div>
	<%@ include file="/back-end/backendBottom.file"%>
</body>
</html>