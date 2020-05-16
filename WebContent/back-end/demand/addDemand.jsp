<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%@ page import="com.member.model.*"%>
<%@ page import="com.demand.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
<%
	HttpSession member = request.getSession();
	member.setAttribute("mem_no", "M0001");
	
	String mem_no=(String)session.getAttribute("mem_no");
	DemandVO demandVO = (DemandVO) request.getAttribute("demandVO");

%>
<title>新增需求商品</title>

<style>
  #table-header {
  	width: 300px;
	margin: 30px;
	text-align: center;
  }
  #homepage {
	color: blue;
	display: inline;
  }
  #allAddDemand {
	display: flex;
	justify-content: center;
	flex-direction: column;
  }
   .input-group-text {
  	width: 100px;
  }
  .custom-file-label::after {
  	content: "" !important;
  	margin: auto;
  	border-radius: 0.25rem 0.25rem 0.25rem 0.25rem;
  	border-left: none !important;
  	background-color: white !important;
  }
  .img-fluid {
  	max-height: 200px;
  	max-width: 280px;
  }
</style>

<head>
	<meta charset="UTF-8">
	<title>後台管理頁面</title>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/back-end/css/bootstrap4_3_1.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/back-end/css/BackEndPageStyle.css">
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
			<div id="allAddDemand">

				<table id="table-header">
					<tr><td>
					 	<h3>新增商品</h3>
					 	<h4 id="homepage" style="font-size: 17px;"><a href="DemandHome.jsp">回首頁</a></h4>
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
	
		<FORM METHOD="post" ACTION="demand.do" name="form1"
			enctype="multipart/form-data" id="form">
			
			<div class="input-group mb-3">
		 		<div class="input-group-prepend">
		    		<span class="input-group-text" id="inputGroup-sizing-default">會員編號:<font color=red><b>*</b></font></span>
		  		</div>
		  		<input class="form-control" type="text" placeholder="${mem_no}" readonly>
			</div>
			
			<div class="input-group mb-3">
		 		<div class="input-group-prepend">
		    		<span class="input-group-text" id="inputGroup-sizing-default">商品名稱:</span>
		  		</div>
		  		<input type="text" name="de_item_name" class="form-control" aria-label="請輸入商品名稱" aria-describedby="inputGroup-sizing-default" value="<%=(demandVO == null) ? "" : demandVO.getDe_item_name()%>">
			</div>
			
			<div class="input-group mb-3">
		 		<div class="input-group-prepend">
		    		<span class="input-group-text" id="inputGroup-sizing-default">價格:</span>
		  		</div>
		  		<input type="text" name="price" class="form-control" aria-label="請輸入價格" aria-describedby="inputGroup-sizing-default" value="<%=(demandVO == null) ? "" : demandVO.getPrice()%>">
			</div>
			
			<div class="input-group mb-3">
		 		<div class="input-group-prepend">
		    		<span class="input-group-text" id="inputGroup-sizing-default">地區:</span>
		  		</div>
		  		<input type="text" name="area" class="form-control" aria-label="請輸入地區" aria-describedby="inputGroup-sizing-default" value="<%=(demandVO == null) ? "Japan" : demandVO.getArea()%>">
			</div>
	
				<jsp:useBean id="demandSvc" scope="page"
					class="com.demand.model.DemandService" />
			
			<div class="custom-file">
				<div class="input-group mb-3">
		  			<div class="input-group-prepend">
		   					<span class="input-group-text" id="inputGroupFileAddon01" style="height: 212px;">商品圖片:</span>
		  			</div>
		 			<div class="custom-file">
		    		<input type="file" accept="image/*" name="de_photo" class="custom-file-input" id="inputGroupFile01" aria-describedby="inputGroupFileAddon01"
						onchange="document.getElementById('blah').src = window.URL.createObjectURL(this.files[0])">
						<label style="height: 212px; text-align: center; line-height: 200px" class="custom-file-label" for="inputGroupFile01"><img id="blah" src="images/暫無圖片.png" class="img-fluid"></label>
		  			</div>
				</div>
			</div>
			
			<br><br><br><br><br><br><br>
			
			<div style="width: 100%; display: flex; justify-content: center;">
				<input type="submit" value="送出修改" class="btn btn-outline-secondary">
			</div>
			
			<input type="hidden" name="de_photo1"> 
			<input type="hidden" name="action" value="add"> 
		</FORM>
	
	</div>
		</div>
	</div>
	
	<script type="text/javascript" src="<%=request.getContextPath()%>/back-end/js/jquery3_3_1.js"></script>
  	<script type="text/javascript" src="<%=request.getContextPath()%>/back-end/js/BackEndPageScript.js"></script>
  	
</body>
</html>