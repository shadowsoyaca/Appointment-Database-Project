/*
UserAccount Rules:
-	username and email are unique
-	None of the fields can be null
*/
CREATE TABLE UserAccount (
username VARCHAR(255) PRIMARY KEY UNIQUE,
password VARCHAR(255) NOT NULL,
email VARCHAR(255) NOT NULL UNIQUE,
user_type VARCHAR(50) NOT NULL
);