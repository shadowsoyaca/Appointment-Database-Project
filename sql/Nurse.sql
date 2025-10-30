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