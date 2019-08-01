package com.hotel.demo.Employee;

import java.util.Date;

public class EmployeeInfo {
    String id;
    String first_name;
    String last_name;
    String gender;
    Date hiring_date;
    String email;
    String phoneNumber;
    String working_department;
    Double salary;
    Double Working_hours;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getHiring_date() {
        return hiring_date;
    }

    public void setHiring_date(Date hiring_date) {
        this.hiring_date = hiring_date;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getWorking_department() {
        return working_department;
    }

    public void setWorking_department(String working_department) {
        this.working_department = working_department;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Double getWorking_hours() {
        return Working_hours;
    }

    public void setWorking_hours(Double working_hours) {
        Working_hours = working_hours;
    }
}
