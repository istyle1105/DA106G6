<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.demand.model.*"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>

<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
	HashMap<Integer, String> Status = new HashMap();
	Status.put(0, "上架中");
	Status.put(1, "已下架");
	Status.put(2, "已下標");
	Status.put(3, "已購買");
	Status.put(4, "運送中");
	Status.put(5, "已送達");
	Status.put(6, "交易完成");
	pageContext.setAttribute("Status", Status);
	
// 	HashMap Status=new HashMap();	
// 	ServletContext context=getServletContext();
// 	Status =(HashMap)context.getAttribute("Status");

	
%>

<%
  DemandVO demandVO = (DemandVO) request.getAttribute("demandVO"); //DemandServlet.java(Concroller), 存入req的demandVO物件
%>

<html>
<head>
<title>需求明細</title>

<style>
 #table-headerOne {
	width: 600px;
	height: 80px;
	background-color: #FFFFFF;
	margin-top: 5px;
	margin-bottom: 10px;
	padding-top: 17px;
	border: 3px ridge Gray;
	text-align: center;
	background-image: url('images/LOGO.jpeg'), url('images/LOGO.jpeg');
	background-position: left, right;
	background-size: 120px 150%;
    background-repeat: no-repeat;
  }
  #homepageOneDemand {
    color: blue;
    display: inline;
    font-size: 17px;
  }
  #all_OneDemand {
	position: absolute;
  }
  body {
	display: flex;
	justify-content: center;
  }
  .table-bordered {
  	text-align: center;
  }
</style>

</head>
<body>

<table class="table table-bordered table-dark">

	<tbody>
   		<tr>
      		<th scope="row">需求編號</th>
      		<td><%=demandVO.getDe_no()%></td>
    	</tr>
    	<tr>
      		<th scope="row">需求商品名稱</th>
      		<td><%=demandVO.getDe_item_name()%></td>
    	</tr>
    	<tr>
      		<th scope="row" style="line-height: 100px;">需求商品照片</th>
      		<td><img src = "data:image/png;base64,${demandVO.de_photo}" height="100"></td>
    	</tr>
    	<tr>
      		<th scope="row">價格</th>
      		<td><%=demandVO.getPrice()%></td>
    	</tr>
    	<tr>
      		<th scope="row">需求者編號</th>
      		<td>${demandVO.de_mem_no}</td>
    	</tr>
    	<tr>
      		<th scope="row">地區</th>
      		<td><%=demandVO.getArea()%></td>
    	</tr>
    	<tr>
      		<th scope="row">需求時間</th>
      		<td><fmt:formatDate value="${demandVO.de_time}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
    	</tr>
    	<tr>
      		<th scope="row">需求狀態</th>
      		<td>
				<c:forEach var="status" items="${Status}">
					${demandVO.status==status.key ? status.value : ''}
				</c:forEach>
			</td>
    	</tr>
    	<tr>
      		<th scope="row" style="line-height: 38px;">更新/刪除</th>
      		<td style="padding-bottom: 0px;">
      			<div class="btn-group" role="group" aria-label="Basic example" style="display: flex; justify-content: space-around; height:100%">
				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/demand/demand.do">
			   		<input type="submit" value="更新需求" class="btn btn-secondary">
			   		<input type="hidden" name="de_no"  value="${demandVO.de_no}">
			  		<input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
			   		<input type="hidden" name="action"	value="getOne_For_Update">
				</FORM>
				
				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/demand/demand.do">
					 <input type="submit" value="刪除需求" class="btn btn-secondary">
					 <input type="hidden" name="de_no"  value="${demandVO.de_no}">
				     <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
				     <input type="hidden" name="action" value="delete">
		    	</FORM>
		    	</div>
			</td>
  	</tbody>
</table>

</div>
</body>
</html>