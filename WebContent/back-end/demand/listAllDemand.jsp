<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.demand.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
    DemandService demandSvc = new DemandService();
    List<DemandVO> list = demandSvc.getAll();
    pageContext.setAttribute("list",list);
%>
<title>所有需求資料</title>

<style>
 #table-headerAll {
	margin: 30px;
	text-align: center;
  }
  #allDemand {
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

<!-- 		<div class="backendContent" style="display: flex; justify-content: center;align-items: flex-start;"> -->
			<div style="display: flex; justify-content: center;align-items: flex-start;">
			<div id="allDemand">
				<table id="table-headerAll">
					<tr><td>
		 				<h3>代購管理</h3>
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
			
			<ul style="margin: auto;">
		
				<%-- <jsp:useBean id="demandSvc" scope="page"
					class="com.demand.model.DemandService" /> --%>
		
				<li>
					<FORM METHOD="post" ACTION="demand.do" style="display: flex; flex-direction: row;">
						<h5 style="line-height: 38px; margin-right: 10px;"><b>請選擇需求編號:</b></h5>
						<select size="1" name="de_no" class="custom-select" style="width: 120px; padding-right: 20px">
							<c:forEach var="demandVO" items="${list}">
								<option value="${demandVO.de_no}">${demandVO.de_no}
							</c:forEach>
						</select>
						<input type="hidden" name="action" value="listOneInHome">
						<input type="submit" value="送出" class="btn btn-light" style="height: 38px; margin-left: 10px;">
					</FORM>
				</li>
			</ul>
			
			<table class="table table-hover table-dark">
				<tr>
					<th scope="col" style="text-align: center;">需求編號</th>
					<th scope="col" style="text-align: center;">需求商品名稱</th>
					<th scope="col" style="text-align: center;">需求商品照片</th>
					<th scope="col" style="text-align: center;">價格</th>
					<th scope="col" style="text-align: center;">地區</th>
					<th scope="col" style="text-align: center;">需求時間</th>
					<th scope="col" style="text-align: center;">修改</th>
					<th scope="col" style="text-align: center;">刪除</th>
				</tr>
				<%@ include file="page1.file" %> 
				<c:forEach var="demandVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
				
					<tr>
						<td><A href="demand.do?de_no=${demandVO.de_no}&action=listOne">${demandVO.de_no}</a></td>
						<td style="font-size: 12px">${demandVO.de_item_name}</td>
						<td style="text-align: center;"><img src = "data:image/png;base64,${demandVO.de_photo}" height="100"></td>
						<td>${demandVO.price}</td>
						<td>${demandVO.area}</td> 
						<td><fmt:formatDate value="${demandVO.de_time}" pattern="yyyy-MM-dd HH:mm:ss"/></td>	
						
						<td>
						  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/demand/demand.do" style="margin-bottom: 0px;">
						     <input type="submit" value="修改" class="btn btn-light">
						     <input type="hidden" name="de_no"  value="${demandVO.de_no}">
						     <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
						     <input type="hidden" name="whichPage"	value="<%=whichPage%>">               <!--送出當前是第幾頁給Controller-->
						     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
						</td>
						<td>
						  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/demand/demand.do" style="margin-bottom: 0px;">
						     <input type="submit" value="刪除" class="btn btn-light">
						     <input type="hidden" name="de_no"  value="${demandVO.de_no}">
						     <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
						     <input type="hidden" name="whichPage"	value="<%=whichPage%>">               <!--送出當前是第幾頁給Controller-->
						     <input type="hidden" name="action" value="delete"></FORM>
						</td>
					</tr>
				</c:forEach>
			</table>
			
			<%@ include file="page2.file" %>
			
						<c:if test="${openModal!=null}">
							<div class="modal fade" id="basicModal" tabindex="-1" role="dialog" aria-labelledby="basicModal" aria-hidden="true">
								<div class="modal-dialog modal-lg">
									<div class="modal-content">
											<h3 style="margin: 20px auto 0px auto;">需求明細</h3>	
										<div class="modal-header">
								        	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
								        	<h3 class="modal-title" id="myModalLabel"></h3>
								        </div>
											
										<div class="modal-body" style="height: 650px; display: flex; justify-content:center;">
						<!-- =========================================以下為原listOneEmp.jsp的內容========================================== -->
								        <jsp:include page="listOneDemand.jsp" />
						<!-- =========================================以上為原listOneEmp.jsp的內容========================================== -->
										</div>
											
										<div class="modal-footer"></div>
										
									</div>
								</div>
							</div>
								
								 <script>
								 	$("#basicModal").modal({show: true});
								 </script>
						</c:if>
			</div>
			</div>
<!-- 		</div> -->
<!-- 	</div> -->
<%@ include file="/back-end/backendBottom.file"%>	
<%-- 	<script type="text/javascript" src="<%=request.getContextPath()%>/back-end/js/jquery3_3_1.js"></script> --%>
<%--   	<script type="text/javascript" src="<%=request.getContextPath()%>/back-end/js/BackEndPageScript.js"></script> --%>
  	
</body>
</html>