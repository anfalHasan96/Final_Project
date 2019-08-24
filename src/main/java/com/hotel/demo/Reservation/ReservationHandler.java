package com.hotel.demo.Reservation;


import org.springframework.beans.factory.annotation.Autowired;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;

public class ReservationHandler {

    @Autowired
    ReservationDao reservationDao;

    public Boolean checkDates(String room_id,Date checkin,Date checkout){
        ArrayList<ReservationInfo> room_reservation=reservationDao.getRoomReservations(room_id);
        Date recentDate=new Date();

        if((checkin.getMonth() < recentDate.getMonth()) ||(checkout.getMonth() < recentDate.getMonth()) ||
        (checkin.getDay() < recentDate.getDay()) || (checkout.getDay() < recentDate.getDay())){
            return false;
        }

        for(int i=0;i<room_reservation.size();i++){
            if(checkin.getMonth()==room_reservation.get(i).getCheckin().getMonth()){
                if( (checkin.getDay() >= room_reservation.get(i).getCheckin().getDay()) &&
                        (checkout.getDay()<= room_reservation.get(i).getCheckout().getDay())){
                    return false;
                }
            } }
        return true;
    }

    public HttpSession haveReserve(HttpServletRequest request){
        HttpSession session=request.getSession(false);

        ReservationInfo reservationInfo=reservationDao.getReserve((String)session.getAttribute("userName"));
        if(reservationInfo == null){
            return null;
        }
        else{
            session.setAttribute("room_id",reservationInfo.getRoom_id());
            session.setAttribute("checkin",reservationInfo.getCheckin());
            session.setAttribute("checkout",reservationInfo.getCheckout());
            return session;
        }
    }


    public Boolean isGuest(HttpSession session){

        Date checkin=(Date)session.getAttribute("checkin");
        Date checkout=(Date)session.getAttribute("checkout");
        Date presentDate=new Date();

        if(checkin.getMonth()==presentDate.getMonth()){
            if( (checkin.getDay() <= presentDate.getDay()) &&
                    (checkout.getDay()>= presentDate.getDay())){
                return true;
            }}

            return false;
            }
}
