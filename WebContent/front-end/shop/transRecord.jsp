<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*"%>
<%@ page import="com.wallet.model.*"%>
<%@ page import="com.member.model.*"%>
<%
	String mem_no=(String)session.getAttribute("mem_no");
	MemberService Membersvc=new MemberService();
	MemberVO memberVO=null;
	memberVO=Membersvc.getOneMember(mem_no);
	pageContext.setAttribute("memberVO", memberVO);	
%> 
<%
	WalletService walletSvc = new WalletService();
    List<WalletVO> list = walletSvc.getByMen_no(mem_no);
    Collections.reverse(list); 
    pageContext.setAttribute("list",list);
%>
<%
	HashMap<String,String> w_txn_status = new HashMap<>();
		w_txn_status.put("0", "儲值");
		w_txn_status.put("1", "扣款");
	pageContext.setAttribute("w_txn_status", w_txn_status);
%>
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
        background-image: url('<%=request.getContextPath()%>/front-end/shop/images/background5.jpg');
        background-repeat: no-repeat;
        background-size: cover;
        background-attachment: fixed; 
        background-position: center;
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

	.side1 {
		top: 8em;
        position: fixed; 
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
				</ul>
				</FORM>
			</div>
		</div>
		
		<div class="rightside">
		<%@ include file="page1_2.file" %> 
			<table class="table">
				<thead class="thead-dark">
					<tr>
						<th scope="col">會員編號</th>
						<th scope="col">金額</th>
						<th scope="col">交易時間</th>
						<th scope="col">交易備註</th>
						<th scope="col">交易狀態</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="walletVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
					<tr>
						<td>${walletVO.mem_no}</td>
						<td>${walletVO.w_amount}</td>
						<td><fmt:formatDate value='${walletVO.w_txn_time}' pattern='yyyy-MM-dd HH:mm:ss'/></td>
						<td>${walletVO.w_txn_note}</td>
						<td class="txn_status">
							<c:forEach var="status" items="${w_txn_status}">
								${walletVO.w_txn_status==status.key? status.value : ''}
							</c:forEach>
						</td>
					</tr>
					</c:forEach>
				</tbody>
			</table>
		<%@ include file="page2.file" %>
		</div>
	</div>
	<%@ include file="/front-end/EndFooter.file" %>
	
</body>
</html>