import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class Part3Servlet extends HttpServlet {
    public void init() {
    }

    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
    response.setContentType("text/html");
    PrintWriter out = response.getWriter();

    String email = request.getParameter("eMail");
    String password = request.getParameter("pass");
    String cpassword = request.getParameter("confPass");
    String gender = request.getParameter("gdr");
    String[] hobby = request.getParameterValues("hb");
    String address = request.getParameter("ad");
    String countryNames = request.getParameter("cN");
    String photo = request.getParameter("photo");

    //Writing the parameters on browser

        out.println("<!doctype HTML>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Form Details</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Form Details</h1>");
        out.println("<b>Email: </b>" + email + "<br>");
        out.println("<b>Password: </b>" + password + "<br>");
        out.println("<b>Confirm Password: </b>" + cpassword + "<br>");
        out.println("<b>Gender: </b>" + gender + "<br>");
        out.println("<b>Country Name: </b>" + countryNames + "<br>");
        out.println("<b>Photo: </b>" + photo + "<br>");
        out.println("<b>Hobbies: </b>" + "<br>");
        out.println("<ul>");
        for(String val : hobby){
            out.println("<li>" + val + "</li>");
        }
        out.println("</ul>");
        out.println("<b>Address: </b>" + address + "<br>");
        out.println("</body>");
        out.println("</html>");

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