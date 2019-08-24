package com.hotel.demo.HotelService.restaurantService.order;

import java.util.ArrayList;

public interface OrderDao {

    void addOrder(OrderInfo orderInfo);
    void deleteOrderItem(Integer id);
    void updateQuantity(Integer id,Integer newQuantity);
    ArrayList<OrderInfo> getAllOrder(String userName);
    void deleteAllOrder(String userName);
}
