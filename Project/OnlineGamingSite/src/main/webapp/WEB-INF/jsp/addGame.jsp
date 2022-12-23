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
        <c:out value="Hello ${sessionScope.user.name}"/>
        <a>${sessionScope.user.name}</a>
        <a href="/allAdminOrders.htm">Orders</a>
        <a href="/managegenre.htm">Manage Genre</a>
        <a href="/signout.htm">Logout</a>
    </ul>
</nav>
<c:out value="${requestScope.ExistingGame}"/>

<form:form modelAttribute="games" action="savegame.htm" onsubmit="return(validate());" method="post"
           enctype="multipart/form-data">
    <table style="alignment: center; display: table; border: solid">
        <h1 style="border: #04AA6D; flex: content-box;padding: 10px; color: blue; position: center">Enter New Game</h1>
        <tr>
            <td>Game Name:</td>
            <td><form:input path="name" id="gameName" size="30"></form:input></td>
        </tr>
        <tr>
            <td>Genre:</td>
            <td>
                <select name="gamegenre" id="gamegenre">
                    <c:forEach var="availableGenres" items="${sessionScope.genreslist}">
                        <option value="${availableGenres.name}">${availableGenres.name}</option>
                    </c:forEach>
                </select>
            </td>
        </tr>

        <tr>
            <td>Is Premium:</td>
            <td>
                <form:select id="gamePremium" path="premium">
                    <form:option value="True"/>
                    <form:option value="False"/>
                </form:select>
            </td>
        </tr>
        <tr>
            <td>Price:</td>
            <td><input type="number" min="1" max="300" step="0.5" name="gamePrice" id="gamePrice" size="40" required/>
            </td>
        </tr>
        <tr>
            <td>Quantity:</td>
            <td><input type="number" min="1" max="100" id="gameQuantity" name="gameQuantity" size="40" required/>
        </tr>
        <tr>
            <td>Select Item Photo:</td>
            <td><input type="file" id="gamePhoto" accept=".jpg, .jpeg, .png" name="gameImage" required/></td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit" value="Add Game"/></td>
        </tr>
    </table>
</form:form>

<script type="text/javascript">
    function validate() {
        if (document.getElementById("gameName").value === "") {
            window.alert("Game Name can't be Empty");
            document.getElementById("gameName").focus();
            return false;
        }
        return (true);
    }

    const input = document.getElementById('gamePhoto')
    input.addEventListener('change', (event) => {
        const target = event.target
        if (target.files && target.files[0]) {
            const maxAllowedSize = 5 * 1024 * 1024;
            if (target.files[0].size > maxAllowedSize) {
                target.value = ''
                window.alert("Max Upload Size is 5 MB");
            }
        }
    })
</script>
</body>
</html>