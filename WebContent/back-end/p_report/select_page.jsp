<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>IBM P_Report: Home</title>

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
   <tr><td><h3>IBM P_Report: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for IBM P_Report: Home</p>

<h3>�ݨD��Ƭd��:</h3>
	
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
  <li><a href='listAllP_Report.jsp'>List</a> all P_Reports  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="p_report.do" >
        <b>��J���|�s�� (�pDR0001):</b>
        <input type="text" name="p_re_no">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="�e�X">
    </FORM>
  </li>

  <jsp:useBean id="p_reportSvc" scope="page" class="com.p_report.model.P_ReportService" />

  <li>
     <FORM METHOD="post" ACTION="p_report.do" >
       <b>������|�s��:</b>
       <select size="1" name="p_re_no">
         <c:forEach var="p_reportVO" items="${p_reportSvc.all}" > 
          <option value="${p_reportVO.p_re_no}">${p_reportVO.p_re_no}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="�e�X">
    </FORM>
  </li>
 
</ul>


<h3>���|�޲z</h3>

<ul>
  <li><a href='addP_Report.jsp'>Add</a> a new Report.</li>
</ul>

</body>
</html>