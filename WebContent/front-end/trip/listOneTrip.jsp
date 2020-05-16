<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.trip.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
  TripVO tripVO = (TripVO) request.getAttribute("tripVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>

<html>
<head>
<title>揪團資料 - listOneTrip.jsp</title>

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
		 <h3>揪團資料 - ListOneTrip.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/front-end/trip/select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>揪團編號</th>
		<th>會員編號</th>
		<th>行程編號</th>
		<th>天數</th>
		<th>預估金額</th>
		<th>起始日期</th>
		<th>結束日期</th>
		<th>報名開始日期</th>
		<th>報名截止日期</th>
		<th>成團人數</th>
		<th>目前人數</th>
		<th>人數上限</th>
		<th>成團狀態</th>
		<th>上架狀態</th>
		<th>最後修改時間</th>
	</tr>
	<tr>
			<td>${tripVO.trip_id}</td>
			<td>${tripVO.mem_no}</td>
			<td>${tripVO.tour_id}</td>
			<td>${tripVO.days}</td>
			<td>${tripVO.trip_price}</td>
			<td>${tripVO.first_date}</td>
			<td>${tripVO.last_date}</td>
			<td>${tripVO.reg_start}</td>
			<td>${tripVO.reg_deadline}</td>
			<td>${tripVO.mem_amount}</td>
			<td>${tripVO.current_mem}</td>
			<td>${tripVO.mem_limited}</td>
			<td>${tripVO.trip_status}</td>
			<td>${tripVO.tour_status}</td>
			<td>${tripVO.last_mod_time}</td>
	</tr>
</table>

</body>
</html>