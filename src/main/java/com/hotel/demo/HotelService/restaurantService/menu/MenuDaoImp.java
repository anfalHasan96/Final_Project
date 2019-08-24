package com.hotel.demo.HotelService.restaurantService.menu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MenuDaoImp implements MenuDao {

    private final String ADD_ITEM="insert into restaurant_menu(item,item_cost)" +
            "values(?,?)";
    private final String DELETE_ITEM="delete from restaurant_menu where id=?";
    private final String UPDATE_PRICE ="update restaurant_menu set item_cost=? where id=?";
    private final String GET_ITEM="select * from restaurant_menu where id=?";
    private final String GET_ITEM_PRICE="select item_cost from restaurant_menu where id=?";
    private final String DELETE_MENU="delete from restaurant_menu";
    private final String GET_MENU="select * from restaurant_menu";


    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void addItem(MenuInfo menuInfo) {
        jdbcTemplate.update(ADD_ITEM,menuInfo.getItem(),menuInfo.getItem_cost());

    }

    @Override
    public void deleteItem(Integer id) {
        jdbcTemplate.update(DELETE_ITEM,id);
    }

    @Override
    public void updateItemPrice(Integer id, Double newPrice) {
        jdbcTemplate.update(UPDATE_PRICE,newPrice,id);
    }

    @Override
    public MenuInfo getItem(Integer id) {
        return jdbcTemplate.queryForObject(GET_ITEM, new Object[]{id}, new RowMapper<MenuInfo>() {
            @Override
            public MenuInfo mapRow(ResultSet resultSet, int i) throws SQLException {
                MenuInfo menuInfo=new MenuInfo();
                menuInfo.setMenu_id(resultSet.getInt("id"));
                menuInfo.setItem(resultSet.getString("item"));
                menuInfo.setItem_cost(resultSet.getDouble("item_cost"));
                return menuInfo;
            }
        });
    }

    @Override
    public Double getItemPrice(Integer item_id) {
        return jdbcTemplate.queryForObject(GET_ITEM_PRICE,new Object[]{item_id},Double.class);
    }

    @Override
    public void deleteMenu() {
        jdbcTemplate.update(DELETE_MENU);
    }

    @Override
    public ArrayList<MenuInfo> getMenu() {

        return (ArrayList<MenuInfo>) jdbcTemplate.query(GET_MENU, new RowMapper<MenuInfo>() {
            @Override
            public MenuInfo mapRow(ResultSet resultSet, int i) throws SQLException {
                MenuInfo menuInfo=new MenuInfo();
                menuInfo.setMenu_id(resultSet.getInt("id"));
                menuInfo.setItem(resultSet.getString("item"));
                menuInfo.setItem_cost(resultSet.getDouble("item_cost"));
                return menuInfo;
            }
        });
    }
}
