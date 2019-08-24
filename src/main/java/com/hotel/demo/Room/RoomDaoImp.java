package com.hotel.demo.Room;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

public class RoomDaoImp implements RoomDao {

    private final String DELETE="delete from room_details where id=?";
    private final String UPDATE="update room set id=?, specification=? ,quality=?," +
            "size=?,price=?";
    private final String ADD="insert into room_details(id,price,specification,size,quality)" +
            "values(?,?,?,?,?) ";
    private final String SELECT="select * from room_details where id =?";
    private final String SELECT_ALL="select * from room_details";

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void deleteRoom(String room_id) {
        jdbcTemplate.update(DELETE,room_id);
    }

    @Override
    public void addRoom(RoomInfo room) {
        jdbcTemplate.update(ADD,room.getId(),room.getPrice(),room.getSpecifications(),room.getSize(),room.getQuality());

    }

    @Override
    public void updateRoom(RoomInfo room) {
        jdbcTemplate.update(UPDATE,room.getId(),room.getSpecifications(),room.getQuality(),room.getSize(),room.getPrice());

    }

    @Override
    public RoomInfo getRoom(String room_id) {
        return jdbcTemplate.queryForObject(SELECT, new Object[]{room_id},new RoomRowMapper());
    }

    @Override
    public ArrayList<RoomInfo> getAllRoom() {
        return (ArrayList)jdbcTemplate.query(SELECT_ALL,new RoomRowMapper());
    }
}
