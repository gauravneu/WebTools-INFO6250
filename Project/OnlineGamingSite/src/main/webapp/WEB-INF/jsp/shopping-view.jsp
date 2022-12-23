<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <link rel="stylesheet" href="style.css"/>
    <title>Shop Now</title>
</head>
<body>
<c:set var="REGULAR" value="Regular"/>
<c:set var="PREMIUM" value="Premium"/>
<c:set var="GUEST" value="Guest"/>
<nav class="navbar">
    <div class="logo">Your Gaming World!!</div>
    <div class="nav-links">
        <c:if test="${null == sessionScope.user.role}">
            Hello ${GUEST} User &nbsp;&nbsp;&nbsp;To Shop : <a href="/signup.htm">Sign Up!!</a> or <a href="/">Log
            In!!</a>
        </c:if>


        <!-- NAVIGATION MENUS -->
        <c:if test="${null != sessionScope.user.role}">
            Hello ${sessionScope.user.name}
            Logged in as : <a>${sessionScope.user.role} User</a>&nbsp;&nbsp;&nbsp;

            <a href="/">My Profile </a>&nbsp;&nbsp;&nbsp;
            <a href="/allUserorders.htm">Orders</a>&nbsp;&nbsp;&nbsp;
            <c:if test="${sessionScope.user.role == REGULAR}">
                <a href="/">Upgrade To Premium</a>&nbsp;&nbsp;&nbsp;
            </c:if>
            <c:if test="${sessionScope.user.role == PREMIUM}">
                <a href="/">Cancel Premium Subscription</a>&nbsp;&nbsp;&nbsp;
            </c:if>
            <a href="/">Delete Profile</a>&nbsp;&nbsp;&nbsp;
            <a href="/viewCart.htm">Cart</a>&nbsp;&nbsp;&nbsp;
            <a href="/signout.htm">Logout</a>&nbsp;&nbsp;&nbsp;
        </c:if>

    </div>
</nav>
<div class="topnav">
    <a class="active">Hello ${user2.name}</a>
    <div class="search-container">
        <div style="row-span: initial">
            <form action="/searchbygenre.htm" method="get">
                Search By Genre: <select name="gamegenre" id="gamegenre">
                <c:forEach var="eachgenre" items="${requestScope.allGenres}">
                    <option value="${eachgenre.name}">${eachgenre.name}</option>
                </c:forEach>
            </select>
                <button type="submit" style="row-span: inherit">Submit</button>
            </form>
        </div>
        <form action="/searchbypricerange.htm" method="get">
            Search By Price: <select name="pricerange" id="pricerange">
            <option value="0-300">Any Price</option>
            <option value="0-50">Below 50 USD</option>
            <option value="50-100">50+ USD - 100 USD</option>
            <option value="100-300">Above 100 USD</option>
        </select>
            <button type="submit">Submit</button>
        </form>
        <form action="/searchbyname.htm" method="get">
            <input type="text" placeholder="Search By Name" name="nameSearch" id="nameSearch">
            <button type="submit">Submit</button>
        </form>
    </div>
</div>
<c:if test="${requestScope.allGames.size() ==0 }"><h1>Looks Like There is Nothing to Show. Please search
    Again!!</h1></c:if>

<c:if test="${requestScope.allGames.size() !=0 }">
    <div class="row">
        <div class="column" style="alignment: right; padding-left: 110px ; padding-right: 110px">
            <table style="border-width: 5px;border-style: solid; border-color: black">
                <tr>
                    <h2 align="center"> Regular Games</h2>
                </tr>
                <tr>
                    <th style="text-align: center; border: solid">Game</th>
                    <th style="text-align: center; border: solid">Game Name</th>
                    <th style="text-align: center; border: solid">Check Out!</th>
                </tr>
                <c:forEach var="availableGames" items="${requestScope.allGames}">
                    <c:if test="${availableGames.premium == false && availableGames.quantity >0}">
                        <tr>
                            <div>
                                <td>
                                    <div>
                                        <img alt="img" style="width: 50px"
                                             src="data:image/jpeg;base64,${availableGames.base64Encoded}"/>
                                    </div>
                                </td>
                                <td>${availableGames.name}</td>
                                <td>
                                    <form:form modelAttribute="games" action="searchgame.htm" method="get"
                                               enctype="multipart/form-data">
                                        <input type="submit" name="gameName" value="${availableGames.name}"/>
                                    </form:form>
                                </td>
                            </div>
                        </tr>
                    </c:if>
                </c:forEach>
            </table>
        </div>
        <div class="column" style="alignment: right; padding-left: 110px ; padding-right: 110px">
            <table style="border-width: 5px;border-style: solid; border-color: black">
                <tr>
                    <h2 align="center"> Premium Benefits</h2>
                </tr>
                <tr>
                    <th style="text-align: center; border: solid">Game</th>
                    <th style="text-align: center; border: solid">Game Name</th>
                    <th style="text-align: center; border: solid">Check Out!</th>
                </tr>
                <c:forEach var="availableGames" items="${requestScope.allGames}">
                    <c:if test="${availableGames.premium == true && availableGames.quantity >0}">
                        <tr>
                            <div>
                                <td>
                                    <div><img alt="img" style="width: 50px"
                                              src="data:image/jpeg;base64,${availableGames.base64Encoded}"/></div>
                                </td>
                                <td>${availableGames.name}</td>
                                <td><form:form modelAttribute="games" action="searchgame.htm" method="get"
                                               enctype="multipart/form-data">
                                    <input type="submit" name="gameName" value="${availableGames.name}"/>
                                </form:form>
                                </td>
                            </div>
                        </tr>
                    </c:if>
                </c:forEach>
            </table>
        </div>
    </div>
</c:if>
<script type="text/javascript">
    function validate() {
        if (document.getElementById("nameSearch").value.trim() === "") {
            window.alert("Game Name can't be Empty");
            document.getElementById("nameSearch").focus();
            return false;
        }
        return (true);
    }
</script>
</body>
</html>