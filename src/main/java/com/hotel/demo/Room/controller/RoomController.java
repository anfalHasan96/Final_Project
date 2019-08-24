package com.hotel.demo.Room.controller;

import com.hotel.demo.Room.RoomDao;
import com.hotel.demo.Room.RoomInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Controller
@RequestMapping("/room")
public class RoomController {

    @Autowired
    RoomDao roomDao;

    @RequestMapping("/viewRoom")
    public String viewReservation(Model model)
    {
        ArrayList<RoomInfo> roomInfoList= roomDao.getAllRoom();
        Map<String,String> roomList=new LinkedHashMap<>();

        for(RoomInfo room : roomInfoList){
            roomList.put(room.getId(),room.getId()+"  "+room.getPrice()+" $");
        }

        model.addAttribute("roomList",roomList);
        model.addAttribute("roomInfo",new RoomInfo());

        return "room";
    }

    @GetMapping(value = "/details/{room_id}",produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public RoomInfo viewReservationDetails(@PathVariable String room_id){

        return roomDao.getRoom(room_id);
    }


}
