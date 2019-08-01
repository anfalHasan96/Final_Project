package com.hotel.demo.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/hotel")
public class HomeController {

    @RequestMapping("/home")
    @ResponseBody
    public String viewHomePage(){
        return "home";
    }

    @RequestMapping("/register")
    @ResponseBody
    public String viewRegistration(){
        return "register";
    }

    @RequestMapping("/reserve")
    @ResponseBody
    public String viewReservation(){
        return "reservation";
    }


    @RequestMapping("/checkout")
    @ResponseBody
    public String viewCheckout(){
        return "checkout";
    }

}
