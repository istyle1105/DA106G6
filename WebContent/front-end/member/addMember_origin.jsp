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

<title>會員新增</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
</head>
<body>


<Table>
	<tr>
		<td><h3>會員新增</h3></td>
		<td><h4><a href="select_page.jsp">回首頁</a></h4></td>
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
<table>
	<tr>
		<td>會員帳號</td>
		<td><input type="text" name="mem_id" size="45" placeholder="只能是英文字母、數字和_長度5-10"
			value="<%= (memberVO==null) ? "" : memberVO.getMem_id()%>">
		</td>
	</tr>
	<tr>
		<td>會員密碼</td>
		<td><input type="password" name="psw" size="45" placeholder="必須包含大小寫英文字母及數字,長度6-15碼"
			value="<%= (memberVO==null) ? "" : memberVO.getPsw()%>">
		</td>
	</tr>
	<tr>
		<td>姓名</td>
		<td><input type="text" name="m_name" size="45" placeholder="請輸入姓名"
			value="<%= (memberVO==null) ? "" : memberVO.getM_name()%>">
		</td>
	</tr>
	<tr>
		<td>性別</td>
		<td>
			<label><input type="radio" name="gender" value="0" ${(memberVO.gender==0) ?'checked' : ''}>男性</label>
			<label><input type="radio" name="gender" value="1" ${(memberVO.gender==1) ?'checked' : ''}>女性</label>
		</td>
	</tr>
	<tr>
		<td>手機</td>
		<td><input type="text" name="cellphone" size="45" placeholder="10個數字，且09開頭"
			value="<%= (memberVO==null) ? "" : memberVO.getCellphone()%>">
		</td>
	</tr>
	<tr>
		<td>E-mail</td>
		<td><input type="email" name="email" size="45" placeholder="請輸入e-mail"
			value="<%= (memberVO==null) ? "" : memberVO.getEmail()%>">
		</td>
	</tr>
	<tr>
		<td>地址</td>
		<td><input type="text" name="address" size="45" placeholder="地址"
			value="<%= (memberVO==null) ? "" : memberVO.getAddress()%>">
		</td>
	</tr>

	<tr>
		<td>會員頭像</td>
		<td>
			<label><input type="FILE" name="m_photo" onchange="loadFile(event)"/></label>
			<br>
			<div id="output" ></div>
		</td>
	</tr>
	
	
	
	<tr>
		<td>身分證照片</td>
		<td>
			<label><input type="FILE" name="id_card" onchange="loadFile2(event)" /></label>
			<br>
			<div id="output2" ></div>
		</td>
	
	</tr>

</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增">
</form>
	<script type="text/javascript">
	var loadFile = function(event){
		var reader = new FileReader();
		reader.onload = function(){
			var output = document.getElementById('output');
			output.innerHTML = "<img width='100' id ='preview'>";
			document.getElementById("preview").src = reader.result;
		};
		reader.readAsDataURL(event.target.files[0]);
	}
	
	var loadFile2 = function(event){
		var reader = new FileReader();
		reader.onload = function(){
			var output2 = document.getElementById('output2');
			output2.innerHTML = "<img width='100' id ='preview2'>";
			document.getElementById("preview2").src = reader.result;
		};
		reader.readAsDataURL(event.target.files[0]);
	}

	</script>



</body>
</html>