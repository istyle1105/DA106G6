<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*"%>
<%@ page import="com.order_master.model.*"%>
<%@ page import="com.util.*"%>
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
	List<String> listtmp=new ArrayList<>();
	for(AuthorityVO authorityVO : listEmp){
		String fun_no=authorityVO.getFun_no();
		listtmp.add(fun_no);
	}
%>
<%
    Order_masterService order_masterSvc = new Order_masterService();
    List<Order_masterVO> list = order_masterSvc.getAll();
    pageContext.setAttribute("list",list);
%>

<html>
<head>
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
	<title>訂單主檔總表</title>
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
		.pagebutton {
		    background-color: #2c3e50;
		    border: 1px solid #2c3e50;
		    border-radius: 4px;
		    color: #fff;
		    float: left;
		    font-size: 14px;
		    line-height: 1.42857;
		    margin-right: 5px;
		    padding: 5px 10px;
		    position: relative;
		    text-decoration: none;
		}
		.oneAuth{
			border-bottom:2px dashed orange;
			display:none;
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
					<th scope="col">會員編號</th>
					<th scope="col">下單時間</th>
					<th scope="col">狀態</th>
					<th scope="col">出貨時間</th>
					<th scope="col">總額</th>
					<th scope="col">動作1</th>
					<th scope="col">動作2</th>
				</tr>
			</thead>
			<tbody> 
			<c:forEach var="order_masterVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
				<tr id="firstrow">
					<th scope="row">${order_masterVO.p_order_id}</th>
					<th>${order_masterVO.mem_no}</th>
					<th>
						<fmt:formatDate value='${order_masterVO.om_time}' pattern='yyyy-MM-dd HH:mm:ss'/>
					</th>
					<th>
						<c:forEach var="status" items="${om_status}">
							${order_masterVO.om_status==status.key? status.value : ''}
						</c:forEach>
					</th>
					<th>
						<fmt:formatDate value='${order_masterVO.om_dlvr}' pattern='yyyy-MM-dd HH:mm'/>
					</th>
					<th>
						<span>$NT </span>${order_masterVO.om_tpr}
					</th>
					<th>
						<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/order_master/order_master.do" style="margin-bottom: 0px;">
							<input type="submit" class="btn btn-danger" value="修改">
							<input type="hidden" name="requestURL"  value="<%=request.getServletPath()%>">
							<input type="hidden" name="whichPage"  value="<%=whichPage%>">
							<input type="hidden" name="p_order_id"  value="${order_masterVO.p_order_id}">
							<input type="hidden" name="action"	value="getOne_For_Update">
						</FORM>
					</th>
					<th>
						<button class="send btn btn-info">查詢明細</button>
					</th>																	
				</tr>
				<jsp:useBean id="Order_detailSvc" scope="page" class="com.order_detail.model.Order_detailService" />
				<jsp:useBean id="productSvc" scope="page" class="com.product.model.ProductService" />
				<tr class="oneAuth" style="background-color:orange;">	
						<th colspan="1">商品編號</th>
						<th colspan="4">商品名稱</th>
						<th colspan="1">數量</th>
						<th colspan="2">價格</th>
				</tr>	
				<c:forEach var="order_detailVO" items="${Order_detailSvc.getOrder_detailByP_order_id(order_masterVO.p_order_id)}" > 
					<tr class="oneAuth">	
						<th colspan="1">${order_detailVO.p_id}</th>
						<th colspan="4">${productSvc.getOneProduct(order_detailVO.p_id).p_name}</th>
						<th colspan="1">${order_detailVO.od_qty}</th>
						<th colspan="2"><span>$NT </span>${order_detailVO.od_pr}</th>	
					</tr>
				</c:forEach>
			</c:forEach>
			</tbody>
		</table>
	<%@ include file="page2.file" %>
	</div>
	<%@ include file="/back-end/backendBottom.file" %>
	<script>
	$(".send").bind('click', function (event) { //event:事件物件
		$(this).parent().parent().nextUntil("#firstrow").toggle();
		event.stopPropagation(); //停止事件冒泡
	});
</script>
</body>
</html>