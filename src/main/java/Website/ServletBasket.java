package Website;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.text.DecimalFormat;

import static java.lang.String.valueOf;

@WebServlet(urlPatterns = "/basket",loadOnStartup = 0)
public class ServletBasket extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String HTML = htmlOutput();
        resp.getWriter().write(HTML);
        DecimalFormat df = new DecimalFormat("0.00");
        int n = LoginDAO.tableSize("basket");
        if(n > 0){
            Double totalBasket = LoginDAO.getBasketTotal();
            String total = df.format(totalBasket);
            resp.getWriter().write("<div class=\"totalContainer\">\n" +
                    "  <p style=\"padding-top: 5px;\">Total: £" + total + "</p>\n" +
                    "   <button href=\"https://phabpharmacy.herokuapp.com/order\" class=\"buttonStyle\">Proceed to Checkout</button>\n" +
                    "</div>\n");
            for(int i=1;i<n+1;i++) {
                Product b = LoginDAO.getBasketInfo(i);
                String price = valueOf(df.format(b.price));
                String subtotal = valueOf(df.format(b.price*b.quantity));
                int max = b.limited ? 1 : 5;
                resp.getWriter().write("<section>" +
                        "<div class=\"basketContainer\" id=\"cont1\">\n" +
                        "  <p style=\"display: inline-block;\"><b>" + b.name + "</b><br>" + b.description + "<br>£<output class=\\\"cost\\\" type=\"number\">" + price + "</output></p>\n" +
                        "  <div class=\"quant\">\n" +
                        "    <form id=\"updateBasket\" action=\"basket\" method=\"post\"> \n" +
                        "    <label for=\"q\">Qty</label><br>\n" +
                        "    <input type=\"number\" name=\"q\" class=\"quantity\" size=\"3\" min=\"1\" max=\"" + max + "\" value=\"" + b.quantity + "\">\n" +
                        "    <input name=\"basketNumber\" type=\"hidden\"value=\"" + i + "\">\n" +
                        "    <input name=\"update\" style=\"margin-left: 0px;\" type=\"submit\" class=\"buttonStyle\" value=\"Update\">\n" +
                        "    <button name=\"update\" type=\"submit\" class=\"buttonStyle\"><i class=\"fa fa-trash\" aria-hidden=\"true\"></i></button>\n" +
                        "    </form>\n" +
                        "  </div>\n" +
                        "  <div class=\"price\">\n" +
                        "    <p>£<output></output>" + subtotal + "</p>\n" +
                        "  </div>\n" +
                        "</div>\n" +
                        "</section>");
            }
        }
        else{
            resp.getWriter().write("<p>Empty Basket</p>");
            resp.getWriter().write("<div class=\"totalContainer\">\n" +
                    "  <p>Total: £0.00</p>\n" +
                    "</div>\n");
        }
        resp.getWriter().write("</body>\n</html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String t = req.getParameter("update");
        int q_in = Integer.parseInt(req.getParameter("q"));
        int basketId = Integer.parseInt(req.getParameter("basketNumber"));
        Product modifiedItem = LoginDAO.getBasketInfo(basketId);
        if (t.equals("Update")) {
            LoginDAO.addToBasket(modifiedItem,q_in);
        }
        else if(t == null){
        }
        else {
            LoginDAO.removeFromBasket(basketId);
        }
        String HTML = htmlOutput();
        resp.getWriter().write(HTML);
        DecimalFormat df = new DecimalFormat("0.00");
        int n = LoginDAO.tableSize("basket");
        if(n > 0){
            Double totalBasket = LoginDAO.getBasketTotal();
            String total = df.format(totalBasket);
            resp.getWriter().write("<div class=\"totalContainer\">\n" +
                    "  <p style=\"padding-top: 5px;\">Total: £" + total + "<br><button href=\"https://phabpharmacy.herokuapp.com/order\" class=\"buttonStyle\">Proceed to Checkout</button></p>\n" +
                    "</div>\n");
            for(int i=1;i<n+1;i++) {
                Product b = LoginDAO.getBasketInfo(i);
                String price = valueOf(df.format(b.price));
                String subtotal = valueOf(df.format(b.price*b.quantity));
                int max = b.limited ? 1 : 5;
                resp.getWriter().write("<section>" +
                        "<div class=\"basketContainer\" id=\"cont1\">\n" +
                        "  <p style=\"display: inline-block;\"><b>" + b.name + "</b><br>" + b.description + "<br>£<output class=\\\"cost\\\" type=\"number\">" + price + "</output></p>\n" +
                        "  <div class=\"quant\">\n" +
                        "    <form id=\"updateBasket\" action=\"basket\" method=\"post\"> \n" +
                        "    <label for=\"q\">Qty</label><br>\n" +
                        "    <input type=\"number\" name=\"q\" class=\"quantity\" size=\"3\" min=\"1\" max=\"" + max + "\" value=\"" + b.quantity + "\">\n" +
                        "    <input name=\"basketNumber\" type=\"hidden\"value=\"" + i + "\">\n" +
                        "    <input name=\"update\" style=\"margin-left: 0px;\" type=\"submit\" class=\"buttonStyle\" value=\"Update\">\n" +
                        "    <button name=\"update\" type=\"submit\" class=\"buttonStyle\"><i class=\"fa fa-trash\" aria-hidden=\"true\"></i></button>\n" +
                        "    </form>\n" +
                        "  </div>\n" +
                        "  <div class=\"price\">\n" +
                        "    <p>£<output></output>" + subtotal + "</p>\n" +
                        "  </div>\n" +
                        "</div>\n" +
                        "</section>");
            }
        }
        else{
            resp.getWriter().write("<p>Empty Basket</p>");
            resp.getWriter().write("<div class=\"totalContainer\">\n" +
                    "  <p>Total: £0.00</p>\n" +
                    "</div>\n");
        }
        resp.getWriter().write("</body>\n</html>");
    }


    public String htmlOutput(){
        int basketSize = LoginDAO.getBasketSize();
        String basketSizeOut="";
        if (basketSize != 0){ basketSizeOut = String.valueOf(basketSize);}
        return "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "    <meta charset=\"utf-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width\">\n" +
                "    <title>Basket</title>\n" +
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
                "        .totalContainer{\n" +
                "          position: relative;\n" +
                "          float: right;\n" +
                "          width: 170px;\n" +
                "          height: 50px;\n" +
                "          border: 1px solid black;\n" +
                "          margin: 0px 50px 0px 0px;\n" +
                "          padding: 0px 0px 0px 20px;\n" +
                "        }\n" +
                "        .basketContainer{\n" +
                "          position: relative;\n" +
                "          width: 350px;\n" +
                "          height: 140px;\n" +
                "          border: 1px solid black;\n" +
                "          margin: 5px;\n" +
                "          padding: 0px 0px 0px 20px;\n" +
                "        }\n" +
                "        div.quant {\n" +
                "            position: absolute;\n" +
                "            bottom: -1px;\n" +
                "            left: -1px;\n" +
                "            width: 350px;\n" +
                "            height: 50px;\n" +
                "            padding-left: 20px;\n" +
                "            padding-top: 5px;\n" +
                "            border: 1px solid black" +
                "        }\n" +
                "        div.price {\n" +
                "            position: absolute;\n" +
                "            bottom: -1px;\n" +
                "            right: -1px;\n" +
                "            width: 80px;\n" +
                "            height: 45px;\n" +
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
                "          padding: 5px 0;\n" +
                "          position: absolute;\n" +
                "          z-index: 1;\n" +
                "        }\n" +
                "        .tooltip:hover .tooltiptext {\n" +
                "          visibility: visible;\n" +
                "        }\n" +
                "    </style>\n" +
                "<script>\n" +
                "    function redirectBrowse(){\n" +
                "        window.location.href=\"https://phabpharmacy.herokuapp.com/browse\"\n" +
                "    }\n" +
                "\n" +
                "</script>\n" +
                "</head>\n" +
                "<body>\n" +
                "<div class=\"navbar\">\n" +
                "    <a href=\"https://phabpharmacy.herokuapp.com/home\"><i class=\"fa fa-fw fa-home\"></i>Home</a>\n" +
                "    <div class=\"dropdown\">\n" +
                "        <button style=\"cursor: pointer;\" class=\"dropbtn\" onclick=\"redirectBrowse()\"><i class=\"fa fa-fw fa-search\"></i>Browse<i class=\"fa fa-caret-down\"></i></button>\n" +
                "        <div class=\"dropdown-content\">\n" +
                "           <a href=\"https://phabpharmacy.herokuapp.com/browse#cold_and_flu\">Cold and Flu</a>\n" +
                "           <a href=\"https://phabpharmacy.herokuapp.com/browse#skincare\">Skincare</a>\n" +
                "           <a href=\"https://phabpharmacy.herokuapp.com/browse#headaches_and_pain_relief\">Headaches and Pain Relief</a>\n" +
                "           <a href=\"https://phabpharmacy.herokuapp.com/browse#digestion\">Digestion</a>\n" +
                "           <a href=\"https://phabpharmacy.herokuapp.com/browse#allergy\">Allergy</a>\n" +
                "           <a href=\"https://phabpharmacy.herokuapp.com/browse#first_aid\">First Aid</a>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "    <a href=\"https://phabpharmacy.herokuapp.com/login\"><i class=\"fa fa-fw fa-user\"></i>Login</a>\n" +
                "    <a href=\"https://phabpharmacy.herokuapp.com/register\"><i class=\"fa fa-fw fa-user-plus\"></i>Register</a>\n" +
                "    <a href=\"https://phabpharmacy.herokuapp.com/basket\" style=\"background-color: #00B8C5; width: 35px;\" class=\"fa fa-fw fa-shopping-basket\"><b style=\"font-family: Arial;\" id=\"basket\">" + basketSizeOut + "</b></a>\n" +
                "</div>\n" +
                "<h1>Shopping Basket</h1>\n";
    }
}
