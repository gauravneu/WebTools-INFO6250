/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/SpringFramework/AbstractController.java to edit this template
 */
package com.assignment4.part7.controller;

import com.assignment4.part7.utility.Utility;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

/**
 *
 * @author gaurav
 */
public class MainController extends AbstractController {
    ApplicationContext context;
    public MainController() {}
    
    protected ModelAndView handleRequestInternal(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        Utility helper = (Utility) this.getApplicationContext().getBean("utilservice");
        
        request.setAttribute("message", helper.printMessage());
        request.setAttribute("objectId", helper);
        return new ModelAndView("printstr");
        
    }
        
    }
    

