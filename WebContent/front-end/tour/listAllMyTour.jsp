<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.tour.model.*"%>
<%@ page import="com.tour_detail.model.*"%>
<%-- �����m�߱ĥ� EL ���g�k���� --%>
<%@ page import="com.member.model.*"%>
<%
	String mem_no=(String)session.getAttribute("mem_no");
	MemberService Membersvc=new MemberService();
	MemberVO memberVO=null;
	memberVO=Membersvc.getOneMember(mem_no);
	pageContext.setAttribute("memberVO", memberVO);	
%> 
<%
	TourService listSvc=new TourService();
	List<TourVO> list=listSvc.getAllMyTour(memberVO.getMem_no());
	session.setAttribute("list",list);
	session.setAttribute("tourSvc",listSvc);
	
//     List<TourVO> list = (List<TourVO>)request.getAttribute("list");
// // 	String mem_no = (String)request.getParameter("mem_no");
//     if(list==null||mem_no==null){
//     	list = (List)session.getAttribute("list");
// //     	mem_no = (String)session.getAttribute("mem_no");
//     }
//     session.setAttribute("list",list);
// //     session.setAttribute("mem_no",mem_no);
%>


<html>
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Let's Go Trip</title>
    <link rel="icon" href="<%=request.getContextPath()%>/front-end/img/favicon.png">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/bootstrap.min.css">
    <!-- animate CSS -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/animate.css">
    <!-- owl carousel CSS -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/owl.carousel.min.css">
    <!-- themify CSS -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/themify-icons.css">
    <!-- flaticon CSS -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/flaticon.css">
    <!-- fontawesome CSS -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/fontawesome/css/all.min.css">
    <!-- magnific CSS -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/magnific-popup.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/gijgo.min.css">
    <!-- niceselect CSS -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/nice-select.css">
    <!-- slick CSS -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/slick.css">
    <!-- style CSS -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/style.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/sweetalert2.min.css">


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


<%@ include file="/front-end/TopHeader.file" %>

<div id="content" class="container">

