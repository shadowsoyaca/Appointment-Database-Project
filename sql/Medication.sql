/*
Medication Rules:
-	id is unique  and 8 characters long.
-	None of the fields can be null.
*/
CREATE TABLE Medication (
id INT AUTO_INCREMENT PRIMARY KEY,
medication_name VARCHAR(255) NOT NULL,
manufacturer_id INT NOT NULL,
strength DOUBLE NOT NULL,
type_of_med VARCHAR(255) NOT NULL,
consumption VARCHAR(255) NOT NULL,
CONSTRAINT fk_med_manufacturer FOREIGN KEY (manufacturer_id) REFERENCES Manufacturer(id)
);