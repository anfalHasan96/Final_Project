package com.hotel.demo.Customer;

import java.util.ArrayList;

public interface AccountDao {
    String update_userName(String userName);
    String update_password(String password);
    ArrayList<Account> get_customer_accounts(String userName);
    ArrayList<Account> get_allAccounts();
}
