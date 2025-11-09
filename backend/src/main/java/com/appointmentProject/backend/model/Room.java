package com.appointmentProject.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

/******************************************************************************
 * Room.java
 *
 *     Represents a Room entity from the database in object
 *     format for transferring from the database to the frontend.
 *      - "room_number": the number that is assigned to the room (3-4 char length)
 *      - "floor_number": the floor the room is on.
 *
 *
 * @author Matthew Kiyono
 * @version 1.1
 * @since 11/4/2025
 ********************************************************************************/
@Entity
public class Room {

    //variables
    @Id
    private int room_number;
    private int floor_number;

    //constructor
    public Room(int room_number, int floor_number) {
        this.room_number = room_number;
        this.floor_number = floor_number;
    }

    //getter methods
    public int getRoom_number() {return room_number;}
    public int getFloor_number() {return floor_number;}

    //setter methods
    public void setRoom_number(int room_number) {this.room_number = room_number;}
    public void setFloor_number(int floor_number) {this.floor_number = floor_number;}

    //toString method
    @Override
    public String toString() {
        return "Room:" +
                "\nNumber: " +  room_number +
                "\nFloor: " + floor_number + "\n";
    }

}
