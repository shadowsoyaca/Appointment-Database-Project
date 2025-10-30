/*
Rules:
-	The ONLY field that can be NULL is insurance_id.
-	id is unique and can only be 8 characters long.
-	cost must be greater than 0
*/
CREATE TABLE Billing (
id INT AUTO_INCREMENT PRIMARY KEY,
insurance_id INT,
cost DECIMAL(10,2) NOT NULL CHECK (cost > 0),
status_of_payment VARCHAR(50) NOT NULL,
payment_type VARCHAR(50) NOT NULL,
CONSTRAINT fk_billing_insurance FOREIGN KEY (insurance_id) REFERENCES Insurance(id)
);