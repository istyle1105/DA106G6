<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.spot.model.*"%>
<%@ page import="com.member.model.*"%>
<%
	String mem_no=(String)session.getAttribute("mem_no");
	MemberService Membersvc=new MemberService();
	MemberVO memberVO=null;
	memberVO=Membersvc.getOneMember(mem_no);
	pageContext.setAttribute("memberVO", memberVO);	
%> 
<%
  SpotVO spotVO = (SpotVO) request.getAttribute("spotVO");
%>
<!DOCTYPE html>
<html>
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
      /* Always set the map height explicitly to define the size of the div
       * element that contains the map. */
      #map {
        height: 90%;
        width: 70%;
        margin-left: 25%;
      }
      /* Optional: Makes the sample page fill the window. */
      html, body {
        height: 100%;
        margin: 0;
        padding: 0;
      }
      #floating-panel {
        position: absolute;
        top: 10px;
        left: 58%;
        z-index: 5;
        background-color: #fff;
        padding: 5px;
        border: 1px solid #999;
        text-align: center;
        font-family: 'Roboto','sans-serif';
        line-height: 30px;
        padding-left: 10px;
      }
      #floating-panel {
        margin-left: -52px;
        display: none;
      }
      #left{
        width: 30%;
        margin:0;
        height: 90%;
        float: left;/*使DIV不會占一整行*/
        /* border: 1px solid #999; */
      }
      #show{
        max-width: 100%;
      }
      .tourRow{
        position: relative;
        background-color:rgb(215, 245, 255);
      }
      .delete{
        position: absolute;
        right:0;
        top:0;
        cursor: pointer;
        background-color: rgb(250, 198, 207);
        opacity: 0.5;
      }
      .noShow{
        display: none;
      }
      #output>img{
        width:300px;
      }
      #output{
        margin-left:5%;
      }

    </style>
    <script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
  </head>
  <body>
<%@ include file="/front-end/TopHeader.file" %>

  
    <div id="left" >
      <!-- <button id="route" onclick="route()">刷新資訊</button>
      <button id="reset" onclick="reset()">清空行程</button>
      <button id="saveData">儲存資料</button> -->
      <h4><b>新增景點</b><h4><br>
      快速導向  國家:
      <select id="country">
        <option value=""></option>
        <option value="0">日本</option>
        <option value="1">美國</option>
        <option value="2">澳洲</option>
      </select>

      <!-- <button id="newspot">開啟景點新增</button> -->
      <br>
      <!-- 請輸入開始時間:<br>
      年月日:<input type="text" id="date"><br>
      <input type="number" max="23" min="0" id="hour"/>時
      <input type="number" max="59" min="0" id="mins"/>分
      <button id="setTime" onclick="start()">設定</button> -->
      <div id="show">
        <b>請以右鍵點擊地圖來新增景點</b>
        <c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
        <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/spot/spot.do" name="form1" enctype="multipart/form-data">
          <table>
            <tr>
              <td>景點名稱:</td>
              <td><input type="TEXT" name="spot_name" id="spot_name" size="45" 
              value="<%= (spotVO==null)? "" : spotVO.getSpot_name()%>" /></td>
            </tr>
            <tr>
              <td>描述:</td>
              <td><input type="TEXT" name="spot_intro" id="spot_intro" size="45"
               value="<%= (spotVO==null)? "" : spotVO.getSpot_intro()%>" /></td>
            </tr>
            <tr>
              <td>地址:</td>
              <td><input type="hidden"  name="address" id="address" size="45"
              value="<%= (spotVO==null)? "" : spotVO.getAddress()%>" />
              <span id="address2"><%= (spotVO==null)? "" : spotVO.getAddress()%></span></td>
            </tr>
            <tr>
              <td>緯度:</td>
              <td><input type="hidden"  name="lat" id="lat" size="45"
               value="<%= (spotVO==null)? "" : spotVO.getLat()%>"/>
               <span id="lat2"><%= (spotVO==null)? "" : spotVO.getLat()%></span></td>
            </tr>
            <tr>
              <td>經度:</td>
              <td><input type="hidden"  name="lng" id="lng" size="45" 
              value="<%= (spotVO==null)? "" : spotVO.getLng()%>" />
              <span id="lng2"><%= (spotVO==null)? "" : spotVO.getLng()%></span></td>
            </tr>
            <tr><td>類型</td>
              <td>
                  <select size="1" name="spot_type">
                    <option value="0" <%= (spotVO!=null&&spotVO.getSpot_type()==0)? "selected" :""%>>景點</option>
                    <option value="1" <%= (spotVO!=null&&spotVO.getSpot_type()==1)? "selected" :""%>>飯店</option>
                    <option value="2" <%= (spotVO!=null&&spotVO.getSpot_type()==2)? "selected" :""%>>美食</option>
                    <!-- <option value="3">購物</option> -->
                  </select>
              </td>
            </tr>
            <tr>
            <!-- 上傳圖片檔案 -->
              <td>景點圖片:</td>
              <td><input type="file"  name="spot_pic"  onchange="loadFile(event)"></td>
            </tr>
          
          </table>
          
          
          
          <input type="hidden" name="action" value="insert">
          <input type="submit" value="新增景點"></FORM>
          <div id="output" style="width:400px;"></div>
      </div>
      
    </div>
    <div id="floating-panel">
      <button id="drop" onclick="drop()">顯示附近景點</button>
     
        <input type="radio" value="0" name="spot_type" class="spot_type">景點
        <input type="radio" value="1" name="spot_type" class="spot_type">飯店
        <input type="radio" value="2" name="spot_type" class="spot_type">美食
        <!-- <input type="radio" value="3">購物 -->
      


    </div>
    <div id="map"></div>
