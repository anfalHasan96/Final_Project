package com.hotel.demo.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import java.util.ArrayList;


public class UserDaoImp implements UserDao {

     private final String CREATE_ACCOUNT="insert into user(first_name,mid_name,last_name,email,ssn,phone_number," +
             "username,password,joining_date) values(?,?,?,?,?,?,?,?,?)";
     private final String DELETE_ACCOUNT="delete from user where username=?";

     private final String GET="select * from user where username=?";

     private final String CHANGE_PASSWORD="update user set password=? where username=?";
     private final String GET_ALL="select* from user where " +
             "user.id=user_role.user_id and user_role.role='customer_role' order by id";

    private final String SET_ROLE="insert into user_role(user_id,role) values(?,?)";
    private final String GET_USER_ROLE="select role from user_role where user_id=? ";
    private final String GET_ID="select id from user where username=?)";

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public String createUser(UserInfo user) {
        jdbcTemplate.update(CREATE_ACCOUNT,user.getFirst_name(),user.getMid_name(),user.getLast_name(),
                user.getEmail(),user.getSsn(),user.getPhoneNumber(),
                user.getUserName(),user.getPassword(),user.getJoin_date());

        return "Account created successfully";
    }

    @Override
    public String deleteUser(String userName) {

        UserInfo deletedUser= getUser(userName);
        jdbcTemplate.update("insert into deleted_user(first_name,mid_name,last_name,email,ssn,phone_number," +
                "username,password,joining_date) values(?,?,?,?,?,?,?,?,?,?)" , deletedUser.getId(),
                deletedUser.getFirst_name(),deletedUser.getMid_name(),deletedUser.getLast_name(),
                deletedUser.getEmail(),deletedUser.getSsn(),deletedUser.getPhoneNumber(),
                deletedUser.getUserName(),deletedUser.getPassword(),deletedUser.getJoin_date());

        jdbcTemplate.update(DELETE_ACCOUNT,userName);
        return "Account deleted successfully";
    }

    @Override
    public UserInfo getUser(String userName) {

       return jdbcTemplate.queryForObject(GET, new Object[]{userName},new UserRowMapper());
    }

    @Override
    public ArrayList<UserInfo> getAllCustomer() {

        return  (ArrayList<UserInfo>) jdbcTemplate.query(GET_ALL, new UserRowMapper());
    }

    @Override
    public String changePassword(String userName, String newPassword) {
        jdbcTemplate.update(CHANGE_PASSWORD,newPassword,userName);
        return "Password changed";
    }

    @Override
    public void setRole(String userName,String role)
    {
        int user_id= getUserId(userName);
        jdbcTemplate.update(SET_ROLE,user_id,role);
    }

    @Override
    public String getRole(String userName) {
        int user_id= getUserId(userName);
        return jdbcTemplate.queryForObject(GET_USER_ROLE,new Object[]{user_id},String.class);
    }

    @Override
    public Integer getUserId(String userName) {
        return  jdbcTemplate.queryForObject(GET_ID,  new Object[] { userName }, Integer.class);

    }
}
