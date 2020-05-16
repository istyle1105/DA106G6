<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>後台管理頁面</title>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/back-end/css/Reset.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/back-end/css/AdmLogin.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/sweetalert2.min.css">
</head>
<body>

	<div id="login-button">
		<img src="img/login-w-icon.png">
		</img>
	</div>

	<div id="container">

		<h1>Log In</h1>
		<span class="close-btn">
			<img src="img/circle_close_delete.png"></img>
		</span>

		<form method="POST" action="<%=request.getContextPath()%>/employee/employee.do">
			<input type="text" name="emp_id" size="20" placeholder="管理員 ID" required>
			<input type="password" name="emp_psw" size="20"  placeholder="Password" required>
			<input type="hidden" name="action" value="adminLogin">
			<input type="submit" value="送出" style="width: 224px;" class="submitStyle">
		</form>
		<input type="hidden" id="error" value="帳號密碼有誤" readonly>

	</div>



	
  		<!-- jQuery -->
	<script type="text/javascript" src="<%=request.getContextPath()%>/back-end/js/jquery-1.12.4.min.js"></script>
	<!-- GSAP(GreenSock Animation Platform) 的 TweenMax -->
	<script type="text/javascript" src="<%=request.getContextPath()%>/back-end/js/TweenMax.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/back-end/js/AdmLoginJs.js"></script>
	<script src="<%=request.getContextPath()%>/front-end/js/sweetalert2.min.js"></script>
<script>
 var error='${param.error}';
 
 if(error=='false'){
	 $("#login-button").css("display","none");
	 $("#container").css("display","block");
	 $("#error").attr("type","text");
	 console.log("error="+error);
 }
 var state='${param.state}'
	    if(state=='noAuth'){
	    	 Swal.fire({
	   		  title: '已離職無法登入',
	   		  icon: 'error',
	   		  showConfirmButton: false,
	   		  showCloseButton: true,
	   		  timer: 2000
	   		})
	    }else if(state=='logout'){
	    	 Swal.fire({
	      		  title: '登出成功',
	      		  icon: 'success',
	      		  showConfirmButton: false,
	      		  showCloseButton: true,
	      		  timer: 1500
	      		})
	       }
</script>  	
</body>
</html>