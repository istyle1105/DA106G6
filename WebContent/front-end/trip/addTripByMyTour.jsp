<%@page import="java.sql.Timestamp"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.trip.model.*"%>
<%@ page import="com.tour.model.*"%>
<%@ page import="com.tour_detail.model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.sql.*"%>
<%@ page import="my_util.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.member.model.*"%>
<%

	String mem_no=(String)session.getAttribute("mem_no");
	MemberService Membersvc=new MemberService();
	MemberVO memberVO=null;
	memberVO=Membersvc.getOneMember(mem_no);
	pageContext.setAttribute("memberVO", memberVO);	
	

%>
<%
	TripVO tripVO = (TripVO) request.getAttribute("tripVO");
	String tour_id = (String) request.getParameter("tour_id");
	TourService tourSvc = new TourService();
	TourVO tourVO = tourSvc.getOneTour(tour_id);
%>
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
</style>

<style>
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
  
  .xdsoft_datetimepicker .xdsoft_datepicker {
           width:  300px;   /* width:  300px; */
  }
  .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
           height: 151px;   /* height:  151px; */
  }
</style>

</head>
<body bgcolor='white'>
<%@ include file="/front-end/TopHeader.file" %>
<div id="content" class="container">
<!-- <table id="table-1"> -->
<!-- 	<tr><td> -->
<!-- 		 <h3>揪團資料新增 - addTrip.jsp</h3></td><td> -->
<!-- 	</td></tr> -->
<!-- </table> -->

<h3>開啟揪團:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/trip/trip.do" name="form1">
<table class="table">
<!-- 	<tr> -->
<!-- 		<td>會員編號:</td> -->
<%-- 		<td>${tripVO.mem_no} --%>
<%-- 		<input type="hidden" name="mem_no" value="<%= (tripVO.getMem_no())%>" /></td> --%>
<!-- 	</tr> -->
	<tr>
		<td>行程名稱:</td>
		<td><%=tourVO.getTour_name() %>
		<input type="hidden" name="tour_id"  value="<%= (tripVO.getTour_id())%>" /></td>
	</tr>
	<tr>
		<td>起始日期:</td>
		<td><fmt:formatDate value="${tripVO.first_date}" pattern="yyyy-MM-dd"/>
		<input name="first_date" id="first_date" value="${tripVO.first_date}"  type="hidden" ></td>
	</tr>
	<tr>
		<td>結束日期:</td>
		<td><fmt:formatDate value="${tripVO.last_date}" pattern="yyyy-MM-dd"/>
		<input name="last_date"  id="last_date" value="${tripVO.last_date}"  type="hidden" ></td>
	</tr>
	<tr>
		<td>天數:</td>
		<td>${tripVO.days}
		<input type="hidden" name="days"  value="<%= (tripVO.getDays())%>" /></td>
	</tr>
	<tr>
		<td>預估金額:</td>
		<td><input type="number" name="trip_price" size="45"  min="0"
			 value="<%= (tripVO.getTrip_price()==null)? "0" : tripVO.getTrip_price()%>" /></td>
	</tr>
	
	<tr>
		<td>報名開始日期:</td>
		<td><input name="reg_start" id="reg_start" type="text" ></td>
	</tr>
	<tr>
		<td>報名截止日期:</td>
		<td><input name="reg_deadline"  id="reg_deadline"   type="text" ></td>
	</tr>
	<tr>
		<td>成團人數:</td>
		<td><input type="number" name="mem_amount" value="<%= (tripVO.getMem_amount()==null)? "1" : tripVO.getMem_amount()%>" min="1" max="100" step="1"></td>
	</tr>
	<tr>
		<td>人數上限:</td>
		<td><input type="number" name="mem_limited" value="<%= (tripVO.getMem_limited()==null)? "1" : tripVO.getMem_limited()%>" min="1" max="100" step="1"></td>
	</tr>
</table>


<input type="hidden" name="mem_no" value="<%= (tripVO.getMem_no())%>" />
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增"></FORM>
</div>
<%@ include file="/front-end/EndFooter.file" %>

</body>

<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<link   rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath() %>/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath() %>/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
  .xdsoft_datetimepicker .xdsoft_datepicker {
           width:  300px;   /* width:  300px; */
  }
  .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
           height: 151px;   /* height:  151px; */
  }
</style>
<% 
  java.sql.Date now = new java.sql.Date(System.currentTimeMillis());
%>


<script>
$.datetimepicker.setLocale('zh'); // kr ko ja en
$(function(){
// 	 $('#first_date').datetimepicker({
// 	  format:'Y-m-d',
<%-- 	  startDate:'<fmt:formatDate value="<%=start %>" pattern="yyyy-MM-dd"/>', --%>
// 	  onShow:function(){
// 	   this.setOptions({
// 	    maxDate:$('#last_date').val()?$('#last_date').val():false,
<%-- 	    minDate: <%=first_date%>,	 --%>
// 	   })
// 	  },
	 
<%-- 	  value: <fmt:formatDate value="<%=start %>" pattern="yyyy-MM-dd"/>, --%>
// 	  timepicker:false
// 	 });
	 
// 	 $('#last_date').datetimepicker({
// 	  format:'Y-m-d',
// 	  onShow:function(){
// 	   this.setOptions({
// 	    minDate:$('#first_date').val()?$('#first_date').val():false
// 	   })
// 	  },
// 	  timepicker:false
// 	 });
	 $('#reg_start').datetimepicker({
		  format:'Y-m-d',
		  onShow:function(){
		   this.setOptions({
		    maxDate:$('#reg_deadline').val()?$('#reg_deadline').val():$('#first_date').val(),
		   	minDate:           '-1970-01-01', // 去除今日(不含)之前
		   })
		  },
		  value:<%=now%>,
		  timepicker:false
		 });
		 
		 $('#reg_deadline').datetimepicker({
		  format:'Y-m-d',
		  onShow:function(){
		   this.setOptions({
		    minDate:$('#reg_start').val()?$('#reg_start').val():false,
		    maxDate:$('#first_date').val()?$('#first_date').val():false
		   })
		  },
		  timepicker:false
		 });
});
</script>
</html>