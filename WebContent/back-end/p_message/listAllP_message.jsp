<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*"%>
<%@ page import="com.p_message.model.*"%>

<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
    P_MessageService p_messageSvc = new P_MessageService();
    List<P_MessageVO> list = p_messageSvc.getAll();
    pageContext.setAttribute("list",list);
%>


<html>
<head>
<title>所有MSG資料 - listAllMessage.jsp</title>

<style>
  table#table-1 {
	background-color: #CCCCFF;
    border: 2px solid black;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

<style>
  table {
	width: 800px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
  #msg {
  	text-align:left;
  }
</style>

</head>
<body bgcolor='white'>

<h4>此頁練習採用 EL 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>所有MSG資料 - listAllMessage.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
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

<table>
	<tr>
		<th>MSG編號</th>
		<th>需求編號</th>
		<th>會員編號</th>
		<th>MSG內容</th>
		<th>MSG時間</th>
		<th>留言板</th>
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="p_messageVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
	
		<tr>
			<td>${p_messageVO.p_msg_no}</td>
			<td>${p_messageVO.de_no}</td>
			<td>${p_messageVO.mem_no}</td>
			<td id="msg">${p_messageVO.p_msg}</td>
			<td><fmt:formatDate value="${p_messageVO.p_msg_time}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/p_message/p_message.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="p_msg_no"  value="${p_messageVO.p_msg_no}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>