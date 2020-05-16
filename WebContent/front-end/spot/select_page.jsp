<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>IBM Emp: Home</title>

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
   <tr><td><h3>IBM Spot: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for IBM Spot: Home</p>

<h3>��Ƭd��:</h3>
	
<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
  <li><a href='<%=request.getContextPath()%>/front-end/spot/listAllSpot.jsp'>List</a> all Spots.  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/spot/spot.do" >
        <b>��J���I�s�� (�pS0001):</b>
        <input type="text" name="spot_id">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="�e�X">
    </FORM>
  </li>

  <jsp:useBean id="empSvc" scope="page" class="com.spot.model.SpotService" />
   
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/spot/spot.do" >
       <b>��ܴ��I�s��:</b>
       <select size="1" name="spot_id">
         <c:forEach var="empVO" items="${empSvc.all}" > 
          <option value="${empVO.spot_id}">${empVO.spot_id}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="�e�X">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/spot/spot.do" >
       <b>��ܴ��I�W��:</b>
       <select size="1" name="spot_id">
         <c:forEach var="empVO" items="${empSvc.all}" > 
          <option value="${empVO.spot_id}">${empVO.spot_name}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="�e�X">
     </FORM>
  </li>
</ul>


<h3>���I�޲z</h3>

<ul>
  <li><a href='<%=request.getContextPath()%>/front-end/spot/addSpot.jsp'>�s�W���I</a></li>
</ul>


</body>
</html>