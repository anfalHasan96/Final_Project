package com.hotel.demo.Bill;

import java.util.ArrayList;

public interface BillDao {
    void addElement(BillInfo billInfo);
    void deleteElement(Integer id);
    void setBilNotValid(String userName);
    ArrayList<BillInfo> getBill(String userName);
    String getBillStatus(String userName );
    void setBillStatusInfo(BillStatusInfo bill);
    BillStatusInfo getBillStatusInfo(String userName);


}
