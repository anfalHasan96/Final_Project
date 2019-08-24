package com.hotel.demo.HotelService.Service;

import java.util.ArrayList;

public interface ServiceDao {
    String addService(ServiceInfo serviceInfo);
    String deleteService(Integer id);
    String updateServiceCost(Integer id,Double newPrice);
    ServiceInfo getService(Integer id);
    Integer getServiceId();
    ArrayList<ServiceInfo> getAllServices();
}
