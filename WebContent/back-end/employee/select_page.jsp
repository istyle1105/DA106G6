<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>���u Employee</title>
</head>
<body>
<table >
   <tr><td><h3>Employee: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>Home page for Employee: Home</p>

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
  <li><a href='listAllEmployee.jsp'>List</a> all Employee.  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="employee.do" >
        <b>��J���u�s�� (�pE001):</b>
        <input type="text" name="emp_no">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="�e�X">
    </FORM>
  </li>

  <jsp:useBean id="employeeSvc" scope="page" class="com.employee.model.EmployeeService" />
   
  <li>
     <FORM METHOD="post" ACTION="employee.do" >
       <b>��ܭ��u�s��:</b>
       <select size="1" name="emp_no">
         <c:forEach var="employeeVO" items="${employeeSvc.all}" > 
          <option value="${employeeVO.emp_no}">${employeeVO.emp_no}</option>
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="�e�X">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="employee.do" >
       <b>��ܭ��u�m�W:</b>
       <select size="1" name="emp_no">
         <c:forEach var="employeeVO" items="${employeeSvc.all}" > 
          <option value="${employeeVO.emp_no}">${employeeVO.emp_name}</option>
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="�e�X">
     </FORM>
  </li>
  
</ul>
<h3>���u�޲z</h3>

<ul>
  <li><a href='addEmployee.jsp'>Add</a> �s�W���u</li>
</ul>


</body>
</html>