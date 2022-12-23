/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/SpringFramework/AbstractController.java to edit this template
 */
package com.webtools.assignment4.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

/**
 *
 * @author gaurav
 */
public class AddMovieController extends AbstractController {
    
    public AddMovieController() {
    }
    
    protected ModelAndView handleRequestInternal(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        String title = request.getParameter("title");
        System.out.println(title);
        String actor = request.getParameter("leadActor");
        String actress = request.getParameter("leadActress");
        String genre = request.getParameter("genre");
        int year = Integer.parseInt(request.getParameter("year"));
        Connection connection = null;
        Statement stmt = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection =
                    DriverManager.getConnection("jdbc:mysql://localhost/moviedb?useSSL=false", "root", "root");
            stmt = connection.createStatement();

            String sql = "insert into movie values ('" + title + "', '" + actor + "', '" + actress + "', '" + genre + "', " + year + ")";

            stmt.executeUpdate(sql);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
     
        }
        return new ModelAndView("movieaddedsuccessfully");
    
    }
    
    
}
