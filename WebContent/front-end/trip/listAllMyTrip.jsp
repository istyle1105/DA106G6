<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.trip.model.*"%>
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
// 	String mem_no = (String)session.getAttribute("mem_no"); //����n��ȱqsession�Ǧn
// 	String mem_no = "M0004";//���եΰ����
	TripService tripSvc = new TripService();
    List<TripVO> list = tripSvc.getAllMyTrip(memberVO.getMem_no());
    pageContext.setAttribute("list",list);
%>
<jsp:useBean id="tourSvc" class ="com.tour.model.TourService"/>


<html>
<head>
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
<%@ include file="/front-end/TopHeader.file" %>
<div id="content" class="container">
<!-- <table id="table-1"> -->
<!-- 	<tr><td> -->
		 <h3>�کҴ����� </h3>
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

<table class="table table-striped table-hover">
	<tr>
		<th>���νs��</th>
		<th>��{�W��</th>
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
		<th>�����W��</th>
		<th>��������</th>
	</tr>
	
	<%@ include file="page1.file" %> 
	<c:forEach var="tripVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td>${tripVO.trip_id}</td>
			<td>${tourSvc.getOneTour(tripVO.tour_id).tour_name}</td>
			<td>${tripVO.days}</td>
			<td>${tripVO.trip_price}</td>
			<td>${tripVO.first_date}</td>
			<td>${tripVO.last_date}</td>
			<td>${tripVO.reg_start}</td>
			<td>${tripVO.reg_deadline}</td>
			<td>${tripVO.mem_amount}</td>
			<td>${tripVO.current_mem}</td>
			<td>${tripVO.mem_limited}</td>
			<td>${tripStatus.get(tripVO.trip_status)}</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/trip/trip.do" style="margin-bottom: 0px;">
			     <input type="submit"  class="btn btn-primary" value="�W��">
			     <input type="hidden" name="trip_id"  value="${tripVO.trip_id}">
			     <input type="hidden" name="action"	value="getOne_Show_Member"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/trip/trip.do" style="margin-bottom: 0px;">
			     <input type="submit"  class="btn btn-danger delete" ${(tripVO.trip_status==2)?"disabled":""} value="����">
			     <input type="hidden" name="trip_id"  value="${tripVO.trip_id}">
			     <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--�e�X�����������|��Controller-->
			     <input type="hidden" name="whichPage"	value="<%=whichPage%>">               <!--�e�X��e�O�ĴX����Controller-->
			     <input type="hidden" name="action" value="cancelTrip"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>
</div>
<%@ include file="/front-end/EndFooter.file" %>

<script >

$('body').on('click','.delete',function(event){
	event.preventDefault();//������s�w�]�欰 ,�����e�X���U��
	
Swal.fire({
	  title: '�аݽT�w�n������?',
	  text: "�������η|�q���Ҧ��έ�",
	  icon: 'warning',
	  showCancelButton: true,
	  confirmButtonColor: '#3085d6',
	  cancelButtonColor: '#d33',
	  confirmButtonText: '�n��!',
	  cancelButtonText:'�٬O���n!'
	}).then((result) => {
	  if (result.value) {
		  
	    Swal.fire(
	      '�������\',
	      'Your file has been deleted.',
	      'success'
	    )
	   	$(this).parent().submit();//�����������e�X
	    
		 
	  }
	});
	
});


</script>

</body>
</html>