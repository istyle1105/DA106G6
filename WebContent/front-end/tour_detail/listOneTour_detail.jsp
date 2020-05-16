<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.tour_detail.model.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%-- �����Ƚm�߱ĥ� Script ���g�k���� --%>

<%
	Tour_detailVO tour_detailVO = (Tour_detailVO) request.getAttribute("tour_detailVO"); //EmpServlet.java(Concroller), �s�Jreq��empVO����
%>
<jsp:useBean id="tourSvc" class="com.tour.model.TourService"/>
<jsp:useBean id="spotSvc" class="com.spot.model.SpotService"/>
<html>
<head>
<title>��{�Ӹ`��� - listOneTour_detail.jsp</title>

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
		 <h3>��{�Ӹ`��� - listOneTour_detail.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/front-end/tour_detail/select_page.jsp"><img src="<%=request.getContextPath()%>/NoData/null2.jpg" width="100" height="32" border="0">�^����</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>��{�Ӹ`�s��</th>
		<th>��{�W��</th>
		<th>���I�W��</th>
		<th>�}�l�ɶ�</th>
		<th>���d�ɶ�</th>
		<th>���ʴy�z</th>
		<th>���ʹϤ�</th>
	</tr>
	<tr>
		<td>${tour_detailVO.tour_detail_id}</td>
		<td>${tourSvc.getOneTour(tour_detailVO.tour_id).tour_name}</td>
		<td>${spotSvc.getOneSpot(tour_detailVO.spot_id).spot_name}</td>
		<td><fmt:formatDate value='${tour_detailVO.start_time}' pattern='yyyy-MM-dd HH:mm'/></td>
		<td>${tour_detailVO.stay_time}����</td>
		<td>${tour_detailVO.act_descrip}</td>
		<td><img src='<%=request.getContextPath()%>/DBGifReader4Tour_detail?tour_detail_id=${tour_detailVO.tour_detail_id}'></td>
	</tr>
</table>

</body>
</html>