<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.notification.model.*"%>
<%
	NotificationVO notificationVO=(NotificationVO)request.getAttribute("notificationVO");

%>

<!DOCTYPE html>
<html>
<head>
<title>單篇通知</title>
</head>
<body>
<div>
	<h3 style="color:black;"><%=notificationVO.getNote_content() %></h3>
</div>

<!-- <table class="table"> -->
<!--   <tbody> -->
<!--     <tr> -->
<%--       <th scope="row"><%=notificationVO.getNote_content() %></th> --%>
<!--     </tr> -->
<!--   </tbody> -->
<!-- </table> -->

</body>
</html>