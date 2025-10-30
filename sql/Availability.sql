/*
Availability Rules: 
-	The forementioned primary key must be unique. 
-	NONE of the variables can be Null.
-	Start_time cannot be less than 00:00
-	End_time cannot be more than 23:59
-	Start_time cannot be later than end_time.

*/
CREATE TABLE Availability (
staff_id INT NOT NULL,
staff_type ENUM('Provider','Nurse') NOT NULL,
day_of_week ENUM('Mon','Tue','Wed','Thu','Fri','Sat','Sun') NOT NULL,
start_time TIME NOT NULL,
end_time TIME NOT NULL,
PRIMARY KEY (staff_id, staff_type, day_of_week),
CONSTRAINT ck_availability_time CHECK (start_time <= end_time)
);