<%--
  Created by IntelliJ IDEA.
  User: debbie
  Date: 14/12/2020
  Time: 19:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width">
    <title>Register</title>
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
            padding: 12px;
            color: white;
            text-decoration: none;
            font-size: 17px;
        }
        .active {
            background-color: #6CCCBF;
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
    </style>

</head>
<body>
<div class="navbar">
    <a class="active" href="https://cmdmcpharmacywebsite.herokuapp.com/home"><i class="fa fa-fw fa-home"></i> Home</a>
    <div class="dropdown">
        <button class= "dropbtn" onclick="redirectBrowse()"><i class="fa fa-fw fa-search"></i>Browse<i class="fa fa-caret-down"></i></button>
        <div class="dropdown-content">
            <a href="#">Cold and Flu</a>
            <a href="#">Skincare</a>
            <a href="#">Headaches and Pain Relief</a>
            <a href="#">Digestion</a>
            <a href="#">Allergy</a>
            <a href="#">First Aid</a>
        </div>
    </div>
    <a href="https://phabpharmacy.herokuapp.com/login"><i class="fa fa-fw fa-user"></i> Login</a>
    <a class="active" href="https://phabpharmacy.herokuapp.com/register"><i class="fa fa-fw fa-user-plus"></i> Register</a>
</div>

<h1><center>Register</center></h1>
<p> Register below. If you already have an account, <a href="https://phabpharmacy.herokuapp.com/login"> login here.</a>
    <form name="registerForm">
        <input type="text" size="30" class="form-control" name="fname" placeholder="First Name*"><br>
        <input type="text" size="30" class="form-control" name="lname" placeholder="Last Name*"><br>
        <input type="text" size="30" class="form-control" name="user" placeholder="Username*"><br>
        <input type="text" size="30" class="form-control" name="email" placeholder="Email Address*"><br>
        <input type="text" size="30" class="form-control" name="pass" placeholder="Password*"><br>
        <input type="text" size="30" class="form-control" name="verifyPass" placeholder="Verify Password*"><br>
        <p><input type="button" value="Submit" onClick="display();"></p>
    </form>
<p id="demo"></p>
</body>
</html>

