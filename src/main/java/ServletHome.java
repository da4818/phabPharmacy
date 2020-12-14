import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns ={"/home"},loadOnStartup = 1)
public class ServletHome extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        DataBase db = new DataBase();
        resp.getWriter().write("<title>Home2</title><<h1>Database name: " + db.fname + "</h1>");
        resp.getWriter().write("<h2>"+db.lname+"</h2>");
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }
}