<%@ include file="/front-end/EndFooter.file" %>
    <script>

      // If you're adding a number of markers, you may want to drop them on the map
      // consecutively rather than all at once. This example shows how to use
      // window.setTimeout() to space your markers' animation.
       
      var datalist =[{address:"〒530-0047 大阪府大阪市北区西天満５丁目３−25",spot_intro:"第一次住宿六天商業飯店真的很不錯、早餐也很不錯下次如有機會會再來住宿。",lng:"135.5090408",spot_id:"S0016",spot_name:"東橫INN 大阪梅田東",lat:34.6990534,spot_type:1,spot_status:0},{address:"〒540-0011 大阪府大阪市中央区農人橋１丁目１−27",spot_intro:"這間 15 層樓高的休閒飯店坐落於中央區，步行至谷町四丁目地鐵站只要 3 分鐘，距離建於 16 世紀的大阪城，以及心齋橋購物及娛樂區皆為 2 公里。",lng:135.5165058,spot_id:"S0017",spot_name:"大阪谷町四丁目站前APA別墅飯店",lat:34.681042,spot_type:1,spot_status:0},{address:"〒552-0022 大阪府大阪市港区海岸通１丁目５−15",spot_intro:"附近有購物區和觀光景點 · 適合開車前往 · 靠近大眾運輸站",lng:135.4208131,spot_id:"S0018",spot_name:"Hotel Seagull Tempozan Osaka",lat:34.6619875,spot_type:1,spot_status:0},{address:"〒556-0002 大阪府大阪市浪速区恵美須東１丁目１８−6",spot_intro:"這座地標以霓虹燈聞名，有著開放大眾參觀的觀景台。",lng:"135.4909408",spot_id:"S0010",spot_name:"通天閣",lat:"34.6388001",spot_type:0,spot_status:0}];
      //資料送進來後依類型分成三堆
      var spotlist = [];
      var foodlist = [];
      var hotellist = [];
      var countrylist = [{center:{lat:34.8998105,lng:135.7049975},zoom:8},  //日本
                          {center:{lat:36.9078314,lng:-105.6855766},zoom:5}, //美國
                          {center:{lat:-29.1031131,lng:128.0774124},zoom:5}];//澳洲
      
      
      //  [//要從資料庫拉進來的緯經度
      //   {lat: 32.039237, lng: 130.8350462},
      //   {lat: 31.8849629, lng:130.9797779},
      //   {lat: 31.497, lng: 130.396},
      //   {lat: 31.517, lng: 130.394},
      //   {lat: 31.617, lng: 130.394},
      //   {lat: 31.567, lng: 130.394},
      //   {lat: 31.519, lng: 130.394}
      // ];

      var markers = [];//保管已經放置的地圖標記
      var map;//放置地圖的本體
      var mapCenter = {//地圖中心
          zoom: 11,
          center: {lat: 34.6777642, lng: 135.4160249},
          disableDefaultUI: true//將地圖預設功能關閉
        };
      var directionsService;
      var directionsDisplay;
      var myRoutes =[];//儲存所有路線點物件
      var timer = new Date(); //設定一個時間物件用來掌控時間
      var stayTime = [];//保存停留時間的陣列
      var startTime = [];//保存開始時間的的陣列,用於資料庫新增的欄位
      var isOff = true;//新增景點功能的開關
      var geocoder;//緯經度轉換服務

      

      function initMap() {
        map = new google.maps.Map(document.getElementById('map'),mapCenter);
        geocoder = new google.maps.Geocoder();
        

        directionsService = new google.maps.DirectionsService();//路線查詢用API
        directionsDisplay = new google.maps.DirectionsRenderer(//繪圖用API
         /* {suppressMarkers:true}*/);//關閉繪圖時產生的Marker
        // 放置路線圖層
          directionsDisplay.setMap(map);

        //整包資料分三種類存放
          spotlist = datalist.filter(d=>d.spot_type==0);
          foodlist = datalist.filter(f=>f.spot_type==1);
          hotellist = datalist.filter(h=>h.spot_type==2);
          console.log(spotlist);
          console.log(foodlist);
          console.log(hotellist);
          newspot();
      }

      function newspot(event){

        map.addListener('rightclick',function(event){
          var lat = event.latLng.lat();
          var lng = event.latLng.lng();

        // alert(lat+"&"+lng);
        // console.log(lat+""+lng);
          $("#lat").val(lat);
          $("#lng").val(lng);
          $("#lat2").text(lat);
          $("#lng2").text(lng);


          // 傳入 latLng 資訊至 geocoder.geocode
          geocoder.geocode({'latLng': new google.maps.LatLng(lat, lng) }, function(results, status) {
            if (status === google.maps.GeocoderStatus.OK) {
              // 如果有資料就會回傳
              if (results) {
                console.log(results);
                $("#address").val(results[0].formatted_address);//內建已經格式好的地址
                $("#address2").text(results[0].formatted_address);//內建已經格式好的地址
              }
            }
            // 經緯度資訊錯誤
            else {
              alert("Reverse Geocoding failed because: " + status);
            }
          });
          var image = {
                  url: '<%=request.getContextPath()%>/front-end/img/icon/animat-rocket-color.gif',
                  // This marker is 20 pixels wide by 32 pixels high.
                  scaledSize: new google.maps.Size(100, 100),
//                   // The origin for this image is (0, 0).
                  origin: new google.maps.Point(0, 0),
//                   // The anchor for this image is the base of the flagpole at (0, 32).
                  anchor: new google.maps.Point(50,100)
                };

        var marker=new google.maps.Marker({
            position: new google.maps.LatLng(event.latLng.lat(),event.latLng.lng()),
            map: map,
            animation: google.maps.Animation.DROP,
            draggable: true,
            icon:image
          })
       	  clearMarkers();
          markers.push(marker);
        
        });
        
        // alert("功能已開啟請以右鍵點擊地圖新增景點");
        // isOff = false;
        // $(this).text("關閉新增景點");


      }
      


      function toLatLng(list){//轉換成緯經度地圖物件
        var latlng = new google.maps.LatLng(list.lat,list.lng);
        return latlng;
      }

      function setRequest(list){
        var request;
        if (list.length<2){
          $("#show").text("請選取航點"); 

          //在第一次加入景點時就會進行導航所以先在控制面板上秀出景點控制
          $("#show").append("<div class='tourRow'>"+showTime(timer)
          +"  停留<input type='number' min='0' style='width:35px;' class='timecount'/>分<span class='delete' title='刪除'>X</span><p class='noShow'>"+0+"</p><br>"
          +myRoutes[0].spot_name+"</div>");
          return;
        } 
        if (list.length===2){
          request = {
            origin: toLatLng(list[0]),
            // waypoints:[{location:{lat:31.550629,lng:130.364586}}],
            destination: toLatLng(list[1]),
            travelMode: 'DRIVING'
          };
          return request;
        }
        if(list.length>2){

          var waypoints=[];
            for (var i = 1; i <list.length-1;i++){
              waypoint={location:toLatLng(list[i])};//組裝成物件
              waypoints.push(waypoint); 
            }
          request = {
            origin:  toLatLng(list[0]),
            waypoints:waypoints,
            destination: toLatLng(list[list.length-1]),
            travelMode: 'DRIVING'
          };
          return request;
        }
      }

      function saveStayTime(){
        var timecount = $("#show").find(".timecount");//取得所有標記好的類別
          stayTime = [];//先清空陣列
          for (var i =0;i<timecount.length; i++){
            var num = parseInt(timecount[i].value);//轉成數字格式
            stayTime.push(isNaN(num)?0:num);//將停留時間先保留起來,剔除NaN的狀態"
          }
          // console.log(stayTime);//測試陣列內容

      }


      function route(){
          
          let request = setRequest(myRoutes);//將保存的地點資料包裝成地圖請求格式

          // console.log($("#show").find(".timecount").eq(0).val());//測試中,都抓不到裡面的值
          // console.log(document.getElementsByClassName("timecount")[0].value);//將text改成number就抓的到值了...黑人問號???

          saveStayTime();//儲存目前格子裡的數字

         // 繪製路線
         directionsService.route(request, function (result, status) {
          if (status == 'OK') {

            // clearMarkers();//先清掉地圖上的圖標
              // 回傳路線上每個步驟的細節
              // var steps = result.routes[0].legs[0].steps;//取得路線的分段詳細資訊,為一個物件陣列
              // console.log(steps);
              // var time = result.routes[0].legs[0].duration;//取得路程總時間,legs為兩點間的資訊,兩點一個三點兩個依此類推.
              // console.log(time);
              // var distance = result.routes[0].legs[0].distance;//取得路程總距離
              // console.log(distance);
              // var routes = result.routes[0];
              // console.log(routes);
              // console.log(result);

              var legs =  result.routes[0].legs
              start();//將時間歸回起點

              $("#show").append("<div class='tourRow'>"+showTime(timer)
              +"   停留<input type='number' min='0' style='width:35px;' class='timecount'/>分<span class='delete' title='刪除'>X</span><p class='noShow'>"+0+"</p><br>"
              +myRoutes[0].spot_name+"</div>");
              for(var i = 0; i < legs.length; i++){
                var text = "<div style='text-align:right;'>耗時: "+legs[i].duration.text+" / 距離: "+legs[i].distance.text+"</div>";
                let min = timer.getMinutes()+legs[i].duration.value/60+stayTime[i];//取出時間物件的分鐘數來+路程分鐘數+停留時間

                timer.setMinutes(min);//分鐘數加進去超過60的部分會幫你進位  讚  但是不知道其他瀏覽器會怎麼樣

                saveStartTime(timer);//在變更後把當前時間存起來

                var spot_name = "<div class='tourRow'>"+showTime(timer)
                +"   停留<input type='number' min='0' style='width:35px;'class='timecount'/>分<span class='delete' title='刪除'>X</span><p class='noShow'>"+(i+1)+"</p><br>"
                +myRoutes[i+1].spot_name+"</div>";
                $("#show").append(text,spot_name);
              }


              // $("#show").append("路程時間與距離"+time.text+distance.text);

              directionsDisplay.setDirections(result);//畫圖方法,附帶focus效果
              // directionsDisplay.setPanel(document.getElementById('show'));//api的展示細節路徑

              for (var i =0;i<stayTime.length; i++){
                $(".timecount").eq(i).val(stayTime[i]);//把停留時間塞回格子裡
              }


          } else {
              console.log(status);
          }
        });
      }

      
      $("#show").on('click','.delete', function(){//註冊動態刪除行程列的點擊事件
        var index = $(this).next().text();
        myRoutes.splice(index,1);
        startTime.splice(index,1);
        // var last = stayTime[stayTime.length-1];
        route();//先刷新,因為會把現有的格子存起來
        stayTime.splice(index,1);

        for (var i =0;i<$(".timecount").length; i++){
                $(".timecount").eq(i).val(stayTime[i]);//把停留時間塞回格子裡
              }//因為在路徑規劃前會先把頁面上格子存起來,所以這裡馬上把變動更新到畫面上
      })

      
      $("body").on('click','.btn',function(event){
        var spot= JSON.parse($(this).next().text());//val()適用於input的部分,其他元件的內容用text來取
        // console.log(spot);
        myRoutes.push(spot);
        // console.log(myRoutes);
        route();
      });

      function addMarker(position){
        var marker=new google.maps.Marker({
            position: new google.maps.LatLng(position.lat,position.lng),
            map: map,
            animation: google.maps.Animation.DROP
          })
          
        markers.push(marker);
        var infowindow = new google.maps.InfoWindow({//資訊視窗的物件,將內容寫在content裡面
          content:"<button class='btn' >加入行程</button>"+ '<p style="display:none;">'+JSON.stringify(position)+"</p>"
        });
        marker.addListener('click', function(){//註冊事件監聽器,點擊時開啟視窗
          infowindow.open(map,marker);
        });
        return marker;
      }
      

      // }

      function drop() {
        clearMarkers();
        var type = $(".spot_type:checked").val();
        console.log(type);
        switch (type) {//依分類把對應的陣列放入
          case '0':
            setLatLngToAddMarker(spotlist);
            break;
          case '1':
            setLatLngToAddMarker(foodlist);
            break;
          case '2':
            setLatLngToAddMarker(hotellist);
            break;
          default://沒選時就傳整包原始資料
            setLatLngToAddMarker(datalist);
            break;
        }
      }

      function setLatLngToAddMarker(list){//將VO資料轉化為緯經度比較現在畫面緯經度來設置標記
        for (var i = 0; i < list.length; i++) {
          var latlng = new google.maps.LatLng(list[i].lat,list[i].lng);//轉化為緯經度物件
          if(map.getBounds().contains(latlng))//在範圍內的才加入圖標
          addMarkerWithTimeout(list[i], i * 120);
        }
      }


      function addMarkerWithTimeout(position, timeout) {
        window.setTimeout(function() {
          markers.push(addMarker(position));
        }, timeout);
      }

      function clearMarkers() {
        for (var i = 0; i < markers.length; i++) {
          markers[i].setMap(null);
        }
        markers = [];
      }

      function reset() {
        //用來儲存資料的陣列通通清空
        myRoutes = [];
        stayTime = [];
        startTime = [];
        $("#show").text("");
        directionsDisplay.setDirections();
      }

      function start(){
        timer = new Date();
        let yyMMdd = $("#date").val();
        let date = yyMMdd.split("-",3);
        console.log(date);
        let hr = $("#hour").val();
        let min = $("#mins").val();
        timer.setHours(hr);
        timer.setMinutes(min);
        timer.setFullYear(date[0]);
        timer.setMonth(parseInt(date[1])-1);
        timer.setDate(date[2]);
        $("#show").text(timer.toDateString()+"  "+showTime(timer));
        // $("#show").text(timer.toString());
        // startTime = [timer.getHours()+":"+timer.getMinutes()];//測試用
        startTime = [timer.getTime()];//清除紀錄的當前時間避免重複塞入,重置為只有一個起始時間的陣列
      }

      function showTime(date){
        return ""+date.getHours()+"時"+date.getMinutes()+"分  ";
      }

      function saveStartTime(date){
        startTime.push(date.getTime());//存值
        // startTime.push(date.getHours()+":"+date.getMinutes());//測試用方便解讀
        // console.log(startTime);
      }

      $("#country").on("change",function(){
        var index = $(this).val();
        console.log(index);
        if(index!=""){
          map.panTo(countrylist[index].center);
          map.setZoom(countrylist[index].zoom);
        }
      });



      

      $("#saveData").click(function(){//運用ajax技術將前端資料包成物件傳給後端存資料庫
        route();//刷新變更
        var spot_id = [];
        for (var i =0;i<myRoutes.length; i++){
          spot_id.push(myRoutes[i].spot_id);//抽出景點ID作成集合
        }
        // var laststay =parseInt($(".timecount").last().val());
        // stayTime.push(isNaN(laststay)?0:laststay);//保存最後一格停留時間
        // //測試資料正確與否
        console.log(spot_id);
        console.log(stayTime);
        console.log(startTime);
        $.ajax({
          url:"/DA106G6/tour_detail/tour_detail.do",//要用JSP釘好路徑
          type:"POST",
          data:{
            tour_id:"R0005",//這裡先寫死,改JSP時把他用EL插進來
            spot_id:spot_id,
            stay_time:stayTime,
            start_time:startTime,
            action:"InsertOrUpdate"//接應控制器的參數
          },
          success:function(){
            alert("資料儲存成功");
          }
        });
      });

      var loadFile = function(event) {
	     	var reader = new FileReader();
	     	reader.onload = function(){
		     	var output = document.getElementById('output');
	         	output.innerHTML = "<img id = 'preview'>";
		        document.getElementById("preview").src = reader.result;
	        };
	    	reader.readAsDataURL(event.target.files[0]);
    	 };
      
    </script>
    <script async defer
    src="https://maps.googleapis.com/maps/api/js?key=AIzaSyASNdeAY6bAWVEIR4putLF7cJ2wmZo990E&callback=initMap">
    </script>
  </body>
</html>