<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.demand.model.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.p_message.model.*"%>
<%@ page import="com.p_report.model.*"%>
<%@ page import="com.employee.model.*"%>
<%@ page import="com.authority.model.*"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>

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
	HashMap<Integer, String> Status = new HashMap();
	Status.put(0, "上架中");
	Status.put(1, "已下架");
	Status.put(2, "已下標");
	Status.put(3, "已購買");
	Status.put(4, "運送中");
	Status.put(5, "已送達");
	Status.put(6, "交易完成");
	pageContext.setAttribute("Status", Status);
%>

<%
  DemandVO demandVO = (DemandVO) request.getAttribute("demandVO"); //DemandServlet.java(Concroller), 存入req的demandVO物件
%>

<head>
	<meta charset="UTF-8">
	<title>後台管理頁面</title>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/back-end/css/bootstrap4_3_1.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/back-end/css/BackEndPageStyle.css">

	<style>
	 .table-headerOneDemand {
		width: 550px;
		margin: 30px;
		text-align: center;
	  }
	  .table-bordered {
	  	text-align: center;
	  }
	  #oneDemand {
		display: flex;
		justify-content: center;
		flex-direction: column;
	  }
	</style>

</head>

<body>

<%@ include file="/back-end/backendTop.file" %>

<div style="border:3px solid black; height:1200px; display: flex; justify-content: center; align-items: flex-start;">
	<div id="OneDemand">
	<table class="table-headerOneDemand">
		<tr><td>
			<h3>需求明細</h3>
		</td></tr>
	</table>
	
	<table class="table table-bordered">
	
		<tbody>
	   		<tr>
	      		<th scope="row">需求編號</th>
	      		<td><%=demandVO.getDe_no()%></td>
	    	</tr>
	    	<tr>
	      		<th scope="row">需求商品名稱</th>
	      		<td><%=demandVO.getDe_item_name()%></td>
	    	</tr>
	    	<tr>
	      		<th scope="row" style="line-height: 100px;">需求商品照片</th>
	      		<td><img src = "data:image/png;base64,${demandVO.de_photo}" height="100"></td>
	    	</tr>
	    	<tr>
	      		<th scope="row">價格</th>
	      		<td><fmt:formatNumber value = "${demandVO.price}" type = "currency"/></td>
	    	</tr>
	    	<tr>
	      		<th scope="row">需求者編號</th>
	      		<td>${demandVO.de_mem_no}</td>
	    	</tr>
	    	<tr>
	      		<th scope="row">地區</th>
	      		<td><%=demandVO.getArea()%></td>
	    	</tr>
	    	<tr>
	      		<th scope="row">需求時間</th>
	      		<td><fmt:formatDate value="${demandVO.de_time}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	    	</tr>
	    	<tr>
	      		<th scope="row">需求狀態</th>
	      		<td>
					<c:forEach var="status" items="${Status}">
						${demandVO.status==status.key ? status.value : ''}
					</c:forEach>
				</td>
	    	</tr>
	    	<tr id="delete">
	      		<th scope="row" style="line-height: 38px;">需求管理</th>
	      		<td style="padding-bottom: 0px;">
	      			<div class="btn-group" role="group" aria-label="Basic example" style="display: flex; justify-content: space-around; height:100%">
						<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/p_report/p_report.do">
							     <input type="submit" value="下架需求" class="btn btn-secondary">
							     <input type="hidden" name="de_no"  value="${demandVO.de_no}">
							     <input type="hidden" name="status"  value="${demandVO.status}">
							     <input type="hidden" name="de_mem_no"  value="${demandVO.de_mem_no}">
							     <input type="hidden" name="price"  value="${demandVO.price}">
							     <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
							     <input type="hidden" name="action" value="deleteInBack">
						</FORM>
			    	</div>
				</td>
			</tr>
	  	</tbody>
	</table>
	
	</div>
</div>

<%@ include file="/back-end/backendBottom.file"%>

<script>
		
		var status='${demandVO.status}'
		if (status == '1') {
			$("#delete").toggle();
		}
</script>
</body>

</html>