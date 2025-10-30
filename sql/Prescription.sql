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
CREATE TABLE Prescription (
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