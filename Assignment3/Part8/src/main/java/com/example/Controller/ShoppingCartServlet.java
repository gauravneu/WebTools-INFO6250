package com.example.Controller;

import com.example.Bean.Item;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;


public class ShoppingCartServlet extends HttpServlet {
    ArrayList<Item> products;
    public void init(){
        products = new ArrayList();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if(request.getServletPath().equals("/add/del")){deleteItem(request, response);}
        else{moreItem(request, response);}
    }

    private void deleteItem(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession httpSession = request.getSession(false);
        String currentUser = (String) httpSession.getAttribute("uName");

        if (currentUser != null) {
            httpSession.removeAttribute(request.getParameter("currentitem"));
            products.remove(request.getParameter("currentitem"));
            if (!products.isEmpty()) {
                for (int i = 0; i < products.size(); i++) {
                    if((products.get(i).toString()).equals(request.getParameter("currentitem"))){
                        products.remove(i);
                    }
                }
                httpSession.setAttribute("products", products);
                response.sendRedirect("../itemList.jsp");
            }
        }
    }

    private void moreItem(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession httpSession = request.getSession(false);
        String user = (String) httpSession.getAttribute("uName");
        String[] books = request.getParameterValues("books");
        String[] coms = request.getParameterValues("coms");
        String[] music = request.getParameterValues("mus");

        if (!user.isEmpty()) {
            String hlink = request.getParameter("hlink");
            request.setAttribute("hlink", hlink);
            if (music != null) {
                int musicLength = music.length;
                for (int i = 0; i < musicLength; i++) {
                    Item musicName = new Item(music[i]);
                    products.add(musicName);
                }
            }
            if (books != null) {
                int bookLength = books.length;;
                for (int i = 0; i < bookLength; i++) {
                    Item bookName = new Item(books[i]);
                    products.add(bookName);
                }
            }
            if (coms != null) {
                int comsLength = coms.length;
                for (int i = 0; i < comsLength; i++) {
                    Item computer = new Item(coms[i]);
                    products.add(computer);
                }
            }

            httpSession.setAttribute("products", products);
            RequestDispatcher rd = request.getRequestDispatcher("updateCart.jsp");
            rd.forward(request, response);
        }
    }



    @Override
    protected void doGet
            (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost
            (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
