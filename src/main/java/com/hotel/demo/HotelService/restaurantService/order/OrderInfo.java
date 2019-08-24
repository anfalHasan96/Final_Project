package com.hotel.demo.HotelService.restaurantService.order;

import com.hotel.demo.HotelService.restaurantService.menu.MenuInfo;

public class OrderInfo extends MenuInfo {
    private Integer order_id;
    private String userName;
    private Integer itemQuantity;
    private double itemTotalPrice;

    public Integer getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Integer order_id) {
        this.order_id = order_id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(Integer itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public double getItemTotalPrice() {
        return itemTotalPrice;
    }

    public void setItemTotalPrice(double itemTotalPrice) {
        this.itemTotalPrice = itemTotalPrice;
    }
}
