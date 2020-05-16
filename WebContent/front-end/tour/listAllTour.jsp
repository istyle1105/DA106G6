<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.tour.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
	TourService tourSvc = new TourService();
    List<TourVO> list = tourSvc.getAll();
    pageContext.setAttribute("list",list);
%>


<html>
<head>
<title>所有行程資料 - listAllTour.jsp</title>

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
  	width:200px;
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
</style>

</head>
<body bgcolor='white'>

<h4>此頁練習採用 EL 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>所有行程資料 - listAllTour.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/front-end/tour/select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
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
<jsp:useBean id="tour_detailSvc" class="com.tour_detail.model.Tour_detailService"/>
<table>
	<tr>
		<th>行程編號</th>
		<th>會員編號</th>
		<th>行程名稱</th>
		<th>行程簡介</th>
		<th>圖片</th>
		<th>上下架</th>
		<th>修改</th>
		<th>刪除</th>
		<th>行程詳情</th>
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="tourVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
				<td>${tourVO.tour_id}</td>
				<td>${tourVO.mem_no}</td>
				<td>${tourVO.tour_name}</td>
				<td>${tourVO.tour_detail}</td>
				<td><img src='<%=request.getContextPath()%>/DBGifReader4Tour?tour_id=${tourVO.tour_id}'></td>
				<td>${tourVO.display}</td>
				<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/tour/tour.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="tour_id"  value="${tourVO.tour_id}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/tour/tour.do" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="tour_id"  value="${tourVO.tour_id}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
			<td>
<!-- 			練習用JSTL設值並應用MV03版的EL呼叫方法方式,取得list的size來設值,將沒有list的size零的送出鍵做disabled的處理 -->
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/tour/tour.do" style="margin-bottom: 0px;">
			  	<c:set var="listsize" value="${tour_detailSvc.getOneTourShowDetail(tourVO.tour_id).size()}"/>
			  	${listsize}筆資料
			     <input type="submit" value="詳情"${(listsize==0)?"disabled":""}>
			     <input type="hidden" name="tour_id"  value="${tourVO.tour_id}">
			     <input type="hidden" name="action" value="getOneTour_Show_Detail"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>