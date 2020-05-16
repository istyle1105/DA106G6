<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.tour.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
	TourVO tourVO = (TourVO) request.getAttribute("tourVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>

<html>
<head>
<title>員工資料 - listOneTour.jsp</title>

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
		 <h3>行程資料 - ListOneTour.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/front-end/tour/select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>行程編號</th>
		<th>會員編號</th>
		<th>行程名稱</th>
		<th>行程簡介</th>
		<th>上下架</th>
		<th>圖片</th>
	</tr>
	<tr>
		<td>${tourVO.tour_id}</td>
		<td>${tourVO.mem_no}</td>
		<td>${tourVO.tour_name}</td>
		<td>${tourVO.tour_detail}</td>
		<td>${tourVO.display}</td>
		<td><img src='<%=request.getContextPath()%>/DBGifReader4Tour?tour_id=${tourVO.tour_id}'></td>
	</tr>
</table>

</body>
</html>