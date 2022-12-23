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
  if (session.getAttribute("username") == null) {
    response.sendRedirect("loginPage.jsp");
  }
</jsp:scriptlet>

<%@include file="productCategories.jsp" %>

</body>
</html>