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



<%  LogService logSvc = new LogService();
	List<LogVO> list = logSvc.getMemAll(memberVO.getMem_no());
	pageContext.setAttribute("list",list); %>





<head>


	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
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


</head>

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

<!--   �d�Źj�} -->
<div style="height:30px"></div> 
<!--   �d�Źj�} -->


<!-- �ӤH��x�޲z -->
<h2  align="center"><b>�ӤH��x�޲z</b></h2>
<!-- �ӤH��x�޲z -->

<!--   �d�Źj�} -->
<div style="height:30px"></div> 
<!--   �d�Źj�} -->

<div style="display: flex; flex-direction: row; justify-content: center;">
<button onclick="javascript:location.href='<%=request.getContextPath()%>/front-end/log/favList.jsp'" style="margin:0 20px;" type="button" class="btn btn-success col-2">�ڪ�����</button>
<button onclick="javascript:location.href='<%=request.getContextPath()%>/front-end/log/myList.jsp'" style="margin:0 20px" type="button" class="btn btn-danger col-2">�ڪ���x</button>
<button onclick="javascript:location.href='<%=request.getContextPath()%>/front-end/log/addLog.jsp'" style="margin:0 20px" type="button" class="btn btn-secondary col-2">�o����x</button>
</div>

<!--   �d�Źj�} -->
<div style="height:30px"></div> 
<!--   �d�Źj�} -->


<div style="margin:auto 215px">
<%@ include file="page1.file" %>
</div>

<!-- ��x�C�� -->
<div style="display: flex;  justify-content: center;">
<table class="table table-hover col-9">
  <thead>
    <tr>
      <th scope="col"></th>
      <th scope="col">��x�s��</th>
      <th scope="col">��x���D</th>
<!--       <th scope="col">��s�ɶ�</th> -->
      <th scope="col">��x���A</th>
      <th scope="col">�Q���ü�</th>
      <th scope="col"></th>
      
    </tr>
  </thead>
  <tbody>
    <c:forEach var="logmemVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
    <tr>
      <th scope="row"></th>
      <td><a href="log.do?log_id=${logmemVO.log_id}&action=getOne_For_Display">${logmemVO.log_id}</a></td>
      <td><a href="#" onclick="init('${logmemVO.log_id}')"><strong>${logmemVO.log_title}</strong></a></td>
<%--       <td><fmt:formatDate value="${logmemVO.l_time}" pattern="yyyy-MM-dd HH:mm"/></td> --%>
      <c:set var="val" value="${logmemVO.l_status}"/>
	    <td>${l_status[val]}</td>
      <td>${logmemVO.l_favorited}</td>
      <td>
      	<form METHOD="post" ACTION="log.do" name="form1" enctype="multipart/form-data" >
      	  
      	  <input type="hidden" name="log_id" value="${logmemVO.log_id}">
      	  <input type="hidden" name="action" value="getOne_For_Update">
      	  <input type="submit" class="btn btn-info" value="�ק�">
      	</form>
      	
      </td>
      	
    </tr>
    </c:forEach>
  </tbody>
</table>
</div>
<!-- ��x�C�� -->

<hr>


<!-- �������s -->
<div style="margin:auto 215px">
<%@ include file="page2.file" %>
</div>
<!-- �������s -->


<!--   �d�Źj�} -->
<div style="height:30px"></div> 
<!--   �d�Źj�} -->




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
					  '��x���D :'+logVO.log_title,
					  logVO.log_con
					)
	   },
	   error : function() {
	    alert('Ajax�s�u���`');
	   }
	  })
	}



var update='${param.update}';
if (update == 'true'){
	Swal.fire(
			'',  
			'�ק令�\!',
			'success'
			)
}


</script>
    
    
    
    
    
</body>

</html>