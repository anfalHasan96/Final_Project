package com.hotel.demo.User;

import java.util.ArrayList;
import java.util.List;

public interface UserDao {
 String createUser(UserInfo user);
 String deleteUser(String UserName);
 UserInfo getUser(String UserName);
 ArrayList<UserInfo> getAllCustomer();
 String changePassword(String userName,String newPassword);
 void setRole(String userName,String role);
 String getRole(String userName);
 Integer getUserId(String UserName);
}
