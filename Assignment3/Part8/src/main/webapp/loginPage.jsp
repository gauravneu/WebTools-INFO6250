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
    <title>Login Page</title>
</head>
<body>
<%@include file="topNavigation.jsp" %>
        <h1>Shopping</h1>
            <jsp:scriptlet>
                if(session.getAttribute("uName") != null){
                    response.sendRedirect("index.jsp");
                }
                else if(session.getAttribute("uName") == null){
            </jsp:scriptlet>
            <form action="index" method="post">
                <div>
                    <label>Username</label>
                    <input type="email" placeholder="Email"  name="l_email" required/>
                </div>
                <div>
                    <label>Password</label>
                    <input type="password" placeholder="Password"  name="l_password" required/>
                </div>
                <div>
                    <input type="submit" value="Login"/>
                </div>
            </form>
<jsp:scriptlet> } </jsp:scriptlet>

</body>
</html>
