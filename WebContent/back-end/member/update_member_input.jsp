<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>

<%
	MemberVO memberVO= (MemberVO)request.getAttribute("memberVO");
%>
<%-- 一開始就是false 一開始就有拿到empVO--%>
<%=  memberVO==null %> --${empVO.deptno}--
<html>
<head>
<title>後台會員資料修改</title>
</head>
<body>

<Table>
	<tr><td>
		 <h3>會員資料修改 - update_member_input.jsp</h3>
		 <h4><a href="select_page.jsp">回首頁</a></h4>
	</td></tr>	
</Table>
<h3>資料修改:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<Form method="post" action="member.do" name="form1" enctype="multipart/form-data">
<table>
	<tr>
		<td>會員編號</td>
		<td><%=memberVO.getMem_no()%></td>
	</tr>
	<tr>
		<td>會員帳號</td>
		<td><%=memberVO.getMem_id()%></td>
	</tr>
	<tr>
		<td>會員密碼</td>
		<td><input type="password" name="psw" size="15" value="<%=memberVO.getPsw()%>">
		</td>
	</tr>
	<tr>
		<td>姓名</td>
		<td><input type="text" name="m_name" size="30" value="<%=memberVO.getM_name()%>">
		</td>
	</tr>
	<tr>
		<td>性別</td>
		<td>
			<label><input type="radio" name="gender" value="0" ${memberVO.gender==0 ? 'checked' : ''} >男性</label>
			<label><input type="radio" name="gender" value="1" ${memberVO.gender==1 ? 'checked' : ''} >女性</label>
		</td>
	</tr>
	<tr>
		<td>手機</td>
		<td><input type="text" name="cellphone" size="10" value="<%=memberVO.getCellphone()%>">
		</td>
	</tr>
	<tr>
		<td>E-mail</td>
		<td><input type="email" name="email" size="45" value="<%=memberVO.getEmail()%>">
		</td>
	</tr>
	<tr>
		<td>地址</td>
		<td><input type="text" name="address" size="45" value="<%=memberVO.getAddress()%>">
		</td>
	</tr>	 
	<tr>
		<td>會員頭像</td>
		<td><input type="file" name="m_photo" ></td>
	</tr>
	<tr>
		<td>身分證照片</td>
		<td><input type="file" name="id_card" ></td>
	</tr>
	<tr>
		<td>一般會員驗證狀態:<font color=red></font></td>
		<td><select size="1" name="mem_verify">
			<option value="0" ${(memberVO.mem_verify==0)?'selected':'' } >未驗證</option>
			<option value="1" ${(memberVO.mem_verify==1)?'selected':'' } >已驗證</option>
			<option value="2" ${(memberVO.mem_verify==2)?'selected':'' } >停權</option>
		</select></td>
	</tr>
	<tr>
		<td>團主驗證狀態:<font color=red></font></td>
		<td><select size="1" name="leader_verify">
			<option value="0" ${(memberVO.leader_verify==0)?'selected':'' } >未驗證</option>
			<option value="1" ${(memberVO.leader_verify==1)?'selected':'' } >等待驗證</option>
			<option value="2" ${(memberVO.leader_verify==2)?'selected':'' } >已驗證</option>
			<option value="3" ${(memberVO.leader_verify==3)?'selected':'' } >停權</option>
		</select></td>
	</tr>
	<tr>
		<td>代購者驗證狀態:<font color=red></font></td>
		<td><select size="1" name="pur_verify">
			<option value="0" ${(memberVO.pur_verify==0)?'selected':'' } >未驗證</option>
			<option value="1" ${(memberVO.pur_verify==1)?'selected':'' } >等待驗證</option>
			<option value="2" ${(memberVO.pur_verify==2)?'selected':'' } >已驗證</option>
			<option value="3" ${(memberVO.pur_verify==3)?'selected':'' } >停權</option>
		</select></td>
	</tr>
</table>

<input type="hidden" name="action" value="back_update">
<input type="hidden" name="mem_no" value="${memberVO.mem_no}">
<%--<input type="hidden" name="mem_id" value="${memberVO.mem_id}"> --%>
<input type="submit" value="送出">
</Form>

</body>
</html>