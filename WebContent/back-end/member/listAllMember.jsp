<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*"%>
<%@ page import="com.member.model.*" %>    
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
	MemberService MemberSvc=new MemberService();
	List<MemberVO> list =MemberSvc.getAll();
	pageContext.setAttribute("list",list);
%>
<%
	//ServletContext context=getServletContext();
	//map =(HashMap)context.getAttribute("empStatus");
	HashMap memStatus=new HashMap();
	memStatus.put("0", "未驗證");
	memStatus.put("1", "已驗證");
	memStatus.put("2", "停權");
	pageContext.setAttribute("memStatus", memStatus);
	
	HashMap leaderStatus=new HashMap();
	leaderStatus.put("0", "未驗證");
	leaderStatus.put("1", "已驗證");
	leaderStatus.put("2", "停權");
	pageContext.setAttribute("leaderStatus", leaderStatus);
	
	HashMap purStatus=new HashMap();
	purStatus.put("0", "未驗證");
	purStatus.put("1", "已驗證");
	purStatus.put("2", "停權");
	pageContext.setAttribute("purStatus", purStatus);
%>
<html>
<head>

	<meta charset="UTF-8">
	<title>所有會員資料</title>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/back-end/css/bootstrap4_3_1.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/back-end/css/BackEndPageStyle.css">
<style>
.adddiv{
position: absolute;
top: 5%;

}

</style>	
	
	


</head>
<body>

<%@ include file="/back-end/backendTop.file" %>


<div class="col-10 adddiv" >
<table class="table table-hover table-dark center">
 <thead>	
	<tr>
		<th scope="col">會員編號</th>
		<th scope="col">會員帳號</th>
		<th scope="col">姓名</th>
		<th scope="col">會員狀態</th>
		<th scope="col">團長狀態</th>
		<th scope="col">代購狀態</th>
		<th scope="col">註冊時間</th>
		<th scope="col">基本資料</th>
		<th scope="col">驗證資料</th>

	</tr>
 </thead>
	<%@ include file="page1_2.file" %>
	<c:forEach var="memberVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
	<tr ${(memberVO.mem_no==param.mem_noUpdata) ? 'style="color:yellow;"':''}>
		<td style="font-weight:bold;">${memberVO.mem_no}</td>
		<td>${memberVO.mem_id}</td>
		<td>${memberVO.m_name}</td>
		<td>
			<c:forEach var="status" items="${memStatus}">
				${memberVO.mem_verify==status.key ? status.value : ''}
			</c:forEach>
		</td>
		<td>
			<c:forEach var="status" items="${leaderStatus}">
				${memberVO.leader_verify==status.key ? status.value : ''}
			</c:forEach>
		</td>
		<td>
			<c:forEach var="status" items="${purStatus}">
				${memberVO.pur_verify==status.key ? status.value : ''}
			</c:forEach>
			
		</td>
		<td>
			<fmt:formatDate value="${memberVO.reg_time}" pattern="yyyy-MM-dd HH:mm:ss"/>
		</td>

<!-- 		<td> -->
<%-- 		<A href="member.do?mem_no=${memberVO.mem_no}&action=backGetOne"><button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal"> --%>
<!-- 		顯示 -->
<!-- 		</button></A> -->
<!-- 		</td> -->
		<td>
		<form method="POST" action="<%=request.getContextPath()%>/member/member.do">	
			<input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>">
			<input type="hidden" name="whichPage"	value="<%=whichPage%>"> 
			<input type="hidden" name="action"	value="back_One_Update"> 
			<input type="hidden" name="mem_no"	value="${memberVO.mem_no}"> 
			<button type="submit" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal">
			修改基本資料
			</button>
		</form>
		</td>
		<td>
		<form method="POST" action="<%=request.getContextPath()%>/member/member.do">	
			<input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>">
			<input type="hidden" name="whichPage"	value="<%=whichPage%>"> 
			<input type="hidden" name="action"	value="back_Status_Update"> 
			<input type="hidden" name="mem_no"	value="${memberVO.mem_no}"> 
			<button type="submit" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal">
			修改驗證狀態
			</button>
		</form>
		</td>
	</tr>
	</c:forEach>
</table>
<%@ include file="page2_2.file" %>
</div>
<%@ include file="/back-end/backendBottom.file"%>


<c:if test="${openModal!=null}">
<div class="col-12" class="margin:auto;">
<div class="modal fade " id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-lg modal-dialog-centered" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">會員資料</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <jsp:include page="/back-end/member/updateMem.jsp" />
      </div>
    </div>
  </div>
</div>

        <script>
    		 $("#exampleModal").modal({show: true});
        </script>
</div>
 </c:if>
<c:if test="${openModal2!=null}">
<div class="col-12" class="margin:auto;">
<div class="modal fade " id="exampleModal2" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">會員驗證資料</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <jsp:include page="/back-end/member/updateStatus.jsp" />
      </div>
    </div>
  </div>
</div>

        <script>
    		 $("#exampleModal2").modal({show: true});
        </script>
</div>
 </c:if>





</body>
</html>