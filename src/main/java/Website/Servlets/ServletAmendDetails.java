package Website.Servlets;

import Website.Entities.Address;
import Website.Entities.CreditCard;
import Website.Entities.User;
import Website.LoginDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/amend_details"},loadOnStartup = 0)
public class ServletAmendDetails extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String HTML = htmlOutput();
        resp.getWriter().write(HTML);
        CreditCard cc = LoginDAO.getCurrentCard();
        User u = LoginDAO.getCurrentUser();

        String cardNoOut = "Card Number";
        String cvvOut = "CVV";
        String sortcodeOut = "Sort Code";
        String accountNoOut = "Account Number";
        String postcodeOut = "Postcode";
        String addressOut = "Address";
        String phoneNoOut = "Phone Number";
        // Fill placeholder text with the current information - to remind the user of the currently stored details
        switch(u.nonNullEntries()){ // Due to nullPointExceptions, we need to check if 'phone_no' and/or 'address' is null within the database
            case 'b':
                Address ad = new Address(u.address,u.postcode);
                addressOut = ad.displayAddress(2);
                phoneNoOut = u.phoneno;
                break;
            case 'a':
                Address ad1 = new Address(u.address,u.postcode);
                addressOut = ad1.displayAddress(2);
                break;
            case 'p':
                phoneNoOut = u.phoneno;
                break;
            default:
                break;
        }
        cardNoOut = cc.cardNumber;
        cvvOut = cc.cvv;
        accountNoOut = cc.accountNumber;
        sortcodeOut = cc.sortCode;
        postcodeOut = u.postcode;

        resp.getWriter().write("<form name=\"amendDetailsForm\" action=\"amend_details\" method=\"post\">\n" +
                "  <h3>Order Information<br><b style=\"font-size: 15px;\">Payment Information</b></h3>\n" +
                "  <label>Card Number</label><br>\n" +
                "  <input type=\"text\" size=\"30\" class=\"form-control\" name=\"card_no\" placeholder=\"" + cardNoOut + "\"><br>\n" +
                "  <label>Sort Code</label><br>\n" +
                "  <input type=\"text\" size=\"30\" class=\"form-control\" name=\"sort_code\" placeholder=\"" + sortcodeOut + "\"><br>\n" +
                "  <label>Account Number</label><br>\n" +
                "  <input type=\"text\" size=\"30\" class=\"form-control\" name=\"account_no\" placeholder=\"" + accountNoOut + "\"><br>\n" +
                "  <label>CVV</label><br>\n" +
                "  <input type=\"text\" size=\"30\" class=\"form-control\" name=\"cvv\" placeholder=\"" + cvvOut + "\"><br>\n" +
                "\n" +
                "  <h3 style=\"font-size: 15px;\">Shipping Information</h3>\n" +
                "  <label>Address</label><br>\n" +
                "  <textarea name=\"address\" style=\"width: 217px; font-family: Arial, Helvetica, sans-serif;\" rows=\"4\" value=\"\" placeholder=\"" + addressOut + "\"></textarea><br>\n" +
                "  <label>Postcode</label><br>\n" +
                "  <input type=\"text\" size=\"30\" class=\"form-control\" name=\"postcode\" placeholder=\"" + postcodeOut + "\"><br>\n" +
                "  <label>Phone Number</label><br>\n" +
                "  <input type=\"text\" size=\"30\" class=\"form-control\" value=\"\" name=\"phone_no\" placeholder=\"" + phoneNoOut +"\"><br>\n" +
                "  \n" +
                "  <input name=\"update\" type=\"submit\" style=\"width: 135px; margin-left: 0px;\" class=\"buttonStyle\" value=\"Update Details\">\n" +
                "  <a class=\"buttonStyle\" style=\"width: 69px; margin-left: 1px;\" href=\"https://phabpharmacy.herokuapp.com/order\">Cancel</a>\n" +
                "</form>\n" +
                "<script>\n" +
                "   function refreshPage(){\n" +
                "       setTimeout(function(){location.reload();},2000)\n" +
                "   }\n" +
                "</script>\n" +
                "</body>\n" +
                "</html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String HTML = htmlOutput();
        resp.getWriter().write(HTML);
        String cn = req.getParameter("card_no");
        String sc = req.getParameter("sort_code");
        String an = req.getParameter("account_no");
        String cvv = req.getParameter("cvv");
        String pc = req.getParameter("postcode");
        String ad = req.getParameter("address");
        String pn = req.getParameter("phone_no");
        // Perform checks to make sure user inputs are valid - card number
        CreditCard cc = new CreditCard(cn,cvv,sc,an);
        Address a = new Address(ad,pc);
        boolean valid = true;
        if (!cc.cardNumber.isEmpty() && !cc.validCardNumber() || !cc.accountNumber.isEmpty() && !cc.validAccountNumber() || !cc.sortCode.isEmpty() && !cc.validSortCode() || !cc.cvv.isEmpty()&& !cc.validCvv()){
            resp.getWriter().write("<h2 name=\"amendDetailsResponse\">Invalid card details, please try again.</h2>");
            valid = false;
        }
        if(!a.postcode.isEmpty() && !a.validPostcode()){
            resp.getWriter().write("<h2 name=\"amendDetailsResponse\">Invalid postcode, please try again.</h2>");
            valid = false;
        }
        if (valid) {
            if(!pc.isEmpty()) {
                LoginDAO.updateCustomer("customer", "postcode", pc);
            }
            if(!ad.isEmpty()) {
                LoginDAO.updateCustomer("customer", "address", ad);
            }
            if(!pn.isEmpty()) {
                LoginDAO.updateCustomer("customer", "phone_no", pn);
            }
            if(!cn.isEmpty()) {
                LoginDAO.updateCustomer("card_details", "card_no", cn);
            }
            if(!cvv.isEmpty()) {
                LoginDAO.updateCustomer("card_details", "cvv", cvv);
            }
            if(!an.isEmpty()) {
                LoginDAO.updateCustomer("card_details", "account_no", an);
            }
            if(!sc.isEmpty()) {
                LoginDAO.updateCustomer("card_details", "sort_code", sc);
            }
            resp.getWriter().write("<h2 name=\"amendDetailsResponse\" onload=\"refreshPage()\">Information updated.</h2>\n" +
                    "<p onload=\"refreshPage()\">You will be redirected back to the order summary.</p>\n" +
                    "<script>\n" +
                    "   function refreshPage(){\n" +
                    "       setTimeout(function(){location.reload();},3000)\n" +
                    "   }\n" +
                    "</script>\n" +
                    "</body>\n" +
                    "</html>");
        }
    }



    String htmlOutput(){
        boolean userLoggedIn = LoginDAO.checkLoggedIn(); //For certain pages (confirming order and amending user details) we do not want them to log out --> this code differs from the other servlets
        String displayCurrentUser = "<div class=\"currentUser\"><i class=\"fa fa-fw fa-user\"></i></div>"; //The use should always be logged in, so this should never account (placed in case of any unexpected outcomes)
        if (userLoggedIn == true) {
            User cUser = LoginDAO.getCurrentUser();
            displayCurrentUser = "<div class=\"currentUser\">" + cUser.fname + "<i class=\"fa fa-fw fa-user\"></i></div>\n";
        }
        int basketSize = LoginDAO.getBasketSize();
        String basketSizeOut="";
        if (basketSize != 0){ basketSizeOut = String.valueOf(basketSize);}
        return "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "   <meta charset=\"utf-8\">\n" +
                "   <meta name=\"viewport\" content=\"width=device-width\">\n" +
                "   <title>Amend Details</title>\n" +
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
                "        div.confirmContainer{\n" +
                "          position: absolute;\n" +
                "          width: 270px;\n" +
                "          height: 70px;\n" +
                "          right: -1px;\n" +
                "          bottom: -87px;\n" +
                "          padding: 0px 0px 15px 20px;\n" +
                "          border: 1px solid black;\n" +
                "        }\n" +
                "        div.addressContainer {\n" +
                "          position: relative;\n" +
                "          width: 270px;\n" +
                "          float: right;\n" +
                "          padding: 0px 0px 10px 20px;\n" +
                "          margin: 0px 50px 0px 0px;\n" +
                "          border: 1px solid black;\n" +
                "        }\n" +
                "        div.basketContainer{\n" +
                "          width: 350px;\n" +
                "          margin: 0px;\n" +
                "          padding: 0px 0px 10px 20px;\n" +
                "          border: 1px solid black;\n" +
                "        }\n" +
                "        .buttonStyle{\n" +
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
                "          padding: 5px 0px;\n" +
                "          position: absolute;\n" +
                "          z-index: 1;\n" +
                "        }\n" +
                "        .tooltip:hover .tooltiptext {\n" +
                "          visibility: visible;\n" +
                "        }\n" +
                "       .currentUser {\n" +
                "           position: relative;\n" +
                "           float: right;\n" +
                "           font-size: 16px;\n" +
                "           color: white;\n" +
                "           text-align: center;\n" +
                "           padding: 14px 14px 4px 16px;\n" +
                "           text-decoration: none;\n" +
                "       }\n" +
                "       textarea::placeholder {\n" +
                "           font-family: Arial, Helvetica, sans-serif;\n" +
                "           width: 40ch; \n" +
                "       }\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "<div class=\"navbar\">\n" +
                "    <a href=\"https://phabpharmacy.herokuapp.com/home\"><i class=\"fa fa-fw fa-home\"></i>Home</a>\n" +
                "    <div class=\"dropdown\">\n" +
                "        <button name=\"Browse\" style=\"cursor: pointer;\" class=\"dropbtn\" onclick=\"redirectBrowse()\"><i class=\"fa fa-fw fa-search\"></i>Browse<i class=\"fa fa-caret-down\"></i></button>\n" +
                "        <div class=\"dropdown-content\">\n" +
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
                "    <a href=\"https://phabpharmacy.herokuapp.com/map\"><i class=\"fa fa-compass\" aria-hidden=\"true\"></i>In-Store</a>\n" +
                "    <a href=\"https://phabpharmacy.herokuapp.com/basket\"name=\"Basket\" style=\"background-color: #00B8C5;\"><i style=\"width: 35px;\" class=\"fa fa-fw fa-shopping-basket\"><p style=\"display: inline; font-family: Arial; font-weight: bold;\" id=\"basket\"> " + basketSizeOut + "</p></i></a>\n" +
                displayCurrentUser +
                "</div>\n" +
                "<h1>Amend Details</h1>\n" +
                "<p>Change any details as required (empty fields will not be updated). If everything is already up to date, press 'Cancel' to go back.</p>";
    }
}
