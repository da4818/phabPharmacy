<%--
  Created by IntelliJ IDEA.
  User: debbie
  Date: 30/12/2020
  Time: 17:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
"<html>
"<head>
   <meta charset="utf-8">
   <meta name="viewport" content="width=device-width">
   <title>Confirm Order</title>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
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
          padding: 14px 14px 4px 16px;
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
      div.basketContainer{
        width: 350px;
        margin: 0px;
        padding: 0px 0px 20px 20px;
        border: 1px solid black;
      }
      div.addressContainer {
        position: relative;
        width: 270px;
        float: right;
        padding: 0px 0px 10px 20px;
        margin: 0px 50px 0px 0px;
        border: 1px solid black;
      }
      div.confirmContainer{
        position: absolute;
        width: 270px;
        height: 70px;
        right: -1px;
        bottom: -87px;
        padding: 0px 0px 15px 20px;
        border: 1px solid black;
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
  </style>
"</head>
"<body>
<div class="navbar">
    <a href="https://phabpharmacy.herokuapp.com/home"><i class="fa fa-fw fa-home"></i>Home</a>
    <div class="dropdown">
        <button name="Browse" class="dropbtn"><i class="fa fa-fw fa-search"></i>Browse<i class="fa fa-caret-down"></i></button>
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
    <a href="https://phabpharmacy.herokuapp.com/basket"name="Basket"><i style="width: 35px;" class="fa fa-fw fa-shopping-basket"><p style="display: inline; font-family: Arial; font-weight: bold;" id="basket"></p></i></a>
    <%-- Tab coloured in blue to indicate it's the active tab --%>
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

<h1>Confirm Order</h1>
<!-- doPost -->
<h2 name="orderResponse">Order Confirmed!</h2>
<!-- doPost includes lines 199-205 -->

<!-- doGet -->
<div class="addressContainer">
    <p style="display: inline-block; margin-bottom: 0px;"><b>Shipping Address</b></p>
    <p>" + u.fname + " " + u.lname + "<br>" + u.postcode + "<br>Payment</p>
    <button name="editDetails" onclick="window.location.href='https://phabpharmacy.herokuapp.com/amend_details';"class="buttonStyle">Edit Details</button>
    <div class="confirmContainer">
        <p>Total Cost: <b>£" + total +"</b></p>
        <form id="confirmOrder" action="order" method="post">
              <input type="submit" name="orderResponse" class="buttonStyle" value="Confirm Order"> //This is the button that the use confirm the order
            </form>
        </div>
    "</div>
<!-- Displaying basket items in order summary -->
<div class="basketContainer">
    <p style="display: inline-block; margin-bottom: 0px;"><b>Order Summary</b></p>
    <p><!-- Product name--> - <!-- Product description--> - x <!-- Product quantity--> - £ <!-- Product subtotal --><br></p>
    <button name="editBasket" onclick="window.location.href='https://phabpharmacy.herokuapp.com/basket';" class="buttonStyle">Edit Basket</button>
</div>
<script>
  function redirectBrowse(){
      window.location.href="https://phabpharmacy.herokuapp.com/browse"
  }
</script>
</body>
</html>

