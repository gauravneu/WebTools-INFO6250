package com.example.part6;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "AddMovieServlet", value = "/AddMovieServlet")
public class AddMovieServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter("title");
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

        RequestDispatcher rd = request.getRequestDispatcher("/JSP/movieAddedConfirmation.jsp");
        rd.forward(request, response);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
     catch(ClassNotFoundException |
    SQLException e)

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
        request.setAttribute("moviesListSearch",movies);
        RequestDispatcher rd = request.getRequestDispatcher("/JSP/searchedMovies.jsp");
        rd.forward(request,response);
}
    }
