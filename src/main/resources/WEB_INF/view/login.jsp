<!DOCTYPE html>
<%@ page  language="java"  contentType="text/html; charset=UTF-8"  %>
<html>
<head>
    <title>register</title>
    <script src="WEB_INF/view/jquery-3.4.1.js"></script>
    <script type="text/javascript">
        $(document).ready(function() {
            var $result= $('#result');
            var $userName=$('#userName').val();
            var $password=$('#password').val();

            $('button[type=submit]').click(function(e) {

                //Prevent default submission of form
                e.preventDefault();
                $.post({
                    url : '/account/login',
                    data : {userName: $userName , password: $password},
                    dataType: "json",
                    success : function(res) {
                        $result.text('');
                        $result.text(res);
                    },
                    error: function () {
                        alert("error in login");

                    }
                });
            });

            $('#btnDelete').on('click',function() {
            $.ajax({
                type: 'DELETE',
                url: '/account/delete',
                dataType: "json",
                success : function(res) {
                    $result.text('');
                    $result.text(res);
                },
                error: function () {
                    alert("error while deleting account");
                }
            });
            });

                });
    </script>
        </head>
<body>
<form method="post" action="/account/login" >
UserName<br> <input type="text" id="userName"  required="required"/><br>
Password <br><input type="password" id="password"  required="required"/>
    <input type="submit" id="login" value="Login"/><br>
</form>

<p id="result"></p>
<br>
  <a href="/account/viewSignUp"><button id="signUp">SignUp</button></a><hr>
  <a href="/account/viewChangePassword"><button id="changePassword">Change Password</button></a><hr>
  <button id="btnDelete">Delete Account</button> <hr>
  <a href="/hotel/home"><button>home</button></a>

</body>
</html>