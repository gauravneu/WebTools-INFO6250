<%--
  Created by IntelliJ IDEA.
  User: gaurav
  Date: 10/19/22
  Time: 9:36 PM
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Shop Music</title>
</head>
<body>
<%@include file="topNavigation.jsp" %>
<h4>Music</h4>
<jsp:scriptlet>
    if (session.getAttribute("uName") == null) {
        response.sendRedirect("loginPage.jsp");
    }
</jsp:scriptlet>
<%@include file="productCategories.jsp" %>


<form method="post" action="addtocart">
            <span>
                <input type="checkbox" name="mus" value="Demons By Imagine Dragons[INR 200.4]"/>
                Demons By Imagine Dragons[INR 200.4]<br/>
                <input type="checkbox" name="mus" value="Radioactive By Imagine Dragons[INR 260.4]"/>
                Radioactive By Imagine Dragons[INR 260.4]<br/>
                <input type="checkbox" name="mus" value="Starboy by Weekend[INR 989.98]"/>
                Starboy by Weekend[INR 989.98]<br/>
                <input type="checkbox" name="mus" value="Blinding Lights by Weekend[INR 879.98]"/>
                Blinding Lights by Weekend[INR 879.98]<br/>
                <input type="checkbox" name="mus" value="24k Magic by Bruno Mars[INR 233.98]"/>
                24k Magic by Bruno Mars[INR 233.98]<br/>
                <input type="submit" value="Add To Cart"/>
            </span>
</form>
</div>
</body>
</html>

