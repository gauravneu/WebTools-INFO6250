/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/SpringFramework/AbstractController.java to edit this template
 */
package com.webtools.assignment4.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

/**
 *
 * @author gaurav
 */
public class BrowseOrAddController extends AbstractController {
    
    public BrowseOrAddController() {
    }
    
    protected ModelAndView handleRequestInternal(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        System.out.println("lll");
        String value = request.getParameter("movieOptions");
        if (value.equalsIgnoreCase("add")){
            return new ModelAndView("add");
        }else{
            return new ModelAndView("browser");
        }
    }
    
}
