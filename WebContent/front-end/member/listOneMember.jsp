<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.member.model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="javax.servlet.ServletContext"%>
<%
	String mem_no=(String)session.getAttribute("mem_no");
	MemberService Membersvc=new MemberService();
	MemberVO memberVO=null;
	memberVO=Membersvc.getOneMember(mem_no);
	pageContext.setAttribute("memberVO", memberVO);	
%>
<%--
	MemberVO memberVO=null;
	memberVO=(MemberVO)session.getAttribute("memberVO");
--%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>會員資料 listOneEmp</title>
    <!-- Required meta tags -->
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
.table > tbody > tr > td {
     vertical-align: middle;
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
  splashWin.document.write("<div id=stillloadingDiv><table width=100% height=22 cellspacing=0 cellpadding=0><tr><td align=left valign=middle width=100%><FONT size=1 face=verdana color=#000000>讀取中...如無法開啟請按 Ctrl+Alt+Del 關閉</font></td></tr></table></div>");
  splashWin.document.write("</body></html>");
  splashWin.document.close();
 }
 else    var splashWin = window.open(theURL, wname, "toolbar=0,location=0,directories=0,status=0,menubar=0,scrollbars=0,resizable=1"+s, true)
 splashWin.focus();
}
function openIT() {
 theurl="<%=request.getContextPath()%>/front-end/member/memberCard2.jsp?mem_no=<%=memberVO.getMem_no()%>"
 wname ="CHROMELESSWIN"
 W=1200;
 H=800;
 openchromeless(theurl, wname, W, H)
}

</script>


<style>
ul.tabs {
	margin: 0;
	padding: 0;
	float: left;
	list-style: none;
	height: 32px;
	border-bottom: 1px solid #333;
	width: 100%;
}

ul.tabs li {
	float: left;
	margin: 0;
	cursor: pointer;
	padding: 0px 21px;
	height: 31px;
	line-height: 31px;
	border-top: 1px solid #333;
	border-left: 1px solid #333;
	border-bottom: 1px solid #333;
	background-color: #666;
	color: #ccc;
	overflow: hidden;
	position: relative;
}

