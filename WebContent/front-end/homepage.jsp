<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.trip.model.*"%>
<%@ page import="com.member.model.*"%>
<%
	String mem_no=(String)session.getAttribute("mem_no");
	MemberService Membersvc=new MemberService();
	MemberVO memberVO=null;
	memberVO=Membersvc.getOneMember(mem_no);
	pageContext.setAttribute("memberVO", memberVO);		
	
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
<style>
body {
  min-height: 100vh;
  position: relative;
  margin: 0;
  padding-bottom: 100px; 
}
#footer {
	width:100%;
  position: absolute;
  bottom: 0;
}
.imgcgange{
cursor: pointer;
clip:rect(0px 345px 300px 0px);
}


</style>

</head>

<body>


   <!--::header part start::-->
   <header class="main_menu">
        <div class="main_menu_iner" style="border-bottom:5px solid black">
            <div class="container">
                <div class="row align-items-center ">
                    <div class="col-lg-12">
                        <nav class="navbar navbar-expand-lg navbar-light justify-content-between">
                            
<!--                             <button class="navbar-toggler" type="button" data-toggle="collapse" -->
<!--                                 data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" -->
<!--                                 aria-expanded="false" aria-label="Toggle navigation"> -->
<!--                                 <span class="navbar-toggler-icon">現在人數為</span> -->
<!--                             </button> -->

                            <div class="collapse navbar-collapse main-menu-item justify-content-center"
                                id="navbarSupportedContent">
                                <a class="navbar-brand" href="<%=request.getContextPath()%>/front-end/homepage.jsp"> <img src="<%=request.getContextPath()%>/front-end/img/logo3.png" alt="logo"> </a>
                                <ul class="navbar-nav">
                                    
                                    <li class="nav-item dropdown">
                                        <a class="nav-link dropdown-toggle" href="blog.html" id="navbarDropdown"
                                            role="button" data-toggle="dropdown" aria-haspopup="true"
                                            aria-expanded="false">
                                            行程
                                        </a>
                                        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                                            <a class="dropdown-item" href="<%=request.getContextPath()%>/front-end/trip/select_page.jsp">揪團首頁</a>
                                            <a class="dropdown-item" href="<%=request.getContextPath()%>/front-end/trip_list/listAllMyTrip_list.jsp">團員專區</a>
                                            <a class="dropdown-item" href="<%=request.getContextPath()%>/front-end/trip/listAllMyTrip.jsp">團主專區</a>
                                            <a class="dropdown-item" href="<%=request.getContextPath()%>/front-end/tour/listAllMyTour.jsp">我的行程</a>
                                        </div>
                                    </li>
                                    <li class="nav-item dropdown">
                                        <a class="nav-link dropdown-toggle" href="blog.html" id="navbarDropdown"
                                            role="button" data-toggle="dropdown" aria-haspopup="true"
                                            aria-expanded="false">
                                            代購
                                        </a>
                                        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                                            <a class="dropdown-item" href="<%=request.getContextPath()%>/front-end/demand/listAllDemand.jsp">代購首頁</a>
                                            <a class="dropdown-item" href="<%=request.getContextPath()%>/front-end/demand/listMyDemand.jsp">我的需求</a>
                                            <a class="dropdown-item" href="<%=request.getContextPath()%>/front-end/demand/listMyOffer.jsp">我的提供</a>
                                        </div>
                                    </li>
                                    <li class="nav-item dropdown">
                                        <a class="nav-link dropdown-toggle" href="blog.html" id="navbarDropdown"
                                            role="button" data-toggle="dropdown" aria-haspopup="true"
                                            aria-expanded="false">
                                            商城
                                        </a>
                                        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                                            <a class="dropdown-item" href="<%=request.getContextPath()%>/front-end/shop/shopHome.jsp">商城首頁</a>
                                            <a class="dropdown-item" href="<%=request.getContextPath()%>/front-end/shop/shopAllOrder.jsp">訂單查詢</a>
                                        </div>
                                    </li>


                                    <li class="nav-item dropdown">
                                        <a class="nav-link dropdown-toggle" href="blog.html" id="navbarDropdown"
                                            role="button" data-toggle="dropdown" aria-haspopup="true"
                                            aria-expanded="false">
                                            日誌
                                        </a>
                                        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                                            <a class="dropdown-item" href="<%=request.getContextPath()%>/front-end/log/logHome.jsp">日誌首頁</a>
                                            <a class="dropdown-item" href="<%=request.getContextPath()%>/front-end/log/myList.jsp">我的日誌</a>
                                        </div>
                                    </li>
                                    <li class="nav-item dropdown">
                                        <a class="nav-link dropdown-toggle" href="blog.html" id="navbarDropdown"
                                            role="button" data-toggle="dropdown" aria-haspopup="true"
                                            aria-expanded="false">會員中心</a>
                                        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                                            <a class="dropdown-item" href="<%=request.getContextPath()%>/front-end/member/listOneMemberSt.jsp">會員資料</a>
                                            <a class="dropdown-item" href="<%=request.getContextPath()%>/front-end/complaint/listAllComp.jsp">會員申訴</a>
                                            <a class="dropdown-item" href="<%=request.getContextPath()%>/front-end/notification/listMemNote.jsp">會員通知</a>
                                            <a class="dropdown-item" href="<%=request.getContextPath()%>/front-end/member/transRecord.jsp">會員錢包</a>
                                        </div>
                                    </li>

                                    
									<c:choose>
									    <c:when test="${not empty sessionScope.mem_no}">
										
										<li class="nav-item btnLogin" id="memshowphoto">
                                        <div id="divmem_photo" style="margin:5px 5px;">
                                        	<img height="50" onerror="javascript:this.src='<%=request.getContextPath()%>/NoData/no_photo.jpg'" src="data:image/png;base64,${memberVO.m_photo}">
                                        </div>
                                   		 </li>
                                    	<li class="nav-item btnLogin" id="mem_name">
                                        <div  style="margin:5px 5px;"><p><b style="color:black;">${memberVO.m_name}</b> 您好</p></div>
                                    	</li>
                                    	<li class="nav-item btnLogin" id="logout">
                                        <a href="<%=request.getContextPath()%>/front-end/member/member.do?action=logout"><button type="button" class="btn btn-primary" >登出</button></a>
                                    	</li>
									    </c:when>
									
									    <c:otherwise>
									    <li class="nav-item btnLogin" id="login">
                                        	<a href="<%=request.getContextPath()%>/front-end/member/Login.jsp?requestURI=<%=request.getRequestURI()%>">
                                        	<button type="button" class="btn btn-primary" >登入</button>
                                        </a>
                                    	</li>
                                    	<li class="nav-item btnLogin" style="margin-left: 5px" id="register">
                                        	<a href="<%=request.getContextPath()%>/front-end/member/addMember.jsp"><button type="button" class="btn btn-primary" >註冊</button></a>
                                    	</li>
									    </c:otherwise>
									</c:choose>                                    

                                </ul>
                            </div>



                            <!-- <div class="shopping_cart">
                                    <a href="cart.html">
                                        <div><i class="fas fa-shopping-cart"></i></div>
                                        <div>Cart <span>(0)</span></div>
                                    </a>
                            </div>
                            <a href="#" class="btn_1 d-none d-lg-block">book now</a> -->
                        </nav>
                    </div>
                </div>
            </div>
        </div>
    </header>
    <!-- Header part end-->


    <!-- banner part start-->
    <section class="banner_part">
        <div class="container">
            <div class="row align-items-center justify-content-center">
                <div class="col-lg-12">
                    <div class="banner_text text-center">
                        <div class="banner_text_iner">
                            <h1> Let's go Japan</h1>
                            <p>Let’s start your journey with us, your dream will come true</p>
                            <a href="<%=request.getContextPath()%>/front-end/trip/select_page.jsp" class="btn_1">Discover Now</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- banner part start-->


