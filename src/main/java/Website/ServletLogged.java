package Website;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/login_status")
public class ServletLogged extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Website.UserDB udb = new Website.UserDB();
        resp.setContentType("text/html");
        ServletLogin sl = new ServletLogin();
        String HTML = sl.htmlOutput();
        PrintWriter writer = resp.getWriter();
        String em = req.getParameter("email");
        String pw = req.getParameter("pass");
        writer.println(HTML);
        if(LoginDAO.validate(em,pw)){
            String output= LoginDAO.getName(em,pw);
            writer.print("<h2>Welcome back, " + output + "!</h2>");
            //RequestDispatcher rd = req.getRequestDispatcher("servlet2");
            //rd.forward(req,resp);
        }
        else if (em.isEmpty() || pw.isEmpty()){
            writer.print("<h2>Incomplete fields, please enter all the information.</h2>");
        }
        else{
            writer.print("<h2>Wrong email or password<h2>");
            RequestDispatcher rd=req.getRequestDispatcher("/home");
            rd.include(req,resp);
        }
        writer.close();

    }
}