<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.spot.model.*"%>
<%@ page import="com.member.model.*"%>
<%
	String mem_no=(String)session.getAttribute("mem_no");
	MemberService Membersvc=new MemberService();
	MemberVO memberVO=null;
	memberVO=Membersvc.getOneMember(mem_no);
	pageContext.setAttribute("memberVO", memberVO);	
%>  
<%-- �����Ƚm�߱ĥ� Script ���g�k���� --%>

<%
  SpotVO spotVO = (SpotVO) request.getAttribute("spotVO"); //EmpServlet.java(Concroller), �s�Jreq��empVO����
  if(spotVO==null){
	  String spot_id = request.getParameter("spot_id");
	  SpotService spotSvc = new SpotService();
	  spotVO = spotSvc.getOneSpot(spot_id);
	  request.setAttribute("spotVO", spotVO);
  }
%>

<html>
<head>
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
  table#table-1 {
	background-color: #CCCCFF;
    border: 2px solid black;
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
  td>img{
  	width:300px;
  }
  .infoPic{
  	width:200px;
  }
</style>

<style>
  table {
	width: 600px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
</style>
<style>
      /* Always set the map height explicitly to define the size of the div
       * element that contains the map. */
      #map {
        height: 60%;
      }
      /* Optional: Makes the sample page fill the window. */
      html, body {
        height: 100%;
        margin: 0;
        padding: 0;
      }
    </style>
</head>
<body bgcolor='white'>
<%@ include file="/front-end/TopHeader.file" %>
<div id="content" class="container">
<!-- <table id="table-1"> -->
<!-- 	<tr><td> -->
<!-- 		 <h3>���I���</h3> -->
<!-- 	</td></tr> -->
<!-- </table> -->

<table class="table table-striped table-hover">
	<tr>
		<th>�Ϥ�</th>
<!-- 		<th>���I�s��</th> -->
		<th>���I����</th>
		<th>���I�W��</th>
		<th>²��</th>
		<th>�a�}</th>
<!-- 		<th>�W�U�[���A</th> -->
<!-- 		<th>�n��</th> -->
<!-- 		<th>�g��</th> -->
<!-- 		<th>���|</th> -->
	</tr>
	<tr>
			<td><img src='<%=request.getContextPath()%>/DBGifReader4?spot_id=${spotVO.spot_id}'></td>
<%-- 			<td>${spotVO.spot_id}</td> --%>
			<td>${spotType.get(spotVO.spot_type)}</td>
			<td>${spotVO.spot_name}</td>
			<td>${spotVO.spot_intro}</td>
			<td>${spotVO.address}</td> 
<%-- 			<td>${spotVO.spot_status}</td> --%>
<%-- 			<td>${spotVO.lat}</td> --%>
<%-- 			<td>${spotVO.lng}</td> --%>
<!-- 			<td> -->
<%-- 			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/spot/spot.do" style="margin-bottom: 0px;"> --%>
<!-- 			     <input type="submit" value="���|"> -->
<%-- 			     <input type="hidden" name="spot_id"  value="${spotVO.spot_id}"> --%>
<!-- 			     <input type="hidden" name="action" value="report"></FORM> -->
<!-- 			</td> -->
	</tr>
</table>
 <div id="map"></div>
    <script>

      function initMap() {
        var myLatLng = {lat: ${spotVO.lat}, lng: ${spotVO.lng}};

        var map = new google.maps.Map(document.getElementById('map'), {
          zoom: 14,
          center: myLatLng
        });

        var marker = new google.maps.Marker({
          position: myLatLng,
          map: map,
       	  icon: "<%=request.getContextPath()%>/front-end/img/icon/pokestop.png",
       	  animation: google.maps.Animation.DROP
        });
        
        var info = "<h4>${spotVO.spot_name}</h4>"+"<br><img class='infoPic' title='<%=spotVO.getSpot_intro().replace("\n", "").replace("\r", "")%>'  src='<%=request.getContextPath()%>/DBGifReader4?spot_id=${spotVO.spot_id}'>"+
		"<br><%=spotVO.getSpot_intro().replace("\n", "").replace("\r", "")%>";

        var infowindow = new google.maps.InfoWindow({//��T����������,�N���e�g�bcontent�̭�
          content:info,
          maxWidth: 250
        });
        infowindow.open(map,marker);
        marker.addListener('click', function(){//���U�ƥ��ť��,�I���ɶ}�ҵ���
          infowindow.open(map,marker);
        });
        
        
      }
    </script>
    <script async defer
    src="https://maps.googleapis.com/maps/api/js?key=AIzaSyASNdeAY6bAWVEIR4putLF7cJ2wmZo990E&callback=initMap">
    </script>
</div>
<%@ include file="/front-end/EndFooter.file" %>
</body>
</html>