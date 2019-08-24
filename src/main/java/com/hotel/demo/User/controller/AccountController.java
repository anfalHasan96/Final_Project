package com.hotel.demo.User.controller;

import com.hotel.demo.AuthenticationHandler;
import com.hotel.demo.User.UserDao;
import com.hotel.demo.User.UserInfo;
import com.hotel.demo.User.ValidUserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AuthenticationHandler authenticationHandler;

    @Autowired
    private UserDao userDao;

    @Autowired
    ValidUserInfo validUserInfo;

    @RequestMapping("/viewLogin")
    public String viewRegistration(){
        return "login";
    }


    @PostMapping("/login")
    @ResponseBody
    public String login( HttpServletRequest request,@RequestParam String userName, @RequestParam String password){

       request= authenticationHandler.checkAuthinticate(request,userName,password);

       if( request.getAttribute("auth")==null){
           return "Invalid userName or password";
       }
       else {
           return "logged in successfully";
       }
    }

    @RequestMapping("/viewSignUp")
    public String viewSignUp(Model model) {
        model.addAttribute("newUser",new UserInfo());
        return "signUp";
    }

    @PostMapping(value = "/signUp",produces = { MediaType.APPLICATION_JSON_VALUE },consumes = { MediaType.APPLICATION_JSON_VALUE} )
    @ResponseBody
    public  String signUp(@RequestBody UserInfo userInfo){

        String validUser=validUserInfo.isValid(userInfo);
        if(!validUser.equalsIgnoreCase("valid")){
         return validUser;
        }

        userInfo.setJoin_date(new Date());
        userDao.setRole(userInfo.getUserName(),"customer_role");
       return userDao.createUser(userInfo);
    }

    @DeleteMapping("/delete")
    @ResponseBody
    public  String viewDelete(HttpServletRequest request) {
        if(!authenticationHandler.checkLogin(request)){
            return "You have to login first";
        }
        String userName= (String) request.getSession(false).getAttribute("userName");
        return userDao.deleteUser(userName);
    }


    @RequestMapping("/viewChangePassword")
    public  String viewChangePassword( HttpServletRequest request) {
        if(!authenticationHandler.checkLogin(request)){
            return "You have to login first";
        }
        return "changePassword";
    }

    @PostMapping("/changePassword")
    public String changePassword(HttpServletRequest request,@RequestParam String newPassword) {

        if(! newPassword.matches("^[A-Za-z0-9\\*]{8,15}$")){
            return "Not valid password. Password be at least 8 at most 15 of the following" +
                    " uppercase lowercase letters, digits or * ";
        }

        HttpSession session=request.getSession(false);
        String userName=(String)session.getAttribute("userName");
        return userDao.changePassword(userName,newPassword);
    }

    @PostMapping("/logout")
    public void logout(HttpServletRequest request) {
        HttpSession session=request.getSession();
        session.removeAttribute("auth");
        session.invalidate();
    }


    }
