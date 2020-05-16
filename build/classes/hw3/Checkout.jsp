<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.product.model.*"%>

<html>
<head>
	<title>Checkout.jsp試作</title>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/ShoppingCart.css">
	<style type="text/css">
		img {
			width: auto;
			height: 50px;
		}
	</style>
</head>
<body>
	<img src="images/tomcat.gif"> <font size="+3">商城 - 結帳：（Checkout.jsp）</font>
	<hr><p>
	
	<table id="table-1" style="margin: auto;">
		<tr>
			<th width="200">商品圖片</th>
			<th width="120">商品編號</th>
			<th width="150">商品名稱</th>
			<th width="100">類別</th>
			<th width="120">價格</th>
			<th width="120">型號</th>
			<th width="200">詳細</th>
		    <th width="120">數量:</th>
			<th width="120"><h3>總價</h3></th>
		</tr>
	</table>
	<table style="margin: auto;">
		<%
			@SuppressWarnings("unchecked")
			Vector<ProductVO> buylist = (Vector<ProductVO>) session.getAttribute("shoppingcart");
			int amount = Integer.valueOf((String) request.getAttribute("amount"));
		%>
		<% for (int i = 0; i < buylist.size(); i++) {
			ProductVO order = buylist.get(i);
		%>
		<tr>
			<td width="200"><img src="<%=request.getContextPath()%>/product/productPic.do?p_id=<%=order.getP_id()%>"></td>
			<td width="120"><%=order.getP_id()%> 		</td>
			<td width="150"><%=order.getP_name()%>		</td>
			<td width="100"><%=order.getP_cat()%>		</td>
			<td width="120"><%=order.getP_pr()%>		</td>
			<td width="120"><%=order.getP_spec()%>		</td>
			<td width="200"><%=order.getP_detail()%> 	</td>
			<td width="100"><%=order.getQuantity()%>	</td>
			<td width="120"><%=(int)Math.round(order.getP_pr()*order.getQuantity())%></td>
		</tr>
		<% }%>
		<tr>
			<td colspan="6" style="text-align:right;"> 
				<font size="+2">總金額： $<%=amount%></font>
			</td>
		</tr>
	</table>
	<div align="center">
		<form  method="POST" action="<%=request.getContextPath()%>/order_master/order_master.do">
			<input type="hidden" name="action"  value="insertWithOrder_details">
			<input type="hidden" name="mem_no"  value="M0001">
			<input type="hidden" name="om_tpr"  value="<%=amount%>">
			<input type="submit" value="確定購買" class="button">
		</form>
	</div>
	<p><a href="<%=request.getContextPath()%>/shop/LetsgotripShop.jsp"><font size="+1"> 是 否 繼 續 購 物</font></a>
</body>
</html>