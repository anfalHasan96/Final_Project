package com.hotel.demo.Reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class ReservationDaoImp implements ReservationDao {

    private final String INSERT="insert into reservation(username,room_id,checkin,checkout)" +
            "values(?,?,?,?)";

    private final String DELETE="delete from reservation where username=? and room_id=?";
    private final String INSERT_RESERVE_HISTORY="insert into reservation_history(username,room_id,checkin,checkout,actuall_checkout)" +
            "values(?,?,?,?,?)";

    private final String CHANGE_ROOM="update reservation set room_id=? where username=?";
    private final String UPDATE_Date="update reservation set checkin=?,checkout=? where username=?";
    private final String SELECT="select * from reservadation where userdatabase name=? ";
    private final String GET_ROOM_RESERVATION="select checkin,checkout from reservation where room_id=? ";

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public String reserveRoom(ReservationInfo reserve) {
        jdbcTemplate.update(INSERT,reserve.getUser_Name(),reserve.getRoom_id(),reserve.getCheckin(),reserve.getCheckout());
        return "Room reserved successfully";
    }

    @Override
    public String deleteReserve(String userName) {
        jdbcTemplate.update(DELETE,userName);
        return "Reserve deleted ";
    }

    @Override
    public String updateReservationDate(String user_name, Date newCheckin,Date newCheckout) {
        jdbcTemplate.update(UPDATE_Date,newCheckin,newCheckout,user_name);
        return "Date changed successfully";
    }

    @Override
    public String changeRoom(String user_name, String room_id) {
        jdbcTemplate.update(CHANGE_ROOM,room_id,user_name);
        return "Room changed successfully";
    }

    @Override
    public ReservationInfo getReserve(String user_name) {
        return jdbcTemplate.queryForObject(SELECT, new Object[]{user_name}, new RowMapper<ReservationInfo>() {
            @Override
            public ReservationInfo mapRow(ResultSet rs, int i) throws SQLException {
               ReservationInfo reservation=new ReservationInfo();
               reservation.setId(rs.getInt("id"));
               reservation.setCheckin(rs.getDate("checkin"));
               reservation.setCheckout(rs.getDate("checkin"));
               reservation.setRoom_id(rs.getString("room_id"));
               reservation.setUser_Name(rs.getString("username"));
                return reservation;
            }
        });

    }

    @Override
    public ArrayList<ReservationInfo> getRoomReservations(String room_id) {

        return (ArrayList<ReservationInfo>) jdbcTemplate.query(GET_ROOM_RESERVATION, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setString(1,room_id);
            }
        }, new RowMapper<ReservationInfo>() {
            @Override
            public ReservationInfo mapRow(ResultSet rs, int i) throws SQLException {
                ReservationInfo reservation = new ReservationInfo();
                reservation.setCheckin(rs.getDate("checkin"));
                reservation.setCheckout(rs.getDate("checkin"));
                reservation.setRoom_id(rs.getString("room_id"));
                reservation.setUser_Name(rs.getString("username"));
                return reservation;
            }
        });

    }

    @Override
    public void moveToHistory(String userName) {

        ReservationInfo reserve=getReserve(userName);
        jdbcTemplate.update(INSERT_RESERVE_HISTORY,reserve.getUser_Name(),reserve.getRoom_id(),reserve.getCheckin()
                ,reserve.getCheckout(),new Date());

        deleteReserve(userName);
    }
}
