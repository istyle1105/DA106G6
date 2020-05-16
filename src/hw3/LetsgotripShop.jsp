<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.product.model.*"%>

<%
    ProductService productSvc = new ProductService();
    List<ProductVO> list = productSvc.getAll();
    pageContext.setAttribute("list",list);
%>

<html>
<head>
	<meta charset="UTF-8">
	<title>LetsgotripShop</title>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/ShoppingCart.css">
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
		img {
			width: auto;
			height: 50px;
		}
		table {
			width: 1000px;
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
<body>
	<img src="images/tomcat.gif"> <font size="+3">Lets Go Trip商店</font>
	<br>
	<table id="table-1">
		<tr> 
			<th width="200">商品圖片</th>
			<th width="120">商品編號</th>
			<th width="150">商品名稱</th>
			<th width="100">類別</th>
			<th width="120">價格</th>
			<th width="120">型號</th>
			<th width="200">詳細</th>
			<th width="100">數量:</th>
			<th width="100"><img src="images/shopping-cart.png" width="45px" height="35px"></th>
		</tr>
		<%@ include file="page1.file" %> 
		
		<c:forEach var="productVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/shop/Shopping.do" enctype="multipart/form-data">
		<table>
			<tr> 
				<td width="200"><div align="center"><img src="<%=request.getContextPath()%>/product/productPic.do?p_id=${productVO.p_id}"></div></td>
				<td width="120"><div align="center">${productVO.p_id}</div></td>
				<td width="150"><div align="center">${productVO.p_name}</div></td>
				<td width="100"><div align="center">${productVO.p_cat}</div></td>
				<td width="120"><div align="center">${productVO.p_pr}</div></td>
				<td width="120"><div align="center">${productVO.p_spec}</div></td>
				<td width="200"><div align="center">${productVO.p_detail}</div></td>	
				<td width="100"><div align="center">數量：<input type="text" name="quantity" size="3" value=1></div></td>
				<td width="100"><div align="center">    <input type="submit" class="button" value="放入購物車"> </div></td>
			</tr>
		</table>		
			
			    <input type="hidden" name="p_pic" value="${productVO.p_pic}">
				<input type="hidden" name="p_id" value="${productVO.p_id}">
				<input type="hidden" name="p_name" value="${productVO.p_name}">
				<input type="hidden" name="p_cat" value="${productVO.p_cat}">
				<input type="hidden" name="p_pr" value="${productVO.p_pr}">
				<input type="hidden" name="p_spec" value="${productVO.p_spec}">
				<input type="hidden" name="p_detail" value="${productVO.p_detail}">
				<input type="hidden" name="action" value="ADD">
		</FORM>
		</c:forEach>

	</table>
	<%@ include file="page2.file" %>
	<br> 

</body>
</html>