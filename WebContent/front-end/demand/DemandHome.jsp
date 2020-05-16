<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%@ page import="com.member.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
<%
	String mem_no=(String)session.getAttribute("mem_no");
%>
<head>
	<meta charset="UTF-8">
	<title>後台管理頁面</title>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/back-end/css/bootstrap4_3_1.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/back-end/css/BackEndPageStyle.css">
	<title>代購管理</title>

	<style>
		table#table-headerHome {
			margin: 30px;
			text-align: center;
			margin-bottom: 50px;
		}
		#allHome {
			width: 400px;
			display: flex;
			justify-content: center;
			flex-direction: column;
		}
	</style>
</head>
<body>
	<div class="leftside">
		<div class="page-wrapper chiller-theme toggled">
			<nav class="sidebar-wrapper">
				<div>
					<div class="sidebar-header">
						<div class="user-info">
							<span class="user-name">
								<strong>Let's 購 Trip</strong>
							</span>
							<span class="user-role">這裡填入網站介紹</span>
						</div>
					</div>
					<div class="sidebar-menu">
						<ul>
							<li class="header-menu">
								<span>一般</span>
							</li>
							<li class="sidebar-dropdown">
								<a href="#">
									<i class="fa fa-before"></i><span>首頁</span>
								</a>
								<div class="sidebar-submenu">
									<ul>
										<li>
											<a href="#">最新消息管理</a>
										</li>
										<li>
											<a href="#">廣告管理</a>
										</li>
									</ul>
								</div>
							</li>
							<li class="sidebar-dropdown">
								<a href="#">
									<i class="fa fa-before"></i><span>會員管理</span>
								</a>
								<div class="sidebar-submenu">
									<ul>
										<li>
											<a href="#">會員帳號管理</a>
										</li>
										<li>
											<a href="#">系統信件管理</a>
									</li>
									</ul>
								</div>
							</li>
							<li class="sidebar-dropdown">
								<a href="#">
									<i class="fa fa-before"></i><span>揪團管理</span>
								</a>
								<div class="sidebar-submenu">
									<ul>
										<li>
											<a href="#">檢舉揪團管理</a>
										</li>
										<li>
											<a href="#">景點維護管理</a>
										</li>
									</ul>
								</div>
							</li>
							<li class="sidebar-dropdown">
								<a href="#">
									<i class="fa fa-before"></i><span>代購管理</span>
								</a>
								<div class="sidebar-submenu">
									<ul>
										<li>
											<a href="#">代購檢舉管理</a>
										</li>
										<li>
											<a href="#">收支管理</a>
										</li>
										<li>
											<a href="#">代購糾紛審核</a>
										</li>
									</ul>
								</div>
							</li>
							<li class="sidebar-dropdown">
								<a href="#">
									<i class="fa fa-before"></i><span>商城管理</span>
								</a>
								<div class="sidebar-submenu">
									<ul>
										<li>
											<a href="#">商品管理</a>
										</li>
										<li>
											<a href="#">訂單管理</a>
										</li>
									</ul>
								</div>
							</li>
							<li class="sidebar-dropdown">
								<a href="#">
									<i class="fa fa-before"></i><span>日誌管理</span>
								</a>
								<div class="sidebar-submenu">
									<ul>
										<li>
											<a href="#">日誌檢舉管理</a>
										</li>
									</ul>
								</div>
							</li>
							<li class="header-menu">
								<span>內部</span>
							</li>
							<li class="sidebar-dropdown">
								<a href="#">
									<i class="fa fa-emp"></i><span>員工管理</span>
									<span class="badge badge-pill badge-primary">3</span>
								</a>
								<div class="sidebar-submenu">
									<ul>
										<li>
											<a href="#">權限設定</a>
										</li>
										<li>
											<a href="#">員工管理</a>
										</li>
										<li>
											<a href="#">線上客服管理</a>
										</li>
									</ul>
								</div>
							</li>
							<li class="header-menu">
								<span>個人</span>
							</li>
							<li class="sidebar-dropdown">
								<a href="#">
									<span>帳號管理</span>
								</a>
								<div class="sidebar-submenu">
									<ul>
										<li>
											<a href="#">我的資料</a>
										</li>
										<li>
											<a href="#">登出</a>
										</li>
									</ul>
								</div>
							</li>
						</ul>
					</div>
				</div>
			</nav>
		</div>
	</div>
	<div class="rightside">
	
		<div class="backendContent" style="display: flex; justify-content: center;align-items: flex-start;">
		
		<div id="allHome">
			<table id="table-headerHome">
				<tr><td><h3>代購管理</h3></td></tr>
			</table>

			<%-- 錯誤表列 --%>
			<c:if test="${not empty errorMsgs}">
				<font style="color: red">請修正以下錯誤:</font>
				<ul>
					<c:forEach var="message" items="${errorMsgs}">
						<li style="color: red">${message}</li>
					</c:forEach>
				</ul>
			</c:if>
		
			<ul>
				<li><h5><a href='addDemand.jsp'>新增代購需求</a></h5><br><br>
				</li>
			
				<li><h5><a href='listAllDemand.jsp'>查詢/修改/刪除</a>所有代購需求</h5><br><br>
				</li>
		
			</ul>
			
			<ul>
		
				<jsp:useBean id="demandSvc" scope="page"
					class="com.demand.model.DemandService" />
		
				<li>
					<FORM METHOD="post" ACTION="demand.do" style="display: flex; flex-direction: row;">
						<h5 style="line-height: 38px; margin-right: 10px;"><b>請選擇需求編號:</b></h5>
						<select size="1" name="de_no" class="custom-select" style="width: 120px; padding-right: 20px">
							<c:forEach var="demandVO" items="${demandSvc.all}">
								<option value="${demandVO.de_no}">${demandVO.de_no}
							</c:forEach>
						</select>
						<input type="hidden" name="action" value="listOneInHome">
						<input type="submit" value="送出" class="btn btn-light" style="height: 38px; margin-left: 10px;">
					</FORM>
				</li>
			</ul>
		</div>
		</div>
	</div>
	
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
	
	<script type="text/javascript" src="<%=request.getContextPath()%>/back-end/js/jquery3_3_1.js"></script>
  	<script type="text/javascript" src="<%=request.getContextPath()%>/back-end/js/BackEndPageScript.js"></script>
  	
</body>
</html>