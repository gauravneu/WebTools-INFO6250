import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class ShowRequestHeaders extends HttpServlet {
    public void init() {
    }


public void handleRequest(HttpServletRequest request,HttpServletResponse response) throws IOException {
    response.setContentType("text/html");
    PrintWriter out = response.getWriter();
    String docType =
      "<!DOCTYPE HTML>\n";
    out.println(docType +
                "<HTML>\n" +
                "<HEAD><TITLE>" + "Assignment1-Part2" + "</TITLE></HEAD>\n" +
                "<BODY BGCOLOR=\"#fbecce\">\n" +
                "<H1 ALIGN=\"CENTER\">" + "Assignment1-Part2" + "</H1>\n" +
                "<TABLE BORDER=2 ALIGN=\"CENTER\">\n" +
                "<TR BGCOLOR=\"#c1b18e\">\n" +
                "<TH>Name<TH>Stored Value");
    Enumeration allHeaderNames = request.getHeaderNames();
    while (allHeaderNames.hasMoreElements()) {
      String headerName = (String) allHeaderNames.nextElement();
      Enumeration allHeaderValues = request.getHeaders(headerName); 
      if (allHeaderValues != null) {
        while (allHeaderValues.hasMoreElements()) {
          String headerValue = (String) allHeaderValues.nextElement();
          out.println("<TR><TD>" + headerName);
          out.println("    <TD>" + headerValue);
        }
      }
    }
     out.println("</TABLE>\n</BODY></HTML>\n" );
}


      public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        handleRequest(request, response);
    }

      public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        handleRequest(request, response);
    }

  
  public void destroy() {
    }
}
