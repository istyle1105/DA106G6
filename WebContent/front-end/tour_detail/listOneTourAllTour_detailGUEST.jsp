<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.tour_detail.model.*"%>
<%-- �����m�߱ĥ� EL ���g�k���� --%>
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
<!-- 		 <h3>�@�Ӧ�{���Ӹ`��� - listOneTourAllTour_detail.jsp</h3> -->
<!-- 	</td></tr> -->
<!-- </table> -->

<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
<table class="col">
<tr>
<th rowspan="4"><img class="pic" src='<%=request.getContextPath()%>/DBGifReader4Tour?tour_id=${tripVO.tour_id}'></th>
<th><h4>��{: ${tourSvc.getOneTour(tour_id).tour_name}</h4></th>
</tr>
<tr>
<th><h4>��{²��: ${tourSvc.getOneTour(tour_id).tour_detail}</h4></th>
</tr>
<tr>
<th><h4>�ɶ�: ${tripVO.first_date}  ~  ${tripVO.last_date}     (${tripVO.days}��C)</h4></th>
</tr>
<tr>
<th><h4>�w�����B :NT. ${tripVO.trip_price} </h4></th>
</tr>
</table>
<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/tour_detail/tour_detail.do" style="margin-bottom: 0px;">
			     <input type="hidden" name="tour_id"  value="${tour_id}">
			     <input type="hidden" name="tour_name"  value="${tourSvc.getOneTour(tour_id).tour_name}">
			     <input type="submit" value="�a����� " <%=list.size()==0?"disabled":"" %>>
			     <input type="hidden" name="action" value="showTourDetailInMap"></FORM>
<%-- <a href="<%=request.getContextPath()%>/front-end/tour_detail/listOneTourOverview.jsp?tour_id=${tour_id}">���I²�� </a><br> --%>
<input type="button" value='���I²��' onclick="location.href='<%=request.getContextPath()%>/front-end/tour_detail/listOneTourOverview.jsp?tour_id=${tour_id}'">
<br>
<input type="button" ${(tripVO.mem_no==mem_no)?"disabled value='�ڬO�ΥD'":"value='�ѥ[����'" } 
				 onclick="location.href='<%=request.getContextPath()%>/trip_list/trip_list.do?trip_id=${tripVO.trip_id}&action=getOne_For_Insert'">
<br>

<table class="table table-striped table-hover">
	<tr>
		<th>�}�l�ɶ�</th>
		<th>���d�ɶ�</th>
		<th>���I�W��</th>
		<th>���ʴy�z</th>
		<th>���ʹϤ�</th>
<!-- 		<th>�ק�</th> -->
<!-- 		<th>�R��</th> -->
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="tour_detailVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td><fmt:formatDate value='${tour_detailVO.start_time}' pattern='yyyy-MM-dd HH:mm'/></td>
			<td>${tour_detailVO.stay_time}����</td>
			<td>${spotSvc.getOneSpot(tour_detailVO.spot_id).spot_name}<br>
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/spot/spot.do" style="margin-bottom: 0px;">
			     <input type="submit" value="�Ա�">
			     <input type="hidden" name="spot_id"  value="${tour_detailVO.spot_id}">
			     <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>">
			      <input type="hidden" name="whichPage"	value="<%=whichPage%>">
			     <input type="hidden" name="action"	value="getOne_For_Display"></FORM></td>
			<td>${tour_detailVO.act_descrip}</td>
			<td><img src='<%=request.getContextPath()%>/DBGifReader4Tour_detail?tour_detail_id=${tour_detailVO.tour_detail_id}'></td>
			
<!-- 			<td> -->
<%-- 			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/tour_detail/tour_detail.do" style="margin-bottom: 0px;"> --%>
<!-- 			     <input type="submit" value="�ק�"> -->
<%-- 			     <input type="hidden" name="tour_detail_id"  value="${tour_detailVO.tour_detail_id}"> --%>
<%-- 			     <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"> --%>
<%-- 			     <input type="hidden" name="whichPage"	value="<%=whichPage%>"> --%>
<!-- 			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM> -->
<!-- 			</td> -->
<!-- 			<td> -->
<%-- 			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/tour_detail/tour_detail.do" style="margin-bottom: 0px;"> --%>
<!-- 			     <input type="submit" value="�R��"> -->
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