<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.employee.model.*"%>
<%@ page import="com.function.model.*"%>
<%@ page import="com.authority.model.*"%>
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
<%	
	request.setCharacterEncoding("UTF-8");	
	EmployeeVO employeeVO2= (EmployeeVO)request.getAttribute("employeeVO2");

%> 
    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>後台管理頁面</title>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/back-end/css/bootstrap4_3_1.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/back-end/css/BackEndPageStyle.css">


<script>

window.onload = function () {
	var alert='${errorMsgs}';
	console.log("alert="+alert);
	console.log("alert.len="+alert.length);
	console.log("alert.type="+typeof alert);
	var err=document.getElementById("err");
	if(alert=='[]'){
		$("#err").css("display","none");
	}
	$("#btn").click(function(){
		if(alert!=null){
			$("#err").css("display","")
		}
	});

}


</script>

<style>
.adddiv{
position: absolute;
top: 5%;
left: 22%;
}

</style>
</head>
<body>
	<%@ include file="/back-end/backendTop.file" %>
	

<div class="col-6 adddiv" style="background-color:white;">

<div id="err" class="alert alert-info alert-dismissible fade show" role="alert">
  <c:if test="${not empty errorMsgs}">
		<font style="color:red">請修正以下錯誤:</font>
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
				<li style="color:red">${message}</li>
				</c:forEach>
			</ul>
	</c:if>
</div>

<Form method="post" action="<%=request.getContextPath()%>/employee/employee.do" name="form1" enctype="multipart/form-data">
<fieldset class="border border-top border-dark p-2 rounded">
<legend class="w-auto">員工基本資料修改</legend>

	<div class="form-row">
		<div class="form-group col-md-6">
			<label for="inputpsw">員工密碼</label>
			<input type="password" class="form-control" id="inputpsw" name="emp_psw" value="<%=employeeVO2.getEmp_psw()%>" >
		</div>
		<div class="form-group col-md-6">
			<label for="inputName">員工姓名</label>
			<input type="text" class="form-control" id="inputName" name="emp_name" value="<%=employeeVO2.getEmp_name()%>" >
		</div>
	</div>
	<div class="form-row">
		<div class="form-group col-md-6">
			<label for="inputPhone">員工手機</label>
			<input type="text" class="form-control" id="inputPhone" name="emp_cellphone" value="<%=employeeVO2.getEmp_cellphone()%>" >
		</div>
		<div class="form-group col-md-6">
			<label for="inputMail">員工信箱</label>
			<input type="email" class="form-control" id="inputMail" name="emp_email" value="<%=employeeVO2.getEmp_email()%>" >
		</div>
	</div>
	<div class="form-group">
			<label>員工頭像</label>
			<div id="putImg" align="center">
				<img id='oimg' width="200" onerror="javascript:this.src='<%=request.getContextPath()%>/NoData/no_photo.jpg'" src="data:image/png;base64,${employeeVO2.emp_photo}">
			</div>	
			<div align="center">
			<input type="file" name="emp_photo" accept="image/gif, image/jpeg, image/png" 
				name="emp_photo" class="form-control-file" id="exampleFormControlFile1"  
				onchange="loadFile(event)"/>
			</div>
	</div>

	<input type="hidden" name="action" value="updateEmp">
	<input type="hidden" name="emp_no" value="<%=employeeVO2.getEmp_no()%>">

	<button type="submit" class="btn btn-dark">送出</button>
	<button type="button" class="btn btn-dark" onclick="location.href='<%=request.getContextPath()%>/back-end/employee/listOneEmployee.jsp'">取消</button>
</fieldset>
</Form>
</div>	
	<script type="text/javascript">
	var loadFile = function(event){
		var reader = new FileReader();
		reader.onload = function(){
			var putImg = document.getElementById('putImg');//為DIV的ID，用來存放圖片的
			putImg.innerHTML = "<img id ='preview' width='200'>"; //生成一個img標籤
			document.getElementById("preview").src = reader.result;//將圖片路徑讀進src
		}
		reader.readAsDataURL(event.target.files[0]);
	}

	</script>	
	<%@ include file="/back-end/backendBottom.file"%>
  	
</body>
</html>