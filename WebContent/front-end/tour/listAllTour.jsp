<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.tour.model.*"%>
<%-- �����m�߱ĥ� EL ���g�k���� --%>

<%
	TourService tourSvc = new TourService();
    List<TourVO> list = tourSvc.getAll();
    pageContext.setAttribute("list",list);
%>


<html>
<head>
<title>�Ҧ���{��� - listAllTour.jsp</title>

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
		 <h3>�Ҧ���{��� - listAllTour.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/front-end/tour/select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">�^����</a></h4>
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
<jsp:useBean id="tour_detailSvc" class="com.tour_detail.model.Tour_detailService"/>
<table>
	<tr>
		<th>��{�s��</th>
		<th>�|���s��</th>
		<th>��{�W��</th>
		<th>��{²��</th>
		<th>�Ϥ�</th>
		<th>�W�U�[</th>
		<th>�ק�</th>
		<th>�R��</th>
		<th>��{�Ա�</th>
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="tourVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
				<td>${tourVO.tour_id}</td>
				<td>${tourVO.mem_no}</td>
				<td>${tourVO.tour_name}</td>
				<td>${tourVO.tour_detail}</td>
				<td><img src='<%=request.getContextPath()%>/DBGifReader4Tour?tour_id=${tourVO.tour_id}'></td>
				<td>${tourVO.display}</td>
				<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/tour/tour.do" style="margin-bottom: 0px;">
			     <input type="submit" value="�ק�">
			     <input type="hidden" name="tour_id"  value="${tourVO.tour_id}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/tour/tour.do" style="margin-bottom: 0px;">
			     <input type="submit" value="�R��">
			     <input type="hidden" name="tour_id"  value="${tourVO.tour_id}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
			<td>
<!-- 			�m�ߥ�JSTL�]�Ȩ�����MV03����EL�I�s��k�覡,���olist��size�ӳ]��,�N�S��list��size�s���e�X�䰵disabled���B�z -->
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/tour/tour.do" style="margin-bottom: 0px;">
			  	<c:set var="listsize" value="${tour_detailSvc.getOneTourShowDetail(tourVO.tour_id).size()}"/>
			  	${listsize}�����
			     <input type="submit" value="�Ա�"${(listsize==0)?"disabled":""}>
			     <input type="hidden" name="tour_id"  value="${tourVO.tour_id}">
			     <input type="hidden" name="action" value="getOneTour_Show_Detail"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>