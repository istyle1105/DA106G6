<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.tour_detail.model.*"%>
<%-- �����m�߱ĥ� EL ���g�k���� --%>

<%
    Tour_detailService tour_detailSvc = new Tour_detailService();
    List<Tour_detailVO> list = tour_detailSvc.getAll();
    pageContext.setAttribute("list",list);
%>

<jsp:useBean id="tourSvc" class="com.tour.model.TourService"/>
<jsp:useBean id="spotSvc" class="com.spot.model.SpotService"/>
<html>
<head>
<title>�Ҧ���{��� - listAllEmp.jsp</title>

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
		 <h3>�Ҧ���{�Ӹ`��� - listAllEmp.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/front-end/tour_detail/select_page.jsp"><img src="<%=request.getContextPath()%>/NoData/null2.jpg" width="100" height="32" border="0">�^����</a></h4>
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
		<th>��{�Ӹ`�s��</th>
		<th>��{�W��</th>
		<th>���I�W��</th>
		<th>�}�l�ɶ�</th>
		<th>���d�ɶ�</th>
		<th>���ʴy�z</th>
		<th>���ʹϤ�</th>
		<th>�ק�</th>
		<th>�R��</th>
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="tour_detailVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td>${tour_detailVO.tour_detail_id}</td>
			<td>${tourSvc.getOneTour(tour_detailVO.tour_id).tour_name}</td>
			<td>${spotSvc.getOneSpot(tour_detailVO.spot_id).spot_name}</td>
			<td><fmt:formatDate value='${tour_detailVO.start_time}' pattern='yyyy-MM-dd HH:mm'/></td>
			<td>${tour_detailVO.stay_time}����</td>
			<td>${tour_detailVO.act_descrip}</td>
			<td><img src='<%=request.getContextPath()%>/DBGifReader4Tour_detail?tour_detail_id=${tour_detailVO.tour_detail_id}'></td>
			
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/tour_detail/tour_detail.do" style="margin-bottom: 0px;">
			     <input type="submit" value="�ק�">
			     <input type="hidden" name="tour_detail_id"  value="${tour_detailVO.tour_detail_id}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/tour_detail/tour_detail.do" style="margin-bottom: 0px;">
			     <input type="submit" value="�R��">
			     <input type="hidden" name="tour_detail_id"  value="${tour_detailVO.tour_detail_id}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>