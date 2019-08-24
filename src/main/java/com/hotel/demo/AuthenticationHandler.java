package com.hotel.demo;

import com.hotel.demo.User.UserDao;
import com.hotel.demo.User.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class AuthenticationHandler {

    @Autowired
    UserDao userDao;

    public Boolean checkLogin(HttpServletRequest request){
        HttpSession session=request.getSession(false);
        if(session==null || session.getAttribute("auth")==null ){
            return false;
        }
        else{
            return true;}
    }

    public HttpServletRequest checkAuthinticate(HttpServletRequest request, String userName, String password){

        Integer counter;
        HttpSession session=request.getSession(false);


        if(session==null ) {

                counter=new Integer(0);
                session=request.getSession(true);
                session.setAttribute("login_counter", counter);
            }

        if((Boolean) session.getAttribute("auth")==true){
            return request;
        }

        else if(!request.isRequestedSessionIdValid()){
            session.setAttribute("blocked","true");
            return request;
        }

        else {
            counter=(Integer) session.getAttribute("login_counter");
        }


        UserInfo user= userDao.getUser(userName);

            if(! userName.equals(user.getUserName()) || !password.equals(user.getPassword()) ){
            counter++;
            session.setAttribute("login_counter",counter);

                if(counter>=3){
                    session.invalidate();
                }
            }

            else {
                session.setAttribute("auth","true");
                session.setAttribute("user_id",user.getId());
                session.setAttribute("userName",userName);
                session.setAttribute("firstName",user.getFirst_name());
                session.setAttribute("lastName",user.getLast_name());
            }
        return request;
    }
}