<div class="container">
        <div class="row justify-content-center text-center">
          <div class="col-md-7">
            <div class="heading-39101 mb-5">
              <span class="backdrop text-center">Journey</span>
              <span class="subtitle-39191">Journey</span>
              <h3>跟著專業旅行者旅行</h3>
            </div>
          </div>
        </div>

<jsp:useBean id="tourSvc" scope="page" class="com.tour.model.TourService"/>

<%	
	TripService tripSvc = new TripService(); 
	List<TripVO> list = tripSvc.getAll(new HashMap<String,String[]>());
	request.setAttribute("listTrips_ByCompositeQuery", list);
%>



<div class="row">
     
       <c:forEach var="tripVO" items="${listTrips_ByCompositeQuery}" begin="0" end="7">    
          <div class="col-lg-3 col-md-5 mb-4" data-aos="fade-up">
            <div class="listing-item" onclick="javascript:location.href='<%=request.getContextPath()%>/tour/tour.do?tour_id=${tripVO.tour_id}&trip_id=${tripVO.trip_id}&action=getOneTour_Show_Detail'">
              <div class="listing-image" >
                	<img src='<%=request.getContextPath()%>/DBGifReader4Tour?tour_id=${tripVO.tour_id}' 
                	alt="Image" class="img-fluid">
              </div>
              <div class="listing-item-content">
                <a class="px-3 mb-3 category bg-primary" href="<%=request.getContextPath()%>/tour/tour.do?tour_id=${tripVO.tour_id}&trip_id=${tripVO.trip_id}&action=getOneTour_Show_Detail">NT ${tripVO.trip_price}</a>
                <h2 class="mb-1"><a href="<%=request.getContextPath()%>/tour/tour.do?tour_id=${tripVO.tour_id}&trip_id=${tripVO.trip_id}&action=getOneTour_Show_Detail">${tourSvc.getOneTour(tripVO.tour_id).tour_name }</a></h2>
              </div>
            </div>
          </div>
		</c:forEach>
