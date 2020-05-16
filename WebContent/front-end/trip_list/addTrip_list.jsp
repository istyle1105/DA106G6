<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.trip_list.model.*"%>
<%@ page import="com.member.model.*"%>
<%
	String mem_no=(String)session.getAttribute("mem_no");
	MemberService Membersvc=new MemberService();
	MemberVO memberVO=null;
	memberVO=Membersvc.getOneMember(mem_no);
	pageContext.setAttribute("memberVO", memberVO);	
%>  
<%
	Trip_listVO trip_listVO = (Trip_listVO) request.getAttribute("trip_listVO");
%>
<jsp:useBean id="tripSvc" class="com.trip.model.TripService"/>
<jsp:useBean id="tourSvc" class="com.tour.model.TourService"/>
<c:set var="tripVO" value="${tripSvc.getOneTrip(trip_id)}"/>
<c:set var="tourVO" value="${tourSvc.getOneTour(tripVO.tour_id)}"/>
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
  table {
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
  }
  table, th, td {
    border: 0px solid #CCCCFF;
  }
  th, td {
    padding: 1px;
  }

  
</style>

</head>
<body bgcolor='white'>
<%@ include file="/front-end/TopHeader.file" %>
<div id="content" >
<div class="col-4" style="margin:20px auto;">
<!-- <table id="table-1"> -->
<!-- 	<tr><td> -->
		 <h3>參加行程</h3>
<!-- 		 </td><td> -->
<!-- 	</td></tr> -->
<!-- </table> -->
<p><b>
歡迎參加行程,在報名截止日以前都可以自由的取消參加行程
如報名截止日以後,因故無法參加,請與團長聯繫</b>
</p>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<!-- <table> -->
<!-- <tr><td colspan="2"></td></tr> -->
<%-- <tr><th>團名</th><td>${tourVO.tour_name}  </td></tr> --%>
<%-- <tr><th>開始時間</th><td>${tripVO.first_date}   </td></tr> --%>
<%-- <tr><th>結束時間</th><td>${tripVO.last_date}   </td></tr> --%>
<%-- <tr><th>行程簡介</th><td>${tourVO.tour_detail}   </td></tr> --%>
<!-- </table> -->

<div class="card text-center" style="width: 30rem; margin:auto;">
  <img src='<%=request.getContextPath()%>/DBGifReader4Tour?tour_id=${tripVO.tour_id}'  class="card-img-top">
  <div class="card-body">
    <h5 class="card-title">${tourVO.tour_name}</h5>
    <p class="card-text">
    <table >
	<tr><th>開始時間</th><td>${tripVO.first_date}   </td></tr>
	<tr><th>結束時間</th><td>${tripVO.last_date}   </td></tr>
	<tr><th>行程簡介</th><td>${tourVO.tour_detail}   </td></tr>
	</table>
	
<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/trip_list/trip_list.do" name="form1">
<input type="hidden" name="trip_id" value="${trip_id}">
<input type="hidden" name="mem_no" value="${mem_no}">
<input type="hidden" name="action" value="insert">
<input type="submit" class="btn btn-primary" value="確認參加行程"></FORM>
	
    </p>
  </div>
</div>

</div>
</div>
<%@ include file="/front-end/EndFooter.file" %>
</body>


</html>