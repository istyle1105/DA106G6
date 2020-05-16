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

<%LogService logSVC = new LogService() ; 
  List<LogVO> listFav = logSVC.getSixLogFav(); 
  pageContext.setAttribute("listFav",listFav);  
 %> 

 <%LogService logSVCDESC = new LogService() ; 
 List<LogVO> listDesc = logSVC.getSixLogDesc(); 
 pageContext.setAttribute("listDesc",listDesc);  
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
<style>
#pic6 {
	height:400px;
	background-color:#F0F0F0 !important;
}
#pic6 img:hover{-webkit-filter:brightness(.5)}
#btn {
	background-color:#D0D0D0;
	border-color:#D0D0D0;
	color:black;
}
#btn :hover{-webkit-filter:brightness(.5)}
#pic{
height:250px;
}
</style>

<body>
<%@ include file="/front-end/TopHeader.file" %>
<div>


<div style="width:100%;text-align:center">
  <%-- ���~��C --%>
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
</div>





<div id="carouselExampleSlidesOnly" class="carousel slide" data-ride="carousel" >
  <div class="carousel-inner">
    <div class="carousel-item active">
  <a href="log.do?action=getLog_Title&log_title=�_���D">
        <img style="height:600px;" src="<%=request.getContextPath()%>/front-end/log/img/bb.jpg" class="d-block w-100" alt="...">
  </a>
    	<div class="carousel-caption d-none d-md-block">
    	  <p class="h1"><font color="white"><strong>�_���D</strong></font></p>
  		</div>
    </div>
    <div class="carousel-item">
  <a href="log.do?action=getLog_Title&log_title=�j��">
        <img style="height:600px;" src="<%=request.getContextPath()%>/front-end/log/img/dd.jpg" class="d-block w-100" alt="...">
    </a>
    	<div class="carousel-caption d-none d-md-block">
    	  <p class="h1"><font color="white"><strong>�ʳ�-�j��</strong></font></p>
  		</div>
    </div>
    <div class="carousel-item">
  <a href="log.do?action=getLog_Title&log_title=�F��">
        <img style="height:600px;" src="<%=request.getContextPath()%>/front-end/log/img/tokyo.jpg" class="d-block w-100" alt="...">
  </a>
    	<div class="carousel-caption d-none d-md-block">
    	  <p class="h1"><font color="white"><strong>�F�ʳ�</strong></font></p>
  		</div>
    </div>
  </div>
 </div> 

<!--   �d�Źj�} -->
<div style="height:30px"></div> 
<!--   �d�Źj�} -->


<!-- <!-- �������s --> 
<!-- <div style="display: flex; flex-direction: row; justify-content: center;"> -->
<!-- <button style="margin:50px ;" type="button" class="btn btn-outline-dark col-2"><b>�ڪ��峹</b></button> -->
<!-- <button style="margin:50px ;" type="button" class="btn btn-outline-dark col-2"><b>�峹�o�G</b></button> -->
<!-- <button style="margin:50px ;" type="button" class="btn btn-outline-dark col-2"><b>�ڪ�����</b></button> -->
<!-- </div> -->
<!-- <!-- �������s --> 





<!-- ���D�j�� -->
<div style="display: flex; flex-direction: row; justify-content: center;">
	<div style="width:400px;text-align:center;">
	<form style="justify-content: center;"  class="form-inline" METHOD="post" ACTION="log.do" >
	  <div class="form-group mx-sm-3 mb-2">
	    <input type="text" class="form-control" id="inputPassword2"  name="log_title" placeholder="�H���D�j��">
	    <input type="hidden" name="action" value="getLog_Title">
	  </div>
	  <button  type="submit" class="btn btn-dark btn-lg">Send</button>
	</form>
	</div>
</div>
<!-- ���D�j�� -->

<!--   �d�Źj�} -->
<div style="height:30px"></div> 
<!--   �d�Źj�} -->

<!-- ������x -->
<h2  align="center"><b>������x</b></h2>
<!-- ������x -->

<!--   �d�Źj�} -->
<div style="height:30px"></div> 
<!--   �d�Źj�} -->

<!-- �d�� -->
<div style="display: flex; flex-wrap: wrap; justify-content: center; ">
<c:forEach var="logVOFAV" items="${listFav}" begin="0" end="5">
<div id="pic6" class="card col-3" style="width: 18rem;margin:20px 20px;">
  <a href="log.do?log_id=${logVOFAV.log_id}&action=getOne_For_Display">
  	<img  id ="pic" src="data:image/png;base64,${logVOFAV.log_pic}" class="card-img-top" onerror="javascript:this.src='<%=request.getContextPath()%>/NoData/no_photo.jpg'">
  </a>
  <div class="card-body">
    <h5 class="card-title" ><a href="#"><font style="color:#272727;font-weight:bold;font-size:25px;line-height: 25px;">${logVOFAV.log_title}</font></a></h5>
    <div style="display: flex; flex-direction: row; justify-content: center;">
    <a id="btn" href="log.do?log_id=${logVOFAV.log_id}&action=getOne_For_Display" class="btn btn-primary">��x�Ա�</a>
    </div>
  </div>
</div>
</c:forEach>
</div>
<!-- �d�� -->

<!-- �������s -->
<a href="<%=request.getContextPath()%>/front-end/log/listAllFav.jsp">
<div style="display: flex; flex-direction: row; justify-content: center;">
<button style="margin:50px ;" type="button" class="btn btn-outline-dark col-3">��h������x...</button>
</div>
</a>
<!-- �������s -->

<!--   �d�Źj�} -->
<div style="height:30px"></div> 
<!--   �d�Źj�} -->

<!-- ������x -->
<h2  align="center"><b>�̷s�峹</b></h2>
<!-- ������x -->

<!-- �d�� -->
<div style="display: flex; flex-wrap: wrap; justify-content: center; ">
<c:forEach var="logVODESC" items="${listDesc}" begin="0" end="5">
<div id="pic6" class="card col-3" style="width: 18rem;margin:20px 20px;">
  <a href="log.do?log_id=${logVODESC.log_id}&action=getOne_For_Display">
  	<img id="pic" src="data:image/png;base64,${logVODESC.log_pic} " class="card-img-top"  onerror="javascript:this.src='<%=request.getContextPath()%>/NoData/no_photo.jpg'">
  </a>
  <div class="card-body">
    <h5 class="card-title"><a href="#"><font style="color:#272727;font-weight:bold;font-size:25px;line-height: 25px;">${logVODESC.log_title}</font></a></h5>
    <div style="display: flex; flex-direction: row; justify-content: center;">
    <a id="btn" href="log.do?log_id=${logVODESC.log_id}&action=getOne_For_Display" class="btn btn-primary" >��x�Ա�</a>
    </div>
  </div>
</div>
</c:forEach>
</div>
<!-- �d�� -->

<!--   �d�Źj�} -->
<div style="height:30px"></div> 
<!--   �d�Źj�} -->

<!-- �������s -->
<a href="<%=request.getContextPath()%>/front-end/log/listAllDesc.jsp">
<div style="display: flex; flex-direction: row; justify-content: center;">
<button style="margin:50px ;" type="button" class="btn btn-outline-dark col-3">��h�̷s�峹...</button>
</div>
</a>
<!-- �������s -->

</div>
<%@ include file="/front-end/EndFooter.file" %>



</body>

</html>