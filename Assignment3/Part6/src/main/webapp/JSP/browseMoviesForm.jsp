<%--
  Created by IntelliJ IDEA.
  User: gaurav
  Date: 10/19/22
  Time: 1:12 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Search Movies Options</title>
</head>
<body>
<h2>Search Movies here</h2>
<form action ="AddMovieServlet" method="get">
    <input type="radio" name="search_key" value="TitleSearch"/>Search By Title<br/>
    <input type="radio" name="search_key" value="ActorSearch"/>Search By Actor<br/>
    <input type="radio" name="search_key" value="ActressSearch"/>Search By Actress<br/><br/>
    <label>Search</label>
    <input type="text" name="searchKeyword"/>
    <input type="submit" value="Search"/>

</form>

</body>
</html>
