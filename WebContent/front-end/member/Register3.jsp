<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>  
<%
	MemberVO memberVO= (MemberVO)request.getAttribute("memberVO");
%>
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Martine</title>
    <link href="https://gitcdn.github.io/bootstrap-toggle/2.2.2/css/bootstrap-toggle.min.css" rel="stylesheet">
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


    <script src="https://kit.fontawesome.com/74b3702900.js" crossorigin="anonymous"></script>
    
    <style>

    .RegContainer{
		margin: auto;
		width: 960px;

	}
	.alert{
		margin: auto;
		width: 960px;
	}
    
    </style>
    
<script>

window.onload = function () {
	var alert='${errorMsgs}';
	console.log("alert="+alert);
	var err=document.getElementById("err");
	if(alert.length==0){
		$("#err").css("display","none");
	}
	$("#btn").click(function(){
		if(alert!=null){
			$("#err").css("display","")
		}
	});

}


</script>    
    
</head>

<body>
   <!--::header part start::-->
   <header class="main_menu">
        <div class="main_menu_iner">
            <div class="container">
                <div class="row align-items-center ">
                    <div class="col-lg-12">
                        <nav class="navbar navbar-expand-lg navbar-light justify-content-between">
                            
                            <button class="navbar-toggler" type="button" data-toggle="collapse"
                                data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                                aria-expanded="false" aria-label="Toggle navigation">
                                <span class="navbar-toggler-icon"></span>
                            </button>

                            <div class="collapse navbar-collapse main-menu-item justify-content-center"
                                id="navbarSupportedContent">
                                <a class="navbar-brand" href="index.html"> <img src="<%=request.getContextPath()%>/front-end/img/logo3.png" alt="logo"> </a>
                                <ul class="navbar-nav">
                                    <li class="nav-item dropdown">
                                        <a class="nav-link dropdown-toggle" href="blog.html" id="navbarDropdown"
                                            role="button" data-toggle="dropdown" aria-haspopup="true"
                                            aria-expanded="false">首頁</a>
                                        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                                            <a class="dropdown-item" href="blog.html">最新消息</a>
                                            <a class="dropdown-item" href="single-blog.html">世界時鐘</a>
                                            <a class="dropdown-item" href="single-blog.html">匯率</a>
                                            <a class="dropdown-item" href="single-blog.html">天氣</a>
                                        </div>
                                    </li>
                                    <li class="nav-item dropdown">
                                        <a class="nav-link dropdown-toggle" href="blog.html" id="navbarDropdown"
                                            role="button" data-toggle="dropdown" aria-haspopup="true"
                                            aria-expanded="false">行程</a>
                                        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                                            <a class="dropdown-item" href="blog.html">熱門行程</a>
                                            <a class="dropdown-item" href="single-blog.html">團主專區</a>
                                            <a class="dropdown-item" href="single-blog.html">團員專區</a>
                                        </div>
                                    </li>
                                    <li class="nav-item dropdown">
                                        <a class="nav-link dropdown-toggle" href="blog.html" id="navbarDropdown"
                                            role="button" data-toggle="dropdown" aria-haspopup="true"
                                            aria-expanded="false">代購</a>
                                        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                                            <a class="dropdown-item" href="blog.html">代購首頁</a>
                                            <a class="dropdown-item" href="single-blog.html">需求代購</a>
                                            <a class="dropdown-item" href="single-blog.html">提供代購</a>
                                        </div>
                                    </li>
                                    <li class="nav-item dropdown">
                                        <a class="nav-link dropdown-toggle" href="blog.html" id="navbarDropdown"
                                            role="button" data-toggle="dropdown" aria-haspopup="true"
                                            aria-expanded="false">商城</a>
                                        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                                            <a class="dropdown-item" href="blog.html">商城首頁</a>
                                            <a class="dropdown-item" href="single-blog.html">訂單查詢</a>
                                        </div>
                                    </li>


                                    <li class="nav-item dropdown">
                                        <a class="nav-link dropdown-toggle" href="blog.html" id="navbarDropdown"
                                            role="button" data-toggle="dropdown" aria-haspopup="true"
                                            aria-expanded="false">日誌</a>
                                        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                                            <a class="dropdown-item" href="blog.html">日誌首頁</a>
                                            <a class="dropdown-item" href="single-blog.html">我的日誌</a>
                                        </div>
                                    </li>
                                    <li class="nav-item dropdown">
                                        <a class="nav-link dropdown-toggle" href="blog.html" id="navbarDropdown"
                                            role="button" data-toggle="dropdown" aria-haspopup="true"
                                            aria-expanded="false">會員中心</a>
                                        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                                            <a class="dropdown-item" href="blog.html">會員資料</a>
                                            <a class="dropdown-item" href="single-blog.html">會員申訴</a>
                                            <a class="dropdown-item" href="single-blog.html">會員通知</a>
                                            <a class="dropdown-item" href="single-blog.html">會員好友</a>
                                            <a class="dropdown-item" href="single-blog.html">會員錢包</a>
                                        </div>
                                    </li>
                                    <li class="nav-item btnLogin">
                                        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModalCenter">登入</button>
                                    </li>
                                    <li class="nav-item btnLogin" style="margin-left: 5px">
                                        <button type="button" class="btn btn-primary">註冊</button>
                                    </li>
                                    
                                   <!--  <li class="nav-item login">
                                        <a class="nav-link signin" href="contact.html">登入</a>
                                    </li>
                                    <li class="nav-item login">
                                        <a class="nav-link signup" href="contact.html">註冊</a>
                                    </li> -->
                                </ul>
                            </div>



                            <!-- <div class="shopping_cart">
                                    <a href="cart.html">
                                        <div><i class="fas fa-shopping-cart"></i></div>
                                        <div>Cart <span>(0)</span></div>
                                    </a>
                            </div>
                            <a href="#" class="btn_1 d-none d-lg-block">book now</a> -->
                        </nav>
                    </div>
                </div>
            </div>
        </div>
    </header>
    <!-- Header part end-->


