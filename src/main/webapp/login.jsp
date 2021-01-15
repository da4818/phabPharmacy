<%--
  Created by IntelliJ IDEA.
  User: debbie
  Date: 14/12/2020
  Time: 18:27
  To change this template use File | Settings | File Templates.
--%>
//UPDATED//
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
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
        .buttonStyle{
            background-color: #00B8C5;
            border: none;
            color: white;
            padding: 5px 25px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 16px;
            margin: 4px 0px;
            cursor: pointer;
        }
    </style>
</head>
<body>
<div class="navbar">
    <a href="https://phabpharmacy.herokuapp.com/home"><i class="fa fa-fw fa-home"></i>Home</a>
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
    <a style="background-color: #00B8C5"><i class="fa fa-fw fa-user"></i>Login</a> <%-- Tab coloured in blue to indicate it's the active tab --%>
    <a href="https://phabpharmacy.herokuapp.com/register"><i class="fa fa-fw fa-user-plus"></i>Register</a>
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

<h1><center>Login</center></h1>
<p> Login below. If you haven't got an account, <a href="https://phabpharmacy.herokuapp.com/register">register here.</a></p>

<form name="loginForm" action="login" method="post">
        <input type="text" size="30" class="form-control" name="email" placeholder="Email Address*"><br>
        <input type="text" size="30" class="form-control" name="pass" placeholder="Password*"><br>
        <input type="hidden" name="logOut" value="false">  <!--a hidden input tag is added to prevent nullPointer errors (in ServletLogin) -->
        <input type="submit" name="login" style="width: 222px;" class="buttonStyle" value="Submit">
</form>
<!-- doPost -->
<!-- Passes all checks -->
<h2 name="loginResponse">Welcome back, <!-- User's name -->!</h2>
<!-- Incomplete fields: email and/or password -->
<h2 name="loginResponse">Incomplete fields, please enter all the information.</h2>
<!-- If email and password don't match with an entry on the database -->
<h2 name="loginResponse">Wrong email or password, please try again.</h2>
<!-- doPost includes lines 172-181 -->
<script>
    function redirectBrowse(){
        window.location.href="https://phabpharmacy.herokuapp.com/browse"
    }
</script>
</body>
</html>






