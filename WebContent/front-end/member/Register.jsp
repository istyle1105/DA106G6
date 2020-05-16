<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>

<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Martine</title>
    <link href="https://gitcdn.github.io/bootstrap-toggle/2.2.2/css/bootstrap-toggle.min.css" rel="stylesheet">
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


    <script src="https://kit.fontawesome.com/74b3702900.js" crossorigin="anonymous"></script>
    
    <style>
    .main_menu{
    	background-color:#F0F0F0;
    }

    </style>
</head>

<body>
   <!--::header part start::-->
   <header class="main_menu">
        <div class="main_menu_iner">
            <div class="container">
                <div class="row align-items-center ">
                    <div class="col-lg-12">
                        <nav class="navbar navbar-expand-lg navbar-light justify-content-between">
                            
                            <button class="navbar-toggler" type="button" data-toggle="collapse"
                                data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                                aria-expanded="false" aria-label="Toggle navigation">
                                <span class="navbar-toggler-icon"></span>
                            </button>

                            <div class="collapse navbar-collapse main-menu-item justify-content-center"
                                id="navbarSupportedContent">
                                <a class="navbar-brand" href="index.html"> <img src="<%=request.getContextPath()%>/front-end/img/logo3.png" alt="logo"> </a>
                                <ul class="navbar-nav">
                                    <li class="nav-item dropdown">
                                        <a class="nav-link dropdown-toggle" href="blog.html" id="navbarDropdown"
                                            role="button" data-toggle="dropdown" aria-haspopup="true"
                                            aria-expanded="false">����</a>
                                        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                                            <a class="dropdown-item" href="blog.html">�̷s����</a>
                                            <a class="dropdown-item" href="single-blog.html">�@�ɮ���</a>
                                            <a class="dropdown-item" href="single-blog.html">�ײv</a>
                                            <a class="dropdown-item" href="single-blog.html">�Ѯ�</a>
                                        </div>
                                    </li>
                                    <li class="nav-item dropdown">
                                        <a class="nav-link dropdown-toggle" href="blog.html" id="navbarDropdown"
                                            role="button" data-toggle="dropdown" aria-haspopup="true"
                                            aria-expanded="false">��{</a>
                                        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                                            <a class="dropdown-item" href="blog.html">������{</a>
                                            <a class="dropdown-item" href="single-blog.html">�ΥD�M��</a>
                                            <a class="dropdown-item" href="single-blog.html">�έ��M��</a>
                                        </div>
                                    </li>
                                    <li class="nav-item dropdown">
                                        <a class="nav-link dropdown-toggle" href="blog.html" id="navbarDropdown"
                                            role="button" data-toggle="dropdown" aria-haspopup="true"
                                            aria-expanded="false">�N��</a>
                                        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                                            <a class="dropdown-item" href="blog.html">�N�ʭ���</a>
                                            <a class="dropdown-item" href="single-blog.html">�ݨD�N��</a>
                                            <a class="dropdown-item" href="single-blog.html">���ѥN��</a>
                                        </div>
                                    </li>
                                    <li class="nav-item dropdown">
                                        <a class="nav-link dropdown-toggle" href="blog.html" id="navbarDropdown"
                                            role="button" data-toggle="dropdown" aria-haspopup="true"
                                            aria-expanded="false">�ӫ�</a>
                                        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                                            <a class="dropdown-item" href="blog.html">�ӫ�����</a>
                                            <a class="dropdown-item" href="single-blog.html">�q��d��</a>
                                        </div>
                                    </li>


                                    <li class="nav-item dropdown">
                                        <a class="nav-link dropdown-toggle" href="blog.html" id="navbarDropdown"
                                            role="button" data-toggle="dropdown" aria-haspopup="true"
                                            aria-expanded="false">��x</a>
                                        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                                            <a class="dropdown-item" href="blog.html">��x����</a>
                                            <a class="dropdown-item" href="single-blog.html">�ڪ���x</a>
                                        </div>
                                    </li>
                                    <li class="nav-item dropdown">
                                        <a class="nav-link dropdown-toggle" href="blog.html" id="navbarDropdown"
                                            role="button" data-toggle="dropdown" aria-haspopup="true"
                                            aria-expanded="false">�|������</a>
                                        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                                            <a class="dropdown-item" href="blog.html">�|�����</a>
                                            <a class="dropdown-item" href="single-blog.html">�|���ӶD</a>
                                            <a class="dropdown-item" href="single-blog.html">�|���q��</a>
                                            <a class="dropdown-item" href="single-blog.html">�|���n��</a>
                                            <a class="dropdown-item" href="single-blog.html">�|�����]</a>
                                        </div>
                                    </li>
                                    <li class="nav-item btnLogin">
                                        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModalCenter">�n�J</button>
                                    </li>
                                    <li class="nav-item btnLogin" style="margin-left: 5px">
                                        <button type="button" class="btn btn-primary">���U</button>
                                    </li>
                                    
                                   <!--  <li class="nav-item login">
                                        <a class="nav-link signin" href="contact.html">�n�J</a>
                                    </li>
                                    <li class="nav-item login">
                                        <a class="nav-link signup" href="contact.html">���U</a>
                                    </li> -->
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


<!-- Modal -->
    <div class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered" role="document">
            <form>
                <div class="modal-content" style="width: 400px">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalCenterTitle">�|���n�J</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>           

                    
                    <div class="login">
                        <div class="form-group">
                            <label for="inputID">�b��</label>
                            <input type="text" class="form-control" id="inputID" aria-describedby="emailHelp" placeholder="Enter MemberID">
                        </div>
                        <div class="form-group">
                            <label for="inputPassword1">�K�X</label>
                            <input type="password" class="form-control" id="inputPassword1" placeholder="Password">
                        </div>
                        <!-- <div class="form-group form-check">
                            <input type="checkbox" class="form-check-input" id="exampleCheck1">
                            <label class="form-check-label" for="exampleCheck1">Check me out</label>
                        </div> -->
                        <div class="modal-footer">
                            <button type="submit" class="btn btn-primary">�n�J</button>
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">����</button>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>

<div id="AddMem">
	<jsp:include page="addMember.jsp" flush="true" />
</div>

<div class="custom-control custom-switch">
  <input type="checkbox" class="custom-control-input " id="customSwitch1">
  <label class="custom-control-label" for="customSwitch1">Toggle this switch element</label>
</div>




    <!-- footer part start-->
    <footer class="footer-area navbar-fixed-bottom">
        <div class="container-fluid">
            <div class="row justify-content-center">
                <div class="col-lg-12">
                    <div class="copyright_part_text text-center">
                        <p class="footer-text m-0"><!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
Copyright &copy;<script>document.write(new Date().getFullYear());</script><i class="ti-heart" aria-hidden="true"></i> by �굦�|DA106 G6
                        </p>
                    </div>
                </div>
            </div>
        </div>
    </footer>
    <!-- footer part end-->

    <!-- jquery plugins here-->
    <script src="https://gitcdn.github.io/bootstrap-toggle/2.2.2/js/bootstrap-toggle.min.js"></script>
    <script src="<%=request.getContextPath()%>/front-end/js/jquery-1.12.1.min.js"></script>
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
</body>

</html>