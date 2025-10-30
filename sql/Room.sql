/*
Room Rules:
-	if room_number’s length is 3, then the floor_number must match the first character of room_number.
-	if room_number’s length is 4, then the floor_number must match the first two characters of room_number.
*/
CREATE TABLE Room (
room_number VARCHAR(4) PRIMARY KEY,
floor_number VARCHAR(2) NOT NULL
);