import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns ={"/login"},loadOnStartup = 0)
public class SerlvetLogin extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.getWriter().write("<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "    <title>Login</title>\n" +
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
                "    </style>\n" +
                "    <script LANGUAGE=\"JavaScript\" type=\"text/javascript\">\n" +
                "        function display() {\n" +
                "            var a =\"username\";\n" +
                "            var b =\"pass1\";\n" +
                "            var x = validateInput();\n" +
                "            if (x==2){\n" +
                "                if (a == document.loginForm.user.value && b == document.loginForm.pass.value){\n" +
                "                    message = \"Login successful. Welcome back, \" + document.loginForm.user.value;\n" +
                "                    message += \"!\";\n" +
                "                    document.getElementById(\"demo\").innerHTML = message\n" +
                "                }\n" +
                "                else{\n" +
                "                    message = \"Incorrect login details. Please try again.\"\n" +
                "                    document.getElementById(\"demo\").innerHTML = message\n" +
                "                    setTimeout(refreshPage,2000)\n" +
                "                }\n" +
                "            }\n" +
                "            else{\n" +
                "                message = \"Incomplete fields, please enter all information.\";\n" +
                "                document.getElementById(\"demo\").innerHTML = message\n" +
                "            }\n" +
                "        }\n" +
                "        function refreshPage(){\n" +
                "            location.reload()\n" +
                "        }\n" +
                "        function validateInput(){\n" +
                "            var t=0;\n" +
                "            if(document.loginForm.user.value.trim()){t=t+1;}\n" +
                "            if(document.loginForm.pass.value.trim()){t=t+1;}\n" +
                "            return t;\n" +
                "        }\n" +
                "    </script>\n" +
                "</head>\n" +
                "<body>\n" +
                "<div class=\"navbar\">\n" +
                "    <a class=\"active\" href=\"https://cmdmcpharmacywebsite.herokuapp.com/home\"><i class=\"fa fa-fw fa-home\"></i> Home</a>\n" +
                "    <div class=\"dropdown\">\n" +
                "        <button class= \"dropbtn\" onclick=\"redirectBrowse()\"><i class=\"fa fa-fw fa-search\"></i>Browse<i class=\"fa fa-caret-down\"></i></button>\n" +
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
                "\n" +
                "<h1><center>Login</center></h1>\n" +
                "<p> Login below. If you haven't got an account, <a href=\"https://phabpharmacy.herokuapp.com/register\"> register here.</a> </p>\n" +
                "\n" +
                "<form name=\"loginForm\">\n" +
                "        <input type=\"text\" size=\"30\" class=\"form-control\" name=\"user\" placeholder=\"Username*\"><br>\n" +
                "        <input type=\"text\" size=\"30\" class=\"form-control\" name=\"pass\" placeholder=\"Password*\"><br>\n" +
                "        <p><input type=\"button\" value=\"Submit\" onClick=\"display();\"></p>\n" +
                "</form>\n" +
                "<p id=\"demo\"></p>\n" +
                "</body>\n" +
                "</html>");
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String usern = req.getParameter("user");
        String passw = req.getParameter("pass");
        resp.setContentType("text/html");
        resp.getWriter().write("<html><head><title>Login</title></head><body><h1>Login</h1><h2>Username: " + usern + "</h2></body></html>");
    }


}
