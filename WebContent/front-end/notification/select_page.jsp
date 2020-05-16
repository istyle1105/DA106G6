<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>單人會員通知</title>
</head>
<body>
<table >
   <tr><td><h3>單人會員通知: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>Home page for NotificationEmployee: Home</p>

<h3>資料查詢:--------${errorMsgs}--------${errorMsgs[0]}</h3>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
  
  <li>
    <FORM METHOD="post" ACTION="notification.do" >
        <b>輸入通知編號 (如MN0001):</b>
        <input type="text" name="note_no">
        <input type="hidden" name="action" value="getOne_Note_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>
<jsp:useBean id="notificationSvc" scope="page" class="com.notification.model.NotificationService" />
  <li>
     <FORM METHOD="post" ACTION="notification.do" >
       <b>選擇通知編號:</b>
       <select size="1" name="note_no">
         <c:forEach var="notificationVO" items="${notificationSvc.all}" > 
          <option value="${notificationVO.note_no}">${notificationVO.note_no}</option>
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_Note_Display">
       <input type="submit" value="送出">
    </FORM>
  </li>
  <li>
    <FORM METHOD="post" ACTION="notification.do" >
        <b>輸入會員編號 (如M0001):</b>
        <input type="text" name="mem_no">
        <input type="hidden" name="action" value="getOne_Mem_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>
  <li>
     <FORM METHOD="post" ACTION="notification.do" >
       <b>選擇會員編號:</b>
       <select size="1" name="mem_no">
         <c:forEach var="notificationVO" items="${notificationSvc.all}" > 
          <option value="${notificationVO.mem_no}">${notificationVO.mem_no}</option>
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_Mem_Display">
       <input type="submit" value="送出">
    </FORM>
  </li>  
   

 

  
</ul>



</body>
</html>