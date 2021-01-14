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

/* When a doPost request is made to this servlet containing a customer ID, it
* returns a JSON packaged class with that customers order */
@WebServlet(urlPatterns={"/accessOrders"},loadOnStartup = 1)
public class AccessOrders extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.getWriter().write("<html> <head> <title>CMDMC</title> </head><body> <h1>Access Orders</h1> </body> </html>");
        resp.getWriter().write(req.getServletPath());
    }
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {
        String reqBody=req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        OrderRequest query = new OrderRequest(Integer.parseInt(reqBody));
        Order order = new Order(query.getOrder(),1);
        Gson gson = new Gson();
        String jsonString = gson.toJson(order);
        resp.setContentType("application/json");
        resp.getWriter().write(jsonString);


    }

}