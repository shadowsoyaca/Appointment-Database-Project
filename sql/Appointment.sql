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
CONSTRAINT fk_appointment_room FOREIGN KEY (room_number) REFERENCES Room(room_number),
CONSTRAINT fk_appointment_laborder FOREIGN KEY (lab_order_id) REFERENCES LabOrder(id)
);