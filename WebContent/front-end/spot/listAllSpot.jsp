<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.spot.model.*"%>
<%-- �����m�߱ĥ� EL ���g�k���� --%>

<%
    SpotService spotSvc = new SpotService();
    List<SpotVO> list = spotSvc.getAll();
    pageContext.setAttribute("list",list);
%>


<html>
<head>
<title>�Ҧ����I��� - listAllspot.jsp</title>

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
		 <h3>�Ҧ����I��� - listAllSpot.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/front-end/spot/select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">�^����</a></h4>
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
		<th>���I�s��</th>
		<th>���I����</th>
		<th>���I�W��</th>
		<th>²��</th>
		<th>�a�}</th>
		<th>�W�U�[���A</th>
		<th>�Ϥ�</th>
		<th>�n��</th>
		<th>�g��</th>
		<th>�ק�</th>
		<th>�R��</th>
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="spotVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td>${spotVO.spot_id}</td>
			<td>${spotVO.spot_type}</td>
			<td>${spotVO.spot_name}</td>
			<td>${spotVO.spot_intro}</td>
			<td>${spotVO.address}</td> 
			<td>${spotVO.spot_status}</td>
			<td><img src='<%=request.getContextPath()%>/DBGifReader4?spot_id=${spotVO.spot_id}'></td>
			<td>${spotVO.lat}</td>
			<td>${spotVO.lng}</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/spot/spot.do" style="margin-bottom: 0px;">
			     <input type="submit" value="�ק�">
			     <input type="hidden" name="spot_id"  value="${spotVO.spot_id}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/spot/spot.do" style="margin-bottom: 0px;">
			     <input type="submit" value="�R��">
			     <input type="hidden" name="spot_id"  value="${spotVO.spot_id}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>