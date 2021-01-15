
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width">
    <title>Amend Details</title>
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
        textarea::placeholder {
            font-family: Arial, Helvetica, sans-serif;
            width: 40ch;
        }
        .buttonStyle {
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

h1>Amend Details</h1>
<p>Change any details as required (empty fields will not be updated). If everything is already up to date, press 'Cancel' to go back.</p>
 <!-- Placeholders will contain the information that is currently stored in the database, according to the users' information-->
<form name="amendDetailsForm" action="amend_details" method="post">
    <h3>Order Information<br><b style="font-size: 15px;">Payment Information</b></h3>
    <label>Card Number</label><br>
    <input type="text" size="30" class="form-control" name="card_no" placeholder=""><br>
    <label>Sort Code</label><br>
    <input type="text" size="30" class="form-control" name="sort_code" placeholder=""><br>
    <label>Account Number</label><br>
    <input type="text" size="30" class="form-control" name="account_no" placeholder=""><br>
    <label>CVV</label><br>
    <input type="text" size="30" class="form-control" name="cvv" placeholder=""><br>

    <h3 style="font-size: 15px;">Shipping Information</h3>
    <label>Address</label><br>
    <textarea name="address" style="width: 217px; font-family: Arial, Helvetica, sans-serif;" rows="4" value="" placeholder=""></textarea><br>
    <label>Postcode</label><br>
    <input type="text" size="30" class="form-control" name="postcode" placeholder=""><br>
    <label>Phone Number</label><br>
    <input type="text" size="30" class="form-control" value="" name="phone_no" placeholder=""><br>
    
    <input name="update" type="submit" style="width: 135px; margin-left: 0px;" class="buttonStyle" value="Update Details">
    <a class="buttonStyle" style="width: 69px; margin-left: 1px;" href="https://phabpharmacy.herokuapp.com/order">Cancel</a>
    "</form>
<script>
 function refreshPage(){
     setTimeout(function(){location.reload();},2000)
 }
</script>
</body>
</html>