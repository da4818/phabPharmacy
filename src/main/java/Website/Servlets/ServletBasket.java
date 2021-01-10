package Website.Servlets;

import Website.Entities.Customer;
import Website.Entities.Product;
import Website.Entities.User;
import Website.Functions.UpdateQuantity;
import Website.LoginDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;

import static java.lang.String.valueOf;

@WebServlet(urlPatterns = {"/basket"},loadOnStartup = 0)
public class ServletBasket extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String HTML = htmlOutput();
        resp.getWriter().write(HTML);
        DecimalFormat df = new DecimalFormat("0.00");
        int n = LoginDAO.tableSize("ordered_product");
        if(n > 0){
            Double totalBasket = LoginDAO.getBasketTotal();
            String total = df.format(totalBasket);
            resp.getWriter().write("<div class=\"totalContainer\">\n" +
                    "  <p style=\"margin-bottom: 10px;\">Total: £" + total + "<br></p><a href=\"https://phabpharmacy.herokuapp.com/order\" class=\"buttonStyle\">Proceed to Checkout</a>\n" +
                    "</div>\n");
            for(int i=1;i<n+1;i++) {
                Product b = LoginDAO.getBasketInfo(i);
                String price = valueOf(df.format(b.price));
                String subtotal = valueOf(df.format(b.price*b.quantity));
                int max = b.limited ? 1 : 5;
                resp.getWriter().write("<section>" +
                        "<div class=\"basketContainer\" id=\"cont1\">\n");
                if(b.limited){
                    resp.getWriter().write("  <p class=\"tooltip\" style=\"display: inline-block;\"><b>" + b.brand + " " + b.name + "</b><br>" + b.amount + "<br>£<output type=\"number\">" + price + "</output><span class=\"tooltiptext\"><i>Limited to one per customer</i></span></p>\n");
                }
                else{
                    resp.getWriter().write("  <p style=\"display: inline-block;\"><b>" + b.brand + " " + b.name +"</b><br>" + b.amount + "<br>£<output type=\"number\">" + price + "</output></p>\n");
                }
                resp.getWriter().write("  <div class=\"quant\">\n" +
                        "    <form id=\"updateBasket\" action=\"basket\" method=\"post\"> \n" +
                        "    <label for=\"basketItemQuantity\">Qty</label><br>\n" +
                        "    <input type=\"number\" name=\"basketItemQuantity\" class=\"quantity\" size=\"3\" min=\"1\" max=\"" + max + "\" value=\"" + b.quantity + "\">\n" +
                        "    <input name=\"basketButtonNumber\" type=\"hidden\"value=\"" + i + "\">\n" +
                        "    <input name=\"basketItemId\" type=\"hidden\"value=\"" + b.barcode + "\">\n" +
                        "    <input name=\"update\" style=\"margin-left: 0px;\" type=\"submit\" class=\"buttonStyle\" value=\"Update\">\n" +
                        "    <input type=\"hidden\" name=\"logOut\" value=\"false\">\n" +
                        "    <button name=\"update\" type=\"submit\" class=\"buttonStyle\"><i class=\"fa fa-trash\" aria-hidden=\"true\"></i></button>\n" +
                        "    </form>\n" +
                        "  </div>\n" +
                        "  <div class=\"price\"><p>£<output></output>" + subtotal + "</p></div>\n" +
                        "</div>\n" +
                        "</section>");
            }
        }
        else{
            resp.getWriter().write("<p>Empty Basket"+n+"</p>");
            resp.getWriter().write("<div class=\"totalContainer\">\n" +
                    "  <p>Total: £0.00</p>\n" +
                    "</div>\n");
        }
        resp.getWriter().write("</body>\n</html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String logOut = req.getParameter("logOut");
        if (logOut.equals("Log Out")){
           LoginDAO.resetTable("logged_in_customer");
           //LoginDAO.resetTable("customer_basket");
        }
        String updateBasket = req.getParameter("update");
        int q = Integer.parseInt(req.getParameter("basketItemQuantity"));
        int basketButtonNumber = Integer.parseInt(req.getParameter("basketButtonNumber"));
        Product modifiedItem = LoginDAO.getBasketInfo(basketButtonNumber);
        if (updateBasket.equals("Update")) {
            LoginDAO.addToBasket(modifiedItem,q);
        }
        else {
            LoginDAO.removeFromBasket(modifiedItem.barcode);
        }
        String HTML = htmlOutput();
        resp.getWriter().write(HTML);
        int n = LoginDAO.tableSize("ordered_product");
        DecimalFormat df = new DecimalFormat("0.00");
        if(n > 0){
            Double totalBasket = LoginDAO.getBasketTotal();
            String total = df.format(totalBasket);
            resp.getWriter().write("<div class=\"totalContainer\">\n" +
                    "  <p style=\"margin-bottom: 10px;\">Total: £" + total + "<br></p><a href=\"https://phabpharmacy.herokuapp.com/order\" class=\"buttonStyle\">Proceed to Checkout</a>\n" +
                    "</div>\n");
            for(int i=1;i<n+1;i++) {
                Product b = LoginDAO.getBasketInfo(i);
                String price = valueOf(df.format(b.price));
                String subtotal = valueOf(df.format(b.price*b.quantity));
                int max = b.limited ? 1 : 5;
                resp.getWriter().write("<section>" +
                        "<div class=\"basketContainer\">\n");
                if(b.limited){
                    resp.getWriter().write("  <p class=\"tooltip\" style=\"display: inline-block;\"><b>" + b.brand + " " + b.name + "</b><br>" + b.amount + "<br>£<output type=\"number\">" + price + "</output><span class=\"tooltiptext\"><i>Limited to one per customer</i></span></p>\n");
                }
                else{
                    resp.getWriter().write("  <p style=\"display: inline-block;\"><b>" + b.brand + " " + b.name + "</b><br>" + b.amount + "<br>£<output type=\"number\">" + price + "</output></p>\n");
                }
                resp.getWriter().write("  <div class=\"quant\">\n" +
                        "    <form id=\"updateBasket\" action=\"basket\" method=\"post\"> \n" +
                        "    <label for=\"basketItemQuantity\">Qty</label><br>\n" +
                        "    <input type=\"number\" name=\"basketItemQuantity\" class=\"quantity\" size=\"3\" min=\"1\" max=\"" + max + "\" value=\"" + b.quantity + "\">\n" +
                        "    <input name=\"basketButtonNumber\" type=\"hidden\"value=\"" + i + "\">\n" +
                        "    <input name=\"basketItemId\" type=\"hidden\"value=\"" + b.barcode + "\">\n" +
                        "    <input name=\"update\" style=\"margin-left: 0px;\" type=\"submit\" class=\"buttonStyle\" value=\"Update\">\n" +
                        "    <button name=\"update\" type=\"submit\" class=\"buttonStyle\"><i class=\"fa fa-trash\" aria-hidden=\"true\"></i></button>\n" +
                        "    </form>\n" +
                        "  </div>\n" +
                        "  <div class=\"price\"><p>£<output></output>" + subtotal + "</p></div>\n" +
                        "</div>\n" +
                        "</section>");

                User u = LoginDAO.getCurrentUser();
                String place_order = req.getParameter("orderResponse");
                if (place_order.equals("orderResponse")){
                    String dbUrl = System.getenv("JDBC_DATABASE_URL");
                    try {
                        Class.forName("org.postgresql.Driver");
                        Connection db = DriverManager.getConnection(dbUrl);
                        Statement stmt = db.createStatement();
                        stmt.execute("INSERT INTO ordered_product (name,quantity,sell_price,orders_id) VALUES(b.name,b.quantity,b.price,u.id)");
                        UpdateQuantity update = new UpdateQuantity(b.name, b.brand, -b.quantity);
                    } catch (ClassNotFoundException | SQLException e) {
                        e.printStackTrace();
                    }
                }
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
        boolean userLoggedIn = LoginDAO.checkLoggedIn();
        String displayCurrentUser = "";
        User cUser = null;
        if (userLoggedIn == true) {
            cUser = LoginDAO.getCurrentUser();
            displayCurrentUser = "     <form name=\"logOut\" action=\"home\" method=\"post\">\n" +
                    "       <div style=\"float: right;\" class=\"currentUser\">" + cUser.fname +"<i class=\"fa fa-fw fa-user\"></i>\n" +
                    "        <div class=\"logOut\">\n" +
                    "          <input type=\"submit\" name=\"logOut\" class=\"logOutButton\" value=\"Log Out\">\n" +
                    "        </div>\n" +
                    "      </div>\n" +
                    "    </form>\n";
        }
        else if (userLoggedIn == false){
            displayCurrentUser = "<div class=\"currentUser\"><i class=\"fa fa-fw fa-user\"></i></div>\n";
        }
        int basketSize = LoginDAO.getBasketSize();
        String basketSizeOut="";
        if (basketSize != 0){ basketSizeOut = String.valueOf(basketSize);}
        return "<!DOCTYPE html>\n" + //HTML comments are on the respective .jsp files (need updating)
                "<html>\n" +
                "<head>\n" +
                "   <meta charset=\"utf-8\">\n" +
                "   <meta name=\"viewport\" content=\"width=device-width\">\n" +
                "   <title>Basket</title>\n" +
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
                "       .totalContainer{\n" +
                "           position: relative;\n" +
                "           float: right;\n" +
                "           width: 170px;\n" +
                "           height: 70px;\n" +
                "           border: 1px solid black;\n" +
                "           margin: 0px 50px 0px 0px;\n" +
                "           padding: 0px 0px 10px 15px;\n" +
                "       }\n" +
                "       .basketContainer{\n" +
                "           position: relative;\n" +
                "           width: 350px;\n" +
                "           height: 140px;\n" +
                "           border: 1px solid black;\n" +
                "           margin: 5px;\n" +
                "           padding: 0px 0px 0px 20px;\n" +
                "       }\n" +
                "       div.quant {\n" +
                "           position: absolute;\n" +
                "           bottom: -1px;\n" +
                "           left: -1px;\n" +
                "           width: 350px;\n" +
                "           height: 50px;\n" +
                "           padding-left: 20px;\n" +
                "           padding-top: 5px;\n" +
                "           border: 1px solid black\n" +
                "       }\n" +
                "       div.price {\n" +
                "           position: absolute;\n" +
                "           bottom: -1px;\n" +
                "           right: -1px;\n" +
                "           width: 80px;\n" +
                "           height: 45px;\n" +
                "       }\n" +
                "       .currentUser {\n" +
                "           position: relative;\n" +
                "           float: right;\n" +
                "           font-size: 16px;\n" +
                "           color: white;\n" +
                "           text-align: center;\n" +
                "           padding: 14px 14px 4px 16px;\n" +
                "           text-decoration: none;\n" +
                "       }\n" +
                "       .logOut {\n" +
                "           height: 10px;\n" +
                "           bottom: 0px;\n" +
                "           margin: 0px;\n" +
                "           border: none;\n" +
                "           background-color: transparent;\n" +
                "           border: none;\n" +
                "           font-size: 8px;\n" +
                "           color: white;\n" +
                "       }\n" +
                "       .logOutButton {\n" +
                "           background-color: transparent;\n" +
                "           font-size: 8px;\n" +
                "           width: 10;\n" +
                "           color: white;\n" +
                "           margin: 0px;\n" +
                "           border: none;\n" +
                "       }\n" +
                "       .buttonStyle {\n" +
                "           background-color: #51B5C2; \n" +
                "           border: none;\n" +
                "           color: white;\n" +
                "           padding: 2px 5px;\n" +
                "           text-align: center;\n" +
                "           text-decoration: none;\n" +
                "           display: inline-block;\n" +
                "           font-size: 15px;\n" +
                "           margin: 2px 4px;\n" +
                "           cursor: pointer;\n" +
                "       }\n" +
                "       section {\n" +
                "           display: table-row;\n" +
                "       }\n" +
                "       .tooltip {\n" +
                "           position: relative;\n" +
                "           display: inline;\n" +
                "       }\n" +
                "       .tooltip .tooltiptext {\n" +
                "           visibility: hidden;\n" +
                "           width: 120px;\n" +
                "           background-color: black;\n" +
                "           color: #fff;\n" +
                "           text-align: center;\n" +
                "           border-radius: 6px;\n" +
                "           padding: 5px 0;\n" +
                "           margin: 5px;\n" +
                "           bottom: -15px;\n" +
                "           position: absolute;\n" +
                "           z-index: 1;\n" +
                "       }\n" +
                "       .tooltip:hover .tooltiptext {\n" +
                "           visibility: visible;\n" +
                "       }\n" +
                "   </style>\n" +
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
                "    <a href=\"https://phabpharmacy.herokuapp.com/map\" ><i class=\"fa fa-compass\" aria-hidden=\"true\"></i> In-Store</a>\n" +
                "    <a href=\"https://phabpharmacy.herokuapp.com/basket\" style=\"background-color: #00B8C5; width: 35px;\"><i style=\"width: 35px;\" class=\"fa fa-fw fa-shopping-basket\"><p style=\"display: inline; font-family: Arial; font-weight: bold\" id=\"basket\"> " + basketSizeOut + "</p></i></a>\n" +
                displayCurrentUser +
                "</div>\n" +
                "<h1>Shopping Basket</h1>\n";
    }
}