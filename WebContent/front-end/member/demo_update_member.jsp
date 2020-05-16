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
	.container{
		margin: auto;
		width: 960px;

	}
</style>


<body>
<Table>
	<tr>
		<td><h3>會員修改</h3></td>
		<td><h4><a href="">回首頁</a></h4></td>
	</tr>
</Table>
<h3>資料新增</h3>
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<form method="post" action="member.do" name="form1" enctype="multipart/form-data">
	<div class="container">
		<div class="form-group">
    		<h2>會員修改</h2>
  	 	</div>
  	 	<div class="form-group">
    		<label for="inputMemNo">會員編號</label>
    		<input type="text" class="form-control" id="inputMemNo"  name="mem_no"
    			value="<%=memberVO.getAddress()%> readonly">
  	 	</div>
  	 	

		<div class="form-row">
		    <div class="form-group col-md-6">
		      <label for="inputId">帳號</label>
		      <input type="text" class="form-control" id="inputId" name="mem_id"
		      	value="<%=memberVO.getMem_id()%>" readonly>
		    </div>
		    <div class="form-group col-md-6">
		      <label for="inputPassword4">Password</label>
		      <input type="password" class="form-control" id="inputPassword4" name="psw" placeholder="Password"
		      	value="<%= (memberVO==null) ? "" : memberVO.getPsw()%>">
		    </div>
	  	</div>		

	  	<div class="form-row">
	    	<div class="form-group col-md-6">
	      		<label for="inputName">姓名</label>
	      		<input type="text" class="form-control" id="inputName" placeholder="Name" name="m_name"
	      	value="<%= (memberVO==null) ? "" : memberVO.getM_name()%>">
	    	</div>
	    	<div class="form-group col-md-6">
	    		<div style="margin-bottom: .5rem">性別</div>
	    		<div class="form-control">
	      			<label for="male">男性</label><input id="male" type="radio" name="gender" value="0" ${(memberVO.gender==0) ?'checked' : ''}>	
					<label for="female">女性</label><input id="female"type="radio" name="gender" value="1" ${(memberVO.gender==1) ?'checked' : ''}>
	    		</div>
	    	</div>
	  	</div>		
	  	<div class="form-row">
	    	<div class="form-group col-md-6">
	      		<label for="inputphone">手機</label>
	      		<input type="text" class="form-control" id="inputphone" name="cellphone"  placeholder="Cellphone" value="<%= (memberVO==null) ? "" : memberVO.getCellphone()%>">
	    	</div>
	    	<div class="form-group col-md-6">
	      		<label for="inputEmail">Email</label>
	      	<input type="email" class="form-control" id="inputEmail" name="email" placeholder="Email" value="<%= (memberVO==null) ? "" : memberVO.getEmail()%>">
	    	</div>
	  	</div>
	  <div class="form-group">
    		<label for="inputAddr">Address</label>
    		<input type="text" class="form-control" id="inputAddr" placeholder="Address" name="address"
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
			<div class="form-group col-md-6" id="output"></div>
	    	<div class="form-group col-md-6" id="output2"></div>
	  </div>
			<input type="hidden" name="action" value="insert"> 		
  			<button type="submit" class="btn btn-primary">註冊</button>
	  </div>
</form>
<div id="output" ></div>



<script type="text/javascript">
	var loadFile = function(event){
		var reader = new FileReader();
		reader.onload = function(){
			var output = document.getElementById('output');
			var oimg = document.getElementById('oimg');
			output.removeChild(oimg);
			output.innerHTML = "<img width='200' id ='preview'>";
			document.getElementById("preview").src = reader.result;
		};
		reader.readAsDataURL(event.target.files[0]);
	}
	
	var loadFile2 = function(event){
		var reader = new FileReader();
		reader.onload = function(){
			var output2 = document.getElementById('output2');
			var oimg2 = document.getElementById('oimg2');
			output2.removeChild(oimg2);
			output2.innerHTML = "<img width='200' id ='preview2'>";
			document.getElementById("preview2").src = reader.result;
		};
		reader.readAsDataURL(event.target.files[0]);
	}

	</script>
</body>
</html>