.tab_last { border-right: 1px solid #333; }

ul.tabs li:hover {
	background-color: #ccc;
	color: #333;
}

ul.tabs li.active {
	background-color: #fff;
	color: #333;
	border-bottom: 1px solid #fff;
	display: block;
}

.tab_container {
	border: 1px solid #333;
	border-top: none;
	clear: both;
	float: left;
	width: 100%;
	background: #fff;
	overflow: auto;
}

.tab_content {
	padding: 20px;
	display: none;
}

.tab_drawer_heading { display: none; }

@media screen and (max-width: 480px) {
	.tabs {
		display: none;
	}
	.tab_drawer_heading {
		background-color: #ccc;
		color: #fff;
		border-top: 1px solid #333;
		margin: 0;
		padding: 5px 20px;
		display: block;
		cursor: pointer;
		-webkit-touch-callout: none;
		-webkit-user-select: none;
		-khtml-user-select: none;
		-moz-user-select: none;
		-ms-user-select: none;
		user-select: none;
	}
	.d_active {
		background-color: #666;
		color: #fff;
	}
}

</style>
</head>
<body>
<%@ include file="/front-end/TopHeader.file" %>


<div class="container" style="margin-top:30px; ">
<div class="row border col-8 center" style="margin:0;margin:auto;border:3px solid red;background-color:white;">
  <div class="col-4">
	<div class="card" >
		<div class="card-header col-12">
 			會員：<b><%=memberVO.getM_name()%></b> 帳號：<b><a href="JavaScript:openIT()"><%=memberVO.getMem_id()%></a></b>
  		</div>

				<ul class="list-group list-group-flush">
					<li class="list-group-item">註冊時間：<fmt:formatDate value="${memberVO.reg_time}" pattern="yyyy-MM-dd HH:mm"/></li>
					<li class="list-group-item">會員錢包餘額：<%=memberVO.getAcc_balance()%></li>
					<li class="list-group-item">會員驗證：
							<c:forEach var="status" items="${memStatus}">
					${memberVO.mem_verify==status.key ? status.value : ''}
					</c:forEach>
					</li>
					    <li class="list-group-item">團長驗證：
							<c:forEach var="status" items="${leaderStatus}">
					${memberVO.leader_verify==status.key ? status.value : ''}
					</c:forEach>
					</li>
					
					<li class="list-group-item">代購驗證：
					<c:forEach var="status" items="${purStatus}">
					${memberVO.pur_verify==status.key ? status.value : ''}
					</c:forEach>
					</li>
					
					<li class="list-group-item">需求代購平均分數：
					<fmt:formatNumber value="${(memberVO.de_total_score div memberVO.de_score_amount)!='NaN' ? (memberVO.de_total_score div memberVO.de_score_amount) : '0'}"
					type="NUMBER" maxFractionDigits="2"/>
					</li>
					<li class="list-group-item">需求代購評價總人數：<%=memberVO.getDe_score_amount()%>人</li>
					
					<li class="list-group-item">提供代購平均分數：
					<fmt:formatNumber value="${(memberVO.pur_total_score div memberVO.pur_score_amount)!='NaN' ? (memberVO.pur_total_score div memberVO.pur_score_amount) : '0'}"
					type="NUMBER" maxFractionDigits="2"/>
					</li>
					<li class="list-group-item">提供代購評價總人數：<%=memberVO.getPur_score_amount()%>人</li>	
					<li class="list-group-item">團長平均分數：
					<fmt:formatNumber value="${(memberVO.leader_total_score div memberVO.leader_score_amount)!='NaN' ? (memberVO.leader_total_score div memberVO.leader_score_amount) : '0'}"
					type="NUMBER" maxFractionDigits="2"/>

					</li>
					<li class="list-group-item">團長評價總人數：<%=memberVO.getLeader_score_amount()%>人</li>
				</ul>

</div>

</div>
<div class="col-5" style="margin:auto;">
    <table class="table table-hover table-bordered h6 col-12">
  <tbody>

  	<tr>
  	  <td style="margin:auto;"colspan="2"><img height="180" onerror="javascript:this.src='<%=request.getContextPath()%>/NoData/no_photo.jpg'" src="data:image/png;base64,${memberVO.m_photo}"></td>
  	</tr>
  
    <tr>
      <td>會員編號</td>
      <td><%=memberVO.getMem_no()%></td>
    </tr>
    <tr>
      <td>會員帳號</td>
      <td><%=memberVO.getMem_id()%></td>
    </tr>
    <tr>
      <td>會員姓名</td>
      <td><%=memberVO.getM_name()%></td>
    </tr> 
    <tr>
      <td>會員性別</td>
      <td><%=memberVO.getGender()==0 ? "男性":"女性"%></td>
    </tr>  
    <tr>
      <td>會員手機</td>
      <td><%=memberVO.getCellphone()%></td>
    </tr>    
    <tr>
      <td>會員信箱</td>
      <td><%=memberVO.getEmail()%></td>
    </tr>
    <tr>
      <td>會員地址</td>
      <td><%=memberVO.getAddress()%></td>
    </tr>
  	<tr>
  		<td colspan="2" style="padding: 0px;">
  			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/member/member.do" style="margin: 3px auto;">
			     <button type="submit" class="btn btn-primary">會員基本資料修改</button>
			     <input type="hidden" name="mem_no"  value="${memberVO.mem_no}">
			     <input type="hidden" name="action"	value="front_One_Update"> 
			</FORM>
		</td>
  	</tr>  
  </tbody>
</table>
  </div>
</div>
</div>

<div style="display:flex;">
<div class="col-3" style="border:3px solid black">
</div>
<div class="col-8">
<ul class="tabs">
  <li class="active" rel="tab1">會員資料</li>
  <li rel="tab2">團長評價</li>
  <li rel="tab3">需求代購評價</li>
  <li rel="tab4">提供代購評價</li>
</ul>
<div class="tab_container" style="margin:0; padding:0;">
  <h3 class="d_active tab_drawer_heading" rel="tab1">Tab 1</h3>
  <div id="tab1" class="tab_content" style="text-align:center;">
  <h2>會員資料</h2>
    <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec ac metus augue.</p>
  </div>
  <!-- #tab1 -->
  <h3 class="tab_drawer_heading" rel="tab2">Tab 2</h3>
  <div id="tab2" class="tab_content" style="text-align:center;">
    <jsp:useBean id="trip_listSvc" scope="page" class="com.trip_list.model.Trip_listService"/>
  <jsp:useBean id="tripSvc" scope="page" class="com.trip.model.TripService"/>
  <jsp:useBean id="memberSvc" scope="page" class="com.member.model.MemberService"/>
  <h2>團長評價</h2>
	  <table class="table col-8 center" style="margin:auto;">
		<thead class="thead-dark">
			<tr>
				<th scope="col">會員帳號</th>
				<th scope="col">評價等級</th>
				<th scope="col">評價內容</th>
				<th scope="col">評價時間</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach var="TripVO" items="${tripSvc.getAllMyTrip(memberVO2.mem_no)}">
			<c:forEach var="trip_listVO" items="${trip_listSvc.getAllMyMember(TripVO.trip_id)}" > 
			<c:if test="${not empty trip_listVO.comment_time }">
			<tr>
			    	<td>${memberSvc.getOneMember(trip_listVO.mem_no).mem_id} </td>
					<td>${trip_listVO.rate}</td>
					<td>${trip_listVO.comment_content}</td>
					<td>${trip_listVO.comment_time}</td>
			</tr>
			</c:if>
			</c:forEach>
		</c:forEach>
		</tbody>
	</table>



  </div>
  <!-- #tab2 -->
  <h3 class="tab_drawer_heading" rel="tab3">Tab 3</h3>
  <div id="tab3" class="tab_content" style="text-align:center;">
  <h2>需求代購評價</h2>
<jsp:useBean id="demandSvc" scope="page" class="com.demand.model.DemandService"/>  

  <table class="tabletest table col-8 center" style="margin:5px auto;">
		<thead class="thead-dark">
			<tr>
				<th scope="col">會員帳號</th>
				<th scope="col">評價等級</th>
				<th scope="col">評價內容</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach var="demandVO" items="${demandSvc.findMyDemand(memberVO2.mem_no)}">
			<c:if test="${demandVO.de_score!=0}">
			<tr>
			    	<td>${memberSvc.getOneMember(demandVO.pur_mem_no).mem_id} </td>
					<td>${demandVO.de_score}</td>
					<td>${demandVO.de_comment}</td>
			</tr>
			</c:if>
		</c:forEach>
		</tbody>
	</table>










  </div>
  <!-- #tab3 -->
  <h3 class="tab_drawer_heading" rel="tab4">Tab 4</h3>
  <div id="tab4" class="tab_content" style="text-align:center;">
  <h2>提供代購評價</h2>

    <table class="table col-8 center" style="margin:5px auto;">
		<thead class="thead-dark">
			<tr>
				<th scope="col">會員帳號</th>
				<th scope="col">評價等級</th>
				<th scope="col">評價內容</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach var="demandVO" items="${demandSvc.findMyOffer(memberVO2.mem_no)}">
			<c:if test="${demandVO.pur_score!=0}">
			<tr>
			    	<td>${memberSvc.getOneMember(demandVO.de_mem_no).mem_id} </td>
					<td>${demandVO.pur_score}</td>
					<td>${demandVO.pur_comment}</td>
			</tr>
			</c:if>
		</c:forEach>
		</tbody>
	</table>
  
  </div>
  <!-- #tab4 --> 
</div>
<!-- .tab_container -->
</div>
</div>
</div>



<%@ include file="/front-end/EndFooter.file" %>
<script>
    var state='${param.state}'
    if(state=='updatesuccess'){
    	 Swal.fire({
   		  title: '修改成功',
   		  icon: 'success',
   		  showConfirmButton: false,
   		  showCloseButton: true,
   		  timer: 1500
   		})
    }
    </script>
    
    
<script>
// tabbed content
// http://www.entheosweb.com/tutorials/css/tabs.asp
$(".tab_content").hide();
$(".tab_content:first").show();

/* if in tab mode */
$("ul.tabs li").click(function() {
	
  $(".tab_content").hide();
  var activeTab = $(this).attr("rel"); 
  $("#"+activeTab).fadeIn();		
	
  $("ul.tabs li").removeClass("active");
  $(this).addClass("active");

  $(".tab_drawer_heading").removeClass("d_active");
  $(".tab_drawer_heading[rel^='"+activeTab+"']").addClass("d_active");
  
});
/* if in drawer mode */
$(".tab_drawer_heading").click(function() {
  
  $(".tab_content").hide();
  var d_activeTab = $(this).attr("rel"); 
  $("#"+d_activeTab).fadeIn();
  
  $(".tab_drawer_heading").removeClass("d_active");
  $(this).addClass("d_active");
  
  $("ul.tabs li").removeClass("active");
  $("ul.tabs li[rel^='"+d_activeTab+"']").addClass("active");
});


/* Extra class "tab_last" 
   to add border to right side
   of last tab */
$('ul.tabs li').last().addClass("tab_last");


</script> 
    
    
</body>
</html>