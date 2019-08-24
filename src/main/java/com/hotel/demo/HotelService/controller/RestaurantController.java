package com.hotel.demo.HotelService.controller;

import com.hotel.demo.AuthenticationHandler;
import com.hotel.demo.Bill.BillDao;
import com.hotel.demo.Bill.BillInfo;
import com.hotel.demo.HotelService.Service.ServiceDao;
import com.hotel.demo.HotelService.Service.ServiceInfo;
import com.hotel.demo.HotelService.restaurantService.menu.MenuDao;
import com.hotel.demo.HotelService.restaurantService.menu.MenuInfo;
import com.hotel.demo.HotelService.restaurantService.order.OrderBillDao;
import com.hotel.demo.HotelService.restaurantService.order.OrderBillInfo;
import com.hotel.demo.HotelService.restaurantService.order.OrderDao;
import com.hotel.demo.HotelService.restaurantService.order.OrderInfo;
import com.hotel.demo.Permission.PermissionHandler;
import com.hotel.demo.Reservation.ReservationDao;
import com.hotel.demo.Reservation.ReservationHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
@RequestMapping("/restaurant")
public class RestaurantController {

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
    MenuDao menuDao;

    @Autowired
    OrderDao orderDao;

    @Autowired
    OrderBillDao orderBillDao;

    @RequestMapping("/view")
    public String viewMenu( Model model){

        ArrayList<MenuInfo> menuList= menuDao.getMenu();

        model.addAttribute("menuList",menuList);
        model.addAttribute("orderInfo",new OrderInfo());

        return "restaurant";
    }

    @PostMapping(value = "/addOrder",produces = { MediaType.APPLICATION_JSON_VALUE },consumes = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public String requestService(HttpServletRequest request, @RequestBody OrderInfo order) {
        if(!permissionHandler.checkPermission(request,"restaurant_service")){
            return  "Sorry this service is not allowed for you !";
        }
        HttpSession session=checkReservation.haveReserve(request);

        if(session==null){
            return "There are no reservations for "+request.getAttribute("firstName")
                    +" "+request.getAttribute("lastName");
        }

        if(!checkReservation.isGuest(session)){
            return "You are not a guest in this hotel";
        }

        if(order.getMenu_id()==null){
            return "You didn't select any order. Please select one of the menu items" ;
        }

        if(order.getItemQuantity()==null){
            return "You need to select quantity for this order" ;
        }

        HttpSession session2=request.getSession(false);
        order.setUserName((String) session2.getAttribute("userName"));

        order.setItem_cost(menuDao.getItemPrice(order.getMenu_id()));
        order.setItemTotalPrice(order.getItem_cost()* order.getItemQuantity());
        orderDao.addOrder(order);
        return "order added to your order";
    }

    @GetMapping(value = "/viewOrder",produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public ArrayList<OrderInfo> viewOrder(HttpServletRequest request){
        HttpSession session=request.getSession(false);
        String userName=(String) session.getAttribute("userName");
        ArrayList<OrderInfo> orderList=orderDao.getAllOrder(userName);

        return orderList;
    }

    @GetMapping(value = "/submitOrder",produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public OrderBillInfo submitOrder(HttpServletRequest request){
        HttpSession session=request.getSession(false);
        String userName=(String) session.getAttribute("userName");

        ArrayList<OrderInfo> orderList=orderDao.getAllOrder(userName);
        Double totalBill=0.0;
        for(OrderInfo order : orderList){
            totalBill+=order.getItemTotalPrice();
        }

        OrderBillInfo orderBillInfo=new OrderBillInfo();
        orderBillInfo.setTotalBill(totalBill);
        orderBillInfo.setUserName(userName);
        orderBillInfo.setOrderDate(new Date());
        orderBillInfo.setBillStatus("valid");
        orderBillDao.addOrderBill(orderBillInfo);

        OrderBillInfo orderBill=orderBillDao.getOrderBill(userName);

        BillInfo billInfo=new BillInfo();
        billInfo.setUserName(userName);
        billInfo.setService_cost(totalBill);
        billInfo.setService_type("Restaurant service");
        billInfo.setService_date(orderBillInfo.getOrderDate());
        billInfo.setBill_status("valid");
        billDao.addElement(billInfo);

        return orderBill;
    }


    @DeleteMapping(value = "/deleteItem/{order_id}",produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public void deleteOredrItem(@PathVariable Integer order_id){
        orderDao.deleteOrderItem(order_id);
    }

    @RequestMapping(value = "/updateQuantity/{order_id}",consumes = MediaType.APPLICATION_JSON_VALUE
            ,method =RequestMethod.POST )
    @ResponseBody
    public void updateQuantity(@PathVariable Integer order_id,@RequestBody Integer newQuantity){
        orderDao.updateQuantity(order_id,newQuantity);
    }


}
