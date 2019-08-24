<!DOCTYPE html>
<%@ page  language="java"  contentType="text/html; charset=UTF-8"  %>
<html>
<head>
    <script src="WEB_INF/view/jquery-3.4.1.js"></script>
    <script type="text/javascript">
        $(document).ready(function() {
            var $result = $('#result');
            var checkin = document.getElementById("checkin").value;
            var checkout = document.getElementById("checkout").value;

            $('#btnUpdate').on('click',function() {

                $.post({
                    url: '/reservation/changeReserveDate',
                    data: {newCheckin: checkin,newCheckout:checkout},
                    dataType: "json",
                    success: function (res) {
                        $result.text('');
                        $result.text(res);
                    },
                    error: function () {
                        alert("error while changing reservation");

                    }
                });
            });
        });
    </script>

</head>
<body>

    new checkin<br> <input type="text" id="checkin" required/><br>
    new checkout <br><input type="text" id="checkout" required/><br>
 <button id="btnUpdate">Update</button>
 <p id="result"></p>
 <br>
<a href="/hotel/home"><button>home</button></a>
</body>
</html>