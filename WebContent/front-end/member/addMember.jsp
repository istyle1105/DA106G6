<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>  
<%
	MemberVO memberVO2= (MemberVO)request.getAttribute("memberVO2");
	String mem_no=(String)session.getAttribute("mem_no");
	MemberService Membersvc=new MemberService();
	MemberVO memberVO=null;
	memberVO=Membersvc.getOneMember(mem_no);
	pageContext.setAttribute("memberVO", memberVO);	

%>
<%--
	MemberVO memberVO2= (MemberVO)request.getAttribute("memberVO2");
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

    <script src="https://kit.fontawesome.com/74b3702900.js" crossorigin="anonymous"></script>
    <style type="text/css">
    .containerAdd{
    	margin-top:50px;
    	margin-bottom:50px;
    }
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
	console.log("alert.len="+alert.length);
	console.log("alert.type="+typeof alert);
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

<style>
.city, .town{width: 100%;}
.f1{float:left;margin-left:5px;margin-right:5px;width:calc(5% - 10px)}
.f2{float:left;margin-left:5px;margin-right:5px;width:calc(10% - 10px)}
.f3{float:left;margin-left:5px;margin-right:5px;width:calc(15% - 10px)}
.f4{float:left;margin-left:5px;margin-right:5px;width:calc(20% - 10px)}
.f5{float:left;margin-left:5px;margin-right:5px;width:calc(25% - 10px)}
.f6{float:left;margin-left:5px;margin-right:5px;width:calc(30% - 10px)}
.f7{float:left;margin-left:5px;margin-right:5px;width:calc(35% - 10px)}
.f8{float:left;margin-left:5px;margin-right:5px;width:calc(40% - 10px)}
.f9{float:left;margin-left:5px;margin-right:5px;width:calc(45% - 10px)}
.f10{float:left;margin-left:5px;margin-right:5px;width:calc(50% - 10px)}
.f11{float:left;margin-left:5px;margin-right:5px;width:calc(55% - 10px)}
.f12{float:left;margin-left:5px;margin-right:5px;width:calc(60% - 10px)}
.f13{float:left;margin-left:5px;margin-right:5px;width:calc(65% - 10px)}
.f14{float:left;margin-left:5px;margin-right:5px;width:calc(70% - 10px)}
.f15{float:left;margin-left:5px;margin-right:5px;width:calc(75% - 10px)}
.f16{float:left;margin-left:5px;margin-right:5px;width:calc(80% - 10px)}
.f17{float:left;margin-left:5px;margin-right:5px;width:calc(85% - 10px)}
.f18{float:left;margin-left:5px;margin-right:5px;width:calc(90% - 10px)}
.f19{float:left;margin-left:5px;margin-right:5px;width:calc(95% - 10px)}
.f20{float:left;margin-left:5px;margin-right:5px;width:calc(100% - 10px)}
</style>

</head>

<body>

<%@ include file="/front-end/TopHeader.file" %>

<div class="containerAdd">
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
		      	value="<%= (memberVO2==null) ? "" : memberVO2.getMem_id()%>" placeholder="只能是英文字母、數字和_長度5-10">
		    </div>
		    <div class="form-group col-md-6">
		      <label for="inputPassword4">Password</label>
		      <input type="password" class="form-control" id="inputPassword4" name="psw" placeholder="密碼只能是中、英文字母、數字和_ , 且長度必需在2到30之間"
		      	value="<%= (memberVO2==null) ? "" : memberVO2.getPsw()%>">
		    </div>
	  	</div>		

	  <div class="form-row">
	    <div class="form-group col-md-6">
	      <label for="inputName">姓名</label>
	      <input type="text" class="form-control" id="inputName" placeholder="請輸入姓名" name="m_name"
	      	value="<%= (memberVO2==null) ? "" : memberVO2.getM_name()%>">
	    </div>
	    <div class="form-group col-md-6">
	    	<div style="margin-bottom: .5rem">性別</div>
	    	<div class="form-control">
	      	<label for="male">男性</label><input id="male" type="radio" name="gender" value="0" ${(memberVO2.gender==0) ?'checked' : ''}>	
			<label for="female">女性</label><input id="female" type="radio" name="gender" value="1" ${(memberVO2.gender==1) ?'checked' : ''}>
	    	</div>
	    </div>
	  </div>		
	  <div class="form-row">
	    <div class="form-group col-md-6">
	      <label for="inputphone">手機</label>
	      <input type="text" class="form-control" id="inputphone" name="cellphone"  placeholder="10個數字，且09開頭"
	      	value="<%= (memberVO2==null) ? "" : memberVO2.getCellphone()%>">
	    </div>
	    <div class="form-group col-md-6">
	      <label for="inputEmail">Email</label>
	      <input type="email" class="form-control" id="inputEmail" name="email" placeholder="請輸入e-mail"
	      	value="<%= (memberVO2==null) ? "" : memberVO2.getEmail()%>">
	    </div>
	  </div>


	  <div class="form-group">
	  <label >地址</label>
	    	<div id="zipcode2"></div>
				<div id="zipcode3">
					<div class="f3" data-role="county">
					</div>
					<div class="f4" data-role="district">
					</div>
				</div>
	<input id="addressInput" name="address" type="text" class="f13 address form-control">
<!--     		<label for="inputAddr">Address</label> -->
<!--     		<input type="text" class="form-control" id="inputAddr" placeholder="地址" name="address" -->
<%--     			value="<%= (memberVO2==null) ? "" : memberVO2.getAddress()%>"> --%>
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
  	<button id="btn" type="button" class="btn btn-primary magicbtn">神奇小按鈕</button>
</div>
</form>  
</div>





<%@ include file="/front-end/EndFooter.file" %>
<script src="<%=request.getContextPath()%>/front-end/js/sweetalert2.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/jquery-twzipcode@1.7.14/jquery.twzipcode.min.js"></script>
	<script>
	$("#zipcode3").twzipcode({
	"zipcodeIntoDistrict": true,
	"css": ["city form-control", "town form-control"],
	"countyName": "city", // 指定城市 select name
	"districtName": "town" // 指定地區 select name
	});
	</script>
	<script>
	$(".magicbtn").click(function(){
		$("#inputId").val("da10606");
		$("#inputPassword4").val("da10606");
		$("#inputName").val("龍克斯");
		$("#inputphone").val("0912345678");
		$("#addressInput").val("中大路300號");
		$("#inputEmail").val("acs099149@gm.ntcu.edu.tw");
		$("#male").prop({"checked":"true"});

		
	});
	
	</script>


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
<script>
$("body").on('focusout','#inputId',function(){
    let inputId = $("#inputId").val().trim();
    if(inputId != ''){
        let params = { "action" : "checkIfIdExist",
                "inputId" : inputId
                };
        $.ajax({
             type: "POST",
             url: '<%=request.getContextPath()%>/front-end/member/member.do',
             data: params,
             dataType: "json",
             success: function(data){
                 $("#available").remove();
                 if(data.available === "Y"){
//                      $("#inputId").after('<span style="color:#80bdff;" id="available">會員帳號可以用</span>');
// 						alert("會員帳號可以用");
						Swal.fire('此會員帳號可使用')
                 }else{
//                      $("#inputId").after('<span style="color:red;"  id="available">會員帳號重複</span>');
//                 	 alert("會員帳號已用過");
                	 Swal.fire({
                		  icon: 'error',
                		  title: '會員帳號已註冊過',
                		})
                 }
             },
             error: function(){ alert('AJAX錯誤！')}
         });
    }
});

</script>


</body>

</html>