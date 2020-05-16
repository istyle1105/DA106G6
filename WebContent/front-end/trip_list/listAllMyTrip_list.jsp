<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
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
// 	String mem_no = (String)session.getAttribute("mem_no");之後要把值從session傳好
// 	String mem_no = "M0005";//測試用假資料
	Trip_listService trip_listSvc = new Trip_listService();
    List<Trip_listVO> list = trip_listSvc.getAllMyTrip(memberVO.getMem_no());
    pageContext.setAttribute("list",list);
%>
<jsp:useBean id="tourSvc" scope="page" class="com.tour.model.TourService" />
<jsp:useBean id="tripSvc" scope="page" class="com.trip.model.TripService" />

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

<title>所有揪團資料 - listAllTrip_list.jsp</title>

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
  td>img{
  width:120px;
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
  splashWin.document.write("<div id=stillloadingDiv><table width=100% height=22 cellspacing=0 cellpadding=0><tr><td align=left valign=middle width=100%><FONT size=1 face=verdana color=#000000>讀取中...如無法開啟請按 Ctrl+Alt+Del 關閉</font></td></tr></table></div>");
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
		 <h3>我所參加的團</h3>
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
<jsp:useBean id="now" scope="page" class="java.util.Date" />
<%-- <h2>會員編號:${memberVO.mem_no}</h2> --%>
<table class="table table-striped table-hover">
	<tr>
		<th>揪團編號</th>
		<th>團主帳號</th>
		<th>行程名稱</th>
		<th>天數</th>
		<th>起始日期</th>
		<th>結束日期</th>
		<th>成團狀態</th>
		<th>目前人數</th>
		<th>預估金額</th>
		<th>評價</th>
		<th>退出</th>
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="trip_listVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
	<c:set var="tripVO" value="${tripSvc.getOneTrip(trip_listVO.trip_id)}"/>
	<c:set var="tourVO" value="${tourSvc.getOneTour(tripVO.tour_id)}"/>
	
		<tr>
			<td>${trip_listVO.trip_id}</td>
<%-- 			<td>${memSvc.getOneMember(tripVO.mem_no).mem_id}</td> --%>
			<td class="send"><b><a  href="JavaScript:openIT()" >${memSvc.getOneMember(tripVO.mem_no).mem_id}</a><p style="display:none;">${tripVO.mem_no}</p></b></td>
			<td><img src='<%=request.getContextPath()%>/DBGifReader4Tour?tour_id=${tripVO.tour_id}'>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/tour/tour.do" style="margin-bottom: 0px;">
			     <input type="submit" value="${tourVO.tour_name}"> 
			     <input type="hidden" name="tour_id"      value="${tripVO.tour_id}">
			     <input type="hidden" name="trip_id"      value="${tripVO.trip_id}">
			     <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
			     <input type="hidden" name="whichPage"	value="<%=whichPage%>">               <!--送出當前是第幾頁給Controller-->
			     <input type="hidden" name="action"	    value="getOneTour_Show_Detail"></FORM></td>
			<td>${tripVO.days}</td>
			<td>${tripVO.first_date}</td>
			<td>${tripVO.last_date}</td>
			<td>${tripStatus.get(tripVO.trip_status)}</td>
			<td>${tripVO.current_mem}</td>
			<td>${tripVO.trip_price}</td>

			<td>
				
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/trip_list/trip_list.do" style="margin-bottom: 0px;">
			     <input type="submit"  class="btn btn-primary" ${(trip_listVO.check_status)==1?" value='評價'":"disabled value='尚未出發'"} value="評價">
			     <input type="hidden" name="trip_id"  value="${trip_listVO.trip_id}">
			     <input type="hidden" name="mem_no"  value="${trip_listVO.mem_no}">
			     <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
			     <input type="hidden" name="whichPage"	value="<%=whichPage%>">               <!--送出當前是第幾頁給Controller-->
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/trip_list/trip_list.do" style="margin-bottom: 0px;">
			     <input type="submit"  class="btn btn-danger delete"  ${now.after(tripVO.getReg_deadline())?"disabled":""} value="退出">
			     <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
			     <input type="hidden" name="trip_id"  value="${trip_listVO.trip_id}">
			     <input type="hidden" name="mem_no"  value="${trip_listVO.mem_no}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
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

$('body').on('click','.delete',function(event){
	event.preventDefault();//阻止按鈕預設行為 ,讓表單送出停下來
	
Swal.fire({
	  title: '請問確定要退出嗎?',
	  text: "在報名截止前都可以自由退出",
	  icon: 'warning',
	  showCancelButton: true,
	  confirmButtonColor: '#3085d6',
	  cancelButtonColor: '#d33',
	  confirmButtonText: '好的!',
	  cancelButtonText:'還是不要!'
	}).then((result) => {
	  if (result.value) {
		  
	    Swal.fire(
	      '退出成功',
	      'Your file has been deleted.',
	      'success'
	    )
	   	$(this).parent().submit();//抓取表單執行表單送出
	    
		 
	  }
	});
	
});


</script>
</body>
</html>