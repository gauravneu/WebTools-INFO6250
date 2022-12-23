<%--
  Created by IntelliJ IDEA.
  User: gaurav
  Date: 10/17/22
  Time: 8:47 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<br>
<head>
    <title>Title</title>
    <style>
        table, th, td {
            margin-left:auto;
            margin-right:auto;
            alignment-baseline: center;
            align-self: center;
            border:1px solid black;
            text-align: center;
            padding-right: 6px;
            padding-left: 6px;
        }
    </style>
</head>
<br>
<h1 style="text-align: center">Assignment 3: Part 3</h1>
<sql:setDataSource var="moviedbs" driver="com.mysql.jdbc.Driver" url="jdbc:mysql://localhost/moviedb?useSSL=false"
                   user="root" password="root" />

<sql:update dataSource="${moviedbs}" var="moviet">
    INSERT INTO MOVIE VALUES("The Burning Train","Dharmendra","Hema Malini","Thriller",1980);
</sql:update>

<sql:update dataSource="${moviedbs}" var="moviet">
    INSERT INTO MOVIE VALUES("The Last Samurai","Tom Cruise","koyuki","Drama",2003);
</sql:update>
<sql:update dataSource="${moviedbs}" var="moviet">
    INSERT INTO MOVIE VALUES("The Legends Of Bhagat Singh","Ajay Devgan","Amrita Rao","Period",2002);
</sql:update>
<sql:update dataSource="${moviedbs}" var="moviet">
    INSERT INTO MOVIE VALUES("The Dark Knight","Christian Bale","Maggie Gyllenhaal","Action",2008);
</sql:update>
<sql:update dataSource="${moviedbs}" var="moviet">
    INSERT INTO MOVIE VALUES("The Batman","Robert Pattinson","Zoë Kravitz","Action",2022);
</sql:update>
<sql:update dataSource="${moviedbs}" var="moviet">
    INSERT INTO MOVIE VALUES("Top Gun Maverick","Tom Cruise","Jennifer Connelly","Action",2022);
</sql:update>
<sql:update dataSource="${moviedbs}" var="moviet">
    INSERT INTO MOVIE VALUES("Oblivion","Tom Cruise","Andrea Riseborough","Action",2013);
</sql:update>
<sql:update dataSource="${moviedbs}" var="moviet">
    INSERT INTO MOVIE VALUES("Jack Reacher","Tom Cruise","Rosamund Pike","Action",2012);
</sql:update>

<sql:query dataSource="${moviedbs}" var="movieset">
    SELECT * from movie;
</sql:query>
<h2 style="text-align: center">Initial Table with All Entries</h2>
<table >
    <tr>
        <th>Title</th>
        <th>Title In Upper Case</th>
        <th>Title In Lower Case</th>
        <th>Actor</th>
        <th>Actress</th>
        <th>Genre</th>
        <th>Year</th>
        <th>Movie Name Starts With 'The'</th>
    </tr>

    <c:forEach var="movies" items="${movieset.rows}">
        <tr>
            <td><c:out value="${movies.title}"/></td>
            <td><c:out value="${fn:toLowerCase(movies.title)}"/></td>
            <td><c:out value="${fn:toUpperCase(movies.title)}"/></td>
            <td><c:out value="${movies.actor}"/></td>
            <td><c:out value="${movies.actress}"/></td>
            <td><c:out value="${movies.genre}"/></td>
            <td><c:out value="${movies.year}"/></td>
            <td><c:out value="${fn:startsWith(movies.title,'The')}"/></td>
        </tr>
    </c:forEach>
</table>
</br>
</br>

<c:set var="TitleDel" value="The Burning Train"/>
<sql:update dataSource="${moviedbs}" var="updateYear">
    Delete from movie where title = ?
    <sql:param value = "${TitleDel}"/>
</sql:update>

<sql:query dataSource="${moviedbs}" var="movieset1">
    SELECT * from movie;
