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
    <title>Add Movie</title>
</head>
<body>
    <form action="addmovie.htm" method="post">
        <div>
            Movie title : <input type="text" name="title" maxlength="80" required/></br>
            Lead Actor : <input type="text" name="leadActor" maxlength="30" required/></br>
            Lead Actress : <input type="text" name="leadActress" maxlength="30" required/></br>
            Genre : <input type="text" name="genre" maxlength="20" required/></br>
            Year : <input type="number" name="year" max="2023" min="1900" required/></br>
        </div>
        <input type="submit" value="Add">
    </form>


</body>
</html>
