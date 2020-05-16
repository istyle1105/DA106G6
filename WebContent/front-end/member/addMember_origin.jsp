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

<title>�|���s�W</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
</head>
<body>


<Table>
	<tr>
		<td><h3>�|���s�W</h3></td>
		<td><h4><a href="select_page.jsp">�^����</a></h4></td>
	</tr>
</Table>

<h3>��Ʒs�W</h3>
<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<form method="post" action="member.do" name="form1" enctype="multipart/form-data">
<table>
	<tr>
		<td>�|���b��</td>
		<td><input type="text" name="mem_id" size="45" placeholder="�u��O�^��r���B�Ʀr�M_����5-10"
			value="<%= (memberVO==null) ? "" : memberVO.getMem_id()%>">
		</td>
	</tr>
	<tr>
		<td>�|���K�X</td>
		<td><input type="password" name="psw" size="45" placeholder="�����]�t�j�p�g�^��r���μƦr,����6-15�X"
			value="<%= (memberVO==null) ? "" : memberVO.getPsw()%>">
		</td>
	</tr>
	<tr>
		<td>�m�W</td>
		<td><input type="text" name="m_name" size="45" placeholder="�п�J�m�W"
			value="<%= (memberVO==null) ? "" : memberVO.getM_name()%>">
		</td>
	</tr>
	<tr>
		<td>�ʧO</td>
		<td>
			<label><input type="radio" name="gender" value="0" ${(memberVO.gender==0) ?'checked' : ''}>�k��</label>
			<label><input type="radio" name="gender" value="1" ${(memberVO.gender==1) ?'checked' : ''}>�k��</label>
		</td>
	</tr>
	<tr>
		<td>���</td>
		<td><input type="text" name="cellphone" size="45" placeholder="10�ӼƦr�A�B09�}�Y"
			value="<%= (memberVO==null) ? "" : memberVO.getCellphone()%>">
		</td>
	</tr>
	<tr>
		<td>E-mail</td>
		<td><input type="email" name="email" size="45" placeholder="�п�Je-mail"
			value="<%= (memberVO==null) ? "" : memberVO.getEmail()%>">
		</td>
	</tr>
	<tr>
		<td>�a�}</td>
		<td><input type="text" name="address" size="45" placeholder="�a�}"
			value="<%= (memberVO==null) ? "" : memberVO.getAddress()%>">
		</td>
	</tr>

	<tr>
		<td>�|���Y��</td>
		<td>
			<label><input type="FILE" name="m_photo" onchange="loadFile(event)"/></label>
			<br>
			<div id="output" ></div>
		</td>
	</tr>
	
	
	
	<tr>
		<td>�����ҷӤ�</td>
		<td>
			<label><input type="FILE" name="id_card" onchange="loadFile2(event)" /></label>
			<br>
			<div id="output2" ></div>
		</td>
	
	</tr>

</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="�e�X�s�W">
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