</sql:query>
<h2 style="text-align: center">Updated Table with One Entry Deleted</h2>
<table style="border: 2px solid black;" >
    <tr>
        <th>Title</th>
        <th>Title In Upper Case</th>
        <th>Title In Lower Case</th>
        <th>Actor</th>
        <th>Actress</th>
        <th>Genre</th>
        <th>Year</th>
        <th>Movie Name Starts With 'The'</th>
    </tr>
    <c:forEach var="movies1" items="${movieset1.rows}">
        <tr>
            <td><c:out value="${movies1.title}"/></td>
            <td><c:out value="${fn:toLowerCase(movies1.title)}"/></td>
            <td><c:out value="${fn:toUpperCase(movies1.title)}"/></td>
            <td><c:out value="${movies1.actor}"/></td>
            <td><c:out value="${movies1.actress}"/></td>
            <td><c:out value="${movies1.genre}"/></td>
            <td><c:out value="${movies1.year}"/></td>
            <td><c:out value="${fn:startsWith(movies1.title,'The')}"/></td>
        </tr>
    </c:forEach>
</table></br>

<sql:update dataSource="${moviedbs}" var="movieset2">
    delete from movie;
</sql:update>

<sql:query dataSource="${moviedbs}" var="movieset3">
    SELECT * from movie;
</sql:query>

<h2 style="text-align: center">Final Table with All Entries Deleted</h2>

<table style="border: 2px solid black;" >
    <tr>
        <th>Title</th>
        <th>Title In Upper Case</th>
        <th>Title In Lower Case</th>
        <th>Actor</th>
        <th>Actress</th>
        <th>Genre</th>
        <th>Year</th>
        <th>Movie Name Starts With 'The'</th>
    </tr>
    <c:forEach var="movies3" items="${movieset3.rows}">
        <tr>
            <td><c:out value="${movies3.title}"/></td>
            <td><c:out value="${fn:toLowerCase(movies3.title)}"/></td>
            <td><c:out value="${fn:toUpperCase(movies3.title)}"/></td>
            <td><c:out value="${movies3.actor}"/></td>
            <td><c:out value="${movies3.actress}"/></td>
            <td><c:out value="${movies3.genre}"/></td>
            <td><c:out value="${movies3.year}"/></td>
            <td><c:out value="${fn:startsWith(movies3.title,'The')}"/></td>
        </tr>
    </c:forEach>
</table>
</br>
</br>


<h2 style="text-align: center">Fmt JSTL Tags Usage</h2>

<c:set var="dateToFormat" value="<%=new java.util.Date()%>" />
<c:set var="amountToFormat" value="967.34" />
<c:set var="Amount1" value="3209.4544" />
<fmt:parseNumber var="formattedAmount" type="number" value="${amountToFormat}" />
<table>
    <tr>
        <th>Original Date</th>
        <th>Formatted Date</th>
        <th>Number To Be Parsed</th>
        <th>Parsed Number</th>
        <th>Number To Be Formatted</th>
        <th>Formatted Number</th>
    </tr>
    <tr>
        <td>
            ${dateToFormat}
        </td>
        <td>
            <fmt:formatDate type="date" value="${dateToFormat}" />
        </td>
        <td>
            ${amountToFormat}
        </td>
        <td>
            ${formattedAmount}
        </td>
        <td>
            ${Amount1}
        </td>
        <td>
            <fmt:formatNumber type="number" maxIntegerDigits="1" value="${Amount1}" />
        </td>
    </tr>
</table>


<h2 style="text-align: center">Movies Information</h2>
<c:set var="movies4">
    <movies>
        <movie>
            <name>Top Gun Maverick</name>
            <actor>Tom Cruise</actor>
            <year>2022</year>
        </movie>
        <movie>
            <name>The Last Samurai</name>
            <author>Tom Cruise</author>
            <year>2003</year>
        </movie>
        <movie>
            <name>Jack Reacher</name>
            <author>Tom Cruise</author>
            <year>2012</year>
        </movie>
    </movies>
</c:set>
<x:parse xml="${movies4}" var="movies5"/>
<x:set var="movieToPrint" select="$movies5/movies/movie[3]/year"/>
<h4 style="text-align: center">Year of release for Top Gun Maverick : <x:out select="$movieToPrint" /></h4>
<h4 style="text-align: center"></h4>

</body>
</html>