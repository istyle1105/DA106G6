<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>  
<%
	MemberVO memberVO= (MemberVO)request.getAttribute("memberVO");
%>
<%= memberVO==null %> 
<html>

<head>
	<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
	<title>Title Page</title>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</head>
<style type="text/css">
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
<body>


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
<div id="output" ></div>







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
</body>
</html>