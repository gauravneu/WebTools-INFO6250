<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Error Page</title>
</head>
<body>
<h1>Go Back To Login Page</h1>


<form:form action="loginuser.htm" method="get" modelAttribute="user">
    <!-- Email input -->
    <input value="LoginToAccount" type="submit" class="btn btn-primary btn-lg"
           style="padding-left: 2.5rem; padding-right: 2.5rem;"/>
</form:form>

</body>
</html>