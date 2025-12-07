package com.appointmentProject.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

/******************************************************************************
 * Room.java
 *
 *     Represents a Room entity from the database in object
 *     format for transferring from the database to the frontend.
 *      - "roomNumber": the number assigned to the room (3–4 char length)
 *      - "floorNumber": the floor the room is on.
 *
 * @author Matthew Kiyono
 * @version 1.1
 * @since 11/4/2025
 ********************************************************************************/

@Entity
@Table(name = "room")
public class Room {

    @Id
    @NotNull
    @Column(name = "roomNumber", unique = true, nullable = false)
    private String roomNumber;

    @NotNull
    @Column(name = "floorNumber", nullable = false)
    private int floorNumber;

    protected Room() {}

    public Room(String roomNumber, int floorNumber) {

        if (roomNumber == null || roomNumber.length() < 3 || roomNumber.length() > 4) {
            throw new IllegalArgumentException("Room number must be 3 or 4 characters.");
        }

        int expectedFloor;

        if (roomNumber.length() == 3) {
            expectedFloor = Integer.parseInt(roomNumber.substring(0, 1));
        } else {
            expectedFloor = Integer.parseInt(roomNumber.substring(0, 2));
        }

        if (floorNumber != expectedFloor) {
            throw new IllegalArgumentException("Floor number must match the room number prefix.");
        }

        this.roomNumber = roomNumber;
        this.floorNumber = floorNumber;
    }

    public String getRoomNumber() { return roomNumber; }
    public int getFloorNumber() { return floorNumber; }

    public void setRoomNumber(String roomNumber) { this.roomNumber = roomNumber; }
    public void setFloorNumber(int floorNumber) { this.floorNumber = floorNumber; }

    @Override
    public String toString() {
        return "Room:" +
                "\nNumber: " + roomNumber +
                "\nFloor: " + floorNumber + "\n";
    }
}
