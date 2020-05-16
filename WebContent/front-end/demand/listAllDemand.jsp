<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.demand.model.*"%>
<%@ page import="com.p_message.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%
	String mem_no=(String)session.getAttribute("mem_no");
	MemberService Membersvc=new MemberService();
	MemberVO memberVO=null;
	memberVO=Membersvc.getOneMember(mem_no);
	pageContext.setAttribute("memberVO", memberVO);	
%>  
<%
    DemandService demandSvc = new DemandService();
    List<DemandVO> list = demandSvc.getAvailable();
    pageContext.setAttribute("list",list);
%>

<title>所有需求資料</title>

<style>
 #table-headerAll {
	margin: 30px;
	text-align: center;
  }
  #allDemand {
	display: flex;
	justify-content: center;
	flex-direction: column;
  }
</style>

<jsp:useBean id="demandVO"  scope="page" class="com.demand.model.DemandJNDIDAO" />

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
<div style="display: flex; justify-content: center;align-items: flex-start;">
		<div id="allDemand">
				<table id="table-headerAll">
					<tr><td>
		 				<h3>代購商品</h3>
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
			
			<ul style="display: flex; justify-content: space-around;">
		
				<li><h5><a href='addDemand.jsp'>新增需求</a></h5>
		
				<li>
					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/demand/demand.do" style="display: flex; flex-direction: row;">
						<h5 style="line-height: 38px; margin-right: 10px;"><b>查詢需求編號:</b></h5>
						<select size="1" name="de_no" class="custom-select" style="width: 120px; padding-right: 20px">
							<c:forEach var="demandVO" items="${list}">
								<option value="${demandVO.de_no}">${demandVO.de_no}
							</c:forEach>
						</select>
						<input type="hidden" name="action" value="listOneInFront">
						<input type="submit" value="送出" class="btn btn-dark" style="height: 38px; margin-left: 10px;">
					</FORM>
				</li>
			</ul>
			
				<br>
			
			<div style="float: right; margin: 0 0 0 auto;"><%@ include file="page1.file" %> </div>
			
				<br>
							
			<div style="width: 800px; display: flex; flex-wrap: wrap; justify-content: flex-start;">
				

				<c:forEach var="demandVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
				
					<div style="height: 250px; width: 200px; text-align: center;">
						<A href="<%=request.getContextPath()%>/front-end/demand/demand.do?de_no=${demandVO.de_no}&action=listOneInFront">
							<span style="text-align: center;"><img src = "data:image/png;base64,${demandVO.de_photo}" height="100"></span><br><br>
							<span style="font-size: 14px">${demandVO.de_item_name}</span><br>
						</a>
						<span><fmt:formatNumber value = "${demandVO.price}" type = "currency"/></span>
					</div>
					
				
				</c:forEach>
			
			</div>
				
				<br><br><br>
			
			<%@ include file="page2.file" %>
			
		</div>
</div>

<%@ include file="/front-end/EndFooter.file" %>
<script src="<%=request.getContextPath()%>/front-end/js/custom.js"></script>
<script src="<%=request.getContextPath()%>/front-end/js/sweetalert2.min.js"></script>

<script>
		var state='${param.state}'
			if (state=='addSuccess') {
				Swal.fire({
					title: '新增成功',
					icon: 'success' ,
					showConfirmButton: false,
					showCloseButton: true,
					timer: 1500
				})
			}
		if (state=='success') {
			Swal.fire({
				title: '接受成功',
				icon: 'success' ,
				showConfirmButton: false,
				showCloseButton: true,
				timer: 1500
			})
		}
		if (state=='fail') {
			Swal.fire({
				title: '不可接受自己的訂單~',
				icon: 'error' ,
				showConfirmButton: false,
				showCloseButton: true,
				timer: 1500
			})
		}
		if (state=='ReportSuccess') {
			Swal.fire({
				title: '檢舉成功',
				icon: 'success' ,
				showConfirmButton: false,
				showCloseButton: true,
				timer: 1500
			})
		}
</script>

</body>

</html>