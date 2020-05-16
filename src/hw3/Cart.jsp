<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.product.model.*"%>

<html>
<head>
	<title>Cart</title>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/ShoppingCart.css">
</head>
<body>
	<br>
	<% @SuppressWarnings("unchecked") 
	Vector<ProductVO> buylist = (Vector<ProductVO>) session.getAttribute("shoppingcart");%>
	<% if(buylist != null && (buylist.size() > 0)) {%>
	<img src="images/tomcat.gif"> <font size="+3">目前購物車的內容如下：（Cart.jsp）</font>
	
	<table id="table-1">
	    <tr> 
			<th width="200">商品圖片</th>
			<th width="120">商品編號</th>
			<th width="150">商品名稱</th>
			<th width="100">類別</th>
			<th width="120">價格</th>
			<th width="120">型號</th>
			<th width="200">詳細</th>
		    <th width="120">數量:</th>
		    <th width="120">刪除?</th>
	    </tr>
	</table>
	<table>
		<% for (int index = 0; index < buylist.size(); index++){
			ProductVO order = buylist.get(index);
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
	        <td width="100">
				<form name="deleteForm" action="<%=request.getContextPath()%>/shop/Shopping.do" method="POST">
					<input type="hidden" name="action"  value="DELETE">
					<input type="hidden" name="del" value="<%= index %>">
					<input type="submit" value="刪 除" class="button">
				</form>
			</td>
		</tr>
		<% }%>
	</table>
	<p>
	<form name="checkoutForm" action="<%=request.getContextPath()%>/shop/Shopping.do" method="POST">
		<input type="hidden" name="action"  value="CHECKOUT"> 
		<input type="submit" value="付款結帳" class="button">
	</form>
	<% }%>
</body>
</html>