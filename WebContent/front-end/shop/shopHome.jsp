<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.product.model.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.wallet.model.*"%>
<%
	String mem_no=(String)session.getAttribute("mem_no");
	MemberService Membersvc=new MemberService();
	MemberVO memberVO=null;
	memberVO=Membersvc.getOneMember(mem_no);
	pageContext.setAttribute("memberVO", memberVO);	
%> 
<%	
	ProductService productSvc = new ProductService();
	List<ProductVO> list = null;
	if (request.getParameter("p_cat") == null) {
	    list = productSvc.get_ALLByP_status();
	} else {
		list = productSvc.getAllByCat(request.getParameter("p_cat"));
	}
	Collections.reverse(list); 	
	pageContext.setAttribute("list",list);
	
	WalletService walletSVC = new WalletService();
	Integer w_amount = walletSVC.getTotalByMen_no(mem_no);
	pageContext.setAttribute("w_amount", w_amount);	

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>ShopHome</title>
	
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
    
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front-end/shop/css/shopStyle.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front-end/shop/css/shopSideStyle.css">
	
	<style type="text/css">
	
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
			width: 1350px !important;
			margin-bottom: 50px;
		}
		.sale_category li {
			list-style-type: disc !important;
		}
		.shopping-cart {
			position: fixed;
			height: 50px;
			width: auto;
			cursor: pointer;
			top:35%;
			left:95%;
			transition: all 0.5s; 
			z-index:99999;
		}
		.shopping-cart:hover {
			transform: scale(1.2,1.2); 
		}
		.side1 {
	        position: fixed; 
	        top: 10em;
	    }
	    .imgd {
	    	border: none;
			background-color: rgba(0,0,0,0);
	    }
	</style>
</head>
<body>
	<%@ include file="/front-end/TopHeader.file" %>
	<img onclick="location.href='<%=request.getContextPath()%>/shop/ShoppingRedis.do?action=none'" src="<%=request.getContextPath()%>/front-end/shop/images/shopping-cart.png" alt="購物車" class="shopping-cart">
	<div id="content" class="container_48">
		<div class="main container_48">
			<div class="line_right container_48">
				<div>
					<input type="hidden" id="categoryMap">
					<div id="exhibit" class="">
						<div id="sidenav" class="grid_9 alpha"  style="margin-right: 40px;">
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
								</ul>
								</FORM>
							</div>
						</div>
						<div class="grid_38 alpha omega" style="left: 230px">
							<table border="0" cellpadding="0" cellspacing="0" width="100%">
								<tbody>
									<tr>
										<td colspan="4">
											<div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
												<ol class="carousel-indicators">
													<li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
													<li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
													<li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
													<li data-target="#carouselExampleIndicators" data-slide-to="3"></li>
													<li data-target="#carouselExampleIndicators" data-slide-to="4"></li>
												</ol>
												<div class="carousel-inner" style="height:400px;">
													<div class="carousel-item active">
														<img src="<%=request.getContextPath()%>/front-end/shop/images/top05.png" class="d-block w-100" alt="pic1" style="">
													</div>
													<div class="carousel-item">
														<img src="<%=request.getContextPath()%>/front-end/shop/images/top04.png" class="d-block w-100" alt="pic2">
													</div>
													<div class="carousel-item">
														<img src="<%=request.getContextPath()%>/front-end/shop/images/top03.png" class="d-block w-100" alt="pic3" >
													</div>
													<div class="carousel-item">
														<img src="<%=request.getContextPath()%>/front-end/shop/images/top02.png" class="d-block w-100" alt="pic4" >
													</div>
													<div class="carousel-item">
														<img src="<%=request.getContextPath()%>/front-end/shop/images/top01.png" class="d-block w-100" alt="pic5" >
													</div>
												</div>
												<a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
													<span class="carousel-control-prev-icon" aria-hidden="true"></span>
													<span class="sr-only">Previous</span>
												</a>
												<a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
													<span class="carousel-control-next-icon" aria-hidden="true"></span>
													<span class="sr-only">Next</span>
												</a>
											</div>
										</td>
									</tr>
								</tbody>
							</table>	
							<%@ include file="page1.file" %> 
							<ul id="newProductList" class="list_display list_outfit">
								<c:forEach var="productVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
									<li>
										<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/product/product.do" enctype="multipart/form-data">
											<button type="submit" class="imgd" name="action" value="detailview">
												<img src="<%=request.getContextPath()%>/product/productPic.do?p_id=${productVO.p_id}" alt="" name="p_pic">
											</button>
											<input type="hidden" name="p_id" value="${productVO.p_id}">
										</FORM>
										<div class="productname">${productVO.p_name}</div>
										<br>
										<span class="hidden" style="display: inline;">
											<span id="currencyIdentifier">NT$</span>
											<span class="currency symbol">${productVO.p_pr}</span>
										</span>
									</li>
								</c:forEach>
							</ul>
							<%@ include file="page2.file" %>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="/front-end/EndFooter.file" %>
<%
	String noMemUrl=request.getContextPath()+"/shop/ShoppingRedis.do?action=none";
	pageContext.setAttribute("noMemUrl", noMemUrl);
	String MemUrl=request.getContextPath()+"/shop/ShoppingRedis.do?action=none&mem_no"+mem_no;
	pageContext.setAttribute("MemUrl", MemUrl);
	%>
	
<script>

function shoppingCart(){
	var url='${url}';
	var mem_no='${mem_no}';
	console.log(mem_no);
	if(mem_no==""){
		location.href="http://localhost:8081/"+"${noMemUrl}";
	}else{
		location.href="http://localhost:8081/"+"${MemUrl}";
	}
}


</script>
</body>
</html>