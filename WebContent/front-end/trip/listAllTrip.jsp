<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.trip.model.*"%>
<%-- �����m�߱ĥ� EL ���g�k���� --%>

<%
	TripService tripSvc = new TripService();
    List<TripVO> list = tripSvc.getAll();
    pageContext.setAttribute("list",list);
%>


<html>
<head>
<title>�Ҧ����θ�� - listAllTrip.jsp</title>

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
		 <h3>�Ҧ����θ�� - listAllTrip.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/front-end/trip/select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">�^����</a></h4>
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
		<th>�ק�</th>
		<th>�R��</th>
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="tripVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
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
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/trip/trip.do" style="margin-bottom: 0px;">
			     <input type="submit" value="�ק�">
			     <input type="hidden" name="trip_id"  value="${tripVO.trip_id}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/trip/trip.do" style="margin-bottom: 0px;">
			     <input type="submit" value="�R��">
			     <input type="hidden" name="trip_id"  value="${tripVO.trip_id}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>