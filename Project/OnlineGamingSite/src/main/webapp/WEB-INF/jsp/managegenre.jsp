<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Manage Genre!!</title>
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
        <a href="/managegenre.htm"">Manage Genres</a>
        <a href="/signout.htm">Logout</a>
    </ul>
</nav>


<div align="center" style="padding-top: 20px">
    <form:form action="savegenre.htm" method="post" name="addGenreForm" modelAttribute="genre"
               onsubmit="return(validate());">
        <div>
            <form:input path="name" type="text" id="formGenre" placeholder="Enter a valid Genre"/>
            <label for="formGenre"></label>
        </div>
        <div>
            <input value="Add Genre" type="submit"/>
        </div>
        <c:if test="${requestScope.gamingException.equals('OldGenre')}">
            <h1>Genre Already Exists!! Try Another One.</h1>
        </c:if>
    </form:form>
</div>


<br/>
<br/>


<form:form action="loginuser.htm" method="get">

    <input value="Click here to Add Games" type="submit"/>

</form:form>


<script type="text/javascript">
    function validate() {
        if (document.getElementById("formGenre").value.trim() === "") {
            window.alert("Genre can't be Empty");
            document.getElementById("formGenre").focus();
            return false;
        }
        return (true);
    }
</script>
</body>
</html>