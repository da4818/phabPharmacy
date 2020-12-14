import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "login")
public class SerlvetLogin extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String usern = req.getParameter("user");
        String passw = req.getParameter("pass");
        System.out.println(usern +"," +passw);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.getWriter().write("<html><head><title>Home</title></head><body><h1>Login</h1><form name =\"loginForm\" method=\"post\"><input type=\"text\" size=\"30\" class=\"form-control\" name=\"user\" placeholder=\"Username*\"><br><input type=\"text\" size=\"30\" class=\"form-control\" name=\"pass\" placeholder=\"Password*\"><br> <p><input type=\"button\" value=\"Login\"></p> </body></html>");
    }
}
