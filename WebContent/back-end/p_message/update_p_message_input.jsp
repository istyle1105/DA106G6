<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.p_message.model.*"%>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>

<%
	P_MessageVO p_messageVO = (P_MessageVO) request.getAttribute("p_messageVO"); //EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>需求資料修改 - update_p_message_input.jsp</title>

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
    float:left;
  }
  textarea {
  	height:80px;
  	width: 500px;
  }
  #msg {
  	border: 1px solid;
  	height: 200px;
  	width: 500px;
  }
</style>

</head>
<body bgcolor='white'>

<table id="table-1">
	<tr><td>
		 <h3>MSG修改 - update_p_message_input.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<h3>MSG修改:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="p_message.do" name="form1" enctype="multipart/form-data">
<table>
	<tr>
		<td>MSG編號:<font color=red><b>*</b></font></td>
		<td><%=p_messageVO.getP_msg_no()%></td>
	</tr>
	<tr>
		<td>會員編號:</td>
		<td><%=p_messageVO.getMem_no()%></td>
	</tr>
	<tr>
		<td>需求編號:</td>
		<td><%=p_messageVO.getDe_no()%></td>
	</tr>
	<tr>
		<td>MSG內容:</td>
	</tr>
	<tr>
		<td id="msg"><%=p_messageVO.getP_msg()%></td>
	</tr>
	<tr>
		<td>請輸入MSG內容:</td>
		<td><textarea name="p_msg" id="p_msg" rows="10"></textarea></td>
	</tr>

	<jsp:useBean id="p_messageSvc" scope="page" class="com.p_message.model.P_MessageService" />
	
</table>	


<input type="hidden" name="action" value="update">
<input type="hidden" name="p_msg_no" value="<%=p_messageVO.getP_msg_no()%>">
<input type="hidden" name="p_msg2" value="<%=p_messageVO.getP_msg()%>">
<input type="hidden" name="de_no"  value="<%=p_messageVO.getDe_no()%>">
<input type="hidden" name="mem_no"  value="<%=p_messageVO.getMem_no()%>">
<input type="hidden" name="p_msg_time"  value="<%=p_messageVO.getP_msg_time()%>">
<input type="submit" value="送出修改"></FORM>


</body>
</html>