<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.demand.model.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.p_message.model.*"%>
<%@ page import="com.p_report.model.*"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%-- 此頁暫練習採用 Script 的寫法取值 --%>
<%
	String mem_no=(String)session.getAttribute("mem_no");
	MemberService Membersvc=new MemberService();
	MemberVO memberVO=null;
	memberVO=Membersvc.getOneMember(mem_no);
	pageContext.setAttribute("memberVO", memberVO);	

%>
<%
	HashMap<Integer, String> Status = new HashMap();
	Status.put(0, "上架中");
	Status.put(1, "已下架");
	Status.put(2, "已下標");
	Status.put(3, "已購買");
	Status.put(4, "運送中");
	Status.put(5, "已送達");
	Status.put(6, "交易完成");
	pageContext.setAttribute("Status", Status);
%>

<%
  DemandVO demandVO = (DemandVO) request.getAttribute("demandVO"); //DemandServlet.java(Concroller), 存入req的demandVO物件
%>

<head>
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
		
	<title>需求明細</title>

	<style>
	 .table-headerOneDemand {
		width: 550px;
		margin: 30px;
		text-align: center;
	  }
	  .table-bordered {
	  	text-align: center;
	  }
	  #oneDemand {
		display: flex;
		justify-content: center;
		flex-direction: column;
	  }
	  #score{
	  	display: none;
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
		 theurl="<%=request.getContextPath()%>/front-end/member/memberCard2.jsp?mem_no=${demandVO.de_mem_no}"
		 wname ="CHROMELESSWIN"
		 W=1200;
		 H=800;
		 openchromeless(theurl, wname, W, H)
		}
		function openIT2() {
			 theurl="<%=request.getContextPath()%>/front-end/member/memberCard2.jsp?mem_no=${demandVO.pur_mem_no}"
			 wname ="CHROMELESSWIN"
			 W=1200;
			 H=800;
			 openchromeless(theurl, wname, W, H)
			}
	
	</script>
</head>

