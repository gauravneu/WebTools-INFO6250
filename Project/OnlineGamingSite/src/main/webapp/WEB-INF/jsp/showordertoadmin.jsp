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
{ADMIN ORDER PAGE}
<div class="row">
    <div class="column" style="alignment: right; padding-left: 110px ; padding-right: 110px">
        <table style="border-width: 5px;border-style: solid; border-color: black">
            <tr>
                <h2 align="center">Order ID: ${requestScope.currentorder.id}</h2>
            </tr>
            <tr>
                <th style="text-align: center; border: solid">Game</th>
                <th style="text-align: center; border: solid">Game Name</th>
                <th style="text-align: center; border: solid">Game Quantity</th>
                <th style="text-align: center; border: solid">Game Price</th>


            </tr>
            <c:forEach var="currentgame" items="${requestScope.currentorder.orders.keySet()}">
                <tr>
                    <div>
                        <td>
                            <div><img alt="img" style="width: 50px"
                                      src="data:image/jpeg;base64,${currentgame.base64Encoded}"/></div>
                        </td>
                        <td>${currentgame.name}</td>
                        <td>${requestScope.currentorder.orders.get(currentgame)}</td>
                        <td>${currentgame.price}</td>
                    </div>
                </tr>
            </c:forEach>
            <tr>
                <td>
                    <c:if test="${requestScope.currentorder.delivered == true}">
                        Order Delivered
                    </c:if>
                    <c:if test="${requestScope.currentorder.delivered == false}">
                        <form method="post" action="cancelorder.htm">
                            <input hidden type="hidden" value="${requestScope.currentorder.id}" name="hiddenorderid"/>
                            <input type="submit" value="Cancel Order!!">
                        </form>
                    </c:if>
                </td>
                <td>
                    <c:if test="${requestScope.currentorder.delivered == false}">
                        <form method="post" action="acceptorder.htm">
                            <input hidden type="hidden" value="${requestScope.currentorder.id}" name="hiddenorderid"/>
                            <input type="submit" value="Accept Order!!">
                        </form>
                    </c:if>
                </td>
            </tr>
        </table>
</div>
</div>


</body>
</html>