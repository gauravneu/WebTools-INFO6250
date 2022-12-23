<%--
  Created by IntelliJ IDEA.
  User: gaurav
  Date: 10/19/22
  Time: 9:35 PM
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <title>Welcome</title>

</head>
<body>

<input type="hidden" name="session" value=${session}/>
<%@include file="topNavigation.jsp" %>

<jsp:scriptlet>
  if (session.getAttribute("uName") == null) {
    response.sendRedirect("loginPage.jsp");
  }
</jsp:scriptlet>

<%@include file="productCategories.jsp" %>

</body>
</html>