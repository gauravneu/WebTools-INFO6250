<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: gaurav
  Date: 10/19/22
  Time: 6:04 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Show Books</title>
    <style>
        table, th, td {
            margin-left:auto;
            margin-right:auto;
            alignment-baseline: center;
            align-self: center;
            border:1px solid black;
            text-align: center;
            padding-right: 6px;
            padding-left: 6px;
        }
    </style>
</head>
<body>
    <h1>Books Successfully Added</h1>
<table style="border: 2px solid color(black); padding-right: 8px; padding-left: 8px; alignment: center">
    <tr>
        <th>ISBN</th>
        <th>Title</th>
        <th>Author</th>
        <th>Price</th>
    </tr>
    <c:forEach var="bookAdded" items="${requestScope.allBooks}">
        <tr>
            <td>${bookAdded.isbn}</td>
            <td>${bookAdded.title}</td>
            <td>${bookAdded.authors}</td>
            <td>${bookAdded.price}</td>
        </tr>
    </c:forEach>
</table>
<a href="index.jsp" >Go Back To Home Page</a>
</body>
</html>
