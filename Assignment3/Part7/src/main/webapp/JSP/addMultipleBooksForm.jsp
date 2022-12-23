<%--
  Created by IntelliJ IDEA.
  User: gaurav
  Date: 10/19/22
  Time: 4:53 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="com.Bean.Book"%>
<%@page import="java.util.ArrayList"%>
<html>
<head>
    <title>Book Add Form</title>
</head>
<body>
<form method="post" action="addBooks">
    <table style="border: 2px; background-color: cyan">
        <c:forEach var="i" begin="1" end="${countBooks}">
            <tr>
                <h4> ENTER DETAILS FOR BOOK ${i}  </h4>
            </tr>
            <tr name="id" value=${i}>
                <label> ISBN </label>
                <input type="text" name="isbn" required/>
                <label> TITLE </label>
                <input type="text" name="title" required/>
                <label> Author </label>
                <input type="text" name="author" required/>
                <label> Price </label>
                <input type="number" step="0.01" name="price"  required/>
                <input type="hidden" name="booksCounter" value="${countBooks}"/>
            </tr>
        </c:forEach><br/>

    </table>
    <input type="submit" value="Submit"/>
    <input type="hidden" name="Set1" value="insertBooks">
</form>
</body>
</html>
