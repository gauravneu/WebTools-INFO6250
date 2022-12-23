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
        <c:if test="${null != sessionScope.user.role}">
            Hello ${sessionScope.user.name}
            Logged in as : <a>${sessionScope.user.role} User</a>&nbsp;&nbsp;&nbsp;
            <a href="/allUserorders.htm">Orders</a>&nbsp;&nbsp;&nbsp;
            <c:if test="${sessionScope.user.role == PREMIUM}">
                <a href="/">Cancel Premium Subscription</a>&nbsp;&nbsp;&nbsp;
            </c:if>
            <a href="/">Delete Profile</a>&nbsp;&nbsp;&nbsp;
            <a href="/viewCart.htm">Cart</a>&nbsp;&nbsp;&nbsp;
            <a href="/signout.htm">Logout</a>&nbsp;&nbsp;&nbsp;
        </c:if>

    </div>
</nav>

<c:if test="${requestScope.userCart.size() ==0 }">
    <h1>
        Looks Like Your Cart Is Empty!!. Please select items to Buy!!
    </h1>
</c:if>

<c:if test="${null != requestScope.someProductLess }">
    <h1>
            ${requestScope.someProductLess}
    </h1>
</c:if>


<c:if test="${requestScope.userCart.size() !=0 }">
    <div class="row">
        <div class="column" style="alignment: right; padding-left: 110px ; padding-right: 110px">
            <c:if test="${null != requestScope.InvalidRequest}"></c:if>
            <h3>${requestScope.InvalidRequest}</h3>
            <table style="border-width: 5px;border-style: solid; border-color: black">
                <tr>
                    <h2 align="center"> Your Cart</h2>
                </tr>
                <tr>
                    <th style="text-align: center; border: solid">Game</th>
                    <th style="text-align: center; border: solid">Game Name</th>
                    <th style="text-align: center; border: solid">Quantity Added</th>
                    <th style="text-align: center; border: solid">Add Quantity</th>
                    <th style="text-align: center; border: solid">Remove Quantity</th>
                </tr>
                <c:forEach var="allGames" items="${requestScope.allGames}">
                    <c:forEach var="cartGames" items="${requestScope.userCart}">
                        <c:if test="${cartGames.key.name.equals(allGames.name) and cartGames.value > 0}">
                            <tr>
                                <div>
                                    <td>
                                        <div><img alt="img" style="width: 50px"
                                                  src="data:image/jpeg;base64,${allGames.base64Encoded}"/></div>
                                    </td>
                                    <td>
                                            ${allGames.name}
                                    </td>
                                    <td>
                                            ${cartGames.value}</td>
                                    <td>
                                        <c:if test="${(allGames.quantity - cartGames.value) > 0}">
                                            <form action="addGameToCart.htm" method="post"
                                                  enctype="multipart/form-data">
                                                <input hidden type="hidden" value="${allGames.name}" name="hiddenGame"/>
                                                <input type="hidden" name="gameQuantityToAdd" value="1">
                                                <input type="submit" name="gameName" value="ADD">
                                            </form>
                                        </c:if>
                                        <c:if test="${(allGames.quantity - cartGames.value) <= 0}">
                                        <h4>Only ${allGames.quantity} items can be added to Cart<h4>
                                                <c:set var="canCheckOut" value="0"/>
                                            </c:if>
                                    </td>
                                    <td>
                                        <form action="addGameToCart.htm" method="post"
                                              enctype="multipart/form-data">
                                            <input hidden type="hidden" value="${allGames.name}" name="hiddenGame"/>
                                            <input type="hidden" name="gameQuantityToRemove" value="1">
                                            <input type="submit" name="gameName" value="REMOVE">
                                        </form>
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

<c:if test="${requestScope.userCart.size() != 0 }">
    <form action="checkout.htm" method="post">
        <input hidden type="hidden" value="${sessionScope.user.email}" name="userCheckingOut"/>
        <input type="submit" name="gameName" value="Check Out!!">
    </form>
</c:if>


</body>
</html>