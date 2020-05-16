<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<% ProductVO productVO = (ProductVO) request.getAttribute("productVO"); %>

<html>
<head>
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
	<title>修改商品 - updateProduct.jsp</title>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/back-end/css/bootstrap4_3_1.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/back-end/css/BackEndPageStyle.css">

	<style type="text/css">
		.container {
			padding-top: 60px;
			width: 900px !important;
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
		th {
			text-align:center;
			line-height:50px;
		}
		.sendBtn {
			text-align:center;
		}
		#putImg img {
			height:150px;
			width: auto;
		}
	</style>
</head>
<body>
	<%@ include file="/back-end/backendTop.file" %>	
	<div class="container">
		<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/product/product.do" name="form1" enctype="multipart/form-data">
			<div id="putImg" align="center">
				<img id="blah" alt="商品圖片" src="<%=request.getContextPath()%>/product/productPic.do?p_id=${productVO.p_id}">
			</div>

			<div class="addtable">
				<table class="table">
					<tr style="background-color:orange" >
					</tr>

					<tr class="oddrow">
						<th scope="row">商品圖片</th>
						<td>
							<input type="file" accept="image/gif, image/jpeg, image/png" 
							name="p_pic" class="form-control-file" id="exampleFormControlFile1"  
							onchange="loadFile(event)"/>
						</td>
					</tr>
					<tr>
						<th scope="row">商品編號</th>
						<td>
							<%=productVO.getP_id()%>
							<input type="hidden" name="p_id" class="form-control" value="<%=productVO.getP_id()%>" readonly/>
						</td>
					</tr>
					<tr class="oddrow">
						<th scope="row">商品名稱</th>
						<td>
							<input type="text" name="p_name" class="form-control" value="<%=productVO.getP_name()%>" />
						</td>
					</tr>
					<tr>
						<th scope="row" >類別</th>
						<td>
							<select name="p_cat" class="form-control" id="exampleFormControlSelect1">
								<option value="旅行配件"  ${ (productVO.p_cat=="旅行配件") ? 'selected': ''}>旅行配件
								<option value="3C配件"  ${ (productVO.p_cat=="3C配件") ? 'selected': ''}>3C配件
								<option value="書籍"  ${ (productVO.p_cat=="書籍") ? 'selected': ''}>書籍  
								<option value="行李箱"  ${ (productVO.p_cat=="行李箱") ? 'selected': ''}>行李箱
								<option value="旅行袋"  ${ (productVO.p_cat=="旅行袋") ? 'selected': ''}>旅行袋  
								<option value="背包"  ${ (productVO.p_cat=="背包") ? 'selected': ''}>背包
							</select>
						</td>
					</tr>
					<tr class="oddrow">
						<th scope="row">價格</th>
						<td>
							<input type="text" name="p_pr" class="form-control" value="<%=productVO.getP_pr()%>" />
						</td>
					</tr>
					<tr>
						<th scope="row">型號</th>
						<td>
							<input type="text" name="p_spec" class="form-control" value="<%=productVO.getP_spec()%>" />
						</td>
					</tr>
					<tr class="oddrow">
						<th scope="row">狀態</th>
						<td>
							<select name="p_status" class="form-control" id="exampleFormControlSelect1">
								<option value="0" ${ (productVO.p_status==0) ? 'selected': ''}>下架
								<option value="1" ${ (productVO.p_status==1) ? 'selected': ''}>上架
							</select>
						</td>
					</tr>
					<tr>
						<th scope="row">詳細內容</th>
						<td>
							<textarea name="p_detail" class="form-control" id="exampleFormControlTextarea1" rows="3" cols="80">${productVO.p_detail}</textarea>
						</td>
					</tr>
				</table>					
			</div>	
			<br>
			<input type="hidden" name="action" value="update">
			<input type="hidden" name="requestURL"  value="<%=request.getAttribute("requestURL")%>">
			<input type="hidden" name="whichPage" value="<%=request.getAttribute("whichPage")%>">
			<button type="submit" class="btn btn-secondary btn-lg btn-info">送出修改</button>
		</FORM>
	</div>	
	<script src="<%=request.getContextPath()%>/ckeditor/ckeditor.js"></script>
	<script>
		CKEDITOR.replace( 'p_detail', {});

		var loadFile = function(event){
			var reader = new FileReader();
			reader.onload = function(){
				var putImg = document.getElementById('putImg');//為DIV的ID，用來存放圖片的
				putImg.innerHTML = "<img id ='preview'>"; //生成一個img標籤
				document.getElementById("preview").src = reader.result;//將圖片路徑讀進src
			}
			reader.readAsDataURL(event.target.files[0]);
		}
	</script>
	<%@ include file="/back-end/backendBottom.file" %>
</body>
</html>