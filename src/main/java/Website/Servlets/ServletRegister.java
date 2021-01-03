package Website.Servlets;

import Website.Entities.EmailValidation;
import Website.Entities.User;
import Website.LoginDAO;

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
        //UserDB udb = new UserDB();
        resp.setContentType("text/html");
        String output = htmlOutput();
        resp.getWriter().write(output);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String logOut = req.getParameter("logOut");
        if (logOut.equals("Log Out")){
            LoginDAO.resetTable("logged");
        }
        String HTML = htmlOutput();
        String fn = req.getParameter("fname");
        String ln = req.getParameter("lname");
        String em = req.getParameter("email");
        String pw = req.getParameter("pass");
        String vpw = req.getParameter("verify_pass");
        String cn = req.getParameter("card_no");
        String ad = req.getParameter("postcode");
        resp.getWriter().write(HTML);
        EmailValidation emailCheck = new EmailValidation(em,pw,vpw);
        if(LoginDAO.validateRegister(em)){ //checks database see if email exists in use database
            resp.getWriter().write("<h2> There is an existing account with the email entered. Please log in.</h2>");
        }
        else if (fn.isEmpty() || ln.isEmpty() || em.isEmpty() || pw.isEmpty() || vpw.isEmpty() || cn.isEmpty() || ad.isEmpty()){
            resp.getWriter().write("<h2>Incomplete fields, please enter all the information.</h2>");
        }
        else if (!pw.equals(vpw)){
            resp.getWriter().write("<h2> Passwords don't match, please try again.</h2>");
        }
        else if(!emailCheck.validEmail()){
            resp.getWriter().write(emailCheck.getErrorMessage());
        }
        else{
            LoginDAO.addUser(fn,ln,em,pw,cn,ad);
            User currentUser = LoginDAO.getUser(em,pw);
            LoginDAO.setLoggedInUser(currentUser);
            LoginDAO.resetTable("basket");
            resp.getWriter().write("<h2>Successful registration. Welcome, " + currentUser.fname + "</h2>");
        }
    }

    public String htmlOutput(){
        boolean userLoggedIn = LoginDAO.checkLoggedIn();
        String displayCurrentUser = "";
        User cUser = null;
        if (userLoggedIn == true) { //If a user is logged in, userMessage will be displayed on the header (see line 144)
            cUser = LoginDAO.getCurrentUser();
            displayCurrentUser = "     <form name=\"logOut\" action=\"home\" method=\"post\">\n" +
                    "       <div style=\"float: right;\" class=\"currentUser\">" + cUser.fname + "<i class=\"fa fa-fw fa-user\"></i>\n" +
                    "           <div class=\"logOut\">\n" +
                    "               <input class=\"logOutButton\" type=\"submit\" name=\"Log Out\" value=\"Log Out\">\n" +
                    "           </div>\n" +
                    "       </div>\n" +
                    "    </form>\n";
        }
        else if (userLoggedIn == false){
            displayCurrentUser = "<div class=\"currentUser\"><i class=\"fa fa-fw fa-user\"></i></div>";
        }
        int basketSize = LoginDAO.getBasketSize();
        String basketSizeOut="";
        if (basketSize != 0){ basketSizeOut = String.valueOf(basketSize);}
        return "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "    <meta charset=\"utf-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width\">\n" +
                "    <title>Register</title>\n" +
                "    <link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css\">\n" +
                "    <style>\n" +
                "        body {font-family: Arial, Helvetica, sans-serif;}\n" +
                "        .navbar {\n" +
                "            width: 100%;\n" +
                "            background-color: #555;\n" +
                "            overflow: auto;\n" +
                "        }\n" +
                "        .navbar a {\n" +
                "          float: left;\n" +
                "          font-size: 16px;\n" +
                "          color: white;\n" +
                "          text-align: center;\n" +
                "          padding: 14px 16px;\n" +
                "          text-decoration: none;\n" +
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
                "        .currentUser{\n" +
                "            position: relative;\n" +
                "            float: right;\n" +
                "            font-size: 16px;\n" +
                "            color: white;\n" +
                "            text-align: center;\n" +
                "            padding: 14px 16px 4px 16px;\n" +
                "            text-decoration: none;\n" +
                "        }\n" +
                "        .logOut{\n" +
                "            position: absolute:\n" +
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
                "        }\n" +
                "        .buttonStyle{\n" +
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
                "        .logOut{\n" +
                "            position: absolute:\n" +
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
                "        }\n" +
                "        input, textarea{\n" +
                "            font-family: Arial, Helvetica, sans-serif;\n" +
                "            font-size: 16px;\n" +
                "            width: 26em; \n" +
                "            width: 40ch; \n" +
                "        }\n" +
                "        textarea::placeholder {\n" +
                "            font-family: Arial, Helvetica, sans-serif;\n" +
                "        }\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "<div class=\"navbar\">\n" +
                "    <a href=\"https://phabpharmacywebsite.herokuapp.com/home\"><i class=\"fa fa-fw fa-home\"></i>Home</a>\n" +
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
                "    <a style=\"background-color: #00B8C5;\"><i class=\"fa fa-fw fa-user-plus\"></i>Register</a>\n" +
                "    <a href=\"https://phabpharmacy.herokuapp.com/basket\"><i style=\"width: 35px;\" class=\"fa fa-fw fa-shopping-basket\"><p style=\"display: inline; font-family: Arial; font-weight: bold\" id=\"basket\"> " + basketSizeOut + "</p></i></a>\n" +
                displayCurrentUser +
                "</div>\n" +
                "\n" +
                "<h1>Register</h1>\n" +
                "<p> Register below. If you already have an account, <a href=\"https://phabpharmacy.herokuapp.com/login\"> login here.</a>\n" +
                "    <form name=\"registerForm\" action=\"register\" method=\"post\">\n" +
                "  <input type=\"text\" size=\"30\" class=\"form-control\" name=\"fname\" placeholder=\"First Name*\"><br>\n" +
                "  <input type=\"text\" size=\"30\" class=\"form-control\" name=\"lname\" placeholder=\"Last Name*\"><br>\n" +
                "  <input type=\"text\" size=\"30\" class=\"form-control\" name=\"email\" placeholder=\"Email Address*\"><br>\n" +
                "  <input type=\"text\" size=\"30\" class=\"form-control\" name=\"pass\" placeholder=\"Password*\"><br>\n" +
                "  <input type=\"text\" size=\"30\" class=\"form-control\" name=\"verifyPass\" placeholder=\"Verify Password*\"><br>\n" +
                "  <h3>Order Information<br><b style=\"font-size: 15px;\">Payment Information</b></h3>\n" +
                "\n" +
                "  <input type=\"text\" size=\"30\" class=\"form-control\" name=\"card_no\" placeholder=\"Card Number*\"><br>\n" +
                "  <input type=\"text\" size=\"30\" class=\"form-control\" name=\"sort_code\" placeholder=\"Sort Code*\"><br>\n" +
                "  <input type=\"text\" size=\"30\" class=\"form-control\" name=\"account_no\" placeholder=\"Account Number*\"><br>\n" +
                "  <input type=\"text\" size=\"30\" class=\"form-control\" name=\"cvv\" placeholder=\"CVV*\"><br>\n" +
                "\n" +
                "  <h3 style=\"font-size: 15px;\">Shipping Information</h3>\n" +
                "  <textarea name=\"addresss\" cols=\"30\" rows=\"4\" placeholder=\"Address\"></textarea><br>\n" +
                "  <input type=\"text\" size=\"30\" class=\"form-control\" name=\"postcode\" placeholder=\"Postcode*\"><br>\n" +
                "  <input type=\"text\" size=\"30\" class=\"form-control\" name=\"phone_no\" placeholder=\"Phone Number\"><br>\n" +
                "  \n" +
                "  <input type=\"submit\" class=\"buttonStyle\" value=\"Submit\">\n" +
                "</form>\n" +
                "<script>\n" +
                "    function redirectBrowse(){\n" +
                "        window.location.href=\"https://phabpharmacy.herokuapp.com/browse\"\n" +
                "    }\n" +
                "</script>\n" +
                "</body>\n" +
                "</html>";
    }
}
