<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.trip_list.model.*"%>
<%-- �����Ƚm�߱ĥ� Script ���g�k���� --%>

<%
  Trip_listVO trip_listVO = (Trip_listVO) request.getAttribute("trip_listVO"); //EmpServlet.java(Concroller), �s�Jreq��empVO����
%>

<html>
<head>
<title>���W���θ�� - listOneTrip_list.jsp</title>

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
  	width:300px;
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
		 <h3>���W���θ�� - ListOneTrip_list.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/front-end/trip_list/select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">�^����</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>���νs��</th>
		<th>�|���s��</th>
		<th>���W�ɶ�</th>
		<th>���쪬�A</th>
		<th>��������</th>
		<th>�������e</th>
		<th>�����ק�ɶ�</th>
	</tr>
	<tr>
			<td>${trip_listVO.trip_id}</td>
			<td>${trip_listVO.mem_no}</td>
			<td>${trip_listVO.reg_time}</td>
			<td>${trip_listVO.check_status}</td>
			<td>${trip_listVO.rate}</td>
			<td>${trip_listVO.comment_content}</td>
			<td>${trip_listVO.comment_time}</td>
	</tr>
</table>

</body>
</html>