<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*"%>
<%@ page import="com.product.model.*"%>
<%@ page import="com.order_master.model.*"%>
<%@ page import="com.order_detail.model.*"%>
<%@ page import="com.member.model.*"%>
<%
	String mem_no=(String)session.getAttribute("mem_no");
	MemberService Membersvc=new MemberService();
	MemberVO memberVO=null;
	memberVO=Membersvc.getOneMember(mem_no);
	pageContext.setAttribute("memberVO", memberVO);	
%> 
<%
	Order_masterService order_masterSvc = new Order_masterService();
	List<Order_masterVO> list = order_masterSvc.findByMemno(mem_no);
	pageContext.setAttribute("list",list);
	Collections.reverse(list);
	List<Order_detailVO> list2 = null;
%>
<%
	HashMap<String,String> om_status = new HashMap<>();
		om_status.put("0", "待出貨");
		om_status.put("1", "已出貨");
		om_status.put("2", "交易完成");
		om_status.put("3", "換貨中");
		om_status.put("4", "退貨");
		om_status.put("5", "已取消");
	pageContext.setAttribute("om_status", om_status);
%>
<jsp:useBean id="productSvc" scope="page" class="com.product.model.ProductService" />
<jsp:useBean id="order_detailSvc" scope="page" class="com.order_detail.model.Order_detailService" />
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
	.side1 {
		top: 8em;
        position: fixed; 
    }
    .imgd {
    	border: none;
		background-color: rgba(0,0,0,0);
    }
    .btnhome{
    	background-color:orange;
    	color:#111;
    }
    .tablepart th,.tablepart td{
    	width: 230px;
    	text-align: center;
    }
	table {
		margin:auto;
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
						<input type="button" value="返回商城" class="paybtn btnhome" onclick="location.href='<%=request.getContextPath()%>/front-end/shop/shopHome.jsp'" >
					</li>
				</ul>
				</FORM>
			</div>
		</div>

		<div class="rightside">
			<br>
			<div class="outframe">
				<div>
					<h1>訂單明細 OrderDetail</h1>
				</div>
				<%@ include file="page1_order.file" %> 
				<c:forEach var="order_masterVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
				<div class="tablepart">
					<table class="tabletitle">
					    <tr style="background-color:orange;">
					    	<th colspan="4"><h3>訂單編號 :${order_masterVO.p_order_id}</h3></th>
					    </tr>
					    <tr>
					    	<th>下單時間：</th>
					    	<th><fmt:formatDate value='${order_masterVO.om_time}' pattern='yyyy-MM-dd HH:mm'/></th>
					    	<th></th>
					    	<th></th>	
					    </tr>	
					    <tr>
					    	<th>狀態：</th>
					    	<th><fmt:formatDate value='${order_masterVO.om_dlvr}' pattern='yyyy-MM-dd HH:mm'/></th>
					    	<th></th>
					    	<th>
					    		<c:forEach var="status" items="${om_status}">
									${order_masterVO.om_status==status.key? status.value : ''}
								</c:forEach>
							</th>
					    </tr>
					    <tr style="border-bottom: 1px dotted black;">
					    	<th></th>
							<th>名稱</th>
							<th>單價</th>
							<th>數量</th>
					    </tr>
					</table>
				</div>
				<div class="tablepart">
					<table class="cartlist" >
					<c:forEach var="order_detailVO" items="${order_detailSvc.getOrder_detailByP_order_id(order_masterVO.p_order_id)}">
						<tr>
							<td class="imgframe"><img src="<%=request.getContextPath()%>/product/productPic.do?p_id=${order_detailVO.p_id}"></td>
							<td>${productSvc.getOneProduct(order_detailVO.p_id).p_name}</td>
							<td>${order_detailVO.od_pr}</td>
							<td>${order_detailVO.od_qty}</td>
						</tr>
					</c:forEach>
					</table>
				</div>
				<div class="tablepart">
					<table class="tabletitle" >
						<tr style="border-top: 1px dotted black;">
							<th>總額</th>
							<th></th>
							<th></th>
							<th><span>$NT </span>${order_masterVO.om_tpr}</th>
						</tr>
					</table>
				</div>
				<br><br><br>
				</c:forEach>
				<%@ include file="page2.file" %>
			</div>
			<br>
		</div>
	</div>
	<%@ include file="/front-end/EndFooter.file" %>
</body>
</html>