<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Movie Add And Search</title>
</head>
<body>
<h1>Welcome to our Movie Store</h1>
<br/>
<form action="MovieServlet" method="get"></br>
    <label for="movieOptions">Please make your selection below:</label></br>

    <select name="movieOptions" id="movieOptions">
        <option value="Browse">Browse Movies</option>
        <option value="Add">Add New Movie To Database</option>
    </select></br>
    <input type="submit" value="Send">
</form>
</body>
</html>