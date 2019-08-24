package com.hotel.demo.HotelService.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ServiceDaoImpl implements ServiceDao{


    private final String ADD="insert into hotel_service(service_type,service_cost)" +
            "values(?,?)";
    private final String DELETE="delete from hotel_service where id=?";
    private final String UPDATE_PRICE="update hotel_service set service_cost=? where id=?";
    private final String GET_SERVICE="select * from hotel_service where id=?";
    private final String GET_ALL="select * from hotel_service";
    private final String GET_ID="select count(*) from hotel_service";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public String addService(ServiceInfo serviceInfo) {

        jdbcTemplate.update(ADD,serviceInfo.getServiceType(),serviceInfo.getServiceCost());
        return "Service added successfully";
    }

    @Override
    public String deleteService(Integer id) {
        jdbcTemplate.update(DELETE,id);
        return "Deleted Successfully";
    }

    @Override
    public String updateServiceCost(Integer id,Double newPrice) {
        jdbcTemplate.update(UPDATE_PRICE,newPrice,id);
        return "Updated successfully";
    }

    @Override
    public ServiceInfo getService(Integer id) {
        return jdbcTemplate.queryForObject(GET_SERVICE, new Object[]{id}, new RowMapper<ServiceInfo>() {
            @Override
            public ServiceInfo mapRow(ResultSet resultSet, int i) throws SQLException {
                ServiceInfo serviceInfo=new ServiceInfo();
                serviceInfo.setId(resultSet.getInt("id"));
                serviceInfo.setServiceType(resultSet.getString("service_type"));
                serviceInfo.setServiceCost(resultSet.getDouble("service_cost"));
                return serviceInfo;
            }
        });
    }

    @Override
    public Integer getServiceId() {
        return jdbcTemplate.queryForObject(GET_ID,Integer.class);

    }

    @Override
    public ArrayList<ServiceInfo> getAllServices() {
        return (ArrayList<ServiceInfo>) jdbcTemplate.query(GET_ALL,  new RowMapper<ServiceInfo>() {
            @Override
            public ServiceInfo mapRow(ResultSet resultSet, int i) throws SQLException {
               ServiceInfo serviceInfo=new ServiceInfo();
                serviceInfo.setId(resultSet.getInt("id"));
                serviceInfo.setServiceType(resultSet.getString("service_type"));
                serviceInfo.setServiceCost(resultSet.getDouble("service_cost"));
                return serviceInfo;
            }
        });

    }
}
