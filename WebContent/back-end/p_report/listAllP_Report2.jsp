<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*"%>
<%@ page import="com.p_report.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
	HashMap<Integer, String> reportStatus = new HashMap();
	reportStatus.put(0, "未處理");
	reportStatus.put(1, "已處理");
	pageContext.setAttribute("reportStatus", reportStatus);
%>

<%
	P_ReportService p_reportSvc = new P_ReportService();
    List<P_ReportVO> list = p_reportSvc.getAll();
    pageContext.setAttribute("list",list);
%>


<html>
<head>
<title>所有需求資料 - listAllP_Report.jsp</title>

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

<h4>此頁練習採用 EL 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>所有檢舉資料 - listAllP_Report.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
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

<table>
	<tr>
		<th>檢舉編號</th>
		<th>需求編號</th>
		<th>會員編號</th>
		<th>檢舉原因</th>
		<th>檢舉狀態</th>
		<th>檢舉時間</th>
		<th>修改</th>
		<th>刪除</th>
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="p_reportVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
	
		<tr>
			<td>${p_reportVO.p_re_no}</td>
			<td>${p_reportVO.de_no}</td>
			<td>${p_reportVO.mem_no}</td>
			<td>${p_reportVO.p_re_reason}</td>
			<td>
				<c:forEach var="p_re_status" items="${reportStatus}">
					${p_reportVO.p_re_status==p_re_status.key ? p_re_status.value : ''}
				</c:forEach>
			</td>
			<td><fmt:formatDate value="${p_reportVO.p_re_time}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/p_report/p_report.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="p_re_no"  value="${p_reportVO.p_re_no}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/p_report/p_report.do" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="p_re_no"  value="${p_reportVO.p_re_no}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>