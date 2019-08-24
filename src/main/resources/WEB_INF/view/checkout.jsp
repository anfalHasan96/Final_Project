<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html >
<head>
    <script src="WEB_INF/view/jquery-3.4.1.js"></script>
    <script type="text/javascript">
        $(document).ready(function() {
            var $result = $('#result');
            $('#btnCheckout').on('click', function () {

                $.get({
                    url: '/checkout/submit',

                    success: function (res) {
                        $result.text('');
                        $result.text(res);}
                });
            });
        });
    </script>
</head>
<body>
<table style="border:1px solid black;  border-collapse: collapse;">
    <tr>
        <th style="border:1px solid black;">ID</th>
        <th style="border:1px solid black;" >Service</th>
        <th style="border:1px solid black;">Service Cost</th>
        <th style="border:1px solid black;">Service Date</th>
    </tr>

    <c:set var="totalBill" value="${0}" />
 <c:forEach var="service" items="${bill}" varStatus="id">
     <c:set var="totalBill" value="${totalBill + service.service_cost}" />
     <tr>
                <td style="border:1px solid black;">${id.count}</td>
                <td style="border:1px solid black;">${service.service_type}</td>
                <td style="border:1px solid black;">${service.service_cost}</td>
                <td style="border:1px solid black;">${service.service_date}</td>

            </tr>
        </c:forEach>
    <tr style="border:1px solid black;">Total Bill : ${totalBill}</tr>
</table>
<br>
<button id="btnCheckout">Checkout</button><hr>
<a href="/hotel/home"><button>home</button></a>
<p id="result"></p>
</body>

</html>