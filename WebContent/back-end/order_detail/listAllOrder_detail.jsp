<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.order_detail.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>
<%@ page import="com.employee.model.*"%>
<%@ page import="com.authority.model.*"%>   
<%
	String emp_no=(String)session.getAttribute("emp_no");
	EmployeeService employeeSvc=new EmployeeService();
	EmployeeVO employeeVO=employeeSvc.getOneEmp(emp_no);
	pageContext.setAttribute("employeeVO", employeeVO);	
	
%> 
<%
	AuthorityService authSvc= new AuthorityService();
	List<AuthorityVO> listEmp=authSvc.getOneEmp(emp_no);
	List<String> listtmp=new ArrayList();
	for(AuthorityVO authorityVO : listEmp){
		String fun_no=authorityVO.getFun_no();
		listtmp.add(fun_no);
	}
%>


<%
    Order_detailService order_detailSvc = new Order_detailService();
    List<Order_detailVO> list = order_detailSvc.getAll();
    pageContext.setAttribute("list",list);
%>
<jsp:useBean id="productSvc" scope="page" class="com.product.model.ProductService" />

<html>
<head>
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
	<title>訂單明細總表</title>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/back-end/css/bootstrap4_3_1.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/back-end/css/BackEndPageStyle.css">

	<style type="text/css">
		.container {
			padding-top: 60px;
			width: 1100px !important;
		}
		.warning {
			visibility: hidden;
			color: red;
			height:5px;
		}
		.oddrow {
			background-color: lightgray;
		}
		th {
			text-align:center;
			line-height:50px;
		}
		#putImg img {
			height:150px;
			width: auto;
		}

	</style>
</head>
<body>
	<%@ include file="/back-end/backendTop.file" %>	
	<div class="container">
	<%@ include file="page1.file" %> 
			<table class="table">
				<thead class="thead-dark">
					<tr>
						<th scope="col">訂單編號</th>
						<th scope="col">商品編號</th>
						<th scope="col">商品名稱</th>
						<th scope="col">數量</th>
						<th scope="col">價格</th>
					</tr>
				</thead>
				<tbody> 
				<c:forEach var="order_detailVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
					<tr>
						<th scope="row">${order_detailVO.p_order_id}</th>
						<th>${order_detailVO.p_id}</th>
						<th>${productSvc.getOneProduct(order_detailVO.p_id).p_name}</th>
						<th>${order_detailVO.od_qty}</th>
						<th><span>$NT </span>${order_detailVO.od_pr}</th>			
					</tr>
					</c:forEach>
				</tbody>
		</table>
	<%@ include file="page2.file" %>
	</div>
	<%@ include file="/back-end/backendBottom.file" %>
</body>
</html>