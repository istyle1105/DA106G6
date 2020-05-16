<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.employee.model.*"%>
<%@ page import="com.authority.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
    
<!DOCTYPE html>
<html>
<head>
	
	<meta charset="UTF-8">
	<title>後台管理頁面</title>
	<link rel="icon" href="<%=request.getContextPath()%>/back-end/img/favicon.png">		
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/back-end/css/bootstrap4_3_1.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/back-end/css/BackEndPageStyle.css">
	<style>
		.backendContent{
			min-height: 100vh;
	        background-image: url('<%=request.getContextPath()%>/back-end/img/back_bg.jpg');
	        background-color: rgba(50,50,50,0.5);
	        background-repeat: no-repeat;
	        background-size: 80%;
	        background-attachment: fixed; 
	        background-position: right;
/* 	        border:5px solid #111; */
	    }
	</style>
</head>
<body>

	<%@ include file="/back-end/backendTop.file" %>
	
	
	

	<%@ include file="/back-end/backendBottom.file"%>


<!-- <script> -->
// //peter/309  動態去給
// var MyPoint = "/MyEchoServer/"+'${employeeVO.emp_no}';
// var host = window.location.host;
// var path = window.location.pathname;
// var webCtx = path.substring(0, path.indexOf('/', 1));
// var endPointURL = "ws://" + window.location.host + webCtx + MyPoint;
// //var endPointURL = "ws://localhost:8081/IBM_WebSocket1_ChatB/MyEchoServer/peter/309";

// var statusOutput = document.getElementById("statusOutput");
// var webSocket;

// window.onload=function (){
	
// 	// 建立 websocket 物件
// 	webSocket = new WebSocket(endPointURL);
// 	console.log(endPointURL);
// 	webSocket.onopen = function(event) {
// 		console.log("成功連線");
// 		updateStatus("WebSocket 成功連線");
// 	};

// 	webSocket.onmessage = function(event) {
// 		var messagesArea = document.getElementById("messagesArea");
//         var jsonObj = JSON.parse(event.data);
//         var message = jsonObj.userName + ": " + jsonObj.message + "\r\n";
//         messagesArea.value = messagesArea.value + message;
//         messagesArea.scrollTop = messagesArea.scrollHeight;
// 	};

// 	webSocket.onclose = function(event) {
// 		updateStatus("WebSocket 已離線");
// 	};

	
	
	
// 	}

	
	
// // 	var inputUserName = document.getElementById("userName");
// // 	inputUserName.focus();
	
// // 	function sendMessage() {
// // 	    var userName = inputUserName.value.trim();
// // 	    if (userName === ""){
// // 	        alert ("使用者名稱請勿空白!");
// // 	        inputUserName.focus();	
// // 			return;
// // 	    }
	    
// // 	    var inputMessage = document.getElementById("message");
// // 	    var message = inputMessage.value.trim();
	    
// // 	    if (message === ""){
// // 	        alert ("訊息請勿空白!");
// // 	        inputMessage.focus();	
// // 	    }else{
// // 	        var jsonObj = {"userName" : userName, "message" : message};
// // 	        webSocket.send(JSON.stringify(jsonObj));
// // 	        inputMessage.value = "";
// // 	        inputMessage.focus();
// // 	    }
// // 	}

	
// 	function disconnect () {
// 		webSocket.close();
// 		document.getElementById('sendMessage').disabled = true;
// 		document.getElementById('connect').disabled = false;
// 		document.getElementById('disconnect').disabled = true;
// 	}

	
// 	function updateStatus(newStatus) {
// 		//statusOutput.innerHTML = newStatus;
// 	}
    
<!-- </script> -->

</body>
</html>