</div>

</div>

    <!--::industries start::-->
<!--     <section class="hotel_list section_padding"> -->
<!--         <div class="container"> -->

<!--             <div class="row justify-content-center text-center"> -->
<!--                 <div class="col-md-7"> -->
<!--                      <div class="heading-39101 mb-5 "> -->
<!--                         <span class="backdrop text-center">PUCHASING</span> -->
<!--                         <span class="subtitle-39191">PUCHASING</span> -->
<!--                         <h3>找人來幫你代購</h3> -->
<!--                  </div> -->
<!--             </div> -->
<!--             </div> -->
<!--             <div class="row justify-content-center text-center"> -->
<!--                 <div class="col-lg-3 col-md-5 mb-4"> -->
<!--                     <div class="single_ihotel_list"> -->
<!--                         <img width="260" height="195" src="img/purchasing/p01.jpg" alt=""> -->
<!--                         <div class="hotel_text_iner"> -->
<!--                             <h3> <a href="#"> Wakamoto 若元錠</a></h3> -->
<!--                             <p>1000錠</p> -->
<!--                             <h5>徵求<span>890元</span></h5> -->
<!--                         </div> -->
<!--                     </div> -->
<!--                 </div> -->
<!--                 <div class="col-lg-3 col-md-5 mb-4"> -->
<!--                     <div class="single_ihotel_list"> -->
<!--                         <img src="img/purchasing/p02.jpg" alt=""> -->
<!--                         <div class="hotel_text_iner"> -->
<!--                             <h3> <a href="#"> Hotel Polonia</a></h3> -->

<!--                             <p>London, United Kingdom</p> -->
<!--                             <h5>From <span>$500</span></h5> -->
<!--                         </div> -->
<!--                     </div> -->
<!--                 </div> -->
<!--                 <div class="col-lg-3 col-md-5 mb-4"> -->
<!--                     <div class="single_ihotel_list"> -->
<!--                         <img width="260" height="195" src="img/purchasing/p03.jpg" alt=""> -->
<!--                         <div class="hotel_text_iner"> -->
<!--                             <h3> <a href="#"> Hotel Polonia</a></h3> -->

<!--                             <p>London, United Kingdom</p> -->
<!--                             <h5>From <span>$500</span></h5> -->
<!--                         </div> -->
<!--                     </div> -->
<!--                 </div> -->
<!--                 <div class="col-lg-3 col-md-5 mb-4"> -->
<!--                     <div class="single_ihotel_list"> -->
<!--                         <img width="260" height="195"  src="img/purchasing/p04.jpg" alt=""> -->
<!--                         <div class="hotel_text_iner"> -->
<!--                             <h3> <a href="#"> Hotel Polonia</a></h3> -->

<!--                             <p>London, United Kingdom</p> -->
<!--                             <h5>From <span>$500</span></h5> -->
<!--                         </div> -->
<!--                     </div> -->
<!--                 </div> -->


<!--             </div> -->
<!--         </div> -->
<!--     </section> -->







    <div class="container" style="margin-top:10px ">
        <div class="row justify-content-center text-center">
            <div class="col-md-7">
                 <div class="heading-39101 mb-5 ">
                    <span class="backdrop text-center">PUCHASING</span>
                    <span class="subtitle-39191">PUCHASING</span>
                    <h3>找人來幫你代購</h3>
             </div>
        </div>
    </div>
