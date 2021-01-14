package Website.Servlets.Access;

import Website.Functions.*;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;
/* When a doPost request is made to this servlet it returns a list of all the customers in the
* database*/
@WebServlet(urlPatterns={"/customers"},loadOnStartup = 1)
public class CustomerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.getWriter().write("<html> <head> <title>CMDMC</title> </head><body> <h1>Customers</h1> </body> </html>");
        resp.getWriter().write(req.getServletPath());
    }
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {
        String reqBody=req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        AccessCustomers query = new AccessCustomers();
        Customers customers = query.getCustomers();
        Gson gson = new Gson();
        String jsonString = gson.toJson(customers);
        resp.setContentType("application/json");
        resp.getWriter().write(jsonString);
    }

}