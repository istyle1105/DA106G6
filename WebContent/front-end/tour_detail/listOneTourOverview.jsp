<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.tour_detail.model.*"%>
<%@ page import="com.spot.model.*"%>
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
//     List<Tour_detailVO> list = (List)request.getAttribute("list");
    String tour_id = (String)request.getParameter("tour_id");
    Tour_detailService t_detailSvc = new Tour_detailService();
    List<List<SpotVO>> list = t_detailSvc.getOneTourShowOverview(tour_id);
//     if(list==null){
//     	list = (List)session.getAttribute("list");
//     	tour_id = (String)session.getAttribute("tour_id");
//     }
    request.setAttribute("list",list);
    request.setAttribute("tour_id",tour_id);
%>

<jsp:useBean id="tourSvc" class="com.tour.model.TourService"/>
<jsp:useBean id="spotSvc" class="com.spot.model.SpotService"/>
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
</style>

</head>
<body bgcolor='white'>

<%@ include file="/front-end/TopHeader.file" %>
<div id="content" class="container">

<!-- <table id="table-1"> -->
<!-- 	<tr><td> -->
		 <h3>行程總覽 </h3>
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
<h3>行程:${tourSvc.getOneTour(tour_id).tour_name}</h3>
<table class="table table-striped table-hover">
	<tr>
		<th>天數</th>
		<th>景點</th>
		<th>美食</th>
		<th>飯店</th>
<!-- 		<th>活動描述</th> -->
<!-- 		<th>活動圖片</th> -->
<!-- 		<th>修改</th> -->
<!-- 		<th>刪除</th> -->
	</tr>
	
	<%@ include file="page1.file" %> 
	<c:forEach var="daylist" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>" varStatus="count">
		
		<tr>
			<td>第 ${count.count} 天</td>
			<td>
			<c:forEach var="spotVO" items="${daylist}">
			<c:if test="${spotVO.spot_type==0}">
			<div><a title='${spotVO.spot_intro}'   href='<%=request.getContextPath()%>/front-end/spot/listOneSpot.jsp?spot_id=${spotVO.spot_id}'>${spotVO.spot_name}</a></div> 
			</c:if>
			</c:forEach>
			</td>
			
			<td>
			<c:forEach var="spotVO" items="${daylist}">
			<c:if test="${spotVO.spot_type==2}">
			<div><a title='${spotVO.spot_intro}'   href='<%=request.getContextPath()%>/front-end/spot/listOneSpot.jsp?spot_id=${spotVO.spot_id}'>${spotVO.spot_name}</a></div> 
			</c:if>
			</c:forEach>
			</td>
			
			<td>
			<c:forEach var="spotVO" items="${daylist}">
			<c:if test="${spotVO.spot_type==1}">
			<div><a title='${spotVO.spot_intro}'   href='<%=request.getContextPath()%>/front-end/spot/listOneSpot.jsp?spot_id=${spotVO.spot_id}'>${spotVO.spot_name}</a></div> 
			</c:if>
			</c:forEach>
			</td>
		
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>
</div>
<%@ include file="/front-end/EndFooter.file" %>
</body>
</html>