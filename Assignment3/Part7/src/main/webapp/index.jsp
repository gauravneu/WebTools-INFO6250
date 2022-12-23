<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1>How many books do you want to add?</h1>
<form method="post" action="addBooks">
    <input type="number" min="1" max="100" name="countOfBooks" required/>
    <input type="hidden" name="Set1" value="createForm">
    <input type="submit" name="Submit" />
</form>

<form method="get" action="addBooks">
    <p>Click here to Check all the Books Added: </p>
    <input type="hidden" name="Set1" value="seeBooks">
    <input type="submit" name="Show All Books" />
</form>
</body>
</html>