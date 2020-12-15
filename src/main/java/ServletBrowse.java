import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/browse",loadOnStartup = 0)
public class ServletBrowse extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.getWriter().write("<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "    <meta charset=\"utf-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width\">\n" +
                "    <title>Browse</title>\n" +
                "    <!-- Import Icon Library -->\n" +
                "    <link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css\">\n" +
                "    <!-- Creates navigation bar -->\n" +
                "    <style>\n" +
                "        body {font-family: Arial, Helvetica, sans-serif;}\n" +
                "        .navbar {\n" +
                "            width: 100%;\n" +
                "            background-color: #555;\n" +
                "            overflow: auto;\n" +
                "        }\n" +
                "        .navbar a {\n" +
                "            float: left;\n" +
                "            padding: 12px;\n" +
                "            color: white;\n" +
                "            text-decoration: none;\n" +
                "            font-size: 17px;\n" +
                "        }\n" +
                "        .active {\n" +
                "            background-color: #51B5C2;\n" +
                "        }\n" +
                "        @media screen and (max-width: 500px) {\n" +
                "            .navbar a {\n" +
                "                float: none;\n" +
                "                display: block;\n" +
                "            }\n" +
                "        }\n" +
                "        .dropdown {\n" +
                "            float: left;\n" +
                "            overflow: hidden;\n" +
                "        }\n" +
                "        .dropdown .dropbtn {\n" +
                "            font-size: 16px;\n" +
                "            border: none;\n" +
                "            outline: none;\n" +
                "            color: white;\n" +
                "            padding: 14px 16px;\n" +
                "            background-color: inherit;\n" +
                "            font-family: inherit;\n" +
                "            margin: 0;\n" +
                "        }\n" +
                "        .navbar a:hover, .dropdown:hover .dropbtn {\n" +
                "            background-color: #000;\n" +
                "        }\n" +
                "        .dropdown-content {\n" +
                "            display: none;\n" +
                "            position: absolute;\n" +
                "            background-color: #f9f9f9;\n" +
                "            min-width: 160px;\n" +
                "            box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);\n" +
                "            z-index: 1;\n" +
                "        }\n" +
                "        .dropdown-content a {\n" +
                "            float: none;\n" +
                "            color: black;\n" +
                "            padding: 12px 16px;\n" +
                "            text-decoration: none;\n" +
                "            display: block;\n" +
                "            text-align: left;\n" +
                "        }\n" +
                "        .dropdown-content a:hover {\n" +
                "            background-color: #ddd;\n" +
                "        }\n" +
                "        .dropdown:hover .dropdown-content {\n" +
                "            display: block;\n" +
                "        }\n" +
                "        div.absolute {\n" +
                "            position: absolute;\n" +
                "            top: 140px;\n" +
                "            width: 200px;\n" +
                "            height: 30px;\n" +
                "        }\n" +
                "        div.relative{\n" +
                "            position: relative;\n" +
                "            width: 200px;\n" +
                "            height: 150px;\n" +
                "            display: inline;\n" +
                "            border: 1px solid black;\n" +
                "            padding: 15px;\n" +
                "            margin: 5px;\n" +
                "            float: left;\n" +
                "        }\n" +
                "        .button2{\n" +
                "            background-color: #51B5C2;\n" +
                "            border: none;\n" +
                "            color: white;\n" +
                "            padding: 5px 25px;\n" +
                "            text-align: center;\n" +
                "            text-decoration: none;\n" +
                "            display: inline-block;\n" +
                "            font-size: 16px;\n" +
                "            margin: 4px 2px;\n" +
                "            cursor: pointer;\n" +
                "        }\n" +
                "        section{\n" +
                "            display: table-row;\n" +
                "        }\n" +
                "        .tooltip{\n" +
                "            position: relative;\n" +
                "            display: inline;\n" +
                "        }\n" +
                "        .tooltip .tooltiptext {\n" +
                "            visibility: hidden;\n" +
                "            width: 120px;\n" +
                "            background-color: black;\n" +
                "            color: #fff;\n" +
                "            text-align: center;\n" +
                "            border-radius: 6px;\n" +
                "            padding: 5px 0;\n" +
                "            position: absolute;\n" +
                "            z-index: 1;\n" +
                "        }\n" +
                "        .tooltip:hover .tooltiptext {\n" +
                "            visibility: visible;\n" +
                "        }\n" +
                "    </style>\n" +
                "\n" +
                "</head>\n" +
                "<body>\n" +
                "<div class=\"navbar\">\n" +
                "    <a href=\"https://phabpharmacy.herokuapp.com/home\"><i class=\"fa fa-fw fa-home\"></i>Home</a>\n" +
                "    <div class=\"dropdown\">\n" +
                "        <button style=\"background-color: #51B5C2;\"class= \"dropbtn\"><i class=\"fa fa-fw fa-search\"></i>Browse<i class=\"fa fa-caret-down\"></i></button>\n" +
                "        <div class=\"dropdown-content\">\n" +
                "            <a href=\"#cold_and_flu\">Cold and Flu</a>\n" +
                "            <a href=\"#skincare\">Skincare</a>\n" +
                "            <a href=\"#headaches_and_pain_relief\">Headaches and Pain Relief</a>\n" +
                "            <a href=\"#digestion\">Digestion</a>\n" +
                "            <a href=\"#allergy\">Allergy</a>\n" +
                "            <a href=\"#first_aid\">First Aid</a>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "    <a href=\"https://phabpharmacy.herokuapp.com/login\"><i class=\"fa fa-fw fa-user\"></i>Login</a>\n" +
                "    <a href=\"https://phabpharmacy.herokuapp.com/register\"><i class=\"fa fa-fw fa-user-plus\"></i>Register</a>\n" +
                "</div>\n" +
                "<section>\n" +
                "    <h2 id=\"cold_and_flu\">Cold and Flu </h2>\n" +
                "    <div id=\"div1\" class =\"relative\">\n" +
                "        <label><center>Vicks Vaporub<br>100g</center></label><br>\n" +
                "        <label><center>£4.50</center></label><br>\n" +
                "        <div class=\"absolute\">\n" +
                "            <input type=\"number\" name =\"VicksV\" size=\"5\" min=\"1\" max=\"5\">\n" +
                "            <button class=\"button button2\">Add to Basket</button>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "    <div id=\"div2\" class =\"relative\">\n" +
                "        <label><center>Vicks First Defence<br>15ml</center></label><br>\n" +
                "        <label><center>£6.80</center></label><br>\n" +
                "        <div class=\"absolute\">\n" +
                "            <input type=\"number\" name =\"VicksF\" size=\"5\" min=\"1\" max=\"5\">\n" +
                "            <button class=\"button button2\">Add to Basket</button>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "    <div id=\"div3\" class =\"relative\">\n" +
                "        <label><center>Gsk Night Nurse<br>160ml</center></label><br>\n" +
                "        <label><center>£8.50</center></label><br>\n" +
                "        <div class=\"absolute\">\n" +
                "            <input type=\"number\" name =\"GskN\" size=\"5\" min=\"1\" max=\"5\">\n" +
                "            <button class=\"button button2\">Add to Basket</button>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "    <div id=\"div4\" class =\"relative\">\n" +
                "        <label><center>Gsk Night Nurse<br>200ml</center></label><br>\n" +
                "        <label><center>£9.00</center></label><br>\n" +
                "        <<div class=\"absolute\">\n" +
                "        <input type=\"number\" name =\"GskN2\" size=\"5\" min=\"1\" max=\"5\">\n" +
                "        <button class=\"button button2\">Add to Basket</button>\n" +
                "    </div>\n" +
                "    </div>\n" +
                "    <div id=\"div5\" class =\"relative\">\n" +
                "        <label><center>Lemsip Max<br>16 caps</center></label><br>\n" +
                "        <label><center>£4.20</center></label><br>\n" +
                "        <div class=\"absolute\">\n" +
                "            <input type=\"number\" name =\"LemsipM\" size=\"5\" min=\"1\" max=\"5\">\n" +
                "            <button class=\"button button2\">Add to Basket</button>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "    <div id=\"div6\" class =\"relative\">\n" +
                "        <label><center>Lemsip Standard<br>10 capsules</center></label><br>\n" +
                "        <label><center>£4.50</center></label><br>\n" +
                "        <div class=\"absolute\">\n" +
                "            <input type=\"number\" name =\"LemsipS\" size=\"5\" min=\"1\" max=\"5\">\n" +
                "            <button class=\"button button2\">Add to Basket</button>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "    <div id=\"div7\" class =\"relative\">\n" +
                "        <label class=\"tooltip\"><center>Sudafed Day & Night<br>16 caps</center>\n" +
                "            <span class=\"tooltiptext\"><i>Limited to one per customer</i></span></label><br>\n" +
                "        <label><center>£4.50</center></label><br>\n" +
                "        <div class=\"absolute\">\n" +
                "            <input type=\"number\" name =\"SudafedDN\" size=\"5\" min=\"1\" max=\"1\">\n" +
                "            <button class=\"button button2\">Add to Basket</button>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "    <div id=\"div8\" class =\"relative\">\n" +
                "        <label class=\"tooltiptext\"><center>Sudafed Max<br>16 caps</center>\n" +
                "            <span class=\"tooltiptext\"><i>Limited to one per customer</i></span></label><br>\n" +
                "        <label><center>£4.20</center></label><br>\n" +
                "        <div class=\"absolute\">\n" +
                "            <input type=\"number\" name =\"SudafedM\" size=\"5\" min=\"1\" max=\"1\">\n" +
                "            <button class=\"button button2\">Add to Basket</button>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "    <div id=\"div9\" class =\"relative\">\n" +
                "        <label><center>Benylin Mucus Relief<br>16 caps</center></label><br>\n" +
                "        <label><center>£4.80</center></label><br>\n" +
                "        <div class=\"absolute\">\n" +
                "            <input type=\"number\" name =\"BenylinM\" size=\"5\" min=\"1\" max=\"5\">\n" +
                "            <button class=\"button button2\">Add to Basket</button>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "    <div id=\"div10\" class =\"relative\">\n" +
                "        <label><center>Benylin 4 Flu<br>24 caps</center></label><br>\n" +
                "        <label><center>£6.00</center></label><br>\n" +
                "        <div class=\"absolute\">\n" +
                "            <input type=\"number\" name =\"BenylinF\" size=\"5\" min=\"1\" max=\"5\">\n" +
                "            <button class=\"button button2\">Add to Basket</button>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "</section>\n" +
                "\n" +
                "\n" +
                "<section>\n" +
                "    <h2 id=\"skincare\">Skincare</h2>\n" +
                "    <div id=\"div11\" class =\"relative\">\n" +
                "        <label><center>E45 Psoriasis Cream<br>50ml</center></label><br>\n" +
                "        <label><center>£20.00</center></label><br>\n" +
                "        <div class=\"absolute\">\n" +
                "            <input type=\"number\" name =\"E45\" size=\"5\" min=\"1\" max=\"5\">\n" +
                "            <button class=\"button button2\">Add to Basket</button>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "    <div id=\"div12\" class =\"relative\">\n" +
                "        <label><center>Eurax Skin Cream<br>100g</center></label><br>\n" +
                "        <label><center>£5.70</center></label><br>\n" +
                "        <div class=\"absolute\">\n" +
                "            <input type=\"number\" name =\"EuraxS\" size=\"5\" min=\"1\" max=\"5\">\n" +
                "            <button class=\"button button2\">Add to Basket</button>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "    <div id=\"div13\" class =\"relative\">\n" +
                "        <label><center>Eucerin Skin Relief Cream<br>50ml</center></label><br>\n" +
                "        <label><center>£9.00</center></label><br>\n" +
                "        <div class=\"absolute\">\n" +
                "            <input type=\"number\" name =\"EucerinS\" size=\"5\" min=\"1\" max=\"5\">\n" +
                "            <button class=\"button button2\">Add to Basket</button>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "    <div id=\"div14\" class =\"relative\">\n" +
                "        <label><center>Eucerin Face Scrub<br>100ml</center></label><br>\n" +
                "        <label><center>£7.50</center></label><br>\n" +
                "        <div class=\"absolute\">\n" +
                "            <input type=\"number\" name =\"EucerinF\" size=\"5\" min=\"1\" max=\"5\">\n" +
                "            <button class=\"button button2\">Add to Basket</button>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "    <div id=\"div15\" class =\"relative\">\n" +
                "        <label><center>Dermalex Psoriasis Cream<br>150ml</center></label><br>\n" +
                "        <label><center>£30.00</center></label><br>\n" +
                "        <div class=\"absolute\">\n" +
                "            <input type=\"number\" name =\"DermalexP\" size=\"5\" min=\"1\" max=\"5\">\n" +
                "            <button class=\"button button2\">Add to Basket</button>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "    <div id=\"div16\" class =\"relative\">\n" +
                "        <label><center>Dermalex Repair & Restore<br>100g</center></label><br>\n" +
                "        <label><center>£12.00</center></label><br>\n" +
                "        <div class=\"absolute\">\n" +
                "            <input type=\"number\" name =\"DermalexR\" size=\"5\" min=\"1\" max=\"5\">\n" +
                "            <button class=\"button button2\">Add to Basket</button>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "    <div id=\"div17\" class =\"relative\">\n" +
                "        <label><center>Dermalex Eczema Cream<br>30g</center></label><br>\n" +
                "        <label><center>£12.00</center></label><br>\n" +
                "        <div class=\"absolute\">\n" +
                "            <input type=\"number\" name =\"Dermalex30\" size=\"5\" min=\"1\" max=\"5\">\n" +
                "            <button class=\"button button2\">Add to Basket</button>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "    <div id=\"div18\" class =\"relative\">\n" +
                "        <label><center>Dermalex Eczema Cream<br>100g</center></label><br>\n" +
                "        <label><center>£25.00</center></label><br>\n" +
                "        <div class=\"absolute\">\n" +
                "            <input type=\"number\" name =\"Dermalex100\" size=\"5\" min=\"1\" max=\"5\">\n" +
                "            <button class=\"button button2\">Add to Basket</button>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "    <div id=\"div19\" class =\"relative\">\n" +
                "        <label><center>Cetaphil Moisturising Cream<br>50ml</center></label><br>\n" +
                "        <label><center>£10.00</center></label><br>\n" +
                "        <div class=\"absolute\">\n" +
                "            <input type=\"number\" name =\"CetaphilM\" size=\"5\" min=\"1\" max=\"5\">\n" +
                "            <button class=\"button button2\">Add to Basket</button>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "    <div id=\"div20\" class =\"relative\">\n" +
                "        <label><center>Cetaphil Exfoliating Cleanser<br>180ml</center></label><br>\n" +
                "        <label><center>£12.00</center></label><br>\n" +
                "        <div class=\"absolute\">\n" +
                "            <input type=\"number\" name =\"CetaphilE\" size=\"5\" min=\"1\" max=\"5\">\n" +
                "            <button class=\"button button2\">Add to Basket</button>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "</section>\n" +
                "<section>\n" +
                "    <h2 id=\"headaches_and_pain_relief\">Headache and Pain Relief</h2>\n" +
                "    <div id=\"div21\" class =\"relative\">\n" +
                "        <label><center>Nurofen Meltlets<br>16 caps</center></label><br>\n" +
                "        <label><center>£4.00</center></label><br>\n" +
                "        <div class=\"absolute\">\n" +
                "            <input type=\"number\" name =\"NurofenM\" size=\"5\" min=\"1\" max=\"5\">\n" +
                "            <button class=\"button button2\">Add to Basket</button>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "    <div id=\"div22\" class =\"relative\">\n" +
                "        <label><center>Nurofen Express<br>16 caps</center></label><br>\n" +
                "        <label><center>£4.00</center></label><br>\n" +
                "        <div class=\"absolute\">\n" +
                "            <input type=\"number\" name =\"NurofenE\" size=\"5\" min=\"1\" max=\"5\">\n" +
                "            <button class=\"button button2\">Add to Basket</button>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "    <div id=\"div23\" class =\"relative\">\n" +
                "        <label><center>Nurofen Max Strength<br>32 caps</center></label><br>\n" +
                "        <label><center>£7.00</center></label><br>\n" +
                "        <div class=\"absolute\">\n" +
                "            <input type=\"number\" name =\"NurofenMS\" size=\"5\" min=\"1\" max=\"5\">\n" +
                "            <button class=\"button button2\">Add to Basket</button>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "    <div id=\"div24\" class =\"relative\">\n" +
                "        <label><center>Nurofen Standard<br>16 caps</center></label><br>\n" +
                "        <label><center>£4.00</center></label><br>\n" +
                "        <div class=\"absolute\">\n" +
                "            <input type=\"number\" name =\"NurofenS\" size=\"5\" min=\"1\" max=\"5\">\n" +
                "            <button class=\"button button2\">Add to Basket</button>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "    <div id=\"div25\" class =\"relative\">\n" +
                "        <label class=\"tooltip\"><center>Cuprofen Max Strength<br>96 caps</center>\n" +
                "            <span class=\"tooltiptext\"><i>Limited to one per customer</i></span></label><br>\n" +
                "        <label><center>£11.00</center></label><br>\n" +
                "        <div class=\"absolute\">\n" +
                "            <input type=\"number\" name =\"Cuprofen\" size=\"5\" min=\"1\" max=\"1\">\n" +
                "            <button class=\"button button2\">Add to Basket</button>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "    <div id=\"div26\" class =\"relative\">\n" +
                "        <label class=\"tooltip\"><center>Solpadeine<br>16 caps</center>\n" +
                "            <span class=\"tooltiptext\"><i>Limited to one per customer</i></span></label><br>\n" +
                "        <label><center>£2.00</center></label><br>\n" +
                "        <div class=\"absolute\">\n" +
                "            <input type=\"number\" name =\"Solpadeine\" size=\"5\" min=\"1\" max=\"1\">\n" +
                "            <button class=\"button button2\">Add to Basket</button>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "    <div id=\"div27\" class =\"relative\">\n" +
                "        <label class=\"tooltip\"><center>Anadin Extra<br>16 caps</center>\n" +
                "            <span class=\"tooltiptext\"><i>Limited to one per customer</i></span></label><br>\n" +
                "        <label><center>£2.30</center></label><br>\n" +
                "        <div class=\"absolute\">\n" +
                "            <input type=\"number\" name =\"AnadinE\" size=\"5\" min=\"1\" max=\"1\">\n" +
                "            <button class=\"button button2\">Add to Basket</button>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "    <div id=\"div28\" class =\"relative\">\n" +
                "        <label class=\"tooltip\"><center>Anadin Triple Action<br>12 caps</center>\n" +
                "            <span class=\"tooltiptext\"><i>Limited to one per customer</i></span></label><br>\n" +
                "        <label><center>£2.00</center></label><br>\n" +
                "        <div class=\"absolute\">\n" +
                "            <input type=\"number\" name =\"AnadinT\" size=\"5\" min=\"1\" max=\"1\">\n" +
                "            <button class=\"button button2\">Add to Basket</button>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "    <div id=\"div1=29\" class =\"relative\">\n" +
                "        <label class=\"tooltip\"><center>Anadin Original<br>16 caps</center>\n" +
                "            <span class=\"tooltiptext\"><i>Limited to one per customer</i></span></label><br>\n" +
                "        <label><center>£1.80</center></label><br>\n" +
                "        <div class=\"absolute\">\n" +
                "            <input type=\"number\" name =\"AnadinO\" size=\"5\" min=\"1\" max=\"1\">\n" +
                "            <button class=\"button button2\">Add to Basket</button>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "    <div id=\"div30\" class =\"relative\">\n" +
                "        <label class=\"tooltip\"><center>Disprin Soluble<br>32 tablets</center>\n" +
                "            <span class=\"tooltiptext\"><i>Limited to one per customer</i></span></label><br>\n" +
                "        <label><center>£3.60</center></label><br>\n" +
                "        <div class=\"absolute\">\n" +
                "            <input type=\"number\" name =\"Disprin\" size=\"5\" min=\"1\" max=\"1\">\n" +
                "            <button class=\"button button2\">Add to Basket</button>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "</section>\n" +
                "\n" +
                "<section>\n" +
                "    <h2 id=\"digestion\">Digestion</h2>\n" +
                "    <div id=\"div31\" class =\"relative\">\n" +
                "        <label><center>Dioralyte Blackurrent<br>12 sachets</center></label><br>\n" +
                "        <label><center>£8.00</center></label><br>\n" +
                "        <div class=\"absolute\">\n" +
                "            <input type=\"number\" name =\"DioralyteB\" size=\"5\" min=\"1\" max=\"5\">\n" +
                "            <button class=\"button button2\">Add to Basket</button>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "    <div id=\"div32\" class =\"relative\">\n" +
                "        <label><center>Dioralyte Lemon<br>12 sachets</center></label><br>\n" +
                "        <label><center>£8.00</center></label><br>\n" +
                "        <div class=\"absolute\">\n" +
                "            <input type=\"number\" name =\"DioralyteL\" size=\"5\" min=\"1\" max=\"5\">\n" +
                "            <button class=\"button button2\">Add to Basket</button>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "    <div id=\"div33\" class =\"relative\">\n" +
                "        <label><center>Gaviscon Chewable<br>24 tablets</center></label><br>\n" +
                "        <label><center>£4.20</center></label><br>\n" +
                "        <div class=\"absolute\">\n" +
                "            <input type=\"number\" name =\"GavisconC\" size=\"5\" min=\"1\" max=\"5\">\n" +
                "            <button class=\"button button2\">Add to Basket</button>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "    <div id=\"div34\" class =\"relative\">\n" +
                "        <label><center>Gaviscon Advance<br>10 tablets</center></label><br>\n" +
                "        <label><center>£3.00</center></label><br>\n" +
                "        <div class=\"absolute\">\n" +
                "            <input type=\"number\" name =\"GavisconA\" size=\"5\" min=\"1\" max=\"5\">\n" +
                "            <button class=\"button button2\">Add to Basket</button>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "    <div id=\"div35\" class =\"relative\">\n" +
                "        <label><center>Senokot Max<br>300ml</center></label><br>\n" +
                "        <label><center>£3.00</center></label><br>\n" +
                "        <div class=\"absolute\">\n" +
                "            <input type=\"number\" name =\"Senokot\" size=\"5\" min=\"1\" max=\"5\">\n" +
                "            <button class=\"button button2\">Add to Basket</button>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "</section>\n" +
                "<section>\n" +
                "    <h2 id=\"allergy\">Allergy</h2>\n" +
                "    <div id=\"div41\" class =\"relative\">\n" +
                "        <label><center>Benadryl<br>24 caps</center></label><br>\n" +
                "        <label><center>£9.00</center></label><br>\n" +
                "        <div class=\"absolute\">\n" +
                "            <input type=\"number\" name =\"Benadryl\" size=\"5\" min=\"1\" max=\"5\">\n" +
                "            <button class=\"button button2\">Add to Basket</button>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "    <div id=\"div42\" class =\"relative\">\n" +
                "        <label><center>Piriteze Tabs<br>7 tablets</center></label><br>\n" +
                "        <label><center>£3.00</center></label><br>\n" +
                "        <div class=\"absolute\">\n" +
                "            <input type=\"number\" name =\"Piriteze\" size=\"5\" min=\"1\" max=\"5\">\n" +
                "            <button class=\"button button2\">Add to Basket</button>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "    <div id=\"div43\" class =\"relative\">\n" +
                "        <label><center>Beconase Relief<br>100 sprays</center></label><br>\n" +
                "        <label><center>£6.00</center></label><br>\n" +
                "        <div class=\"absolute\">\n" +
                "            <input type=\"number\" name =\"BeconaseR\" size=\"5\" min=\"1\" max=\"5\">\n" +
                "            <button class=\"button button2\">Add to Basket</button>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "</section>\n" +
                "<section>\n" +
                "    <h2 id=\"first_aid\">First Aid</h2>\n" +
                "    <div id=\"div51\" class =\"relative\">\n" +
                "        <label><center>Dettol Antiseptic<br>500ml</center></label><br>\n" +
                "        <label><center>£3.20</center></label><br>\n" +
                "        <div class=\"absolute\">\n" +
                "            <input type=\"number\" name =\"DettolA\" size=\"5\" min=\"1\" max=\"5\">\n" +
                "            <button class=\"button button2\">Add to Basket</button>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "    <div id=\"div52\" class =\"relative\">\n" +
                "        <label><center>Dettol Hand Sanitiser<br>500ml</center></label><br>\n" +
                "        <label><center>£7.00</center></label><br>\n" +
                "        <div class=\"absolute\">\n" +
                "            <input type=\"number\" name =\"DettolH\" size=\"5\" min=\"1\" max=\"5\">\n" +
                "            <button class=\"button button2\">Add to Basket</button>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "    <div id=\"div53\" class =\"relative\">\n" +
                "        <label><center>Elastoplast Plasters<br>20 plasters</center></label><br>\n" +
                "        <label><center>£3.00</center></label><br>\n" +
                "        <div class=\"absolute\">\n" +
                "            <input type=\"number\" name =\"Elastoplast\" size=\"5\" min=\"1\" max=\"5\">\n" +
                "            <button class=\"button button2\">Add to Basket</button>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "    <div id=\"div54\" class =\"relative\">\n" +
                "        <label><center>TCP Liquid<br>200ml</center></label><br>\n" +
                "        <label><center>£4.00</center></label><br>\n" +
                "        <div class=\"absolute\">\n" +
                "            <input type=\"number\" name =\"TCP\" size=\"5\" min=\"1\" max=\"5\">\n" +
                "            <button class=\"button button2\">Add to Basket</button>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "</section>\n" +
                "</body>\n" +
                "</html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }
}
