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

%>

<%

	DemandVO demandVO = (DemandVO) request.getAttribute("demandVO"); //EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>
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
	
<style>
  #table-header {
	width: 400px;
	margin: 30px;
	text-align: center;
  }
  .input-group-text {
  	width: 120px;
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

</head>

<body>
<%@ include file="/front-end/TopHeader.file" %>
<div style="display: flex; justify-content: center;align-items: flex-start;">
<div id="allUpdate">
		<table id="table-header">
			<tr><td>
				 <h3>需求商品資料更新</h3>
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
		
		<FORM METHOD="post" ACTION="demand.do" name="form1" enctype="multipart/form-data" style="margin-top: 16px;">
		
			<div class="input-group mb-3">
		 		<div class="input-group-prepend">
		    		<span class="input-group-text" id="inputGroup-sizing-default">需求編號:<font color=red><b>*</b></font></span>
		  		</div>
		  		<input class="form-control" type="text" placeholder="<%=demandVO.getDe_no()%>" readonly>
			</div>
			
			<div class="input-group mb-3">
		 		<div class="input-group-prepend">
		    		<span class="input-group-text" id="inputGroup-sizing-default">需求商品名稱:</span>
		  		</div>
		  		<input type="text" name="de_item_name" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default" value="<%=demandVO.getDe_item_name()%>">
			</div>
			
			<div class="input-group mb-3">
		 		<div class="input-group-prepend">
		    		<span class="input-group-text" id="inputGroup-sizing-default">價格:</span>
		  		</div>
		  		<input type="text" name="price" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default" value="<%=demandVO.getPrice()%>">
			</div>
			
			<div class="input-group mb-3">
		 		<div class="input-group-prepend">
		    		<span class="input-group-text" id="inputGroup-sizing-default">地區:</span>
		  		</div>
		  		<input type="text" name="area" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default" value="<%=demandVO.getArea()%>">
			</div>
		
			<jsp:useBean id="demandSvc" scope="page" class="com.demand.model.DemandService" />
			
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
				<input type="submit" value="送出修改" class="btn btn-outline-secondary">
			</div>
				
		<input type="hidden" name="de_photo1" value="${demandVO.de_photo}">
		<input type="hidden" name="action" value="updateInFront">
		<input type="hidden" name="de_no" value="<%=demandVO.getDe_no()%>">
		<input type="hidden" name="de_mem_no" value="${demandVO.de_mem_no}">
		<input type="hidden" name="de_time" value="${demandVO.de_time}">
		<input type="hidden" name="status" value="${demandVO.status}">
		<input type="hidden" name="requestURL" value="<%=request.getParameter("requestURL")%>"> <!--接收原送出修改的來源網頁路徑後,再送給Controller準備轉交之用-->
		<input type="hidden" name="whichPage"  value="<%=request.getParameter("whichPage")%>">  <!--只用於:istAllEmp.jsp-->
		</FORM>
		
		</div>
</div>

<%@ include file="/front-end/EndFooter.file" %>

</body>

</html>