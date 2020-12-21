import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/registration_status")
public class ServletRegistered extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //UserDB udb = new UserDB();
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();
        String nm = req.getParameter("fname");
        String em = req.getParameter("email");
        String pw = req.getParameter("pass");
        String vpw = req.getParameter("verifyPass");
        String cn = req.getParameter("cardno");
        String ad = req.getParameter("postcode");
        if(LoginDAO.validate(em,pw)){ //create validation to see if email exists
           writer.println("<h2> There is an existing account with the email entered. Please log in.</h2>");
        }
        else if (!pw.equals(vpw)){
            writer.println("<h2> Passwords don't match. Please try again.</h2>");
        }
        else if (nm.isEmpty() || em.isEmpty() || pw.isEmpty() || vpw.isEmpty() || cn.isEmpty() || ad.isEmpty()){
            writer.print("<h2>Incomplete fields, please enter all the information.</h2>");
        }
        else{
            LoginDAO.addUser(nm,em,pw,cn,ad);
            String output= LoginDAO.getName(em,pw);
            writer.print("<h2>Succesful registration. Welcome, " + output + "</h2>");
        }
    }
}
