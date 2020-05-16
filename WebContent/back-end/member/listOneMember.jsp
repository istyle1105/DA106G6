<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.member.model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="javax.servlet.ServletContext"%>
<%
	String mem_no=(String)session.getAttribute("mem_no");
	MemberService Membersvc=new MemberService();
	MemberVO memberVO=null;
	memberVO=Membersvc.getOneMember(mem_no);
	pageContext.setAttribute("memberVO", memberVO);	
%>
<%--
	MemberVO memberVO=null;
	memberVO=(MemberVO)session.getAttribute("memberVO");
--%>


<html>
<head>
<title>會員資料 listOneEmp</title>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Let's Go Trip</title>
    <link rel="icon" href="<%=request.getContextPath()%>/front-end/img/favicon.png">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/bootstrap.min.css">
    <!-- animate CSS -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/animate.css">
    <!-- owl carousel CSS -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/owl.carousel.min.css">
    <!-- themify CSS -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/themify-icons.css">
    <!-- flaticon CSS -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/flaticon.css">
    <!-- fontawesome CSS -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/fontawesome/css/all.min.css">
    <!-- magnific CSS -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/magnific-popup.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/gijgo.min.css">
    <!-- niceselect CSS -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/nice-select.css">
    <!-- slick CSS -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/slick.css">
    <!-- style CSS -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/style.css">
    	<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/sweetalert2.min.css">

<style>
.table > tbody > tr > td {
     vertical-align: middle;
     text-align: center;
}
</style>


<script>
function openchromeless(theURL, wname, W, H) {
 var windowW = W;
 var windowH = H;
 var windowX = Math.ceil( (window.screen.width  - windowW) / 2 );
 var windowY = Math.ceil( (window.screen.height - windowH) / 2 );
 if (navigator.appName == "Microsoft Internet Explorer" && parseInt(navigator.appVersion)>=4) isie=true
 else                isie=false
 if (isie) { H=H+20+2; W=W+2; }
 s = ",width="+W+",height="+H;
 if (isie) {
  splashWin = window.open( "" , wname, "fullscreen=1,toolbar=0,location=0,directories=0,status=0,menubar=0,scrollbars=0,resizable=0"+s)
  splashWin.resizeTo( Math.ceil( W )       , Math.ceil( H ) )
  splashWin.moveTo  ( Math.ceil( windowX ) , Math.ceil( windowY ) )
  splashWin.document.open("text/html", "replace");
  splashWin.document.write("<html><style type='text/css'>\n");
  splashWin.document.write("body                  { border: 1px solid #000000; overflow: hidden; margin: 0pt;}");
  splashWin.document.write("#stillloadingDiv  { position: absolute; left: 0px; top: 0px; width: 100%px; height: 19px; z-index: 1; background-color: #C0C0C0; layer-background-color: #C0C0C0; clip:rect(0,100%,19,0);}");
  splashWin.document.write("</style>\n");
  splashWin.document.write("<body onload=\"top.document.location.replace('"+theurl+"')\" TOPMARGIN=0 LEFTMARGIN=0 MARGINWIDTH=0 MARGINHEIGHT=0 scroll='no'>");
  splashWin.document.write("<div id=stillloadingDiv><table width=100% height=22 cellspacing=0 cellpadding=0><tr><td align=left valign=middle width=100%><FONT size=1 face=verdana color=#000000>讀取中...如無法開啟請按 Ctrl+Alt+Del 關閉</font></td></tr></table></div>");
  splashWin.document.write("</body></html>");
  splashWin.document.close();
 }
 else    var splashWin = window.open(theURL, wname, "toolbar=0,location=0,directories=0,status=0,menubar=0,scrollbars=0,resizable=1"+s, true)
 splashWin.focus();
}
function openIT() {
 theurl="<%=request.getContextPath()%>/front-end/member/memberCard.jsp?mem_no=<%=memberVO.getMem_no()%>"
 wname ="CHROMELESSWIN"
 W=1200;
 H=700;
 openchromeless(theurl, wname, W, H)
}

</script>

</head>
<body>
<%@ include file="/front-end/TopHeader.file" %>


