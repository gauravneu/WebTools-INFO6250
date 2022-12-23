<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Buy Games Online!!</title>

</head>
<body>
<section class="vh-100">

    <c:if test="${null != requestScope.InvalidPassword}">
        <h1 align="center">Password is Invalid!! Please Try Again!!</h1>
    </c:if>

    <c:if test="${null != requestScope.INVALIDUSERNAME}">
        <h1 align="center">Password is Invalid!! Please Try Again!!</h1>
    </c:if>

    <div align="center">
        <div>
            <div>
                <div>
                    <img src="https://blog.placeit.net/wp-content/uploads/2021/05/mockup-of-a-gamer-with-a-hoodie-.png"
                         class="img-fluid" alt="Sample image" height="250px">
                </div>
                <div>
                    <form:form action="loginuser.htm" method="post" name="loginForm" modelAttribute="user"
                               onsubmit="return(validate());">
                        <c:if test="${user2.role == 'UserNotFound'}">
                            <div>
                                <h1 style="alignment: center">UserName Or Password is Incorrect!!</h1>
                            </div>
                        </c:if>
                        <div>
                            <form:input path="email" type="email" id="formEmail" name="emailJS"
                                        placeholder="Enter a valid email address"/>
                            <label for="formEmail"></label>
                        </div>
                        <div class="form-outline mb-3">
                            <form:input path="password" type="password" name="passwordJS" id="formPassword"
                                        placeholder="Enter password"/>
                            <label class="form-label" for="formPassword"></label>
                        </div>
                        <div>
                            <input value="LoginToAccount" type="submit"/>
                            <p>Don't have an account? <a href="signup.htm">Register</a></p>
                        </div>
                        <div>
                            <p>Want to check-out without login? <a href="/guest.htm">Guest User</a></p>
                        </div>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</section>
<script type="text/javascript">
    function validate() {
        if (document.getElementById("formEmail").value.trim() === "") {
            window.alert("Email can't be Empty");
            document.getElementById("formEmail").focus();
            return false;
        }
        if (document.getElementById("formPassword").value.trim() === "") {
            alert("Password can't be Empty");
            document.getElementById("formPassword").focus();
            return false;
        }
        return (true);
    }
</script>
</body>
</html>