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