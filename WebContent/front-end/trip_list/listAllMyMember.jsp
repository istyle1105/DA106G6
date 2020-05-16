<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.trip_list.model.*"%>
<%@ page import="com.trip.model.*"%>
<%@ page import="com.tour.model.*"%>
<%-- �����m�߱ĥ� EL ���g�k���� --%>
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
// 	String trip_id = (String)session.getAttribute("trip_id");����n��ȱqsession�Ǧn
// 	String trip_id = "T0003";//���եΰ����
// 	Trip_listService trip_listSvc = new Trip_listService();
//     List<Trip_listVO> list = trip_listSvc.getAllMyMember(trip_id);
	String trip_id = (String)request.getAttribute("trip_id");
    List<Trip_listVO> list = (List<Trip_listVO>)request.getAttribute("list");
	if(trip_id==null||list==null){
		trip_id = (String)session.getAttribute("trip_id");
		list = (List<Trip_listVO>)session.getAttribute("list");
	}
	session.setAttribute("list",list);
    session.setAttribute("trip_id",trip_id);
    TripService tripSvc = new TripService();
    TourService tourSvc = new TourService();
    TripVO tripVO = tripSvc.getOneTrip(trip_id);
    String tour_id = tripVO.getTour_id();
    TourVO tourVO = tourSvc.getOneTour(tour_id) ;
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
<script>
function openchromeless(theURL, wname, W, H) {
 var windowW = W;
 var windowH = H;
 var windowX = Math.ceil( (window.screen.width  - windowW) / 2 );
 var windowY = Math.ceil( (window.screen.height - windowH) / 2 );
 if (navigator.appName == "Microsoft Internet Explorer" && parseInt(navigator.appVersion)>=4) isie=true
 else                isie=false
 if (isie) { H=H+20+2; W=W+2; }
 s = ",width="+W+",height="+H;
 if (isie) {
  splashWin = window.open( "" , wname, "fullscreen=1,toolbar=0,location=0,directories=0,status=0,menubar=0,scrollbars=0,resizable=0"+s)
  splashWin.resizeTo( Math.ceil( W )       , Math.ceil( H ) )
  splashWin.moveTo  ( Math.ceil( windowX ) , Math.ceil( windowY ) )
  splashWin.document.open("text/html", "replace");
  splashWin.document.write("<html><style type='text/css'>\n");
  splashWin.document.write("body                  { border: 1px solid #000000; overflow: hidden; margin: 0pt;}");
  splashWin.document.write("#stillloadingDiv  { position: absolute; left: 0px; top: 0px; width: 100%px; height: 19px; z-index: 1; background-color: #C0C0C0; layer-background-color: #C0C0C0; clip:rect(0,100%,19,0);}");
  splashWin.document.write("</style>\n");
  splashWin.document.write("<body onload=\"top.document.location.replace('"+theurl+"')\" TOPMARGIN=0 LEFTMARGIN=0 MARGINWIDTH=0 MARGINHEIGHT=0 scroll='no'>");
  splashWin.document.write("<div id=stillloadingDiv><table width=100% height=22 cellspacing=0 cellpadding=0><tr><td align=left valign=middle width=100%><FONT size=1 face=verdana color=#000000>Ū����...�p�L�k�}�ҽЫ� Ctrl+Alt+Del ����</font></td></tr></table></div>");
  splashWin.document.write("</body></html>");
  splashWin.document.close();
 }
 else    var splashWin = window.open(theURL, wname, "toolbar=0,location=0,directories=0,status=0,menubar=0,scrollbars=0,resizable=1"+s, true)
 splashWin.focus();
}

var index;
function openIT() {
 theurl="<%=request.getContextPath()%>/front-end/member/memberCard2.jsp?mem_no="+index;
 wname ="CHROMELESSWIN"
 W=1200;
 H=900;
 openchromeless(theurl, wname, W, H)
}



</script>
</head>
<body bgcolor='white'>
<%@ include file="/front-end/TopHeader.file" %>
<div id="content" class="container">

<!-- <table id="table-1"> -->
<!-- 	<tr><td> -->
		 <h3>�έ����</h3>
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


<h3>���νs��:${trip_id}</h3>
<h3>��{�W��:<%=tourVO.getTour_name() %></h3>
<table class="table table-striped table-hover">
	<tr>
<!-- 		<th>���νs��</th> -->
		<th>�έ��b��</th>
		<th>���W�ɶ�</th>
		<th>���쪬�A</th>
		<th>��������</th>
		<th>�������e</th>
		<th>�����ɶ�</th>
<!-- 		<th>�ק�</th> -->
<!-- 		<th>�R��</th> -->
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="trip_listVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
<%-- 			<td>${trip_listVO.trip_id}</td> --%>
			<td class="send"><b><a  href="JavaScript:openIT()" >${memSvc.getOneMember(trip_listVO.mem_no).mem_id}</a><p style="display:none;">${trip_listVO.mem_no}</p></b></td>
			<td><fmt:formatDate value="${trip_listVO.reg_time}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
			<td>${checkStatus.get(trip_listVO.check_status)}</td>
			<td>${trip_listVO.rate}</td>
			<td>${trip_listVO.comment_content}</td>
			<td><fmt:formatDate value="${trip_listVO.comment_time}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
<!-- 			<td> -->
<%-- 			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/trip_list/trip_list.do" style="margin-bottom: 0px;"> --%>
<!-- 			     <input type="submit" value="�ק�"> -->
<%-- 			     <input type="hidden" name="trip_id"  value="${trip_listVO.trip_id}"> --%>
<%-- 			     <input type="hidden" name="mem_no"  value="${trip_listVO.mem_no}"> --%>
<!-- 			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM> -->
<!-- 			</td> -->
<!-- 			<td> -->
<%-- 			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/trip_list/trip_list.do" style="margin-bottom: 0px;"> --%>
<!-- 			     <input type="submit" value="�R��"> -->
<%-- 			     <input type="hidden" name="trip_id"  value="${trip_listVO.trip_id}"> --%>
<%-- 			     <input type="hidden" name="mem_no"  value="${trip_listVO.mem_no}"> --%>
<!-- 			     <input type="hidden" name="action" value="delete"></FORM> -->
<!-- 			</td> -->
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>
</div>
<%@ include file="/front-end/EndFooter.file" %>
<script >
$(".send").bind("click",function(){
	index = $(this).find("p").text();
});
</script>
</body>
</html>