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
/* When a doPost request is made to this servlet, the details passed into the request are used
* to update the quantity of an item in the shop_product table, as well as carry
* out stock checks after this update has been made */
@WebServlet(urlPatterns={"/update"},loadOnStartup = 1)
public class UpdateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.getWriter().write("<html> <head> <title>CMDMC</title> </head><body> <h1>Update Quantity</h1> </body> </html>");
        resp.getWriter().write(req.getServletPath());
    }
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {
        String reqBody=req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        Gson gson = new Gson();
        Product p=gson.fromJson(reqBody, Product.class);
        UpdateQuantity update = new UpdateQuantity(p.name, p.brand, p.change);
        resp.setContentType("text/html");
        resp.getWriter().write("Quantity Updated");
    }

}