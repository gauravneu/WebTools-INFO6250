import java.io.*;
import java.util.Enumeration;
import javax.servlet.http.*;

//@WebServlet(name = "helloServlet", value = "/WaiverForm")
public class Tpart7 extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Enumeration enum1 = request.getParameterNames();
        out.println("<TABLE BORDER=1px>\n" + "<TR><TH>Name</TH><TH>Value</TH></TR>");

        while (enum1.hasMoreElements()) {
            Object obj = enum1.nextElement();
            String s = (String) obj;
            String[] s1 = request.getParameterValues(s);
            out.println("<TR><TD>" + s + "</TD><TD>");
            for (String h : s1) {
                out.println("<LI>" + h + "</LI>");
            }
            out.println("</TD></TR>");
        }
        out.println("</TABLE>");
    }

    public void destroy() {
    }
}