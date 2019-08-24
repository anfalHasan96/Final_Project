package com.hotel.demo.Room;

import com.hotel.demo.User.UserInfo;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RoomRowMapper implements RowMapper<RoomInfo> {
    @Override
    public RoomInfo mapRow(ResultSet rs, int i) throws SQLException {

        RoomInfo room=new RoomInfo();
        room.setSize(rs.getString("size"));
        room.setId(rs.getString("id"));
        room.setQuality(rs.getString("quality"));
        room.setSpecifications(rs.getString("specifications"));
        room.setPrice(rs.getDouble("price"));
    return room;
    }
    }