package com.hotel.demo.HotelService.controller;

import com.hotel.demo.AuthenticationHandler;
import com.hotel.demo.Bill.BillDao;
import com.hotel.demo.Bill.BillInfo;
import com.hotel.demo.HotelService.Service.ServiceDao;
import com.hotel.demo.HotelService.Service.ServiceInfo;
import com.hotel.demo.Permission.PermissionHandler;
import com.hotel.demo.Reservation.ReservationHandler;
import com.hotel.demo.Reservation.ReservationDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
@RequestMapping("/service")
public class ServiceController {

    @Autowired
    AuthenticationHandler authenticationHandler;
    @Autowired
    PermissionHandler permissionHandler;
    @Autowired
    ReservationDao reservationDao;
    @Autowired
    ReservationHandler checkReservation;
    @Autowired
    BillDao billDao;
    @Autowired
    ServiceDao serviceDao;

    @RequestMapping("/view")
    public String viewService(HttpServletRequest request, Model model){
        if(!authenticationHandler.checkLogin(request)) {
            return "register";
        }
        ArrayList<ServiceInfo> serviceInfoList=serviceDao.getAllServices();
        Map<Integer,String> serviceList=new LinkedHashMap<>();

        for(ServiceInfo service : serviceInfoList){
         serviceList.put(service.getId(),service.getServiceType());
        }

        model.addAttribute("serviceList",serviceList);

        return "service";
    }

    @GetMapping(value = "/request/{service_id}",produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public String requestService(HttpServletRequest request,@PathVariable Integer service_id) {
        if(!permissionHandler.checkPermission(request,"hotel_service")){
            return  "Sorry this service is not allowed for you !";
        }
        HttpSession session=checkReservation.haveReserve(request);

        if(session==null){
            return "There are no reservations for "+request.getAttribute("firstName")+" "+request.getAttribute("lastName");
        }

        if(!checkReservation.isGuest(session)){
            return "You are not a guest in this hotel for this day";
        }

        if(service_id==null){
            return "You didn't select any service. Please select one" ;
        }
        BillInfo billInfo=new BillInfo();

            ServiceInfo serviceInfo = serviceDao.getService(service_id);
            billInfo.setService_cost(serviceInfo.getServiceCost());
            billInfo.setService_type(serviceInfo.getServiceType());
            billInfo.setService_date(new Date());
            billInfo.setBill_status("valid");
            billDao.addElement(billInfo);

        return "Your request is sent";
    }

    }