</div>

<jsp:useBean id="demandSvc" scope="page" class="com.demand.model.DemandService"/> 
    <div class="products">
        <div class="container">

            <div class="row">
						<c:forEach var="demandVO" items="${demandSvc.available}" begin="0" end="3">    
                        <div class="col-lg-3 col-sm-6 product">
                            <div class="product_image">
                                <img class="imgcgange"  style="max-height:345px;"
                                src="data:image/png;base64,${demandVO.de_photo}"
                                 onclick="javascript:location.href='<%=request.getContextPath()%>/front-end/demand/demand.do?de_no=${demandVO.de_no}&action=listOneInFront'">
                            </div>
                            <div class="product_content">
                                <div class="product_title" style="text-align: center;">
                                    <a href="<%=request.getContextPath()%>/front-end/demand/demand.do?de_no=${demandVO.de_no}&action=listOneInFront">${demandVO.de_item_name}</a>
                                </div>
                                <div class="product_price" style="text-align: center;">NT$${demandVO.price}</div>
                            </div>
                        </div>
						</c:forEach>

                   
                        
                
            </div>
        </div>
    </div>







    <div class="container" style="margin-top:10px ">
        <div class="row justify-content-center text-center">
            <div class="col-md-7">
                 <div class="heading-39101 mb-5 ">
                    <span class="backdrop text-center">SHOPPING</span>
                    <span class="subtitle-39191">SHOPPING</span>
                    <h3>出國缺什麼</h3>
             </div>
        </div>
    </div>
</div>

<jsp:useBean id="productSvc" scope="page" class="com.product.model.ProductService"/> 
    <div class="products">
        <div class="container">

            <div class="row">
						<c:forEach var="productVO" items="${productSvc.all}" begin="0" end="7">    
                        <!-- Product -->
                        <div class="col-lg-3 col-sm-6 product">
                            <div class="product_image">
                                <img style="cursor: pointer;" width="345" height="345" src="<%=request.getContextPath()%>/product/productPic.do?p_id=${productVO.p_id}"
                                 onclick="javascript:location.href='<%=request.getContextPath()%>/product/product.do?action=detailview&p_id=${productVO.p_id}'">
                            </div>
<!--                             <div class="product_extra product_sale"><a href="">Sale</a></div> -->
                            <div class="product_content">
                                <div class="product_title" style="text-align: center;">
                                    <a href="<%=request.getContextPath()%>/product/product.do?action=detailview&p_id=${productVO.p_id}">${productVO.p_name}</a>
                                </div>
                                <div class="product_price" style="text-align: center;">NT$${productVO.p_pr}</div>
                            </div>
                        </div>
						</c:forEach>

                   
                        
                
            </div>
        </div>
    </div>



<!--     ::industries end:: -->
<!--     <div class="site-section"> -->

<!--       <div class="container"> -->
<!--         <div class="row justify-content-center text-center"> -->
<!--           <div class="col-md-10"> -->
<!--             <div class="heading-39101 mb-5"> -->
<!--               <span class="backdrop text-center">Blog</span> -->
<!--               <span class="subtitle-39191">Updates</span> -->
<!--               <h3>Our Blog</h3> -->
<!--             </div> -->
<!--           </div> -->
<!--         </div> -->

<!--         <div class="row"> -->
<!--           <div class="col-lg-4 col-md-6 mb-4"> -->
<!--             <div class="post-entry-1 h-100"> -->
<!--               <a href="single.html"> -->
<!--                 <img src="img/blog/img_1.jpg" alt="Image" -->
<!--                  class="img-fluid"> -->
<!--               </a> -->
<!--               <div class="post-entry-1-contents"> -->
                
<!--                 <h2><a href="single.html">Lorem ipsum dolor sit amet</a></h2> -->
<!--                 <span class="meta d-inline-block mb-3">July 17, 2019 <span class="mx-2">by</span> <a href="#">Admin</a></span> -->
<!--                 <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Dolores eos soluta, dolore harum molestias consectetur.</p> -->
<!--               </div> -->
<!--             </div> -->
<!--           </div> -->
<!--           <div class="col-lg-4 col-md-6 mb-4"> -->
<!--             <div class="post-entry-1 h-100"> -->
<!--               <a href="single.html"> -->
<!--                 <img src="img/blog/img_2.jpg" alt="Image" -->
<!--                  class="img-fluid"> -->
<!--               </a> -->
<!--               <div class="post-entry-1-contents"> -->
                
