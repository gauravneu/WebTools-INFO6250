<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

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
            Hello ${GUEST} User &nbsp;&nbsp;&nbsp;To Shop : <a href="/signup.htm">Sign Up!!</a> or <a href="/">Log
            In!!</a>
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


Final Order:


<c:if test="${requestScope.userCart.size() !=0 }">
    <div class="row">
        <div class="column" style="alignment: right; padding-left: 110px ; padding-right: 110px">
            <h3>${requestScope.InvalidRequest}</h3>
            <table style="border-width: 5px;border-style: solid; border-color: black">
                <tr>
                    <h2 align="center"> Your Cart</h2>
                </tr>
                <tr>
                    <th style="text-align: center; border: solid">Game</th>
                    <th style="text-align: center; border: solid">Game Name</th>
                    <th style="text-align: center; border: solid">Quantity Added</th>
                    <th style="text-align: center; border: solid">Price per Piece</th>
                    <th style="text-align: center; border: solid">Discount</th>
                </tr>
                <c:forEach var="allGames" items="${requestScope.allGames}">
                    <c:forEach var="userGames" items="${requestScope.allGameswithUser}">
                        <c:if test="${userGames.key.equals(allGames)}">
                            <tr>
                                <div>
                                    <td>
                                        <img alt="img" style="width: 50px"
                                             src="data:image/jpeg;base64,${allGames.base64Encoded}"/>
                                    </td>
                                    <td>
                                            ${allGames.name}
                                    </td>
                                    <td>
                                            ${userGames.value}
                                    </td>
                                    <td>
                                            ${allGames.price}
                                    </td>
                                    <td>
                                            ${requestScope.discount}
                                    </td>
                                </div>
                            </tr>
                        </c:if>
                    </c:forEach>
                </c:forEach>
            </table>
        </div>
    </div>
</c:if>


<h3 align="center">Final Price : ${requestScope.payableAmount}</h3>


<h3 align="center">Billing Address : ${sessionScope.user.address}</h3>

<form action="updateorder.htm" method="post">
    <input hidden type="hidden" value="${requestScope.payableAmount}" name="hiddenAmount"/>
    <input type="submit" value="Pay For Order">
</form>

</body>
</html>