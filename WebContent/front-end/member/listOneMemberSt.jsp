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
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Let's Go Trip</title>

    <link rel="icon" href="<%=request.getContextPath()%>/front-end/img/favicon.png">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/bootstrap.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/animate.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/owl.carousel.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/themify-icons.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/flaticon.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/fontawesome/css/all.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/magnific-popup.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/gijgo.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/nice-select.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/slick.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/style.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/sweetalert2.min.css">
    
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front-end/shop/css/layout_base.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front-end/shop/css/type_base.css">

	<style type="text/css">
	    body.products ul#nav5 a.w1-1 {
	        width: 230px !important;
	        height: 34px !important;
	    }
	    body.products .ui-tabs .ui-tabs-panel {
	        width: 920px !important;
	    } 
		.leftside {
		    background: none repeat scroll 0 0 transparent;
		    float: left;
		}
		.rightside {
			float: right;
			height: 100%;
			overflow-x: hidden;
		}

		.nav {
			display: block !important;
		}
		.container_48 {
			top: 8em;
			margin-left:auto;
			margin-right:auto;
			width: 1250px !important;
			lefet: ; 
		}
		.side1 {
			top: 10em;
	        position: fixed; 
	        width: 240px;
	        border:5px solid #111;
	        border-radius: 15px;
	    }
	    .sec2 {
	    	text-align:center;
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
</head>

<body class="products" style="padding-top: 0px;">
	<%@ include file="/front-end/TopHeader.file" %>
	<div class="container">
		<div class="main_bg">
			<div class="main">
				<div class="side1">
					<div class="card-header col-12">
			 			會員：<b style="color: #fe5c24;"><%=memberVO.getM_name()%></b> 帳號：<b  style="color: #007bff;"><a href="JavaScript:openIT()"><%=memberVO.getMem_id()%></a></b>
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
				
				<div class="content" style="left: 300px">
					<div class="sec2">
						<div id="tabs" class="ui-tabs ui-widget ui-widget-content ui-corner-all">
							<ul id="nav5" class="nav_h ui-tabs-nav ui-helper-reset ui-helper-clearfix ui-widget-header ui-corner-all">
								<li class="ui-state-default ui-corner-top ui-tabs-active ui-state-active">
									<a href="#tabs-1" class="w1-1 ui-tabs-anchor"id="ui-id-1">會員資料</a>
								</li>
								<li class="ui-state-default ui-corner-top">
									<a href="#tabs-2" class="w1-1 ui-tabs-anchor"id="ui-id-2">團長評價</a>
								</li>
								<li class="ui-state-default ui-corner-top">
									<a href="#tabs-3" class="w1-1 ui-tabs-anchor"id="ui-id-3">需求代購評價</a>
								</li>
								<li class="ui-state-default ui-corner-top">
									<a href="#tabs-4" class="w1-1 ui-tabs-anchor"id="ui-id-4">提供代購評價</a>
								</li>
							</ul>
							<div id="tabs-1" class="ui-tabs-panel ui-widget-content ui-corner-bottom" style="height: 500px;">
								
							<div style="display:flex;'">	
								<div class="col-4" style="margin-top:65px;">
									<img height="250" onerror="javascript:this.src='<%=request.getContextPath()%>/NoData/no_photo.jpg'" src="data:image/png;base64,${memberVO.m_photo}">
								</div>
								<div class="col-8" style="margin-top:20px;">
								<h2>會員資料</h2>
										<div class="col-10" style="margin:auto;">
											<table class="table table-hover table-bordered h6 col-12">
												<tbody>
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
							
							
							
							
							</div>
							
							<div id="tabs-2" class="ui-tabs-panel ui-widget-content ui-corner-bottom" style="display: none;height: 500px;">
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
							
							<div id="tabs-3" class="ui-tabs-panel ui-widget-content ui-corner-bottom" style="display: none;height: 500px;">
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
							
							<div id="tabs-4" class="ui-tabs-panel ui-widget-content ui-corner-bottom" style="display: none;height: 500px;">
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
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	 <%@ include file="/front-end/EndFooter.file" %>

	<script type="text/javascript" src="<%=request.getContextPath()%>/front-end/shop/js/jquery3_3_1.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/front-end/shop/js/jquery-ui.min.js"></script>

	<script type="text/javascript">
		$(function () {
			$("#tabs").tabs();
		});
	</script>

</body>
</html>