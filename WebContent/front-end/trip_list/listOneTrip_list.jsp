<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.trip_list.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
  Trip_listVO trip_listVO = (Trip_listVO) request.getAttribute("trip_listVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>

<html>
<head>
<title>報名揪團資料 - listOneTrip_list.jsp</title>

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
  td>img{
  	width:300px;
  }
</style>

<style>
  table {
	width: 600px;
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
</style>

</head>
<body bgcolor='white'>

<h4>此頁暫練習採用 Script 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>報名揪團資料 - ListOneTrip_list.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/front-end/trip_list/select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>揪團編號</th>
		<th>會員編號</th>
		<th>報名時間</th>
		<th>報到狀態</th>
		<th>評價等級</th>
		<th>評價內容</th>
		<th>評價修改時間</th>
	</tr>
	<tr>
			<td>${trip_listVO.trip_id}</td>
			<td>${trip_listVO.mem_no}</td>
			<td>${trip_listVO.reg_time}</td>
			<td>${trip_listVO.check_status}</td>
			<td>${trip_listVO.rate}</td>
			<td>${trip_listVO.comment_content}</td>
			<td>${trip_listVO.comment_time}</td>
	</tr>
</table>

</body>
</html>