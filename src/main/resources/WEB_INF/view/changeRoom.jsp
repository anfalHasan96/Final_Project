<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<%@ page  language="java"  contentType="text/html; charset=UTF-8"  %>
<html>
<head>

    <script src="WEB_INF/view/jquery-3.4.1.js"></script>
    <script type="text/javascript">
        $(document).ready(function() {
            var room_id=document.getElementById("roomList").value;
            var $result = $('#result');

            $('#btnChange').on('click',function() {

                $.get({
                    url : '/reservation/changeRoom/'+ room_id,

                    success : function(res) {
                        $result.text('');
                        $result.text(JSON.stringify(res));

                    },
                    error: function () {
                        alert("error while changing room");

                    }
                });
            });

        });
    </script>

</head>
<body>
<h4>Please select the new room order_id you want</h4><br>

    <select path="order_id"  multiple="false" id="roomList">
        <option value="-" label="--Select Rooms"/>
        <options items="${roomList}" />
    </select>
    <button id="btnChange">Change</button><br>
<p id="result"></p>
<br>
<a href="/hotel/home"><button>home</button></a>
</body>
</html>