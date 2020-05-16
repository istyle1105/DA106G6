<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%@ page import="com.member.model.*"%>
<%@ page import="com.demand.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%

	String mem_no=(String)session.getAttribute("mem_no");
	MemberService Membersvc=new MemberService();
	MemberVO memberVO=null;
	memberVO=Membersvc.getOneMember(mem_no);
	pageContext.setAttribute("memberVO", memberVO);	
	
	DemandVO demandVO = (DemandVO) request.getAttribute("demandVO");

%>

<title>新增需求商品</title>

<style>
  #table-header {
  	width: 300px;
	margin: 30px;
	text-align: center;
  }
  #allAddDemand {
	display: flex;
	justify-content: center;
	flex-direction: column;
  }
   .input-group-text {
  	width: 100px;
  }
  .custom-file-label::after {
  	content: "" !important;
  	margin: auto;
  	border-radius: 0.25rem 0.25rem 0.25rem 0.25rem;
  	border-left: none !important;
  	background-color: white !important;
  }
  .img-fluid {
  	max-height: 200px;
  	max-width: 280px;
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


</head>

<body>
<%@ include file="/front-end/TopHeader.file" %>
<div style="display: flex; justify-content: center;align-items: flex-start;">
<div id="allAddDemand">

	<table id="table-header">
		<tr><td>
				<h3>新增商品</h3>
		</td></tr>
	</table>
	
		<%-- 錯誤表列 --%>
		<c:if test="${not empty errorMsgs}">
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<c:set var="errors" value="${message}" scope="page"></c:set>
					<script>
						var message = "${errors}";
						alert(message);
					</script>
				</c:forEach>
			</ul>
		</c:if>
	
		<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/demand/demand.do" name="form1"
			enctype="multipart/form-data" id="form">
			
			<div class="input-group mb-3">
		 		<div class="input-group-prepend">
		    		<span class="input-group-text" id="inputGroup-sizing-default">會員編號:<font color=red><b>*</b></font></span>
		  		</div>
		  		<input class="form-control" name="mem_no" type="text" placeholder="${mem_no}" value="${mem_no}" readonly>
			</div>
			
			<div class="input-group mb-3">
		 		<div class="input-group-prepend">
		    		<span class="input-group-text" id="inputGroup-sizing-default">商品名稱:</span>
		  		</div>
		  		<input type="text" name="de_item_name" class="form-control" aria-label="請輸入商品名稱" aria-describedby="inputGroup-sizing-default" value="<%=(demandVO == null) ? "" : demandVO.getDe_item_name()%>">
			</div>
			
			<div class="input-group mb-3">
		 		<div class="input-group-prepend">
		    		<span class="input-group-text" id="inputGroup-sizing-default">價格:</span>
		  		</div>
		  		<input type="text" name="price" class="form-control" aria-label="請輸入價格" aria-describedby="inputGroup-sizing-default" value="<%=(demandVO == null) ? "" : demandVO.getPrice()%>">
			</div>
			
			<div class="input-group mb-3">
		 		<div class="input-group-prepend">
		    		<span class="input-group-text" id="inputGroup-sizing-default">地區:</span>
		  		</div>
		  		<input type="text" name="area" class="form-control" aria-label="請輸入地區" aria-describedby="inputGroup-sizing-default" value="<%=(demandVO == null) ? "Japan" : demandVO.getArea()%>">
			</div>
	
				<jsp:useBean id="demandSvc" scope="page"
					class="com.demand.model.DemandService" />
			
			<div class="custom-file">
				<div class="input-group mb-3">
		  			<div class="input-group-prepend">
		   					<span class="input-group-text" id="inputGroupFileAddon01" style="height: 212px;">商品圖片:</span>
		  			</div>
		 			<div class="custom-file">
		    		<input type="file" accept="image/*" name="de_photo" class="custom-file-input" id="inputGroupFile01" aria-describedby="inputGroupFileAddon01"
						onchange="document.getElementById('blah').src = window.URL.createObjectURL(this.files[0])">
						<label style="height: 212px; text-align: center; line-height: 200px" class="custom-file-label" for="inputGroupFile01"><img id="blah" src="images/暫無圖片.png" class="img-fluid"></label>
		  			</div>
				</div>
			</div>
			
			<br><br><br><br><br><br><br>
			
			<div style="width: 100%; display: flex; justify-content: center;">
				<input type="submit" value="送出" class="btn btn-outline-secondary">
			</div>
			
			<input type="hidden" name="de_photo1"> 
			<input type="hidden" name="action" value="add"> 
		</FORM>
	
			</div>
</div>

<%@ include file="/front-end/EndFooter.file" %>

</body>

</html>