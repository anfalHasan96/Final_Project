package com.hotel.demo.HotelService.restaurantService.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDaoImpl implements OrderDao {

    private final String ADD = "insert into restaurant_order(user_name,item_id,price_per_unit,item_quantity,item_total_price)" +
            "values(?,?,?,?,?)";
    private final String DELETE_ITEM = "delete from restaurant_order where id=?";
    private final String UPDATE_QUANTITY ="update restaurant_order set item_quantity=? where id=?";
    private final String GET_ALL = "select * from restaurant_order where user_name=?";
    private final String DELETE_ALL = "delete from restaurant_order where user_name=?";


    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void addOrder(OrderInfo orderInfo) {
        jdbcTemplate.update(ADD, orderInfo.getUserName(), orderInfo.getOrder_id(), orderInfo.getItem_cost(),
                orderInfo.getItemQuantity(), orderInfo.getItemTotalPrice());

    }


    @Override
    public void deleteOrderItem(Integer id) {
        jdbcTemplate.update(DELETE_ITEM, id);
    }

    @Override
    public void updateQuantity(Integer id,Integer newQuantity) {
        jdbcTemplate.update(UPDATE_QUANTITY,newQuantity,id);
    }

    @Override
    public ArrayList<OrderInfo> getAllOrder(String userName) {
        return (ArrayList<OrderInfo>) jdbcTemplate.query(GET_ALL, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setString(1, userName);
            }
        }, new RowMapper<OrderInfo>() {
            @Override
            public OrderInfo mapRow(ResultSet resultSet, int i) throws SQLException {

                OrderInfo orderInfo = new OrderInfo();
                orderInfo.setOrder_id(resultSet.getInt("id"));
                orderInfo.setUserName(resultSet.getString("user_name"));
                orderInfo.setMenu_id(resultSet.getInt("item_id"));
                orderInfo.setItemQuantity(resultSet.getInt("item_quantity"));
                orderInfo.setItem_cost(resultSet.getDouble("price_per_unit"));
                orderInfo.setItemTotalPrice(resultSet.getDouble("item_total_price"));
                return orderInfo;
            }
        });
    }

    @Override
    public void deleteAllOrder(String userName) {
        jdbcTemplate.update(DELETE_ALL, userName);
    }
}
