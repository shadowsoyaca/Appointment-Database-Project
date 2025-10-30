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