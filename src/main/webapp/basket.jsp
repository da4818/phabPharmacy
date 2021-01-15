<%--
  Created by IntelliJ IDEA.
  User: debbie
  Date: 18/12/2020
  Time: 18:50
  To change this template use File | Settings | File Templates.
--%>
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
        .navbar { <%-- Navigation bar code (lines 20-37, 53-55) --%>
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
        .dropdown { <%-- Dropdown code for product categories in Browse tab (lines 39-77) --%>
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
        .totalContainer{ <%-- Section to contain total cost of items in the basket --%>
            position: relative;
            loat: right;
            width: 170px;
            height: 70px;
            border: 1px solid black;
            margin: 0px 50px 0px 0px;
            padding: 0px 0px 10px 20px;
        }
        .basketContainer{ <%-- Section to contain item information from the basket --%>
            position: relative;
            width: 350px;
            height: 140px;
            border: 1px solid black;
            margin: 5px;
            padding: 0px 0px 0px 20px;
        }
        div.quant { <%-- Section to contain the quantity of each item in the basket --%>
            position: absolute;
            bottom: -1px;
            left: -1px;
            width: 350px;
            height: 50px;
            padding-left: 20px;
            padding-top: 5px;
            border: 1px solid black
        }
        div.price { <%-- Section to contain the sell_price of each item in the basket --%>
            position: absolute;
            bottom: -1px;
            right: -1px;
            width: 80px;
            height: 45px;
        }
        .buttonStyle{
            background-color: #51B5C2;
            border: none;
            color: white;
            padding: 2px 5px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 15px;
            margin: 2px 4px;
            cursor: pointer;
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
    <script>
        function redirectBrowse(){ <%-- When the Browse tab is clicked, it will call the function to redirect to the URL--%>
            window.location.href="https://phabpharmacy.herokuapp.com/browse"
        }
    </script>
</head>
<body>
<div class="navbar">
    <a href="https://phabpharmacy.herokuapp.com/home"><i class="fa fa-fw fa-home"></i>Home</a>
    <div class="dropdown">
        <button class= "dropbtn"><i class="fa fa-fw fa-search"></i>Browse<i class="fa fa-caret-down"></i></button>
        <div class="dropdown-content">
            <a href="#cold_and_flu">Cold and Flu</a>
            <a href="#skincare">Skincare</a>
            <a href="#">Headaches and Pain Relief</a>
            <a href="#">Digestion</a>
            <a href="#">Allergy</a>
            <a href="#">First Aid</a>
        </div>
    </div>
    <a href="https://phabpharmacy.herokuapp.com/login"><i class="fa fa-fw fa-user"></i>Login</a>
    <a href="https://phabpharmacy.herokuapp.com/register"><i class="fa fa-fw fa-user-plus"></i>Register</a>
    <a href="https://phabpharmacy.herokuapp.com/basket"name="Basket" style="background-color: #00B8C5;"><i style="width: 35px;" class="fa fa-fw fa-shopping-basket"><p style="display: inline; font-family: Arial; font-weight: bold;" id="basket"></p></i></a>
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
<h1>Shopping Basket</h1>
<!-- doGet and doPost (display same HTML) -->
<div class="totalContainer">
    <p style="margin-bottom: 10px;">Total: £x.xx<br></p><a href="https://phabpharmacy.herokuapp.com/order" class="buttonStyle">Proceed to Checkout</a>
</div>
<!-- Repeat function for number of items in basket -->
<section>
    <div class="basketContainer">

        <!--If product is limited: -->
        <p class="tooltip" style="display: inline-block;"><b>" + b.name + "</b><br>" + b.description + "<br>£<output type="number">" + price + "</output><span class="tooltiptext"><i>Limited to one per customer</i></span></p>
        <!--------------------------->

        <p style="display: inline-block;"><b><!--basket item name--></b><br><!--basket item description--><br>£<output type="number"><!--basket item price --></output></p>
        <div class="quant">
            <form id="updateBasket" action="basket" method="post">
                <label for="itemQuantity">Qty</label><br>
                <input type="number" id="itemQuantity" name="itemQuantity" class="quantity" size="3" min="1" max="<!-- max item quantity-->" value="basketQuantity">
                <input name="basketNumber" type="hidden"value="basketName">
                <input name="update" style="margin-left: 0px;" type="submit" class="buttonStyle" value="Update">
                <button name="update" type="submit" class="buttonStyle"><i class="fa fa-trash" aria-hidden="true"></i></button>
            </form>
        </div>
        <div class="price">
            <p>£<output></output><!--subtotal for basket item (price*quantity)--></p>
        </div>
    </div>
</section>

<!-- If Basket is empty -->
<p>Empty Basket</p>
<div class="totalContainer">
    <p>Total: £0.00</p>
</div>
<!------------------------>

</body>
</html>