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
                "    <title>Home</title>\n" +
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
                "            background-color: #6CCCBF;\n" +
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
                "        .boxed{\n" +
                "            width: 200px;\n" +
                "            border: 1px solid black;\n" +
                "            padding: 15px 15px 10px 15px;\n" +
                "            margin: 5px;\n" +
                "            float: left;\n" +
                "        }\n" +
                "        .button2{\n" +
                "            background-color: #4CAF50; /* Green */\n" +
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
                "\n" +
                "</head>\n" +
                "<body>\n" +
                "<div class=\"navbar\">\n" +
                "    <a href=\"https://phabpharmacy.herokuapp.com/home\"><i class=\"fa fa-fw fa-home\"></i> Home</a>\n" +
                "    <div class=\"dropdown\">\n" +
                "        <button style=\"background-color: #6CCCBF;\"class= \"dropbtn\" <i class=\"fa fa-fw fa-search\"></i>Browse<i class=\"fa fa-caret-down\"></i></button>\n" +
                "        <div class=\"dropdown-content\">\n" +
                "            <a href=\"#\">Cold and Flu</a>\n" +
                "            <a href=\"#\">Skincare</a>\n" +
                "            <a href=\"#\">Headaches and Pain Relief</a>\n" +
                "            <a href=\"#\">Digestion</a>\n" +
                "            <a href=\"#\">Allergy</a>\n" +
                "            <a href=\"#\">First Aid</a>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "    <a href=\"https://phabpharmacy.herokuapp.com/login\"><i class=\"fa fa-fw fa-user\"></i> Login</a>\n" +
                "    <a href=\"https://phabpharmacy.herokuapp.com/register\"><i class=\"fa fa-fw fa-user-plus\"></i> Register</a>\n" +
                "</div>\n" +
                "<h2>Cold and Flu </h2>\n" +
                "<div id=\"div1\" class =\"boxed\">\n" +
                "    <label><center>Vicks Vaporub<br>100g</center></label><br>    <label><center>£4.50</center></label><br>\n" +
                "    <input type=\"number\" name =\"VicksV\" size=\"5\" min=\"1\" max=\"5\">\n" +
                "    <button class=\"button button2\">Add to Basket</button>\n" +
                "</div>\n" +
                "<div id=\"div2\" class =\"boxed\">\n" +
                "    <label><center>Vicks First Defence<br>15ml</center></label><br>    <label><center>£6.80</center></label><br>\n" +
                "    <input type=\"number\" name =\"VicksF\" size=\"5\" min=\"1\" max=\"5\">\n" +
                "    <button class=\"button button2\">Add to Basket</button>\n" +
                "</div>\n" +
                "\n" +
                "<div id=\"div3\" class =\"boxed\">\n" +
                "    <label><center>Gsk Night Nurse<br>160ml</center></label><br>\n" +
                "    <label><center>£8.50</center></label><br>\n" +
                "    <input type=\"number\" name =\"GskN\" size=\"5\" min=\"1\" max=\"5\">\n" +
                "    <button class=\"button button2\">Add to Basket</button>\n" +
                "</div>\n" +
                "<div id=\"div4\" class =\"boxed\">\n" +
                "    <label><center>Gsk Night Nurse<br>200ml</center></label><br>\n" +
                "    <label><center>£9.00</center></label><br>\n" +
                "    <input type=\"number\" name =\"GskN2\" size=\"5\" min=\"1\" max=\"5\">\n" +
                "    <button class=\"button button2\">Add to Basket</button>\n" +
                "</div>\n" +
                "\n" +
                "<div id=\"div5\" class =\"boxed\">\n" +
                "    <label><center>Lemsip Max<br>16 caps</center></label><br>\n" +
                "    <label><center>£4.20</center></label><br>\n" +
                "    <input type=\"number\" name =\"LemsipM\" size=\"5\" min=\"1\" max=\"5\">\n" +
                "    <button class=\"button button2\">Add to Basket</button>\n" +
                "</div>\n" +
                "<div id=\"div6\" class =\"boxed\">\n" +
                "    <label><center>Lemsip Standard<br>10 capsules</center></label><br>\n" +
                "    <label><center>£4.50</center></label><br>\n" +
                "    <input type=\"number\" name =\"LemsipS\" size=\"5\" min=\"1\" max=\"5\">\n" +
                "    <button class=\"button button2\">Add to Basket</button>\n" +
                "</div>\n" +
                "\n" +
                "<div id=\"div7\" class =\"boxed\">\n" +
                "    <label><center>Sudafed Day & Night<br>16 caps</center></label><br>\n" +
                "    <label><center>£4.50</center></label><br>\n" +
                "    <input type=\"number\" name =\"SudafedDN\" size=\"5\" min=\"1\" max=\"1\">\n" +
                "    <button class=\"button button2\">Add to Basket</button>\n" +
                "</div>\n" +
                "<div id=\"div8\" class =\"boxed\">\n" +
                "    <label><center>Sudafed Max<br>16 caps</center></label><br>\n" +
                "    <label><center>£4.20</center></label><br>\n" +
                "    <input type=\"number\" name =\"SudafedM\" size=\"5\" min=\"1\" max=\"1\">\n" +
                "    <button class=\"button button2\">Add to Basket</button>\n" +
                "</div>\n" +
                "\n" +
                "<div id=\"div9\" class =\"boxed\">\n" +
                "    <label><center>Benylin Mucus Relief<br>16 caps</center></label><br>\n" +
                "    <label><center>£4.80</center></label><br>\n" +
                "    <input type=\"number\" name =\"BenylinM\" size=\"5\" min=\"1\" max=\"5\">\n" +
                "    <button class=\"button button2\">Add to Basket</button>\n" +
                "</div>\n" +
                "<div id=\"div10\" class =\"boxed\">\n" +
                "    <label><center>Benylin 4 Flu<br>24 caps</center></label><br>\n" +
                "    <label><center>£6.00</center></label><br>\n" +
                "    <input type=\"number\" name =\"BenylinF\" size=\"5\" min=\"1\" max=\"5\">\n" +
                "    <button class=\"button button2\">Add to Basket</button>\n" +
                "</div>\n" +
                "\n" +
                "</body>\n" +
                "</html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }
}
