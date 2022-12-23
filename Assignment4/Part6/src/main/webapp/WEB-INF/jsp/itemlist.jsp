<%--
  Created by IntelliJ IDEA.
  User: gaurav
  Date: 10/19/22
  Time: 9:35 PM
  To change this template use File | Settings | File Templates.
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Cart Page</title>
</head>
<body>
<%@include file="topNavigation.jsp" %>
<%@include file="productCategories.jsp" %>

<jsp:scriptlet>
            if (session.getAttribute("username") == null) {
                response.sendRedirect("loginPage.jsp");
            }
</jsp:scriptlet>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h4 style="alignment: center">Your Cart</h4>
<c:choose>
    <c:when test="${!empty items}">
        <table style="border: 2px; alignment: center">
            <tr>
                <th>Items</th>
                <th>Add/Remove</th>
            </tr>
            <c:forEach var="j" items="${items}">
                <tr>
                    <td>
                            ${j}
                    </td>
                    <td>
                        <form method="get" action="del.htm?currentitem=${j}">
                            <input type="hidden" name="currentitem" value="${j}"/>
                            <input type="submit" value="Remove"/>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </c:when>
    <c:when test="${empty items}">
        <h1 style="alignment: center">your Cart is Empty!</h1>
    </c:when>
</c:choose>
</body>
</html>
