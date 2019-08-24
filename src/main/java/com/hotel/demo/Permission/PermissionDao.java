package com.hotel.demo.Permission;

import java.util.ArrayList;

public interface PermissionDao {

    ArrayList<String> getAllPermission(String userRole);
    void addPermission(PermissionInfo permissionInfo);
    void deletePermission(String permission);
}
