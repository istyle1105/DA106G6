<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.member.model.*"%>
<%@ page import="com.notification.model.*"%>
<%@ page import="java.util.*"%>
<%
	String mem_no=(String)session.getAttribute("mem_no");
	MemberService Membersvc=new MemberService();
	MemberVO memberVO=null;
	memberVO=Membersvc.getOneMember(mem_no);
	pageContext.setAttribute("memberVO", memberVO);
	
	NotificationService notificationSvc=new NotificationService();
	List<NotificationVO> list = notificationSvc.getMemAllNote(mem_no);
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

<%@ include file="page1_2.file" %>
<div class="col-8"style="margin:20px auto;">

<table class="table table-hover col-12 center" style="margin-top:3px;">
  <thead> 
    <tr>
      <th scope="col">#</th>
      <th scope="col">通知編號</th>
      <th scope="col">通知日期</th>
      <th scope="col">通知狀態</th>
      <th scope="col">申訴內容</th>
    </tr>
  </thead>
  <tbody>
	<c:forEach var="notificationVO" varStatus="counter" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">	    
    <tr>

      <th scope="row">${counter.count}</th>
      <td>${notificationVO.note_no}</td>
      <td>
      	<fmt:formatDate value="${notificationVO.note_date}" pattern="yyyy-MM-dd HH:mm:ss"/>
      </td>
      <td>
      <c:forEach var="status" items="${noteStatus}">
			${notificationVO.note_status==status.key ? status.value : ''}
	  </c:forEach>
      </td>
      <td>
	      <form method="post" action="<%=request.getContextPath()%>/notification/notification.do">
	      	<input type="hidden" name="note_no" value="${notificationVO.note_no}">
	      	<input type="hidden" name="action" value="listOneNote">
	      	<button type="submit" class="btn btn-dark">顯示通知內容</button>
	      </form>
	      
      </td>
    </tr>
 	
    </c:forEach>
  </tbody>
</table>
<%@ include file="page2_2.file"%>

</div>

<%@ include file="/front-end/EndFooter.file" %>
<c:if test="${openModal!=null}">

<div class="modal fade" id="basicModal" tabindex="-1" role="dialog" aria-labelledby="basicModal" aria-hidden="true">
	<div class="modal-dialog modal-lg ">
		<div class="modal-content">
				
			<div class="modal-header">
                <h3 class="modal-title" id="myModalLabel">通知內容</h3>
            </div>
			
			<div class="modal-body">
<!-- =========================================以下為原listOneEmp.jsp的內容========================================== -->
               <jsp:include page="/front-end/notification/listOneNote.jsp" />
<!-- =========================================以上為原listOneEmp.jsp的內容========================================== -->
			</div>
			
			<div class="modal-footer">
                <button type="button" class="btn btn-dark" data-dismiss="modal">Close</button>
            </div>
		
		</div>
	</div>
</div>

        <script>
    		 $("#basicModal").modal({show: true});
        </script>
 </c:if>

</body>


</html>