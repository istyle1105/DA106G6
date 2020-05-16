<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*"%>
<%@ page import="com.wallet.model.*"%>
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
	WalletService walletSvc = new WalletService();
    List<WalletVO> list = walletSvc.getAll();
    pageContext.setAttribute("list",list);

	HashMap<String,String> w_txn_status = new HashMap<>();
	w_txn_status.put("0", "儲值");
	w_txn_status.put("1", "扣款");
	pageContext.setAttribute("w_txn_status", w_txn_status);
%>

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
	</style>
</head>
<body>
	<%@ include file="/back-end/backendTop.file" %>	
	<div class="container">
	<%@ include file="page1.file" %> 
			<table class="table">
				<thead class="thead-dark">
					<tr>
						<th scope="col">交易紀錄編號</th>
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
						<th>${walletVO.w_rec_id}</th>
						<th>${walletVO.mem_no}</th>
						<th><span>$NT </span>${walletVO.w_amount}</th>
						<th><fmt:formatDate value='${walletVO.w_txn_time}' pattern='yyyy-MM-dd HH:mm:ss'/></th>
						<th>${walletVO.w_txn_note}</th>
						<th>
							<c:forEach var="status" items="${w_txn_status}">
								${walletVO.w_txn_status==status.key? status.value : ''}
							</c:forEach>			
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