<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.employee.model.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.complaint.model.*"%>
<%@ page import="java.util.*"%>
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
	ComplaintService complaintSvc=new ComplaintService();
	List<ComplaintVO> list = complaintSvc.getAll();
	pageContext.setAttribute("list",list);

%>
<%
	HashMap compStatus=new HashMap();
	compStatus.put("0", "未處理");
	compStatus.put("1", "已處理");
	pageContext.setAttribute("compStatus", compStatus);
%>
    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>後台申訴頁面</title>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/back-end/css/bootstrap4_3_1.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/back-end/css/BackEndPageStyle.css">
</head>
<style>
.adddiv{
position: absolute;
top: 5%;
left: 22%;
}

</style>	

<body>
	<%@ include file="/back-end/backendTop.file" %>
<div class="col-8 adddiv">
<%@ include file="page1.file" %>
<table class="table table-dark table-hover col-12 center" style="margin-top:3px;">
  <thead> 
    <tr>
      <th scope="col">申訴編號</th>
      <th scope="col">申訴日期</th>
      <th scope="col">申訴狀態</th>
      <th scope="col">處理</th>
      <th scope="col">申訴內容</th>
      
    </tr>
  </thead>
  <tbody>
	<c:forEach var="complaintVO" varStatus="counter" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">	
    
    <tr ${(complaintVO.comp_no==param.comp_noUpdata) ? 'style="color:yellow;"':''}>
      <td>${complaintVO.comp_no}</td>
      <td>
      	<fmt:formatDate value="${complaintVO.comp_date}" pattern="yyyy-MM-dd HH:mm:ss"/>
      </td>
      <td>
	      <c:forEach var="status" items="${compStatus}">
				${complaintVO.comp_status==status.key ? status.value : ''}
		  </c:forEach>
      </td>
      <td>
      	<form method="post" action="<%=request.getContextPath()%>/complaint/complaint.do">
	      	<input type="hidden" name="comp_no"  value="${complaintVO.comp_no}">
			<input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>">
			<input type="hidden" name="whichPage"	value="<%=whichPage%>"> 
			<input type="hidden" name="action"	value="compUpdateStatus">
	      	<button type="submit" class="btn ${complaintVO.comp_status==0 ? 'btn-danger' :'btn-success'}">
	      		處理完成
	      	</button>
      	</form>
      </td>
      <td>
      <a tabindex="0" class="btn btn-danger popover-dismiss" role="button" data-toggle="popover" data-trigger="focus" 
		data-content="${complaintVO.comp_content}">
		檢舉內容</a>
      </td>
      
    </tr>
  
    </c:forEach>
  </tbody>
</table>

<%@ include file="page2.file"%>	
</div>	
	<%@ include file="/back-end/backendBottom.file"%>

<script>
$('.popover-dismiss').popover({
	  trigger: 'focus'
	})
</script>  	
</body>
</html>