<!--                 <h2><a href="single.html">Lorem ipsum dolor sit amet</a></h2> -->
<!--                 <span class="meta d-inline-block mb-3">July 17, 2019 <span class="mx-2">by</span> <a href="#">Admin</a></span> -->
<!--                 <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Dolores eos soluta, dolore harum molestias consectetur.</p> -->
<!--               </div> -->
<!--             </div> -->
<!--           </div> -->

<!--           <div class="col-lg-4 col-md-6 mb-4"> -->
<!--             <div class="post-entry-1 h-100"> -->
<!--               <a href="single.html"> -->
<!--                 <img src="img/blog/img_3.jpg" alt="Image" -->
<!--                  class="img-fluid"> -->
<!--               </a> -->
<!--               <div class="post-entry-1-contents"> -->
                
<!--                 <h2><a href="single.html">Lorem ipsum dolor sit amet</a></h2> -->
<!--                 <span class="meta d-inline-block mb-3">July 17, 2019 <span class="mx-2">by</span> <a href="#">Admin</a></span> -->
<!--                 <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Dolores eos soluta, dolore harum molestias consectetur.</p> -->
<!--               </div> -->
<!--             </div> -->
<!--           </div> -->
<!--         </div> -->
<!--       </div> -->
<!--     </div> -->



    <!-- footer part start-->
    <footer id="footer" class="footer-area">
        <div class="container-fluid">
            <div class="row justify-content-center">
                <div class="col-lg-12">
                    <div class="copyright_part_text text-center">
                        <p class="footer-text m-0"><!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
Copyright &copy;<script>document.write(new Date().getFullYear());</script><i class="ti-heart" aria-hidden="true"></i> by 資策會DA106 G6
                        </p>
                    </div>
                </div>
            </div>
        </div>
    </footer>
    <!-- footer part end-->




<%--  <img onclick="location.href=''" src="<%=request.getContextPath()%>/front-end/img/message.png" alt="聊天室" class="messagesign"> --%>

<style type="text/css">
	.messagesign{
		position: fixed;
		height: 50px;
		width: auto;
		cursor: pointer;
		top:85%;
		left:95%;
		z-index:99999;
	}
</style> 









    <!-- jquery plugins here-->
    <script src="<%=request.getContextPath()%>/front-end/js/jquery-1.12.4.min.js"></script>
    <!-- popper js -->
    <script src="<%=request.getContextPath()%>/front-end/js/popper.min.js"></script>
    <!-- bootstrap js -->
    <script src="<%=request.getContextPath()%>/front-end/js/bootstrap.min.js"></script>
    <!-- magnific js -->
    <script src="<%=request.getContextPath()%>/front-end/js/jquery.magnific-popup.js"></script>
    <!-- swiper js -->
    <script src="<%=request.getContextPath()%>/front-end/js/owl.carousel.min.js"></script>
    <!-- masonry js -->
    <script src="<%=request.getContextPath()%>/front-end/js/masonry.pkgd.js"></script>
    <!-- masonry js -->
    <script src="<%=request.getContextPath()%>/front-end/js/jquery.nice-select.min.js"></script>
    <script src="<%=request.getContextPath()%>/front-end/js/gijgo.min.js"></script>
    <!-- contact js -->
    <script src="<%=request.getContextPath()%>/front-end/js/jquery.ajaxchimp.min.js"></script>
    <script src="<%=request.getContextPath()%>/front-end/js/jquery.form.js"></script>
    <script src="<%=request.getContextPath()%>/front-end/js/jquery.validate.min.js"></script>
    <script src="<%=request.getContextPath()%>/front-end/js/mail-script.js"></script>
    <script src="<%=request.getContextPath()%>/front-end/js/contact.js"></script>
    <!-- custom js -->
    <script src="<%=request.getContextPath()%>/front-end/js/custom.js"></script>
    <script src="<%=request.getContextPath()%>/front-end/js/sweetalert2.min.js"></script>
    <script>
