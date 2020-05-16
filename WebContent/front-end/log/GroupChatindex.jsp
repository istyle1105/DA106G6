<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
    
<%@ page import="com.member.model.*"%>
<%@ page import="com.log.model.*"%>

<%
	String mem_no=(String)session.getAttribute("mem_no");
	MemberService Membersvc=new MemberService();
	MemberVO memberVO=null;
	memberVO=Membersvc.getOneMember(mem_no);
	pageContext.setAttribute("memberVO", memberVO);	
%>  
    
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>Chat Room</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<link rel="stylesheet" href="css/styles1.css" type="text/css" />
</head>
<body onload="connect();" onunload="disconnect();">
<script src="<%=request.getContextPath()%>/front-end/js/jquery-1.12.4.min.js"></script>
	<h1 id ="customerserviceid">E0001</h1>
	<h2 id ="memberno">${memberVO.m_name}</h2>
	
	<h3 id="statusOutput" class="statusOutput"></h3>
	<textarea id="messagesArea" class="panel message-area" readonly></textarea>
	<div class="panel input-area">
		<input id="message" class="text-field"  type="text" placeholder="Message" onkeydown="if (event.keyCode == 13) sendMessage();" /> 
		<input type="submit" id="sendMessage" class="button" value="Send" onclick="sendMessage();" /> 
		<input type="button" id="connect" class="button" value="Connect" onclick="connect();" /> 
		<input type="button" id="disconnect" class="button" value="Disconnect" onclick="disconnect();" />
	</div>
</body>
<script type="text/javascript">
var memberno =$("#memberno").text();
var customerserviceid = $("#customerserviceid").text();
var MyPoint = "/GroupChat";
var host = window.location.host;
var path = window.location.pathname;
var webCtx = path.substring(0, path.indexOf('/', 1));
var endPointURL = "ws://" + window.location.host + webCtx + MyPoint+"/"+memberno+"/"+customerserviceid;
//var endPointURL = "ws://localhost:8081/IBM_WebSocket1_ChatB/MyEchoServer/peter/309";

var statusOutput = document.getElementById("statusOutput");
var webSocket;

function connect() {
	// 建立 websocket 物件
	webSocket = new WebSocket(endPointURL);
	
	webSocket.onopen = function(event) {
		updateStatus("WebSocket 成功連線");
		document.getElementById('sendMessage').disabled = false;
		document.getElementById('connect').disabled = true;
		document.getElementById('disconnect').disabled = false;
	};

	webSocket.onmessage = function(event) {
		var messagesArea = document.getElementById("messagesArea");
        var jsonObj = JSON.parse(event.data);
        console.log(messageobj );
        if(jsonObj.action === "history"){
        	var messagestr = JSON.parse(jsonObj.message);
            for (i = 0; i < messagestr.length; i++) {
            	var messageobj = JSON.parse(messagestr[i]);
            	var memberno =messageobj.memberno;
                var message =messageobj.message;
            	var inside = memberno+":"+message+ "\r\n";
		        messagesArea.value = messagesArea.value + inside;
       		}
       	}else{
			var memberno = jsonObj.memberno;
			var message = jsonObj.message;
			var inside = memberno+":"+message+ "\r\n";
			messagesArea.value = messagesArea.value + inside;
   		}
        	
		messagesArea.scrollTop = messagesArea.scrollHeight;
	};

	webSocket.onclose = function(event) {
		updateStatus("WebSocket 已離線");
	};
}


function sendMessage() {
   
    
    var inputMessage = document.getElementById("message");
    var message = inputMessage.value.trim();
    
    if (message === ""){
        alert ("訊息請勿空白!");
        inputMessage.focus();	
    }else{
        var jsonObj = {"memberno" : memberno, "message" : message};
        webSocket.send(JSON.stringify(jsonObj));
        inputMessage.value = "";
        inputMessage.focus();
    }
}


function disconnect () {
	webSocket.close();
	document.getElementById('sendMessage').disabled = true;
	document.getElementById('connect').disabled = false;
	document.getElementById('disconnect').disabled = true;
}


function updateStatus(newStatus) {
	statusOutput.innerHTML = newStatus;
}

</script>

</html>