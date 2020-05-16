<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>IBM Tour_detail: Home</title>

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
 <jsp:useBean id="tourSvc" class="com.tour.model.TourService"/>
<table id="table-1">
   <tr><td><h3>IBM Tour_detail: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for IBM Tour_detail: Home</p>

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
  <li><a href='<%=request.getContextPath()%>/front-end/tour_detail/listAllTour_detail.jsp'>List</a> all Tour_details.  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/tour_detail/tour_detail.do" >
        <b>輸入行程細節編號 (如RD0001):</b>
        <input type="text" name="tour_detail_id">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>

  <jsp:useBean id="tour_detailSvc" scope="page" class="com.tour_detail.model.Tour_detailService" />
   
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/tour_detail/tour_detail.do" >
       <b>選擇行程細節編號:</b>
       <select size="1" name="tour_detail_id">
         <c:forEach var="tour_detailVO" items="${tour_detailSvc.all}" > 
          <option value="${tour_detailVO.tour_detail_id}">${tour_detailVO.tour_detail_id}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/tour_detail/tour_detail.do" >
       <b>選擇行程細節描述:</b>
       <select size="1" name="tour_detail_id">
         <c:forEach var="tour_detailVO" items="${tour_detailSvc.all}" > 
          <option value="${tour_detailVO.tour_detail_id}">${tour_detailVO.act_descrip}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
     </FORM>
  </li>
  <li>

     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/tour_detail/tour_detail.do" >
     	
     <b>行程展示:</b><p>
       <b>選擇行程:</b>
       <select size="1" name="tour_id">
         <c:forEach var="tourVO" items="${tourSvc.all}" > 
          <option value="${tourVO.tour_id}">${tourVO.tour_name}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOneTour_Show_Detail">
       <input type="submit" value="送出">
     </FORM>
  </li>
</ul>


<h3>行程細節管理</h3>

<ul>
  <li><a href='<%=request.getContextPath()%>/front-end/tour_detail/addTour_detail.jsp'>Add</a> a new Tour_detail.</li>
</ul>

</body>
</html>