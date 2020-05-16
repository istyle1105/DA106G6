<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*"%>
<%@ page import="com.notification.model.*"%>
<%
	List<NotificationVO> list = (List<NotificationVO>)request.getAttribute("list");
 
   
%>


<html>
<head>
<title>所有該會員的通知</title>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
</head>
<body>
<table>
	<tr><td>
		 <h3>所有該會員的通知</h3>
		 <h4><a href="select_page.jsp">回首頁</a></h4>
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

<table border="1px solid black">
	<tr>
		<th>通知編號</th>
		<th>會員編號</th>
		<th>通知內容</th>
		<th>通知日期</th>
		<th>通知狀態</th>
	</tr>
	<%@ include file="page1.file" %>
	<c:forEach var="notificationVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
	<tr>
		<td>${notificationVO.note_no}</td>
		<td>${notificationVO.mem_no}</td>
		<td>${notificationVO.note_content}</td>
		<td>${notificationVO.note_date}</td>
		<td>
			<c:forEach var="status" items="${compStatus}">
			${notificationVO.note_status==status.key ? status.value : ''}
			</c:forEach>
		</td>
	
	</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>