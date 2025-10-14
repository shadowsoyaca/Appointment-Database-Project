CREATE DATABASE IF NOT EXISTS project;

use project;

/*
Emergency Contact Rules:
- id is unique. It cannot be greater than 8 characters long.
- first_name and last_name are a unique combination.
- ONLY email and address can be null.
*/
CREATE TABLE IF NOT EXISTS EmergencyContact (
id INT AUTO_INCREMENT PRIMARY KEY,
first_name VARCHAR(100) NOT NULL,
last_name VARCHAR(100) NOT NULL,
phone BIGINT NOT NULL,
email VARCHAR(255),
address VARCHAR(255),
CONSTRAINT uq_name UNIQUE (first_name, last_name)
-- CONSTRAINT check_id CHECK (id <= 99999999). check_id cant refer to auto incremented column.
);

/*
Insurance Rules: 
EVERY field is unique. 
Id is 8 characters long ***SEE CONSTRAINT IN EMERGENCY CONTACT TABLE***
None of the fields can be null. 
*/
CREATE TABLE Insurance (
id INT AUTO_INCREMENT PRIMARY KEY,
insurance_name VARCHAR(255) NOT NULL UNIQUE,
phone BIGINT NOT NULL UNIQUE,
email VARCHAR(255) NOT NULL UNIQUE,
address VARCHAR(255) NOT NULL UNIQUE
);

/*
Manufacturer Rules: 
EVERY field is unique 
Id is 8 characters long  
None of the fields can be null 
*/
CREATE TABLE Manufacturer(
id INT AUTO_INCREMENT PRIMARY KEY,
manufacturer_name VARCHAR(255) NOT NULL UNIQUE,
phone BIGINT NOT NULL UNIQUE,
email VARCHAR(255) NOT NULL UNIQUE,
address VARCHAR(255) NOT NULL UNIQUE
);

/*
Room Rules:
-	if room_number’s length is 3, then the floor_number must match the first character of room_number.
-	if room_number’s length is 4, then the floor_number must match the first two characters of room_number.
*/
CREATE TABLE Room (
room_number VARCHAR(4) PRIMARY KEY,
floor_number VARCHAR(2) NOT NULL
);

/*
Provider Rules:
-	id is unique and cannot be more than 8 characters long.
-	first_name and last_name are a unique combination.
-	None of the fields can be null.
*/
CREATE TABLE Provider (
id INT AUTO_INCREMENT PRIMARY KEY,
first_name VARCHAR(255) NOT NULL,
last_name VARCHAR(255) NOT NULL,
phone BIGINT NOT NULL,
email VARCHAR(255) NOT NULL,
specialty VARCHAR(255) NOT NULL,
address VARCHAR(255) NOT NULL,
availability VARCHAR(255) NOT NULL,
CONSTRAINT uq_provider_name UNIQUE (first_name, last_name)
);

/*
Nurse Rules:
-	id is unique and cannot be more than 8 characters long.
-	first_name and last_name are a  unique combination.
-	None of the fields can be null.
*/
CREATE TABLE Nurse (
id INT AUTO_INCREMENT PRIMARY KEY,
first_name VARCHAR(255) NOT NULL,
last_name VARCHAR(255) NOT NULL,
phone BIGINT NOT NULL,
email VARCHAR(255) NOT NULL,
address VARCHAR(255) NOT NULL,
CONSTRAINT uq_nurse_name UNIQUE (first_name, last_name)
);

/*
Account Rules:
-	username and email are unique
-	None of the fields can be null
*/
CREATE TABLE Account(
username VARCHAR(255) PRIMARY KEY UNIQUE,
password VARCHAR(255) NOT NULL,
email VARCHAR(255) NOT NULL UNIQUE,
user_type VARCHAR(50) NOT NULL
);

/*
Patient Rules: 
id is unique. It cannot be greater than 8 characters 
first_name and last_name are a unique combination. 
age cannot be less than 0 
weight and height must be greater than 0 
only gender, email, insurance_id, and emergency_contact_id can be null 
*/

