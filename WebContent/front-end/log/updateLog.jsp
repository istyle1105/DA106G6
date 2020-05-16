<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.member.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.log.model.*"%>

<%
	String mem_no=(String)session.getAttribute("mem_no");
	MemberService Membersvc=new MemberService();
	MemberVO memberVO=null;
	memberVO=Membersvc.getOneMember(mem_no);
	pageContext.setAttribute("memberVO", memberVO);	
%>  
<%
  LogVO logVO = (LogVO) request.getAttribute("logvo");
%>


 
<head>

<script src="<%=request.getContextPath()%>/ckeditor/ckeditor.js"></script>

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


<div style="width:100%;text-align:center">
  <%-- 錯誤表列 --%>
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



<!--   留空隔開 -->
<div style="height:30px"></div> 
<!--   留空隔開 -->


<!-- 個人日誌管理 -->
<h2  align="center"><b>個人日誌修改</b></h2>
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


<!-- 新增區塊 -->
<FORM METHOD="post" ACTION="log.do" name="form1" enctype="multipart/form-data" >
<div style="display: flex;  justify-content: center;">
<div class="input-group  mb-3 col-7">
  <div class="input-group-prepend">
    <span class="input-group-text" id="basic-addon1">日誌標題</span>
  </div>
  <input type="text" class="form-control"  value="<%= (logVO==null)? "" : logVO.getLog_title()%>" placeholder="Log Title" name="log_title" aria-describedby="basic-addon1">
</div>
</div>










<div style="display: flex;  justify-content: center;">
<div class="input-group mb-3 col-7">
  <div class="input-group-prepend">
    <span class="input-group-text" id="inputGroupFileAddon01">封面圖片</span>
  </div>
  <div class="custom-file">
    <input type="file" class="custom-file-input" id="inputGroupFile01" aria-describedby="inputGroupFileAddon01" name="log_pic">
    <label class="custom-file-label" for="inputGroupFile01">Choose Photo</label>
  </div>
</div>
</div>


<div style="display: flex;  justify-content: center; ">
<div class="input-group  mb-3 col-9">
  <div class="input-group-prepend">
    <span class="input-group-text" id="basic-addon1" style="text-align:center;">日誌內容</span>
  </div>
  <textarea  name="content" id="content" class="form-control" >${logvo.log_con}</textarea>
</div>
</div>





<div style="display: flex;  justify-content: center;">
<input  style="margin:0 20px" value="送出修改" type="submit" class="btn btn-danger col-2">

	<input type="hidden" name="l_favorited" value="${logvo.l_favorited}">
	<input type="hidden" name="l_status" value="${logvo.l_status}">
    <input type="hidden" name="mem_no" value="${memberVO.mem_no}">
	<input type="hidden" name="log_id" value="${logvo.log_id}">
    <input type="hidden" name="action" value="update">
</div>



</FORM>

<!-- 新增區塊 -->







</div>

<%@ include file="/front-end/EndFooter.file" %>


<script>
					CKEDITOR.replace( 'content', {
						height:['600px'],
						width :['1500px']
						
					});
				</script>



</body>

</html>