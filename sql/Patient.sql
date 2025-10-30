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