<%--
  Created by IntelliJ IDEA.
  User: gaurav
  Date: 10/19/22
  Time: 9:34 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Cart</title>

</head>
<body>
<%@include file="topNavigation.jsp" %>
<h4>Books</h4>
<jsp:scriptlet>
    if (session.getAttribute("username") == null) {
        response.sendRedirect("index.jsp");
    }
</jsp:scriptlet>
<%@include file="productCategories.jsp" %>

<form method="post" action="addtocart.htm">
         <span>
             <input type="checkbox" name="books" value="Java Web Design[INR 350.08]"/>
            Java Web Design[INR 350.08]<br/>
            <input type="checkbox" name="books" value="Head First Python[INR 551.08]"/>
            Head First Python[INR 551.08]<br/>
            <input type="checkbox" name="books" value="Head First Core Servlet[INR 334.65]"/>
            Head First Core Servlet[INR 334.65]<br/>
            <input type="checkbox" name="books" value="Head First More Servlet[INR 556.98]"/>
            Head First More Servlet[INR 556.98]<br/>
            <input type="checkbox" name="books" value="Java Servlets [INR 290.08]"/>
            Java Servlets [INR 290.08]<br/>
<!--            <input type="hidden" value=${hlink} name="hlink"/>
            <h1>${hlink}</h1>-->
            <input type="submit" value="Add To Cart"/>
        </span>
</form>
</body>
</html>