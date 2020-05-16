<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <title>Marker Animations With setTimeout()</title>
    <style>
      /* Always set the map height explicitly to define the size of the div
       * element that contains the map. */
      #map {
        height: 100%;
        width: 75%;
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
        left: 25%;
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
      }
      div>img{
      	width:120px;
      }
      #left{
      	width:20%;
      	margin:0
      	border:1px soild #000;
      }
    </style>
  </head>
  <body>
  	<div id="left"></div>
    <div id="floating-panel">
      <button id="drop" onclick="drop()">顯示附近景點</button>
    </div>
    <div id="map"></div>
    <script>

      // If you're adding a number of markers, you may want to drop them on the map
      // consecutively rather than all at once. This example shows how to use
      // window.setTimeout() to space your markers' animation.

      var datalist = ${result};//取得資料庫的景點
      
      var markers = [];//保管已經放置的地圖標記
      var map;//放置地圖的本體
      var mapCenter = {//地圖中心
          zoom: 13,
          center: {lat: 34.6777642, lng: 135.4160249}
        };

      function initMap() {
        map = new google.maps.Map(document.getElementById('map'),mapCenter);

        // for(var i=0; i<datalist.length;i++){
        //  addMarker(i);
        // }
        
      }

      function addMarker(data){
        var marker=new google.maps.Marker({
            position: new google.maps.LatLng(data.lat, data.lng),
            map: map,
            animation: google.maps.Animation.DROP
          })
        markers.push(marker);
        //地標的視窗內容編輯
        var info = data.spot_name+"<br><img src='<%=request.getContextPath()%>/DBGifReader4?spot_id="+data.spot_id+"'>"+
        		"<br><a href='<%=request.getContextPath()%>/front-end/tour_detail/addTour_detailWithMap.jsp?spot_id="
        				+data.spot_id+"&tour_id="+"' target='_blank'>加入行程</a>";
        
        var infowindow = new google.maps.InfoWindow({//資訊視窗的物件,將內容寫在content裡面
          content:info
        });
        marker.addListener('click', function(){//註冊事件監聽器,點擊時開啟視窗
          infowindow.open(map,marker);
        });
        return marker;
      }


      function drop() {
        clearMarkers();
        for (var i = 0; i < datalist.length; i++) {
          var latlng = new google.maps.LatLng(datalist[i].lat, datalist[i].lng);
          if(map.getBounds().contains(latlng))
          addMarkerWithTimeout(datalist[i], i * 100);

        }
      }

      function addMarkerWithTimeout(data, timeout) {
        window.setTimeout(function() {
          markers.push(addMarker(data));
        }, timeout);
      }

      function clearMarkers() {
        for (var i = 0; i < markers.length; i++) {
          markers[i].setMap(null);
        }
        markers = [];
      }
    </script>
    <script async defer
    src="https://maps.googleapis.com/maps/api/js?key=AIzaSyASNdeAY6bAWVEIR4putLF7cJ2wmZo990E&callback=initMap">
    </script>
  </body>
</html>