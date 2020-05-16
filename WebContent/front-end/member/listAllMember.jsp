<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*"%>
<%@ page import="com.member.model.*" %>    


<%
	MemberService MemberSvc=new MemberService();
	List<MemberVO> list =MemberSvc.getAll();
	pageContext.setAttribute("list",list);
	//下面第74行會在pagescope getList出來
%>

<html>
<head>

<title>所有會員資料 </title>

</head>
<body>

<h4>此頁練習採用 EL 的寫法取值:</h4>
<table>
	<tr>
		<td>
			<h3>所有會員資料</h3>
			<h4><a href="select_page.jsp">回首頁</a></h4>
		</td>
	</tr>
</table>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>




<table border="1px solid black">
	<tr>
		<th>會員編號</th>
		<th>會員帳號</th>
		<th>密碼</th>
		<th>姓名</th>
		<th>性別</th>
		<th>手機</th>
		<th>Email</th>
		<th>地址</th>
		<th>頭像照片</th>
		<th>身分證照片</th>
		<th>會員驗證狀態</th>
		<th>團長驗證狀態</th>
		<th>代購驗證狀態</th>
		<th>註冊時間</th>
		<th>會員錢包餘額</th>
		<th>需求代購評分總人數</th>
		<th>需求代購評分總分</th>
		<th>提供代購評分總人數</th>
		<th>提供代購評分總分</th>
		<th>團長評分總人數</th>
		<th>團長評分總分</th>
		<th>修改</th>
		<th>刪除</th>
	</tr>
	<%@ include file="page1.file" %>
	<c:forEach var="memberVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
	<tr>
		<td>${memberVO.mem_no}</td>
		<td>${memberVO.mem_id}</td>
		<td>${memberVO.psw}</td>
		<td>${memberVO.m_name}</td>
		<td>${memberVO.gender == 0 ? 'MAle':'Female'}</td>
		<td>${memberVO.cellphone}</td>
		<td>${memberVO.email}</td>
		<td>${memberVO.address}</td>
		<td><img width="100" onerror="javascript:this.src='/DA106G6/NoData/no_photo.jpg'" src="data:image/png;base64,${memberVO.m_photo}"></td>
		<td><img width="100" src="<%=request.getContextPath()%>/MemeberId_cardReader?mem_no=${memberVO.mem_no}"></td>		
		<td>
			<c:set var="mem_verify" value="${memberVO.mem_verify}"/>
			<c:choose>
			    <c:when test="${mem_verify== 0}">
					未驗證
			    </c:when>
			    <c:when test="${mem_verify== 1}">
					已驗證
			    </c:when>
			    <c:when test="${mem_verify== 2}">
					會員停權
			    </c:when>
			</c:choose>
		</td>
		<td>
			<c:set var="leader_verify" value="${memberVO.leader_verify}"/>
			<c:choose>
			    <c:when test="${leader_verify== 0}">
					未驗證
			    </c:when>
			    <c:when test="${leader_verify== 1}">
					等待驗證
			    </c:when>
			    <c:when test="${leader_verify== 2}">
					已驗證
			    </c:when>
			    <c:when test="${leader_verify== 3}">
					團長權限停權
			    </c:when>
			</c:choose>
		</td>
		<td>
			<c:set var="pur_verify" value="${memberVO.pur_verify}"/>
			<c:choose>
			    <c:when test="${pur_verify== 0}">
					未驗證
			    </c:when>
			    <c:when test="${pur_verify== 1}">
					等待驗證
			    </c:when>
			    <c:when test="${pur_verify== 2}">
					已驗證
			    </c:when>
			    <c:when test="${pur_verify== 3}">
					代購權限停權
			    </c:when>
			</c:choose>
		</td>
		<td><fmt:formatDate value="${memberVO.reg_time}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
		<td>${memberVO.acc_balance}</td>
		<td>${memberVO.de_score_amount}</td>
		<td>${memberVO.de_total_score}</td>
		<td>${memberVO.pur_score_amount}</td>
		<td>${memberVO.pur_total_score}</td>
		<td>${memberVO.leader_score_amount}</td>
		<td>${memberVO.leader_total_score}</td>
		<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/member/member.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="mem_no"  value="${memberVO.mem_no}">
			     <input type="hidden" name="action"	value="getOne_For_Update">
			  </FORM>
		</td>
		<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/member/member.do" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="mem_no"  value="${memberVO.mem_no}">
			     <input type="hidden" name="action" value="delete">
			  </FORM>
		</td>
	</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>


</body>
</html>