package com.appointmentProject.backend.controller;

import com.appointmentProject.backend.model.Room;
import com.appointmentProject.backend.service.RoomService;
import com.appointmentProject.backend.exception.RecordNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
@CrossOrigin(origins = "*")
public class RoomController {

    @Autowired
    private RoomService roomService;

    //GET all
    @GetMapping
    public ResponseEntity<List<Room>> getAllRooms() {
        return ResponseEntity.ok(roomService.getAllRooms());
    }

    //GET one
    @GetMapping("/{roomNumber}")
    public ResponseEntity<?> getRoom(@PathVariable String roomNumber) {
        try {
            return ResponseEntity.ok(roomService.getRoom(roomNumber));
        } catch (RecordNotFoundException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    //POST create
    @PostMapping
    public ResponseEntity<?> addRoom(@RequestBody Room room) {
        try {
            return ResponseEntity.status(201).body(roomService.addRoom(room));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    //PUT update
    @PutMapping("/{roomNumber}")
    public ResponseEntity<?> updateRoom(@PathVariable String roomNumber,
                                        @RequestBody Room roomUpdate) {
        try {
            return ResponseEntity.ok(roomService.updateRoom(roomNumber, roomUpdate));
        } catch (RecordNotFoundException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    //DELETE
    @DeleteMapping("/{roomNumber}")
    public ResponseEntity<?> deleteRoom(@PathVariable String roomNumber) {
        try {
            roomService.removeRoom(roomNumber);
            return ResponseEntity.ok("Deleted " + roomNumber);
        } catch (RecordNotFoundException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }
}
