<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.member.model.*"%>
<%@ page import="java.util.*"%>
<%
	MemberService Membersvc=new MemberService();

	String mem_no_info=request.getParameter("mem_no");
	MemberVO memberVO2=null;
	memberVO2=Membersvc.getOneMember(mem_no_info);
	pageContext.setAttribute("memberVO2", memberVO2);	
%>

   
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Let's Go Trip</title>
    <link rel="icon" href="<%=request.getContextPath()%>/front-end/img/favicon.png">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/bootstrap.min.css">

<style>
.table > tbody > tr > td {
     vertical-align: middle;
     text-align: center;
}
.table > thead > tr {
	text-align: center;
	vertical-align: middle!important;
}
.tabcard{_
	overflow:auto;
}


</style>

</head>


<body>



<!-- List group -->
<div class="list-group tabtop col-12" id="myList" role="tablist">
  <a class="list-group-item list-group-item-action active" data-toggle="list" href="#home" role="tab">會員卡片</a>
  <a class="list-group-item list-group-item-action" data-toggle="list" href="#profile" role="tab">團長評價</a>
  <a class="list-group-item list-group-item-action" data-toggle="list" href="#messages" role="tab">需求代購</a>
  <a class="list-group-item list-group-item-action" data-toggle="list" href="#settings" role="tab">提供代購</a>
</div>

<!-- Tab panes -->
<div class="tab-content tabcard" style="margin:auto;">
  <div class="tab-pane active tabcard" id="home" role="tabpanel">
	<form>
	<fieldset class="border border-top border-dark p-2 rounded">
	<legend class="w-auto">會員名片</legend>
	<table class="table table-active" style="table-layout:fixed ;border:2px solid black;">
		<tr>
			<td colspan="2" rowspan="5">
			<img width="200" onerror="javascript:this.src='<%=request.getContextPath()%>/NoData/no_photo.jpg'" src="data:image/png;base64,${memberVO2.m_photo}">
			</td>
			<td>會員帳號</td>
			<td>${memberVO2.mem_id}</td>
		</tr>
		<tr>
			<td>會員姓名</td>
			<td>${memberVO2.m_name}</td>
		</tr>
		<tr>
			<td>會員驗證</td>
			<td>
			<c:forEach var="status" items="${memStatus}">
						${memberVO2.mem_verify==status.key ? status.value : ''}
			</c:forEach>
			</td>
		</tr>
		<tr>
			<td>代購驗證</td>
			<td>
			<c:forEach var="status" items="${purStatus}">
						${memberVO2.pur_verify==status.key ? status.value : ''}
			</c:forEach>
			</td>
		</tr>
		<tr>
			<td>團主驗證</td>
			<td>
			<c:forEach var="status" items="${leaderStatus}">
						${memberVO2.leader_verify==status.key ? status.value : ''}
			</c:forEach>
			</td>
		</tr>	
		<tr>
			<td>需求代購平均分數</td>
			<td>
			<fmt:formatNumber value="${(memberVO2.de_total_score div memberVO2.de_score_amount)!='NaN' ? (memberVO2.de_total_score div memberVO2.de_score_amount) : '0'}"
						type="NUMBER" maxFractionDigits="2"/>
			</td>
			<td>需求代購評價總人數</td>
			<td>${memberVO2.de_score_amount}</td>
		</tr>
		<tr>
			<td>提供代購平均分數</td>
			<td>
			<fmt:formatNumber value="${(memberVO2.pur_total_score div memberVO2.pur_score_amount)!='NaN' ? (memberVO2.pur_total_score div memberVO2.pur_score_amount) : '0'}"
						type="NUMBER" maxFractionDigits="2"/>
			</td>
			<td>提供代購評價總人數</td>
			<td>${memberVO2.pur_score_amount}</td>
		</tr>
		<tr>
			<td>團長平均分數</td>
			<td>
			<fmt:formatNumber value="${(memberVO2.leader_total_score div memberVO2.leader_score_amount)!='NaN' ? (memberVO2.leader_total_score div memberVO2.leader_score_amount) : '0'}"
						type="NUMBER" maxFractionDigits="2"/>
			</td>
			<td>團長評價總人數</td>
			<td>${memberVO2.leader_score_amount}</td>
		</tr>
	</table>
	</fieldset>
	</form>
  </div>
  <jsp:useBean id="trip_listSvc" scope="page" class="com.trip_list.model.Trip_listService"/>
  <jsp:useBean id="tripSvc" scope="page" class="com.trip.model.TripService"/>
  <jsp:useBean id="memberSvc" scope="page" class="com.member.model.MemberService"/>
  <div class="tab-pane tabcard " id="profile" role="tabpanel" style="margin:10px auto;">
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
  <jsp:useBean id="demandSvc" scope="page" class="com.demand.model.DemandService"/>
  <div class="tab-pane tabcard" id="messages" role="tabpanel">
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
  <div class="tab-pane tabcard" id="settings" role="tabpanel">

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




<%@ include file="/front-end/EndFooter.file" %>

</body>

</html>