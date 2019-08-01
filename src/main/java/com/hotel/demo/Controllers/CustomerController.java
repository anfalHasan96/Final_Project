package com.hotel.demo.Controllers;

import com.hotel.demo.Customer.CustomerInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/hotel/customer")
public class CustomerController {

    @PostMapping("/createCustomer")
    public String createCustomer(@RequestParam String firstName,@RequestParam String lastName,
                                 @RequestParam String phoneNumber,@RequestParam String email,
                                 @RequestParam String userName,@RequestParam String password){
    CustomerInfo customer=new CustomerInfo();
    customer.setFirst_name(firstName);
    customer.setLast_name(lastName);
    customer.setPhoneNumber(phoneNumber);
    customer.setUserName(userName);
    customer.setPassword(password);
    customer.setJoin_date(new Date());
   return "Account created successfully";
    }


}
