/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/SpringFramework/AbstractController.java to edit this template
 */
package com.webtools.assignment4.controller;

import com.webtools.assignment4.pojo.Movie;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

/**
 *
 * @author gaurav
 */
public class BrowseMovieController extends AbstractController {
    
    public BrowseMovieController() {
    }
    
    protected ModelAndView handleRequestInternal(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
                String searchCriteria = request.getParameter("search_key");
        if(searchCriteria == null){
            RequestDispatcher rd = request.getRequestDispatcher("/JSP/browseMoviesForm.jsp");
            rd.forward(request,response);
        }
        String sC = request.getParameter("searchKeyword");
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        List<Movie> movies = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        String titleSearch = "select * from movie where title=?;";
        String actorSearch = "select * from movie where actor=?;";
        String actressSearch = "select * from movie where actress=?;";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con =
                     DriverManager.getConnection("jdbc:mysql://localhost/moviedb?useSSL=false", "root", "root");
            stmt = con.createStatement();
            String sql = null;
            if (searchCriteria.equals("TitleSearch")) {
                preparedStatement = con.prepareStatement(titleSearch);

            } else if (searchCriteria.equals("ActorSearch")) {
                preparedStatement = con.prepareStatement(actorSearch);
            } else {
                preparedStatement = con.prepareStatement(actressSearch);
            }
            preparedStatement.setString(1, sC);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String title = rs.getString("title");
                String actress = rs.getString("actress");
                String actor = rs.getString("actor");
                String genre = rs.getString("genre");
                int year = rs.getInt("year");
                movies.add(new Movie(title, actress, actor, genre, year));
            }
        }
     catch(ClassNotFoundException e)

    {
        e.printStackTrace();
    }finally

    {
        try {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
        return new ModelAndView("movieview","movies",movies);
    }
    
}
