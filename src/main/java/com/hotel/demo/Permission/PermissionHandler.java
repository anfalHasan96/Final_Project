package com.hotel.demo.Permission;

import com.hotel.demo.User.UserDao;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

public class PermissionHandler {

    @Autowired
    PermissionDao permissionDao;

    @Autowired
    UserDao userDao;

    public Boolean checkPermission(HttpServletRequest request,String service_type){

        HttpSession session=request.getSession(false);
        String user_Role= userDao.getRole((String) session.getAttribute("userName"));
        ArrayList<String> user_permission= permissionDao.getAllPermission(user_Role);

        if(!user_permission.contains(service_type)){
            return false;
        }

        return true;

        }
}
