<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.p_report.model.*"%>

<%
  P_ReportVO p_reportVO = (P_ReportVO) request.getAttribute("p_reportVO"); //EmpServlet.java (Concroller) �s�Jreq��empVO���� (�]�A�������X��empVO, �]�]�A��J��ƿ��~�ɪ�empVO����)
%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>���|��ƭק� - update_p_report_input.jsp</title>

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
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
  }
  table, th, td {
    border: 0px solid #CCCCFF;
  }
  th, td {
    padding: 1px;
  }
</style>

</head>
<body bgcolor='white'>

<table id="table-1">
	<tr><td>
		 <h3>���|��ƭק� - update_p_report_input.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">�^����</a></h4>
	</td></tr>
</table>

<h3>��ƭק�:</h3>

<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="p_report.do" name="form1" enctype="multipart/form-data">
<table>
	<tr>
		<td>���|�s��:<font color=red><b>*</b></font></td>
		<td><%=p_reportVO.getP_re_no()%></td>
	</tr>
	<tr>
		<td>�ݨD�s��:</td>
		<td>${p_reportVO.de_no}</td> 
	</tr>
	<tr>
		<td>���|���e:</td>
		<td><textarea rows="10" cols="45" name="p_re_reason">${p_reportVO.p_re_reason}</textarea></td>
	</tr>
	<tr>
		<td>���|���A:</td>
		<td>${p_reportVO.p_re_status}</td> 
	</tr>
	<tr>
		<td>���|�ɶ�:</td>
		<td><fmt:formatDate value="${p_reportVO.p_re_time}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	</tr>

	<jsp:useBean id="p_reportSvc" scope="page" class="com.p_report.model.P_ReportService" />
	
</table>

<input type="hidden" name="action" value="update">
<input type="hidden" name="p_re_no" value="<%=p_reportVO.getP_re_no()%>">
<input type="hidden" name="de_no" value="${p_reportVO.de_no}">
<input type="hidden" name="p_re_reason" value="${p_reportVO.p_re_reason}">
<input type="hidden" name="p_re_status" value="${p_reportVO.p_re_status}">
<input type="hidden" name="p_re_time" value="${p_reportVO.p_re_time}">
<input type="submit" value="�e�X�ק�"></FORM>


</body>
</html>