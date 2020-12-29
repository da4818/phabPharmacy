import Website.ServletLogin;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import sun.reflect.annotation.ExceptionProxy;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import Website.*;
public class TestServletLogin extends ServletLogin{
    @Mock
    HttpServletRequest request;
    @Mock
    HttpServletResponse response;
    @Before
    public void setUp() throws Exception{
        MockitoAnnotations.initMocks(this);
    }
    @Test
    public void testLoginValidation() throws IOException, ServletException{
        when(request.getParameter("email")).thenReturn("email1");
        when(request.getParameter("pass")).thenReturn("pass1");
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        when(response.getWriter()).thenReturn(pw);
        ServletLogin slogin = new ServletLogin();
        super.doPost(request,response);
        String result = sw.getBuffer().toString();
        Assert.assertThat(result,is(equalTo("<h2>Welcome back,John!</h2>")));


    }


}
