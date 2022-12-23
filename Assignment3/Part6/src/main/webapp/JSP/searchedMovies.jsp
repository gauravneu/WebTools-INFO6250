<%--
  Created by IntelliJ IDEA.
  User: gaurav
  Date: 10/19/22
  Time: 2:42 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>View Searched Movies</title>
</head>
<body>
The Searched movie is - <br/><br/>
<TABLE border="2">
    <TR>
        <TH>Title</th>
        <TH>Actor</th>
        <TH>Actress</th>
        <TH>Genre</th>
        <TH>Year</th>
    </TR>
    <c:forEach var="movie" items="${moviesListSearch}">
        <tr>
            <td>${movie.title}</td>
            <td>${movie.actor}</td>
            <td>${movie.actress}</td>
            <td>${movie.genre}</td>
            <td>${movie.year}</td>
        </tr>
    </c:forEach>
</table>
<a href="index.jsp">Click here to go back to the main page</a>
</body>
</html>
