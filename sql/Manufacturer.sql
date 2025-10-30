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