/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/SpringFramework/AbstractController.java to edit this template
 */
package com.webtools.part5.controller;

import com.webtools.part5.dao.BookDAO;
import com.webtools.part5.pojo.Book;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

/**
 *
 * @author gaurav
 */
public class BookAddController extends AbstractController {
    private final BookDAO bookDao;
    private List<Book> allBooks; 
    public BookAddController() {
        bookDao = new BookDAO();
        allBooks = new ArrayList<>();
    }
    
    @Override
    protected ModelAndView handleRequestInternal(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        System.out.println("ops");
        if(request.getParameter("ops").equals("numBook")){
            System.out.println("getBooks2");
            return fetchForm(request,response);
        }
        else if(request.getParameter("ops").equals("insertbooks")){
System.out.println("getBooks");
        return updateBooks(request, response);
        }
        else{
        return getAllBooks(request,response);
        }
    }
    
    public ModelAndView fetchForm(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        request.setAttribute("count",request.getParameter("countOfBooks"));
        return new ModelAndView("addbookform");
    }
    
    public ModelAndView updateBooks(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
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
            return new ModelAndView("booksuccesfullyadded");
    }
   
    public ModelAndView getAllBooks(HttpServletRequest request,
            HttpServletResponse response) throws Exception{
    try {
                    allBooks = bookDao.getAllBooks();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                request.setAttribute("allBooks", allBooks);
            
            return new ModelAndView("allbookshow");
    }
    

}
