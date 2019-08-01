package com.hotel.demo.Customer;

import java.util.ArrayList;

public interface CustomerDao {
 String createCustomer(CustomerInfo customer);
 String deleteCustomer(String UserName);
 CustomerInfo getCustomer(String UserName);
 ArrayList<CustomerInfo> getAllCustomer();
}
