/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/SpringFramework/AbstractController.java to edit this template
 */
package com.webtools.assignment4.controller;

import com.webtools.assignment4.pojo.Movie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

/**
 *
 * @author gaurav
 */
public class MovieController extends AbstractController {
    
    public MovieController() {
    }
    
    protected ModelAndView handleRequestInternal(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        Movie movie = new Movie();
        movie.setActor("Tom");
        movie.setActress("Dua");
        movie.setGenre("Action");
        movie.setTitle("TomDua");
        movie.setYear(1995);
        return new ModelAndView("movieView","movie",movie);
    }
    
}
