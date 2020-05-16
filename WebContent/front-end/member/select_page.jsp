<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<html>
<head>
<title>Member HOME</title>
</head>
<body>

<p>
<h3>�e�x�|��</h3>
<h3>��Ƭd��</h3>
<p>${errorMsgs}</p>
<p>${errorMsgs[0]}</p>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	
	</ul>
</c:if>

<ul>
<%-- 
	<li><a href='listAllMember.jsp'>List</a> all Member.</li>
--%>	
	<li>
		<Form method="post" action="member.do">
			<b>��J�|���s��</b>
			<input type="text" name="mem_no">	
			<input type="hidden" name="action" value="front_One_Display">
			<input type="submit" value="�e�X">	
		</Form>
	</li>
	<jsp:useBean id="memberSvc" scope="page" class="com.member.model.MemberService" />
	<li>
		<Form method="post" action="member.do">
			<b>��ܷ|���s���G</b>
			<%--�U�Ԧ���� --%>
			<select size=1 name="mem_no">
				<c:forEach var="memberVO" items="${memberSvc.all}">
					<option value="${memberVO.mem_no}">${memberVO.mem_no}</option>
				</c:forEach>
			</select>
			<input type="hidden" name="action" value="front_One_Display">
			<input type="submit" value="�e�X">
		</Form>
	</li>
	
	<li>
		<Form method="post" action="member.do">
			<b>��ܷ|���b���G</b>
			<%--�U�Ԧ���� --%>
			<select size=1 name="mem_no">
				<c:forEach var="memberVO" items="${memberSvc.all}">
					<option value="${memberVO.mem_no}">${memberVO.mem_id}</option>
				</c:forEach>
			</select>
			<input type="hidden" name="action" value="front_One_Display">
			<input type="submit" value="�e�X">
		</Form>
	</li>
</ul>

<h3>�|���s�W</h3>

<ul>
	<li><a href='addMember.jsp'>ADD</a> a new member.</li>
</ul>

</body>
</html>