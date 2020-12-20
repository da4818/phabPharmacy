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
            background-color: #51B5C2;
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
        .container{
            position: relative;
            width: auto;
            height: 100px;
            border: 1px solid black;
            margin: 5px;
            padding: 0px 0px 0px 20px;
        }
        div.quant {
            position: absolute;
            top: -1px;
            right: 150px;
            width: 100px;
            height: 100px;
        }
        div.price {
            position: absolute;
            top: -1px;
            right: 50px;
            width: 70px;
            height: 100px;
        }

        .button2{
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
        function total(){
            var num=document.getElementById("number1").value;
            document.getElementById("basket").innerHTML = num
            document.getElementById("basket").style.fontFamily ="Arial, Helvetica, sans-serif";
        }
        function showPrice(){
            var c = document.getElementsByClassName("cost");
            var q = document.getElementsByClassName("quantity");
            var d = document.getElementsByClassName("demo");
            for(var i=0;i<c.length;i++){
                d[i].innerHTML = parseFloat(c[i].value).toFixed(2)*parseFloat(q[i].value).toFixed(2)
                d[i].innerHTML=parseFloat(d[i].innerHTML).toFixed(2)
            }
        }

        function loadPrice(){
            var c = document.getElementsByClassName("cost");
            var q = document.getElementsByClassName("quantity");
            var d = document.getElementsByClassName("demo");
            for(var i=0;i<c.length;i++){
                d[i].innerHTML = parseFloat(c[i].value).toFixed(2)*parseFloat(q[i].value).toFixed(2)
                d[i].innerHTML=parseFloat(d[i].innerHTML).toFixed(2)
            }
        }
        window.onload = loadPrice;
        function passVal(n){
            removeItem(n);
        }
        function removeItem(n){
            var q = document.getElementsByClassName("quantity");
            q[n].value = 0;
            showPrice();
        }

    </script>
</head>
<body>
<div class="navbar">
    <a href="https://phabpharmacy.herokuapp.com/home"><i class="fa fa-fw fa-home"></i>Home</a>
    <div class="dropdown">
        <button style="background-color: #51B5C2;"class= "dropbtn"><i class="fa fa-fw fa-search"></i>Browse<i class="fa fa-caret-down"></i></button>
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
    <a style="background-color: #00B8C5" class="fa fa-fw fa-shopping-basket"><b id="basket"></b></a>
</div>
<h1>Shopping Basket</h1>

<pre>
<script>
  for(var i=0;i<3;i++){
      document.write("<style>p{font-family: Arial, Helvetica, sans-serif;}</style>"+
          "<div class=\"container\" +id=\"cont1\">"+
          "<p style=\"display: inline-block;\"><b>Vicks Vaporub</b><br>100g<br>£<output class=\"cost\" type=\"number\">4.50</output></p>" +
          "<div class=\"quant\">" +
          "<p>Quantity</p><input class=\"quantity\" onclick=\"showPrice()\" type=\"number\" min=\"1\" max=\"5\" value=\"2\"><button onclick=\"passVal("+i+")\" class=\"button2\"><i class=\"fa fa-trash\" aria-hidden=\"true\"></i></button>" +
          "</div>" +
          "<div class=\"price\">" +
          "<p>Price<br><br>£<output class=\"demo\"></output></p>" +
          "</div>"+
          "</div>");
  }
</script>
</pre>
</body>
</html>