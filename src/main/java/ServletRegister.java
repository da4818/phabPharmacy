import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/register", loadOnStartup = 0)
public class ServletRegister extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.getWriter().write("<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "    <meta charset=\"utf-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width\">\n" +
                "    <title>Register</title>\n" +
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
                "    </style>\n" +
                "\n" +
                "</head>\n" +
                "<body>\n" +
                "<div class=\"navbar\">\n" +
                "    <a href=\"https://cmdmcpharmacywebsite.herokuapp.com/home\"><i class=\"fa fa-fw fa-home\"></i>Home</a>\n" +
                "    <div class=\"dropdown\">\n" +
                "        <button style=\"cursor: pointer;\" class= \"dropbtn\" onclick=\"redirectBrowse()\"><i class=\"fa fa-fw fa-search\"></i>Browse<i class=\"fa fa-caret-down\"></i></button>\n" +
                "        <div class=\"dropdown-content\">\n" +
                "            <a href=\"https://phabpharmacy.herokuapp.com/browse#cold_and_flu\">Cold and Flu</a>\n" +
                "            <a href=\"https://phabpharmacy.herokuapp.com/browse#skincare\">Skincare</a>\n" +
                "            <a href=\"https://phabpharmacy.herokuapp.com/browse#headaches_and_pain_relief\">Headaches and Pain Relief</a>\n" +
                "            <a href=\"https://phabpharmacy.herokuapp.com/browse#digestion\">Digestion</a>\n" +
                "            <a href=\"https://phabpharmacy.herokuapp.com/browse#allergy\">Allergy</a>\n" +
                "            <a href=\"https://phabpharmacy.herokuapp.com/browse#first_aid\">First Aid</a>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "    <a href=\"https://phabpharmacy.herokuapp.com/login\"><i class=\"fa fa-fw fa-user\"></i>Login</a>\n" +
                "    <a class=\"active\"><i class=\"fa fa-fw fa-user-plus\"></i>Register</a>\n" +
                "</div>\n" +
                "\n" +
                "<h1><center>Register</center></h1>\n" +
                "<p> Register below. If you already have an account, <a href=\"https://phabpharmacy.herokuapp.com/login\"> login here.</a>\n" +
                "    <form name=\"registerForm\">\n" +
                "        <input type=\"text\" size=\"30\" class=\"form-control\" name=\"fname\" placeholder=\"First Name*\"><br>\n" +
                "        <input type=\"text\" size=\"30\" class=\"form-control\" name=\"lname\" placeholder=\"Last Name*\"><br>\n" +
                "        <input type=\"text\" size=\"30\" class=\"form-control\" name=\"user\" placeholder=\"Username*\"><br>\n" +
                "        <input type=\"text\" size=\"30\" class=\"form-control\" name=\"email\" placeholder=\"Email Address*\"><br>\n" +
                "        <input type=\"text\" size=\"30\" class=\"form-control\" name=\"pass\" placeholder=\"Password*\"><br>\n" +
                "        <input type=\"text\" size=\"30\" class=\"form-control\" name=\"verifyPass\" placeholder=\"Verify Password*\"><br>\n" +
                "        <p><input class=\"button button2\" value=\"Submit\" onClick=\"display();\"></p>\n" +
                "    </form>\n" +
                "<p id=\"demo\"></p>\n" +
                "\n" +
                "<script>\n" +
                "    function display() {\n" +
                "        var x = validateInput();\n" +
                "        if (x==6){\n" +
                "            if (document.registerForm.pass.value == document.registerForm.verifyPass.value){\n" +
                "                message = \"Registration successful. Welcome \" + document.registerForm.user.value;\n" +
                "                message += \"!\";\n" +
                "                document.getElementById(\"demo\").innerHTML = message\n" +
                "            }\n" +
                "            else if (document.registerForm.pass.value != document.registerForm.verifyPass.value){\n" +
                "\n" +
                "                message = \"Passwords don't match. Please try again.\";\n" +
                "                document.getElementById(\"demo\").innerHTML = message\n" +
                "            }\n" +
                "        }\n" +
                "        else{\n" +
                "            message = \"Incomplete fields, please enter all information.\";\n" +
                "            document.getElementById(\"demo\").innerHTML = message\n" +
                "        }\n" +
                "    }\n" +
                "    function validateInput(){\n" +
                "        var t=0;\n" +
                "        if(document.registerForm.fname.value.trim()){t=t+1;}\n" +
                "        if(document.registerForm.lname.value.trim()){t=t+1;}\n" +
                "        if(document.registerForm.user.value.trim()){t=t+1;}\n" +
                "        if(document.registerForm.email.value.trim()){t=t+1;}\n" +
                "        if(document.registerForm.pass.value.trim()){t=t+1;}\n" +
                "        if(document.registerForm.verifyPass.value.trim()){t=t+1;}\n" +
                "        return t;\n" +
                "    }\n" +
                "    function redirectBrowse(){\n" +
                "        window.location.href=\"https://phabpharmacy.herokuapp.com/browse\"\n" +
                "    }\n" +
                "</script>\n" +
                "</body>\n" +
                "</html>");
    }
}
