<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*"%>
<%@ page import="com.member.model.*" %>    


<%
	MemberService MemberSvc=new MemberService();
	List<MemberVO> list =MemberSvc.getAll();
	pageContext.setAttribute("list",list);
	//�U����74��|�bpagescope getList�X��
%>

<html>
<head>

<title>�Ҧ��|����� </title>

</head>
<body>

<h4>�����m�߱ĥ� EL ���g�k����:</h4>
<table>
	<tr>
		<td>
			<h3>�Ҧ��|�����</h3>
			<h4><a href="select_page.jsp">�^����</a></h4>
		</td>
	</tr>
</table>

<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>




<table border="1px solid black">
	<tr>
		<th>�|���s��</th>
		<th>�|���b��</th>
		<th>�K�X</th>
		<th>�m�W</th>
		<th>�ʧO</th>
		<th>���</th>
		<th>Email</th>
		<th>�a�}</th>
		<th>�Y���Ӥ�</th>
		<th>�����ҷӤ�</th>
		<th>�|�����Ҫ��A</th>
		<th>�Ϊ����Ҫ��A</th>
		<th>�N�����Ҫ��A</th>
		<th>���U�ɶ�</th>
		<th>�|�����]�l�B</th>
		<th>�ݨD�N�ʵ����`�H��</th>
		<th>�ݨD�N�ʵ����`��</th>
		<th>���ѥN�ʵ����`�H��</th>
		<th>���ѥN�ʵ����`��</th>
		<th>�Ϊ������`�H��</th>
		<th>�Ϊ������`��</th>
		<th>�ק�</th>
		<th>�R��</th>
	</tr>
	<%@ include file="page1.file" %>
	<c:forEach var="memberVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
	<tr>
		<td>${memberVO.mem_no}</td>
		<td>${memberVO.mem_id}</td>
		<td>${memberVO.psw}</td>
		<td>${memberVO.m_name}</td>
		<td>${memberVO.gender == 0 ? 'MAle':'Female'}</td>
		<td>${memberVO.cellphone}</td>
		<td>${memberVO.email}</td>
		<td>${memberVO.address}</td>
		<td><img width="100" onerror="javascript:this.src='/DA106G6/NoData/no_photo.jpg'" src="data:image/png;base64,${memberVO.m_photo}"></td>
		<td><img width="100" src="<%=request.getContextPath()%>/MemeberId_cardReader?mem_no=${memberVO.mem_no}"></td>		
		<td>
			<c:set var="mem_verify" value="${memberVO.mem_verify}"/>
			<c:choose>
			    <c:when test="${mem_verify== 0}">
					������
			    </c:when>
			    <c:when test="${mem_verify== 1}">
					�w����
			    </c:when>
			    <c:when test="${mem_verify== 2}">
					�|�����v
			    </c:when>
			</c:choose>
		</td>
		<td>
			<c:set var="leader_verify" value="${memberVO.leader_verify}"/>
			<c:choose>
			    <c:when test="${leader_verify== 0}">
					������
			    </c:when>
			    <c:when test="${leader_verify== 1}">
					��������
			    </c:when>
			    <c:when test="${leader_verify== 2}">
					�w����
			    </c:when>
			    <c:when test="${leader_verify== 3}">
					�Ϊ��v�����v
			    </c:when>
			</c:choose>
		</td>
		<td>
			<c:set var="pur_verify" value="${memberVO.pur_verify}"/>
			<c:choose>
			    <c:when test="${pur_verify== 0}">
					������
			    </c:when>
			    <c:when test="${pur_verify== 1}">
					��������
			    </c:when>
			    <c:when test="${pur_verify== 2}">
					�w����
			    </c:when>
			    <c:when test="${pur_verify== 3}">
					�N���v�����v
			    </c:when>
			</c:choose>
		</td>
		<td><fmt:formatDate value="${memberVO.reg_time}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
		<td>${memberVO.acc_balance}</td>
		<td>${memberVO.de_score_amount}</td>
		<td>${memberVO.de_total_score}</td>
		<td>${memberVO.pur_score_amount}</td>
		<td>${memberVO.pur_total_score}</td>
		<td>${memberVO.leader_score_amount}</td>
		<td>${memberVO.leader_total_score}</td>
		<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/member/member.do" style="margin-bottom: 0px;">
			     <input type="submit" value="�ק�">
			     <input type="hidden" name="mem_no"  value="${memberVO.mem_no}">
			     <input type="hidden" name="action"	value="getOne_For_Update">
			  </FORM>
		</td>
		<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/member/member.do" style="margin-bottom: 0px;">
			     <input type="submit" value="�R��">
			     <input type="hidden" name="mem_no"  value="${memberVO.mem_no}">
			     <input type="hidden" name="action" value="delete">
			  </FORM>
		</td>
	</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>


</body>
</html>