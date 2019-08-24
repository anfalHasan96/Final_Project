package com.hotel.demo.User;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<UserInfo> {
    @Override
    public UserInfo mapRow(ResultSet rs, int i) throws SQLException {
        UserInfo user=new UserInfo();
        user.setId(rs.getInt("id"));
        user.setFirst_name(rs.getString("first_name"));
        user.setMid_name(rs.getString("mid_name"));
        user.setLast_name(rs.getString("last_name"));
        user.setEmail(rs.getString("email"));
        user.setPhoneNumber(rs.getString("phone_number"));
        user.setSsn(rs.getString("ssn"));
        user.setUserName(rs.getString("username"));
        user.setPassword(rs.getString("password"));
        user.setJoin_date(rs.getDate("joining_date"));

        return user;
    }
}

