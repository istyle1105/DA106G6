<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.p_message.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
  P_MessageVO p_messageVO = (P_MessageVO) request.getAttribute("p_messageVO"); //DemandServlet.java(Concroller), 存入req的demandVO物件
%>

<html>
<head>
<title>MSG資料 - listOneP_message.jsp</title>

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
	width: 600px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
</style>

</head>
<body bgcolor='white'>

<h4>此頁暫練習採用 Script 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>需求資料 - ListOneP_message.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>MSG編號</th>
		<th>需求編號</th>
		<th>會員編號</th>
		<th>MSG內容</th>
		<th>MSG時間</th>
	</tr>
	<tr>
		<td><%=p_messageVO.getP_msg_no()%></td>
		<td><%=p_messageVO.getDe_no()%></td>
		<td><%=p_messageVO.getMem_no()%></td>
		<td><%=p_messageVO.getP_msg()%></td>
		<td><%=p_messageVO.getP_msg_time()%></td>
	</tr>
</table>

</body>
</html>