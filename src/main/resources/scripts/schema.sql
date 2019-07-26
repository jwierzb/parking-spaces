DROP TABLE CURRENCY IF EXISTS;
DROP TABLE DRIVER_TYPE IF EXISTS ;
DROP TABLE ROLE IF EXISTS ;
DROP TABLE TRANSACTION IF EXISTS ;
DROP TABLE USER IF EXISTS ;
DROP TABLE USER_ROLES IF EXISTS ;
DROP TABLE VEHICLE IF EXISTS ;

CREATE TABLE CURRENCY(
ID INT,
EXCHANGE_RATE DOUBLE,
LAST_UPDATE TIMESTAMP,
NAME VARCHAR(32),
PRIMARY KEY(ID)
);

CREATE TABLE DRIVER_TYPE(
ID INT,
NAME VARCHAR(32) UNIQUE,
FIRST_HOUR_PRICE DOUBLE,
SECOND_HOUR_PRICE DOUBLE,
NEXT_HOURS_MULTIPILER DOUBLE,
PRIMARY KEY(ID)
);

CREATE TABLE ROLE(
ID INT NOT NULL,
NAME VARCHAR(32) NOT NULL,
PRIMARY KEY(ID)
);

CREATE TABLE USER(
ID BIGINT NOT NULL,
EMAIL VARCHAR(64) NOT NULL,
ENABLED INTEGER NOT NULL DEFAULT TRUE,
FIRST_NAME VARCHAR(32) NOT NULL,
LAST_NAME VARCHAR(32) NOT NULL,
USER_NAME VARCHAR(32) NOT NULL,
NON_LOCKED INT NOT NULL DEFAULT 0,
PASSWORD VARCHAR(64) NOT NULL,
PHONE_NUMBER INT NOT NULL,
CURRENCY_ID INT NOT NULL,
DRIVER_TYPE_ID INT NOT NULL,
CREATED_DATE TIMESTAMP NOT NULL,
PRIMARY KEY(ID),
FOREIGN KEY(CURRENCY_ID) REFERENCES CURRENCY(ID),
FOREIGN KEY(DRIVER_TYPE_ID) REFERENCES DRIVER_TYPE(ID)
);

CREATE TABLE USER_ROLES(
USER_ID BIGINT NOT NULL,
ROLE_ID INTEGER NOT NULL,
FOREIGN KEY(USER_ID) REFERENCES USER(ID),
FOREIGN KEY(ROLE_ID) REFERENCES ROLE(ID)
);

CREATE TABLE VEHICLE(
ID BIGINT NOT NULL,
REGISTRATION_NUMBER VARCHAR(10) NOT NULL UNIQUE,
USER_ID BIGINT NOT NULL,
PRIMARY KEY(ID),
FOREIGN KEY(USER_ID) REFERENCES USER(ID)
);

CREATE TABLE TRANSACTION(
ID BIGINT,
START_TIME TIMESTAMP NOT NULL,
END_TIME TIMESTAMP,
PRICE DECIMAL,
VEHICLE_ID BIGINT NOT NULL,
PAYED INTEGER NOT NULL DEFAULT 0,
USER_ID BIGINT NOT NULL,
PRIMARY KEY(ID),
FOREIGN KEY(USER_ID) REFERENCES USER(ID),
FOREIGN KEY(VEHICLE_ID) REFERENCES VEHICLE(ID)
);

CREATE SEQUENCE IF NOT EXISTS user_id_sequence
START WITH 100000
INCREMENT BY 1
MINVALUE 1
MAXVALUE 100000000
NOCYCLE;

CREATE SEQUENCE IF NOT EXISTS transaction_id_sequence
START WITH 1000
INCREMENT BY 1
MINVALUE 1000
MAXVALUE 10000000000
NOCYCLE;

CREATE SEQUENCE IF NOT EXISTS vehicle_id_sequence
START WITH 1000
INCREMENT BY 1
MINVALUE 1000
MAXVALUE 10000000000
NOCYCLE;