<!-- Modal -->
    <div class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered" role="document">
            <form>
                <div class="modal-content" style="width: 400px">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalCenterTitle">會員登入</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>           

                    
                    <div class="login">
                        <div class="form-group">
                            <label for="inputID">帳號</label>
                            <input type="text" class="form-control" id="inputID" aria-describedby="emailHelp" placeholder="Enter MemberID">
                        </div>
                        <div class="form-group">
                            <label for="inputPassword1">密碼</label>
                            <input type="password" class="form-control" id="inputPassword1" placeholder="Password">
                        </div>
                        <!-- <div class="form-group form-check">
                            <input type="checkbox" class="form-check-input" id="exampleCheck1">
                            <label class="form-check-label" for="exampleCheck1">Check me out</label>
                        </div> -->
                        <div class="modal-footer">
                            <button type="submit" class="btn btn-primary">登入</button>
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">取消</button>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>

<div id="AddMem">


<div id="err" class="message alert alert-success alert-dismissible">
    <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
    
    <c:if test="${not empty errorMsgs}">
		<font style="color:red">請修正以下錯誤:</font>
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
				<li style="color:red">${message}</li>
				</c:forEach>
			</ul>
	</c:if>
  </div>

<form method="post" action="member.do" name="form1" enctype="multipart/form-data">
<div class="RegContainer">
		<%-- <div class="form-group">
    		<h2>會員新增</h2>
    		<h4><a href="select_page.jsp">回首頁</a></h4>
  	 	</div>
