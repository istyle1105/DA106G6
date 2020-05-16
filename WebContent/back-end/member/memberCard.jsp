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
</style>

</head>


<body>
<div class="row col-10" style="margin: 40px auto;">
<form>
<fieldset class="border border-top border-dark p-2 rounded">
<legend class="w-auto">會員名片</legend>
<table class="table table-active" style="table-layout:fixed ;border:2px solid black;">
	<tr>
		<td colspan="2" rowspan="5">
		<img weight="200" onerror="javascript:this.src='<%=request.getContextPath()%>/NoData/no_photo.jpg'" src="data:image/png;base64,${memberVO.m_photo}">
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

<%@ include file="/front-end/EndFooter.file" %>

</body>

</html>