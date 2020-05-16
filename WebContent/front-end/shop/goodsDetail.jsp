<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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

<%
	ProductVO productVO = (ProductVO) request.getAttribute("productVO");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>商品詳細</title>
	
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
    
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front-end/shop/css/layout_base.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front-end/shop/css/type_base.css">

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
		.shopping-cart {
			position: fixed;
			height: 50px;
			width: auto;
			cursor: pointer;
			top:80%;
			left:95%;
			transition: all 0.5s; 
		}
		.shopping-cart:hover {
			transform: scale(1.2,1.2); 
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
		.side1 {
			top: 12em;
	        position: fixed; 
	    }
		input[type="number"] {
			-webkit-appearance: textfield;
			-moz-appearance: textfield;
			appearance: textfield;
		}

		input[type=number]::-webkit-inner-spin-button,
		input[type=number]::-webkit-outer-spin-button {
			-webkit-appearance: none;
		}

		.number-input {
			border: 2px solid #ddd;
			display: inline-flex;
		}

		.number-input,
		.number-input * {
			box-sizing: border-box;
		}

		.number-input button {
			outline:none;
			-webkit-appearance: none;
			background-color: transparent;
			border: none;
			align-items: center;
			justify-content: center;
			width: 25px;
			height: 25px;
			cursor: pointer;
			margin: 0;
			position: relative;
		}

		.number-input button:before,
		.number-input button:after {
			display: inline-block;
			position: absolute;
			content: '';
			width: 9px;
			height: 1px;
			background-color: #212121;
			transform: translate(-50%, -50%);
		}
		.number-input button.plus:after {
			transform: translate(-50%, -50%) rotate(90deg);
		}

		.number-input input[type=number] {
			font-family: sans-serif;
			max-width: 45px;
			padding: .50px;
			border: solid #ddd;
			border-width: 0 2px;
			font-size: 17px;
			height: 25px;
			font-weight: bold;
			text-align: center;
		}
		button.checkout img {
			width: 149px;
			height: 42px;
			content:url("<%=request.getContextPath()%>/front-end/shop/images/button01.png");
		}
		
		button.checkout img:hover {
			width: 149px;
			height: 42px;
			content:url("<%=request.getContextPath()%>/front-end/shop/images/button02.png");
		}
		
		button.cart img {
			width: 149px;
			height: 42px;
			content:url("<%=request.getContextPath()%>/front-end/shop/images/button03.png");
		}
		
		button.cart img:hover {
			width: 149px;
			height: 42px;
			content:url("<%=request.getContextPath()%>/front-end/shop/images/button04.png");                  
		}

	</style>
</head>
<body>
	<%@ include file="/front-end/TopHeader.file" %>
<body class="products">
	<div class="container">
		<div class="main_bg">
			<div class="main">
				<div class="side1">
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
					</ul>
				</div>
				
				<div class="content" style="left: 230px">
					<div class="sec1">
						<h2>
							<p class="c">
								${productVO.p_name}
							</p>
							<input type="hidden" name="p_name" value="${productVO.p_name}">
						</h2>
						<div class="exhibit">
							<div class="pro">
								<img title="${productVO.p_name}" src="<%=request.getContextPath()%>/product/productPic.do?p_id=${productVO.p_id}" style="width: 377px; border-width: 0px; opacity: 1;">
								<input type="hidden" name="p_pic" value="${productVO.p_pic}">
							</div>
							
							<div class="tt">
								<ul class="detail">
									<li>
										<span class="fields">商品編號：</span>
										<p><span>${productVO.p_id}</span></p>
										<input type="hidden" name="p_id" value="${productVO.p_id}">
									</li>
									<li>
										<span class="fields">商品類別：</span>
										<p><span>${productVO.p_cat}</span></p>
										<input type="hidden" name="p_cat" value="${productVO.p_cat}">
									</li>
									
									<li id="NOW_PRICE_LI">
										<span class="fields">售　價　：</span>
										<p><span class="red"><span>NT$ </span>${productVO.p_pr}</span></p>
										<input type="hidden" name="p_pr" value="${productVO.p_pr}">
									</li>
									<li id="QTY_LI">
										<span class="fields">數　　量：</span>
										<div class="number-input">
											<button id="btn1" onclick="this.parentNode.querySelector('input[type=number]').stepDown()" ></button>
											<input id="qty1" class="quantity" min="0" name="qty" value="1" type="number">
											<button id="btn2" onclick="this.parentNode.querySelector('input[type=number]').stepUp()" class="plus"></button>
										</div>
										<span style="color:Red;"></span>
									</li>
								</ul>
								<FORM  METHOD="post" ACTION="<%=request.getContextPath()%>/shop/ShoppingRedis.do" enctype="multipart/form-data">
								<ul id="SHOP_UL" class="buttons">
									<li class="noright">
										<button id="subForm" class="cart" id="JoinCarButton" name="action" value="ADD">
											<img src="" alt="放入購物車">
										</button>
									</li>		
								</ul>
								<input type="hidden" name="p_pic" value="${productVO.p_pic}">
								<input type="hidden" name="p_id" value="${productVO.p_id}">
								<input type="hidden" name="p_pr" value="${productVO.p_pr}">
								<input type="hidden" name="p_name" value="${productVO.p_name}">
								<input type="hidden" name="quantity" value="1" id="qty2">
								</form>
							</div>
						</div>
					</div>
					<div class="sec2">
						<div id="tabs" class="ui-tabs ui-widget ui-widget-content ui-corner-all">
							<ul id="nav5" class="nav_h ui-tabs-nav ui-helper-reset ui-helper-clearfix ui-widget-header ui-corner-all" style="padding-top: 400px">
								<li class="ui-state-default ui-corner-top ui-tabs-active ui-state-active">
									<a href="#tabs-1" class="w1-1 ui-tabs-anchor"id="ui-id-1">商品詳細</a>
								</li>
								<li class="ui-state-default ui-corner-top">
									<a href="#tabs-2" class="w1-1 ui-tabs-anchor"id="ui-id-2">商品規格</a>
								</li>
							</ul>
							<div id="tabs-1" class="ui-tabs-panel ui-widget-content ui-corner-bottom">
								${productVO.p_detail}
								<input type="hidden" name="p_detail" value="${productVO.p_detail}">
							</div>
							<div id="tabs-2" class="ui-tabs-panel ui-widget-content ui-corner-bottom" style="display: none;">
								<span class="fields">商品型號：</span>${productVO.p_spec}
								<%@ include file="goodsexplain.file" %>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
    <%@ include file="/front-end/EndFooter.file" %>
    
	<script type="text/javascript" src="<%=request.getContextPath()%>/front-end/shop/js/jquery3_3_1.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/front-end/shop/js/jquery-ui.min.js"></script>
	
	<script type="text/javascript">
		$(function () {
			$("#tabs").tabs();
			$("#tabs2").tabs();
		});
		$(function(){
			$("#btn1").click(function(){
				$("#qty2").val($("#qty1").val());
			});
			$("#btn2").click(function(){
				$("#qty2").val($("#qty1").val());
			});
		});
	</script>

</body>
</html>