--%>
		<div class="form-row">
		    <div class="form-group col-md-6">
		      <label for="inputId">帳號</label>
		      <input type="text" class="form-control" id="inputId" name="mem_id"
		      	value="<%= (memberVO==null) ? "" : memberVO.getMem_id()%> "placeholder="只能是英文字母、數字和_長度5-10">
		    </div>
		    <div class="form-group col-md-6">
		      <label for="inputPassword4">Password</label>
		      <input type="password" class="form-control" id="inputPassword4" name="psw" placeholder="必須包含大小寫英文字母及數字,長度6-15碼"
		      	value="<%= (memberVO==null) ? "" : memberVO.getPsw()%>">
		    </div>
	  	</div>		

	  <div class="form-row">
	    <div class="form-group col-md-6">
	      <label for="inputName">姓名</label>
	      <input type="text" class="form-control" id="inputName" placeholder="請輸入姓名" name="m_name"
	      	value="<%= (memberVO==null) ? "" : memberVO.getM_name()%>">
	    </div>
	    <div class="form-group col-md-6">
	    	<div style="margin-bottom: .5rem">性別</div>
	    	<div class="form-control">
	      	<label for="male">男性</label><input id="male" type="radio" name="gender" value="0" ${(memberVO.gender==0) ?'checked' : ''}>	
			<label for="female">女性</label><input id="female" type="radio" name="gender" value="1" ${(memberVO.gender==1) ?'checked' : ''}>
	    	</div>
	    </div>
	  </div>		
	  <div class="form-row">
	    <div class="form-group col-md-6">
	      <label for="inputphone">手機</label>
	      <input type="text" class="form-control" id="inputphone" name="cellphone"  placeholder="10個數字，且09開頭"
	      	value="<%= (memberVO==null) ? "" : memberVO.getCellphone()%>">
	    </div>
	    <div class="form-group col-md-6">
	      <label for="inputEmail">Email</label>
	      <input type="email" class="form-control" id="inputEmail" name="email" placeholder="請輸入e-mail"
	      	value="<%= (memberVO==null) ? "" : memberVO.getEmail()%>">
	    </div>
	  </div>


	  <div class="form-group">
    		<label for="inputAddr">Address</label>
    		<input type="text" class="form-control" id="inputAddr" placeholder="地址" name="address"
    			value="<%= (memberVO==null) ? "" : memberVO.getAddress()%>">
  	 </div>
 	  <div class="form-row">
			<div class="form-group col-md-6">
	      		<label for="inputPhoto">上傳會員頭像</label>
	    		<div><input type="File" id="inputPhoto" name="m_photo" onchange="loadFile(event)"/></div>
	    	</div>
	    	<div class="form-group col-md-6">
	      		<label for="inputIdcard">上傳身分證</label>
	    		<div><input type="File" id="inputIdcard"  name="id_card" onchange="loadFile2(event)" ></div>
	    	</div>
	  </div>
	   	  <div class="form-row">
			<div class="form-group col-md-6" id="output">
	    	</div>
	    	<div class="form-group col-md-6" id="output2">
	    	</div>
	  </div>
	<input type="hidden" name="action" value="insert"> 		
  	<button id="btn" type="submit" class="btn btn-primary">註冊</button>
</div>
</form>
<div id="output" ></div>

</div>



    <!-- footer part start-->
    <footer class="footer-area">
        <div class="container-fluid">
            <div class="row justify-content-center">
                <div class="col-lg-12">
                    <div class="copyright_part_text text-center">
                        <p class="footer-text m-0"><!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
Copyright &copy;<script>document.write(new Date().getFullYear());</script><i class="ti-heart" aria-hidden="true"></i> by 資策會DA106 G6
                        </p>
                    </div>
                </div>
            </div>
        </div>
    </footer>
    <!-- footer part end-->
<script type="text/javascript">
	var loadFile = function(event){
		var reader = new FileReader();
		reader.onload = function(){
			var output = document.getElementById('output');
			output.innerHTML = "<img width='200' id ='preview'>";
			document.getElementById("preview").src = reader.result;
		};
		reader.readAsDataURL(event.target.files[0]);
	}
	
	var loadFile2 = function(event){
		var reader = new FileReader();
		reader.onload = function(){
			var output2 = document.getElementById('output2');
			output2.innerHTML = "<img width='200' id ='preview2'>";
			document.getElementById("preview2").src = reader.result;
		};
		reader.readAsDataURL(event.target.files[0]);
	}
</script>

    <!-- jquery plugins here-->
    <script src="https://gitcdn.github.io/bootstrap-toggle/2.2.2/js/bootstrap-toggle.min.js"></script>
    <script src="<%=request.getContextPath()%>/front-end/js/jquery-1.12.1.min.js"></script>
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