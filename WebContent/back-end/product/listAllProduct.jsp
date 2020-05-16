<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*"%>
<%@ page import="com.product.model.*"%>
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
    ProductService productSvc = new ProductService();
    List<ProductVO> list = productSvc.getAll();
    pageContext.setAttribute("list",list);

	HashMap<String,String> p_status = new HashMap<>();
	p_status.put("0", "下架");
	p_status.put("1", "上架");
	pageContext.setAttribute("p_status", p_status);
%>

<html>
<head>
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
	<title>商品總表</title>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/back-end/css/bootstrap4_3_1.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/back-end/css/BackEndPageStyle.css">

	<style type="text/css">
		.container {
			padding-top: 60px;
			width: 1300px !important;
		}
		fieldset {
			text-align: center;
		}
		.warning {
			visibility: hidden;
			color: red;
			height:5px;
		}
		.oddrow {
			background-color: lightgray;
		}
		.homekey{
			position: fixed;
			height: 50px;
			width: auto;
			cursor: pointer;
			top:85%;
			left:90%;
			transition: all 0.5s; 
		}
		.homekey:hover{
			transform: rotateZ(720deg) scale(1.2,1.2); 
		}
		th {
			text-align:center;
			line-height:50px;
		}
		.sendBtn {
			text-align:center;
		}
		.imgd {
	    	border: none;
			background-color: rgba(0,0,0,0);
	    }
		img {
			height:60px;
			width: auto;
		}
	</style>
</head>
<body>
	<%@ include file="/back-end/backendTop.file" %>	
	<div class="container">
	<input type="button" value="新增商品" class="btn btn-outline-success" onclick="location.href='<%=request.getContextPath()%>/back-end/product/addProduct.jsp'" >
	<%@ include file="page1.file" %> 
		<table class="table">
			<thead class="thead-dark">
				<tr>
					<th scope="col">商品圖片</th>
					<th scope="col">商品編號</th>
					<th scope="col">商品名稱</th>
					<th scope="col">類別</th>
					<th scope="col">價格</th>
					<th scope="col">型號</th>
					<th scope="col">更新日期</th>
					<th scope="col">狀態</th>
					<th scope="col">修改</th>	
				</tr>
			</thead>
			<tbody> 
			<c:forEach var="productVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
				<tr>
					<th class="imgd"><img src="<%=request.getContextPath()%>/product/productPic.do?p_id=${productVO.p_id}"></th>
					<th>${productVO.p_id}</th>
					<th>${productVO.p_name}</th>
					<th>${productVO.p_cat}</th>
					<th><span>$NT </span>${productVO.p_pr}</th>
					<th>${productVO.p_spec}</th>	
					<th><fmt:formatDate value='${productVO.p_renew}' pattern='yyyy-MM-dd HH:mm'/></th>
					<th>
						<c:forEach var="status" items="${p_status}">
							${productVO.p_status==status.key? status.value : ''}
						</c:forEach>
					</th>	
					<th>
						<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/product/product.do" style="margin-bottom: 0px;">
							<input type="submit" class="btn btn-danger" value="修改">
							<input type="hidden" name="requestURL"  value="<%=request.getServletPath()%>">
							<input type="hidden" name="whichPage"  value="<%=whichPage%>">
							<input type="hidden" name="p_id"  value="${productVO.p_id}">
							<input type="hidden" name="action"	value="getOne_For_Update">
						</FORM>
					</th>																
				</tr>
			</c:forEach>
			</tbody>
		</table>
	<%@ include file="page2.file" %>
	</div>
	<%@ include file="/back-end/backendBottom.file" %>
</body>
</html>