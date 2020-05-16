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

<%@ include file="/front-end/TopHeader.file" %>

<div class="containerAdd">
<div id="err" class="message alert alert-success alert-dismissible">
    <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
    
    <c:if test="${not empty errorMsgs}">
		<font style="color:red">�Эץ��H�U���~:</font>
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
    		<h2>�|���s�W</h2>
    		<h4><a href="select_page.jsp">�^����</a></h4>
  	 	</div>
--%>
		<div class="form-row">
		    <div class="form-group col-md-6">
		      <label for="inputId">�b��</label>
		      <input type="text" class="form-control" id="inputId" name="mem_id"
		      	value="<%= (memberVO==null) ? "" : memberVO.getMem_id()%> "placeholder="�u��O�^��r���B�Ʀr�M_����5-10">
		    </div>
		    <div class="form-group col-md-6">
		      <label for="inputPassword4">Password</label>
		      <input type="password" class="form-control" id="inputPassword4" name="psw" placeholder="�����]�t�j�p�g�^��r���μƦr,����6-15�X"
		      	value="<%= (memberVO==null) ? "" : memberVO.getPsw()%>">
		    </div>
	  	</div>		

	  <div class="form-row">
	    <div class="form-group col-md-6">
	      <label for="inputName">�m�W</label>
	      <input type="text" class="form-control" id="inputName" placeholder="�п�J�m�W" name="m_name"
	      	value="<%= (memberVO==null) ? "" : memberVO.getM_name()%>">
	    </div>
	    <div class="form-group col-md-6">
	    	<div style="margin-bottom: .5rem">�ʧO</div>
	    	<div class="form-control">
	      	<label for="male">�k��</label><input id="male" type="radio" name="gender" value="0" ${(memberVO.gender==0) ?'checked' : ''}>	
			<label for="female">�k��</label><input id="female" type="radio" name="gender" value="1" ${(memberVO.gender==1) ?'checked' : ''}>
	    	</div>
	    </div>
	  </div>		
	  <div class="form-row">
	    <div class="form-group col-md-6">
	      <label for="inputphone">���</label>
	      <input type="text" class="form-control" id="inputphone" name="cellphone"  placeholder="10�ӼƦr�A�B09�}�Y"
	      	value="<%= (memberVO==null) ? "" : memberVO.getCellphone()%>">
	    </div>
	    <div class="form-group col-md-6">
	      <label for="inputEmail">Email</label>
	      <input type="email" class="form-control" id="inputEmail" name="email" placeholder="�п�Je-mail"
	      	value="<%= (memberVO==null) ? "" : memberVO.getEmail()%>">
	    </div>
	  </div>


	  <div class="form-group">
    		<label for="inputAddr">Address</label>
    		<input type="text" class="form-control" id="inputAddr" placeholder="�a�}" name="address"
    			value="<%= (memberVO==null) ? "" : memberVO.getAddress()%>">
  	 </div>
 	  <div class="form-row">
			<div class="form-group col-md-6">
	      		<label for="inputPhoto">�W�Ƿ|���Y��</label>
	    		<div><input type="File" id="inputPhoto" name="m_photo" onchange="loadFile(event)"/></div>
	    	</div>
	    	<div class="form-group col-md-6">
	      		<label for="inputIdcard">�W�Ǩ�����</label>
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
  	<button id="btn" type="submit" class="btn btn-primary">���U</button>
</div>
</form>  
</div>


<%@ include file="/front-end/EndFooter.file" %>


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
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
</body>

</html>