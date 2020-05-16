<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.order_master.model.*"%>
<%@ page import="com.employee.model.*"%>
<%@ page import="com.authority.model.*"%>   
<%
	String emp_no=(String)session.getAttribute("emp_no");
	EmployeeService employeeSvc=new EmployeeService();
	EmployeeVO employeeVO=employeeSvc.getOneEmp(emp_no);
	pageContext.setAttribute("employeeVO", employeeVO);	
	
%> 
<%
	AuthorityService authSvc= new AuthorityService();
	List<AuthorityVO> listEmp=authSvc.getOneEmp(emp_no);
	List<String> listtmp=new ArrayList();
	for(AuthorityVO authorityVO : listEmp){
		String fun_no=authorityVO.getFun_no();
		listtmp.add(fun_no);
	}
%>
<%
  Order_masterVO order_masterVO = (Order_masterVO) request.getAttribute("order_masterVO"); //Order_masterServlet.java (Concroller) 存入req的order_masterVO物件 (包括幫忙取出的order_masterVO, 也包括輸入資料錯誤時的order_masterVO物件)
%>

<html>
<head>
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
	<title>訂單修改表</title>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/back-end/css/bootstrap4_3_1.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/back-end/css/BackEndPageStyle.css">

	<style type="text/css">
		.container {
			padding-top: 60px;
			width: 1100px !important;
		}
		.warning {
			visibility: hidden;
			color: red;
			height:5px;
		}
		.oddrow {
			background-color: lightgray;
		}
		th {
			text-align:center;
			line-height:50px;
		}
		#putImg img {
			height:150px;
			width: auto;
		}
	</style>
</head>
<body>
	<%@ include file="/back-end/backendTop.file" %>	
	<div class="container">
	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/order_master/order_master.do" style="margin-bottom: 0px;">
		<table class="table">
			<thead class="thead-dark">
				<tr>
					<th scope="col">訂單編號</th>
					<th scope="col">會員編號</th>
					<th scope="col">下單時間</th>
					<th scope="col">訂單狀態</th>
					<th scope="col">出貨時間</th>
					<th scope="col">總價格</th>
					<th scope="col">動作</th>
				</tr>
			</thead>
			<tbody> 
				<tr>
					<th scope="row">${order_masterVO.p_order_id}</th>
					<th>${order_masterVO.mem_no}</th>
					<th>
						<fmt:formatDate value='${order_masterVO.om_time}' pattern='yyyy-MM-dd HH:mm:ss'/>
					</th>
					<th>
						<select size="1" name="om_status" class="form-control">
								<option value="0">待出貨
								<option value="1">已出貨
								<option value="2">交易完成
								<option value="3">換貨中
								<option value="4">退貨
								<option value="5">已取消
						</select>
					</th>
					<th>
						<input name="om_dlvr" id="f_date2" type="text" style="height: 36px;">
					</th>
					<th>
						<span>$NT </span>${order_masterVO.om_tpr}
					</th>
					<th>
						<input type="hidden" name="action" value="update">
						<input type="hidden" name="p_order_id"  value="${order_masterVO.p_order_id}">
						<input type="hidden" name="requestURL"  value="<%=request.getAttribute("requestURL")%>">
						<input type="hidden" name="whichPage" value="<%=request.getAttribute("whichPage")%>">	
						<input type="submit" class="btn btn-success" value="送出修改">
					</th>										
				</tr>
			</tbody>
		</table>
		</FORM>
	</div>
	<%@ include file="/back-end/backendBottom.file" %>
</body>
<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
  .xdsoft_datetimepicker .xdsoft_datepicker {
           width:  150px;   /* width:  300px; */
  }
  .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
           height: 20px;   /* height:  151px; */
  }
</style>

<script>
		$.datetimepicker.setLocale('zh');
		$('#f_date2').datetimepicker({
			theme: 'dark',              //theme: 'dark',
			timepicker: true,       //timepicker:true,
			step: 60,                //step: 60 (這是timepicker的預設間隔60分鐘)
			format:'Y-m-d H:i:s',         //format:'Y-m-d H:i:s',
			value: '', // value:   new Date(),
			//disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
			//startDate:	            '2017/07/10',  // 起始日
			minDate: '<%=order_masterVO.getOm_dlvr()%>', // 去除今日(不含)之前
			//maxDate:               '+1970-01-01'  // 去除今日(不含)之後
		});
        
   
        // ----------------------------------------------------------以下用來排定無法選擇的日期-----------------------------------------------------------

			//1.以下為某一天之前的日期無法選擇
		//	var somedate1 = new Date('2017-06-15');
		//	$('#f_date1').datetimepicker({
		//	beforeShowDay: function(date) {
		//	if (  date.getYear() <  somedate1.getYear() || 
		//	(date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
		//	(date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
		//	) {
		//	return [false, ""]
		//	}
		//	return [true, ""];
		//	}});

        
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