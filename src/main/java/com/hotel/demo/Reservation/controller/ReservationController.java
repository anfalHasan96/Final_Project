package com.hotel.demo.Reservation.controller;

import com.hotel.demo.AuthenticationHandler;
import com.hotel.demo.Permission.PermissionHandler;
import com.hotel.demo.Reservation.ReservationHandler;
import com.hotel.demo.Reservation.ReservationDao;
import com.hotel.demo.Reservation.ReservationInfo;
import com.hotel.demo.Room.RoomDao;
import com.hotel.demo.Room.RoomInfo;
import com.hotel.demo.User.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
@RequestMapping("/reservation")
public class ReservationController {


    @Autowired
    AuthenticationHandler authenticationHandler;

    @Autowired
    PermissionHandler permissionHandler;

    @Autowired
    ReservationDao reservationDao;

    @Autowired
    UserDao userDao;

    @Autowired
    ReservationHandler checkReservation;
    @Autowired
    RoomDao roomDao;

    @GetMapping("/showReservation")
    @ResponseBody
    public ArrayList<String> showRoomResrvations(@RequestParam String room_id){

        ArrayList<ReservationInfo> reservations=reservationDao.getRoomReservations(room_id);
        ArrayList<String> checkin_checkout=new ArrayList<>();
        for(int i=0;i<reservations.size();i++){

        checkin_checkout.add(reservations.get(i).getCheckin()+"  -  "+reservations.get(i).getCheckout());
        }
        return checkin_checkout;
    }

    @RequestMapping("/viewReservation")
    public String viewReserve(HttpServletRequest request) {
        if(!authenticationHandler.checkLogin(request)) {
            return "register";
        }

        return "reservation";
    }

    @RequestMapping("/viewReserveRoom")
    public String viewReserveRoom(Model model)
    {

        ArrayList<RoomInfo> roomInfoList= roomDao.getAllRoom();
        Map<String,String> roomList=new HashMap<>();

        for(RoomInfo room : roomInfoList){
            roomList.put(room.getId(),room.getId()+"  "+room.getPrice()+" $");
        }

        model.addAttribute("roomList",roomList);
        model.addAttribute("reservationInfo",new ReservationInfo());

        return "reserveRoom";
    }


    @PostMapping(value = "/reserveRoom",produces = { MediaType.APPLICATION_JSON_VALUE },consumes = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public String reserveRoom(HttpServletRequest request,
                              @RequestBody ReservationInfo reservationInfo){


        if(!permissionHandler.checkPermission(request,"reserve_room")){

            return  "Sorry this service is not allowed for you !";
        }

        if(!checkReservation.checkDates(reservationInfo.getRoom_id(),reservationInfo.getCheckin(),
                reservationInfo.getCheckout())){
            return "The room is reserved in this date or the date you entered is not correct.\nTHe following list shows" +
                    "reservations for this room \n"+
                    showRoomResrvations(reservationInfo.getRoom_id());
        }

        return reservationDao.reserveRoom(reservationInfo);
    }

    @RequestMapping("/viewDeleteReserve")
    public String viewDeleteReserve(Model model,HttpServletRequest request)
    {
        HttpSession session=request.getSession(false);
        String userName=(String) session.getAttribute("userName");

        ReservationInfo reserve=reservationDao.getReserve(userName);
        String showreserve;

        if(reserve==null){
            showreserve="There are no reservations for "+session.getAttribute("firstName")+" "+session.getAttribute("lastName");
        }
        else {
            showreserve="You have a reservation for room "+reserve.getRoom_id()+" reserved from "+reserve.getCheckin()
                    +" to "+ reserve.getCheckout()+". If you want to delete this reserve click on delete button";
        }
        model.addAttribute("showReserve",showreserve);
        return "deleteReserve";
    }

    @DeleteMapping(value = "/deleteReserve",produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public String deleteReserve(HttpServletRequest request){
        HttpSession session1=request.getSession(false);

        if(!permissionHandler.checkPermission(request,"delete room")){
            return  "Sorry this service is not allowed for you !";
        }
        HttpSession session=checkReservation.haveReserve(request);

        if(session==null){
            return "There are no reservations for "+session1.getAttribute("firstName")+" "+session1.getAttribute("lastName");
        }

        return reservationDao.deleteReserve((String)session1.getAttribute("userName"));
    }


        @RequestMapping("/viewChangeReserveDate")
    public String viewUpdateReserve() {
        return "changeReserveDate";
    }

    @PostMapping(value = "/changeReserveDate",consumes = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public String updateReserve(HttpServletRequest request,@RequestParam Date newCheckin,
                                @RequestParam Date newCheckout){

        if(!permissionHandler.checkPermission(request,"change_reservation")){
            return  "Sorry this service is not allowed for you !";
        }

        HttpSession session=checkReservation.haveReserve(request);

        if(session==null){
            return "There are no reservations for "+request.getAttribute("firstName")+" "+request.getAttribute("lastName");
        }

        if(!checkReservation.checkDates((String) session.getAttribute("room_id"),newCheckin,newCheckout)){
            return "The room is reserved in this date or the date you entered is not correct.\nTHe following list shows" +
                    "reservations for this room \n"+
                    showRoomResrvations((String) session.getAttribute("room_id"));
        }

        return reservationDao.updateReservationDate((String)request.getSession(false)
                .getAttribute("userName"),newCheckin,newCheckout);
    }


        @RequestMapping("/viewChangeRoom")
    public String viewChangeRoom(Model model)
        {
            ArrayList<RoomInfo> roomInfoList= roomDao.getAllRoom();
            Map<String,String> roomList=new LinkedHashMap<>();

            for(RoomInfo room : roomInfoList){
                roomList.put(room.getId(),room.getId()+"  "+room.getPrice()+" $");
            }

            model.addAttribute("roomList",roomList);
            return "changeRoom";
    }



    @GetMapping(value = "/changeRoom/{room_id}",produces = { MediaType.APPLICATION_JSON_VALUE })
    public String changeRoom(HttpServletRequest request,@PathVariable String room_id) {

        if (!permissionHandler.checkPermission(request, "change_room")) {
            return "Sorry this service is not allowed for you !";
        }

        HttpSession session = checkReservation.haveReserve(request);

        if (session == null) {
            return "There are no reservations for " + request.getAttribute("firstName") + " " + request.getAttribute("lastName");
        }
        if(!checkReservation.checkDates(room_id,(Date) session.getAttribute("checkin"),(Date) session.getAttribute("checkout"))){
            return "The room is reserved in this date or the date you entered is not correct.\nTHe following list shows" +
                    "reservations for this room \n"+
                    showRoomResrvations(room_id);
        }
        return reservationDao.changeRoom((String)request.getSession(false).getAttribute("userName"),room_id);

    }
}