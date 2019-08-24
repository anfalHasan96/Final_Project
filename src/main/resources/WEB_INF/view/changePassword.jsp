<%@ page import="javax.swing.text.Document" %>
<!DOCTYPE html>
<%@ page  language="java"  contentType="text/html; charset=UTF-8"  %>
<html>
<head>
    <title>Change Password</title>
    <script src="WEB_INF/view/jquery-3.4.1.js"></script>
    <script type="text/javascript">
        $(document).ready(function() {
            var $result = $('#result');
            var $password = $('#password').val();

            $('#btnChange').on('click',function() {

                $.post({
                    url: 'account/changePassword',
                    data: {newPassword: $password},
                    dataType: "json",
                    success: function (res) {
                        $result.text('');
                        $result.text(res);
                    },
                    error: function () {
                        alert("error in login");

                    }
                });
            });
        });
</script>
        </head>
<body>
    New Password <br><input type="password" id="password " required="required"/><br>
    <button id="btnChange">Submit</button>

    <p id="result"></p><br>
<a href="/hotel/home"><button>home</button></a>
</body>
</html>