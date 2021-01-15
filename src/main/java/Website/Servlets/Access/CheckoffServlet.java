package Website.Servlets.Access;

import Website.Functions.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;
/* When a doPost request is made to this servlet the barcode of the product passed in the request is used
* to delete that product from the ordered product table */
@WebServlet(urlPatterns={"/checkoff"},loadOnStartup = 1)
public class CheckoffServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");;
        resp.getWriter().write("<html> <head> <title>CMDMC</title> </head><body> <h1>CheckOff Page</h1> </body> </html>");
        resp.getWriter().write(req.getServletPath());
    }
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {
        String reqBody=req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        String[] parts = reqBody.split(" ");
        String productId = parts[0];
        String customerId = parts[1];
        System.out.println(productId);
        DeleteOrderedProduct dop = new DeleteOrderedProduct(productId, customerId);
        resp.setContentType("text/html");
        resp.getWriter().write("Succeeded");
    }
}