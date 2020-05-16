<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>IBM Tour: Home</title>

<style>
  table#table-1 {
	width: 450px;
	background-color: #CCCCFF;
	margin-top: 5px;
	margin-bottom: 10px;
    border: 3px ridge Gray;
    height: 80px;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

</head>
<body bgcolor='white'>

<table id="table-1">
   <tr><td><h3>IBM Tour: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for IBM Tour: Home</p>

<h3>資料查詢:</h3>
	
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
  <li><a href='<%=request.getContextPath()%>/front-end/tour/listAllTour.jsp'>所有行程列表</a>   <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/tour/tour.do" >
        <b>輸入行程編號 (如R0001):</b>
        <input type="text" name="tour_id">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>

  <jsp:useBean id="tourSvc" scope="page" class="com.tour.model.TourService" />
   
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/tour/tour.do" >
       <b>選擇行程編號:</b>
       <select size="1" name="tour_id">
         <c:forEach var="tourVO" items="${tourSvc.all}" > 
          <option value="${tourVO.tour_id}">${tourVO.tour_id}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/tour/tour.do" >
       <b>選擇行程名稱:</b>
       <select size="1" name="tour_id">
         <c:forEach var="tourVO" items="${tourSvc.all}" > 
          <option value="${tourVO.tour_id}">${tourVO.tour_name}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
     </FORM>
  </li>
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/tour/tour.do" >
       <b>選擇會員名稱:</b>
       <select size="1" name="mem_no">
         <c:forEach var="tourVO" items="${tourSvc.all}" > 
          <option value="${tourVO.mem_no}">${tourVO.mem_no}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOnePerson_For_Display">
       <input type="submit" value="送出">
     </FORM>
  </li>
</ul>


<h3>行程管理</h3>

<ul>
  <li><a href='<%=request.getContextPath()%>/front-end/tour/addTour.jsp'>建立行程</a> </li>
</ul>

</body>
</html>