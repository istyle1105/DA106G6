<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.tour_detail.model.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
	Tour_detailVO tour_detailVO = (Tour_detailVO) request.getAttribute("tour_detailVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>
<jsp:useBean id="tourSvc" class="com.tour.model.TourService"/>
<jsp:useBean id="spotSvc" class="com.spot.model.SpotService"/>
<html>
<head>
<title>行程細節資料 - listOneTour_detail.jsp</title>

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
		 <h3>行程細節資料 - listOneTour_detail.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/front-end/tour_detail/select_page.jsp"><img src="<%=request.getContextPath()%>/NoData/null2.jpg" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>行程細節編號</th>
		<th>行程名稱</th>
		<th>景點名稱</th>
		<th>開始時間</th>
		<th>停留時間</th>
		<th>活動描述</th>
		<th>活動圖片</th>
	</tr>
	<tr>
		<td>${tour_detailVO.tour_detail_id}</td>
		<td>${tourSvc.getOneTour(tour_detailVO.tour_id).tour_name}</td>
		<td>${spotSvc.getOneSpot(tour_detailVO.spot_id).spot_name}</td>
		<td><fmt:formatDate value='${tour_detailVO.start_time}' pattern='yyyy-MM-dd HH:mm'/></td>
		<td>${tour_detailVO.stay_time}分鐘</td>
		<td>${tour_detailVO.act_descrip}</td>
		<td><img src='<%=request.getContextPath()%>/DBGifReader4Tour_detail?tour_detail_id=${tour_detailVO.tour_detail_id}'></td>
	</tr>
</table>

</body>
</html>