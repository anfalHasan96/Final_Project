package com.hotel.demo.HotelService.restaurantService.order;

import java.util.Date;

public interface OrderBillDao {

    void addOrderBill(OrderBillInfo orderBillInfo);
    OrderBillInfo getOrderBill(String user_name);
    void setNotValidBill(String user_name, Date orderDate);
}
