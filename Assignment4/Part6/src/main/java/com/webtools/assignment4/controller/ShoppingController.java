/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/SpringFramework/AbstractController.java to edit this template
 */
package com.webtools.assignment4.controller;

import com.webtools.assignment4.pojo.Item;
import java.util.ArrayList;
import java.util.Iterator;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

/**
 *
 * @author gaurav
 */
@Controller
public class ShoppingController extends AbstractController {
    ArrayList<Item> items;
    public ShoppingController() {
        items = new ArrayList();
    }
    
    @RequestMapping(value = "/del.htm", method = RequestMethod.GET)
    @Override
    protected ModelAndView handleRequestInternal(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
                HttpSession httpSession = request.getSession(false);
        String currentUser = (String) httpSession.getAttribute("username");

        if (currentUser != null) {
            httpSession.removeAttribute(request.getParameter("currentitem"));
            items.remove(request.getParameter("currentitem"));
            if (!items.isEmpty()) {
                for (int i = 0; i < items.size(); i++) {
                    if((items.get(i).toString()).equals(request.getParameter("currentitem"))){
                        items.remove(i);
                    }
                }
                httpSession.setAttribute("items", items);
            }
        }
        return new ModelAndView("itemlist");
    }
    
    @RequestMapping(value = {"/viewcart.htm","/addtocart.htm"}, method = RequestMethod.GET)
    protected ModelAndView viewCart(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
                HttpSession httpSession = request.getSession(false);
        String currentUser = (String) httpSession.getAttribute("username");
        if (currentUser != null) {   
                httpSession.setAttribute("items", items);
            }
        return new ModelAndView("itemlist");
        }
    
    


//    @RequestMapping(value = "/addtocart.htm", method = RequestMethod.GET)
//    protected ModelAndView viewCartAgain(
//            HttpServletRequest request,
//            HttpServletResponse response) throws Exception {
//                HttpSession httpSession = request.getSession(false);
//        String currentUser = (String) httpSession.getAttribute("username");
//        if (currentUser != null) {   
//                httpSession.setAttribute("items", items);
//            }
//        return new ModelAndView("itemlist");
//        }

    
        @RequestMapping(value = "/books.htm", method = RequestMethod.GET)
    protected ModelAndView books(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
                HttpSession httpSession = request.getSession(false);
        String currentUser = (String) httpSession.getAttribute("username");
        if (currentUser != null) {   
                httpSession.setAttribute("items", items);
                return new ModelAndView("books");
            }
        return new ModelAndView("loginpage");
        }
    
    
        @RequestMapping(value = "/computer.htm", method = RequestMethod.GET)
    protected ModelAndView computer(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
                HttpSession httpSession = request.getSession(false);
        String currentUser = (String) httpSession.getAttribute("username");
        if (currentUser != null) {   
                httpSession.setAttribute("items", items);
                return new ModelAndView("computer");
            }
        return new ModelAndView("loginpage");
        }
    
    @RequestMapping(value = "/music.htm", method = RequestMethod.GET)
    protected ModelAndView music(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
                HttpSession httpSession = request.getSession(false);
        String currentUser = (String) httpSession.getAttribute("username");
        if (currentUser != null) {   
                httpSession.setAttribute("items", items);
                return new ModelAndView("music");
            }
        return new ModelAndView("loginpage");
        }
        
      
    @RequestMapping(value = "/index.htm", method = RequestMethod.GET)
    protected ModelAndView handleRequestInternal1(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        String username = request.getParameter("l_email");
        String password = request.getParameter("l_password");

        if (username.equals("root@gmail.com") && password.equals("root")) {
            HttpSession httpSession = request.getSession(false);
            if(httpSession != null){
                httpSession.setAttribute("username", username);
                httpSession.setAttribute("password", password);
                return new ModelAndView("index");
            }
        } else {
            return new ModelAndView("loginpage");
        }
        return new ModelAndView();
    }
    
        @RequestMapping(value = "/signout.htm", method = RequestMethod.GET)
    protected ModelAndView signout(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
            System.out.println("signout called");
            HttpSession session = request.getSession(false);
            if(session.getAttribute("username") != null){
            session.invalidate();
            for (Iterator<Item> iterator = items.iterator(); iterator.hasNext(); ) {
    Item value = iterator.next();
//    if (value.length() > 5) {
        iterator.remove();
//    }
}
            return new ModelAndView("loginpage");
        }
        else {
             return new ModelAndView("loginpage");
        }
    }
    
    
    @RequestMapping(value = "/addtocart.htm", method = RequestMethod.POST)
    protected ModelAndView addToCart(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        HttpSession httpSession = request.getSession(false);
        String user = (String) httpSession.getAttribute("username");
        String[] books = request.getParameterValues("books");
        String[] coms = request.getParameterValues("coms");
        String[] music = request.getParameterValues("mus");

        if (!user.isEmpty()) {

            if (music != null) {
                int musicLength = music.length;
                for (int i = 0; i < musicLength; i++) {
                    Item musicName = new Item(music[i]);
                    items.add(musicName);
                }
            }
            if (books != null) {
                int bookLength = books.length;;
                for (int i = 0; i < bookLength; i++) {
                    Item bookName = new Item(books[i]);
                    items.add(bookName);
                }
            }
            if (coms != null) {
                int comsLength = coms.length;
                for (int i = 0; i < comsLength; i++) {
                    Item computer = new Item(coms[i]);
                    items.add(computer);
                }
            }

            httpSession.setAttribute("items", items);
            
        }
    
        return new ModelAndView("itemlist");   
}
}