package com.hotel.demo.HotelService.restaurantService.menu;

public class MenuInfo {
   private Integer menu_id;
   private String item;
   private Double item_cost;

    public Integer getMenu_id() {
        return menu_id;
    }

    public void setMenu_id(Integer order_id) {
        this.menu_id = order_id;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public Double getItem_cost() {
        return item_cost;
    }

    public void setItem_cost(Double item_cost) {
        this.item_cost = item_cost;
    }
}
