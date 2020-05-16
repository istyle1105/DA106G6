<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
<%@ page import="com.log.model.*"%>    
    
    
<!DOCTYPE html>
<%LogVO logvo = (LogVO) request.getAttribute("logvo"); %>

<html>
<head>
<style>

</style>
<meta charset="BIG5">
<title>Insert title here</title>
</head>
<body>

<table id="lasttable">
<tr>
	<th>日誌內容</th>
</tr>
<tr>
	<td>${logvo.log_con}</td>
</tr>


</table>


</body>
</html>