<!-- <table id="table-1"> -->
<!-- 	<tr><td> -->
		 <h3>�ڪ���{</h3>
<!-- 	</td></tr> -->
<!-- </table> -->

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
<form action="<%=request.getContextPath()%>/front-end/tour/addTour.jsp">
  <input type="submit" value="�إ߷s��{">
</form>
<button onclick="location.href='<%=request.getContextPath()%>/front-end/spot/addSpotInMap.jsp'">�s�W���I</button><br>
<table class="table table-striped table-hover">
	<tr>


		<th>��{�W��</th>
		<th>��{²��</th>
		<th>�Ϥ�</th>
		<th>�ק�</th>
		<th>�R��</th>
		<th>��{�Ա�</th>
		<th>�}�Ҵ���</th>
	</tr>
	<%@ include file="page1.file" %> 
	
	<c:forEach var="tourVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>


				<td>${tourVO.tour_name}</td>
				<td>${tourVO.tour_detail}</td>
				<td><img src='<%=request.getContextPath()%>/DBGifReader4Tour?tour_id=${tourVO.tour_id}'></td>

				<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/tour/tour.do" style="margin-bottom: 0px;">
			     <input type="submit"  class="btn btn-primary" ${(tourVO.display==2)?"disabled":""} value="�ק�">
			     <input type="hidden" name="tour_id"  value="${tourVO.tour_id}">
			     <input type="hidden" name="mem_no"  value="${tourVO.mem_no}">
			     <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--�e�X�����������|��Controller-->
			     <input type="hidden" name="whichPage"	value="<%=whichPage%>">               <!--�e�X��e�O�ĴX����Controller-->
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" name=""  ACTION="<%=request.getContextPath()%>/tour/tour.do" style="margin-bottom: 0px;">
			     <input type="submit" class="delete btn btn-danger" ${(tourVO.display==2)?"disabled":""} value="�R��">
			     <input type="hidden" name="tour_id"  value="${tourVO.tour_id}">
			     <input type="hidden" name="mem_no"  value="${tourVO.mem_no}">
			     <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--�e�X�����������|��Controller-->
			     <input type="hidden" name="whichPage"	value="<%=whichPage%>">               <!--�e�X��e�O�ĴX����Controller-->
			     <input type="hidden" name="action" value="tourDisable"></FORM>
			</td>
			
			<td>
<!-- 			�m�ߥ�JSTL�]�Ȩ�����MV03����EL�I�s��k�覡,���olist��size�ӳ]��,�N�S��list��size�s���e�X�䰵disabled���B�z -->
			  	<c:set var="listsize" value="${tour_detailSvc.getOneTourShowDetail(tourVO.tour_id).size()}"/>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/tour/tour.do" style="margin-bottom: 0px;">
			  	${listsize}�����
			     <input type="submit"  class="btn btn-primary" value="�Ա�"${(listsize==0)?"disabled":""}>
			     <input type="hidden" name="tour_id"  value="${tourVO.tour_id}">
			     <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--�e�X�����������|��Controller-->
			     <input type="hidden" name="whichPage"	value="<%=whichPage%>">               <!--�e�X��e�O�ĴX����Controller-->
			     <input type="hidden" name="action" value="getOneTour_Show_Detail"></FORM>
			     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/tour_detail/tour_detail.do" style="margin-bottom: 0px;">
			     <input type="submit"  class="btn btn-primary" ${(tourVO.display==2)?"disabled":""} value="�W����{">
			     <input type="hidden" name="tour_id"  value="${tourVO.tour_id}">
			     <input type="hidden" name="tour_name"  value="${tourVO.tour_name}">
			     <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--�e�X�����������|��Controller-->
			     <input type="hidden" name="whichPage"	value="<%=whichPage%>">               <!--�e�X��e�O�ĴX����Controller-->
			     <input type="hidden" name="action" value="planningTour_Detail"></FORM>
			     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/tour_detail/tour_detail.do" style="margin-bottom: 0px;">
			     <input type="hidden" name="tour_id"  value="${tourVO.tour_id}">
			     <input type="hidden" name="tour_name"  value="${tourVO.tour_name}">
			     <input type="submit"  class="btn btn-primary" value="�a����� " ${(listsize==0)?"disabled":""}>
			     <input type="hidden" name="action" value="showTourDetailInMap"></FORM>
			     <input type="button"  class="btn btn-primary" value='���I²��' ${(listsize==0)?"disabled":""} onclick="location.href='<%=request.getContextPath()%>/front-end/tour_detail/listOneTourOverview.jsp?tour_id=${tourVO.tour_id}'">
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/trip/trip.do" style="margin-bottom: 0px;">
			     <input type="submit"  class="btn btn-primary" value="�}�Ҵ���"${(listsize==0)||(tourVO.display==2)?"disabled":""}>
			     <input type="hidden" name="tour_id"  value="${tourVO.tour_id}">
			     <input type="hidden" name="mem_no"  value="${memberVO.mem_no}">
			     <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--�e�X�����������|��Controller-->
			     <input type="hidden" name="whichPage"	value="<%=whichPage%>">               <!--�e�X��e�O�ĴX����Controller-->
			     <input type="hidden" name="action" value="getOneTourToInsert"></FORM>
			</td>
		</tr>
	</c:forEach>
<%-- 	<input type="hidden" name="mem_no"  value="${param.mem_no}"> --%>
</table>
<%@ include file="page2.file" %>


</div>
<%@ include file="/front-end/EndFooter.file" %>

<script >
$('body').on('click','.delete',function(event){
	event.preventDefault();//������s�w�]�欰 ,�����e�X���U��
	
Swal.fire({
	  title: '�аݽT�w�n�R����?',
	  text: "�R����N�L�k�A�_��",
	  icon: 'warning',
	  showCancelButton: true,
	  confirmButtonColor: '#3085d6',
	  cancelButtonColor: '#d33',
	  confirmButtonText: '�n��!',
	  cancelButtonText:'�٬O���n!'
	}).then((result) => {
	  if (result.value) {
		  $(this).parent().submit();//�����������e�X
		  
	    Swal.fire(
	      '�R�����\',
	      'Your file has been deleted.',
	      'success'
	    )
	  }
	});
	
});

</script>


</body>
</html>