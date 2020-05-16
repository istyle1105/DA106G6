<%@page import="java.sql.Timestamp"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.tour_detail.model.*"%>

<%
  Tour_detailVO tour_detailVO = (Tour_detailVO) request.getAttribute("tour_detailVO");
%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>行程細節資料新增 - addTour_detail.jsp</title>

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
  div>img{
  max-width:100%;
  }
</style>

</head>
<body bgcolor='white'>

<table id="table-1">
	<tr><td>
		 <h3>行程細節資料新增 - addTour_detail.jsp</h3></td><td>
		 <h4><a href="<%=request.getContextPath()%>/front-end/tour_detail/select_page.jsp"><img src="<%=request.getContextPath()%>/NoData/null2.jpg" width="100" height="100" border="0">回首頁</a></h4>
	</td></tr>
</table>

<h3>資料新增:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/tour_detail/tour_detail.do" name="form1" enctype="multipart/form-data">
<table>
	<tr>
<jsp:useBean id="tourSvc" scope="page" class="com.tour.model.TourService" />
		<td>行程:<font color=red><b>*</b></font></td>
		<td><select size="1" name="tour_id">
			<c:forEach var="tourVO" items="${tourSvc.all}">
				<option value="${tourVO.tour_id}" ${(tour_detailVO.tour_id==tourVO.tour_id)? 'selected':'' } >${tourVO.tour_name}
			</c:forEach>
		</select></td>
	</tr>
<!-- 	<tr> -->
<!-- 		<td>行程編號:</td> -->
<!-- 		<td><input type="TEXT" name="tour_id" size="45"  -->
<%-- 			 value="<%= (tour_detailVO==null)? "R0001" : tour_detailVO.getTour_id()%>" /></td> --%>
<!-- 	</tr> -->
	<tr>
<jsp:useBean id="spotSvc" scope="page" class="com.spot.model.SpotService" />
		<td>景點:<font color=red><b>*</b></font></td>
		<td><select size="1" name="spot_id">
			<c:forEach var="spotVO" items="${spotSvc.all}">
				<option value="${spotVO.spot_id}" ${(tour_detailVO.spot_id==spotVO.spot_id)? 'selected':'' } >${spotVO.spot_name}
			</c:forEach>
		</select></td>
	</tr>
<!-- 	<tr> -->
<!-- 		<td>景點編號:</td> -->
<!-- 		<td><input type="TEXT" name="spot_id" size="45" -->
<%-- 			 value="<%= (tour_detailVO==null)? "S0001" : tour_detailVO.getSpot_id()%>" /></td> --%>
<!-- 	</tr> -->
	<tr>
		<td>開始時間:</td>
		<td><input name="start_time" id="f_date1" type="text"></td>
	</tr>
	<tr>
		<td>停留時間:</td>
		<td><input type="TEXT" name="stay_time" size="45"
			 value="<%= (tour_detailVO==null)? "0" : tour_detailVO.getStay_time()%>" /></td>
	</tr>
	<tr>
		<td>行程細節描述:</td>
		<td><input type="TEXT" name="act_descrip" size="45" style=" height:100px; word-wrap:break-all; "
			 value="<%= (tour_detailVO==null)? "" : tour_detailVO.getAct_descrip()%>" /></td>
	</tr>
	
	<tr>
	<!-- 上傳圖片檔案 -->
		<td>行程活動圖片:</td>
		<td><input type="file"  name="act_pic"  onchange="loadFile(event)"></td>
	</tr>
	
<%-- 	<jsp:useBean id="deptSvc" scope="page" class="com.dept.model.DeptService" /> --%>
<!-- 	<tr> -->
<!-- 		<td>類型:<font color=red><b>*</b></font></td> -->
<!-- 		<td><select size="1" name="deptno"> -->
<%-- 			<c:forEach var="deptVO" items="${deptSvc.all}"> --%>
<%-- 				<option value="${deptVO.deptno}" ${(empVO.deptno==deptVO.deptno)? 'selected':'' } >${deptVO.dname} --%>
<%-- 			</c:forEach> --%>
<!-- 		</select></td> -->
<!-- 	</tr> -->

</table>


<input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增"></FORM>

<div id="output" style="width:400px;"></div>
<!-- 上傳圖片預覽 -->

</body>
<!-- 100行的要用EL寫?因為一開始是空值 ,用JAVA的話會先空值例外500-->


<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<%
	java.sql.Timestamp start_time = null;//這裡要再處理Timestamp轉DATE處理,不然日曆跑不出來
	try {
		start_time = tour_detailVO.getStart_time();
	} catch (Exception e) {
		start_time = new java.sql.Timestamp(System.currentTimeMillis());
	}
%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
  .xdsoft_datetimepicker .xdsoft_datepicker {
           width:  300px;   /* width:  300px; */
  }
  .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
           height: 151px;   /* height:  151px; */
  }
</style>

<script>
		//圖片預覽
		var loadFile = function(event) {
			var reader = new FileReader();
			reader.onload = function(){
		    	var output = document.getElementById('output');
		    	output.innerHTML = "<img id = 'preview'>";
		       document.getElementById("preview").src = reader.result;
		   };
			reader.readAsDataURL(event.target.files[0]);
		};
		

        $.datetimepicker.setLocale('zh');
        $('#f_date1').datetimepicker({
	       theme: '',              //theme: 'dark',
	       timepicker:true,       //timepicker:true,
	       step: 5,                //step: 60 (這是timepicker的預設間隔60分鐘)
	       format:'Y-m-d H:i',         //format:'Y-m-d H:i:s',
		   value: '', // value:   new Date(),
//            disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
//            startDate:	            '2017/07/10',  // 起始日
              minDate:               '-1970-01-01', // 去除今日(不含)之前
//            maxDate:               '+1970-01-01'  // 去除今日(不含)之後
        });
        
        
   
        // ----------------------------------------------------------以下用來排定無法選擇的日期-----------------------------------------------------------

        //      1.以下為某一天之前的日期無法選擇
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

        
        //      2.以下為某一天之後的日期無法選擇
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


        //      3.以下為兩個日期之外的日期無法選擇 (也可按需要換成其他日期)
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
        
       
</script>
</html>