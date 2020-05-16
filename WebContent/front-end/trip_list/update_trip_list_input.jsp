<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.trip_list.model.*"%>
<%@ page import="com.member.model.*"%>
<%
	String mem_no=(String)session.getAttribute("mem_no");
	MemberService Membersvc=new MemberService();
	MemberVO memberVO=null;
	memberVO=Membersvc.getOneMember(mem_no);
	pageContext.setAttribute("memberVO", memberVO);	
	pageContext.setAttribute("memSvc", Membersvc);	
%>  

<%
	Trip_listVO trip_listVO = (Trip_listVO) request.getAttribute("trip_listVO"); //EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>
<jsp:useBean id="tripSvc" class="com.trip.model.TripService"/>
<jsp:useBean id="tourSvc" class="com.tour.model.TourService"/>
<c:set var="tripVO" value="${tripSvc.getOneTrip(trip_listVO.trip_id)}"/>
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
<div id="content" class="container">

<!-- <table id="table-1"> -->
<!-- 	<tr><td> -->
		 <h3>評價團主:${memSvc.getOneMember(tripVO.mem_no).mem_id}</h3>
<%-- 		 <h4><a href="<%=request.getContextPath()%>/front-end/trip_list/select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4> --%>
<!-- 	</td></tr> -->
<!-- </table> -->

<h3>行程名稱:${tourVO.tour_name}</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="POST" ACTION="<%=request.getContextPath()%>/trip_list/trip_list.do" name="form1" oninput="answer.innerText=parseInt(s1.value)">
<table>
<!-- 	<tr> -->
<!-- 		<td>報到狀態:<font color=red><b>*</b></font></td> -->
<!-- 		<td> -->
<!-- 		<select size="1" name="check_status"> -->
<!-- 			<option value="0">未報到</option> -->
<%-- 			<option value="1" <%=(trip_listVO.getCheck_status()==1)?"selected":""%>>已報到</option> --%>
<!-- 		</select> -->

<!-- 	</tr> -->
	<tr>
		<td>評價等級:<font color=red><b>*</b></font></td>
		<td><input type="range" min="1" max="5" step="1" name="rate" size="45"	id="s1"
		value="<%=(trip_listVO.getRate()==0)?"3":trip_listVO.getRate()%>" />
		<output id="answer"><%=(trip_listVO.getRate()==0)?"3":trip_listVO.getRate()%></output>顆星</td>
	</tr>
	<tr>
		<td>評價內容:<font color=red><b>*</b></font></td>
		<td><input type="TEXT" name="comment_content" size="45"	
		value="<%=(trip_listVO.getComment_content()==null)?"":trip_listVO.getComment_content()%>" /></td>
	</tr>
	
	
</table>
<jsp:useBean id="now" scope="page" class="java.util.Date" />     
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="check_status" value="<%=trip_listVO.getCheck_status()%>">
<input type="hidden" name="trip_id" value="<%=trip_listVO.getTrip_id()%>">
<input type="hidden" name="mem_no" value="<%=trip_listVO.getMem_no()%>">
<input type="hidden" name="reg_time" value="<fmt:formatDate value="${trip_listVO.reg_time}" pattern="yyyy-MM-dd HH:mm:ss"/>">
<input type="hidden" name="comment_time" value="<fmt:formatDate value="${now}" pattern="yyyy-MM-dd HH:mm:ss"/>">
<input type="hidden" name="requestURL" value="<%=request.getParameter("requestURL")%>"> <!--接收原送出修改的來源網頁路徑後,再送給Controller準備轉交之用-->
<input type="hidden" name="whichPage"  value="<%=request.getParameter("whichPage")%>">  <!--只用於:istAllEmp.jsp-->
<input type="submit" value="送出評價"></FORM>


</div>
<%@ include file="/front-end/EndFooter.file" %>
</body>


</html>