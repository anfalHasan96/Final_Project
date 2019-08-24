<%@ page import="com.hotel.demo.Reservation.ReservationDao" %>
<%@ page import="com.hotel.demo.Reservation.ReservationDaoImp" %>
<%@ page import="com.hotel.demo.Reservation.ReservationInfo" %>
<!DOCTYPE html>
<%@ page  language="java"  contentType="text/html; charset=UTF-8"  %>
<html>
<head>

    <script src="WEB_INF/view/jquery-3.4.1.js"></script>
    <script type="text/javascript">
        $(document).ready(function() {
            var $result= $('#result');
    $('#btnDelete').on('click',function() {
    $.ajax({
    type: 'DELETE',
    url: '/reservation/deleteReserve',
    dataType: "json",
    success : function(res) {
    $result.text('');
    $result.text(res);
    },
    error: function () {
    alert("error while deleting reservation");
    }
    });
    });

    });
    </script>
</head>
<body>

<h4>
    {$showReserve}
</h4>
<p id="result"></p>
<br>
<button id="btnDelete">Delete</button><br>
<a href="/hotel/home"><button>home</button></a>
</body>
</html>