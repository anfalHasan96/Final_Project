create database HOTEL;

create table ROOM_DETAILS(
ID varchar(32) primary KEY,
PRICE float,
SPECIFICATIONS text,
SIZE varchar(63),
QUALITY varchar(63)
);

create table ROOM(
ID varchar(32) primary KEY,
PRICE float,
foreign key FK1(ID) references ROOM_DETAILS(ID)
on update cascade
on delete restrict 
);
create table USER(
ID INT auto_increment primary key,
FIRST_NAME varchar(31),
MID_NAME varchar(31),
LAST_NAME varchar(31),
EMAIL varchar(127),
SSN varchar(127),
PHONE_NUMBER varchar(31),
USERNAME varchar(63) unique,
PASSWORD varchar(63),
JOINING_DATE datetime
);

create table USER_ROLE(
USER_ID int,
ROLE varchar(63) primary key,
foreign key FK1(USER_ID) references USER(ID)
on update cascade
on delete restrict
);
create table PERMISSION(
ID INT auto_increment primary key,
USER_ROLE varchar(63) ,
PERMISSION_TYPE varchar(127)
);

CREATE table DELETED_USER(
ID INT primary key,
FIRST_NAME varchar(31),
MID_NAME varchar(31),
LAST_NAME varchar(31),
EMAIL varchar(127),
SSN varchar(127),
PHONE_NUMBER varchar(31),
USER_NAME varchar(63) unique,
PASSWORD varchar(63),
JOINING_DATE datetime
);
create table HOTEL_SERVICE(
ID INT auto_increment primary key,
SERVICE_TYPE varchar(127),
SERVICE_COST float
);
create table RESTAURANT_MENU(
ID INT auto_increment primary key,
ITEM varchar(127),
ITEM_COST float
);

create table RESTAURANT_ORDER(
ID INT auto_increment primary key,
USER_NAME varchar(63) ,
ITEM_ID int,
PRICE_PER_UNIT FLOAT,
ITEM_QUANTITY int,
ITEM_TOTAL_PRICE float,
foreign key FK1(USER_NAME) references USER(USERNAME)
on update cascade
on delete restrict
);

create table ORDER_BILL(
OREDR_ID INT auto_increment primary key,
USER_NAME varchar(63) ,
TOTAL_BILL FLOAT,
order_date datetime,
BIIL_STATUS varchar(16), 
foreign key (USER_NAME) references RESTAURANT_ORDER(USER_NAME)
on update cascade
on delete restrict
);


create table CUSTOMER_BILL(
ID INT auto_increment,
USER_NAME varchar(63) ,
SERVICE_TYPE varchar(127),
SERVICE_COST float,
SERVICE_DATE datetime,
BILL_STATUS varchar(16),
primary key(ID,USER_NAME), 
foreign key FK1(USER_NAME) references USER(USERNAME)
on update cascade
on delete restrict
);
create table BILL_STATUS(
BILL_ID INT auto_increment primary key,
USER_NAME varchar(63) ,
TOTAL_BILL float,
STATUS varchar(15),
CHECKOUT_DATE datetime,
foreign key FK1(USER_NAME) references CUSTOMER_BILL(USER_NAME)
on update cascade
on delete restrict
);

CREATE TABLE RESERVATION(
ID INT auto_increment primary key,
USERNAME varchar(63),
ROOM_ID varchar(32), 
CHECKIN date,
CHECKOUT date
);
CREATE TABLE RESERVATION_HISTORY(
ID INT auto_increment primary key,
USERNAME varchar(63),
ROOM_ID varchar(32), 
CHECKIN date,
CHECKOUT date,
ACTUALL_CHECKOUT datetime
);
