package com.hotel.demo.Bill;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class BillDaoImpl implements BillDao {

    private final String ADD_ELEMENT="insert into customer_bill(user_name,service_type,service_cost,service_date,bill_status)" +
            "values(?,?,?)";
    private final String DELETE_ELEMENT="delete from customer_bill where id=?";
    private final String SET_BILL_NOTVALID="update customer_bill set bill_status='not_valid' where user_name=?";

    private final String GET_BILL="select * from customer_bill WHERE " +
            "user_name=? AND bill_status='valid'";

    private final String GET_Status="select status from bill_status where user_name=?";
    private final String SET_BILL_STATUS="insert into bill_status(user_name,total_bill,status,checkout_date)" +
            "values(?,?,?,?)";
    private final String GET_BILL_INFO="select * from bill_status where " +
            "user_name=?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void addElement(BillInfo billInfo) {
        jdbcTemplate.update(ADD_ELEMENT,billInfo.getUserName(),billInfo.getService_type(),
                billInfo.getService_cost(),new Date(),billInfo.getBill_status());

    }

    @Override
    public void deleteElement(Integer id) {
        jdbcTemplate.update(DELETE_ELEMENT,id);

    }

    @Override
    public void setBilNotValid(String userName) {
        jdbcTemplate.update(SET_BILL_NOTVALID,userName);
    }

    @Override
    public ArrayList<BillInfo> getBill(String userName) {
        return (ArrayList) jdbcTemplate.query(GET_BILL, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setString(1, userName);
            }
        }, new RowMapper<BillInfo>() {
            @Override
            public BillInfo mapRow(ResultSet resultSet, int i) throws SQLException {
                BillInfo billInfo=new BillInfo();
                billInfo.setId(resultSet.getInt("id"));
                billInfo.setUserName(resultSet.getString("user_name"));
                billInfo.setService_type(resultSet.getString("service_type"));
                billInfo.setService_cost(resultSet.getDouble("service_cost"));
                billInfo.setService_date(resultSet.getDate("service_date"));
                billInfo.setBill_status(resultSet.getString("bill_status"));
                return billInfo;
            }
        });
    }

    @Override
    public String getBillStatus(String userName) {

        return jdbcTemplate.queryForObject(GET_Status,new Object[]{userName},String.class);

    }

    @Override
    public void setBillStatusInfo(BillStatusInfo bill) {
        jdbcTemplate.update(SET_BILL_STATUS,bill.getUserName(),bill.getTotalBill(),bill.getBillStatus()
        ,bill.getCheckoutDate());
    }

    @Override
    public BillStatusInfo getBillStatusInfo(String userName) {
        return jdbcTemplate.queryForObject(GET_BILL_INFO,new Object[]{userName}, new RowMapper<BillStatusInfo>() {
            @Override
            public BillStatusInfo mapRow(ResultSet resultSet, int i) throws SQLException {
                BillStatusInfo billInfo=new BillStatusInfo();
                billInfo.setBill_id(resultSet.getInt("bill_id"));
                billInfo.setUserName(resultSet.getString("user_name"));
                billInfo.setBillStatus(resultSet.getString("status"));
                billInfo.setTotalBill(resultSet.getDouble("total_bill"));
                billInfo.setCheckoutDate(resultSet.getDate("checkout_date"));
                return billInfo;
            }
        });
    }
}

