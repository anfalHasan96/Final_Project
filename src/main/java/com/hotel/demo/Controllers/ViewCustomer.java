package com.hotel.demo.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/hotel/viewCustomer")
public class ViewCustomer {

    @RequestMapping("/createAccount")
    public String createAccount(){
        return "crateAccount";
    }
}
