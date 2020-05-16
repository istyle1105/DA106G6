<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>IBM Trip_list: Home</title>

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
   <tr><td><h3>IBM Trip_list: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for IBM Trip_list: Home</p>

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
  <li><a href='<%=request.getContextPath()%>/front-end/trip_list/listAllTrip_list.jsp'>List</a> all Trip_lists.  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/trip_list/trip_list.do" >
        <b>��J���νs�� (�pT0001):</b>
        <input type="text" name="trip_id">
        <br>
        <b>��J�|���s�� (�pM0001):</b>
        <input type="text" name="mem_no">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="�e�X">
    </FORM>
  </li>

  <jsp:useBean id="trip_listSvc" scope="page" class="com.trip_list.model.Trip_listService" />
   
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/trip_list/trip_list.do" >
       <b>��ܴ��νs��:</b>
       <select size="1" name="trip_id">
         <c:forEach var="trip_listVO" items="${trip_listSvc.all}" > 
          <option value="${trip_listVO.trip_id}">${trip_listVO.trip_id}
         </c:forEach>   
       </select>
       <br>
       <b>��ܷ|���s��:</b>
       <select size="1" name="mem_no">
         <c:forEach var="trip_listVO" items="${trip_listSvc.all}" > 
          <option value="${trip_listVO.mem_no}">${trip_listVO.mem_no}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="�e�X">
    </FORM>
  </li>
  
</ul>


<h3>���γ��W�޲z</h3>

<ul>
  <li><a href='<%=request.getContextPath()%>/front-end/trip_list/addTrip_list.jsp'>Add</a> a new Trip_list.</li>
</ul>

</body>
</html>