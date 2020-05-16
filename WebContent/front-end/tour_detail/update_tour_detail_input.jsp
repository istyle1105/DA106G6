<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.tour_detail.model.*"%>
<%@ page import="com.spot.model.*"%>
<%@ page import="com.member.model.*"%>
<%
	String mem_no=(String)session.getAttribute("mem_no");
	MemberService Membersvc=new MemberService();
	MemberVO memberVO=null;
	memberVO=Membersvc.getOneMember(mem_no);
	pageContext.setAttribute("memberVO", memberVO);		
	
%> 
<%
	Tour_detailVO tour_detailVO = (Tour_detailVO) request.getAttribute("tour_detailVO"); //EmpServlet.java (Concroller) �s�Jreq��empVO���� (�]�A�������X��empVO, �]�]�A��J��ƿ��~�ɪ�empVO����)
	SpotService spotSvc = new SpotService();
	SpotVO spotVO = spotSvc.getOneSpot(tour_detailVO.getSpot_id());
%>

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
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
  }
  table, th, td {
    border: 0px solid #CCCCFF;
  }
  th, td {
    padding: 1px;
  }
  
  .xdsoft_datetimepicker .xdsoft_datepicker {
           width:  300px;   /* width:  300px; */
  }
  .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
           height: 151px;   /* height:  151px; */
  }

</style>

</head>
<body bgcolor='white'>
<%@ include file="/front-end/TopHeader.file" %>
<div id="content" class="container">

<!-- <table id="table-1"> -->
<!-- 	<tr><td> -->
<!-- 		 <h3>��{�Ӹ`��ƭק� - update_tour_detail_input.jsp</h3> -->
<!-- 	</td></tr> -->
<!-- </table> -->

<h3>��ƽs��:</h3>

<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<div id="output" style="width:400px;"><img width="400" src='<%=request.getContextPath()%>/DBGifReader4Tour_detail?tour_detail_id=${tour_detailVO.tour_detail_id}'></div>
<!-- �W�ǹϤ��w�� -->
<FORM METHOD="POST" ACTION="tour_detail.do" name="form1" enctype="multipart/form-data">
<table class="table">
<!-- 	<tr> -->
<!-- 		<td>��{�Ӹ`�s��:<font color=red><b>*</b></font></td> -->
<%-- 		<td><%=tour_detailVO.getTour_detail_id()%></td> --%>
<!-- 	</tr> -->
<!-- 	<tr> -->
<!-- 		<td>��{�s��:<font color=red><b>*</b></font></td> -->
<%-- 		<td><%=tour_detailVO.getTour_id()%></td> --%>
<!-- 	</tr> -->
	<tr>
		<td>���I�W��:<font color=red><b>*</b></font></td>
		<td><%=spotVO.getSpot_name()%></td>
	</tr>
	<tr>
		<td>�}�l�ɶ�:</td>
		<td><input name="start_time" id="f_date1" type="text" value="<fmt:formatDate value='${tour_detailVO.start_time}' pattern='yyyy-MM-dd HH:mm'/>"></td>
		</tr>
	<tr>
		<td>���d�ɶ�:</td>
		<td><input type="TEXT" name="stay_time" size="45" value="${tour_detailVO.stay_time}"></td>
	</tr>
	
	<tr>
		<td>��{�Ӹ`�y�z:</td>
		<td><input type="TEXT" name="act_descrip" size="45"	value="<%=(tour_detailVO.getAct_descrip()==null)?"":tour_detailVO.getAct_descrip()%>" /></td>
	</tr>
	<tr>
	<!-- �W�ǹϤ��ɮ� -->
		<td>��{���ʹϤ�:</td>
		<td><input type="file"  name="act_pic"  onchange="loadFile(event)"></td>
	</tr>

	
</table>

<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="tour_detail_id" value="<%=tour_detailVO.getTour_detail_id()%>">
<input type="hidden" name="tour_id" value="<%=tour_detailVO.getTour_id()%>">
<input type="hidden" name="spot_id" value="<%=tour_detailVO.getSpot_id()%>">
<input type="hidden" name="requestURL" value="<%=request.getParameter("requestURL")%>"> <!--������e�X�ק諸�ӷ��������|��,�A�e��Controller�ǳ���椧��-->
<input type="hidden" name="whichPage"  value="<%=request.getParameter("whichPage")%>">  <!--�u�Ω�:istAllEmp.jsp-->
<input type="submit" value="�s�觹��"></FORM>

</div>
<%@ include file="/front-end/EndFooter.file" %>
</body>



<!-- =========================================�H�U�� datetimepicker �������]�w========================================== -->

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>



<script>
	$.datetimepicker.setLocale('zh');
	$('#f_date1').datetimepicker({
		theme : '', //theme: 'dark',
		timepicker : true, //timepicker:true,
		step : 5, //step: 60 (�o�Otimepicker���w�]���j60����)
		format : 'Y-m-d H:i', //format:'Y-m-d H:i:s',
		value : "<fmt:formatDate value='${empVO.start_time}' pattern='yyyy-MM-dd HH:mm'/>"  // value:   new Date(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // �h���S�w���t
           //startDate:	            '2017/07/10',  // �_�l��
           //minDate:               '-1970-01-01', // �h������(���t)���e
           //maxDate:               '+1970-01-01'  // �h������(���t)����
        });
        
        
   
        // ----------------------------------------------------------�H�U�ΨӱƩw�L�k��ܪ����-----------------------------------------------------------

        //      1.�H�U���Y�@�Ѥ��e������L�k���
        //      var somedate1 = new Date('2017-06-15');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() <  somedate1.getYear() || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});

        
        //      2.�H�U���Y�@�Ѥ��᪺����L�k���
        //      var somedate2 = new Date('2017-06-15');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() >  somedate2.getYear() || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});


        //      3.�H�U����Ӥ�����~������L�k��� (�]�i���ݭn������L���)
        //      var somedate1 = new Date('2017-06-15');
        //      var somedate2 = new Date('2017-06-25');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() <  somedate1.getYear() || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
        //		             ||
        //		            date.getYear() >  somedate2.getYear() || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});
        
    //�Ϥ��w��
	var loadFile = function(event) {
		var reader = new FileReader();
		reader.onload = function() {
			var output = document.getElementById('output');
			output.innerHTML = "<img id = 'preview'>";
			document.getElementById("preview").src = reader.result;
		};
		reader.readAsDataURL(event.target.files[0]);
	};
       
</script>
</html>