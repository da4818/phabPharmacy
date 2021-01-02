package Website.Servlets;

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
import java.util.ArrayList;
import static java.lang.String.valueOf;

@WebServlet(urlPatterns = "/browse",loadOnStartup = 0)
public class ServletBrowse extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String HTML= htmlOutput();
        resp.getWriter().write(HTML);
                ArrayList<String> headers = getHeaderinfo("headers"); //See line 110
                ArrayList<String> headerURLs = getHeaderinfo("headerURLs"); //See line 110
                int j=1;
                Product p = LoginDAO.getProduct(j);
                for (int i=0;i<6;i++) {
                    resp.getWriter().write("<section>\n" +
                            "<h2 id=\""+headerURLs.get(i)+"\">" + headers.get(i) + "</h2>\n"); //See line 110
                    while (p.category.equals(headers.get(i))) {
                        DecimalFormat df = new DecimalFormat("0.00");
                        String price = valueOf(df.format(p.price)); //This allows us to output the number in the format of money (2dp)
                        int max = p.limited ? 1 : 5; //Some products are limited to 1 per customer - if this is the case (i.e. limited is TRUE), max will be set to 1. if limited is FALSE, max is set to 5 (an arbitrary maximum)
                        resp.getWriter().write("<div class=\"relative\">\n");
                        if (p.limited){
                            resp.getWriter().write("<label class=\"tooltip\"><center>" + p.name + "<br>" + p.description + "</center>\n" +
                                    "<span class=\"tooltiptext\"><i>Limited to one per customer</i></span></label><br>\n");
                        }
                        else{
                            resp.getWriter().write("<label><center>" + p.name + "<br>" + p.description + "</center></label><br>\n");
                        }
                        resp.getWriter().write("<label><center>£" + price + "</label></center><br>\n" +
                                "<div class=\"absolute\">\n" +
                                "<form action=\"browse\" method=\"post\">\n" +
                                "<input name=\"basketQuantity\" type=\"number\" size=\"5\" min=\"1\" max=\"" + max + "\">\n" +
                                "<input name=\"buttonNumber\" type=\"hidden\"value=\"" + j + "\">\n" +
                                "<input type=\"submit\" class=\"buttonStyle\" value=\"Add to Basket\">\n" +
                                "</form>\n" +
                                "</div>\n" +
                                "</div>");
                        j++;
                        p = LoginDAO.getProduct(j);
                    }
                    resp.getWriter().write("</section>");
                }
        resp.getWriter().write("</body>\n" +
                "</html>");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String notLoggedInMessage="";
        if (!LoginDAO.checkLoggedIn()){ //If no one is logged in, it will prevent them from adding items to their basket
            notLoggedInMessage = "<script>window.onload(alert(\"Please ensure that you have created an account and logged in before adding items to your basket.\"));</script>";
        }
        else{
            int pos = Integer.parseInt(req.getParameter("buttonNumber"));
            int q = Integer.parseInt(req.getParameter("basketQuantity"));
            Product pBasket = LoginDAO.getProduct(pos);
            LoginDAO.addToBasket(pBasket,q);
        }
        String HTML= htmlOutput();
        resp.getWriter().write(HTML);
        ArrayList<String> headers = getHeaderinfo("headers");
        ArrayList<String> headerURLs = getHeaderinfo("headerURLs");
        int j=1;
         Product p = LoginDAO.getProduct(j);
        for (int i=0;i<6;i++) {
            resp.getWriter().write("<section>\n" +
                    "<h2 id=\""+headerURLs.get(i)+"\">" + headers.get(i) + "</h2>\n");
            while (p.category.equals(headers.get(i))) {
                DecimalFormat df = new DecimalFormat("0.00");
                String price = valueOf(df.format(p.price));
                int max = p.limited ? 1 : 5;
                resp.getWriter().write("<div class=\"relative\">\n");
                if (p.limited){
                    resp.getWriter().write("<label class=\"tooltip\"><center>" + p.name + "<br>" + p.description + "</center>\n" +
                            "<span class=\"tooltiptext\"><i>Limited to one per customer</i></span></label><br>\n");
                }
                else{
                    resp.getWriter().write("<label><center>" + p.name + "<br>" + p.description + "</center></label><br>\n");
                }
                resp.getWriter().write("<label><center>£" + price + "</label></center><br>\n" +
                        "<div class=\"absolute\">\n" +
                        "<form action=\"browse\" method=\"post\">\n" +
                        "<input name=\"basketQuantity\" type=\"number\" size=\"5\" min=\"0\" max=\"" + max + "\">\n" +
                        "<input name=\"buttonNumber\" type=\"hidden\"value=\"" + j + "\">\n" +
                        "<input type=\"submit\"class=\"buttonStyle\" value=\"Add to Basket\">\n" +
                        "</form>\n" +
                        "</div>\n" +
                        "</div>");
                j++;
                p = LoginDAO.getProduct(j);
            }
            resp.getWriter().write("</section>");
        }
        resp.getWriter().write(notLoggedInMessage);
        resp.getWriter().write("</body>\n" +
                "</html>");
    }

    public ArrayList <String> getHeaderinfo(String info_in){
        ArrayList <String> output = new ArrayList<>();
        if (info_in.equals("headers")){ //This is the categories to be displayed
            output.add("Cold and Flu"); //The headers will correspond to the anchor link URL (see line 120)
            output.add("Skincare");
            output.add("Headaches and Pain Relief");
            output.add("Digestion");
            output.add("Allergy");
            output.add("First Aid");
        }
        else if (info_in.equals("headerURLs")){ //These are the names to hyperlink to a specific part of the page (termed an 'anchor link')
            output.add("cold_and_flu"); //Each anchor link is named in the 'id' section of the header tag (line 30)
            output.add("skincare"); //This anchor link corresponds to the hyperlink URL for each dropdown option in the navigation bar (lines 273-278)
            output.add("headaches_and_pain_relief");
            output.add("digestion");
            output.add("allergy");
            output.add("first_aid");
        }
        return output;
    }
    public String htmlOutput(){
        boolean userLoggedIn = LoginDAO.checkLoggedIn();
        String userMessage = "";
        String basketWarning = "";
        User cUser = null;
        if (userLoggedIn == true) {
            cUser = LoginDAO.getCurrentUser();
            userMessage = cUser.fname;
        }
        int basketSize = LoginDAO.getBasketSize();
        String basketSizeOut="";
        if (basketSize != 0){ basketSizeOut = String.valueOf(basketSize);}
        return "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "    <meta charset=\"utf-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width\">\n" +
                "    <title>Browse</title>\n" +
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
                "        div.absolute {\n" +
                "            position: absolute;\n" +
                "            top: 140px;\n" +
                "            width: 200px;\n" +
                "            height: 30px;\n" +
                "        }\n" +
                "        div.relative{\n" +
                "            position: relative;\n" +
                "            width: 200px;\n" +
                "            height: 150px;\n" +
                "            display: inline;\n" +
                "            border: 1px solid black;\n" +
                "            padding: 15px;\n" +
                "            margin: 5px;\n" +
                "            float: left;\n" +
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
                "        .scrollBtn {\n" +
                "            display: none;\n" +
                "            position: fixed;\n" +
                "            bottom: 20px;\n" +
                "            right: 30px;\n" +
                "            z-index: 99;\n" +
                "            outline: none;\n" +
                "            background-color: #555;\n" +
                "            border: none;\n" +
                "            color: white;\n" +
                "            padding: 5px 25px;\n" +
                "            text-align: center;\n" +
                "            text-decoration: none;\n" +
                "            font-size: 16px;\n" +
                "            margin: 4px 2px;\n" +
                "            cursor: pointer;\n" +
                "        }\n" +
                "        .scrollBtn:hover {\n" +
                "            background-color: #00B8C5;\n" +
                "        }\n" +
                "        section{\n" +
                "            display: table-row;\n" +
                "        }\n" +
                "        .tooltip{\n" +
                "            position: relative;\n" +
                "            display: inline;\n" +
                "        }\n" +
                "        .tooltip .tooltiptext {\n" +
                "            visibility: hidden;\n" +
                "            width: 120px;\n" +
                "            background-color: black;\n" +
                "            color: #fff;\n" +
                "            text-align: center;\n" +
                "            border-radius: 6px;\n" +
                "            padding: 5px 0;\n" +
                "            position: absolute;\n" +
                "            z-index: 1;\n" +
                "        }\n" +
                "        .tooltip:hover .tooltiptext {\n" +
                "            visibility: visible;\n" +
                "        }\n" +
                "        .currentUser{\n" +
                "           float: right;\n" +
                "           font-size: 16px;\n" +
                "           color: white;\n" +
                "           text-align: center;\n" +
                "           padding: 14px 16px;\n" +
                "           text-decoration: none;\n" +
                "        }\n" +
                "    </style>\n" +
                "\n" +
                "</head>\n" +
                "<body>\n" +
                "<div class=\"navbar\">\n" +
                "    <a href=\"https://phabpharmacy.herokuapp.com/home\"><i class=\"fa fa-fw fa-home\"></i>Home</a>\n" +
                "    <div class=\"dropdown\">\n" +
                "        <button style=\"background-color: #00B8C5;\" class= \"dropbtn\"><i class=\"fa fa-fw fa-search\"></i>Browse<i class=\"fa fa-caret-down\"></i></button>\n" +
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
                "    <a href=\"https://phabpharmacy.herokuapp.com/basket\"><i style=\"width: 35px;\" class=\"fa fa-fw fa-shopping-basket\"><p style=\"display: inline; font-family: Arial; font-weight: bold\" id=\"basket\"> " + basketSizeOut + "</p></i></a>\n" +
                "    <div class=\"currentUser\">" + userMessage + "<i class=\"fa fa-fw fa-user\"></i></div>\n" +
                "</div>\n";
    }
}
