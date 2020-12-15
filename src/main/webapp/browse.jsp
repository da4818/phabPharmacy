<%--
  Created by IntelliJ IDEA.
  User: debbie
  Date: 14/12/2020
  Time: 19:45
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
        .boxed{
            width: 200px;
            border: 1px solid black;
            padding: 15px 15px 10px 15px;
            margin: 5px;
            float: left;
        }
        .button2{
            background-color: #4CAF50; /* Green */
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
        section{
            display: table-row;
        }
    </style>

</head>
<body>
<div class="navbar">
    <a href="https://phabpharmacy.herokuapp.com/home"><i class="fa fa-fw fa-home"></i>Home</a>
    <div class="dropdown">
        <button style="background-color: #6CCCBF;"class= "dropbtn"><i class="fa fa-fw fa-search"></i>Browse<i class="fa fa-caret-down"></i></button>
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
</div>
<section>
    <h2 id="cold_and_flu">Cold and Flu </h2>
    <div id="div1" class ="boxed">
        <label><center>Vicks Vaporub<br>100g</center></label><br>
        <label><center>£4.50</center></label><br>
        <input type="number" name ="VicksV" size="5" min="1" max="5">
        <button class="button button2">Add to Basket</button>
    </div>
    <div id="div2" class ="boxed">
        <label><center>Vicks First Defence<br>15ml</center></label><br>
        <label><center>£6.80</center></label><br>
        <input type="number" name ="VicksF" size="5" min="1" max="5">
        <button class="button button2">Add to Basket</button>
    </div>
    <div id="div3" class ="boxed">
        <label><center>Gsk Night Nurse<br>160ml</center></label><br>
        <label><center>£8.50</center></label><br>
        <input type="number" name ="GskN" size="5" min="1" max="5">
        <button class="button button2">Add to Basket</button>
    </div>
    <div id="div4" class ="boxed">
        <label><center>Gsk Night Nurse<br>200ml</center></label><br>
        <label><center>£9.00</center></label><br>
        <input type="number" name ="GskN2" size="5" min="1" max="5">
        <button class="button button2">Add to Basket</button>
    </div>
    <div id="div5" class ="boxed">
        <label><center>Lemsip Max<br>16 caps</center></label><br>
        <label><center>£4.20</center></label><br>
        <input type="number" name ="LemsipM" size="5" min="1" max="5">
        <button class="button button2">Add to Basket</button>
    </div>
    <div id="div6" class ="boxed">
        <label><center>Lemsip Standard<br>10 capsules</center></label><br>
        <label><center>£4.50</center></label><br>
        <input type="number" name ="LemsipS" size="5" min="1" max="5">
        <button class="button button2">Add to Basket</button>
    </div>
    <div id="div7" class ="boxed">
        <label><center>Sudafed Day & Night<br>16 caps</center></label><br>
        <label><center>£4.50</center></label><br>
        <input type="number" name ="SudafedDN" size="5" min="1" max="1">
        <button class="button button2">Add to Basket</button>
    </div>
    <div id="div8" class ="boxed">
        <label><center>Sudafed Max<br>16 caps</center></label><br>
        <label><center>£4.20</center></label><br>
        <input type="number" name ="SudafedM" size="5" min="1" max="1">
        <button class="button button2">Add to Basket</button>
    </div>
    <div id="div9" class ="boxed">
        <label><center>Benylin Mucus Relief<br>16 caps</center></label><br>
        <label><center>£4.80</center></label><br>
        <input type="number" name ="BenylinM" size="5" min="1" max="5">
        <button class="button button2">Add to Basket</button>
    </div>
    <div id="div10" class ="boxed">
        <label><center>Benylin 4 Flu<br>24 caps</center></label><br>
        <label><center>£6.00</center></label><br>
        <input type="number" name ="BenylinF" size="5" min="1" max="5">
        <button class="button button2">Add to Basket</button>
    </div>
</section>

<section>
    <h2 id="skincare">Skincare</h2>
    <div id="div11" class ="boxed">
        <label><center>E45 Psoriasis Cream<br>50ml</center></label><br>
        <label><center>£20.00</center></label><br>
        <input type="number" name ="E45" size="5" min="1" max="5">
        <button class="button button2">Add to Basket</button>
    </div>
    <div id="div12" class ="boxed">
        <label><center>Eurax Skin Cream<br>100g</center></label><br>
        <label><center>£5.70</center></label><br>
        <input type="number" name ="Eurax" size="5" min="1" max="5">
        <button class="button button2">Add to Basket</button>
    </div>
    <div id="div13" class ="boxed">
        <label><center>Eucerin Skin Relief Cream<br>50ml</center></label><br>
        <label><center>£9.00</center></label><br>
        <input type="number" name ="EucerinS" size="5" min="1" max="5">
        <button class="button button2">Add to Basket</button>
    </div>
    <div id="div14" class ="boxed">
        <label><center>Eucerin Face Scrub<br>100ml</center></label><br>
        <label><center>£7.50</center></label><br>
        <input type="number" name ="EucerinF" size="5" min="1" max="5">
        <button class="button button2">Add to Basket</button>
    </div>
    <div id="div15" class ="boxed">
        <label><center>Dermalex Psoriasis Cream<br>150ml</center></label><br>
        <label><center>£30.00</center></label><br>
        <input type="number" name ="DermalexP" size="5" min="1" max="5">
        <button class="button button2">Add to Basket</button>
    </div>
    <div id="div16" class ="boxed">
        <label><center>Dermalex Repair & Restore<br>100g</center></label><br>
        <label><center>£12.00</center></label><br>
        <input type="number" name ="DermalexR" size="5" min="1" max="5">
        <button class="button button2">Add to Basket</button>
    </div>
    <div id="div17" class ="boxed">
        <label><center>Dermalex Eczema Cream<br>30g</center></label><br>
        <label><center>£12.00</center></label><br>
        <input type="number" name ="DermalexE30" size="5" min="1" max="5">
        <button class="button button2">Add to Basket</button>
    </div>
    <div id="div18" class ="boxed">
        <label><center>Dermalex Eczema Cream<br>100g</center></label><br>
        <label><center>£25.00</center></label><br>
        <input type="number" name ="DermalexE100" size="5" min="1" max="5">
        <button class="button button2">Add to Basket</button>
    </div>
    <div id="div19" class ="boxed">
        <label><center>Cetaphil Moisturising Cream<br>50ml</center></label><br>
        <label><center>£10.00</center></label><br>
        <input type="number" name ="CetaphilM" size="5" min="1" max="5">
        <button class="button button2">Add to Basket</button>
    </div>
    <div id="div20" class ="boxed">
        <label><center>Cetaphil Exfoliating Cleanser<br>180ml</center></label><br>
        <label><center>£12.00</center></label><br>
        <input type="number" name ="CetaphilE" size="5" min="1" max="5">
        <button class="button button2">Add to Basket</button>
    </div>
</section>

</body>
</html>
