<%@ page import="java.util.Arrays" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: jason
  Date: 2/13/23
  Time: 12:37 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zxx" class="no-js">
<head>
    <meta http-equiv="cache-control" content="no-cache">
    <!-- Mobile Specific Meta -->
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Favicon-->
    <link rel="shortcut icon" href="views/user/img/fav.png">
    <!-- Author Meta -->
    <meta name="author" content="codepixer">
    <!-- Meta Description -->
    <meta name="description" content="">
    <!-- Meta Keyword -->
    <meta name="keywords" content="">
    <!-- meta character set -->
    <meta charset="UTF-8">
    <!-- Site Title -->
    <title>Book</title>

    <link href="https://fonts.googleapis.com/css?family=Poppins:100,200,400,300,500,600,700" rel="stylesheet">
    <!--
    CSS
    ============================================= -->
    <link rel="stylesheet" href="views/user/css/linearicons.css">
    <link rel="stylesheet" href="views/user/css/font-awesome.min.css">
    <link rel="stylesheet" href="views/user/css/bootstrap.css">
    <link rel="stylesheet" href="views/user/css/magnific-popup.css">
    <link rel="stylesheet" href="views/user/css/nice-select.css">
    <link rel="stylesheet" href="views/user/css/animate.min.css">
    <link rel="stylesheet" href="views/user/css/owl.carousel.css">
    <link rel="stylesheet" href="views/user/css/main.css">
</head>
<body>

<header id="header" id="home1">
    <div class="container">
        <div class="row align-items-center justify-content-between d-flex">
            <div id="logo">
                <a href="/"><img src="views/user/img/logo.png" alt="Logo" title="" width="30%"/></a>
            </div>
            <nav id="nav-menu-container">
                <ul class="nav-menu ">
                    <li class="menu-active"><a href="/">Home</a></li>
                    <li class="menu-active"><a href="/book">Book a room</a></li>
                    <li class="menu-active"><a href="/user/active">Reservations</a></li>

                            <% if (request.getCookies() != null && Arrays.stream(request.getCookies()).anyMatch(cookie -> cookie.getName().equalsIgnoreCase("remember_me"))) { %>
<%--                            <% if (session.getAttribute("user_id") != null) { %>--%>
                    <li class="menu-has-children"><a href="#">Logged in</a>
                        <ul>
                            <li><a href="/profile">Go to Profile</a></li>
                            <li><a href="/logout">Log Out</a></li>
                        </ul>
                    </li>
                    <% } else { %>
                    <li><a href="/login">Sign in</a></li>
                    <% } %>

                </ul>
            </nav><!-- #nav-menu-container -->
        </div>
    </div>
</header><!-- #header -->


<!-- start banner Area -->
<section class="banner-area" id="home">
    <div class="container">
        <div class="row fullscreen d-flex align-items-center justify-content-start">
            <div class="banner-content col-lg-7">
                <%--        <h5 class="text-white text-uppercase">sheengo Books</h5>--%>
                <h1 class="text">
                    Book a room <br>with just<br> a click
                </h1>
                <p class="text-white pt-20 pb-20">
                    Find the room where you can study and learn<br> with your friends in piece and quiet.
                </p>
                <a href="/book" class="primary-btn text-uppercase">Book a Room</a>
            </div>
            <div class="col-lg-5 banner-right">
                <img class="img-fluid" src="views/user/img/header-img1.jpg" alt="">
            </div>
        </div>
    </div>
</section>
<!-- End banner Area -->

<script src="views/user/js/vendor/jquery-2.2.4.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
        integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
        crossorigin="anonymous"></script>
<script src="views/user/js/vendor/bootstrap.min.js"></script>
<script type="text/javascript"
        src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBhOdIF3Y9382fqJYt5I_sswSrEw5eihAA"></script>
<script src="views/user/js/easing.min.js"></script>
<script src="views/user/js/hoverIntent.js"></script>
<script src="views/user/js/superfish.min.js"></script>
<script src="views/user/js/jquery.ajaxchimp.min.js"></script>
<script src="views/user/js/jquery.magnific-popup.min.js"></script>
<script src="views/user/js/owl.carousel.min.js"></script>
<script src="views/user/js/jquery.sticky.js"></script>
<script src="views/user/js/jquery.nice-select.min.js"></script>
<script src="views/user/js/parallax.min.js"></script>
<script src="views/user/js/waypoints.min.js"></script>
<script src="views/user/js/jquery.counterup.min.js"></script>
<script src="views/user/js/mail-script.js"></script>
<script src="views/user/js/main.js"></script>
</body>
</html>



