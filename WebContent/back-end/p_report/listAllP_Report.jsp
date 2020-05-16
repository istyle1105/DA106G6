<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.demand.model.*"%>
<%@ page import="com.p_report.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>

<!-- --------------ADD---------------- -->
<%@ page import="com.employee.model.*"%>
<%@ page import="com.authority.model.*"%>   
<%
	String emp_no=(String)session.getAttribute("emp_no");
	EmployeeService employeeSvc=new EmployeeService();
	EmployeeVO employeeVO=employeeSvc.getOneEmp(emp_no);
	pageContext.setAttribute("employeeVO", employeeVO);	
	
%> 
<%
	HashMap<Integer, String> reportStatus = new HashMap();
	reportStatus.put(0, "未處理");
	reportStatus.put(1, "已處理");
	pageContext.setAttribute("reportStatus", reportStatus);
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
<!-- --------------ADD---------------- -->
<%
	P_ReportService p_reportSvc = new P_ReportService();
    List<P_ReportVO> list = p_reportSvc.getAll();
    pageContext.setAttribute("list",list);
%>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/style.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/sweetalert2.min.css">
<title>所有需求資料</title>

<style>
 #table-headerAll {
	margin: 30px;
	text-align: center;
  }
  #allReport {
	display: flex;
	justify-content: center;
	flex-direction: column;
  }
  .table td {
  	vertical-align: middle !important;
  }
</style>

<jsp:useBean id="demandVO"  scope="page" class="com.demand.model.DemandJNDIDAO" />

<head>
	<meta charset="UTF-8">
	<title>後台管理頁面</title>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/back-end/css/bootstrap4_3_1.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/back-end/css/BackEndPageStyle.css">
</head>
<body>

<%@ include file="/back-end/backendTop.file" %>

		<div style="display: flex; justify-content: center;align-items: flex-start;">
			<div id="allReport">
				<table id="table-headerAll">
					<tr><td>
		 				<h3>檢舉管理</h3>
					</td></tr>
				</table>
			
			<%-- 錯誤表列 --%>
				<c:if test="${not empty errorMsgs}">
					<ul>
						<c:forEach var="message" items="${errorMsgs}">
							<c:set var="errors" value="${message}" scope="page"></c:set>
							<script>
								var message = "${errors}";
								alert(message);
							</script>
						</c:forEach>
					</ul>
				</c:if>
			
			<table class="table table-hover table-dark">
				<tr>
					<th scope="col" style="text-align: center;">檢舉編號</th>
					<th scope="col" style="text-align: center;">需求編號</th>
					<th scope="col" style="text-align: center;">會員編號</th>
					<th scope="col" style="text-align: center;">檢舉原因</th>
					<th scope="col" style="text-align: center;">檢舉時間</th>
					<th scope="col" style="text-align: center;">檢舉狀態</th>
					<th scope="col" style="text-align: center;">處理進度</th>
				</tr>
				<%@ include file="page1.file" %> 
				<c:forEach var="p_reportVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
				
					<tr>
						<td>${p_reportVO.p_re_no}</td>
						<td><A href="<%=request.getContextPath()%>/back-end/p_report/p_report.do?de_no=${p_reportVO.de_no}&action=listOneInFrontOnly">${p_reportVO.de_no}</a></td>
						<td>${p_reportVO.mem_no}</td>
						<td>${p_reportVO.p_re_reason}</td>
						<td><fmt:formatDate value="${p_reportVO.p_re_time}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
						<td>
							<c:forEach var="p_re_status" items="${reportStatus}">
								${p_reportVO.p_re_status==p_re_status.key ? p_re_status.value : ''}
							</c:forEach>
						</td>
						<td>
						  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/p_report/p_report.do" style="margin-bottom: 0px;">
						     <input type="submit" value="處理完成" class="btn btn-light">
						     <input type="hidden" name="p_re_no"  value="${p_reportVO.p_re_no}">
						     <input type="hidden" name="action" value="delete"></FORM>
						</td>
					</tr>
				</c:forEach>
			</table>
			</div>
		</div>
<%@ include file="/back-end/backendBottom.file"%>	
<script src="<%=request.getContextPath()%>/front-end/js/custom.js"></script>
<script src="<%=request.getContextPath()%>/front-end/js/sweetalert2.min.js"></script>

<script>
		var state='${param.state}'
		if (state=='deleteSuccess') {
			Swal.fire({
				title: '下架成功',
				icon: 'success' ,
				showConfirmButton: false,
				showCloseButton: true,
				timer: 1500
			})
		}
</script>  	
</body>
</html>