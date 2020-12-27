package Website;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/order", loadOnStartup = 0)
public class ServletOrder extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String HTML = htmlOutput();
        resp.getWriter().write(HTML);
        int basketSize = LoginDAO.tableSize("basket");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
    }
    public String htmlOutput(){
        int basketSize = LoginDAO.tableSize("basket");
        String basketSizeOut="";
        if (basketSize != 0){ basketSizeOut = String.valueOf(basketSize);}
        return "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "    <meta charset=\"utf-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width\">\n" +
                "    <title>Confirm Order</title>\n" +
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
                "        div.confirmContainer{\n" +
                "          float: right;\n" +
                "          width: 170px;\n" +
                "          height: 70px;\n" +
                "          margin: 0px 50px 0px 0px;\n" +
                "          padding: 0px 0px 15px 10px;\n" +
                "          border: 1px solid black;\n" +
                "        }\n" +
                "        div.basketContainer{\n" +
                "          width: 350px;\n" +
                "          margin: 0px;\n" +
                "          padding: 0px 0px 20px 20px;\n" +
                "          border: 1px solid black;\n" +
                "        }\n" +
                "        div.addressContainer {\n" +
                "            //position: absolute;\n" +
                "            width: 350px;\n" +
                "            height: 150px;\n" +
                "            margin: -1px 0px 0px 0px;\n" +
                "            padding: 0px 0px 20px 20px;\n" +
                "            border: 1px solid black;\n" +
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
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "<div class=\"navbar\">\n" +
                "    <a href=\"https://phabpharmacy.herokuapp.com/home\"><i class=\"fa fa-fw fa-home\"></i>Home</a>\n" +
                "    <div class=\"dropdown\">\n" +
                "        <button style=\"cursor: pointer;\" class= \"dropbtn\" onclick=\"redirectBrowse()\"><i class=\"fa fa-fw fa-search\"></i>Browse<i class=\"fa fa-caret-down\"></i></button>\n" +
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
                "    <a style=\"background-color: #51B5C2; width: 35px;\" class=\"fa fa-fw fa-shopping-basket\"><b style=\"font-family: Arial;\" id=\"basket\">" + basketSizeOut + "</b></a> \n" +
                "</div>\n" +
                "<h1>Confirm Order</h1>\n" +
                "<div class=\"confirmContainer\">\n" +
                "  <form action=\"/order\" method=\"post\"\n" +
                "  <p>Total Cost</p>\n" +
                "  <input type=\"submit\" class=\"buttonStyle\" value=\"Confirm Order\">\n" +
                "</div>\n" +
                "  <div class=\"basketContainer\" id=\"cont1\">\n" +
                "  <p style=\"display: inline-block; margin-bottom: 0px;\"><b>Order Summary</b></p>\n" +
                "  <p>Item Quantity Subtotal<br>Item Quantity Subtotal<br>Item Quantity Subtotal<br>Item Quantity Subtotal<br>Item Quantity Subtotal<br>Item Quantity Subtotal</p>\n" +
                "  \n" +
                "  <form action=\"/basket\" method=\"post\">\n" +
                "    <input type=\"submit\" class=\"buttonStyle\" value=\"Edit Basket\">\n" +
                "  </form>\n" +
                "</div>\n" +
                "<div class=\"addressContainer\">\n" +
                "  <form id=\"updateBasket\" method=\"post\"> \n" +
                "  <p style=\"display: inline-block; margin-bottom: 0px;\"><b>Shipping Adress</b></p>\n" +
                "  <p>Name<br>Shipping Address<br>Payment</p>\n" +
                "  <input type=\"submit\" class=\"buttonStyle\" value=\"Edit Details\">\n" +
                "  </form>\n" +
                "</div>\n" +
                "\n" +
                "<script>\n" +
                "    function redirectBrowse(){\n" +
                "        window.location.href=\"https://phabpharmacy.herokuapp.com/browse\"\n" +
                "    }\n" +
                "</script>\n" +
                "</body>\n" +
                "</html>";
    }
}
