<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="javax.servlet.ServletContext"%>


<%
	MemberVO memberVO2= (MemberVO)request.getAttribute("memberVO2");
	pageContext.setAttribute("memberVO2", memberVO2);
%> 
<%
	//ServletContext context=getServletContext();
	//map =(HashMap)context.getAttribute("empStatus");
	HashMap memStatus=new HashMap();
	memStatus.put("0", "未驗證");
	memStatus.put("1", "已驗證");
	memStatus.put("2", "停權'");
	pageContext.setAttribute("memStatus", memStatus);
	
	HashMap leaderStatus=new HashMap();
	leaderStatus.put("0", "未驗證");
	leaderStatus.put("1", "已驗證");
	leaderStatus.put("2", "停權'");
	pageContext.setAttribute("leaderStatus", leaderStatus);
	
	HashMap purStatus=new HashMap();
	purStatus.put("0", "未驗證");
	purStatus.put("1", "已驗證");
	purStatus.put("2", "停權'");
	pageContext.setAttribute("purStatus", purStatus);
%>


<html>
<head>
<title>會員資料</title>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/back-end/css/bootstrap4_3_1.css">


</head>
<body>
<form method="post" action="<%=request.getContextPath()%>/member/member.do" name="form1">
<table>
	<tr>
		<td rowspan="4">
			<img class="pimg" width="200" src="<%=request.getContextPath()%>/MemeberId_cardReader?mem_no=${memberVO2.mem_no}">
		</td>
		<td>會員驗證狀態<font color=red></font></td>
		<td><select size="1" name="mem_verify">
			<option value="0" ${(memberVO2.mem_verify==0)?'selected':'' } >未驗證</option>
			<option value="1" ${(memberVO2.mem_verify==1)?'selected':'' } >已驗證</option>
			<option value="2" ${(memberVO2.mem_verify==2)?'selected':'' } >停權</option>
		</select></td>
	</tr>
	<tr>
		<td>團主驗證狀態<font color=red></font></td>
		<td><select size="1" name="leader_verify">
			<option value="0" ${(memberVO2.leader_verify==0)?'selected':'' } >未驗證</option>
			<option value="1" ${(memberVO2.leader_verify==1)?'selected':'' } >已驗證</option>
			<option value="2" ${(memberVO2.leader_verify==2)?'selected':'' } >停權</option>
		</select></td>
	</tr>
	<tr>
		<td>代購者驗證狀態<font color=red></font></td>
		<td><select size="1" name="pur_verify">
			<option value="0" ${(memberVO2.pur_verify==0)?'selected':'' } >未驗證</option>
			<option value="1" ${(memberVO2.pur_verify==1)?'selected':'' } >已驗證</option>
			<option value="2" ${(memberVO2.pur_verify==2)?'selected':'' } >停權</option>
		</select></td>
	</tr>
	<tr>
		<td rowspan="2">
			<input type="hidden" name="action" value="updateStatus">
			<input type="hidden" name="mem_no" value="${memberVO2.mem_no}">
			<input type="hidden" name="requestURL" value="<%=request.getAttribute("requestURL")%>">
			<input type="hidden" name="whichPage"  value="<%=request.getAttribute("whichPage")%>"> 
			<button id="btn" type="submit" class="btn btn-primary">修改</button>
		</td>
	</tr>
</table>
	
</form>

<div id="outerdiv" style="position:fixed;top:0;left:0;background:rgba(0,0,0,0.7);z-index:2;width:100%;height:100%;display:none;">
    <div id="innerdiv" style="position:absolute;">
        <img id="bigimg" style="border:5px solid #fff;" src="" />
    </div>
</div>
<script src="<%=request.getContextPath()%>/back-end/js/jquery-1.12.4.min.js"></script>
<script src="<%=request.getContextPath()%>/back-end/js/bootstrap.min.js"></script>
<script>  
    $(function(){  
        $(".pimg").click(function(){  
            var _this = $(this);//將當前的pimg元素作為_this傳入函式  
            imgShow("#outerdiv", "#innerdiv", "#bigimg", _this);  
        });  
    });  

    function imgShow(outerdiv, innerdiv, bigimg, _this){  
        var src = _this.attr("src");//獲取當前點選的pimg元素中的src屬性  
        $(bigimg).attr("src", src);//設定#bigimg元素的src屬性  
      
            /*獲取當前點選圖片的真實大小，並顯示彈出層及大圖*/  
        $("<img/>").attr("src", src).load(function(){  
            var windowW = $(window).width();//獲取當前視窗寬度  
            var windowH = $(window).height();//獲取當前視窗高度  
            var realWidth = this.width;//獲取圖片真實寬度  
            var realHeight = this.height;//獲取圖片真實高度  
            var imgWidth, imgHeight;  
            var scale = 0.8;//縮放尺寸，當圖片真實寬度和高度大於視窗寬度和高度時進行縮放  
              
            if(realHeight>windowH*scale) {//判斷圖片高度  
                imgHeight = windowH*scale;//如大於視窗高度，圖片高度進行縮放  
                imgWidth = imgHeight/realHeight*realWidth;//等比例縮放寬度  
                if(imgWidth>windowW*scale) {//如寬度扔大於視窗寬度  
                    imgWidth = windowW*scale;//再對寬度進行縮放  
                }  
            } else if(realWidth>windowW*scale) {//如圖片高度合適，判斷圖片寬度  
                imgWidth = windowW*scale;//如大於視窗寬度，圖片寬度進行縮放  
                            imgHeight = imgWidth/realWidth*realHeight;//等比例縮放高度  
            } else {//如果圖片真實高度和寬度都符合要求，高寬不變  
                imgWidth = realWidth;  
                imgHeight = realHeight;  
            }  
                    $(bigimg).css("width",imgWidth);//以最終的寬度對圖片縮放  
              
            var w = (windowW-imgWidth)/2;//計算圖片與視窗左邊距  
            var h = (windowH-imgHeight)/2;//計算圖片與視窗上邊距  
            $(innerdiv).css({"top":h, "left":w});//設定#innerdiv的top和left屬性  
            $(outerdiv).fadeIn("fast");//淡入顯示#outerdiv及.pimg  
        });  
          
        $(outerdiv).click(function(){//再次點選淡出消失彈出層  
            $(this).fadeOut("fast");  
        });  
    }  
</script>
</body>
</html>