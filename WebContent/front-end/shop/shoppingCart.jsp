<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.product.model.*"%>
<%@ page import="com.member.model.*"%>
<%
	String mem_no=(String)session.getAttribute("mem_no");
	MemberService Membersvc=new MemberService();
	MemberVO memberVO=null;
	memberVO=Membersvc.getOneMember(mem_no);
	pageContext.setAttribute("memberVO", memberVO);
%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<head>
	<title>ShoppingCart</title>
		<link rel="icon" href="<%=request.getContextPath()%>/front-end/img/favicon.png">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front-end/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front-end/css/animate.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front-end/css/owl.carousel.min.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front-end/css/themify-icons.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front-end/css/flaticon.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front-end/fontawesome/css/all.min.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front-end/css/magnific-popup.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front-end/css/gijgo.min.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front-end/css/nice-select.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front-end/css/slick.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front-end/css/style.css">
    
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front-end/shop/css/shopSideStyle.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front-end/shop/css/cartStyle.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front-end/shop/css/ckeckStyle.css">
	<style type="text/css">
	.leftside {
	    background: none repeat scroll 0 0 transparent;
	    float: left;
	    width: 212px;
	}
	.rightside {
		float: right;
		width: calc(100% - 18.5em);
		height: 100%;
		overflow-x: hidden;
	}
	body{
        background-image: url('<%=request.getContextPath()%>/front-end/shop/images/background5.jpg') !important;
        background-repeat: no-repeat !important;
        background-size: cover !important;
        background-attachment: fixed !important; 
        background-position: center !important;
    }
	.nav {
		display: block !important;
	}
	.container_48 {
		top: 8em;
		margin-left:auto;
		margin-right:auto;
		width: 1350px !important;
		lefet: ; 
	}
	.sale_category li {
		list-style-type: disc !important;
	}

	.side1 {
		top: 8em;
        position: fixed; 
    }
    .imgd {
    	border: none;
		background-color: rgba(0,0,0,0);
    }
    .outframe table tr th,.outframe table tr td {
    	width:100px;
    }
	table {
		margin:auto;
	}
	.paybtn:hover{
		background-color:orange;
	}
	.cleanbtn:hover{
		background-color:#DF3535;
	}
	.tabletitle, .cartlist {
		width: 1000px !important;
	}
	</style>
</head>
<body>
	<%@ include file="/front-end/TopHeader.file" %>
	<div class="container_48">
		<div class="leftside">
			<div class="side1">
				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/product/product.do" enctype="multipart/form-data">
				<ul class="nav" id="sublink">
					<li>
						<a class="" href="<%=request.getContextPath()%>/front-end/shop/shopHome.jsp">總覽
							<br>
							<span>All</span>
						</a>
					</li>
					<li>
						<a class="" href="<%=request.getContextPath()%>/front-end/shop/shopHome.jsp?p_cat=0">行李箱
							<br>
							<span>Suitcase</span>
						</a>        
					</li>
					<li>
						<a href="<%=request.getContextPath()%>/front-end/shop/shopHome.jsp?p_cat=1">旅行袋
							<br>
							<span>Travel Bag</span>
						</a>              
					</li>
					<li>
						<a href="<%=request.getContextPath()%>/front-end/shop/shopHome.jsp?p_cat=2">背　包
							<br>
							<span>Backpack</span>
						</a>               
					</li>
					<li>
						<a href="<%=request.getContextPath()%>/front-end/shop/shopHome.jsp?p_cat=3">旅行配件
							<br>
							<span>Goods</span>
						</a>
					</li>
					<li>
						<a href="<%=request.getContextPath()%>/front-end/shop/shopHome.jsp?p_cat=4">3C配件
							<br>
							<span>3C</span>
						</a> 
					</li>    
					<li>
						<a href="<%=request.getContextPath()%>/front-end/shop/shopHome.jsp?p_cat=5">書籍
							<br>
							<span>Book</span>
						</a> 
					</li>
					<li>
						<a class="" href="<%=request.getContextPath()%>/front-end/shop/shoppingCart.jsp">購物車
							<br>
							<span>Shopping Cart</span>
						</a>
					</li>
				</ul>
				</FORM>
			</div>
		</div>

		<div class="rightside">
			<br>
			<div class="outframe">
				<div>
					<h1>購物車明細 ShoppingCart</h1>
				</div>
				<form name="deleteForm" action="<%=request.getContextPath()%>/shop/ShoppingRedis.do" method="POST">
					
					<table class="tabletitle">
					    <tr>
					    	<th></th>
							<th class="imgframe"></th>
							<th>編號</th>
							<th>名稱</th>
							<th>價格</th>
							<th>數量</th>
							<th>總額</th>
						    <th>動作</th>
					    </tr>
					</table>
					<table class="cartlist">
					<%
						@SuppressWarnings("unchecked") 
						List<ProductVO> buylist = (List<ProductVO>) session.getAttribute("buylist");
						
					%>
					<% double total = 0;%>
					<%
					if(buylist == null){
						
					%>
					<tr></tr>
					<%
					} else {
					%>
					<% if(buylist != null && (buylist.size() > 0)) {%>
					<% for (int index = 0; index < buylist.size(); index++){
						ProductVO order = buylist.get(index);
						total+=Math.round(order.getP_pr()*order.getQuantity());
					%>
					<tr>
						<td>
							<label class="checklabel">
								<input type="checkbox" name="checkact" value="<%= index %>">
								<span class="checkmark"></span>
							</label>
						</td>
						<td class="imgframe"><img src="<%=request.getContextPath()%>/product/productPic.do?p_id=<%=order.getP_id()%>"></td>
						<td><%=order.getP_id()%></td>
						<td><%=order.getP_name()%></td>					
						<td><span>$NT </span><%=order.getP_pr()%></td>
						<td><%=order.getQuantity()%></td>	
						<td><span>$NT </span><%=(int)Math.round(order.getP_pr()*order.getQuantity())%></td>
				        <td>	
							<input type="hidden" name="action"  value="deleteSelected">
							<input type="hidden" name="del" value="<%= index %>">
							<input type="button" value="刪 除" class="button"  
								onclick="location.href='<%=request.getContextPath()%>/shop/ShoppingRedis.do?action=DELETE&del=<%=index%>&p_id=<%=order.getP_id()%>'">
						</td>
					</tr>
					<% }%>
					<tr>
						<td colspan="5">
						<td>總金額<span> $NT </span></td>
						<td><%=(int)total %></td>
						<td>
					</tr>
					</table>
					<br>
					<br>
					<div>
						<input type="submit" value="清除勾選" class="paybtn cleanbtn">
					</div>
				</form>

				<div style="margin-left:230px;margin-top:-55px">
					<form name="checkoutForm" action="<%=request.getContextPath()%>/shop/ShoppingRedis.do" method="POST">
						<input type="hidden" name="action"  value="CHECKOUT"> 
						<input type="hidden" name="loginLocation"  value="<%=request.getRequestURI()%>"> 
						<input type="submit" value="前往結帳" class="paybtn">
					</form>
				</div>
			</div>
			<br>

			<% }}%>
		</div>
	</div>
	<%@ include file="/front-end/EndFooter.file" %>
<script>	
$(function(){
	$("#btn1").click(function(){
		$ajax({
			type:"GET",
			url:"<%=request.getContextPath()%>/shop/CartChange.do?action=REPLACE",
			success: function (data){}
		});	
	});
	$("#btn2").click(function(){
		$ajax({
			type:"GET",
			url:"<%=request.getContextPath()%>/shop/CartChange.do?action=REPLACE",
			success: function (data){}
		});
	});
});

</script>
</body>
</html>