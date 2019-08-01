package com.hotel.demo.Employee;

import java.util.ArrayList;

public interface EmployeeDao {
    String addEmployee(EmployeeInfo employee);

    String deleteEmployee(String id);

    String updateEmployee(String id);

    EmployeeInfo getEmployee_byId(String id);

    ArrayList<EmployeeInfo> getEmployee_byName(String firstName);

}
