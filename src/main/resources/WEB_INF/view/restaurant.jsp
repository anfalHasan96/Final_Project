
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<%@ page  language="java"  contentType="text/html; charset=UTF-8"  %>
<html>
<head>
<title>Restaurant</title>
    <script src="WEB_INF/view/jquery-3.4.1.js"></script>
    <script type="text/javascript">
        $(document).ready(function() {
            var $result= $('#result');
            var $orderList= $('#orderList');
            var $orderBill= $('#orderBill');


            $('button[type=submit]').click(function(e) {

                    //Prevent default submission of form
                    e.preventDefault();
                $.post({
                    url : '/restaurant/addOrder',
                    data : $('form[name=addOrderForm]').serialize(),
                    success : function(res) {
                        $result.text('');
                        $result.text(JSON.stringify(res));

                    },
                    error: function () {
                        alert("error while adding order");

                    }
                });
            });

            $('#btnView').on('click',function() {

                $.get({
                    url :'/restaurant/viewOrder',

                    success: function (orders) {
                        $result.text('');
                        $orderList.text('');
                        $orderList.text('<tr>' +
                            '<th style=" border:1px solid black;">id</th> ' +
                            '<th style=" border:1px solid black;">item</th>' +
                            ' <th style=" border:1px solid black;">quantity</th> ' +
                            '<th style=" border:1px solid black;">price per unit</th> ' +
                            '<th style=" border:1px solid black;">total price</th>' +
                            '</tr>');

                        $.each(orders,function (i, order) {
                            $orderList.append('<tr>' +
                                '<td style=" border:1px solid black;">'+order.id+'</td> ' +
                                '<td style=" border:1px solid black;">'+order.item+ '</td>' +
                                ' <td style=" border:1px solid black;">'+order.itemQuantity +'</td>' +
                                ' <td style=" border:1px solid black;">'+order.item_cost+'</td> ' +
                                '<td style=" border:1px solid black;">' +order.itemTotalPrice+'</td>' +
                                ' <td style=" border:1px solid black;">'+
                            '<button value="' + order.order_id+'"  class="remove">x</button> </td>' +
                                '</tr>')
                        });
                    }
                });
            });

            $('#btnSend').on('click',function() {

                $.get({
                    url: '/restaurant/submitOrder',

                    success: function (data) {
                        $('#orderNumber').text('');
                        $orderBill.text('');

                        $('#orderNumber').text('Order Number : '+data.order_id);
                        $orderBill.text('Total Bill : '+data.totalBill+'  Date : '+data.orderDate);
                    }
                });
                    });
            
            $orderList.delegate('.remove','click',function () {
                var $tr=$(this).closest('tr');
                $.ajax({
                    type: 'DELETE',
                    url: '/restaurant/deleteItem/' + $(this).attr('id'),
                    dataType: "json",
                    success: function () {
                        $tr.remove();
                    }
                });
            });
        });

    </script>
</head>

<body>

<h4>Select the item from the menu you want to order</h4>

<form:form method="post" action="/restaurant/addOrder"  name="addOrderForm"  >
    <modelAttribute>orderInfo</modelAttribute>

    <form:select path="item_id"   >
        <form:option value="-" label="Menu"/>
        <form:options items="${menuList}"  itemValue="${menuList.item_id}" itemLabel="${menuList.item}"/>
    </form:select>

    <form:select path="itemQuantity" name="quantity"  >
        <form:option value="none" label="Quantity" selected="selected" disabled="true" hidden="true" />
        <form:option value="1" label="1"/>
        <form:option value="2" label="2"/>
        <form:option value="3" label="3"/>
        <form:option value="4" label="4"/>
        <form:option value="5" label="5"/>
        <form:option value="6" label="6"/>
        <form:option value="7" label="7"/>
        <form:option value="8" label="8"/>
        <form:option value="9" label="9"/>
        <form:option value="10" label="10"/>
    </form:select>


    <button TYPE="submit" >ADD order</button>
</form:form>
<br>
<p id="result"></p>
<p id="orderNumber"></p>
<table id="orderList"  style=" border:1px solid black;  border-collapse: collapse;"></table>
<p id="orderBill"></p>
    <br>
<button id="btnView" >View Order</button>
<button id="btnSend">Submit Order</button>
<a href="/hotel/home"><button>home</button></a>
</body>
</html>