package Website.Servlets;

import Website.Entities.CreditCard;
import Website.Entities.Product;
import Website.Entities.User;
import Website.LoginDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DecimalFormat;


import static java.lang.String.valueOf;

@WebServlet(urlPatterns = {"/order"}, loadOnStartup = 0)
public class ServletOrder extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String HTML = htmlOutput();
        resp.getWriter().write(HTML);
        DecimalFormat df = new DecimalFormat("0.00"); //Allows us to output numerical values in a currency format
        Double totalBasket = LoginDAO.getBasketTotal(); //total cost of the basket
        CreditCard cc = LoginDAO.getCurrentCard();
        String total = df.format(totalBasket);
        User u = LoginDAO.getCurrentUser();
        resp.getWriter().write("<div class=\"addressContainer\">\n" +
                "  <p style=\"display: inline-block; margin-bottom: 0px;\"><b>Shipping Address</b></p>\n" +
                "  <p>" + u.fname + " " + u.lname + "<br>" + u.postcode + "</p>\n" +
                "  <p><b>Payment Details</b></p>\n" +
                "  <p>" + cc.getCensoredCardNumber() + "<br>" +cc.sortCode+ "<br>" + cc.accountNumber +"</p>\n" +
                "  <button onclick=\"window.location.href='https://phabpharmacy.herokuapp.com/amend_details';\"class=\"buttonStyle\">Edit Details</button>\n" +
                "  <div class=\"confirmContainer\">\n" +
                "  <p>Total Cost: <b>£" + total +"</b></p>\n" +
                "  <form id=\"confirmOrder\"  action=\"order\" method=\"post\">\n" + //A form allows us to to retrieve information added by the user (e.g. entering information)
                // 'action="order"' sends this information to ServletOrder (as its URL pattern is "/order"), 'method="post"' sends this to doPost function
                "    <input type=\"submit\" name=\"orderResponse\" class=\"buttonStyle\" value=\"Confirm Order\">\n" + //This is the button that the use confirm the order
                "  </form>\n" +
                "  </div>\n" +
                "</div>\n");
        int n = LoginDAO.tableSize("ordered_products");
        if(n>0) {
            resp.getWriter().write("<div class=\"basketContainer\">\n" +
                    " <p style=\"display: inline-block; margin-bottom: 0px;\"><b>Order Summary</b></p>\n" +
                    " <p>");
            for(int i=1;i<n+1;i++) {
            Product b = LoginDAO.getBasketInfo(i);
            String subtotal = valueOf(df.format(b.price*b.quantity));
            resp.getWriter().write(b.brand + " " + b.name + " " + b.amount + " - x" + b.quantity + " - £" + subtotal + "<br>");
            }
            resp.getWriter().write("</p>\n" +
                    "    <button onclick=\"window.location.href='https://phabpharmacy.herokuapp.com/basket';\" class=\"buttonStyle\">Edit Basket</button>\n" +
                    "</div>\n");
            resp.getWriter().write("<script>\n" +
                    "function redirectBrowse(){\n" +
                    "   window.location.href=\"https://phabpharmacy.herokuapp.com/browse\"\n" +
                    "}\n" +
                    "</script>\n" +
                    "</body>\n" +
                    "</html>");
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException { //No logout feature when confirming order
        resp.setContentType("text/html");
        String HTML = htmlOutput();
        resp.getWriter().write(HTML); //There is no information request in the ServletOrder doPost - the only possible way to redirect to this doPost in particular is through clicking 'Confirm Order' button
        //LoginDAO.resetTable("ordered_products");
        resp.getWriter().write("<h2>Order confirmed!</h2>");

        resp.getWriter().write("<script>\n" + //This is to finish of the HTML code initiated in htmlOutput()
                "    function redirectBrowse(){\n" +
                "        window.location.href=\"https://phabpharmacy.herokuapp.com/browse\"\n" +
                "    }\n" +
                "</script>\n" +
                "</body>\n" +
                "</html>");

    }
    public String htmlOutput(){
        boolean userLoggedIn = LoginDAO.checkLoggedIn(); //For certain pages (confirming order and amending user details) we do not want them to log out --> this code differs from the other servlets
        String displayCurrentUser = "<div class=\"currentUser\"><i class=\"fa fa-fw fa-user\"></i></div>";;
        User cUser = null;
        if (userLoggedIn == true) {
            cUser = LoginDAO.getCurrentUser();
            displayCurrentUser = "<div class=\"currentUser\">" + cUser.fname + "<i class=\"fa fa-fw fa-user\"></i></div>\n";
        }
        int basketSize = LoginDAO.getBasketSize();
        String basketSizeOut="";
        if (basketSize != 0){ basketSizeOut = String.valueOf(basketSize);}
        return "<!DOCTYPE html>\n" + //HTML comments are on the respective .jsp files (need updating)
                "<html>\n" +
                "<head>\n" +
                "    <meta charset=\"utf-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width\">\n" +
                "    <title>Confirm Order</title>\n" +
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
                "        }\n" +
                "        div.basketContainer{\n" +
                "          width: 350px;\n" +
                "          margin: 0px;\n" +
                "          padding: 0px 0px 20px 20px;\n" +
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
                "        div.confirmContainer{\n" +
                "          position: absolute;\n" +
                "          width: 270px;\n" +
                "          height: 70px;\n" +
                "          right: -1px;\n" +
                "          bottom: -87px;\n" +
                "          padding: 0px 0px 15px 20px;\n" +
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
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "<div class=\"navbar\">\n" +
                "    <a href=\"https://phabpharmacy.herokuapp.com/home\"><i class=\"fa fa-fw fa-home\"></i>Home</a>\n" +
                "    <div class=\"dropdown\">\n" +
                "        <button style=\"cursor: pointer;\" class=\"dropbtn\" onclick=\"redirectBrowse()\"><i class=\"fa fa-fw fa-search\"></i>Browse<i class=\"fa fa-caret-down\"></i></button>\n" +
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
                "    <a href=\"https://phabpharmacy.herokuapp.com/map\"><i class=\"fa fa-compass\" aria-hidden=\"true\"></i> In-Store</a>\n" +
                "    <a style=\"background-color: #00B8C5; width: 35px;\"><i style=\"width: 35px;\" class=\"fa fa-fw fa-shopping-basket\"><p style=\"display: inline; font-family: Arial; font-weight: bold\" id=\"basket\"> " + basketSizeOut + "</p></i></a>\n" +
                displayCurrentUser +
                "</div>\n" +
                "<h1>Confirm Order</h1>\n";

    }
}
