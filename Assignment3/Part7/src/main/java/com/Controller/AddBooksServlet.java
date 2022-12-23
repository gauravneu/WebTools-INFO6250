package com.Controller;

import com.Bean.Book;
import com.Dao.BookDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "AddBooksServlet", value = "/addBooks")
public class AddBooksServlet extends HttpServlet {

    private BookDAO bookDao;
    private List<Book> allBooks;
    public void init(){
        bookDao = new BookDAO();
        allBooks = new ArrayList<>();
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
        response.setContentType("text/plain");
        PrintWriter pw = response.getWriter();
        String s1 = request.getParameter("Set1");
        if(s1.equals("createForm")){
            System.out.println("Reached else 3");
            request.setAttribute("countBooks", Integer.parseInt(request.getParameter("countOfBooks")));
            RequestDispatcher rd = request.getRequestDispatcher("/JSP/addMultipleBooksForm.jsp");
            rd.forward(request, response);
        }else if(s1.equals("insertBooks")){
            System.out.println("INSET");
            int counter = Integer.parseInt(request.getParameter("booksCounter"));
            System.out.println("Reached counter " + counter);
            String[] isbnArray = request.getParameterValues("isbn");
            String[] titleArray = request.getParameterValues("title");
            String[] authorArray = request.getParameterValues("author");
            String[] priceArray = request.getParameterValues("price");

            for(int i=0;i<counter;i++){
                Book book = new Book(isbnArray[i], titleArray[i], authorArray[i],Float.parseFloat(priceArray[i]));
                bookDao.insertBook(book);
                try {
                    allBooks = bookDao.getAllBooks();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                request.setAttribute("allBooks", allBooks);
            }
            RequestDispatcher rd1 = request.getRequestDispatcher("/JSP/booksAdded.jsp");
            rd1.forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            allBooks = bookDao.getAllBooks();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute("allBooks", allBooks);

        RequestDispatcher rd1 = request.getRequestDispatcher("/JSP/allBooksShow.jsp");
        rd1.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}
