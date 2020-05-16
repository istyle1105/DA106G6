<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.product.model.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.cart.model.*"%>
<%@ page import="com.wallet.model.*"%>
<%
	String mem_no=(String)session.getAttribute("mem_no");
	MemberService Membersvc=new MemberService();
	MemberVO memberVO=null;
	memberVO=Membersvc.getOneMember(mem_no);
	pageContext.setAttribute("memberVO", memberVO);	
	
	CartService cartSVC = new CartService();
%> 
<jsp:useBean id="walletSVC" scope="page" class="com.wallet.model.WalletService" />
<jsp:useBean id="productSvc" scope="page" class="com.product.model.ProductService" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<head>
	<title>ShopCheckout</title>
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
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/sweetalert2.min.css">
    
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front-end/shop/css/shopSideStyle.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front-end/shop/css/cartStyle.css">
	
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
    .btns {
    	display:inline !important;
    }
    .btnhome{
    	background-color:#0794F9;
    	color:#111;
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
	.btnhome:hover{
    	background-color:#2638ED;
    	color:#111;
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
						<a class="" href="<%=request.getContextPath()%>/front-end/shop/shoppingRedisCart.jsp">購物車
							<br>
							<span>Shopping Cart</span>
						</a>
					</li>
					<li>
						<a>會員餘額
						<br>
						<span>$NT </span><span>${walletSVC.getTotalByMen_no(mem_no)}</span>
						</a>
					</li>
					<li>
						<input type="button" value="返回商城" class="paybtn btnhome" onclick="location.href='<%=request.getContextPath()%>/front-end/shop/shopHome.jsp'" >
					</li>
				</ul>
				</FORM>
			</div>
		</div>
		<%
			@SuppressWarnings("unchecked")
			List<ProductVO> buylist = (List<ProductVO>)session.getAttribute("buylist");
			Integer amount = (Integer)request.getAttribute("amount");
			request.setAttribute("amount",amount);
		%>
		<div class="rightside">
			<br>
			<div class="outframe">
				<div>
					<h1>結帳前確認 Check </h1>
				</div>
				<table class="tabletitle">
				    <tr>
				    	<th></th>
						<th class="imgframe"></th>
						<th>編號</th>
						<th>名稱</th>
						<th>價格</th>
						<th>數量</th>
						<th>總額</th>
				    </tr>
				</table>
				<% for (int i = 0; i < buylist.size(); i++) {
					ProductVO order = buylist.get(i);
				%>
				<table class="cartlist">
					<tr>
						<td class="imgframe"><img src="<%=request.getContextPath()%>/product/productPic.do?p_id=<%=order.getP_id()%>"></td>
						<td><%=order.getP_id()%></td>
						<td><%=productSvc.getOneProduct(order.getP_id()).getP_name()%></td>					
						<td><span>$NT </span><%=order.getP_pr()%></td>
						<td><%=order.getQuantity()%></td>
						<td><span>$NT </span><%= (cartSVC.getValueByP_id(mem_no,order.getP_id()))*(order.getP_pr())%></td>
					</tr>
				<% }%>
					<tr>
						<td colspan="4">
						<td>總金額</td>
						<td><span>$NT </span><%=amount %></td>
					</tr>
				</table>
				<br>
				<form  method="POST" action="<%=request.getContextPath()%>/order_master/order_master.do">
					<input type="hidden" name="action"  value="insertWithOrder_detailsAndwallet">
					<input type="hidden" name="requestURL"  value="<%=request.getServletPath()%>">
					<input type="hidden" name="mem_no"  value="${memberVO.mem_no}">
					<input type="hidden" name="om_tpr"  value="<%=amount%>">
					<input type="submit" value="確定購買" class="paybtn">
				</form>
			</div>
		</div>
	</div>
	<%@ include file="/front-end/EndFooter.file" %>
	
	<script>
		var state='${param.state}'
		if(state=="enoughmoney"){
			Swal.fire({
				title: '會員餘額不足',
				icon: 'warning',
				showCancelButton: true,
				icon: 'warning',
				showCancelButton: true,
				confirmButtonText: '儲值去',
				cancelButtonText: '返回首頁',
				reverseButtons: true
			}).then((result) => {
				if (result.value) {
					document.location.href="<%=request.getContextPath()%>/front-end/member/chargePage.jsp";
				} else if (
					result.dismiss === Swal.DismissReason.cancel
				) {
					document.location.href="<%=request.getContextPath()%>/front-end/shop/shopHome.jsp";
				}
			})
		}
	</script>
</body>
</html>