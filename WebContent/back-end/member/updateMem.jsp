<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="javax.servlet.ServletContext"%>


<%
	MemberVO memberVO2= (MemberVO)request.getAttribute("memberVO2");
	pageContext.setAttribute("memberVO2", memberVO2);
%> 
<%
	//ServletContext context=getServletContext();
	//map =(HashMap)context.getAttribute("empStatus");
	HashMap memStatus=new HashMap();
	memStatus.put("0", "未驗證");
	memStatus.put("1", "已驗證");
	memStatus.put("2", "停權'");
	pageContext.setAttribute("memStatus", memStatus);
	
	HashMap leaderStatus=new HashMap();
	leaderStatus.put("0", "未驗證");
	leaderStatus.put("1", "已驗證");
	leaderStatus.put("2", "停權'");
	pageContext.setAttribute("leaderStatus", leaderStatus);
	
	HashMap purStatus=new HashMap();
	purStatus.put("0", "未驗證");
	purStatus.put("1", "已驗證");
	purStatus.put("2", "停權'");
	pageContext.setAttribute("purStatus", purStatus);
%>


<html>
<head>
<title>會員資料</title>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/back-end/css/bootstrap4_3_1.css">


</head>
<body>


<form method="post" action="<%=request.getContextPath()%>/member/member.do" name="form1" enctype="multipart/form-data">
<fieldset class="border border-top border-dark p-2 rounded">
<legend class="w-auto"><%=memberVO2.getM_name()%>的會員資料</legend>
	<div class="form-row">
		<div class="form-group col-md-6">
		<label for="inputId">會員編號</label>
		<input type="text" class="form-control" id="inputId" name="mem_id"
			value="<%=memberVO2.getMem_no()%>" readonly >
		</div>
		<div class="form-group col-md-6">
		<label for="inputId">會員帳號</label>
		<input type="text" class="form-control" id="inputId" name="mem_id"
			value="<%=memberVO2.getMem_id()%>" readonly >
		</div>
	</div>
	  	
	  	  <div class="form-group">
    		<label for="inputAddr">會員密碼</label>
    		<input type="password" class="form-control" id="inputAddr" name="psw"
    			value="<%=memberVO2.getPsw()%>">
  	 </div>			

	  <div class="form-row">
	    <div class="form-group col-md-6">
	      <label for="inputName">姓名</label>
	      <input type="text" class="form-control" id="inputName"  name="m_name"
	      	value="<%=memberVO2.getM_name()%>">
	    </div>
	    <div class="form-group col-md-6">
	    	<div style="margin-bottom: .5rem">性別</div>
	    	<div class="form-control">
	      	<label for="male">男性</label><input id="male" type="radio" name="gender" value="0" ${(memberVO2.gender==0) ?'checked' : ''}>	
			<label for="female">女性</label><input id="female" type="radio" name="gender" value="1" ${(memberVO2.gender==1) ?'checked' : ''}>
	    	</div>
	    </div>
	  </div>		
	  <div class="form-row">
	    <div class="form-group col-md-6">
	      <label for="inputphone">手機</label>
	      <input type="text" class="form-control" id="inputphone" name="cellphone"
	      	value="<%=memberVO2.getCellphone()%>">
	    </div>
	    <div class="form-group col-md-6">
	      <label for="inputEmail">Email</label>
	      <input type="email" class="form-control" id="inputEmail" name="email"
	      	value="<%=memberVO2.getEmail()%>">
	    </div>
	  </div>


	  <div class="form-group">
    		<label for="inputAddr">Address</label>
    		<input type="text" class="form-control" id="inputAddr" name="address"
    			value="<%=memberVO2.getAddress()%>">
  	 </div>
 	  <div class="form-row">
			<div class="form-group col-md-6">
				<label>會員頭像</label>	
				<div id="putImg" align="center" style="display:inline-block">
					<img id='oimg' height="150" onerror="javascript:this.src='<%=request.getContextPath()%>/NoData/no_photo.jpg'" src="data:image/png;base64,${memberVO2.m_photo}">
				</div>
	    		<input type="file" accept="image/gif, image/jpeg, image/png" 
				name="m_photo" class="form-control-file" id="exampleFormControlFile1"  
				onchange="loadFile(event)"/>
	    	</div>
	    	<div class="form-group col-md-6">
	    		<label>身分證</label>
	    		<div id="putImg2" align="center" style="display:inline-block">
					<img id='oimg2' height="150"  src="<%=request.getContextPath()%>/MemeberId_cardReader?mem_no=${memberVO2.mem_no}">
				</div>
				<input type="file" name="id_card" accept="image/gif, image/jpeg, image/png" 
				class="form-control-file" id="exampleFormControlFile1"  
				onchange="loadFile2(event)"/>
			</div>
				
	    	
	  </div>
	   	  
	<input type="hidden" name="action" value="back_update">
	<input type="hidden" name="mem_no" value="${memberVO2.mem_no}">
	<input type="hidden" name="mem_id" value="${memberVO2.mem_id}">	
	<input type="hidden" name="requestURL" value="<%=request.getAttribute("requestURL")%>">
	<input type="hidden" name="whichPage"  value="<%=request.getAttribute("whichPage")%>"> 
  	<button id="btn" type="submit" class="btn btn-primary">修改</button>
</fieldset>
</form>
	<script type="text/javascript">
	var loadFile = function(event){
		var reader = new FileReader();
		reader.onload = function(){
			var putImg = document.getElementById('putImg');//為DIV的ID，用來存放圖片的
			putImg.innerHTML = "<img id ='preview' height='150' >"; //生成一個img標籤
			document.getElementById("preview").src = reader.result;//將圖片路徑讀進src
		}
		reader.readAsDataURL(event.target.files[0]);
	}
	var loadFile2= function(event){
		var reader = new FileReader();
		reader.onload = function(){
			var putImg2 = document.getElementById('putImg2');//為DIV的ID，用來存放圖片的
			putImg2.innerHTML = "<img id ='preview2' height='150'>"; //生成一個img標籤
			document.getElementById("preview2").src = reader.result;//將圖片路徑讀進src
		}
		reader.readAsDataURL(event.target.files[0]);
	}



	</script>


<script src="<%=request.getContextPath()%>/back-end/js/jquery-1.12.4.min.js"></script>
<script src="<%=request.getContextPath()%>/back-end/js/bootstrap.min.js"></script>
</body>
</html>