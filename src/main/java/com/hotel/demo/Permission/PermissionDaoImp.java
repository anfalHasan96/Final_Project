package com.hotel.demo.Permission;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PermissionDaoImp implements PermissionDao{
    private final String GET_PERMISSIN="SELECT PERMISSION_TYPE FROM PERMISSION WHERE USER_ROLE=?";
    private final String DELETE_PERMISSIN="DELETE FROM PERMISSION WHERE PERMISSION_TYPE=?";
    private final String ADD_PERMISSIN="INSERT INTO PERMISSION(USER_ROLE,PERMISSION_TYPE) " +
            "VALUES(?,?)";
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public ArrayList<String> getAllPermission(String userRole) {
        return (ArrayList) jdbcTemplate.query(GET_PERMISSIN, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setString(1, userRole);
            }
        }, new RowMapper<PermissionInfo>() {
            @Override
            public PermissionInfo mapRow(ResultSet resultSet, int i) throws SQLException {
                PermissionInfo permission=new PermissionInfo();
                permission.setUserRole(resultSet.getString("user_role"));
                permission.setPermission(resultSet.getString("permission_type"));
                return permission;
            }
        });

    }

    @Override
    public void addPermission(PermissionInfo permissionInfo) {
        jdbcTemplate.update(ADD_PERMISSIN,permissionInfo.getUserRole(),permissionInfo.getPermission());

    }

    @Override
    public void deletePermission(String permission) {
        jdbcTemplate.update(DELETE_PERMISSIN,permission);

    }
}
