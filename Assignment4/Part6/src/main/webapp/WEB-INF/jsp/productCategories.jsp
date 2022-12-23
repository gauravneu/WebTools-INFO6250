<%--
  Created by IntelliJ IDEA.
  User: gaurav
  Date: 10/19/22
  Time: 8:50 PM
  To change this template use File | Settings | File Templates.
--%>
<html>
    
    <jsp:scriptlet>
        if (session.getAttribute("username") != null) {
    </jsp:scriptlet>
    <div><form action = "books.htm" method="get"><input type="submit" value="Books"/></form>
    <form action = "computer.htm" method="get"><input type="submit"  value="Computer"/></form>
    <form action = "music.htm" method="get"><input type="submit"  value="Music"/></form></div>
  <jsp:scriptlet> }</jsp:scriptlet>
</html>
