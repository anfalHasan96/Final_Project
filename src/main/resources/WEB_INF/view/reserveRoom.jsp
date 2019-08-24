<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<%@ page  language="java"  contentType="text/html; charset=UTF-8"  %>
<html>
<head>
    <script src="WEB_INF/view/jquery-3.4.1.js"></script>
    <script type="text/javascript">
        $(document).ready(function() {
            var $result = $('#result');

            $('button[type=submit]').click(function (e) {

                //Prevent default submission of form
                e.preventDefault();
                $.post({
                    url: '/reservation/reserveRoom',
                    data: $('form[name=reserveForm]').serialize(),
                    success: function (res) {
                        $result.text('');
                        $result.text(res);

                    },
                    error: function () {
                        alert("error while saving reservation");

                    }
                });
            });
        });
    </script>

</head>
<body>

    <form:form method="post"  action="/reservation/reserveRoom" name="reserveForm">

        <modelAttribute>reservationInfo</modelAttribute>

        <form:select path="room_id" >
            <form:option value="-" label="--Select Rooms"/>
            <form:options items="${roomList}" />
        </form:select>
        <br>
        Checkin Date<br> <form:input type="text" id="checkin" path="checkin"/><br>
        Checkout Date<br> <form:input type="text" id="checkout" path="checkout"/><br>

        <input type="submit" value="Reserve"/><br>
    </form:form>
    <p id="result"></p>
    <br>
<a href="/hotel/home"><button>home</button></a>

</body>
</html>