/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/SpringFramework/AbstractController.java to edit this template
 */
package com.assignment4.part8.controller;

import com.assignment4.part8.utility.Utility;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author gaurav
 */
@Controller
public class MainController {
   
    @Autowired
    Utility utility;
    
    public MainController() {}
    @GetMapping("/printstr.htm")
    public String handleRequestInternal(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        request.setAttribute("message", utility.printMessage());
        request.setAttribute("objectId", utility);
        return "printstr";
        
    }      
 }
    

