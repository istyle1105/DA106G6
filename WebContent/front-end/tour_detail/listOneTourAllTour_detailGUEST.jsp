<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.tour_detail.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>
<%@ page import="com.member.model.*"%>
<%
	String mem_no=(String)session.getAttribute("mem_no");
	MemberService Membersvc=new MemberService();
	MemberVO memberVO=null;
	memberVO=Membersvc.getOneMember(mem_no);
	pageContext.setAttribute("memberVO", memberVO);	
%> 
<%
    List<Tour_detailVO> list = (List)request.getAttribute("list");
    String tour_id = (String)request.getAttribute("tour_id");
    if(list==null){
    	list = (List)session.getAttribute("list");
    	tour_id = (String)session.getAttribute("tour_id");
    }
    session.setAttribute("list",list);
    session.setAttribute("tour_id",tour_id);
    String trip_id = request.getParameter("trip_id");
    if(trip_id==null){
    	trip_id = (String)session.getAttribute("trip_id");
    }
    session.setAttribute("trip_id",trip_id);
%>

<jsp:useBean id="tourSvc" class="com.tour.model.TourService"/>
<jsp:useBean id="spotSvc" class="com.spot.model.SpotService"/>
<jsp:useBean id="tripSvc" class="com.trip.model.TripService"/>
<c:set var="tripVO" value="${tripSvc.getOneTrip(trip_id)}"/>
<html>
<head>
<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Let's Go Trip</title>
    <link rel="icon" href="<%=request.getContextPath()%>/front-end/img/favicon.png">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/bootstrap.min.css">
    <!-- animate CSS -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/animate.css">
    <!-- owl carousel CSS -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/owl.carousel.min.css">
    <!-- themify CSS -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/themify-icons.css">
    <!-- flaticon CSS -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/flaticon.css">
    <!-- fontawesome CSS -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/fontawesome/css/all.min.css">
    <!-- magnific CSS -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/magnific-popup.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/gijgo.min.css">
    <!-- niceselect CSS -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/nice-select.css">
    <!-- slick CSS -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/slick.css">
    <!-- style CSS -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/style.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/sweetalert2.min.css">

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
  .pic{
  	width:250px;
  }
</style>

</head>
<body bgcolor='white'>

<%@ include file="/front-end/TopHeader.file" %>
<div id="content" class="container">

<!-- <table id="table-1"> -->
<!-- 	<tr><td> -->
<!-- 		 <h3>一個行程的細節資料 - listOneTourAllTour_detail.jsp</h3> -->
<!-- 	</td></tr> -->
<!-- </table> -->

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
<table class="col">
<tr>
<th rowspan="4"><img class="pic" src='<%=request.getContextPath()%>/DBGifReader4Tour?tour_id=${tripVO.tour_id}'></th>
<th><h4>行程: ${tourSvc.getOneTour(tour_id).tour_name}</h4></th>
</tr>
<tr>
<th><h4>行程簡介: ${tourSvc.getOneTour(tour_id).tour_detail}</h4></th>
</tr>
<tr>
<th><h4>時間: ${tripVO.first_date}  ~  ${tripVO.last_date}     (${tripVO.days}日遊)</h4></th>
</tr>
<tr>
<th><h4>預估金額 :NT. ${tripVO.trip_price} </h4></th>
</tr>
</table>
<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/tour_detail/tour_detail.do" style="margin-bottom: 0px;">
			     <input type="hidden" name="tour_id"  value="${tour_id}">
			     <input type="hidden" name="tour_name"  value="${tourSvc.getOneTour(tour_id).tour_name}">
			     <input type="submit" value="地圖顯示 " <%=list.size()==0?"disabled":"" %>>
			     <input type="hidden" name="action" value="showTourDetailInMap"></FORM>
<%-- <a href="<%=request.getContextPath()%>/front-end/tour_detail/listOneTourOverview.jsp?tour_id=${tour_id}">景點簡表 </a><br> --%>
<input type="button" value='景點簡表' onclick="location.href='<%=request.getContextPath()%>/front-end/tour_detail/listOneTourOverview.jsp?tour_id=${tour_id}'">
<br>
<input type="button" ${(tripVO.mem_no==mem_no)?"disabled value='我是團主'":"value='參加揪團'" } 
				 onclick="location.href='<%=request.getContextPath()%>/trip_list/trip_list.do?trip_id=${tripVO.trip_id}&action=getOne_For_Insert'">
<br>

<table class="table table-striped table-hover">
	<tr>
		<th>開始時間</th>
		<th>停留時間</th>
		<th>景點名稱</th>
		<th>活動描述</th>
		<th>活動圖片</th>
<!-- 		<th>修改</th> -->
<!-- 		<th>刪除</th> -->
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="tour_detailVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td><fmt:formatDate value='${tour_detailVO.start_time}' pattern='yyyy-MM-dd HH:mm'/></td>
			<td>${tour_detailVO.stay_time}分鐘</td>
			<td>${spotSvc.getOneSpot(tour_detailVO.spot_id).spot_name}<br>
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/spot/spot.do" style="margin-bottom: 0px;">
			     <input type="submit" value="詳情">
			     <input type="hidden" name="spot_id"  value="${tour_detailVO.spot_id}">
			     <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>">
			      <input type="hidden" name="whichPage"	value="<%=whichPage%>">
			     <input type="hidden" name="action"	value="getOne_For_Display"></FORM></td>
			<td>${tour_detailVO.act_descrip}</td>
			<td><img src='<%=request.getContextPath()%>/DBGifReader4Tour_detail?tour_detail_id=${tour_detailVO.tour_detail_id}'></td>
			
<!-- 			<td> -->
<%-- 			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/tour_detail/tour_detail.do" style="margin-bottom: 0px;"> --%>
<!-- 			     <input type="submit" value="修改"> -->
<%-- 			     <input type="hidden" name="tour_detail_id"  value="${tour_detailVO.tour_detail_id}"> --%>
<%-- 			     <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"> --%>
<%-- 			     <input type="hidden" name="whichPage"	value="<%=whichPage%>"> --%>
<!-- 			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM> -->
<!-- 			</td> -->
<!-- 			<td> -->
<%-- 			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/tour_detail/tour_detail.do" style="margin-bottom: 0px;"> --%>
<!-- 			     <input type="submit" value="刪除"> -->
<%-- 			     <input type="hidden" name="tour_detail_id"  value="${tour_detailVO.tour_detail_id}"> --%>
<%-- 			     <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"> --%>
<%-- 			     <input type="hidden" name="whichPage"	value="<%=whichPage%>"> --%>
<!-- 			     <input type="hidden" name="action" value="delete"></FORM> -->
<!-- 			</td> -->
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>
</div>
<%@ include file="/front-end/EndFooter.file" %>
</body>
</html>