package com.hotel.demo.User;

import org.springframework.beans.factory.annotation.Autowired;

public class ValidUserInfo {

    @Autowired
    private UserDao userDao;

    public String isValid(UserInfo user){
         StringBuilder status=new StringBuilder(null);

        if(user.getUserName()==null){
            status.append("UserName cannot be empty\n");
        }
        else {
            UserInfo userInfo=userDao.getUser(user.getUserName());
            if(userInfo !=null){
             return "This userName already exist. Please change your userName";
            }
        }

        if(user.getPassword()==null){
            status.append("Password cannot be empty\n");
        }
        else if(!user.getPassword().matches("^[A-Za-z0-9\\*]{8,15}$")){
            status.append("Not valid password. Password be at least 8 at most 15 of the following" +
                    " uppercase lowercase letters, digits or * \n");
        }

            if(user.getFirst_name()==null){
                status.append("First name cannot be empty\n");
            }

            if(user.getMid_name()==null){
            status.append("Mid name cannot be empty\n");
            }

        if(user.getLast_name()==null){
            status.append("Last name cannot be empty\n");
        }


        if(user.getEmail()==null) {
            status.append("email address cannot be empty\n");
        }
        else if(!user.getEmail().matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-\\+]+(\\.[A-Za-z]{2,})$")){
            status.append("Not valid email address\n");
        }

        if(user.getSsn()==null){
            status.append("ssn cannot be empty\n");
        }

        if(user.getPhoneNumber()==null){
            status.append("Phone number cannot be empty\n");
        }
        if(status.equals(null)){
            status=new StringBuilder("valid");
        }

        return status.toString();
    }
}
