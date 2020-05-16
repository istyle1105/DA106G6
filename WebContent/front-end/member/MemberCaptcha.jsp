<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.member.model.*"%>
<%
	String mem_no=(String)session.getAttribute("mem_no");
	MemberService Membersvc=new MemberService();
	MemberVO memberVO=null;
	memberVO=Membersvc.getOneMember(mem_no);
	pageContext.setAttribute("memberVO", memberVO);	
%>
<%--
	MemberVO memberVO=null;
	memberVO=(MemberVO)session.getAttribute("memberVO");
--%>
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
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/sweetalert2.min.css">
<style>
.checkMem{
	width: 400px;
	margin:70px auto;	
	background-color: rgba(0,0,0,0) !important;
	height:300px !important;
}


</style>
    
</head>

<body>
<%@ include file="/front-end/TopHeader.file" %>





<div class="checkMemContainer">
<div class="card checkMem" >
  <h5 class="card-header">會員驗證</h5>
  <form method="POST" action="member.do">
  <div class="card-body">
    <div class="card-text">
    	<div class="form-group">
			<label for="inputID">帳號</label>
            <input type="text" class="form-control" id="inputID" name="mem_id" placeholder="MemberID">
        </div>
        <div class="form-group">
            <label for="inputPassword1">驗證碼</label>
            <input type="text" class="form-control" id="inputPassword1" name="captcha" placeholder="Captcha">
        </div>
    </div>
    <input type="hidden" name="action" value="checkMem">
    <div id="errorShow"style="display:flex; justify-content:flex-start;"></div>
    <div style="display:flex; justify-content:flex-end;">
    <button type="submit" class="btn btn-primary" >驗證</button>
    </div>
  </div>
  </form>
</div>
</div>

<%@ include file="/front-end/EndFooter.file" %>


    
<script>
 var error='${param.error}';
 if(error=='space'){
	 Swal.fire({
		  title: '請勿不輸入',
		  text: "請重新輸入",
		  icon: 'error',
		  showConfirmButton: false,
		  timer: 1500
		}).then(function () {
	        window.location.href = "<%=request.getContextPath()%>/front-end/member/MemberCaptcha.jsp"
	    })
	 
 }else if(error=='noId'){
	 Swal.fire({
		  title: '沒有此帳號',
		  text: "請重新輸入",
		  icon: 'error',
		  showConfirmButton: false,
		  timer: 1500
		}).then(function () {
	        window.location.href = "<%=request.getContextPath()%>/front-end/member/MemberCaptcha.jsp"
	    })
	 
 }else if(error=='wrongNum'){
	 
	 Swal.fire({
		  title: '驗證碼錯誤',
		  text: "請重新輸入",
		  icon: 'error',
		  showConfirmButton: false,
		  timer: 1500
		}).then(function () {
	        window.location.href = "<%=request.getContextPath()%>/front-end/member/MemberCaptcha.jsp"
	    }) 
 }else if(error=='success'){
	 Swal.fire({
		  title: '驗證成功',
		  text: "1.5秒後跳轉至登入換面",
		  icon: 'success',
		  showConfirmButton: false,
		  timer: 1500
		}).then(function () {
	        window.location.href = "<%=request.getContextPath()%>/front-end/member/Login.jsp"
	    })
 }

</script>         
</body>

</html>