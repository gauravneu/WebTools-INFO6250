<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<style>

</style>
<body>


<c:if test="${null != requestScope.ExistingUser}">
    <h1 align="center">Email Already Exists</h1>
</c:if>


<form:form action="/signup.htm" method="post" modelAttribute="user" onsubmit="return(validate());">
    <div>
        <h1>Sign Up</h1>
        <p>Please fill in this form to create an account.</p>

        <hr>
        <label for="name"><b>Name</b></label>
        <form:input path="name" id="formName" type="text" placeholder="Enter Name"/>

        <label for="email"><b>Email</b></label>
        <form:input path="email" id="formEmail" type="text" placeholder="Enter Email"/>

        <label for="password"><b>Password</b></label>
        <form:input path="password" type="password" id="formPassword" placeholder="Enter Password"/>

        <label for="repeatPassword"><b>Repeat Password</b></label>
        <form:input path="repeatPassword" type="Password" id="formRepeatPassword" placeholder="Repeat Password"/>

        <label for="formaddress"><b>Address</b></label>
        <form:input path="address" type="text" id="formaddress" placeholder="Enter Address"/>

        Role :
        <form:select path="role">
            <form:option value="Regular" label="Regular"/>
            <form:option value="Premium" label="Premium"/>
            <form:option value="admin" label="admin"/>
        </form:select>


        <div>
            <button type="submit">Sign Up</button>
        </div>

    </div>
</form:form>
<br/>
<br/>
Go Back To Login:

<form:form action="loginuser.htm" method="get" modelAttribute="user">
    <!-- Email input -->
    <input value="LoginToAccount" type="submit" class="btn btn-primary btn-lg"
           style="padding-left: 2.5rem; padding-right: 2.5rem;"/>
</form:form>

<script type="text/javascript">
    function validate() {
        if (document.getElementById("formName").value.trim() === "") {
            window.alert("Name can't be Empty");
            document.getElementById("formName").focus();
            return false;
        }
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
        if (document.getElementById("formRepeatPassword").value.trim() === "") {
            alert("Repeat Password can't be Empty");
            document.getElementById("formRepeatPassword").focus();
            return false;
        }
        if (document.getElementById("formPassword").value.trim() !== document.getElementById("formRepeatPassword").value.trim()) {
            alert("Passwords Not Matching");
            document.getElementById("formRepeatPassword").focus();
            return false;
        }
        if (document.getElementById("formaddress").value.trim() === "") {
            alert("Address can't be Empty");
            document.getElementById("formaddress").focus();
            return false;
        }
        return (true);
    }
</script>

</body>
</html>
