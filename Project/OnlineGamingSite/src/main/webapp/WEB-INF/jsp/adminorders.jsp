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
        <a href="/">My Profile </a>
        <a href="/allAdminOrders.htm">Orders</a>
        <a href="/signout.htm">Logout</a>
    </ul>
</nav>

<c:if test="${requestScope.orderList.size() ==0 }"><h1>Looks Like There is Nothing to Show. Please continue
    shopping!!</h1></c:if>

<c:if test="${requestScope.orderList.size() !=0 }">
    <div class="row">
        <div class="column" style="alignment: right; padding-left: 110px ; padding-right: 110px">
            <table style="border-width: 5px;border-style: solid; border-color: black">
                <tr>
                    <h2 align="center">Completed Orders</h2>
                </tr>
                <tr>
                    <th style="text-align: center; border: solid">Order Id</th>
                    <th style="text-align: center; border: solid">Order Status</th>
                    <th style="text-align: center; border: solid">Check Order</th>


                </tr>
                <c:forEach var="availableOrders" items="${requestScope.orderList}">
                    <tr>
                        <div>
                            <td>
                                <div align="center">
                                        ${availableOrders.id}
                                </div>
                            </td>
                            <td>
                                <div align="center">
                                    <c:if test="${availableOrders.delivered == true }">Delivered</c:if>
                                    <c:if test="${availableOrders.delivered == false }">Yet To Be Delievred</c:if>
                                </div>
                            </td>
                            <td>
                                <div align="center">
                                    <form method="get" action="checkorder.htm">
                                        <input hidden type="hidden" value="${availableOrders.id}" name="hiddenorderid"/>
                                        <input type="submit" value="Check Order!!">
                                    </form>
                                </div>
                            </td>
                        </div>
                    </tr>
                </c:forEach>
            </table>
        </div>

        </table>
    </div>
    </div>
</c:if>


</body>
</html>