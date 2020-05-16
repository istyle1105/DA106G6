<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*"%>
<%@ page import="com.employee.model.*"%>
<%@ page import="com.authority.model.*"%>
<%@ page import="javax.servlet.ServletContext"%>


<%
	String emp_no=(String)session.getAttribute("emp_no");
	EmployeeService employeeSvc=new EmployeeService();
	EmployeeVO employeeVO=employeeSvc.getOneEmp(emp_no);
	pageContext.setAttribute("employeeVO", employeeVO);	
	
	List<EmployeeVO> list = employeeSvc.getAll();
    pageContext.setAttribute("list",list); 

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
	HashMap<Integer, String> empStatus=new HashMap();	
	ServletContext context=getServletContext();
	empStatus =(HashMap<Integer, String>)context.getAttribute("empStatus");
// 	empStatus.put("0", "在職");
// 	empStatus.put("1", "離職");
	pageContext.setAttribute("empStatus", empStatus);

%>

<html>
<head>
<title>所有員工資料 - listAllEmployee.jsp</title>


<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/back-end/css/bootstrap4_3_1.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/back-end/css/BackEndPageStyle.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/back-end/css/bootstrap4-toggle.min.css" >
<script src="<%=request.getContextPath()%>/back-end/js/jquery-1.12.4.min.js"></script>
<script src="<%=request.getContextPath()%>/back-end/js/bootstrap4-toggle.min.js"></script>
<script src="<%=request.getContextPath()%>/back-end/js/popper.min.js"></script>
<script src="<%=request.getContextPath()%>/back-end/js/bootstrap.min.js"></script>


<style type="text/css">
.oneAuth{
	display:none;
}
table{
	vertical-align: middle !important;

}


</style>

</head>
<body>
	<%@ include file="/back-end/backendTop.file" %>
<div class="adddiv" >
<table>
	<tr><td>
		 <a href="<%=request.getContextPath()%>/back-end/employee/addEmployee.jsp"><button type="button" class="btn btn-dark">新增員工</button></a>
		 <h3>所有員工資料</h3>
	</td></tr>
</table>
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
<div id="table" style="marign:10px 20px;">
	<%@ include file="page1.file" %>
<table class="table table-hover table-dark table-bordered" style="width:960px;">
	<thead>
	<tr>
		<th scope="col">員工狀態</th>
		<th scope="col">員工編號</th>
		<th scope="col">員工帳號</th>
<%-- 		<th scope="col">員工密碼</th>--%>
		<th scope="col">姓名</th>
		<th scope="col">手機</th>
		<th scope="col">email</th>
<%--		<th scope="col">照片</th>--%>
		<th scope="col">修改</th>
<%-- 		<th>刪除</th>--%>
	</tr>
	 </thead>

	<tbody>
	<c:forEach var="empVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
	<tr ${(empVO.emp_no==param.emp_noUpdata) ? 'style="color:yellow;"':''}>
		
		<td>
			<c:forEach var="status" items="${empStatus}">
			${empVO.emp_status==status.key ? status.value : ''}
			</c:forEach>
		</td>
		<td>${empVO.emp_no}</td>
		<td>${empVO.emp_id}</td>
<%--		<td>${employeeVO.emp_psw}</td>--%>
		<td>${empVO.emp_name}</td>
		<td>${empVO.emp_cellphone}</td>
		<td>${empVO.emp_email}</td>
<%--		<td><img width="50" onerror="javascript:this.src='/DA106G6/NoData/no_photo.jpg'" src="data:image/png;base64,${employeeVO.emp_photo}"></td>
--%>
		<td>
			<div style="display:inline-block;">
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/employee/employee.do" style="margin-bottom: 0px;">
			     
			     
			     <input type="hidden" name="emp_no"  value="${empVO.emp_no}">
			     <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>">
			     <input type="hidden" name="whichPage"	value="<%=whichPage%>"> 
			     <input type="hidden" name="action"	value="getOne_For_Update">
			     <button type="submit" class="btn btn-light btn-sm">修改</button>
			  </FORM>
			  </div>
			  <div style="margin-top:5px; display:inline-block;">
			  	<button class="send btn btn-light btn-sm">顯示權限</button>
			  </div>
			     
		</td>		
	</tr>
	<jsp:useBean id="functionSvc" scope="page" class="com.function.model.FunctionService" />
	<jsp:useBean id="authoritySvc" scope="page" class="com.authority.model.AuthorityService" />
		
	<tr class="oneAuth">
		<FORM  id="subAuth" METHOD="POST" ACTION="<%=request.getContextPath()%>/authority/authority.do" style="margin-bottom: 0px;">
		<td colspan="6">
		<c:forEach var="functionVO" items="${functionSvc.all}" > 
			<label>
				<input id="my-input" type="checkbox" name="fun_no" value="${functionVO.fun_no}"
				<c:forEach var="authorityVO" items="${authoritySvc.getOneEmp(empVO.emp_no)}">
					${(authorityVO.fun_no== functionVO.fun_no) ? 'checked' : ''}
				</c:forEach>
				<%-- data-toggle="toggle" data-onstyle="light" data-offstyle="dark"
				data-style="border"	data-size="xs"--%>
				>${functionVO.fun_name}　
			</label>
		</c:forEach>
		</td>
		<td> 
			     <input type="hidden" name="emp_no"  value="${empVO.emp_no}">
			     <input type="hidden" name="action"	value="change">
			     <button type="submit" class="btn btn-light btn-sm">修改權限</button>
		</td>
		</FORM>	
	</tr>
	
	</c:forEach>
	 </tbody>
</table>
</div>

<%@ include file="page2.file"%>
</div>



<script>
$(".send").bind('click', function (event) { //event:事件物件
	$(this).parent().parent().parent().next().toggle();
	event.stopPropagation(); //停止事件冒泡
	});
</script>
	<%@ include file="/back-end/backendBottom.file"%>
</body>
</html>