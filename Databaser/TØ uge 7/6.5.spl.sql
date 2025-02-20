CREATE SCHEMA `car-rental`;

CREATE TABLE CUSTOMER (
	Cust_no INT	NOT NULL,	
    Cname 	VARCHAR(30) NOT NULL,
    City 	VARCHAR(30) NOt NULL,
	PRIMARY KEY (Cust_no)
);

CREATE TABLE RENTAL(
Rental_no 	int NOT NULL, 
Rdate 	VARCHAR(30), 
Cust_no INT, 
Time 	TIME, 
Date 	DATE, 
Hourly_rate VARCHAR(30) NOT NULL,
PRIMARY KEY (Rental_no),
FOREIGN KEY (Cust_no) REFERENCES CUSTOMER(Cust_no) ON DELETE SET NULL ON UPDATE CASCADE
);

CREATE TABLE RENTAL_CAR (
Rental_no 		INT NOT NULL, 
Car_no			INT 	PRIMARY KEY, 
Driver_no 		INT, 
Start_time 		TIME, 
End_time 		TIME, 
Amount_received VARCHAR(30),
FOREIGN KEY (Rental_no) REFERENCES RENTAL(Rental_no)
);

CREATE TABLE CAR (
Car_no 			INT, 
Year 			YEAR, 
Model 			VARCHAR(30), 
Price 			INT, 
Depreciation	INT, 
Last_service 	DATE,
FOREIGN KEY (Car_no) REFERENCES RENTAL_CAR(Car_no)
);

CREATE TABLE SERVICING (
Garager 		INT, 
Car 			INT, 
Service_date 	DATE
);

# a)
ALTER TABLE CAR ADD COLUMN Color VARCHAR(50); 

# b)
ALTER TABLE RENTAL_CAR DROP COLUMN Amount_received; 

# c)
ALTER TABLE RENTAL MODIFY COLUMN Hourly_rate DECIMAL(10, 2);

# d)
ALTER TABLE SERVICING ADD FOREIGN KEY (Car) REFERENCES CAR(Car_no);




