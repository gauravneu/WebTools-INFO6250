import java.io.*;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

//@WebServlet(name = "helloServlet", value = "/WaiverForm")
public class TPart8 extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        Map<String, String[]> hm = new HashMap<>();
        hm = request.getParameterMap();
        out.println("<HTML><HEAD><TITLE>Tuition Part 8</TITLE></HEAD><BODY><TABLE BORDER=1px>\n" + "<TR><TH>Name</TH><TH>Value</TH>");
        for(String key:hm.keySet()){
            out.println("<TR><TD>"+key+" </TD><TD>" );
            String[] hs = hm.get(key);
            for(String j:hs){
                out.println("<LI>" + j+ "</LI>");
            }
            out.println("</TD></TR>");
        }
        out.println("</TABLE></BODY></HTML>");
    }

    public void destroy() {
    }
}