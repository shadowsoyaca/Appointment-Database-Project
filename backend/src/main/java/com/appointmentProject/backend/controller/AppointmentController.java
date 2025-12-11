package com.appointmentProject.backend.controller;

import com.appointmentProject.backend.model.Appointment;
import com.appointmentProject.backend.service.AppointmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    private final AppointmentService service;

    public AppointmentController(AppointmentService service) {
        this.service = service;
    }

    @GetMapping("/all")
    public List<Appointment> getAll() {
        return service.getAllAppointments();
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody Appointment a) {
        try {
            Appointment created = service.create(a);
            return ResponseEntity.ok(created);
        } catch (IllegalArgumentException ex) {
            // Body format: "FIELD_KEY: message"
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
}
