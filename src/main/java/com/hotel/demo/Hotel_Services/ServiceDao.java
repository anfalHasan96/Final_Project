package com.hotel.demo.Hotel_Services;

import java.util.ArrayList;

public interface ServiceDao {

    String addService(ServiceInfo service);

    String deleteService(String service_name);

    String updateServiceCost(String service_name);

    ArrayList<ServiceInfo> selectServices();
}
