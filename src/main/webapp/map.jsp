<%--
  Created by IntelliJ IDEA.
  User: debbie
  Date: 15/01/2021
  Time: 12:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Find Items In-Store</title>
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
        .section{
          display: table-cell;
        }
        .images{
          display: table-row;
          float: right;           
          right: 15%;
        }
        div.box{
          position: absolute;
          top: 157px;
          left: 280px;
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
    </style>
        
</head>
<body>
<div class="navbar">
    <a><i class="fa fa-fw fa-home"></i>Home</a> 
    <div class="dropdown">
        <button name="Browse" style="cursor: pointer;" class= "dropbtn" onclick="redirectBrowse()"><i class="fa fa-fw fa-search"></i>Browse<i class="fa fa-caret-down"></i></button>
        <div class="dropdown-content">
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
    <a style="background-color: #00B8C5"><i class="fa fa-compass" aria-hidden="true"></i>In-Store</a> <%-- Tab coloured in blue to indicate it's the active tab --%>
    <a href="https://phabpharmacy.herokuapp.com/basket"name="Basket"><i style="width: 35px;" class="fa fa-fw fa-shopping-basket"><p style="display: inline; font-family: Arial; font-weight: bold;" id="basket"></p></i></a>
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

<h1>Find Items in Store</h1>
<p>Choose a category to see all of items in the section and their in-store (Paddington branch) location.</p>
<section>
    <div class="dropdown">
        <button class="buttonStyle dropbtn" style="width: 222px;">Categories</button>
        <div class="dropdown-content">
            <a id="cf" href="https://phabpharmacy.herokuapp.com/map/cold_and_flu"> Cold and Flu</a>
            <a id="s" href="https://phabpharmacy.herokuapp.com/map/skincare"> Skincare</a>
            <a id="hpr" href="https://phabpharmacy.herokuapp.com/map/headaches_and_pain_relief">Headaches and Pain Relief</a>
            <a id="d" href="https://phabpharmacy.herokuapp.com/map/digestion">Digestion</a>
            <a id="a" href="https://phabpharmacy.herokuapp.com/map/allergy">Allergy</a>
            <a id="fa" href="https://phabpharmacy.herokuapp.com/map/first_aid">First Aid</a>
        </div>
    </div>
</section>

<!-- Main -->
<h3 name="mapResponse"></h3>
<div class="box"></div>
<section style="float: right; margin-right: 15%;">
    <img class="images" src="https://bit.ly/main_map" alt="Paddington Store" width="316" height="400">
</section>

<!-- Cold and Flu -->
<section>
    <div class="box">
        <h3 name="mapResponse">Products in Cold and Flu</h3><p name="mapListResponse">
        </p><!-- Brand, name, amount --></div>
</section>
<section style="float: right; margin-right: 15%;">
    <img class="images" src="https://bit.ly/cold_flu_map" alt="Paddington Store" width="316" height="400">
</section>

<!-- Skincare -->
<section>
    <div class="box">
        <h3 name="mapResponse">Products in Skincare</h3><p name="mapListResponse">
    </p><!-- Brand, name, amount --></div>
</section>
<section style="float: right; margin-right: 15%;">
    <img class="images" src="https://bit.ly/skincare_map" alt="Paddington Store" width="316" height="400">
</section>


</body>
</html>