<body>
<%@ include file="/front-end/TopHeader.file" %>
<div style="display: flex; justify-content: center;align-items: flex-start;">
	<div id="OneDemand">
	<table class="table-headerOneDemand">
		<tr><td>
			<h3>需求明細</h3>
		</td></tr>
	</table>
	
	<table class="table table-bordered">
	
		<tbody>
	   		<tr>
	      		<th scope="row">需求編號</th>
	      		<td><%=demandVO.getDe_no()%></td>
	    	</tr>
	    	<tr>
	      		<th scope="row">需求商品名稱</th>
	      		<td><%=demandVO.getDe_item_name()%></td>
	    	</tr>
	    	<tr>
	      		<th scope="row" style="vertical-align: middle;">需求商品照片</th>
	      		<td><img src = "data:image/png;base64,${demandVO.de_photo}" height="100"></td>
	    	</tr>
	    	<tr>
	      		<th scope="row">價格</th>
	      		<td><fmt:formatNumber value = "${demandVO.price}" type = "currency"/></td>
	    	</tr>
	    	<tr>
	      		<th scope="row">需求者編號</th>
	      		<td><a href="JavaScript:openIT()">${demandVO.de_mem_no}</a></td>
	    	</tr>
	    	<tr>
	      		<th scope="row">代購者編號</th>
	      		<td><a href="JavaScript:openIT2()">${demandVO.pur_mem_no}</a></td>
	    	</tr>
	    	<tr>
	      		<th scope="row">地區</th>
	      		<td><%=demandVO.getArea()%></td>
	    	</tr>
	    	<tr>
	      		<th scope="row">需求時間</th>
	      		<td><fmt:formatDate value="${demandVO.de_time}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	    	</tr>
	    	<tr>
	      		<th scope="row">需求狀態</th>
	      		<td>
					<c:forEach var="status" items="${Status}">
						${demandVO.status==status.key ? status.value : ''}
					</c:forEach>
				</td>
	    	</tr>
	    	<tr id="status">
	      		<th scope="row" style="line-height: 38px;">需求管理</th>
	      		<td style="padding-bottom: 0px;">
	      			<div class="btn-group" role="group" aria-label="Basic example" style="display: flex; justify-content: space-around; height:100%">
					
					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/demand/demand.do" style="display: flex; justify-content: space-around;">
					
					<select name="status" class="form-control" style="margin-right:10px;">
							<option value="" selected>請選擇狀態</option>
							<option value="2">已下標</option>
							<option value="3">已購買</option>
							<option value="4">運送中</option>
							<option value="5">已送達</option>
					</select>
					
						     <input type="submit" value="更新狀態" class="btn btn-secondary">
						     <input type="hidden" name="de_no"  value="${demandVO.de_no}">
						     <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
						     <input type="hidden" name="action"	value="updateStatus">
					</FORM>
					
			    	</div>
				</td>
			</tr>
			<tr id="score">
				<th scope="row" style="vertical-align: middle;">評價</th>
				<td style="padding-bottom: 0px;">
					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/demand/demand.do" style="padding: 20px;">
						<div style="padding-bottom: 20px;">
							<div class="custom-control custom-radio custom-control-inline">
								<input type="radio" id="customRadioInline1" name="de_score" class="custom-control-input" value="1" required>
								<label class="custom-control-label" for="customRadioInline1">1星</label>
							</div>
							<div class="custom-control custom-radio custom-control-inline">
								<input type="radio" id="customRadioInline2" name="de_score" class="custom-control-input" value="2" required>
								<label class="custom-control-label" for="customRadioInline2">2星</label>
							</div>
							<div class="custom-control custom-radio custom-control-inline">
								<input type="radio" id="customRadioInline3" name="de_score" class="custom-control-input" value="3" required>
								<label class="custom-control-label" for="customRadioInline3">3星</label>
							</div>
							<div class="custom-control custom-radio custom-control-inline">
								<input type="radio" id="customRadioInline4" name="de_score" class="custom-control-input" value="4" required>
								<label class="custom-control-label" for="customRadioInline4">4星</label>
							</div>
							<div class="custom-control custom-radio custom-control-inline">
								<input type="radio" id="customRadioInline5" name="de_score" class="custom-control-input" value="5" required>
								<label class="custom-control-label" for="customRadioInline5">5星</label>
							</div>
						</div>
						
						<div class="form-group" style="padding-bottom: 20px;">
						    <textarea name="de_comment" class="form-control" id="exampleFormControlTextarea1" rows="3"></textarea>
						</div>
						
						<div>
							<input type="submit" value="提交評價" class="btn btn-secondary">
							<input type="hidden" name="action" value="addScore2">
							<input type="hidden" name="de_no" value="${demandVO.de_no}">
							<input type="hidden" name="de_mem_no" value="${demandVO.de_mem_no}">
						</div>
					</FORM>
				</td>
			</tr>
	  	</tbody>
	</table>
	
	<%
	  P_MessageService p_messageSvc = new P_MessageService();
	  List<P_MessageVO> listmsg = p_messageSvc.findByDe_no(demandVO.getDe_no());
	  pageContext.setAttribute("listmsg",listmsg);
	%>	
	
	<jsp:useBean id="memberSvc" scope="page" class="com.member.model.MemberService"/>
	
		<table class="table-headerOneDemand">
			<tr><td>
				<h3>留言板</h3>
			</td></tr>
		</table>
		
		<div style="width: 100%; height: 200px; display: flex; flex-direction: row; border: 1px solid #dee2e6; margin: 0px; border-radius: 1em;">
				
				<table style="display: flex; justify-content: flex-start; overflow-y: scroll; resize: none; width: 100%; flex-direction: column-reverse;">
						<c:forEach var="p_messageVO" items="${listmsg}">
							
						
							<tr>
								<td style="padding-left: 10px;">${memberSvc.getOneMember(p_messageVO.mem_no).m_name}: ${p_messageVO.p_msg}</td>
								<td style="padding-left: 10px; color: gray;"><fmt:formatDate value="${p_messageVO.p_msg_time}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
							</tr>
						</c:forEach>
				</table>
		</div>
		
				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/demand/demand.do" style="display: flex; justify-content: flex-start; margin-top: 20px;">
					<textarea name="p_msg" style="width: 85%; border: 1px solid #dee2e6; margin: 0px; border-radius: 1em;"></textarea>
					<input type="submit" value="送出留言" class="btn btn-secondary">
					<input type="hidden" name="de_no"  value="${demandVO.de_no}">
					<input type="hidden" name="mem_no"  value="${mem_no}">
					<input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
					<input type="hidden" name="action"	value="addMSG_listOnly">
				</FORM>
	
	
	</div>
</div>

<%@ include file="/front-end/EndFooter.file" %>
<script src="<%=request.getContextPath()%>/front-end/js/custom.js"></script>
<script src="<%=request.getContextPath()%>/front-end/js/sweetalert2.min.js"></script>

<script>
		var state='${param.state}'
		if (state=='updateSuccess') {
			Swal.fire({
				title: '更新成功',
				icon: 'success' ,
				showConfirmButton: false,
				showCloseButton: true,
				timer: 1500
			})
		}
		if (state=='msgSuccess') {
			Swal.fire({
				title: '留言成功',
				icon: 'success' ,
				showConfirmButton: false,
				showCloseButton: true,
				timer: 1500
			})
		}
		if (state=='msgFail') {
			Swal.fire({
				title: '請輸入留言內容',
				icon: 'error' ,
				showConfirmButton: false,
				showCloseButton: true,
				timer: 1500
			})
		}
		var status='${demandVO.status}'
		var de_score='${demandVO.de_score}'
		if (status == '6') {
			if (de_score=='0')
				$("#score").toggle();
		}
		if (status == '6') {
			$("#status").toggle();
		}
</script>
</body>
</body>

</html>