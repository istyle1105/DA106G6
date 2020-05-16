<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.log.model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.member.model.*"%>

<%
	String mem_no=(String)session.getAttribute("mem_no");
	MemberService Membersvc=new MemberService();
	MemberVO memberVO=null;
	memberVO=Membersvc.getOneMember(mem_no);
	pageContext.setAttribute("memberVO", memberVO);	
%> 



<% LogVO logVO = (LogVO) request.getAttribute("logvo");%>



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

	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
	
	<style type="text/css">
		p{
		  color:black;
		}
	</style>

</head>

<body onload="connect();" onunload="disconnect();">

<div style="display:none" id ="customerserviceid">Notice</div>
<div style="display:none" id ="memberno">${memberVO.m_name}</div>

<!-- 錯誤訊息 -->
<c:if test="${not empty errorMsgs}">
  <ul>
   <c:forEach var="message" items="${errorMsgs}">
    <c:set var="errors" value="${message}" scope="page"></c:set>
    <script >
     var message = "${errors}";
     alert(message);
    </script>
   </c:forEach>
  </ul>
 </c:if>
<!-- 錯誤訊息 -->



<%@ include file="/front-end/TopHeader.file" %>
<div>

<!--   留空隔開 -->
<div style="height:30px"></div> 
<!--   留空隔開 -->

<!-- 日誌標題 -->
<div style="display: flex; flex-direction: row; justify-content: center;">
<font style="color:#272727;font-weight:bold;font-weight:bold;font-size:30px">${logvo.log_title}</font>
</div>
<!-- 日誌標題 -->

<hr>


<!--   留空隔開 -->
<div style="height:30px"></div> 
<!--   留空隔開 -->

<!-- 日誌內容 -->
<div style="display: flex; justify-content: center;">
<font style="color:#272727;font-weight:bold;font-size:22px;width:600px;">${logvo.log_con}</font>
</div>
<!-- 日誌內容 -->

<!--   留空隔開 -->
<div style="height:30px"></div> 
<!--   留空隔開 -->

<!-- 更新時間 -->
<div style="display: flex; flex-direction: column;">
<div style="display: flex;  justify-content: center;">
	最後更新於:<fmt:formatDate value="${logvo.l_time}" pattern="yyyy-MM-dd HH:mm"/>
</div>
<div style="display: flex;  justify-content: center;">
	<font style="color:#272727;font-weight:bold;font-size:22px;">收藏數:${logvo.l_favorited}</font>
</div>
</div>
<!-- 更新時間 -->


<!-- 檢舉收藏按鈕 -->
<div style="display: flex; justify-content: center;">
<button onclick="AddFavorited('${logvo.log_id}','${memberVO.mem_no}')" style="margin:10px 10px" type="button" class="btn btn-info col-2"><font style="font-weight:bold;font-size:18px;">我要收藏</font></button>


<!-- <button style="margin:10px 10px" type="button" class="btn btn-danger col-2"><font style="font-weight:bold;font-size:18px;">我要檢舉</font></button> -->

<!-- Button trigger modal -->
<button onclick="AddReport('${memberVO.mem_no}')" style="margin:10px 10px" type="button" class="btn btn-danger col-2" >
  <font style="font-weight:bold;font-size:18px;">我要檢舉</font>
</button>

<!-- Modal -->
<div style="display:none;z-index:99999;" class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">檢舉原因:</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <form METHOD="post" ACTION="log_report.do" name="form1">
      <div class="modal-body">
        	<textarea name="l_re_reason" id="cmooent" class="form-control" id="exampleFormControlTextarea1"  rows="3"></textarea>
        	<input type="hidden" name="action"	value="insert_Log_report">
    		<input type="hidden" name="log_id"	value="${logvo.log_id}">
    		<input type="hidden" name="mem_no"	value="${memberVO.mem_no}">
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">再想想!</button>
        <input  class="btn btn-primary" type="submit" value="堅持送出!" onclick="sendMessage();">
      </div>
      </form>
    </div>
  </div>
</div>



</div>
<!-- 檢舉收藏按鈕 -->



<hr>

<!-- javabean -->
<jsp:useBean id="log_comSvc" scope="page" class="com.logcom.model.Log_comService" />
<!-- javabean -->

<!-- 留言區塊 -->
<div style="display: flex; justify-content: center;">
<div style="flex-direction: column; justify-content: center;">

<c:forEach var="log_comVO" items="${log_comSvc.getLog_com(logvo.log_id)}" >

	<div style="width:800px;border:3px solid #6C6C6C;position: relative;border-radius:10px;margin:5px">
	<jsp:useBean id="memSvc" scope="page" class="com.member.model.MemberService" />
		<div><font style="font-weight:bold;font-size:18px;">${memSvc.getOneMember(log_comVO.mem_no).m_name}</font></div>
		<div style="padding-left:150px;padding-right:150px">
			<font style="font-size:14px;">${log_comVO.log_com}</font>
		</div>
		<div style="position: absolute;bottom: 0;right: 0;">
		<font style="font-size:14px;">
			<fmt:formatDate value="${log_comVO.com_time}" pattern="yyyy-MM-dd HH:mm"/>
		</font>
		
		</div>
	</div>
