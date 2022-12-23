<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
    <style>
        h1 {
            text-align: center;
            color: black;
        }
        h1 span {
            background-color: aqua;
        }
        div{
            text-align: center;
        }
    </style>
</head>
<body>
<form action="TagLibServlet" method="get">
    <h1>
        <span>Click On Submit Button Below to Check JSTL Tags Usages</span>
    </h1>
    <div><input type="submit" value="Submit" style="text-align: center"/></div>
</form>

</body>
</html>