<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.member.model.*"%>
<%@ page import="com.complaint.model.*"%>
<%@ page import="java.util.*"%>
<%
	String mem_no=(String)session.getAttribute("mem_no");
	MemberService Membersvc=new MemberService();
	MemberVO memberVO=null;
	memberVO=Membersvc.getOneMember(mem_no);
	pageContext.setAttribute("memberVO", memberVO);
	
	ComplaintService complaintSvc=new ComplaintService();
	List<ComplaintVO> list = complaintSvc.getMemAll(mem_no);
	pageContext.setAttribute("list",list);
	
	HashMap compStatus=new HashMap();
	compStatus.put("0", "未處理");
	compStatus.put("1", "已處理");
	pageContext.setAttribute("compStatus", compStatus);
	
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

<%@ include file="page1.file" %>
<div class="col-8"style="margin:10px auto;">

<a href="<%=request.getContextPath()%>/front-end/complaint/addComp.jsp"><button type="button" class="btn btn-dark">新增申訴</button></a>

<table class="table table-hover col-12 center" style="margin-top:3px;">
  <thead> 
    <tr>
      <th scope="col">#</th>
      <th scope="col">申訴編號</th>
      <th scope="col">申訴日期</th>
      <th scope="col">申訴狀態</th>
      <th scope="col">申訴內容</th>
    </tr>
  </thead>
  <tbody>
	<c:forEach var="complaintVO" varStatus="counter" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">	
    <% int counter=0; %>
    
    <tr>

      <th scope="row">${counter.count}</th>
      <td>${complaintVO.comp_no}</td>
      <td>
      	<fmt:formatDate value="${complaintVO.comp_date}" pattern="yyyy-MM-dd HH:mm:ss"/>
      </td>
      <td>
      <c:forEach var="status" items="${compStatus}">
			${complaintVO.comp_status==status.key ? status.value : ''}
	  </c:forEach>
      </td>
      <td>
      <a tabindex="0" class="btn btn-danger popover-dismiss" role="button" data-toggle="popover" data-trigger="focus" 
	
	data-content="${complaintVO.comp_content}">
	檢舉內容</a>
      </td>
    </tr>
  
    </c:forEach>
  </tbody>
</table>
<%@ include file="page2.file"%>
</div>


<%@ include file="/front-end/EndFooter.file" %>


</body>

<script>
$('.popover-dismiss').popover({
	  trigger: 'focus'
	})
</script>
</html>