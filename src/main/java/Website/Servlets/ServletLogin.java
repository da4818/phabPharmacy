package Website.Servlets;

import Website.Entities.User;
import Website.LoginDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns ={"/login"},loadOnStartup = 0)
public class ServletLogin extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String output = htmlOutput();
        resp.getWriter().write(output);
        resp.getWriter().write("<script>\n" +
                "    function refreshPage(){\n" +
                "        location.reload()\n" +
                "    }\n" +
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
        String logOut = req.getParameter("logOut"); // For each form submission - there is a hidden button named "logOut" - this is for pages where no log out button is present (when no one is logged in) - if the hidden button didn't exist for these pages, it will result in a nullPointerException error - line 225
        if (logOut.equals("Log Out")){
            LoginDAO.resetTable("logged_in_customer");
            // LoginDAO.resetTable("customer_basket");
        }
        // Retrieves info that user has entered - in HTML code, the input tags are named "email" and "pass" (lines 160-161)
        String em = req.getParameter("email");
        String pw = req.getParameter("pass");
        String HTML = htmlOutput();
        resp.getWriter().write(HTML);
        if(LoginDAO.validateLogin(em,pw)){ // Checks that the input variables match existing entries in the customer ('user') database
            User currentUser = LoginDAO.getUser(em,pw); // If the login entries pass the validation checks
            LoginDAO.setLoggedInUser(currentUser); // This will update the current user database (I've called it 'logged') so they know which customer is currently logged in
            resp.getWriter().write("<h2>Welcome back, " + currentUser.fname + "!</h2>\n");
        }
        else if (em.isEmpty() || pw.isEmpty()){
            resp.getWriter().write("<h2>Incomplete fields, please enter all the information.</h2>\n");
        }
        else{ // If the input variables don't match any existing entries in the customer ('user') database
            resp.getWriter().write("<h2>Wrong email or password, please try again.</h2>\n");
        }
        resp.getWriter().write("<script>\n" +
                "    function refreshPage(){\n" +
                "        location.reload()\n" +
                "    }\n" +
                "    function redirectBrowse(){\n" +
                "        window.location.href=\"https://phabpharmacy.herokuapp.com/browse\"\n" +
                "    }\n" +
                "</script>\n" +
                "</body>\n" +
                "</html>");
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
        return "<!DOCTYPE html>\n" + // HTML comments are on the respective .jsp files (need updating)
                "<html>\n" +
                "<head>\n" +
                "   <meta charset=\"utf-8\">\n" +
                "   <meta name=\"viewport\" content=\"width=device-width\">\n" +
                "   <title>Login</title>\n" +
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
                "        .buttonStyle {\n" +
                "            background-color: #00B8C5;\n" +
                "            border: none;\n" +
                "            color: white;\n" +
                "            padding: 5px 25px;\n" +
                "            text-align: center;\n" +
                "            text-decoration: none;\n" +
                "            display: inline-block;\n" +
                "            font-size: 16px;\n" +
                "            margin: 4px 0px;\n" +
                "            cursor: pointer;\n" +
                "        }\n" +
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
                "    <a style=\"background-color: #00B8C5;\"><i class=\"fa fa-fw fa-user\"></i>Login</a>\n" +
                "    <a href=\"https://phabpharmacy.herokuapp.com/register\"><i class=\"fa fa-fw fa-user-plus\"></i>Register</a>\n" +
                "    <a href=\"https://phabpharmacy.herokuapp.com/map\"><i class=\"fa fa-compass\" aria-hidden=\"true\"></i>In-Store</a>\n" +
                "    <a href=\"https://phabpharmacy.herokuapp.com/basket\" name=\"Basket\"><i style=\"width: 35px;\" class=\"fa fa-fw fa-shopping-basket\"><p style=\"display: inline; font-family: Arial; font-weight: bold\" id=\"basket\"> " + basketSizeOut + "</p></i></a>\n" +
                displayCurrentUser +
                "</div>\n" +
                "<h1>Login</h1>\n" +
                "<p> Login below. If you haven't got an account, <a href=\"https://phabpharmacy.herokuapp.com/register\"> register here.</a></p>\n" +
                "\n" +
                "<form name=\"loginForm\" action=\"login\" method=\"post\">\n" +
                "        <input type=\"text\" size=\"30\" class=\"form-control\" name=\"email\" placeholder=\"Email Address*\"><br>\n" +
                "        <input type=\"text\" size=\"30\" class=\"form-control\" name=\"pass\" placeholder=\"Password*\"><br>\n" +
                "        <input type=\"hidden\" name=\"logOut\" value=\"false\">\n" + // a hidden input tag is added to prevent nullPointer errors (line 25)
                "        <input type=\"submit\" name=\"login\" style=\"width: 222px;\" class=\"buttonStyle\" value=\"Submit\">\n" +
                "</form>\n";
    }
}

/*

 */
