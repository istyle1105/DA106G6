<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.trip.model.*"%>
<%@ page import="java.util.*"%>
<%
	String mem_no=(String)session.getAttribute("mem_no");
	MemberService Membersvc=new MemberService();
	MemberVO memberVO=null;
	memberVO=Membersvc.getOneMember(mem_no);
	pageContext.setAttribute("memberVO", memberVO);	
	pageContext.setAttribute("memSvc", Membersvc);	
%>  
<%	
	TripService tripSvc = new TripService(); 
	List<TripVO> list = tripSvc.getAll(new HashMap<String,String[]>());
	request.setAttribute("listTrips_ByCompositeQuery", list);
%>
<jsp:useBean id="listTrips_ByCompositeQuery" scope="request" type="java.util.List<TripVO>" /> <!-- 於EL此行可省略 -->
<jsp:useBean id="tourSvc" scope="page" class="com.tour.model.TourService" />

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
	width: 450px;
	background-color: #CCCCFF;
	margin-top: 5px;
	margin-bottom: 10px;
    border: 3px ridge Gray;
    height: 80px;
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
  	width:150px;
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

<div class="row">
<div class="col-2">
<!-- <table id="table-1"> -->
<!--    <tr><td><h3>IBM Trip: Home</h3><h4>( MVC )</h4></td></tr> -->
<!-- </table> -->
<%@ include file="/front-end/trip/searchPage.file" %>

</div>
	<div class="col-10">
	<%-- 錯誤表列 --%>
<%-- <c:if test="${not empty errorMsgs}"> --%>
<!-- 	<font style="color:red">請修正以下錯誤:</font> -->
<!-- 	<ul> -->
<%-- 	    <c:forEach var="message" items="${errorMsgs}"> --%>
<%-- 			<li style="color:red">${message}</li> --%>
<%-- 		</c:forEach> --%>
<!-- 	</ul> -->
<%-- </c:if> --%>
	<table class="table table-striped table-hover">
	<tr>
		<th>揪團編號</th>
		<th>團主帳號</th>
		<th>行程名稱</th>
		<th>天數</th>
		<th>預估金額</th>
		<th>起始日期</th>
		<th>結束日期</th>
		<th>報名開始日期</th>
		<th>報名截止日期</th>
		<th>目前人數</th>
		<th>成團人數</th>
		<th>人數上限</th>
		<th>成團狀態</th>
<!-- 		<th>上架狀態</th> -->
<!-- 		<th>最後修改時間</th> -->
		<th>行程詳情</th>
		<th>參加行程</th>
<!-- 		<th>追蹤行程</th> -->
		<th>檢舉行程</th>
	</tr>
	<%@ include file="page1_ByCompositeQuery.file" %>
	<c:forEach var="tripVO" items="${listTrips_ByCompositeQuery}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr align='center' valign='middle' ${(tripVO.trip_id==param.trip_id) ? 'bgcolor=#CCCCFF':''}><!--將修改的那一筆加入對比色而已-->
			<td>${tripVO.trip_id}</td>
			<td class="send"><b><a  href="JavaScript:openIT()" >${memSvc.getOneMember(tripVO.mem_no).mem_id}</a><p style="display:none;">${tripVO.mem_no}</p></b></td>
			<td>${tourSvc.getOneTour(tripVO.tour_id).tour_name}</td>
			<td>${tripVO.days}</td>
			<td>${tripVO.trip_price}</td>
			<td>${tripVO.first_date}</td>
			<td>${tripVO.last_date}</td>
			<td>${tripVO.reg_start}</td>
			<td>${tripVO.reg_deadline}</td>
			<td>${tripVO.current_mem}</td>
			<td>${tripVO.mem_amount}</td>
			<td>${tripVO.mem_limited}</td>
			<td>${tripStatus.get(tripVO.trip_status)}</td>
