<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.trip.model.*"%>
<%@ page import="java.util.*"%>

<%-- �U�νƦX�d��-�i�ѫȤ��select_page.jsp�H�N�W�����Q�d�ߪ���� --%>
<%-- �����u�@���ƦX�d�߮ɤ����G�m�ߡA�i���ݭn�A�W�[�����B�e�X�ק�B�R�����\��--%>
<%	
	TripService tripSvc = new TripService(); 
	List<TripVO> list = tripSvc.getAll(new HashMap<String,String[]>());
	request.setAttribute("listTrips_ByCompositeQuery", list);
%>
<jsp:useBean id="listTrips_ByCompositeQuery" scope="request" type="java.util.List<TripVO>" /> <!-- ��EL����i�ٲ� -->
<jsp:useBean id="tourSvc" scope="page" class="com.tour.model.TourService" />


<html>
<head>
<meta charset="utf-8">
<!--     <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"> -->
<!--     <title>Let's Go Trip</title> -->
<%--     <link rel="icon" href="<%=request.getContextPath()%>/front-end/img/favicon.png"> --%>
<!--     Bootstrap CSS -->
<%--     <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/bootstrap.min.css"> --%>
<!--     animate CSS -->
<%--     <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/animate.css"> --%>
<!--     owl carousel CSS -->
<%--     <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/owl.carousel.min.css"> --%>
<!--     themify CSS -->
<%--     <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/themify-icons.css"> --%>
<!--     flaticon CSS -->
<%--     <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/flaticon.css"> --%>
<!--     fontawesome CSS -->
<%--     <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/fontawesome/css/all.min.css"> --%>
<!--     magnific CSS -->
<%--     <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/magnific-popup.css"> --%>
<%--     <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/gijgo.min.css"> --%>
<!--     niceselect CSS -->
<%--     <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/nice-select.css"> --%>
<!--     slick CSS -->
<%--     <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/slick.css"> --%>
<!--     style CSS -->
<%--     <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/style.css"> --%>
<%--     <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/sweetalert2.min.css"> --%>


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
  td>img{
  	width:150px;
  }
</style>

</head>
<body bgcolor='white'>

<div id="content">
<!-- <table id="table-1"> -->
<!-- 	<tr><td> -->
<!-- 		 <h3>�Ҧ����θ�� - listAllEmp.jsp</h3> -->
<!-- 	</td></tr> -->
<!-- </table> -->
<%-- <%@ include file="/front-end/trip/searchPage.file" %> --%>

<table class="table table-striped table-hover">
	<tr>
		<th>���νs��</th>
<!-- 		<th>�|���s��</th> -->
		<th>��{�W��</th>
		<th>�Ѽ�</th>
		<th>�w�����B</th>
		<th>�_�l���</th>
		<th>�������</th>
		<th>���W�}�l���</th>
		<th>���W�I����</th>
		<th>�ثe�H��</th>
		<th>���ΤH��</th>
		<th>�H�ƤW��</th>
		<th>���Ϊ��A</th>
<!-- 		<th>�W�[���A</th> -->
<!-- 		<th>�̫�ק�ɶ�</th> -->
		<th>��{�Ա�</th>
		<th>�ѥ[��{</th>
<!-- 		<th>�l�ܦ�{</th> -->
		<th>���|��{</th>
	</tr>
	<%@ include file="page1_ByCompositeQuery.file" %>
	<c:forEach var="tripVO" items="${listTrips_ByCompositeQuery}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr align='center' valign='middle' ${(tripVO.trip_id==param.trip_id) ? 'bgcolor=#CCCCFF':''}><!--�N�ק諸���@���[�J����Ӥw-->
			<td>${tripVO.trip_id}</td>
<%-- 			<td>${tripVO.mem_no}</td> --%>
			<td>${tourSvc.getOneTour(tripVO.tour_id).tour_name}</td>
			<td>${tripVO.days}</td>
			<td>${tripVO.trip_price}</td>
			<td>${tripVO.first_date}</td>
			<td>${tripVO.last_date}</td>
			<td>${tripVO.reg_start}</td>
			<td>${tripVO.reg_deadline}</td>
			<td>${tripVO.current_mem}</td>
			<td>${tripVO.mem_amount}</td>
			<td>${tripVO.mem_limited}</td>
			<td>${tripStatus.get(tripVO.trip_status)}</td>
<%-- 			<td>${tripVO.tour_status}</td> --%>
<%-- 			<td>${tripVO.last_mod_time}</td> --%>
			
			<td>
			<img src='<%=request.getContextPath()%>/DBGifReader4Tour?tour_id=${tripVO.tour_id}'>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/tour/tour.do" style="margin-bottom: 0px;">
			     <input type="submit" value="�Ա�"> 
			     <input type="hidden" name="tour_id"      value="${tripVO.tour_id}">
			     <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--�e�X�����������|��Controller-->
			     <input type="hidden" name="whichPage"	value="<%=whichPage%>">               <!--�e�X��e�O�ĴX����Controller-->
			     <input type="hidden" name="action"	    value="getOneTour_Show_Detail"></FORM>
			</td>
			<td>
				<input type="button" ${(tripVO.mem_no==mem_no)?"disabled value='�ΥD'":"value='�ѥ['" }  onclick="location.href='<%=request.getContextPath()%>/front-end/trip_list/addTrip_list.jsp?trip_id=${tripVO.trip_id}'">
<%-- 			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/trip_list/addTrip_list.jsp" style="margin-bottom: 0px;"> --%>
<!-- 			     <input type="submit" value="�ѥ["> -->
<%-- 			     <input type="hidden" name="mem_no"      value="${mem_no}"> --%>
<%-- 			      <input type="hidden" name="trip_id"    value="${tripVO.trip_id}"> --%>
<%-- 			     <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--�e�X�����������|��Controller--> --%>
<%-- 			     <input type="hidden" name="whichPage"	value="<%=whichPage%>">               <!--�e�X��e�O�ĴX����Controller--> --%>
<!-- 			     <input type="hidden" name="action"     value="delete"></FORM> -->
			</td>
<!-- 			<td> -->
<%-- 			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/trip_list/trip_list.do" style="margin-bottom: 0px;"> --%>
<!-- 			     <input type="submit" value="�l��"> -->
<%-- 			     <input type="hidden" name="mem_no"      value="${mem_no}"> --%>
<%-- 			      <input type="hidden" name="trip_id"    value="${tripVO.trip_id}"> --%>
<%-- 			     <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--�e�X�����������|��Controller--> --%>
<%-- 			     <input type="hidden" name="whichPage"	value="<%=whichPage%>">               <!--�e�X��e�O�ĴX����Controller--> --%>
<!-- 			     <input type="hidden" name="action"     value="delete"></FORM> -->
<!-- 			</td> -->
			<td>
			<input type="button" ${(tripVO.mem_no==mem_no)?"disabled value='���|'":"value='���|'" }  
			onclick="location.href='<%=request.getContextPath()%>/front-end/trip_report/addTrip_report.jsp?trip_id=${tripVO.trip_id}&mem_no=${mem_no}'">
			
<%-- 			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/trip_report/trip_report.do" style="margin-bottom: 0px;" > --%>
<!-- 			     <input type="submit" value="���|"> -->
<%-- 			     <input type="hidden" name="mem_no"      value="${mem_no}"> --%>
<%-- 			      <input type="hidden" name="trip_id"    value="${tripVO.trip_id}"> --%>
<%-- 			     <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--�e�X�����������|��Controller--> --%>
<%-- 			     <input type="hidden" name="whichPage"	value="<%=whichPage%>">               <!--�e�X��e�O�ĴX����Controller--> --%>
<!-- 			     <input type="hidden" name="action"     value="insert"></FORM> -->
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2_ByCompositeQuery.file" %>

</div>

</body>
</html>