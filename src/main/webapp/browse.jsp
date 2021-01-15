
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width">
    <title>Browse</title>
    <!-- Import Icon Library -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <!-- Creates navigation bar -->
    <style>
        body {font-family: Arial, Helvetica, sans-serif;}
        .navbar {
            width: 100%;
            background-color: #555;
            overflow: auto;
        }
        .navbar a {
            float: left;
            font-size: 16px;
            color: white;
            text-align: center;
            padding: 14px 16px;
            text-decoration: none;
        }
        @media screen and (max-width: 500px) {
            .navbar a {
                float: none;
                display: block;
            }
        }
        .dropdown {
            float: left;
            overflow: hidden;
        }
        .dropdown .dropbtn {
            font-size: 16px;
            border: none;
            outline: none;
            color: white;
            padding: 14px 16px;
            background-color: inherit;
            font-family: inherit;
            margin: 0;
        }
        .navbar a:hover, .dropdown:hover .dropbtn {
            background-color: #000;
        }
        .dropdown-content {
            display: none;
            position: absolute;
            background-color: #f9f9f9;
            min-width: 160px;
            box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
            z-index: 1;
        }
        .dropdown-content a {
            float: none;
            color: black;
            padding: 12px 16px;
            text-decoration: none;
            display: block;
            text-align: left;
        }
        .dropdown-content a:hover {
            background-color: #ddd;
        }
        .dropdown:hover .dropdown-content {
            display: block;
        }
        div.absolute {
            position: absolute;
            top: 140px;
            width: 200px;
            height: 30px;
        }
        div.relative{
            position: relative;
            width: 200px;
            height: 150px;
            display: inline;
            border: 1px solid black;
            padding: 15px;
            margin: 5px;
            float: left;
        }
        .currentUser{ <%-- This is the section to contain the logged in user's icon and name --%>
            position: relative;
            float: right;
            font-size: 16px;
            color: white;
            text-align: center;
            padding: 14px 16px 4px 16px;
            text-decoration: none;
        }
        .logOut{
            position: absolute;
            height: 10px;
            bottom: 0px;
            margin: 0px;
            border: none;
            background-color: transparent;
            border: none;
            font-size: 8px;
            color: white;
        }
        .logOutButton{
            background-color: transparent;
            font-size: 8px;
            color: white;
            margin: 0px;
            border: none;
        }
        .buttonStyle{
            background-color: #00B8C5;
            border: none;
            color: white;
            padding: 5px 25px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 16px;
            margin: 4px 2px;
            cursor: pointer;
        }
        #scrollBtn {
            display: none;
            position: fixed;
            bottom: 20px;
            right: 30px;
            z-index: 99;
            outline: none;
            background-color: #555;
            border: none;
            color: white;
            padding: 5px 25px;
            text-align: center;
            text-decoration: none;
            font-size: 16px;
            margin: 4px 2px;
            cursor: pointer;
        }
        #scrollBtn:hover {
            background-color: #00B8C5;
        }
        section{
            display: table-row;
        }
        .tooltip{
            position: relative;
            display: inline;
        }
        .tooltip .tooltiptext {
            visibility: hidden;
            width: 120px;
            background-color: black;
            color: #fff;
            text-align: center;
            border-radius: 6px;
            padding: 5px 0;
            position: absolute;
            z-index: 1;
        }
        .tooltip:hover .tooltiptext {
            visibility: visible;
        }
    </style>

</head>
<body>
<button onclick="topFunction()" id="scrollBtn" title="Go to top">Top</button>
<div class="navbar">
    <a href="https://phabpharmacy.herokuapp.com/home"><i class="fa fa-fw fa-home"></i>Home</a>
    <div class="dropdown">
        <button class= "dropbtn" style="background-color: #00B8C5;"><i class="fa fa-fw fa-search"></i>Browse<i class="fa fa-caret-down"></i></button>
        <div class="dropdown-content"> <%-- Tab coloured in blue to indicate it's the active tab --%>
            <a href="https://phabpharmacy.herokuapp.com/browse#cold_and_flu">Cold and Flu</a>
            <a href="https://phabpharmacy.herokuapp.com/browse#skincare">Skincare</a>
            <a href="https://phabpharmacy.herokuapp.com/browse#headaches_and_pain_relief">Headaches and Pain Relief</a>
            <a href="https://phabpharmacy.herokuapp.com/browse#digestion">Digestion</a>
            <a href="https://phabpharmacy.herokuapp.com/browse#allergy">Allergy</a>
            <a href="https://phabpharmacy.herokuapp.com/browse#first_aid">First Aid</a>
        </div>
    </div>
    <a href="https://phabpharmacy.herokuapp.com/login"><i class="fa fa-fw fa-user"></i>Login</a>
    <a href="https://phabpharmacy.herokuapp.com/register"><i class="fa fa-fw fa-user-plus"></i>Register</a>
    <a href="https://phabpharmacy.herokuapp.com/basket" style="width: 35px;" class="fa fa-fw fa-shopping-basket"><b id="basket"></b></a>
    <!-- If a user is logged in -->
    <form name="logOut" action="home" method="post"> <!-- A form is needed to process the log out button -->
        <div style="float: right;" class="currentUser">" + cUser.fname + "<i class="fa fa-fw fa-user"></i>
            <div class="logOut">
                <input class="logOutButton" type="submit" name="logOut" value="Log Out">
            </div>
        </div>
    </form>
    <!---------------------------->

    <!-- If no one is logged in -->
    <div class="currentUser"><i class="fa fa-fw fa-user"></i></div>
    <!---------------------------->
</div>
<!-- doPost -->
<!-- If the user isn't logged in and tries to add an item to their basket -->
<pre><script>window.onload(alert("Please ensure that you have created an account and logged in before adding items to your basket."));</script></pre>
<!-------------------------------------------------------------------------->

<!-- doGet and doPost (display same HTML) -->
<!-- For loop to display value of each item -->
<section>
    <h2 id="<!--header URL-->"><!--header name--></h2>
    <div class ="relative">
        <!-- If the product is limited to 1 item -->
        <label class="tooltip"><center>itemName<br>itemDescription</center><br>
            <span class="tooltiptext"><i>Limited to one per customer</i></span>
        </label><br>
        <!---------------------------------->
        
        <!-- If the product isn't limited to 1 item -->
        <label><center><!--item name--><br><!--item description--></center></label><br>
        <!-------------------------------------------->
        
        <label><center><!--item price--></center></label><br>
        <div class="absolute">
            <form action="browse" method="post">
                <input name="basketQuantity" type="number" size="5" min="1" max="">
                <input name="buttonNumber" type="hidden"value="">
                <input type="hidden" name="logOut" value="false">
                <input type="submit" class="buttonStyle" value="Add to Basket">
            </form>
        </div>
    </div>
</section>

<script>
    var mybutton = document.getElementById("scrollBtn");
    window.onscroll = function() {scrollFunction()};
    function scrollFunction() {
        if (document.body.scrollTop > 20 || document.documentElement.scrollTop > 20) {
            mybutton.style.display = "block";
        } else {
            mybutton.style.display = "none";
        }
    }
    function topFunction() {
        document.body.scrollTop = 0;
        document.documentElement.scrollTop = 0;
    }
</script>
</body>
</html>
