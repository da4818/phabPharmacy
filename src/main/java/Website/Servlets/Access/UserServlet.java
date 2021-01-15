package Website.Servlets.Access;

import Website.Functions.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;
/* When a doPost request is made to this servlet it uses the string formatted date
 * passed from the request to return the profit on that date */
@WebServlet(urlPatterns={"/checkUser"},loadOnStartup = 1)
public class UserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String str="'2020-12-27'";
        ProfitRequest profitRequest = new ProfitRequest(str);
        resp.getWriter().write("<html> <head> <title>CMDMC</title> </head><body> <h1>User Checking</h1> </body> </html>");
        resp.getWriter().write(req.getServletPath());
    }
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {
        String reqBody=req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        String[] parts = reqBody.split(" ");
        String username = parts[0];
        String password = parts[1];
        AccessUsers query = new AccessUsers(username, password);
        resp.setContentType("text/html");
        String response = new String(query.getOutput());
        resp.getWriter().write(response);


    }

}