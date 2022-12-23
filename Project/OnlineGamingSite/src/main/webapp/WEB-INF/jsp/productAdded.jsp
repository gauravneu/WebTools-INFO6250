<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Add Products</title>
    <link rel="stylesheet" href="style.css"/>
</head>
<body>
<nav class="navbar">
    <div class="logo">Your Gaming World!!</div>
    <ul class="nav-links">
        <c:out value="${sessionScope.user.name}"/>
        <a>${sessionScope.user.name}</a>
        <a href="/">My Profile </a>
        <a href="/allAdminOrders.htm">Orders</a>
        <a href="/">Delete Profile</a>
        <a href="/signout.htm">Logout</a>
    </ul>
</nav>
<h1>Product Added</h1>

<h1>To Add More Products, Click Below</h1>

<form:form action="addGame.htm" method="get" modelAttribute="games">
    <!-- Email input -->
    <input value="Add More products" type="submit" class="btn btn-primary btn-lg"
           style="padding-left: 2.5rem; padding-right: 2.5rem;"/>
</form:form>
</body>
</html>