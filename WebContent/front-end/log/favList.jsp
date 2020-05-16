<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.member.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.logfav.model.*"%>
<%@ page import="com.log.model.*"%>
<%@ page import="java.util.*"%>



<%
	String mem_no=(String)session.getAttribute("mem_no");
	MemberService Membersvc=new MemberService();
	MemberVO memberVO=null;
	memberVO=Membersvc.getOneMember(mem_no);
	pageContext.setAttribute("memberVO", memberVO);	
%> 

<%
 Log_favService logfavSvc = new Log_favService();
 List<Log_favVO> list = logfavSvc.getMemFav(memberVO.getMem_no());
 pageContext.setAttribute("list",list); 	
%> 



  
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Let's Go Trip</title>
    <link rel="icon" href="<%=request.getContextPath()%>/front-end/img/favicon.png">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/bootstrap.min.css">
    <!-- animate CSS -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/animate.css">
    <!-- owl carousel CSS -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/owl.carousel.min.css">
    <!-- themify CSS -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/themify-icons.css">
    <!-- flaticon CSS -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/flaticon.css">
    <!-- fontawesome CSS -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/fontawesome/css/all.min.css">
    <!-- magnific CSS -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/magnific-popup.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/gijgo.min.css">
    <!-- niceselect CSS -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/nice-select.css">
    <!-- slick CSS -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/slick.css">
    <!-- style CSS -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/style.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/sweetalert2.min.css">


</head>


<body>
<%@ include file="/front-end/TopHeader.file" %>
<div>


<!--   留空隔開 -->
<div style="height:30px"></div> 
<!--   留空隔開 -->


<!-- 個人日誌管理 -->
<h2  align="center"><b>個人追蹤日誌</b></h2>
<!-- 個人日誌管理 -->

<!--   留空隔開 -->
<div style="height:30px"></div> 
<!--   留空隔開 -->

<!-- 頂部按鈕 -->
<div style="display: flex; flex-direction: row; justify-content: center;">
<button onclick="javascript:location.href='<%=request.getContextPath()%>/front-end/log/favList.jsp'" style="margin:0 20px;" type="button" class="btn btn-success col-2">我的收藏</button>
<button onclick="javascript:location.href='<%=request.getContextPath()%>/front-end/log/myList.jsp'" style="margin:0 20px" type="button" class="btn btn-danger col-2">我的日誌</button>
<button onclick="javascript:location.href='<%=request.getContextPath()%>/front-end/log/addLog.jsp'" style="margin:0 20px" type="button" class="btn btn-secondary col-2">發布日誌</button>
</div>
<!-- 頂部按鈕 -->


<!--   留空隔開 -->
<div style="height:30px"></div> 
<!--   留空隔開 -->


<div style="margin:auto 215px">
<%@ include file="page1.file" %>
</div>



<!-- 日誌列表 -->
<div style="display: flex;  justify-content: center;">
<table class="table table-hover col-9">
  <thead>
    <tr>
      <th scope="col"></th>
      <th scope="col">日誌編號</th>
      <th scope="col">日誌標題</th>
      <th scope="col">最後更新時間</th>
      <th scope="col">收藏狀態</th>
      <th scope="col"></th>
      
    </tr>
  </thead>
  <tbody>
    <c:forEach var="logfavVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
    <jsp:useBean id="logSvc" scope="page" class="com.log.model.LogService" />
    <tr>
      <th scope="row"></th>
      <td>${logfavVO.log_id}</td>
      <td><a href="#" onclick="init('${logfavVO.log_id}')"><strong>${logSvc.getOneLog(logfavVO.log_id).log_title}</strong></a></td>
      <td><fmt:formatDate value="${logSvc.getOneLog(logfavVO.log_id).l_time}" pattern="yyyy-MM-dd HH:mm"/></td>
      <c:set var="val" value="${logfavVO.fav_status}"/>
	    <td>${l_status[val]}</td>
<%--       <td><button onclick="CancelFav('${logfavVO.log_id}','${memberVO.mem_no}')" type="button" class="btn btn-danger ">取消收藏</button></td> --%>
      <td><form METHOD="post" ACTION="log_fav.do" name="form1">
      	<input type="hidden" name="log_id" value="${logfavVO.log_id}">
      	<input type="hidden" name="mem_no" value="${memberVO.mem_no}">
      	<input type="hidden" name="action" value="delete">
      	<input value="取消追蹤" type="submit" class="btn btn-danger">
      	 </form>
      </td>
    </tr>
    </c:forEach>
  </tbody>
</table>
</div>
<!-- 日誌列表 -->

<hr>

      <!--   留空隔開 -->
<div style="height:30px"></div> 
<!--   留空隔開 -->

<!-- 底部按鈕 -->
<div style="margin:auto 215px">
<%@ include file="page2.file" %>
</div>
<!-- 底部按鈕 -->





</div>

<%@ include file="/front-end/EndFooter.file" %>


<script type="text/javascript">

function init(data) {
	  $.ajax({
	   type : "GET",
	   url : "log.do",
	   data : {
	    "action" : "getOne_For_Display_Ajax","log_id" : data
	   },
	   dataType : "json",
	   success : function(logVO) {
		   Swal.fire(
					  '日誌標題 :'+logVO.log_title,
					  logVO.log_con
					)
	   },
	   error : function() {
	    alert('Ajax連線異常');
	   }
	  })
	}



function CancelFav(log_id,mem_no){
	$.ajax({
		   type : "GET",
		   url : "log_fav.do",
		   data : { "action" : "delete","log_id" : log_id ,"mem_no" : mem_no},
		   success : function() {
			   Swal.fire({
				   icon: 'warning',
				   text: '取消追蹤!',
				 })
		   },
		   error : function() {
		    alert('Ajax連線異常');
		   }
		  })
}



var cancel='${param.cancel}';
if (cancel == 'true'){
	Swal.fire({
		   icon: 'warning',
		   text: '取消追蹤!',
		 })
}





</script>


</body>

</html>