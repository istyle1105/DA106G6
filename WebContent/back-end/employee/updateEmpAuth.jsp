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
	
	request.setCharacterEncoding("UTF-8");	
	EmployeeVO employeeVO2= (EmployeeVO)request.getAttribute("employeeVO2");

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
</head>
<body>
	<%@ include file="/back-end/backendTop.file" %>
	




<div class="col-8" style="margin-top:10px; background-color:white;">

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
<legend class="w-auto">員工資料修改</legend>
	<div class="form-group">
		員工狀態
		<label for="emp_status0">在職</label><input id="emp_status0" type="radio" name="emp_status" value="0" ${(employeeVO2.emp_status==0) ?'checked' : ''}>	
		<label for="emp_status1">　離職</label><input id="emp_status1" type="radio" name="emp_status" value="1" ${(employeeVO2.emp_status==1) ?'checked' : ''}>
	</div>
	<div class="form-row">
		<div class="form-group col-md-6">
			<label for="inputName">員工姓名</label>
			<input type="text" class="form-control" id="inputName" name="emp_name" value="<%=employeeVO2.getEmp_name()%>" >
		</div>
		<div class="form-group col-md-6">
			<label for="inputpsw">員工密碼</label>
			<input type="password" class="form-control" id="inputpsw" name="emp_psw" value="<%=employeeVO2.getEmp_psw()%>" >
		</div>
	</div>
	<div class="form-row">
		<div class="form-group col-md-6">
			<label for="inputPhone">員工手機</label>
			<input type="text" class="form-control" id="inputPhone" name="emp_cellphone" value="<%=employeeVO2.getEmp_cellphone()%>" >
		</div>
		<div class="form-group col-md-6">
			<label for="inputMail">員工信箱</label>
			<input type="text" class="form-control" id="inputMail" name="emp_email" value="<%=employeeVO2.getEmp_email()%>" >
		</div>
	</div>

<jsp:useBean id="authoritySvc" scope="page" class="com.authority.model.AuthorityService" />
<jsp:useBean id="functionSvc" scope="page" class="com.function.model.FunctionService" />
<%
	AuthorityVO authorityVO = new AuthorityVO();
	List<AuthorityVO> list = new ArrayList<AuthorityVO>();
	list = authoritySvc.getOneEmp(employeeVO2.getEmp_no());
	pageContext.setAttribute("list",list);
	
%>	
	<div class="form-group">
		員工權限：
		<div class="form-control">
		<c:forEach var="functionVO" items="${functionSvc.all}" > 
			<label>
				<input type="checkbox" name="fun_no" value="${functionVO.fun_no}" 
					<c:forEach var="authorityVO" items="${list}"> 
						${(authorityVO.fun_no== functionVO.fun_no) ? 'checked' : ''}
					</c:forEach> >${functionVO.fun_name}
			</label>
		</c:forEach> 
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
	<input type="hidden" name="action" value="updateEmpAuth">
	<input type="hidden" name="emp_no" value="<%=employeeVO2.getEmp_no()%>">
	<input type="hidden" name="requestURL" value="<%=request.getAttribute("requestURL")%>">
	<input type="hidden" name="whichPage"  value="<%=request.getAttribute("whichPage")%>"> 
	<button type="submit" class="btn btn-dark">送出</button>
	<button type="button" class="btn btn-dark" onclick="location.href='<%=request.getContextPath()%>/back-end/employee/listAllEmployee.jsp'">取消</button>
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