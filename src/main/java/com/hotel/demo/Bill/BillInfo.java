package com.hotel.demo.Bill;

import java.util.Date;

public class BillInfo {
    private Integer id;
    private String userName;
    private String service_type;
    private Double service_cost;
    private Date service_date;
    private String bill_status;


    public String getBill_status() {
        return bill_status;
    }

    public void setBill_status(String bill_status) {
        this.bill_status = bill_status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String  getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getService_type() {
        return service_type;
    }

    public void setService_type(String service_type) {
        this.service_type = service_type;
    }

    public Double getService_cost() {
        return service_cost;
    }

    public void setService_cost(Double service_cost) {
        this.service_cost = service_cost;
    }

    public Date getService_date() {
        return service_date;
    }

    public void setService_date(Date service_date) {
        this.service_date = service_date;
    }
}
