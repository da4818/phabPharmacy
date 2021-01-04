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
        input, textarea{
            font-family: Arial, Helvetica, sans-serif;
            font-size: 16px;
            width: 26em; /* fallback for the next one, for browsers not recognizing ch */
            width: 40ch; /* sets the width to 40 times the width of the digit “0” */
        }
        textarea::placeholder {
            font-family: Arial, Helvetica, sans-serif;
        }
    </style>
</head>
<body>
<div class="navbar">
    <a href="https://phabpharmacywebsite.herokuapp.com/home"><i class="fa fa-fw fa-home"></i>Home</a>
    <div class="dropdown">
        <button style="cursor: pointer;" class= "dropbtn" onclick="redirectBrowse()"><i class="fa fa-fw fa-search"></i>Browse<i class="fa fa-caret-down"></i></button>
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
    <a style="background-color: #00B8C5"><i class="fa fa-fw fa-user-plus"></i>Register</a> <%-- Tab coloured in blue to indicate it's the active tab --%>
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
<h1>>Register</h1>
<p> Register below. If you already have an account, <a href="https://phabpharmacy.herokuapp.com/login"> login here.</a>
<form name="registerForm" action="register" method="post">
    <input type="text" size="30" class="form-control" name="fname" placeholder="First Name*"><br>
    <input type="text" size="30" class="form-control" name="lname" placeholder="Last Name*"><br>
    <input type="text" size="30" class="form-control" name="email" placeholder="Email Address*"><br>
    <input type="text" size="30" class="form-control" name="pass" placeholder="Password*"><br>
    <input type="text" size="30" class="form-control" name="verify_pass" placeholder="Verify Password*"><br>
    <h3>Order Information<br><b style="font-size: 15px;">Payment Information</b></h3>

    <input type="text" size="30" class="form-control" name="card_no" placeholder="Card Number*"><br>
    <input type="text" size="30" class="form-control" name="sort_code" placeholder="Sort Code*"><br>
    <input type="text" size="30" class="form-control" name="account_no" placeholder="Account Number*"><br>
    <input type="text" size="30" class="form-control" name="cvv" placeholder="CVV*"><br>

    <h3 style="font-size: 15px;">Shipping Information</h3>
    <textarea name="addresss" cols="30" rows="4" placeholder="Address"></textarea><br>
    <input type="text" size="30" class="form-control" name="postcode" placeholder="Postcode*"><br>
    <input type="text" size="30" class="form-control" name="phone_no" placeholder="Phone Number"><br>
    <input type="hidden" name="logOut" value="false"> <!--a hidden input tag is added to prevent nullPointer errors (in ServletRegister) -->
    <input type="submit" class="buttonStyle" value="Submit">
</form>

<!-- doPost response -->
<!-- Passes all checks -->
<h2>Successful registration. Welcome, <!--Newly registered user's name-->!</h2>
<!-- If the user logs in with an email that has an existing account -->
<h2>There is an existing account with the email entered. Please log in.</h2>
<!-- Incomplete fields: names, email, password (and verification), card details, postcode (full address and phone number are optional) -->
<h2>Incomplete fields, please enter all the information.</h2>
<!-- If the password and password verification don't match -->
<h2>Passwords don't match, please try again.</h2>
<!-- doPost includes lines 192-198 -->
<script>
    function redirectBrowse(){
        window.location.href="https://phabpharmacy.herokuapp.com/browse"
    }
</script>
</body>
</html>

