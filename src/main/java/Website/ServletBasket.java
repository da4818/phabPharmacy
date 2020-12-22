package Website;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/basket",loadOnStartup = 0)
public class ServletBasket extends HttpServlet {
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
                "        .container{\n" +
                "          position: relative;\n" +
                "          width: auto;\n" +
                "          height: 100px;\n" +
                "          border: 1px solid black;\n" +
                "          margin: 5px;\n" +
                "          padding: 0px 0px 0px 20px;\n" +
                "        }\n" +
                "        div.quant {\n" +
                "            position: absolute;\n" +
                "            top: -1px;\n" +
                "            right: 150px;\n" +
                "            width: 100px;\n" +
                "            height: 100px;\n" +
                "        }\n" +
                "        div.price {\n" +
                "            position: absolute;\n" +
                "            top: -1px;\n" +
                "            right: 50px;\n" +
                "            width: 70px;\n" +
                "            height: 100px;\n" +
                "        }\n" +
                "      \n" +
                "        .button2{\n" +
                "            background-color: #51B5C2; \n" +
                "            border: none;\n" +
                "            color: white;\n" +
                "            padding: 2px 5px;\n" +
                "            text-align: center;\n" +
                "            text-decoration: none;\n" +
                "            display: inline-block;\n" +
                "            font-size: 15px;\n" +
                "            margin: 2px 4px;\n" +
                "            cursor: pointer;\n" +
                "        }\n" +
                "        section{\n" +
                "          display: table-row;\n" +
                "        }\n" +
                "        .tooltip{\n" +
                "          position: relative;\n" +
                "          display: inline;\n" +
                "        }\n" +
                "        .tooltip .tooltiptext {\n" +
                "          visibility: hidden;\n" +
                "          width: 120px;\n" +
                "          background-color: black;\n" +
                "          color: #fff;\n" +
                "          text-align: center;\n" +
                "          border-radius: 6px;\n" +
                "          padding: 5px 0;\n" +
                "          position: absolute;\n" +
                "          z-index: 1;\n" +
                "        }\n" +
                "        .tooltip:hover .tooltiptext {\n" +
                "          visibility: visible;\n" +
                "        }\n" +
                "    </style>\n" +
                "<script>\n" +
                "  function total(){\n" +
                "    var num=document.getElementById(\"number1\").value;\n" +
                "    document.getElementById(\"basket\").innerHTML = num\n" +
                "    document.getElementById(\"basket\").style.fontFamily =\"Arial, Helvetica, sans-serif\";\n" +
                "  }\n" +
                "  function showPrice(){\n" +
                "    var c = document.getElementsByClassName(\"cost\");\n" +
                "      var q = document.getElementsByClassName(\"quantity\");\n" +
                "      var d = document.getElementsByClassName(\"demo\");\n" +
                "      for(var i=0;i<c.length;i++){\n" +
                "        d[i].innerHTML = parseFloat(c[i].value).toFixed(2)*parseFloat(q[i].value).toFixed(2)\n" +
                "        d[i].innerHTML=parseFloat(d[i].innerHTML).toFixed(2)\n" +
                "      }\n" +
                "  }\n" +
                " \n" +
                "    function loadPrice(){\n" +
                "      var c = document.getElementsByClassName(\"cost\");\n" +
                "      var q = document.getElementsByClassName(\"quantity\");\n" +
                "      var d = document.getElementsByClassName(\"demo\");\n" +
                "      for(var i=0;i<c.length;i++){\n" +
                "        d[i].innerHTML = parseFloat(c[i].value).toFixed(2)*parseFloat(q[i].value).toFixed(2)\n" +
                "        d[i].innerHTML=parseFloat(d[i].innerHTML).toFixed(2)\n" +
                "      }\n" +
                "    }\n" +
                "  window.onload = loadPrice;\n" +
                "  function passVal(n){\n" +
                "    removeItem(n);\n" +
                "  }\n" +
                "  function removeItem(n){\n" +
                "    var q = document.getElementsByClassName(\"quantity\");\n" +
                "    q[n].value = 0;\n" +
                "    showPrice();\n" +
                "  } \n" +
                "\n" +
                "</script>\n" +
                "</head>\n" +
                "<body>\n" +
                "<div class=\"navbar\">\n" +
                "    <a href=\"https://phabpharmacy.herokuapp.com/home\"><i class=\"fa fa-fw fa-home\"></i>Home</a>\n" +
                "    <div class=\"dropdown\">\n" +
                "        <button style=\"cursor: pointer;\" class= \"dropbtn\" onclick=\"redirectBrowse()\"><i class=\"fa fa-fw fa-search\"></i>Browse<i class=\"fa fa-caret-down\"></i></button>\n" +
                "            <a href=\"#cold_and_flu\">Cold and Flu</a>\n" +
                "            <a href=\"#skincare\">Skincare</a>\n" +
                "            <a href=\"#\">Headaches and Pain Relief</a>\n" +
                "            <a href=\"#\">Digestion</a>\n" +
                "            <a href=\"#\">Allergy</a>\n" +
                "            <a href=\"#\">First Aid</a>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "    <a href=\"https://phabpharmacy.herokuapp.com/login\"><i class=\"fa fa-fw fa-user\"></i>Login</a>\n" +
                "    <a href=\"https://phabpharmacy.herokuapp.com/register\"><i class=\"fa fa-fw fa-user-plus\"></i>Register</a>\n" +
                "    <a style=\"background-color: #00B8C5;\" class=\"fa fa-fw fa-shopping-basket\"><b id=\"basket\"></b></a> \n" +
                "</div>\n" +
                "<h1>Shopping Basket</h1>\n" +
                "\n" +
                "<pre>\n" +
                "<script>\n" +
                "  for(var i=0;i<3;i++){\n" +
                "  document.write(\"<style>p{font-family: Arial, Helvetica, sans-serif;}</style>\"+\n" +
                "  \"<div class=\\\"container\\\" +id=\\\"cont1\\\">\"+\n" +
                "  \"<p style=\\\"display: inline-block;\\\"><b>Vicks Vaporub</b><br>100g<br>£<output class=\\\"cost\\\" type=\\\"number\\\">4.50</output></p>\" +\n" +
                "  \"<div class=\\\"quant\\\">\" +\n" +
                "    \"<p>Quantity</p><input class=\\\"quantity\\\" onclick=\\\"showPrice()\\\" type=\\\"number\\\" min=\\\"1\\\" max=\\\"5\\\" value=\\\"2\\\"><button onclick=\\\"passVal(\"+i+\")\\\" class=\\\"button2\\\"><i class=\\\"fa fa-trash\\\" aria-hidden=\\\"true\\\"></i></button>\" +\n" +
                "  \"</div>\" + \n" +
                "  \"<div class=\\\"price\\\">\" +\n" +
                "   \"<p>Price<br><br>£<output class=\\\"demo\\\"></output></p>\" +\n" +
                "  \"</div>\"+\n" +
                "\"</div>\");\n" +
                "}\n" +
                "</script>\n" +
                "</pre>\n" +
                "</body>\n" +
                "</html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }
}