</c:forEach>
</div>
</div>
<!-- 留言區塊 -->

<!-- 留言標題 -->
<div style="display: flex; justify-content: center;">
	<font style="color:#272727;font-weight:bold;font-weight:bold;font-size:24px">您的留言 :</font>
</div>
<!-- 留言標題 -->


<!-- 新增留言 -->
<div style="display: flex; flex-wrap: wrap; justify-content: center;">
	<div style="width:800px;border:3px solid #6C6C6C;position: relative;border-radius:10px;margin:5px">
		<FORM  METHOD="post" ACTION="log.do" name="form1">
		  <div class="form-group">
<!--     		 <label for="exampleFormControlTextarea1"> -->
<!--     		 	<font style="font-weight:bold;font-size:18px;">我想留言:</font> -->
<!--     		 </label> -->
    		<textarea name="log_com" id="cmooent" class="form-control" id="exampleFormControlTextarea1"  rows="3"></textarea>
    		<div style="position: absolute;bottom: 0;right: 0;">
    		<input type="hidden" name="action"	value="insert_Log_Com">
    		<input type="hidden" name="log_id"	value="${logvo.log_id}">
    		<input type="hidden" name="mem_no"	value="${memberVO.mem_no}">
    		<input style="margin:10px auto " class="btn btn-success" type="submit" value="留言!">
    		</div>
  	  	  </div>
		</FORM>
		
		
	</div>
</div>
<!-- 新增留言 -->









</div>

<%@ include file="/front-end/EndFooter.file" %>

    <!-- jquery plugins here-->
    <script src="<%=request.getContextPath()%>/front-end/js/jquery-1.12.4.min.js"></script>
    <!-- popper js -->
    <script src="<%=request.getContextPath()%>/front-end/js/popper.min.js"></script>
    <!-- bootstrap js -->
    <script src="<%=request.getContextPath()%>/front-end/js/bootstrap.min.js"></script>
    <!-- magnific js -->
    <script src="<%=request.getContextPath()%>/front-end/js/jquery.magnific-popup.js"></script>
    <!-- swiper js -->
    <script src="<%=request.getContextPath()%>/front-end/js/owl.carousel.min.js"></script>
    <!-- masonry js -->
    <script src="<%=request.getContextPath()%>/front-end/js/masonry.pkgd.js"></script>
    <!-- masonry js -->
    <script src="<%=request.getContextPath()%>/front-end/js/jquery.nice-select.min.js"></script>
    <script src="<%=request.getContextPath()%>/front-end/js/gijgo.min.js"></script>
    <!-- contact js -->
    <script src="<%=request.getContextPath()%>/front-end/js/jquery.ajaxchimp.min.js"></script>
    <script src="<%=request.getContextPath()%>/front-end/js/jquery.form.js"></script>
    <script src="<%=request.getContextPath()%>/front-end/js/jquery.validate.min.js"></script>
    <script src="<%=request.getContextPath()%>/front-end/js/mail-script.js"></script>
    <script src="<%=request.getContextPath()%>/front-end/js/contact.js"></script>
    <!-- custom js -->
    <script src="<%=request.getContextPath()%>/front-end/js/custom.js"></script>
    
    
<script type="text/javascript">

var memberno =$("#memberno").text();
var customerserviceid = $("#customerserviceid").text();
var MyPoint = "/oneonone";
var host = window.location.host;
var path = window.location.pathname;
var webCtx = path.substring(0, path.indexOf('/', 1));
var endPointURL = "ws://" + window.location.host + webCtx + MyPoint+"/"+memberno+"/"+customerserviceid;
var webSocket;

function connect() {
	// 建立 websocket 物件
	webSocket = new WebSocket(endPointURL);
	
	webSocket.onopen = function(event) {
	};

	webSocket.onmessage = function(event) {
	};

	webSocket.onclose = function(event) {
	};
}

function sendMessage() {
    var jsonObj = {"memberno" : memberno, "message" : "1"};
    webSocket.send(JSON.stringify(jsonObj));
}

function disconnect () {
	webSocket.close();
	
}






function AddReport(mem_no){
	if(mem_no == ""){
		Swal.fire({
			   icon: 'error',
			   text: '請先登入!',
			 })
	}else{
		$("#exampleModal").modal({show:true});
	}
}




var report='${param.report}';
if (report == 'true'){
	Swal.fire(
			'',  
			'檢舉成功!',
			'success'
			)
}






function AddFavorited(log_id,mem_no){
	
		if (mem_no == ""){
			Swal.fire({
				   icon: 'error',
				   text: '請先登入!',
				 })
			
		}else{
	
	$.ajax({
		   type : "GET",
		   url : "log_fav.do",
		   data : { "action" : "insert","log_id" : log_id ,"mem_no" : mem_no},
		   success : function() {
			   Swal.fire({
				   icon: 'success',
				   text: '收藏成功!',
				 })
		   },
		   error : function() {
			   Swal.fire({
				   icon: 'error',
				   text: '已經收藏過了唷!',
				 })
		   }
		  })
		}
}
    
    
    
    
</script>
    
    
    
    
    
</body>

</html>