<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.trip_list.model.*"%>
<%-- �����m�߱ĥ� EL ���g�k���� --%>

<%
	Trip_listService trip_listSvc = new Trip_listService();
    List<Trip_listVO> list = trip_listSvc.getAll();
    pageContext.setAttribute("list",list);
%>


<html>
<head>
<title>�Ҧ����θ�� - listAllTrip_list.jsp</title>

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
</style>

</head>
<body bgcolor='white'>

<h4>�����m�߱ĥ� EL ���g�k����:</h4>
<table id="table-1">
	<tr><td>
		 <h3>�Ҧ����W���θ�� - listAllTrip_list.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/front-end/trip_list/select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">�^����</a></h4>
	</td></tr>
</table>

<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<table>
	<tr>
		<th>���νs��</th>
		<th>�|���s��</th>
		<th>���W�ɶ�</th>
		<th>���쪬�A</th>
		<th>��������</th>
		<th>�������e</th>
		<th>�����ק�ɶ�</th>
		<th>�ק�</th>
		<th>�R��</th>
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="trip_listVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td>${trip_listVO.trip_id}</td>
			<td>${trip_listVO.mem_no}</td>
			<td>${trip_listVO.reg_time}</td>
			<td>${trip_listVO.check_status}</td>
			<td>${trip_listVO.rate}</td>
			<td>${trip_listVO.comment_content}</td>
			<td>${trip_listVO.comment_time}</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/trip_list/trip_list.do" style="margin-bottom: 0px;">
			     <input type="submit" value="�ק�">
			     <input type="hidden" name="trip_id"  value="${trip_listVO.trip_id}">
			     <input type="hidden" name="mem_no"  value="${trip_listVO.mem_no}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/trip_list/trip_list.do" style="margin-bottom: 0px;">
			     <input type="submit" value="�R��">
			     <input type="hidden" name="trip_id"  value="${trip_listVO.trip_id}">
			     <input type="hidden" name="mem_no"  value="${trip_listVO.mem_no}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>