<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.p_report.model.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%-- �����Ƚm�߱ĥ� Script ���g�k���� --%>

<%
	P_ReportVO p_reportVO = (P_ReportVO) request.getAttribute("p_reportVO"); //DemandServlet.java(Concroller), �s�Jreq��demandVO����
%>

<html>
<head>
<title>���|��� - listOneP_Report.jsp</title>

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
	width: 600px;
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

<h4>�����Ƚm�߱ĥ� Script ���g�k����:</h4>
<table id="table-1">
	<tr><td>
		 <h3>���|��� - ListOneP_Report.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">�^����</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>���|�s��</th>
		<th>�ݨD�s��</th>
		<th>���|���A</th>
		<th>���|��]</th>
		<th>���|�ɶ�</th>
	</tr>
	<tr>
		<td>${p_reportVO.p_re_no}</td>
		<td>${p_reportVO.de_no}</td>
		<td>
			<c:forEach var="p_re_status" items="${reportStatus}">
				${p_reportVO.p_re_status==p_re_status.key ? p_re_status.value : ''}
			</c:forEach>
		</td>
		<td>${p_reportVO.p_re_reason}</td>
		<td><fmt:formatDate value="${p_reportVO.p_re_time}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	</tr>
</table>

</body>
</html>