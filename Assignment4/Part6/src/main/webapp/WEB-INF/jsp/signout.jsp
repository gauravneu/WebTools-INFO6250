<%--
  Created by IntelliJ IDEA.
  User: gaurav
  Date: 10/19/22
  Time: 9:36 PM
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <title>Orders Page</title>
</head>
<body>
<%@include file="topNavigation.jsp" %>
<jsp:scriptlet>
    if(session.getAttribute("username") != null){
        session.invalidate();
        response.sendRedirect("loginPage.jsp");
    }
    else {
        response.sendRedirect("loginPage.jsp");
    }
</jsp:scriptlet>
</body>
</html>
