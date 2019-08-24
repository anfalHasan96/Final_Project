package com.hotel.demo.Room;

import java.util.ArrayList;
import java.util.Date;

public interface RoomDao {
    void deleteRoom(String room_id);
    void addRoom(RoomInfo room);
    void updateRoom(RoomInfo room);
    RoomInfo getRoom(String room_id);
    ArrayList<RoomInfo> getAllRoom();
}
