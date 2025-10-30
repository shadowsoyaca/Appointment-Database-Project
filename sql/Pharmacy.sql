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