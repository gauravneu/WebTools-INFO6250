<%--
  Created by IntelliJ IDEA.
  User: gaurav
  Date: 10/19/22
  Time: 9:36 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Cart</title>
</head>
<body>
<%@include file="topNavigation.jsp" %>
<h3>Computers</h3>

<jsp:scriptlet>
    if(session.getAttribute("username") == null){
        response.sendRedirect("login.jsp");
    }
</jsp:scriptlet>
<%@include file="productCategories.jsp" %>


    <form method="post" action="addtocart.htm">
        <span>
        <input type="checkbox" name="coms" value="Lenovo [INR 50000.09]"/>
        Lenovo [INR 50000.09]<br/>
        <input type="checkbox" name="coms" value="HP [INR 61000.09]"/>
        HP [INR 61000.09]<br/>
        <input type="checkbox" name="coms" value="Apple Macbook [INR 69000.09]"/>
        Apple Macbook [INR 69000.09]<br/>
        <input type="checkbox" name="coms" value="Apple Macbook Pro [INR 89090.09]"/>
        Apple Macbook Pro [INR 89090.09]<br/>
        <input type="checkbox" name="coms" value="Compaq [INR 35000.98]"/>
        Compaq [INR 35000.98]<br/>
         <input type="submit" value="Add To Cart"/>
        </span>
    </form>

</body>
</html>
