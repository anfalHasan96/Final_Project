<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<%@ page  language="java"  contentType="text/html; charset=UTF-8"  %>
<html>
<head>
    <title>Create Account </title>
    <script src="WEB_INF/view/jquery-3.4.1.js"></script>
    <script type="text/javascript">
        $(document).ready(function() {
            var $result = $('#result');

            $('button[type=submit]').click(function (e) {

                //Prevent default submission of form
                e.preventDefault();
                $.post({
                    url: '/account/signUp',
                    data: $('form[name=signUpForm]').serialize(),
                    success: function (res) {
                        $result.text('');
                        $result.text(res);

                    },
                    error: function () {
                        alert("error while saving information");

                    }
                });
            });
        });
    </script>
        </head>
<body>
<form:form method="post" action="/account/signUp" name="signUpForm">
    <modelAttribute>newUser</modelAttribute>

    First Name<br> <form:input type="text" id="firstName"  path="first_name" /><hr>
    Mid Name<br><form:input type="text" id="midName" path="mid_name"/><hr>
    Last Name<br><form:input type="text" id="lastName" path="last_name"/><br>
    SSN<br> <form:input type="text" id="ssn" path="ssn"/><hr>
    phone Number<br> <form:input type="text" id="phone" path="phoneNumber"/><hr>
    email<br> <form:input type="text" id="email" path="email"/><br>
    UserName<br> <form:input type="text" id="userName" path="userName"/><hr>
    Password<br> <form:input type="password" id="password" path="password"/><br>
    <input type="submit" id="signUp" value="create"/><br>
</form:form>
<p id="result"></p>
</body>
</html>