<%--
  Created by IntelliJ IDEA.
  User: gaurav
  Date: 10/19/22
  Time: 8:52 PM
  To change this template use File | Settings | File Templates.
--%>
    <ul>
<jsp:scriptlet>
        if(session.getAttribute("uName") != null){
 </jsp:scriptlet>
      <li>
        <a href="itemList.jsp">[View Cart]</a>
      </li>
      <li>
        <a href="signout.jsp">[Logout]</a>
      </li>
<jsp:scriptlet> } </jsp:scriptlet>
    </ul>




