<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>��H�|���q��</title>
</head>
<body>
<table >
   <tr><td><h3>��H�|���q��: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>Home page for NotificationEmployee: Home</p>

<h3>��Ƭd��:--------${errorMsgs}--------${errorMsgs[0]}</h3>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
  
  <li>
    <FORM METHOD="post" ACTION="notification.do" >
        <b>��J�q���s�� (�pMN0001):</b>
        <input type="text" name="note_no">
        <input type="hidden" name="action" value="getOne_Note_Display">
        <input type="submit" value="�e�X">
    </FORM>
  </li>
<jsp:useBean id="notificationSvc" scope="page" class="com.notification.model.NotificationService" />
  <li>
     <FORM METHOD="post" ACTION="notification.do" >
       <b>��ܳq���s��:</b>
       <select size="1" name="note_no">
         <c:forEach var="notificationVO" items="${notificationSvc.all}" > 
          <option value="${notificationVO.note_no}">${notificationVO.note_no}</option>
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_Note_Display">
       <input type="submit" value="�e�X">
    </FORM>
  </li>
  <li>
    <FORM METHOD="post" ACTION="notification.do" >
        <b>��J�|���s�� (�pM0001):</b>
        <input type="text" name="mem_no">
        <input type="hidden" name="action" value="getOne_Mem_Display">
        <input type="submit" value="�e�X">
    </FORM>
  </li>
  <li>
     <FORM METHOD="post" ACTION="notification.do" >
       <b>��ܷ|���s��:</b>
       <select size="1" name="mem_no">
         <c:forEach var="notificationVO" items="${notificationSvc.all}" > 
          <option value="${notificationVO.mem_no}">${notificationVO.mem_no}</option>
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_Mem_Display">
       <input type="submit" value="�e�X">
    </FORM>
  </li>  
   

 

  
</ul>



</body>
</html>