//     var mem_no='${sessionScope.mem_no}';
//     console.log("mem_no"+mem_no);
//     if(mem_no==''){
//     	$("#logout").css("display","none");
//     	$("#mem_name").css("display","none");
//     	$("#memshowphoto").css("display","none");
//     }else{
//     	$("#login").css("display","none");
//     	$("#register").css("display","none");
//     }
    
    
    var state='${param.state}'
    if(state=='logout'){
    	 Swal.fire({
   		  title: '登出成功',
   		  icon: 'success',
   		  showConfirmButton: false,
   		  showCloseButton: true,
   		  timer: 1500
   		})
    }
    </script>


    
<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/chatroom/chatroom2.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/chatroom/italic.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/chatroom/material-design-iconic-font/css/material-design-iconic-font.min.css"/>



<div class="fabs">
  <div class="chat">
  	
  	
  	
  	<div class="chatPart">
	    <div class="chat_header">
	      <div class="chat_option">
	      <div class="header_img">
	        <img src="<%=request.getContextPath()%>/front-end/chatroom/img/cus_service.png"/>
	        </div>
	        <div >
	        <span id="chat_head" ><h3 style="line-height: 1.5;">客服人員</h3></span>
<!-- 	        <span class="agent">Agent</span> -->
<!-- 	        <span class="online">(Online)</span> -->
			</div>
	      </div>

	    </div>
	    <div class="chat_body chat_login">
	     <a id="chat_first_screen" class="fab"><i class="zmdi zmdi-arrow-right"></i></a>
	        <p>We make it simple and seamless for businesses and people to talk to each other. Ask us anything</p>
	    </div>
	    
	    
	    
	    
	<!--  對話框主體 -->    
	    
	    <div id="chat_converse" class="chat_conversion chat_converse">
	<!--                <a id="chat_second_screen" class="fab"><i class="zmdi zmdi-arrow-right"></i></a>  -->
		  <span class="receiver" style="display:hidden;"></span>
	      <span class="chat_msg_item chat_msg_item_admin">
	            <div class="chat_avatar">
	               <img src="http://res.cloudinary.com/dqvwa7vpe/image/upload/v1496415051/avatar_ma6vug.jpg"/>
	            </div>Hey there! Any question?</span>
	      <span class="chat_msg_item chat_msg_item_user">
	            Hello!</span>
	            <span class="status">20m ago</span>
	      <span class="chat_msg_item chat_msg_item_admin">
	            <div class="chat_avatar">
	               <img src="http://res.cloudinary.com/dqvwa7vpe/image/upload/v1496415051/avatar_ma6vug.jpg"/>
	            </div>Hey! Would you like to talk sales, support, or anyone?</span>
	      <span class="chat_msg_item chat_msg_item_user">
	            Lorem Ipsum is simply dummy text of the printing and typesetting industry.</span>
	             <span class="status2">Just now. Not seen yet</span>
	    </div>
	    
	<!--  對話框主體 -->





	    <div id="chat_body" class="chat_body">
	        <div class="chat_category">
	          <a id="chat_third_screen" class="fab"><i class="zmdi zmdi-arrow-right"></i></a>
	        <p>What would you like to talk about?</p>
	        <ul>
	          <li>Tech</li>
	          <li class="active">Sales</li>
	          <li >Pricing</li>
	          <li>other</li>
	        </ul>
	        </div>

	    </div>
	    <div id="chat_form" class="chat_converse chat_form">
	    <a id="chat_fourth_screen" class="fab"><i class="zmdi zmdi-arrow-right"></i></a>
	      <span class="chat_msg_item chat_msg_item_admin">
	            <div class="chat_avatar">
	               <img src="http://res.cloudinary.com/dqvwa7vpe/image/upload/v1496415051/avatar_ma6vug.jpg"/>
	            </div>Hey there! Any question?</span>
	      <span class="chat_msg_item chat_msg_item_user">
	            Hello!</span>
	            <span class="status">20m ago</span>
	      <span class="chat_msg_item chat_msg_item_admin">
	            <div class="chat_avatar">
	               <img src="http://res.cloudinary.com/dqvwa7vpe/image/upload/v1496415051/avatar_ma6vug.jpg"/>
	            </div>Agent typically replies in a few hours. Don't miss their reply.
	            <div>
	              <br>
	              <form class="get-notified">
	                  <label for="chat_log_email">Get notified by email</label>
	                  <input id="chat_log_email" placeholder="Enter your email"/>
	                  <i class="zmdi zmdi-chevron-right"></i>
	              </form>
	            </div></span>

	        <span class="chat_msg_item chat_msg_item_admin">
	            <div class="chat_avatar">
	               <img src="http://res.cloudinary.com/dqvwa7vpe/image/upload/v1496415051/avatar_ma6vug.jpg"/>
	            </div>Send message to agent.
	            <div>
	              <form class="message_form">
	                  <input placeholder="Your email"/>
	                  <input placeholder="Technical issue"/>
	                  <textarea rows="4" placeholder="Your message"></textarea>
	                  <button>Send</button> 
	              </form>

	        </div></span>   
	    </div>
	      <div id="chat_fullscreen" class="chat_conversion chat_converse">
	      <span class="chat_msg_item chat_msg_item_admin">
	            <div class="chat_avatar">
	               <img src="http://res.cloudinary.com/dqvwa7vpe/image/upload/v1496415051/avatar_ma6vug.jpg"/>
	            </div>Hey there! Any question?</span>
	      <span class="chat_msg_item chat_msg_item_user">
	            Hello!</span>
	            <div class="status">20m ago</div>
	      <span class="chat_msg_item chat_msg_item_admin">
	            <div class="chat_avatar">
	               <img src="http://res.cloudinary.com/dqvwa7vpe/image/upload/v1496415051/avatar_ma6vug.jpg"/>
	            </div>Hey! Would you like to talk sales, support, or anyone?</span>
	      <span class="chat_msg_item chat_msg_item_user">
	            Lorem Ipsum is simply dummy text of the printing and typesetting industry.</span>
	      <span class="chat_msg_item chat_msg_item_admin">
	            <div class="chat_avatar">
	               <img src="http://res.cloudinary.com/dqvwa7vpe/image/upload/v1496415051/avatar_ma6vug.jpg"/>
	             </div>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.</span>
	      <span class="chat_msg_item chat_msg_item_user">
	            Where can I get some?</span>
	      <span class="chat_msg_item chat_msg_item_admin">
	            <div class="chat_avatar">
	               <img src="http://res.cloudinary.com/dqvwa7vpe/image/upload/v1496415051/avatar_ma6vug.jpg"/>
	             </div>The standard chuck...</span>
	      <span class="chat_msg_item chat_msg_item_user">
	            There are many variations of passages of Lorem Ipsum available</span>
	            <div class="status2">Just now, Not seen yet</div>
	      <span class="chat_msg_item ">
	          <ul class="tags">
	            <li>Hats</li>
	            <li>T-Shirts</li>
	            <li>Pants</li>
	          </ul>
	      </span>
	    </div>
	    <div class="fab_field">
	      <a id="fab_send" class="fab"><i class="zmdi zmdi-mail-send"></i></a>
		      <div id="imgUpdate" class="fab">
	      		<label id="labelUpdatePic" for="inputUpdatePic">
		     		<input type="file" id="inputUpdatePic" hidden name="avatar" accept="image/gif, image/png, image/jpeg">
	     		</label>
	  		  </div>
	      <textarea id="chatSend" name="chat_message" placeholder="Send a message" class="chat_field chat_message"></textarea>
	    </div>
    </div>
  </div>
    <a id="prime" class="fab"><img src="<%=request.getContextPath()%>/front-end/img/message.png" ></a>
