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
.login{
	width: 400px;
	margin:70px auto;	
}
.logContainer{

}
</style>

</head>

<body>
<%@ include file="/front-end/TopHeader.file" %>
<div class="logContainer">
<div class="card login">
  <h5 class="card-header">會員登入</h5>
  <form method="POST" action="<%=request.getContextPath()%>/front-end/member/member.do">
  <div class="card-body">
    <div class="card-text">
    	<div class="form-group">
			<label for="inputID">帳號</label>
            <input type="text" class="form-control" id="inputID" name="mem_id" placeholder="Enter MemberID">
        </div>
        <div class="form-group">
            <label for="inputPassword1">密碼</label>
            <input type="password" class="form-control" id="inputPassword1" name="psw" placeholder="Password">
        </div>
    </div>
    <input type="hidden" name="action" value="login">
    <input type="hidden" name="loginLocation" value="${param.requestURI}">
    <div id="errorShow"style="display:flex; justify-content:flex-start;"></div>
    <div style="display:flex; justify-content:flex-end;">
    <button type="submit" class="btn btn-primary" >登入</button>
    </div>
  </div>
  </form>
</div>
</div>

<%@ include file="/front-end/EndFooter.file" %>

    
<script>
 var error='${param.error}';
 
 if(error=='false'){
	 $("#errorShow").text("沒有此帳號");
	 console.log("error="+error);
 }else if(error=='true'){
	 $("#errorShow").text("密碼錯誤");
	 console.log("error="+error);
 }
 var state='${param.state}'
	if(state=='noVerify'){
	    	Swal.fire({
	     		  title: '登入失敗',
	     		  icon: 'error',
	     		 text: '會員驗證尚未驗證成功',
	     		  showConfirmButton: false,
	     		  showCloseButton: true,
	     		 timer: 1500
	     		})
	 }else if(state=='noAuth'){
	    	Swal.fire({
	     		  title: '登入失敗',
	     		  icon: 'error',
	     		 text: '會員已停權',
	     		  showConfirmButton: false,
	     		  showCloseButton: true,
	     		 timer: 1500
	     		})
	 }

</script>     
</body>

</html>