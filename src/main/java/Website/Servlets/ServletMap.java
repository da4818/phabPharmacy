package Website.Servlets;

import Website.Entities.Product;
import Website.Entities.User;
import Website.LoginDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.*;
import java.io.File;
import java.io.IOException;

@WebServlet (urlPatterns = {"/map", "/map/cold_and_flu", "/map/skincare","/map/headaches_and_pain_relief","/map/digestion","/map/allergy","/map/first_aid"},loadOnStartup = 0)
public class ServletMap extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String HTML = htmlOutput();
        resp.getWriter().write(HTML);
        if(req.getServletPath().equals("/map")){
            resp.getWriter().write("<h3> </h3>" + "<div class=\"box\">\n" + "</div>\n" +
                    "<img class=\"images\" src=\"https://bit.ly/main_map\" alt=\"Paddington Store\" width=\"316\" height=\"400\">\n");
        } // Embedded photo url for main store

        else if(req.getServletPath().equals("/map/cold_and_flu")){
            resp.getWriter().write("<section>" +
                    "<div class=\"box\">\n" +
                    "<h3>Products in Cold and Flu</h3>\n");
            int j = 1;
            Product p = LoginDAO.getProduct(j);
            while (j <= LoginDAO.tableSize("shop_product")){
                if (p.category.equals("Cold and Flu")) {
                    resp.getWriter().write(p.brand + " " + p.name + " " + p.amount + "<br>");
                }
                j++;
                p = LoginDAO.getProduct(j);
            }
            resp.getWriter().write("</div>\n" + "</section>\n" +
                    "<img src=\"https://bit.ly/cold_flu_map\" alt=\"Paddington Store\" width=\"316\" height=\"400\">\n");
        }

        else if(req.getServletPath().equals("/map/skincare")){
            resp.getWriter().write("<section>" +
                    "<div class=\"box\">\n" +
                    "<h3>Products in Skincare</h3>\n");
            int j = 1;
            Product p = LoginDAO.getProduct(j);
            while (j <= LoginDAO.tableSize("shop_product")){
                if (p.category.equals("Skincare")) {
                    resp.getWriter().write(p.brand + " " + p.name + " " + p.amount + "<br>");
                }
                j++;
                p = LoginDAO.getProduct(j);
            }
            resp.getWriter().write("</div>\n" + "</section>\n" +
                    "<img class=\"images\" src=\"https://bit.ly/skincare_map\" alt=\"Paddington Store\" width=\"316\" height=\"400\">\n");
        }
        else if(req.getServletPath().equals("/map/headaches_and_pain_relief")){
            resp.getWriter().write("<section>" +
                    "<div class=\"box\">\n" +
                    "<h3>Products in Headaches and Pain Relief</h3>\n");
            int j = 1;
            Product p = LoginDAO.getProduct(j);
            while (j <= LoginDAO.tableSize("shop_product")){
                if (p.category.equals("Headaches and Pain Relief")) {
                    resp.getWriter().write(p.brand + " " + p.name + " " + p.amount + "<br>");
                }
                j++;
                p = LoginDAO.getProduct(j);
            }
            resp.getWriter().write("</div>\n" + "</section>\n" +
                    "<img class=\"images\" src=\"https://bit.ly/headache_map\" alt=\"Paddington Store\" width=\"316\" height=\"400\">\n");
        }
        else if(req.getServletPath().equals("/map/digestion")){
            resp.getWriter().write("<section>" +
                    "<div class=\"box\">\n" +
                    "<h3>Products in Digestion</h3>\n");
            int j = 1;
            Product p = LoginDAO.getProduct(j);
            while (j <= LoginDAO.tableSize("shop_product")){
                if (p.category.equals("Digestion")) {
                    resp.getWriter().write(p.brand + " " + p.name + " " + p.amount + "<br>");
                }
                j++;
                p = LoginDAO.getProduct(j);
            }
            resp.getWriter().write("</div>\n" + "</section>\n" +
                    "<img class=\"images\" src=\"https://bit.ly/digestion_map\" alt=\"Paddington Store\" width=\"316\" height=\"400\">\n");
        }
        else if(req.getServletPath().equals("/map/allergy")){
            resp.getWriter().write("<section>" +
                    "<div class=\"box\">\n" +
                    "<h3>Products in Allergy</h3>\n");
            int j = 1;
            Product p = LoginDAO.getProduct(j);
            while (j <= LoginDAO.tableSize("shop_product")){
                if (p.category.equals("Allergy")) {
                    resp.getWriter().write(p.brand + " " + p.name + " " + p.amount + "<br>");
                }
                j++;
                p = LoginDAO.getProduct(j);
            }
            resp.getWriter().write("</div>\n" + "</section>\n" +
                    "<img class=\"images\" src=\"https://bit.ly/allergy_map\" alt=\"Paddington Store\" width=\"316\" height=\"400\">\n");
        }
        else if(req.getServletPath().equals("/map/first_aid")){
            resp.getWriter().write("<section>" +
                    "<div class=\"box\">\n" +
                    "<h3>Products in First Aid</h3>\n");
            int j = 1;
            Product p = LoginDAO.getProduct(j);
            while (j <= LoginDAO.tableSize("shop_product")){
                if (p.category.equals("First Aid")) {
                    resp.getWriter().write(p.brand + " " + p.name + " " + p.amount + "<br>");
                }
                j++;
                p = LoginDAO.getProduct(j);
            }
            resp.getWriter().write("</div>\n" + "</section>\n" +
                    "<section style=\"float: right; margin-right: 15%;\">\n" +
                    "<img class=\"images\" src=\"https://bit.ly/first_aid_map_\" alt=\"Paddington Store\" width=\"316\" height=\"400\">\n" +
                    "</section>\n");
        }
        resp.getWriter().write("<script>\n" +
                "function redirectBrowse(){\n" +
                "        window.location.href=\"https://phabpharmacy.herokuapp.com/browse\"\n" +
                "    }\n" +
                "</script>\n" +
                "</body>\n" +
                "</html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
    }
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
        return "<!DOCTYPE html>\n" +
                "<html>\n" +
                "  <head>\n" +
                "    <meta charset=\"utf-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width\">\n" +
                "    <title>Find Items In-Store</title>\n" +
                "    <link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css\">\n" +
                "    <style>\n" +
                "      body {font-family: Arial, Helvetica, sans-serif;}\n" +
                "      .navbar {\n" +
                "            width: 100%;\n" +
                "            background-color: #555;\n" +
                "            overflow: auto;\n" +
                "        }\n" +
                "      .navbar a {\n" +
                "            float: left;\n" +
                "            font-size: 16px;\n" +
                "            color: white;\n" +
                "            text-align: center;\n" +
                "            padding: 14px 16px;\n" +
                "            text-decoration: none;\n" +
                "      }\n" +
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
                "      .section{\n" +
                "        display: table-cell;\n" +
                "      }\n" +
                "      .images{\n" +
                "        display: table-row;\n" +
                "        float: right;           \n" +
                "        right: 15%;\n" +
                "      }\n" +
                "      div.box{\n" +
                "        position: absolute;\n" +
                "        top: 157px;\n" +
                "        left: 280px;\n" +
                "      }\n" +
                "      .buttonStyle{\n" +
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
                "    </style>\n" +
                "  </head>\n" +
                "  <body>\n" +
                "    <div class=\"navbar\">\n" +
                "    <a href=\"https://phabpharmacy.herokuapp.com/home\"><i class=\"fa fa-fw fa-home\"></i>Home</a> \n" +
                "    <div class=\"dropdown\">\n" +
                "        <button style=\"cursor: pointer;\" class=\"dropbtn\" onclick=\"redirectBrowse()\"><i class=\"fa fa-fw fa-search\"></i>Browse<i class=\"fa fa-caret-down\"></i></button>\n" +
                "        <div class=\"dropdown-content\">\n" +
                "            <a href=\"https://phabpharmacy.herokuapp.com/browse#cold_and_flu\">Cold and Flu</a>\n" +
                "            <a href=\"https://phabpharmacy.herokuapp.com/browse#skincare\">Skincare</a>\n" +
                "            <a href=\"https://phabpharmacy.herokuapp.com/browse#headaches_and_pain_relief\">Headaches and Pain Relief</a>\n" +
                "            <a href=\"https://phabpharmacy.herokuapp.com/browse#digestion\">Digestion</a>\n" +
                "            <a href=\"https://phabpharmacy.herokuapp.com/browse#allergy\">Allergy</a>\n" +
                "            <a href=\"https://phabpharmacy.herokuapp.com/browse#first_aid\">First Aid</a> \n" +
                "            \n" +
                "        </div>\n" +
                "    </div>\n" +
                "    <a href=\"https://phabpharmacy.herokuapp.com/login\"><i class=\"fa fa-fw fa-user\"></i>Login</a>\n" +
                "    <a href=\"https://phabpharmacy.herokuapp.com/register\"><i class=\"fa fa-fw fa-user-plus\"></i>Register</a>\n" +
                "    <a style=\"background-color: #00B8C5\"><i class=\"fa fa-compass\" aria-hidden=\"true\"></i> In-Store</a>\n" +
                "    <a href=\"https://phabpharmacy.herokuapp.com/basket\" style=\"width: 35px;\"><i style=\"width: 35px;\" class=\"fa fa-fw fa-shopping-basket\"><p style=\"display: inline; font-family: Arial; font-weight: bold\" id=\"basket\"> " + basketSizeOut + "</p></i></a>\n" +
                displayCurrentUser +
                "</div>\n" +
                "   <h1>Find Items in Store</h1>\n" +
                "   <p>Choose a category to see all of items in the section and their in-store (Paddington branch) location.</p>\n" +
                "   <section>\n" +
                "       <div class=\"dropdown\">\n" +
                "           <button class=\"buttonStyle\" style=\"dropbtn; width: 222px;\">Categories</button>\n" +
                "           <div class=\"dropdown-content\">\n" +
                "               <a id=\"cf\" href=\"https://phabpharmacy.herokuapp.com/map/cold_and_flu\"> Cold and Flu</a>\n" +
                "               <a id=\"s\" href=\"https://phabpharmacy.herokuapp.com/map/skincare\"> Skincare</a>\n" +
                "               <a id=\"hpr\" href=\"https://phabpharmacy.herokuapp.com/map/headaches_and_pain_relief\">Headaches and Pain Relief</a>\n" +
                "               <a id=\"d\" href=\"https://phabpharmacy.herokuapp.com/map/digestion\">Digestion</a>\n" +
                "               <a id=\"a\" href=\"https://phabpharmacy.herokuapp.com/map/allergy\">Allergy</a>\n" +
                "               <a id=\"fa\" href=\"https://phabpharmacy.herokuapp.com/map/first_aid\">First Aid</a>\n" +
                "           </div>\n" +
                "       </div>\n" +
                "   </section>\n";
    }
}
