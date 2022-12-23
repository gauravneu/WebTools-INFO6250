<%--
  Created by IntelliJ IDEA.
  User: gaurav
  Date: 10/19/22
  Time: 8:52 PM
  To change this template use File | Settings | File Templates.
--%>
    <ul>
<jsp:scriptlet>
        if(session.getAttribute("username") != null){
 </jsp:scriptlet>
        <form action = "viewcart.htm" method="get"><input type="submit" value="View Cart"/></form>
        <form action = "signout.htm" method="get"><input type="submit" value="Signout"/></form>
 
<jsp:scriptlet> } </jsp:scriptlet>
    </ul>