</div>

	<script src="<%=request.getContextPath()%>/front-end/chatroom/chatroom2.js"></script>



<script> 


$(function(){
        	
	
	<%
	// CustomerVO vo = (CustomerVO)session.getAttribute("customerVO");
		if(session.getAttribute("mem_no") == null){
			if(session.getAttribute("visitorId") == null){
				String visitorId = "vis" + (int)(Math.random() * 10000);
				session.setAttribute("visitorId", visitorId);
			}
		}
	
	 %> 
	        	
	var myPoint = "/CusSvcChat/";
	<c:choose>
		<c:when test="${not empty sessionScope.mem_no}">
			var userId = "${sessionScope.mem_no}";//改成直接輸入名字也可以，懶得改變數名
		</c:when >
		<c:otherwise>
			var userId = "${sessionScope.visitorId}";
		</c:otherwise>
	</c:choose>
	if(userId.length == 0)userId = "${visitorId}";
	
	var host = window.location.host;
	var path = window.location.pathname;
	var webCtx = path.substring(0, path.indexOf('/', 1));
	var webSocket;
	var randomN;
	var userId;
	var firstGetMsg = 1;
	connect();//老師註冊在body
				
	function getHistoryMsgs(userId,chatWith){
		obj = {
				"action" : "getHistoryMsgs",
				"sender": userId,
				"receiver" : chatWith
		}
		$.ajax({
			 type: "POST",
			 url: "<%=request.getContextPath()%>/SocketServlet",
			 data: obj,
			 dataType: "json",
			 success: function (data){
				 let html = '';
	
				 $.each(data, function(index, element){
					 obj = JSON.parse(element);
						if(obj.sender === userId){
							html += '<span class="chat_msg_item chat_msg_item_user">' +
							obj.message + '</span>';
						}else{
							if(obj.message.includes("http")){
								obj.message = '<a href="' + obj.message + '" >' + obj.message + '</a>';
							}
							html += '<span class="chat_msg_item chat_msg_item_admin">'+
					            '<div class="chat_avatar">'+
				                '<img src="<%=request.getContextPath()%>/front-end/chatroom/img/cus_service.png"/>'+
				            	'</div>' + obj.message + '</span>';
						}
				 });
				$("#chat_converse").empty();
				$("#chat_converse").append(html);
				$("#chat_converse").scrollTop($('#chat_converse').prop('scrollHeight'));
			 },
			 error:function(){
				 
			 }
		 });
	}
				
	var firstTime = 1;//第一次進來才需要用ajax讀取歷史訊息
	
	function connect(){
		var endPointURL = "ws://" + window.location.host + webCtx + myPoint + userId;
		
		webSocket = new WebSocket(endPointURL);
		
		
		webSocket.onopen = function(event){
			if(firstTime === 1 ){
				getHistoryMsgs(userId,"emp");
				firstTime = 0;
			}
								
		}
					
					
		//接收訊息
		webSocket.onmessage = function(event){
			
			var jsonObj = JSON.parse(event.data);
			let sender = jsonObj.sender;
			let text = jsonObj.message;
			let isPic = jsonObj.isPic;
			
			
			let html = '';
			if((sender.toString() === userId) || ((sender.includes("emp"))&&(userId.includes("emp")))){
				html = '<span class="chat_msg_item chat_msg_item_user">' +
	            		text + '</span>';
			}else{
				html = '<span class="chat_msg_item chat_msg_item_admin">'+
		            '<div class="chat_avatar">'+
	                '<img src="<%=request.getContextPath()%>/front-end/chatroom/img/cus_service.png"/>'+
	            	'</div>' + text + '</span>';
			}
			$("#chat_converse").append(html);
			$("#chat_converse").scrollTop($('#chat_converse').prop('scrollHeight'));
		}
			
		webSocket.onclose = function(event){
			//console.log("離線");
			//console.log(event);
		}
	}
				
	function sendMessage(message,isPic) {
		
		let receiver = $(".receiver").text();
		//只有會員(或訪客)在第一次送值的時候可能為空(員工無法主動發起對話)
		if(receiver === ''){
			receiver = "emp";
		}
		if(message === ""){
			$("#chatSend").focus();
		}else{
			let jsonObj = {
					"sender" : userId,//在send中，此人是sender
					"receiver" : receiver,
					"message" : message,
					"isPic" : isPic //判斷是否為圖片
			};
			
			console.log("jsonObj = ");
			console.log(jsonObj);
			let jsonStr = JSON.stringify(jsonObj);
			webSocket.send(jsonStr);
			$("#chatSend").val("");
			$("#chatSend").focus();
		}
	}
	
	$("body").on("click","#fab_send",function(){
		let message = $("#chatSend").val().trim();
		console.log("message"+message);
		sendMessage(message,0);
	});
	
	$("#inputUpdatePic").change(function(){
		let input = this;
		let message = '';
		//console.log(input.files);
		//console.log(input.files[0]);
		
		if(input.files && input.files[0]){
		    var reader = new FileReader();
	
		    reader.onload = function (e) {
	
		    	message =  e.target.result;
				sendMessage(message,1);
	
		    }
	
		    reader.readAsDataURL(input.files[0]);
	
		  }
	});
	
	//function disconnect
	$("body").on( "unload" ,function disconnect () {
		webSocket.close();
		document.getElementById('sendMessage').disabled = true;
		document.getElementById('connect').disabled = false;
		document.getElementById('disconnect').disabled = true;
	});
			
});			
			

    </script> 	
        	
        	


    

</body>

</html>