package ClientUI;
import Website.Entities.Customer;
import Website.Entities.Product;
import Website.Functions.MailSender;
import Website.Functions.UpdateQuantity;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import ClientUI.OnlineOrderList;

@WebServlet(urlPatterns={"/onlineorders"},loadOnStartup = 1)
public class OnlineOrdersServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.getWriter().write(req.getServletPath());
    }
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {

        String reqBody=req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        Gson gson = new Gson();
        Customer cust = gson.fromJson(reqBody,Customer.class);
        JButton jb = gson.fromJson(reqBody,JButton.class);
        jb.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String dbUrl = System.getenv("JDBC_DATABASE_URL");
                try {
                    Class.forName("org.postgresql.Driver");
                    Connection db = DriverManager.getConnection(dbUrl);
                    Statement stmt = db.createStatement();

                } catch (ClassNotFoundException | SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
}

