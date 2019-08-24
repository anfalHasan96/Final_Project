<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page  language="java"  contentType="text/html; charset=UTF-8"  %>
<html lang="en">
<head>
    <title></title>
</head>
<body>
<p>
    <a href="/account/viewLogin"><button>Login</button></a><hr>
    <a href="/account/logout"><button>logout</button></a><hr>
</p>
<ul>
    <li><a href="/room/viewRoom"><button>Show Rooms</button></a></li>
    <li><a href="/reservation/viewReservation"><button>Reservation</button></a></li>
    <li><a href="/service/view"><button>Guest Services</button></a></li>
    <li><a href="/checkout/viewCheckout"><button>Checkout</button></a></li>

</ul>
</body>
</html>