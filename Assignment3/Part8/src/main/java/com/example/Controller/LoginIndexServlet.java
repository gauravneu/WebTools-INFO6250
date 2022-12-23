package com.example.Controller;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
public class LoginIndexServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uName = request.getParameter("l_email");
        String pwd = request.getParameter("l_password");

        if (uName.equals("root@gmail.com") && pwd.equals("root")) {
            HttpSession httpSession = request.getSession(false);
            if(httpSession != null){
                httpSession.setAttribute("uName", uName);
                httpSession.setAttribute("pwd", pwd);
                response.sendRedirect("index.jsp");
            }
        } else {
            response.sendRedirect("loginPage.jsp");
        }
    }
}
