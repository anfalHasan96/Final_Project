package com.hotel.demo.checkout.controller;

import com.hotel.demo.AuthenticationHandler;
import com.hotel.demo.Bill.BillDao;
import com.hotel.demo.Bill.BillInfo;
import com.hotel.demo.Bill.BillStatusInfo;
import com.hotel.demo.HotelService.restaurantService.order.OrderBillDao;
import com.hotel.demo.HotelService.restaurantService.order.OrderBillInfo;
import com.hotel.demo.HotelService.restaurantService.order.OrderDao;
import com.hotel.demo.Reservation.ReservationDao;
import com.hotel.demo.Reservation.ReservationHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;

@Controller
@RequestMapping("/checkout")
public class CheckoutController {
    @Autowired
    BillDao billDao;
    @Autowired
    AuthenticationHandler authenticationHandler;
    @Autowired
    ReservationDao reservationDao;
    @Autowired
    OrderBillDao orderBillDao;
    @Autowired
    OrderDao orderDao;
    @Autowired
    ReservationHandler checkReservation;


    @RequestMapping("/viewCheckout")
    public String viewCheckout(Model model, HttpServletRequest request)
    {
        if(!authenticationHandler.checkLogin(request)) {
            return "register";
        }
        HttpSession session=request.getSession(false);
        String userName=(String) session.getAttribute("userName");
        ArrayList<BillInfo> billInfo= billDao.getBill(userName);
        model.addAttribute("bill",billInfo);
        return "checkout";
    }

    @GetMapping(value = "/submit")
    @ResponseBody
    public String checkoutSubmit(HttpServletRequest request){

        HttpSession checkSession=checkReservation.haveReserve(request);

        if(checkSession==null){
            return "There are no reservations for "+request.getAttribute("firstName")
                    +" "+request.getAttribute("lastName");
        }

        if(!checkReservation.isGuest(checkSession)){
            return "You are not a guest in this hotel";
        }

        HttpSession session=request.getSession(false);
        String userName=(String) session.getAttribute("userName");

        ArrayList<BillInfo> servicesBill= billDao.getBill(userName);
        Double totalBill=0.0;
        for (BillInfo service : servicesBill){
            totalBill+=service.getService_cost();
        }

        BillStatusInfo bill=new BillStatusInfo();
        bill.setUserName(userName);
        bill.setTotalBill(totalBill);
        bill.setBillStatus("paid");
        bill.setCheckoutDate(new Date());
        billDao.setBillStatusInfo(bill);

        orderDao.deleteAllOrder(userName);

        OrderBillInfo orderBill=orderBillDao.getOrderBill(userName);
        orderBillDao.setNotValidBill(userName,orderBill.getOrderDate());

        reservationDao.moveToHistory(userName);
        billDao.setBilNotValid(userName);

        return "Your bill has been paid. Thank you for using our hotel visit us again!";
    }




}