CREATE TABLE Patient (
id INT AUTO_INCREMENT PRIMARY KEY,
first_name VARCHAR(100) NOT NULL,
last_name VARCHAR(100) NOT NULL,
DoB DATE NOT NULL,
gender VARCHAR(25),
age INT NOT NULL CHECK (age >= 0),
weight DOUBLE NOT NULL CHECK (weight > 0),
height DOUBLE NOT NULL CHECK (height > 0),
phone BIGINT NOT NULL,
email VARCHAR(255),
insurance_id INT, 
emergency_contact_id INT,
CONSTRAINT uq_patient_name UNIQUE(first_name, last_name),
CONSTRAINT fk_patient_insurance FOREIGN KEY (insurance_id) REFERENCES Insurance(id),
CONSTRAINT fk_patient_emergency FOREIGN KEY (emergency_contact_id) REFERENCES EmergencyContact(id)
);

/*
Pharmacy Rules:
-	EVERY field EXCEPT opening_time & closing_time are unique.
-	Id is 8 characters long
-	None of the fields can be null
*/
CREATE TABLE Pharmacy (
id INT AUTO_INCREMENT PRIMARY KEY,
address VARCHAR(255) NOT NULL UNIQUE,
opening_time TIME,
closing_time TIME,
phone BIGINT NOT NULL UNIQUE,
email VARCHAR(255) NOT NULL UNIQUE
);

/*
Medication Rules:
-	id is unique  and 8 characters long.
-	None of the fields can be null.
*/
CREATE TABLE Medication(
id INT AUTO_INCREMENT PRIMARY KEY,
medication_name VARCHAR(255) NOT NULL,
manufacturer_id INT NOT NULL,
strength DOUBLE NOT NULL,
type_of_med VARCHAR(255) NOT NULL,
consumption VARCHAR(255) NOT NULL,
CONSTRAINT fk_med_manufacturer FOREIGN KEY (manufacturer_id) REFERENCES Manufacturer(id)
);
/*
Rules:
-	ONLY insurance_id, end_date, and date_picked_up can be null.
-	Id is unique and can only be 8 characters long.
-	End_date cannot be earlier than start_date
-	Cost, quantity, and frequency must be greater than 0
-	date_picked_up defaults as NULL. (meaning they haven’t picked it up yet) 
-	date_picked_up (if not null) cannot be earlier than start_date
-	date_picked_up (if not null) cannot be later than end_date

*/
CREATE TABLE PRESCRIPTION (
id INT AUTO_INCREMENT PRIMARY KEY,
medication_id INT NOT NULL,
pharmacy_id INT NOT NULL,
insurance_id INT,
quantity DOUBLE NOT NULL CHECK (quantity > 0),
frequency INT NOT NULL CHECK (frequency > 0),
start_date DATE NOT NULL,
end_date DATE,
cost DECIMAL(10,2) NOT NULL CHECK (cost > 0),
_status VARCHAR(50) NOT NULL,
date_picked_up DATETIME DEFAULT NULL,
CONSTRAINT fk_prescription_medication FOREIGN KEY (medication_id) REFERENCES Medication(id),
CONSTRAINT fk_prescription_pharmacy FOREIGN KEY (pharmacy_id) REFERENCES Pharmacy(id),
CONSTRAINT fk_prescription_insurance FOREIGN KEY (insurance_id) REFERENCES Insurance(id)
);

/*
Rules:
-	The ONLY field that can be NULL is insurance_id.
-	id is unique and can only be 8 characters long.
-	cost must be greater than 0
*/
CREATE TABLE Billing(
id INT AUTO_INCREMENT PRIMARY KEY,
insurance_id INT,
cost DECIMAL(10,2) NOT NULL CHECK (cost > 0),
status_of_payment VARCHAR(50) NOT NULL,
payment_type VARCHAR(50) NOT NULL,
CONSTRAINT fk_billing_insurance FOREIGN KEY (insurance_id) REFERENCES Insurance(id)
);

