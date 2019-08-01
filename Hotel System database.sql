create database HOTEL;

create table ROOM(
IMAGE_REFERENCE varchar(255),
SPECIFICATIONS text,
SIZE varchar(63),
QUALITY varchar(63)
);

create table EMPLOYEE(
ID varchar(127) primary key,
FIRST_NAME varchar(31),
LAST_NAME varchar(31),
EMAIL varchar(127),
PHONE_NUMBER varchar(31),
WORKING_DEPARTMENT varchar(127),
WORKING_HOURS FLOAT,
SALARY float,
GENDER varchar(15),
HIRING_DATE date
);

create table SERVICE(
FREE_SERVICES varchar(127),
PAID_SERVICES varchar(127),
SERVICE_COST float 
);

create table ACCOUNT(
ID INT auto_increment primary KEY,
USER_NAME varchar(63),
PASSWORD varchar(63),
DATE datetime
);
create TABLE LOG_IN(
USER_NAME varchar(63),
PASSWORD varchar(63),
DATE datetime
);

create table CUSTOMER(
ID INT auto_increment primary key,
FIRST_NAME varchar(31),
LAST_NAME varchar(31),
EMAIL varchar(127),
PHONE_NUMBER varchar(31),
USER_NAME varchar(63),
PASSWORD varchar(63),
CREDIT_CARD_NUMBER varchar(127),
BOOKING_DATE datetime,
foreign key (USER_NAME,PASSWORD) references ACCOUNT(USER_NAME,PASSWORD)
on update cascade
on delete restrict  
);

create table CUSTOMER_BILL(
CUSTOMER_ID INT primary key,
SERVICE_TYPE varchar(127),
SERVICE_COST float,
SERVICE_DATE datetime,
TOTAL_BILL float,
BILL_STATUS varchar(15),
foreign key fk1(CUSTOMER_ID) references CUSTOMER(ID)
on update cascade
on delete restrict,
foreign key (SERVICE_COST) references SERVICE(SERVICE_COST)
on update cascade
on delete restrict 
);

