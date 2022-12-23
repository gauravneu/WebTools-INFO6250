import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.io.IOException;
import javax.servlet.ServletException;
import java.util.*;

public class ShowServletGetRequests extends HttpServlet {
    
    
    public void init() {
        //This method runs only once at the time when servlet starts
    }
    
    public void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException{
        
        response.setContentType("text/html");
        PrintWriter pw  = response.getWriter();

        
        Enumeration headerNames = request.getHeaderNames();
        response.setCharacterEncoding("UTF-8");
        pw.println("<!DOCTYPE HTML><HTML>");
        pw.println("<BODY BGCOLOR=\"#FDF5E6\">");

        

        pw.print("<div class = \"column\" float=\"left\" width=\"50%\">");
        pw.print("<h2 ALIGN=\"CENTER\">" + "<u>" + "HTTP SERVLET REQUEST" + "</u>" + "</h2>");
        pw.print("</div></div>");


        pw.print("<BR>\n" +
                            "<TABLE BORDER=0 ALIGN=\"CENTER\">\n" +
                            "<TR BGCOLOR=\"#FFAD00\">\n" +
                            "<TH>Method Name" +
                            "</TH>" +
                            "<TH>Value" +
                            "</TH>" +
                            "</TR>" );
                            
                            pw.println("<TR><TD>" + "getAuthType");
                            pw.println("    <TD>" + request.getAuthType());

                            pw.println("<TR><TD>" + "getContextPath");
                            pw.println("    <TD>" + request.getContextPath());

                            pw.println("<TR><TD>" + "getCookies");
                            pw.println("    <TD>" + request.getCookies());

                            pw.println("<TR><TD>" + "getDateHeader");
                            pw.println("    <TD>" + request.getDateHeader("If-Modified-Since"));

                            pw.println("<TR><TD>" + "getHeader");
                            pw.println("    <TD>" + request.getHeader("User-Agent"));

                            pw.println("<TR><TD>" + "getHeaderNames");
                            pw.println("    <TD>" + request.getHeaderNames());

                            pw.println("<TR><TD>" + "getMethod");
                            pw.println("    <TD>" + request.getMethod());

                            pw.println("<TR><TD>" + "getPathInfo");
                            pw.println("    <TD>" + request.getPathInfo());

                            pw.println("<TR><TD>" + "getPathTranslated");
                            pw.println("    <TD>" + request.getPathTranslated());

                            pw.println("<TR><TD>" + "getQueryString");
                            pw.println("    <TD>" + request.getQueryString());

                            pw.println("<TR><TD>" + "getRemoteUser");
                            pw.println("    <TD>" + request.getRemoteUser());

                            pw.println("<TR><TD>" + "getRequestedSessionId");
                            pw.println("    <TD>" + request.getRequestedSessionId());

                            pw.println("<TR><TD>" + "getRequestURI");
                            pw.println("    <TD>" + request.getRequestURI());

                            pw.println("<TR><TD>" + "getRequestURL");
                            pw.println("    <TD>" + request.getRequestURL());

                            pw.println("<TR><TD>" + "getServletPath");
                            pw.println("    <TD>" + request.getServletPath());

                            while(headerNames.hasMoreElements()) {
                                String headerName = (String)headerNames.nextElement();
                                pw.println("<TR><TD>" + headerName);
                                pw.println("    <TD>" + request.getHeader(headerName));
                               // pw.println("<TR><TD>" + headerName);
                               // pw.println("    <TD>" + request.getIntHeader(headerName));            
                            }

        
        pw.println("</TD></TH></TR></TABLE><BR><BR>");
        


        pw.print("<h1 ALIGN=\"CENTER\">" + "<u>" + "SERVLET REQUEST" + "</u>" + "</h1>");

 pw.print("<BR>\n" +
                            "<TABLE BORDER=0 ALIGN=\"CENTER\"><TR BGCOLOR=\"#FFAD00\"><TH>Method Name</TH><TH>Value</TH></TR>");
                            
                            pw.println("<TR><TD>" + "   getCharacterEncoding");
                            pw.println("    <TD>" + request.getCharacterEncoding());

                            while(request.getAttributeNames().hasMoreElements()) {
                                String headerName = (String)headerNames.nextElement();
                                pw.println("<TR><TD>" + headerName);
                                pw.println("    <TD>" + request.getAttribute(headerName));           
                            }

                            pw.println("<TR><TD>" + "getContentLength");
                            pw.println("    <TD>" + request.getContentLength());

                            pw.println("<TR><TD>" + "getContentType");
                            pw.println("    <TD>" + request.getContentType());

                            pw.println("<TR><TD>" + "getLocalAddr");
                            pw.println("    <TD>" + request.getLocalAddr());

                            pw.println("<TR><TD>" + "getLocalName");
                            pw.println("    <TD>" + request.getLocalName());

                            pw.println("<TR><TD>" + "getLocalPort");
                            pw.println("    <TD>" + request.getLocalPort());


                            pw.println("<TR><TD>" + "getProtocol");
                            pw.println("    <TD>" + request.getProtocol());

                            pw.println("<TR><TD>" + "getRemoteAddr");
                            pw.println("    <TD>" + request.getRemoteAddr());

                            pw.println("<TR><TD>" + "getRemoteHost");
                            pw.println("    <TD>" + request.getRemoteHost());

                            pw.println("<TR><TD>" + "getRemotePort");
                            pw.println("    <TD>" + request.getRemotePort());

                            pw.println("<TR><TD>" + "getScheme");
                            pw.println("    <TD>" + request.getScheme());

                            pw.println("<TR><TD>" + "getServerName");
                            pw.println("    <TD>" + request.getServerName());

                            pw.println("<TR><TD>" + "getServerPort");
                            pw.println("    <TD>" + request.getServerPort());


                            
                            
        pw.println("</TD</TH</TR></TABLE><BR><BR>");



        pw.print("<h1 ALIGN=\"CENTER\"><u>HTTP SERVLET</u></h1>");
        
        pw.print("<BR><TABLE BORDER=0 ALIGN=\"CENTER\">\n<TR BGCOLOR=\"#FFAD00\">\n<TH>Method Name</TH><TH>Value</TH></TR><TR><TD>getLastModified</TD><TD>" + getLastModified(request) + "</TD></TR>" );

        
        pw.println("</TD</TH</TR></TABLE><BR><BR></BODY></HTML>");
    }
    
    public void destroy() {
        //This method is called when servlet gets undeployed or server stops.
    }
}