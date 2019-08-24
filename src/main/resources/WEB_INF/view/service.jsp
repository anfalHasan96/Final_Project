
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<%@ page  language="java"  contentType="text/html; charset=UTF-8"  %>
<html>
<head>
    <script src="WEB_INF/view/jquery-3.4.1.js"></script>
    <script type="text/javascript">
        $(document).ready(function() {
            var service_id=document.getElementById("serviceList").value;
            var $result = $('#result');


            $('#btnSubmit').on('click',function() {
                $.get({
                    url : '/service/request' + service_id,

                    success : function(res) {
                        $result.text('');
                        $result.text(JSON.stringify(res));

                    },
                    error: function () {
                        alert("error while ordering service");

                    }
                });
            });

        });
    </script>

</head>
<body>

    <select path="selectedItem" multiple="false"  name="serviceList">
    <option value="-" label="--Select Service"/>
    <options items="${serviceList}" />
</select>
<br>
<button id="btnSubmit">Submit</button>
<p id="result"></p>
<br>
<a href="/restaurant/view"><button>restaurant</button></a><hr>
<a href="/hotel/home"><button>home</button></a>
</body>
</html>