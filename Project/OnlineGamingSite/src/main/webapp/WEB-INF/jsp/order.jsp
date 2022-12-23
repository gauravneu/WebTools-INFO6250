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
<div class="topnav">
    <a class="active">Hello ${user2.name}</a>
    <div class="search-container">
        <div style="row-span: initial">
            <form action="/searchbygenre.htm" method="get">
                Search By Genre: <select name="gamegenre" id="gamegenre">
                <c:forEach var="eachgenre" items="${requestScope.allGenres}">
                    <option value="${eachgenre.name}">${eachgenre.name}</option>
                </c:forEach>
            </select>
                <button type="submit" style="row-span: inherit">Submit</button>
            </form>
        </div>
        <form action="/searchbypricerange.htm" method="get">
            Search By Price: <select name="pricerange" id="pricerange">
            <option value="0-300">Any Price</option>
            <option value="0-50">Below 50 USD</option>
            <option value="50-100">50+ USD - 100 USD</option>
            <option value="100-300">Above 100 USD</option>
        </select>
            <button type="submit">Submit</button>
        </form>
        <form action="/searchbyname.htm" method="get">
            <input type="text" placeholder="Search By Name" name="nameSearch" id="nameSearch">
            <button type="submit">Submit</button>
        </form>
    </div>
</div>


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
    </table>
</div>
</div>


</body>
</html>