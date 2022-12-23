/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.webtools.part5.dao;

import com.webtools.part5.pojo.Book;
import java.sql.Connection;
import static java.sql.DriverManager.getConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gaurav
 */
public class BookDAO {
    Connection connection = null;
    Statement stmt = null;

    PreparedStatement preparedStatement = null;
    private static final String insert = "INSERT INTO books VALUES  (?, ?, ?,?);";
    private static final String select = "select * from books";




    public void insertBook(Book book) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = getConnection("jdbc:mysql://localhost/booksdb?useSSL=false", "root", "root");
            preparedStatement = connection.prepareStatement(insert);
            preparedStatement.setString(1, book.getIsbn());
            preparedStatement.setString(2, book.getTitle());
            preparedStatement.setString(3, book.getAuthors());
            preparedStatement.setFloat(4, book.getPrice());
            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Book> getAllBooks() throws SQLException {
        List<Book> books = new ArrayList<>();
        try {Class.forName("com.mysql.jdbc.Driver");
        connection = getConnection("jdbc:mysql://localhost/booksdb?useSSL=false", "root", "root");
            preparedStatement = connection.prepareStatement(select);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String isbn = resultSet.getString("isbn");
                String title = resultSet.getString("title");
                String author = resultSet.getString("authors");
                String price = resultSet.getString("price");

                books.add(new Book(isbn, title, author, Float.parseFloat(price)));
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return books;
    }
}
