<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.trip.model.*"%>

<%
	TripVO tripVO = (TripVO) request.getAttribute("tripVO");
%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>���θ�Ʒs�W - addTrip.jsp</title>

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
</style>

<style>
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

<table id="table-1">
	<tr><td>
		 <h3>���θ�Ʒs�W - addTrip.jsp</h3></td><td>
		 <h4><a href="<%=request.getContextPath()%>/front-end/trip/select_page.jsp"><img src="images/tomcat.png" width="100" height="100" border="0">�^����</a></h4>
	</td></tr>
</table>

<h3>��Ʒs�W:</h3>

<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/trip/trip.do" name="form1">
<table>
	<tr>
		<td>�|���s��:</td>
		<td><input type="TEXT" name="mem_no" size="45" 
			 value="<%= (tripVO==null)? "M0001" : tripVO.getMem_no()%>" /></td>
	</tr>
	<tr>
		<td>��{�s��:</td>
		<td><input type="TEXT" name="tour_id" size="45" 
			 value="<%= (tripVO==null)? "R0001" : tripVO.getTour_id()%>" /></td>
	</tr>
	<tr>
		<td>�Ѽ�:</td>
		<td><input type="TEXT" name="days" size="45" 
			 value="<%= (tripVO==null)? "1" : tripVO.getDays()%>" /></td>
	</tr>
	<tr>
		<td>�w�����B:</td>
		<td><input type="TEXT" name="trip_price" size="45" 
			 value="<%= (tripVO==null)? "0" : tripVO.getTrip_price()%>" /></td>
	</tr>
	<tr>
		<td>�_�l���:</td>
		<td><input name="first_date" id="first_date" type="text" ></td>
	</tr>
	<tr>
		<td>�������:</td>
		<td><input name="last_date"   id="last_date"   type="text" ></td>
	</tr>
	<tr>
		<td>���W�}�l���:</td>
		<td><input name="reg_start" id="reg_start" type="text" ></td>
	</tr>
	<tr>
		<td>���W�I����:</td>
		<td><input name="reg_deadline"   id="reg_deadline"   type="text" ></td>
	</tr>
	<tr>
		<td>���ΤH��:</td>
		<td><input type="number" name="mem_amount" value="<%= (tripVO==null)? "1" : tripVO.getMem_amount()%>" min="1" max="100" step="1"></td>
	</tr>
	<tr>
		<td>�H�ƤW��:</td>
		<td><input type="number" name="mem_limited" value="<%= (tripVO==null)? "1" : tripVO.getMem_limited()%>" min="1" max="100" step="1"></td>
	</tr>
</table>



<input type="hidden" name="action" value="insert">
<input type="submit" value="�e�X�s�W"></FORM>


</body>

<!-- =========================================�H�U�� datetimepicker �������]�w========================================== -->

<link   rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath() %>/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath() %>/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
  .xdsoft_datetimepicker .xdsoft_datepicker {
           width:  300px;   /* width:  300px; */
  }
  .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
           height: 151px;   /* height:  151px; */
  }
</style>
<% 
  java.sql.Date first_date = null;
  try {
	  first_date = tripVO.getFirst_date();
   } catch (Exception e) {
	  first_date = new java.sql.Date(System.currentTimeMillis());
   }
%>


<script>
$.datetimepicker.setLocale('zh'); // kr ko ja en
$(function(){
	 $('#first_date').datetimepicker({
	  format:'Y-m-d',
	  startDate:'<%=first_date%>',
	  onShow:function(){
	   this.setOptions({
	    maxDate:$('#last_date').val()?$('#last_date').val():false,
	    minDate: <%=first_date%>,	
	   })
	  },
	 
	  value: <%=first_date%>,
	  timepicker:false
	 });
	 
	 $('#last_date').datetimepicker({
	  format:'Y-m-d',
	  onShow:function(){
	   this.setOptions({
	    minDate:$('#first_date').val()?$('#first_date').val():false
	   })
	  },
	  timepicker:false
	 });
	 $('#reg_start').datetimepicker({
		  format:'Y-m-d',
		  onShow:function(){
		   this.setOptions({
		    maxDate:$('#reg_deadline').val()?$('#reg_deadline').val():false
		   })
		  },
		  timepicker:false
		 });
		 
		 $('#reg_deadline').datetimepicker({
		  format:'Y-m-d',
		  onShow:function(){
		   this.setOptions({
		    minDate:$('#reg_start').val()?$('#reg_start').val():false,
		    maxDate:$('#first_date').val()?$('#first_date').val():false
		   })
		  },
		  timepicker:false
		 });
});
</script>
</html>