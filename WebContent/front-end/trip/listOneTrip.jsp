<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.trip.model.*"%>
<%-- �����Ƚm�߱ĥ� Script ���g�k���� --%>

<%
  TripVO tripVO = (TripVO) request.getAttribute("tripVO"); //EmpServlet.java(Concroller), �s�Jreq��empVO����
%>

<html>
<head>
<title>���θ�� - listOneTrip.jsp</title>

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
		 <h3>���θ�� - ListOneTrip.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/front-end/trip/select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">�^����</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>���νs��</th>
		<th>�|���s��</th>
		<th>��{�s��</th>
		<th>�Ѽ�</th>
		<th>�w�����B</th>
		<th>�_�l���</th>
		<th>�������</th>
		<th>���W�}�l���</th>
		<th>���W�I����</th>
		<th>���ΤH��</th>
		<th>�ثe�H��</th>
		<th>�H�ƤW��</th>
		<th>���Ϊ��A</th>
		<th>�W�[���A</th>
		<th>�̫�ק�ɶ�</th>
	</tr>
	<tr>
			<td>${tripVO.trip_id}</td>
			<td>${tripVO.mem_no}</td>
			<td>${tripVO.tour_id}</td>
			<td>${tripVO.days}</td>
			<td>${tripVO.trip_price}</td>
			<td>${tripVO.first_date}</td>
			<td>${tripVO.last_date}</td>
			<td>${tripVO.reg_start}</td>
			<td>${tripVO.reg_deadline}</td>
			<td>${tripVO.mem_amount}</td>
			<td>${tripVO.current_mem}</td>
			<td>${tripVO.mem_limited}</td>
			<td>${tripVO.trip_status}</td>
			<td>${tripVO.tour_status}</td>
			<td>${tripVO.last_mod_time}</td>
	</tr>
</table>

</body>
</html>