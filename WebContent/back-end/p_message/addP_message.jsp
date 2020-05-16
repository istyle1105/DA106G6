<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.p_message.model.*"%>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>

<%
	P_MessageVO p_messageVO = (P_MessageVO) request.getAttribute("p_messageVO");
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>新增MSG - addP_message.jsp</title>

<style>
table#table-1 {
	background-color: #CCCCFF;
	border: 2px solid black;
	text-align: center;
}

table#table-1 h4 {
	color: red;
	display: block;
	margin-bottom: 1px;
}

h4 {
	color: blue;
	display: inline;
}
</style>

<style>
table {
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
}

table, th, td {
	border: 0px solid #CCCCFF;
}

th, td {
	padding: 1px;
}
</style>

</head>
<body bgcolor='white'>

	<table id="table-1">
		<tr>
			<td>
				<h3>新增MSG - addP_messgae.jsp</h3>
			</td>
			<td>
				<h4>
					<a href="select_page.jsp"><img src="images/tomcat.png"
						width="100" height="100" border="0">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<h3>新增MSG:</h3>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<FORM METHOD="post" ACTION="p_message.do" name="form1"
		enctype="multipart/form-data">

		<h4>會員編號:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</h4>
		<select id="show" name="mem_no"></select>
		<script>
			$.ajax({
				url : "findMember.jsp",
				type : "GET",
				success : function(data) {
					showNames(data);
				}
			});

			function showNames(data) {
				var names = data.split(",");
				$("#show").html("");
				for (i in names) {
					var opt = $("<option>").val(names[i].trim()).text(names[i].trim());
					$("#show").append(opt);
				}
			}
		</script>
		<br/>
		
		<h4>需求編號:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</h4>
		<select id="show2" name="de_no"></select>
		<script>
			$.ajax({
				url : "findDe_no.jsp",
				type : "GET",
				success : function(data) {
					showNo(data);
				}
			});

			function showNo(data) {
				var no = data.split(",");
				$("#show2").html("");
				for (i in no) {
					var opt2 = $("<option>").val(no[i].trim()).text(no[i].trim());
					$("#show2").append(opt2);
				}
			}
		</script>

		<table>
			
			<tr>
				<td>MSG內容:</td>
			</tr>
			<tr>
				<td><textarea name="p_msg" id="p_msg" rows="10" cols="80"></textarea></td>
			</tr>
			

			<jsp:useBean id="p_messageSvc" scope="page"
				class="com.p_message.model.P_MessageService" />


		</table><br> 
		
		<input type="hidden" name="action" value="add"> 
		<input type="submit" value="送出需求">
	</FORM>


</body>
</html>