<%-- 			<td>${tripVO.tour_status}</td> --%>
<%-- 			<td>${tripVO.last_mod_time}</td> --%>
			
			<td>
			<img src='<%=request.getContextPath()%>/DBGifReader4Tour?tour_id=${tripVO.tour_id}'>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/tour/tour.do" style="margin-bottom: 0px;">
			     <input type="submit" class="btn btn-info" value="詳情"> 
			     <input type="hidden" name="tour_id"      value="${tripVO.tour_id}">
			     <input type="hidden" name="trip_id"      value="${tripVO.trip_id}">
			     <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
			     <input type="hidden" name="whichPage"	value="<%=whichPage%>">               <!--送出當前是第幾頁給Controller-->
			     <input type="hidden" name="action"	    value="getOneTour_Show_Detail"></FORM>
			</td>
			<td>
				<input type="button" class="btn btn-success" ${(tripVO.mem_no==mem_no)?"disabled value='團主'":"value='參加'" } 
				 onclick="location.href='<%=request.getContextPath()%>/trip_list/trip_list.do?trip_id=${tripVO.trip_id}&action=getOne_For_Insert'">
<%-- 			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/trip_list/addTrip_list.jsp" style="margin-bottom: 0px;"> --%>
<!-- 			     <input type="submit" value="參加"> -->
<%-- 			     <input type="hidden" name="mem_no"      value="${mem_no}"> --%>
<%-- 			      <input type="hidden" name="trip_id"    value="${tripVO.trip_id}"> --%>
<%-- 			     <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller--> --%>
<%-- 			     <input type="hidden" name="whichPage"	value="<%=whichPage%>">               <!--送出當前是第幾頁給Controller--> --%>
<!-- 			     <input type="hidden" name="action"     value="delete"></FORM> -->
			</td>
<!-- 			<td> -->
<%-- 			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/trip_list/trip_list.do" style="margin-bottom: 0px;"> --%>
<!-- 			     <input type="submit" value="追蹤"> -->
<%-- 			     <input type="hidden" name="mem_no"      value="${mem_no}"> --%>
<%-- 			      <input type="hidden" name="trip_id"    value="${tripVO.trip_id}"> --%>
<%-- 			     <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller--> --%>
<%-- 			     <input type="hidden" name="whichPage"	value="<%=whichPage%>">               <!--送出當前是第幾頁給Controller--> --%>
<!-- 			     <input type="hidden" name="action"     value="delete"></FORM> -->
<!-- 			</td> -->
			<td>
			<input type="button" class="btn btn-warning" ${(tripVO.mem_no==mem_no)?"disabled value='檢舉'":"value='檢舉'" }  
			onclick="location.href='<%=request.getContextPath()%>/front-end/trip_report/addTrip_report.jsp?trip_id=${tripVO.trip_id}&mem_no=${mem_no}&requestURL=<%=request.getServletPath()%>&whichPage=<%=whichPage%>'">
			
<%-- 			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/trip_report/trip_report.do" style="margin-bottom: 0px;" > --%>
<!-- 			     <input type="submit" value="檢舉"> -->
<%-- 			     <input type="hidden" name="mem_no"      value="${mem_no}"> --%>
<%-- 			      <input type="hidden" name="trip_id"    value="${tripVO.trip_id}"> --%>
<%-- 			     <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller--> --%>
<%-- 			     <input type="hidden" name="whichPage"	value="<%=whichPage%>">               <!--送出當前是第幾頁給Controller--> --%>
<!-- 			     <input type="hidden" name="action"     value="insert"></FORM> -->
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2_ByCompositeQuery.file" %>
	</div>
</div>
</div>
<%@ include file="/front-end/EndFooter.file" %>

<script >
$(".send").bind("click",function(){
	index = $(this).find("p").text();
});

var state='${param.state}';
    if(state=='notverify'){
    	 Swal.fire({
   		  title: '請取得團主權限',
   		  icon: 'error',
   		  showConfirmButton: false,
   		  showCloseButton: true,
   		  timer: 1500
   		})
    }

</script>
</body>


</html>
