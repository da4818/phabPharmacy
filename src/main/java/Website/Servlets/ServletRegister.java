package Website.Servlets;

import Website.Entities.*;
import Website.Functions.AddCustomer;
import Website.LoginDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/register"}, loadOnStartup = 0)
public class ServletRegister extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String output = htmlOutput();
        resp.getWriter().write(output);
        resp.getWriter().write("<script>\n" +
                "    function redirectBrowse(){\n" +
                "        window.location.href=\"https://phabpharmacy.herokuapp.com/browse\"\n" +
                "    }\n" +
                "</script>\n" +
                "</body>\n" +
                "</html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String logOut = req.getParameter("logOut");
        if (logOut.equals("Log Out")){
            LoginDAO.resetTable("logged_in_customer");
        }
        String HTML = htmlOutput();
        resp.getWriter().write(HTML);

        String fn = req.getParameter("fname");
        String ln = req.getParameter("lname");
        String em = req.getParameter("email");
        String pw = req.getParameter("pass");
        String vpw = req.getParameter("verify_pass");
        String cn = req.getParameter("card_no");
        String cvv = req.getParameter("cvv");
        String sc = req.getParameter("sort_code");
        String an = req.getParameter("account_no");
        String pc = req.getParameter("postcode");
        String ad = req.getParameter("address");
        String pn = req.getParameter("phone_no");

        EmailValidation emailCheck = new EmailValidation(em,pw,vpw);
        CreditCard cc = new CreditCard(cn,cvv,sc,an);
        Address a = new Address(ad,pc);
        boolean allowReg = true;
        if (fn.isEmpty() || ln.isEmpty() || em.isEmpty() || pw.isEmpty() || vpw.isEmpty() || pc.isEmpty() || cn.isEmpty() || cvv.isEmpty()|| sc.isEmpty() || an.isEmpty()){ //Checks if any of the required fields are empty
            resp.getWriter().write("<h2>Incomplete fields, please enter all the information.</h2>");
            allowReg = false;
        }
       // resp.getWriter().write("<p>List:<br>"+fn+"<br>"+ln+"<br>"+em+"<br>"+pw+"<br>"+vpw+"<br>"+cn+"<br>"+cvv+"<br>"+sc+"<br>"+an+"<br>"+pc+"<br>"+ad+"<br>"+pn+"<br>");
       else{
            if(LoginDAO.validateRegister(em)){ //Checks database to see if email exists in use database
                resp.getWriter().write("<h2>There is an existing account with the email entered, please log in.</h2>");
                allowReg = false;
            }
            if(!emailCheck.validEmail()){
                resp.getWriter().write(emailCheck.getErrorMessage());
                allowReg = false;
            }
            if (!pw.equals(vpw)){
                resp.getWriter().write("<h2>Passwords don't match, please try again.</h2>");
                allowReg = false;
            }

            if (!cc.validCardNumber() || !cc.validAccountNumber() || !cc.validSortCode() || !cc.validCvv()){
                resp.getWriter().write("<h2>Invalid card details, please try again.</h2>");
                allowReg = false;
            }
            if(!a.validPostcode()){
                resp.getWriter().write("<h2>Invalid postcode, please try again.</h2>");
                allowReg = false;
            }
            if (allowReg){
                //LoginDAO.addUser(fn,ln,em,pw,pc,ad,pn);
                Customer c = new Customer(fn,ln,pc,em,pw,ad,pn);
                //CreditCard cc = new CreditCard(cn,cvv,sc,an);
                new AddCustomer(c,cc);
                User currentUser = LoginDAO.getUser(em,pw); //*rewrite to constructor with string values
                LoginDAO.setLoggedInUser(currentUser);
                resp.getWriter().write("<h2>Successful registration. Welcome, " + currentUser.fname + "</h2>");
            }
        }

       resp.getWriter().write("<script>\n" +
               "    function redirectBrowse(){\n" +
               "        window.location.href=\"https://phabpharmacy.herokuapp.com/browse\"\n" +
               "    }\n" +
               "</script>\n" +
               "</body>\n" +
               "</html>");
    }
    //If a user is logged in, the current user and a 'log out' button will be displayed on the header (see line 144)
    public String htmlOutput(){
        boolean userLoggedIn = LoginDAO.checkLoggedIn();
        String displayCurrentUser = "<div class=\"currentUser\"><i class=\"fa fa-fw fa-user\"></i></div>\n";
        if (userLoggedIn) {
            User cUser = LoginDAO.getCurrentUser();
            displayCurrentUser = "     <form name=\"logOut\" action=\"home\" method=\"post\">\n" +
                    "       <div style=\"float: right;\" class=\"currentUser\">" + cUser.fname +"<i class=\"fa fa-fw fa-user\"></i>\n" +
                    "        <div class=\"logOut\">\n" +
                    "          <input type=\"submit\" name=\"logOut\" class=\"logOutButton\" value=\"Log Out\">\n" +
                    "        </div>\n" +
                    "      </div>\n" +
                    "    </form>\n";
        }
        int basketSize = LoginDAO.getBasketSize();
        String basketSizeOut = "";
        if (basketSize != 0){ basketSizeOut = String.valueOf(basketSize);}
        return "<!DOCTYPE html>\n" + //HTML comments are on the respective .jsp files (need updating)
                "<html>\n" +
                "<head>\n" +
                "   <meta charset=\"utf-8\">\n" +
                "   <meta name=\"viewport\" content=\"width=device-width\">\n" +
                "   <title>Register</title>\n" +
                "   <link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css\">\n" +
                "   <style>\n" +
                "       body {font-family: Arial, Helvetica, sans-serif;}\n" +
                "       .navbar {\n" +
                "           width: 100%;\n" +
                "           background-color: #555;\n" +
                "           overflow: auto;\n" +
                "       }\n" +
                "       .navbar a {\n" +
                "           float: left;\n" +
                "           font-size: 16px;\n" +
                "           color: white;\n" +
                "           text-align: center;\n" +
                "           padding: 14px 16px;\n" +
                "           text-decoration: none;\n" +
                "       }\n" +
                "       @media screen and (max-width: 500px) {\n" +
                "           .navbar a {\n" +
                "               float: none;\n" +
                "               display: block;\n" +
                "           }\n" +
                "       }\n" +
                "       .dropdown {\n" +
                "           float: left;\n" +
                "           overflow: hidden;\n" +
                "       }\n" +
                "       .dropdown .dropbtn {\n" +
                "           font-size: 16px;\n" +
                "           border: none;\n" +
                "           outline: none;\n" +
                "           color: white;\n" +
                "           padding: 14px 16px;\n" +
                "           background-color: inherit;\n" +
                "           font-family: inherit;\n" +
                "           margin: 0;\n" +
                "       }\n" +
                "       .navbar a:hover, .dropdown:hover .dropbtn {\n" +
                "           background-color: #000;\n" +
                "       }\n" +
                "       .dropdown-content {\n" +
                "           display: none;\n" +
                "           position: absolute;\n" +
                "           background-color: #f9f9f9;\n" +
                "           min-width: 160px;\n" +
                "           box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);\n" +
                "           z-index: 1;\n" +
                "       }\n" +
                "       .dropdown-content a {\n" +
                "           float: none;\n" +
                "           color: black;\n" +
                "           padding: 12px 16px;\n" +
                "           text-decoration: none;\n" +
                "           display: block;\n" +
                "           text-align: left;\n" +
                "       }\n" +
                "       .dropdown-content a:hover {\n" +
                "           background-color: #ddd;\n" +
                "       }\n" +
                "       .dropdown:hover .dropdown-content {\n" +
                "           display: block;\n" +
                "       }\n" +
                "       .currentUser{\n" +
                "           position: relative;\n" +
                "           float: right;\n" +
                "           font-size: 16px;\n" +
                "           color: white;\n" +
                "           text-align: center;\n" +
                "           padding: 14px 14px 4px 16px;\n" +
                "           text-decoration: none;\n" +
                "       }\n" +
                "       .logOut{\n" +
                "           height: 10px;\n" +
                "           bottom: 0px;\n" +
                "           margin: 0px;\n" +
                "           border: none;\n" +
                "           background-color: transparent;\n" +
                "           border: none;\n" +
                "           font-size: 8px;\n" +
                "           color: white;\n" +
                "       }\n" +
                "       .logOutButton{\n" +
                "           background-color: transparent;\n" +
                "           font-size: 8px;\n" +
                "           width: 10;\n" +
                "           color: white;\n" +
                "           margin: 0px;\n" +
                "           border: none;\n" +
                "       }\n" +
                "       textarea::placeholder {\n" +
                "           font-family: Arial, Helvetica, sans-serif;\n" +
                "           width: 40ch; \n" +
                "       }\n" +
                "       .buttonStyle {\n" +
                "           background-color: #00B8C5;\n" +
                "           border: none;\n" +
                "           color: white;\n" +
                "           padding: 5px 25px;\n" +
                "           text-align: center;\n" +
                "           text-decoration: none;\n" +
                "           display: inline-block;\n" +
                "           font-size: 16px;\n" +
                "           margin: 4px 0px;\n" +
                "           cursor: pointer;\n" +
                "       }\n" +
                "   </style>\n" +
                "\n" +
                "</head>\n" +
                "<body>\n" +
                "<div class=\"navbar\">\n" +
                "    <a href=\"https://phabpharmacy.herokuapp.com/home\"><i class=\"fa fa-fw fa-home\"></i>Home</a>\n" +
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
                "    <a href=\"https://phabpharmacy.herokuapp.com/map\"><i class=\"fa fa-compass\" aria-hidden=\"true\"></i> In-Store</a>\n" +
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
                "  <input type=\"text\" size=\"30\" class=\"form-control\" name=\"verify_pass\" placeholder=\"Verify Password*\"><br>\n" +
                "  <h3>Order Information<br><b style=\"font-size: 15px;\">Payment Information</b></h3>\n" +
                "\n" +
                "  <input type=\"text\" size=\"30\" class=\"form-control\" name=\"card_no\" placeholder=\"Card Number*\"><br>\n" +
                "  <input type=\"text\" size=\"30\" class=\"form-control\" name=\"sort_code\" placeholder=\"Sort Code*\"><br>\n" +
                "  <input type=\"text\" size=\"30\" class=\"form-control\" name=\"account_no\" placeholder=\"Account Number*\"><br>\n" +
                "  <input type=\"text\" size=\"30\" class=\"form-control\" name=\"cvv\" placeholder=\"CVV*\"><br>\n" +
                "\n" +
                "  <h3 style=\"font-size: 15px;\">Shipping Information</h3>\n" +
                "  <textarea style=\"width: 217px; font-family: Arial, Helvetica, sans-serif;\" name=\"address\" value=\"\" placeholder=\"Address\"></textarea><br>\n" +
                "  <input type=\"text\" size=\"30\" class=\"form-control\" name=\"postcode\" placeholder=\"Postcode*\"><br>\n" +
                "  <input type=\"text\" size=\"30\" class=\"form-control\" name=\"phone_no\" value=\"\" placeholder=\"Phone Number\"><br>\n" +
                "  \n" +
                "  <input type=\"hidden\" name=\"logOut\" value=\"false\">\n" +
                "  <input type=\"submit\" name=\"register\" style=\"width: 222px;\" class=\"buttonStyle\" value=\"Submit\">\n" +
                "</form>\n";
    }
}
