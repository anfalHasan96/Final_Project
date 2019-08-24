package com.hotel.demo.HotelService.restaurantService.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class OrderBillDaoImpl implements OrderBillDao {

    private final String ADD = "insert into order_bill(user_name,total_bill,order_date,bill_status)" +
            "values(?,?,?,?)";

    private final String GET = "select * from order_bill where userName=? and bill_status='valid'";
    private final String NOT_VALID_BILL = "update order_bill set bill_status='not_valid' where user_name=?" +
            "and oredr_date=?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void addOrderBill(OrderBillInfo orderBillInfo) {
        jdbcTemplate.update(ADD,orderBillInfo.getUserName(),orderBillInfo.getTotalBill()
                ,orderBillInfo.getOrderDate(),orderBillInfo.getBillStatus());

    }

    @Override
    public OrderBillInfo getOrderBill(String user_name) {
        return jdbcTemplate.queryForObject(GET, new Object[]{user_name},
                new RowMapper<OrderBillInfo>() {
                    @Override
                    public OrderBillInfo mapRow(ResultSet resultSet, int i) throws SQLException {
                        OrderBillInfo orderBillInfo = new OrderBillInfo();
                        orderBillInfo.setUserName(resultSet.getString("user_name"));
                        orderBillInfo.setOrder_id(resultSet.getInt("order_id"));
                        orderBillInfo.setOrderDate(resultSet.getDate("order_date"));
                        orderBillInfo.setTotalBill(resultSet.getDouble("total_bill"));
                        orderBillInfo.setBillStatus(resultSet.getString("bill_status"));

                        return orderBillInfo;
                    }});
}

    @Override
    public void setNotValidBill(String user_name, Date orderDate) {
        jdbcTemplate.update(NOT_VALID_BILL,user_name,orderDate);

    }
}