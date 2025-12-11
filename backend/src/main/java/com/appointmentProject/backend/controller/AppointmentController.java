package com.appointmentProject.backend.controller;

import com.appointmentProject.backend.model.Appointment;
import com.appointmentProject.backend.service.AppointmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointment")
@CrossOrigin(origins = "*")
public class AppointmentController {

    private final AppointmentService service;

    public AppointmentController(AppointmentService service) {
        this.service = service;
    }

    // ---------- READ ----------

    @GetMapping("/all")
    public ResponseEntity<List<Appointment>> getAll() {
        return ResponseEntity.ok(service.getAllAppointments());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable int id) {
        return service.getAppointmentById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ---------- CREATE ----------

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody Appointment a) {
        try {
            Appointment created = service.create(a);
            return ResponseEntity.ok(created);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    // ---------- UPDATE ----------

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody Appointment a) {
        try {
            Appointment updated = service.update(a);
            return ResponseEntity.ok(updated);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    // ---------- DELETE ----------

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        try {
            service.delete(id);
            return ResponseEntity.ok("Appointment deleted.");
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
}
