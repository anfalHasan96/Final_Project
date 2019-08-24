package com.hotel.demo;

import com.hotel.demo.Bill.BillDao;
import com.hotel.demo.Bill.BillDaoImpl;
import com.hotel.demo.HotelService.Service.ServiceDao;
import com.hotel.demo.HotelService.Service.ServiceDaoImpl;
import com.hotel.demo.HotelService.restaurantService.menu.MenuDao;
import com.hotel.demo.HotelService.restaurantService.menu.MenuDaoImp;
import com.hotel.demo.HotelService.restaurantService.order.OrderBillDao;
import com.hotel.demo.HotelService.restaurantService.order.OrderBillDaoImpl;
import com.hotel.demo.HotelService.restaurantService.order.OrderDao;
import com.hotel.demo.HotelService.restaurantService.order.OrderDaoImpl;
import com.hotel.demo.Permission.PermissionDao;
import com.hotel.demo.Permission.PermissionDaoImp;
import com.hotel.demo.Permission.PermissionHandler;
import com.hotel.demo.Reservation.ReservationHandler;
import com.hotel.demo.Reservation.ReservationDao;
import com.hotel.demo.Reservation.ReservationDaoImp;
import com.hotel.demo.Room.RoomDao;
import com.hotel.demo.Room.RoomDaoImp;
import com.hotel.demo.User.UserDao;
import com.hotel.demo.User.UserDaoImp;
import com.hotel.demo.User.ValidUserInfo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication extends SpringBootServletInitializer {

    @Bean
    public RoomDao roomDao(){
        return new RoomDaoImp();
    }

    @Bean
    public UserDao userDao(){
        return new UserDaoImp();
    }

    @Bean
    public ReservationDao reservationDao(){
        return new ReservationDaoImp();
    }

    @Bean
    public AuthenticationHandler authenticationHandler(){
        return new AuthenticationHandler();
    }

    @Bean
    public PermissionDao permissionDao(){
        return new PermissionDaoImp();
    }

    @Bean
    public PermissionHandler permissionHandler(){
        return new PermissionHandler();
    }

    @Bean
    public ReservationHandler checkReservation(){return new ReservationHandler();}

    @Bean
    public BillDao billDao(){return new BillDaoImpl();
    }
    @Bean
    public ServiceDao serviceDao(){return new ServiceDaoImpl();
    }

    @Bean
    public MenuDao menuDao(){return new MenuDaoImp();
    }

    @Bean
    public OrderDao ordeDao(){return new OrderDaoImpl();
    }

    @Bean
    public OrderBillDao ordeBillDao(){return new OrderBillDaoImpl();
    }
    @Bean
    public ValidUserInfo validUserInfo() {
        return new ValidUserInfo();
    }


        @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(DemoApplication.class);
    }


    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}
