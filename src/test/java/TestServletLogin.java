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

import static org.mockito.Mockito.when;

public class TestServletLogin {
    @Mock
    HttpServletRequest request;
    @Mock
    HttpServletResponse response;
    @Before
    public void setUp() throws Exception{
        MockitoAnnotations.initMocks(this);
    }
    @Test
    public void testDoGet() throws IOException, ServletException{
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(printWriter);
    }


}
