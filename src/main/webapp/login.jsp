<%--
  Created by IntelliJ IDEA.
  User: debbie
  Date: 14/12/2020
  Time: 18:27
  To change this template use File | Settings | File Templates.
--%>
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
    </style>
</head>
<body>
<div class="navbar">
    <a href="https://phabpharmacy.herokuapp.com/home"><i class="fa fa-fw fa-home"></i>Home</a>
    <div class="dropdown">
        <button style="cursor: pointer;" class= "dropbtn" onclick="redirectBrowse()"><i class="fa fa-fw fa-search"></i>Browse<i class="fa fa-caret-down"></i></button>
        <div class="dropdown-content">
            <a href="https://phabpharmacy.herokuapp.com/browse#cold_and_flu">Cold and Flu</a>
            <a href="https://phabpharmacy.herokuapp.com/browse#skincare">Skincare</a>
            <a href="https://phabpharmacy.herokuapp.com/browse#headaches_and_pain_relief">Headaches and Pain Relief</a>\n" +
            <a href="https://phabpharmacy.herokuapp.com/browse#digestion">Digestion</a>
            <a href="https://phabpharmacy.herokuapp.com/browse#allergy">Allergy</a>
            <a href="https://phabpharmacy.herokuapp.com/browse#first_aid">First Aid</a>
        </div>
    </div>
    <a class="active"><i class="fa fa-fw fa-user"></i>Login</a>
    <a href="https://phabpharmacy.herokuapp.com/register"><i class="fa fa-fw fa-user-plus"></i>Register</a>
</div>

<h1><center>Login</center></h1>
<p> Login below. If you haven't got an account, <a href="https://phabpharmacy.herokuapp.com/register"> register here.</a> </p>

<form name="loginForm">
        <input type="text" size="30" class="form-control" name="user" placeholder="Username*"><br>
        <input type="text" size="30" class="form-control" name="pass" placeholder="Password*"><br>
        <p><input type="button" value="Submit" onClick="display();"></p>
</form>
<p id="demo"></p>
<script>
    function display() {
        var a ="username";
        var b ="pass1";
        var x = validateInput();
        if (x==2){
            if (a == document.loginForm.user.value && b == document.loginForm.pass.value){
                message = "Login successful. Welcome back, " + document.loginForm.user.value;
                message += "!";
                document.getElementById("demo").innerHTML = message
            }
            else{
                message = "Incorrect login details. Please try again."
                document.getElementById("demo").innerHTML = message
                setTimeout(refreshPage,2000)
            }
        }
        else{
            message = "Incomplete fields, please enter all information.";
            document.getElementById("demo").innerHTML = message
        }
    }
    function refreshPage(){
        location.reload()
    }
    function validateInput(){
        var t=0;
        if(document.loginForm.user.value.trim()){t=t+1;}
        if(document.loginForm.pass.value.trim()){t=t+1;}
        return t;
    }
    function redirectBrowse(){
        window.location.href="https://phabpharmacy.herokuapp.com/browse"
    }
</script>
</body>
</html>

