<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.employee.model.*"%>

<%
  EmployeeVO employeeVO = (EmployeeVO) request.getAttribute("employeeVO");
%>
<%-- �P�_�O�_�O�ŭȶ� request.scope--%>
<%--  employeeVO==null --%>
<%--   <%= empVO.getEmpno() %>   �@�}�l�S�����쪫��A�o�̷|�e�A500 NullPointerException--%>

<html>
<head>
<title>���u��Ʒs�W</title>
</head>
<body>

<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
<FORM METHOD="post" ACTION="employee.do" name="form1" enctype="multipart/form-data">

<table class="table table-bordered table-hover">
	<tr>
		<td>���u�b��:</td>
		<td><input type="TEXT" name="emp_id" size="30" placeholder="�п�J�b��"
			 value="<%= (employeeVO==null)? "" : employeeVO.getEmp_id()%>" /></td>
	</tr>
	<tr>
		<td>���u�K�X</td>
		<td><input type="password" name="emp_psw" size="30" placeholder="�п�J�K�X"
			value="<%= (employeeVO==null) ? "" : employeeVO.getEmp_psw()%>">
		</td>
	</tr>
	<tr>
		<td>���u�m�W:</td>
		<td><input type="TEXT" name="emp_name" size="30" placeholder="�п�J�m�W"
			 value="<%= (employeeVO==null)? "" : employeeVO.getEmp_name()%>" /></td>
	</tr>
	<tr>
		<td>���u���:</td>
		<td><input type="TEXT" name="emp_cellphone" size="30" placeholder="�п�J���"
			 value="<%= (employeeVO==null)? "" : employeeVO.getEmp_cellphone()%>" /></td>
	</tr>
	<tr>
		<td>���u�H�c:</td>
		<td><input type="email" name="emp_email" size="30" placeholder="�п�J�H�c"
			 value="<%= (employeeVO==null)? "" : employeeVO.getEmp_email()%>" /></td>
	</tr>
		<tr>
		<td>�W�ǭ��u�j�Y��</td>
		<td>
        	<input type="file" name="emp_photo" onchange="loadFile(event)">
			<br>
			<div id="output" ></div>
		</td>
	</tr>
 
<jsp:useBean id="functionSvc" scope="page" class="com.function.model.FunctionService" />
	<tr>
		<td>���u�v��</td>
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
<input type="submit" value="�e�X�s�W">
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