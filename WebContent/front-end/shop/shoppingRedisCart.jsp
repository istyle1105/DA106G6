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
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/sweetalert2.min.css">
    
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

	.number-input img {
		outline:none;
		-webkit-appearance: none;
		background-color: transparent;
		border: none;
		align-items: center;
		justify-content: center;
		top: 5px;
		width: 15px;
		height: 15px;
		cursor: pointer;
		margin: 0;
		position: relative;
	}

	.number-input img:before,
	.number-input img:after {
		display: inline-block;
		position: absolute;
		content: '';
		width: 5px;
		height: 1px;
		background-color: #212121;
		transform: translate(-50%, -50%);
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
	.swal-wide{
	    width:1100px;
	    height:400px;
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
				<form name="selectedForm"  method="POST">		
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
					<% double total = 0;%>
					<% 
						List<ProductVO> RedisBuylist = (List<ProductVO>) cartSVC.getAllP_idByMem_no(mem_no);
						List<ProductVO> buylist = new ArrayList<>();
					%>
					<%  if(RedisBuylist != null && (RedisBuylist.size() > 0)) {%>
					<%  for (int index = 0; index < RedisBuylist.size(); index++){
						ProductVO order = RedisBuylist.get(index);
						total += (cartSVC.getValueByP_id(mem_no, order.getP_id()))*(productSvc.getOneProduct(order.getP_id()).getP_pr());
					%>
						<tr>
							<td>
								<label class="checklabel">
									<input id="boxchecked<%= index %>" type="checkbox" name="checkact" value="<%= index %>">
									<input id="checknum<%= index %>" type="hidden" value="0">
									<input id="forPlusnum<%= index %>" type="hidden" value="<%= (cartSVC.getValueByP_id(mem_no, order.getP_id()))*(productSvc.getOneProduct(order.getP_id()).getP_pr())%>">
									<span class="checkmark"></span>
								</label>
							</td>
							<td class="imgframe"><img src="<%=request.getContextPath()%>/product/productPic.do?p_id=<%=order.getP_id()%>"></td>
							<td><%=order.getP_id()%></td>
							<td><%=productSvc.getOneProduct(order.getP_id()).getP_name()%></td>					
							<td><span>$NT </span><%=productSvc.getOneProduct(order.getP_id()).getP_pr()%></td>
							<td>
								<div class="number-input">
									<img id="btn<%= index %>" class="addqua" onclick="this.parentNode.querySelector('input[type=number]').stepUp()" src="<%=request.getContextPath()%>/front-end/shop/images/plus.png" alt="購物車">
									<input id="qty<%= index %>" class="quantity" min="0" name="qty" value="<%=cartSVC.getValueByP_id(mem_no, order.getP_id())%>" type="number">
									<img id="btn<%= index + "_1" %>" onclick="this.parentNode.querySelector('input[type=number]').stepDown()" src="<%=request.getContextPath()%>/front-end/shop/images/minus.png" alt="購物車" class="plus">
								</div>	
							</td>
							<td id="td<%= index %>"><span>$NT </span><span id="span<%= index %>"><%= (cartSVC.getValueByP_id(mem_no, order.getP_id()))*(productSvc.getOneProduct(order.getP_id()).getP_pr())%></span></td>
					        <td>		
					        	<input type="hidden" name="action"  value="deleteSelected"> 
								<input type="hidden" name="p_id"  value="<%=order.getP_id()%>"> 
								<input type="hidden" name="p_pr"  value="<%=productSvc.getOneProduct(order.getP_id()).getP_pr()%>"> 
								<input type="hidden" name="quantity" value="1" id="qty<%= index + "_1"%>">
								<input type="button" value="刪 除" class="button" 
									onclick="location.href='<%=request.getContextPath()%>/shop/ShoppingRedis.do?action=DELETE&del=<%=index%>&p_id=<%=order.getP_id()%>&quantity=<%=cartSVC.getValueByP_id(mem_no, order.getP_id())%>'" >
							</td>
						</tr>
					<% }%>
					<tr>
						<td colspan="5">
						<td>總金額<span> $NT </span></td>
						<td id="checkTotal">0</td>
						<td>
					</tr>
					</table>
					<br>
					<input type="button" value="清除勾選" class="paybtn cleanbtn" 
									onclick="go('<%=request.getContextPath()%>/shop/ShoppingRedis.do?action=deleteSelected')">
					<input type="button" value="前往結帳" class="paybtn"
									onclick="go('<%=request.getContextPath()%>/shop/ShoppingRedis.do?action=CHECKOUT')" > 
				</form>
			</div>
			<% }%>
		</div>
	</div>
	<%@ include file="/front-end/EndFooter.file" %>
	<script type="text/javascript">
	function go(data){
		document.selectedForm.action=data;
		document.selectedForm.submit();
	}
	</script>
	<script type="text/javascript">
	$(function(){
		
		
		<%  for (int index = 0; index < RedisBuylist.size(); index++){%>
			<%ProductVO order = RedisBuylist.get(index);%>
			
			var paramsAdd_<%= index %> = {
				"action":"AddQUANTITY",
				"index":<%= index %>,
				"p_id":<%="\"" + order.getP_id() + "\""%>,
				"p_name":<%="\"" + productSvc.getOneProduct(order.getP_id()).getP_name() + "\""%>,
				"p_pr":<%=productSvc.getOneProduct(order.getP_id()).getP_pr()%>,
			};
			$("#btn<%= index %>").click(function(event){
				event.stopPropagation();
				console.log(<%="\"" + order.getP_id() + "\""%>);
				$.ajax({
					data:paramsAdd_<%= index %>,
					type:"POST",
					dataType: "json",
					url:"<%=request.getContextPath()%>/shop/Changecart.do",
					success: function (data){
						console.log("增加"+data.amount);
						$("#td<%= index %>").html("<span>$NT </span>"+data.amount);	
						
						if($("#checknum<%= index %>").val()!=0){
							$("#checknum<%= index %>").val(data.amount);
						}	
						$("#forPlusnum<%= index %>").val(data.amount);
						
						var shoppingTatal = 0;
						<%  for (int a = 0; a < RedisBuylist.size(); a++){%>
							shoppingTatal += parseInt($("#checknum<%= a %>").val(),10);
						<%}%>
						$("#checkTotal").text(shoppingTatal);
					}
				});	
			});
			var paramsMin_<%= index %> = {
				"action":"MinusQUANTITY",
				"index":<%= index %>,
				"p_id":<%="\"" + order.getP_id() + "\""%>,
				"p_name":<%="\"" + productSvc.getOneProduct(order.getP_id()).getP_name() + "\""%>,
				"p_pr":<%=productSvc.getOneProduct(order.getP_id()).getP_pr()%>,
			};
			
			$("#btn<%= index + "_1" %>").click(function(event){
				
				event.stopPropagation();
				console.log(<%="\"" + order.getP_id() + "\""%>);
				$.ajax({
					data:paramsMin_<%= index %>,
					type:"POST",
					dataType: "json",
					url:"<%=request.getContextPath()%>/shop/Changecart.do",
					success: function (data){
						console.log("減少"+data.amount);
						console.log("index"+data.index);
						if(data.amount!='')
							$("#td<%= index %>").html("<span>$NT </span>"+data.amount);
							if($("#checknum<%= index %>").val()!=0){
								$("#checknum<%= index %>").val(data.amount);
							}	
							$("#forPlusnum<%= index %>").val(data.amount);
							
							var shoppingTatal = 0;
							<%  for (int a = 0; a < RedisBuylist.size(); a++){%>
								shoppingTatal += parseInt($("#checknum<%= a %>").val(),10);
							<%}%>
							$("#checkTotal").text(shoppingTatal);
							
						if(data.amount=== undefined){
							Swal.fire({
							  title: '刪除商品',
							  text: '數量為0，從購物車移除',
							  icon: 'warning',
							  showConfirmButton: false,
							  customClass: 'swal-wide',
							  timer: 1500
							}).then(function () {
						        window.location.href = "<%=request.getContextPath()%>/front-end/shop/shoppingRedisCart.jsp"
						    })
						}
					}
				});	
			});
			
			$("#boxchecked<%= index %>").change(function(event){
				
				console.log("check="+$("#boxchecked<%= index %>").prop("checked"));
				console.log("forPlusnum="+$("#forPlusnum<%= index %>").val());
				event.stopPropagation();
				$.ajax({
					data:creatCheckedJson($("#boxchecked<%= index %>").prop("checked"),$("#forPlusnum<%= index %>").val()),
					type:"POST",
					dataType: "json",
					url:"<%=request.getContextPath()%>/shop/Changecart.do",
					success: function (data){		
						console.log("Checkamount="+data.amount);
						$("#checknum<%= index %>").val(data.amount);
						
						var shoppingTatal = 0;
						<%  for (int a = 0; a < RedisBuylist.size(); a++){%>
							shoppingTatal += parseInt($("#checknum<%= a %>").val(),10);
						<%}%>
						$("#checkTotal").text(shoppingTatal);
					}
				});
			});
		<%}%>
		});
		function creatCheckedJson(boxchecked, amount){
			console.log("boxchecked:"+boxchecked+"; amount:"+amount);
			var checkedJson= {"action":"BOXCHECKED", "boxchecked":boxchecked, "amount":amount};
			return checkedJson;
		}
	</script>
</body>
</html>