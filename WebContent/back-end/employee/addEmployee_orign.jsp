<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.employee.model.*"%>

<%
  EmployeeVO employeeVO = (EmployeeVO) request.getAttribute("employeeVO");
%>
<%-- 判斷是否是空值嗎 request.scope--%>
<%--  employeeVO==null --%>
<%--   <%= empVO.getEmpno() %>   一開始沒有取到物件，這裡會送你500 NullPointerException--%>

<html>
<head>
<title>員工資料新增</title>
</head>
<body>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
<FORM METHOD="post" ACTION="employee.do" name="form1" enctype="multipart/form-data">

<table class="table table-bordered table-hover">
	<tr>
		<td>員工帳號:</td>
		<td><input type="TEXT" name="emp_id" size="30" placeholder="請輸入帳號"
			 value="<%= (employeeVO==null)? "" : employeeVO.getEmp_id()%>" /></td>
	</tr>
	<tr>
		<td>員工密碼</td>
		<td><input type="password" name="emp_psw" size="30" placeholder="請輸入密碼"
			value="<%= (employeeVO==null) ? "" : employeeVO.getEmp_psw()%>">
		</td>
	</tr>
	<tr>
		<td>員工姓名:</td>
		<td><input type="TEXT" name="emp_name" size="30" placeholder="請輸入姓名"
			 value="<%= (employeeVO==null)? "" : employeeVO.getEmp_name()%>" /></td>
	</tr>
	<tr>
		<td>員工手機:</td>
		<td><input type="TEXT" name="emp_cellphone" size="30" placeholder="請輸入手機"
			 value="<%= (employeeVO==null)? "" : employeeVO.getEmp_cellphone()%>" /></td>
	</tr>
	<tr>
		<td>員工信箱:</td>
		<td><input type="email" name="emp_email" size="30" placeholder="請輸入信箱"
			 value="<%= (employeeVO==null)? "" : employeeVO.getEmp_email()%>" /></td>
	</tr>
		<tr>
		<td>上傳員工大頭照</td>
		<td>
        	<input type="file" name="emp_photo" onchange="loadFile(event)">
			<br>
			<div id="output" ></div>
		</td>
	</tr>
 
<jsp:useBean id="functionSvc" scope="page" class="com.function.model.FunctionService" />
	<tr>
		<td>員工權限</td>
		<td>
		<c:forEach var="functionVO" items="${functionSvc.all}" > 
			<label>
				<input type="checkbox" name="fun_no" value="${functionVO.fun_no}">${functionVO.fun_name}
				</label>

		</c:forEach> 
		
		</td>
	</tr>

</table>
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增">
</FORM>
	<script type="text/javascript">
	var loadFile = function(event){
		var reader = new FileReader();
		reader.onload = function(){
			var output = document.getElementById('output');
			output.innerHTML = "<img width='100' id ='preview'>";
			document.getElementById("preview").src = reader.result;
		};
		reader.readAsDataURL(event.target.files[0]);
	}

	</script>
</body>
</html>