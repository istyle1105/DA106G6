<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.employee.model.*"%>
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
  EmployeeVO employeeVO2 = (EmployeeVO) request.getAttribute("employeeVO2");
%>
<%-- 判斷是否是空值嗎 request.scope--%>
<%--  employeeVO==null --%>
<%--   <%= empVO.getEmpno() %>   一開始沒有取到物件，這裡會送你500 NullPointerException--%>

<html>
<head>
<title>員工資料新增</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/back-end/css/bootstrap4_3_1.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/back-end/css/BackEndPageStyle.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/back-end/css/bootstrap4-toggle.min.css" >
<script src="<%=request.getContextPath()%>/back-end/js/jquery-1.12.4.min.js"></script>
<script src="<%=request.getContextPath()%>/back-end/js/bootstrap4-toggle.min.js"></script>
<script src="<%=request.getContextPath()%>/back-end/js/popper.min.js"></script>
<script src="<%=request.getContextPath()%>/back-end/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/sweetalert2.min.css">
<script>
window.onload = function () {
	var alert='${errorMsgs}';
	console.log("alert="+alert);
	var err=document.getElementById("err");
	if(alert.length==0){
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
top: 20%;
left: 20%;
}


</style>
</head>
<body>
	<%@ include file="/back-end/backendTop.file" %>
<%-- 錯誤表列 --%>

<div class="col-10 adddiv" >
<div id="err" class="message alert alert-success alert-dismissible col-8">
    <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
    
    <c:if test="${not empty errorMsgs}">
		<font style="color:red">請修正以下錯誤:</font>
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
				<li style="color:red">${message}</li>
				</c:forEach>
			</ul>
	</c:if>
</div>
<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/employee/employee.do" name="form1" enctype="multipart/form-data">
<fieldset class="col-8 border border-top border-dark p-2 rounded">

<legend class="w-auto">員工新增</legend>
	<div class="form-row">
		<div class="form-group col-6">
		 		<label for="inputEmpId">員工帳號</label>
		 		<input type="text" class="form-control" id="inputEmpId" name="emp_id"
		 			value="<%= (employeeVO2==null)? "" : employeeVO2.getEmp_id()%>">
		</div>
		<div class="form-group col-6">
			<label for="inputEmpName">員工姓名</label>
			<input type="text" class="form-control" id="inputEmpName" name="emp_name"
				value="<%= (employeeVO2==null)? "" : employeeVO2.getEmp_name()%>">
		</div>
	</div>
	<div class="form-row">	
		<div class="form-group col-6">
			<label for="inputEmpPhone">員工手機</label>
			<input type="text" class="form-control" id="inputEmpPhone" name="emp_cellphone"
				value="<%= (employeeVO2==null)? "" : employeeVO2.getEmp_cellphone()%>">
		</div>
		<div class="form-group col-6">
			<label for="inputEmpMail">員工信箱</label>
			<input type="text" class="form-control" id="inputEmpMail" name="emp_email"
				value="<%= (employeeVO2==null)? "" : employeeVO2.getEmp_email()%>">
		</div>
	</div>
<jsp:useBean id="functionSvc" scope="page" class="com.function.model.FunctionService" />
  	<div class="form-row">
		<div class="form-group col-12">
		 	<div style="margin-bottom: .5rem">員工權限</div>
			<div class="form-control">
				<c:forEach var="functionVO" items="${functionSvc.all}" > 
			  		<label>
						<input type="checkbox" name="fun_no" value="${functionVO.fun_no}">${functionVO.fun_name}
					</label>
			 	</c:forEach> 
			</div>
		</div>
	</div>
	<div class="form-row">
	  	<div class="form-group col-6">
	    		<label for="inputEmpPhoto">上傳員工大頭照</label>
	    		<input type="file" id="inputEmpPhoto" name="emp_photo"
	    		onchange="loadFile(event)">
	  	 </div>
  	</div>
  	<div class="form-row">
	  	  <div class="form-group col-6">
	    		<div id="output" ></div>
	  	  </div>
	</div>

<input type="hidden" name="action" value="insert">
<button type="submit" class="btn btn-dark">送出新增</button>
<button type="button" class="btn btn-dark magicbtn">神奇小按鈕</button>
</fieldset>
</FORM>
</div>

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
<%@ include file="/back-end/backendBottom.file"%>
<script>
$(".magicbtn").click(function(){
	$("#inputEmpId").val("emp003");
	$("#inputEmpName").val("粒狸");
	$("#inputEmpPhone").val("0912345678");
	$("#inputEmpMail").val("acs099149@gm.ntcu.edu.tw");	
});



$("body").on('focusout','#inputEmpId',function(){
    let inputEmpId = $("#inputEmpId").val().trim();
    if(inputEmpId != ''){
        let params = { "action" : "checkIfIdExist",
                "inputEmpId" : inputEmpId
                };
        $.ajax({
             type: "POST",
             url: '<%=request.getContextPath()%>/employee/employee.do',
             data: params,
             dataType: "json",
             success: function(data){
                 $("#available").remove();
                 if(data.available === "Y"){
//                      $("#inputId").after('<span style="color:#80bdff;" id="available">會員帳號可以用</span>');
						//alert("員工帳號可以用");
						Swal.fire('此員工帳號可使用')
                 }else{
//                      $("#inputId").after('<span style="color:red;"  id="available">會員帳號重複</span>');
                 	 //alert("員工帳號已用過");
                	 Swal.fire({
                		  icon: 'error',
                		  title: '員工帳號已註冊過',
                		})
                 }
             },
             error: function(){ alert('AJAX錯誤！')}
         });
    }
});

</script>

</body>
</html>