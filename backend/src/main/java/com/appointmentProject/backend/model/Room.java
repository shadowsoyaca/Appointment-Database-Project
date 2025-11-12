package com.appointmentProject.backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.antlr.v4.runtime.misc.NotNull;

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
@Table(name = "room")
public class Room {

    //variables
    @Id
    @NotNull
    @Column(name = "room_number", unique = true, nullable = false)
    private String room_number;

    @NotNull
    @Column(name = "floor_number", nullable = false)
    private int floor_number;

    //Test Constructor Only!
    protected Room() {}

    //constructor
    public Room(String room_number, int floor_number) {
        this.room_number = room_number;
        this.floor_number = floor_number;
    }

    //getter methods
    public String getRoom_number() {return room_number;}
    public int getFloor_number() {return floor_number;}

    //setter methods
    public void setRoom_number(String room_number) {this.room_number = room_number;}
    public void setFloor_number(int floor_number) {this.floor_number = floor_number;}

    //toString method
    @Override
    public String toString() {
        return "Room:" +
                "\nNumber: " +  room_number +
                "\nFloor: " + floor_number + "\n";
    }

}