/*
Appointment Rules:
-	ONLY nurse_id, prescription_id, lab_order_id, start_time, and end_time can be NULL.
-	id is unique and  can only be 8 characters long.
-	start_time (if not null) cannot be earlier than appointment_date.
-	start_time (if not null )  cannot be later than  end_time (if not null)
-	end_time (if not null)  cannot be earlier than appointment_date OR start_time (if not null).
-	provider_id and appointment_date are a unique combination
-	this is to prevent providers from being scheduled to see two patients at the same time
-	room_number and appointment_date are a unique combination
-	this is to prevent two doctors and two patients from being scheduled in the same room. 
-	appointment_length can only be 30 minutes, 45 minutes, or an hour (60 minutes).
*/
CREATE TABLE Appointment (
id INT AUTO_INCREMENT PRIMARY KEY,
patient_id INT NOT NULL,
provider_id INT NOT NULL,
nurse_id INT,
prescription_id INT,
billing_id INT,
lab_order_id INT, -- keep the column but no FK yet
appointment_date DATETIME NOT NULL,
appointment_length ENUM('30','45','60') NOT NULL,
room_number VARCHAR(4),
start_time TIME,
end_time TIME,
reason_for_visiting VARCHAR(255) NOT NULL,
CONSTRAINT uq_provider_appointment UNIQUE (provider_id, appointment_date),
CONSTRAINT uq_room_appointment UNIQUE (room_number, appointment_date),
CONSTRAINT fk_appointment_patient FOREIGN KEY (patient_id) REFERENCES Patient(id),
CONSTRAINT fk_appointment_provider FOREIGN KEY (provider_id) REFERENCES Provider(id),
CONSTRAINT fk_appointment_nurse FOREIGN KEY (nurse_id) REFERENCES Nurse(id),
CONSTRAINT fk_appointment_prescription FOREIGN KEY (prescription_id) REFERENCES Prescription(id),
CONSTRAINT fk_appointment_billing FOREIGN KEY (billing_id) REFERENCES Billing(id),
CONSTRAINT fk_appointment_room FOREIGN KEY (room_number) REFERENCES Room(room_number)
);

/*
Lab Order Rules:
-	ONLY nurse_id, date_of_completion, and provider_receiver_id can be NULL.
-	id is unique and can only be 8 characters long.
-	date_of_completion cannot be earlier than the appointment table’s appointment_date field.
-	price must be greater than 0 and must have a format of only two decimals.
-	results defaults as 0 (false).
*/
CREATE TABLE LabOrder (
id INT AUTO_INCREMENT PRIMARY KEY,
appointment_id INT NOT NULL,
provider_requester_id INT NOT NULL,
provider_receiver_id INT,
nurse_id INT,
patient_id INT NOT NULL,
date_of_completion DATETIME,
testing_purpose VARCHAR(255) NOT NULL,
results BOOLEAN DEFAULT 0,
price DECIMAL(10,2) NOT NULL CHECK (price > 0),
CONSTRAINT fk_laborder_appointment FOREIGN KEY (appointment_id) REFERENCES Appointment(id),
CONSTRAINT fk_laborder_provider_req FOREIGN KEY (provider_requester_id) REFERENCES Provider(id),
CONSTRAINT fk_laborder_provider_rec FOREIGN KEY (provider_receiver_id) REFERENCES Provider(id),
CONSTRAINT fk_laborder_nurse FOREIGN KEY (nurse_id) REFERENCES Nurse(id),
CONSTRAINT fk_laborder_patient FOREIGN KEY (patient_id) REFERENCES Patient(id)
);
/*
This allows Appointment table to be created because it doesnt require Lab Order table to exist.(the reference)
*/
ALTER TABLE Appointment
ADD CONSTRAINT fk_appointment_laborder
FOREIGN KEY (lab_order_id) REFERENCES LabOrder(id);

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








