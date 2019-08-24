package com.hotel.demo.Reservation;

import java.util.ArrayList;
import java.util.Date;

public interface ReservationDao {
    String reserveRoom(ReservationInfo reserve);
    String deleteReserve(String user_name);
    String updateReservationDate(String user_name, Date newCheckin,Date newCheckout);
    String changeRoom(String user_name, String room_id);
    ReservationInfo getReserve(String user_name);
    ArrayList<ReservationInfo> getRoomReservations(String room_id);
    void moveToHistory(String userName);

}
