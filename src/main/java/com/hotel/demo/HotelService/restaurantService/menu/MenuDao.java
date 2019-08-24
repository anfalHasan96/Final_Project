package com.hotel.demo.HotelService.restaurantService.menu;

import java.util.ArrayList;

public interface MenuDao {
    void addItem(MenuInfo menuInfo);
    void deleteItem(Integer id);
    void updateItemPrice(Integer id,Double newPrice);
    MenuInfo getItem(Integer id);
    Double getItemPrice(Integer item_id);
    void deleteMenu();
    ArrayList<MenuInfo> getMenu();

}
