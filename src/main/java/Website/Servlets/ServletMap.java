package Website.Servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet (urlPatterns = "/map",loadOnStartup = 0)
public class ServletMap extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String HTML = htmlOutput();
        resp.getWriter().write(HTML);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String out = req.getParameter("category");
        resp.getWriter().write("<p>output:" + out + "</p>");
    }
    public String htmlOutput(){
        return "<!DOCTYPE html>\n" +
                "<html>\n" +
                "  <head>\n" +
                "    <meta charset=\"utf-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width\">\n" +
                "    <title>Find Items In-Store</title>\n" +
                "    <link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css\">\n" +
                "    <style>\n" +
                "      body {font-family: Arial, Helvetica, sans-serif;}\n" +
                "      .navbar {\n" +
                "            width: 100%;\n" +
                "            background-color: #555;\n" +
                "            overflow: auto;\n" +
                "        }\n" +
                "      .navbar a {\n" +
                "            float: left;\n" +
                "            font-size: 16px;\n" +
                "            color: white;\n" +
                "            text-align: center;\n" +
                "            padding: 14px 16px;\n" +
                "            text-decoration: none;\n" +
                "      }\n" +
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
                "        .currentUser{ \n" +
                "            position: relative;\n" +
                "            float: right;\n" +
                "            font-size: 16px;\n" +
                "            color: white;\n" +
                "            text-align: center;\n" +
                "            padding: 14px 16px 4px 16px;\n" +
                "            text-decoration: none;\n" +
                "        }\n" +
                "        .logOut{\n" +
                "            position: absolute;\n" +
                "            height: 10px;\n" +
                "            bottom: 0px;\n" +
                "            margin: 0px;\n" +
                "            border: none;\n" +
                "            background-color: transparent;\n" +
                "            border: none;\n" +
                "            font-size: 8px;\n" +
                "            color: white;\n" +
                "        }\n" +
                "        .logOutButton{\n" +
                "            background-color: transparent;\n" +
                "            font-size: 8px;\n" +
                "            color: white;\n" +
                "            margin: 0px;\n" +
                "            border: none;\n" +
                "        } \n" +
                "      .section{\n" +
                "        display: table-row;\n" +
                "      }\n" +
                "      .images{\n" +
                "        display: table-row;\n" +
                "        float: right;           \n" +
                "        margin-right: 15%;\n" +
                "      }\n" +
                "      .box {\n" +
                "        float: left;\n" +
                "        padding: 2px 5px;\n" +
                "        margin-left: 10px;\n" +
                "        margin-top: 10px;\n" +
                "      }\n" +
                "      .buttonStyle{\n" +
                "            background-color: #00B8C5;\n" +
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
                "    </style>\n" +
                "  </head>\n" +
                "  <body>\n" +
                "    <div class=\"navbar\">\n" +
                "    <a><i class=\"fa fa-fw fa-home\"></i>Home</a> \n" +
                "    <div class=\"dropdown\">\n" +
                "        <button style=\"cursor: pointer;\" class= \"dropbtn\" onclick=\"redirectBrowse()\"><i class=\"fa fa-fw fa-search\"></i>Browse<i class=\"fa fa-caret-down\"></i></button>\n" +
                "        <div class=\"dropdown-content\">\n" +
                "            <a href=\"https://phabpharmacy.herokuapp.com/browse#cold_and_flu\">Cold and Flu</a>\n" +
                "            <a href=\"https://phabpharmacy.herokuapp.com/browse#skincare\">Skincare</a>\n" +
                "            <a href=\"https://phabpharmacy.herokuapp.com/browse#headaches_and_pain_relief\">Headaches and Pain Relief</a>\n" +
                "            <a href=\"https://phabpharmacy.herokuapp.com/browse#digestion\">Digestion</a>\n" +
                "            <a href=\"https://phabpharmacy.herokuapp.com/browse#allergy\">Allergy</a>\n" +
                "            <a href=\"https://phabpharmacy.herokuapp.com/browse#first_aid\">First Aid</a> \n" +
                "            \n" +
                "        </div>\n" +
                "    </div>\n" +
                "    <a href=\"https://phabpharmacy.herokuapp.com/login\"><i class=\"fa fa-fw fa-user\"></i>Login</a>\n" +
                "    <a href=\"https://phabpharmacy.herokuapp.com/register\"><i class=\"fa fa-fw fa-user-plus\"></i>Register</a>\n" +
                "    <a href=\"https://phabpharmacy.herokuapp.com/map\" style=\"background-color: #00B8C5\"><i class=\"fa fa-compass\" aria-hidden=\"true\"></i> In-Store</a>\n" +
                "    <a href=\"https://phabpharmacy.herokuapp.com/basket\" style=\"width: 35px;\" class=\"fa fa-fw fa-shopping-basket\"><b id=\"basket\"></b></a>\n" +
                "    <form name=\"logOut\" action=\"home\" method=\"post\"> \n" +
                "        <div style=\"float: right;\" class=\"currentUser\">Luke<i class=\"fa fa-fw fa-user\"></i>\n" +
                "            <div class=\"logOut\">\n" +
                "                <input class=\"logOutButton\" type=\"submit\" name=\"logOut\" value=\"Log Out\">\n" +
                "            </div>\n" +
                "        </div>\n" +
                "    </form>\n" +
                "    </div>\n" +
                "    \n" +
                "    <h1>Find Items in Store</h1>\n" +
                "    <form id=\"b\">\n" +
                "    <input id=\"demo\" type=\"text\">\n" +
                "    </form>\n" +
                "    <p>Choose a category to see all of items in the section and their in-store (Paddington branch) location.</p>\n" +
                "    <section>\n" +
                "      <div class=\"box\" style=\"padding-top: 12px;\">\n" +
                "        <div class=\"dropdown\">\n" +
                "          <button class=\"buttonStyle\" style=\"dropbtn; width: 222px;\">Categories</button>\n" +
                "          <div class=\"dropdown-content\">\n" +
                "            <form id=\"findCategory\" action=\"map\" method=\"post\">\n" +
                "            <a id=\"cf\" href=\"#cold_and_flu\" >Cold and Flu</a>\n" +
                "            <a id=\"s\" href=\"#skincare\">Skincare</a>\n" +
                "            <a id=\"hpr\">Headaches and Pain Relief</a>\n" +
                "            <a id=\"d\">Digestion</a>\n" +
                "            <a id=\"a\">Allergy</a>\n" +
                "            <a id=\"fa\">First Aid</a>\n" +
                "            <input id=\"category\" name=\"category\" type=\"hidden\">\n" +
                "            </form>  \n" +
                "          </div>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "    </section>\n" +
                "    <section>\n" +
                "      <div class=\"box\">\n" +
                "        <h3>Products in Cold and Flu:</h3>\n" +
                "        <p>Vicks Vaporub 100g<br>Vicks First Defence 15ml<br>Item</p>\n" +
                "      </div>\n" +
                "    </section>\n" +
                "  \n" +
                "  <img class=\"images\" style=\"float: right; margin:\"src=\"map.png\" alt=\"Girl in a jacket\" width=\"237\" height=\"300\">\n" +
                "\n" +
                " \n" +
                "<script>\n" +
                "   document.getElementById(\"cf\").onclick = function() {\n" +
                "       var x = document.getElementById(\"cf\").innerHTML;\n" +
                "       document.getElementById(\"category\").value = x\n" +
                "       document.getElementById(\"findCategory\").submit();\n" +
                "   }\n" +
                "   document.getElementById(\"s\").onclick = function() {\n" +
                "       var x = document.getElementById(\"s\").innerHTML;\n" +
                "       document.getElementById(\"category\").value = x\n" +
                "   document.getElementById(\"findCategory\").submit();\n" +
                "   }\n" +
                "   document.getElementById(\"hpr\").onclick = function() {\n" +
                "       var x = document.getElementById(\"hpr\").innerHTML;\n" +
                "       document.getElementById(\"category\").value = x\n" +
                "       document.getElementById(\"findCategory\").submit();\n" +
                "   }\n" +
                "   document.getElementById(\"d\").onclick = function() {\n" +
                "       var x = document.getElementById(\"d\").innerHTML;\n" +
                "       document.getElementById(\"category\").value = x\n" +
                "       document.getElementById(\"findCategory\").submit();\n" +
                "   }\n" +
                "   document.getElementById(\"a\").onclick = function() {\n" +
                "       var x = document.getElementById(\"a\").innerHTML;\n" +
                "       document.getElementById(\"category\").value = x\n" +
                "       document.getElementById(\"findCategory\").submit();\n" +
                "   }\n" +
                "   document.getElementById(\"fa\").onclick = function() {\n" +
                "       var x = document.getElementById(\"fa\").innerHTML;\n" +
                "       document.getElementById(\"category\").value = x\n" +
                "       document.getElementById(\"findCategory\").submit();\n" +
                "   }\n" +
                "</script>\n" +
                "</body>\n" +
                "</html>";
    }
}
