<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.log.model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.member.model.*"%>

<%
	String mem_no=(String)session.getAttribute("mem_no");
	MemberService Membersvc=new MemberService();
	MemberVO memberVO=null;
	memberVO=Membersvc.getOneMember(mem_no);
	pageContext.setAttribute("memberVO", memberVO);	
%> 

<%LogService logSVC = new LogService() ; 
 List<LogVO> list = logSVC.getAllLogFav(); 
 pageContext.setAttribute("list",list);  
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


</head>
<style>
#pic img:hover{-webkit-filter:brightness(.5)}
#piclist img:hover{-webkit-filter:brightness(.5)}
</style>

<body>
<%@ include file="/front-end/TopHeader.file" %>
<div>

<!--   留空隔開 -->
<div style="height:30px"></div> 
<!--   留空隔開 -->


<!-- 標題顯示 -->
<h2  align="center" style="color:#272727;"><strong>日誌列表</strong></h2>
<!-- 標題顯示 -->



<!--   留空隔開 -->
<div style="height:30px"></div> 
<!--   留空隔開 -->

<!-- 頂部圖示 -->
<div style="display: flex; flex-direction: row; justify-content: center;"  >
<table>
	<tr>
	<td rowspan="2"><div id="pic"><a href="log.do?log_id=L0001&action=getOne_For_Display"><img style="height:400px; width:550px" src="<%=request.getContextPath()%>/front-end/log/img/tokyo.jpg"></a></div></td>
	<td><div id="pic"><a href="log.do?log_id=L0002&action=getOne_For_Display"><img style="height:200px; width:350px" src="<%=request.getContextPath()%>/front-end/log/img/dd.jpg"></a></div></td>
	</tr>
	<tr>
	<td><div id="pic"><a href="log.do?log_id=L0003&action=getOne_For_Display"><img style="height:200px; width:350px" src="<%=request.getContextPath()%>/front-end/log/img/bb.jpg"></a></div></td>
	</tr>
</table>
</div>
<!-- 頂部圖示 -->


<!--   留空隔開 -->
<div style="height:30px"></div> 
<!--   留空隔開 -->

<!-- 文章頂部 -->
<div style="display: flex; flex-direction: row; justify-content: center;">
<form  style="color:#272727;"><strong>NEW ARTICLE 文章列表</strong></form>
</div>
<!-- 文章頂部 -->

<!--   留空隔開 -->
<div style="height:30px"></div> 
<!--   留空隔開 -->


<!-- include -->
<div style="margin:auto 215px">
 <%@ include file="page1.file" %>
</div>
 <!-- include -->

<!-- 文章本體 -->
<div style="display: flex-direction: column; justify-content: center;">

<c:forEach var="logVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">

<div style="display: flex; flex-direction: row; justify-content: center;">
<table>
	<tr>
	<td>
		<div id="pic">
			<a href="log.do?log_id=${logVO.log_id}&action=getOne_For_Display">
			<img style="width:400px;height:200px "src="data:image/png;base64,${logVO.log_pic}"></a>
		</div></td>
	<td>
		<div style="width:250px;height:200px;text-align:center;line-height:30px;">
			<a href="log.do?log_id=${logVO.log_id}&action=getOne_For_Display">
			<font style="color:#272727;font-weight:bold;">${logVO.log_title}</font></a>
		</div>
	</td>
	</tr>
</table>
</div>

<!--   留空隔開 -->
<div style="height:15px"></div> 
<!--   留空隔開 -->

</c:forEach>
</div>

<!-- 文章本體 -->


<!-- include -->
<div style="margin:auto 215px">
<%@ include file="page2.file" %>
</div>
<!-- include -->





</div>

<%@ include file="/front-end/EndFooter.file" %>


    <!-- jquery plugins here-->
    <script src="<%=request.getContextPath()%>/front-end/js/jquery-1.12.4.min.js"></script>
    <!-- popper js -->
    <script src="<%=request.getContextPath()%>/front-end/js/popper.min.js"></script>
    <!-- bootstrap js -->
    <script src="<%=request.getContextPath()%>/front-end/js/bootstrap.min.js"></script>
    <!-- magnific js -->
    <script src="<%=request.getContextPath()%>/front-end/js/jquery.magnific-popup.js"></script>
    <!-- swiper js -->
    <script src="<%=request.getContextPath()%>/front-end/js/owl.carousel.min.js"></script>
    <!-- masonry js -->
    <script src="<%=request.getContextPath()%>/front-end/js/masonry.pkgd.js"></script>
    <!-- masonry js -->
    <script src="<%=request.getContextPath()%>/front-end/js/jquery.nice-select.min.js"></script>
    <script src="<%=request.getContextPath()%>/front-end/js/gijgo.min.js"></script>
    <!-- contact js -->
    <script src="<%=request.getContextPath()%>/front-end/js/jquery.ajaxchimp.min.js"></script>
    <script src="<%=request.getContextPath()%>/front-end/js/jquery.form.js"></script>
    <script src="<%=request.getContextPath()%>/front-end/js/jquery.validate.min.js"></script>
    <script src="<%=request.getContextPath()%>/front-end/js/mail-script.js"></script>
    <script src="<%=request.getContextPath()%>/front-end/js/contact.js"></script>
    <!-- custom js -->
    <script src="<%=request.getContextPath()%>/front-end/js/custom.js"></script>
</body>

</html>