<div class="container" style="margin-top:10px;">
<div class="row border col-8 center" style="margin:0;margin:auto;border:3px solid red">
  <div class="col-4">
	<div class="card" >
		<div class="card-header col-12">
 			會員：<b><%=memberVO.getM_name()%></b> 帳號：<b><a href="JavaScript:openIT()"><%=memberVO.getMem_id()%></a></b>
  		</div>

				<ul class="list-group list-group-flush">
					<li class="list-group-item">註冊時間：<fmt:formatDate value="${memberVO.reg_time}" pattern="yyyy-MM-dd HH:mm"/></li>
					<li class="list-group-item">會員錢包餘額：<%=memberVO.getAcc_balance()%></li>
					<li class="list-group-item">會員驗證：
							<c:forEach var="status" items="${memStatus}">
					${memberVO.mem_verify==status.key ? status.value : ''}
					</c:forEach>
					</li>
					    <li class="list-group-item">團長驗證：
							<c:forEach var="status" items="${leaderStatus}">
					${memberVO.leader_verify==status.key ? status.value : ''}
					</c:forEach>
					</li>
					    <li class="list-group-item">代購驗證：
							<c:forEach var="status" items="${purStatus}">
					${memberVO.pur_verify==status.key ? status.value : ''}
					</c:forEach>
					</li>
					
					<li class="list-group-item">需求代購平均分數：
					<fmt:formatNumber value="${(memberVO.de_total_score div memberVO.de_score_amount)!='NaN' ? (memberVO.de_total_score div memberVO.de_score_amount) : '0'}"
					type="NUMBER" maxFractionDigits="2"/>
					</li>
					<li class="list-group-item">需求代購評價總人數：<%=memberVO.getDe_score_amount()%>人</li>
					
					<li class="list-group-item">提供代購平均分數：
					<fmt:formatNumber value="${(memberVO.pur_total_score div memberVO.pur_score_amount)!='NaN' ? (memberVO.pur_total_score div memberVO.pur_score_amount) : '0'}"
					type="NUMBER" maxFractionDigits="2"/>
					</li>
					<li class="list-group-item">提供代購評價總人數：<%=memberVO.getPur_score_amount()%>人</li>	
					<li class="list-group-item">團長平均分數：
					<fmt:formatNumber value="${(memberVO.leader_total_score div memberVO.leader_score_amount)!='NaN' ? (memberVO.leader_total_score div memberVO.leader_score_amount) : '0'}"
					type="NUMBER" maxFractionDigits="2"/>

					</li>
					<li class="list-group-item">團長評價總人數：<%=memberVO.getLeader_score_amount()%>人</li>
				</ul>

</div>

</div>
<div class="col-5" style="margin:auto;">
    <table class="table table-hover table-bordered h6 col-12">
  <tbody>

  	<tr>
  	  <td style="margin:auto;"colspan="2"><img height="180" onerror="javascript:this.src='<%=request.getContextPath()%>/NoData/no_photo.jpg'" src="data:image/png;base64,${memberVO.m_photo}"></td>
  	</tr>
  
    <tr>
      <td>會員編號</td>
      <td><%=memberVO.getMem_no()%></td>
    </tr>
    <tr>
      <td>會員帳號</td>
      <td><%=memberVO.getMem_id()%></td>
    </tr>
    <tr>
      <td>會員姓名</td>
      <td><%=memberVO.getM_name()%></td>
    </tr> 
    <tr>
      <td>會員性別</td>
      <td><%=memberVO.getGender()==0 ? "男性":"女性"%></td>
    </tr>  
    <tr>
      <td>會員手機</td>
      <td><%=memberVO.getCellphone()%></td>
    </tr>    
    <tr>
      <td>會員信箱</td>
      <td><%=memberVO.getEmail()%></td>
    </tr>
    <tr>
      <td>會員地址</td>
      <td><%=memberVO.getAddress()%></td>
    </tr>
  	<tr>
  		<td colspan="2" style="padding: 0px;">
  			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/member/member.do" style="margin: 3px auto;">
			     <button type="submit" class="btn btn-primary">會員基本資料修改</button>
			     <input type="hidden" name="mem_no"  value="${memberVO.mem_no}">
			     <input type="hidden" name="action"	value="front_One_Update"> 
			</FORM>
		</td>
  	</tr>  
  </tbody>
</table>
  </div>
</div>
</div>


<%@ include file="/front-end/EndFooter.file" %>
<script>
    var state='${param.state}'
    if(state=='updatesuccess'){
    	 Swal.fire({
   		  title: '修改成功',
   		  icon: 'success',
   		  showConfirmButton: false,
   		  showCloseButton: true,
   		  timer: 1500
   		})
    }
    </script>    
</body>
</html>