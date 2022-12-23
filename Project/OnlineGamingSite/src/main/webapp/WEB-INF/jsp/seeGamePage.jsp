<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <link rel="stylesheet" href="style.css"/>
    <title>Shop Now</title>
</head>
<body>
<c:set var="REGULAR" value="Regular"/>
<c:set var="PREMIUM" value="Premium"/>
<c:set var="GUEST" value="Guest"/>
<nav class="navbar">
    <div class="logo">Your Gaming World!!</div>
    <div class="nav-links">
        <c:if test="${null == sessionScope.user.role}">
            Hello ${GUEST} User &nbsp;&nbsp;&nbsp;To Shop : <a href="/">Sign Up!!</a> or <a href="/">Log In!!</a>
        </c:if>


        <!-- NAVIGATION MENUS -->
        <c:if test="${null != sessionScope.user.role}">
            Hello ${sessionScope.user.name}
            Logged in as : <a>${sessionScope.user.role} User</a>&nbsp;&nbsp;&nbsp;

            <a href="/">My Profile </a>&nbsp;&nbsp;&nbsp;
            <a href="/allUserorders.htm">Orders</a>&nbsp;&nbsp;&nbsp;
            <c:if test="${sessionScope.user.role == REGULAR}">
                <a href="/">Upgrade To Premium</a>&nbsp;&nbsp;&nbsp;
            </c:if>
            <c:if test="${sessionScope.user.role == PREMIUM}">
                <a href="/">Cancel Premium Subscription</a>&nbsp;&nbsp;&nbsp;
            </c:if>
            <a href="/">Delete Profile</a>&nbsp;&nbsp;&nbsp;
            <a href="/viewCart.htm">Cart</a>&nbsp;&nbsp;&nbsp;
            <a href="/signout.htm">Logout</a>&nbsp;&nbsp;&nbsp;
        </c:if>

    </div>
</nav>
<div align="center">
    <div><img alt="img" style="width: 270px" src="data:image/jpeg;base64,${game.base64Encoded}"/></div>
    <div><font size="130px">Game Name: ${game.name}</font></div>

    <c:if test="${game.quantity < 1}">
        <h1>Game is Currently Not Available!! Please check again later</h1>
    </c:if>

    <c:if test="${(sessionScope.user.role == 'Premium' && game.quantity > 0 && game.premium == true) or
    (null != sessionScope.user.role && game.premium == false && game.quantity > 0)}">
    <div><font size="130px">Quantity Available: ${game.quantity}</font></div>

    <c:set var="alreadyExists" value="0"/>

    <c:forEach var="allgamelist" items="${sessionScope.user.cartGameMap}">
        <c:if test="${allgamelist.key.name.equals(game.name)}">
            <c:set var="alreadyExists" value="1"/>
            <h3>No Of Items In Cart: ${allgamelist.value}</h3>
            <c:if test="${allgamelist.value < game.quantity}">
                <form action="/addGameToCart.htm" method="post">
                    <input type="number" min="${1}" max="${game.quantity - allgamelist.value}" name="gameQuantityToAdd"
                           required/>
                    <input hidden type="hidden" value="${game.name}" name="hiddenGame"/>
                    <input type="submit" value="Add To Cart!!">
                </form>
            </c:if>
            <form action="/addGameToCart.htm" method="post">
                <input type="number" min="1" max="${allgamelist.value}" name="gameQuantityToRemove" required/>
                <input hidden type="hidden" value="${game.name}" name="hiddenGame"/>
                <input type="submit" value="Remove From Cart!!">
            </form>


        </c:if>
    </c:forEach>

    <c:if test="${alreadyExists == 0}">
        <form action="/addGameToCart.htm" method="post">
            <input type="number" min="1" max="${game.quantity}" name="gameQuantityToAdd" required/>
            <input hidden type="hidden" value="${game.name}" name="hiddenGame"/>
            <input type="submit" value="Add To Cart!!">
        </form>
    </c:if>

</div>
</c:if>

<c:if test="${sessionScope.user.role == 'Regular' && game.premium == true}">
    <h1>Update To Premium To Purchase This Product!!</h1>
</c:if>
<c:if test="${null == sessionScope.user.role}">
    <h1>Login to continue Shopping!! </h1>
    <h1>Only ${game.quantity} left!!</h1>
</c:if>


</body>
</html>