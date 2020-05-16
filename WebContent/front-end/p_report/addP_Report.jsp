<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.p_report.model.*"%>
<%@ page import="com.demand.model.*"%>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>

<%
P_ReportVO p_reportVO = (P_ReportVO) request.getAttribute("p_reportVO");
DemandVO demandVO = (DemandVO) request.getAttribute("demandVO");

%>
<%
	String mem_no=(String)session.getAttribute("mem_no");

%>
<style>
  #reportHeader {
  	width: 300px;
	margin: 30px;
	text-align: center;
  }
  #allAddReport {
	display: flex;
	justify-content: center;
	flex-direction: column;
  }
   .input-group-text {
  	width: 100px;
  }
  </style>

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
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/style.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/sweetalert2.min.css">
</head>

<body>
<%@ include file="/front-end/TopHeader.file" %>
<div style="border:3px solid black; height:800px; display: flex; justify-content: center;align-items: flex-start;">
<div id="allAddReport">

	<table id="reportHeader">
		<tr><td>
				<h3>檢舉</h3>
		</td></tr>
	</table>
	
		<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/p_report/p_report.do" name="form1" class="was-validated"
			enctype="multipart/form-data" id="form">
			
			<div class="input-group mb-3">
		 		<div class="input-group-prepend">
		    		<span class="input-group-text" id="inputGroup-sizing-default">會員編號:<font color=red><b>*</b></font></span>
		  		</div>
		  		<input class="form-control" name="mem_no" type="text" placeholder="${mem_no}" value="${mem_no}" readonly>
			</div>
			
			<div class="input-group mb-3">
		 		<div class="input-group-prepend">
		    		<span class="input-group-text" id="inputGroup-sizing-default">需求編號:<font color=red><b>*</b></font></span>
		  		</div>
		  		<input class="form-control" name="de_no" type="text" placeholder="${demandVO.de_no}" value="${demandVO.de_no}" readonly>
			</div>
			
			<div class="input-group mb-3">
		 		<div class="input-group-prepend">
		    		<span class="input-group-text" id="inputGroup-sizing-default" style="height: 150px;">檢舉內容:</span>
		  		</div>
		  		<textarea class="form-control is-invalid" name="p_re_reason" style="height: 150px;" placeholder="請輸入檢舉內容" required></textarea>
			</div>
	
				<jsp:useBean id="p_reportSvc" scope="page"
					class="com.p_report.model.P_ReportService" />
			
			<br><br>
			
			<div style="width: 100%; display: flex; justify-content: center;">
				<input type="submit" value="送出" class="btn btn-outline-secondary">
			</div>
			
			<input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
			<input type="hidden" name="action" value="add"> 
		</FORM>
	
			</div>

</div>

<%@ include file="/front-end/EndFooter.file" %>
<script src="<%=request.getContextPath()%>/front-end/js/custom.js"></script>
<script src="<%=request.getContextPath()%>/front-end/js/sweetalert2.min.js"></script>

<script>
		var state='${param.state}'
		if (state=='fail') {
			Swal.fire({
				title: '檢舉內容請勿留白',
				icon: 'error' ,
				showConfirmButton: false,
				showCloseButton: true,
				timer: 1500
			})
		}
</script>
</body>

</html>