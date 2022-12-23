<%--
  Created by IntelliJ IDEA.
  User: gaurav
  Date: 10/19/22
  Time: 8:50 PM
  To change this template use File | Settings | File Templates.
--%>
<ul>
    <jsp:scriptlet>
        if (session.getAttribute("uName") != null) {
    </jsp:scriptlet>
    <a href="books.jsp?hlink=books">Books</a></br>
    <a href="computer.jsp?hlink=computer">Computer</a></br>
    <a href="music.jsp?hlink=music">Music</a></br>
  <jsp:scriptlet> }</jsp:scriptlet>
</ul>
