<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<%@ page  language="java"  contentType="text/html; charset=UTF-8" %>
<html lang="en">
<head>
    <script src="WEB_INF/view/jquery-3.4.1.js"></script>
    <script type="text/javascript">
        $(document).ready(function() {
            var room_id=document.getElementById("roomList").value;
            var $result = $('#result');

            $('button[type=submit]').click(function(e) {

                //Prevent default submission of form
                e.preventDefault();
                $.get({
                    url : '/room/details/' + room_id,

                    success : function(res) {
                        $result.text('');
                        $result.text(JSON.stringify(res));

                    },
                    error: function () {
                        alert("error while adding order");

                    }
                });
            });

        });
    </script>
        </head>
<body>
<form:form  name="roomDetailsForm" method="get" >

    <modelAttribute>roomInfo</modelAttribute>

    <form:select path="id" id="roomList" >
        <form:option value="-" label="--Select Rooms"/>
        <form:options items="${roomList} " />
    </form:select>
    <input type="submit" value="Room Details">
</form:form>
<p id="result"></p>
<br>
<a href="/reservation/viewReservation"> <button>Reserve Room</button></a><br>
<a href="/hotel/home"><button>home</button></a>


</body>
</html>