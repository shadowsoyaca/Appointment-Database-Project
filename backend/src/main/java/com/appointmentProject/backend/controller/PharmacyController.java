package com.appointmentProject.backend.controller;

import com.appointmentProject.backend.exception.RecordNotFoundException;
import com.appointmentProject.backend.model.Pharmacy;
import com.appointmentProject.backend.service.PharmacyService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/******************************************************************************************
 * PharmacyController.java
 *
 *      REST endpoints for Pharmacy operations.
 *
 ******************************************************************************************/
@RestController
@RequestMapping("/pharmacy")
@CrossOrigin(origins = "*")
public class PharmacyController {

    @Autowired
    PharmacyService pharmacyService;

    @PostMapping("/add")
    public ResponseEntity<Pharmacy> addPharmacy(@RequestBody Pharmacy pharmacy) {
        return ResponseEntity.ok(pharmacyService.addPharmacy(pharmacy));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Pharmacy>> getAllPharmacies() {
        return ResponseEntity.ok(pharmacyService.getAllPharmacies());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pharmacy> getById(@PathVariable int id) {
        Optional<Pharmacy> result = pharmacyService.getPharmacyById(id);
        if (result.isEmpty()) {
            throw new RecordNotFoundException("Pharmacy with ID " + id + " not found.");
        }
        return ResponseEntity.ok(result.get());
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<Pharmacy>> getByName(@PathVariable String name) {
        return ResponseEntity.ok(pharmacyService.getByName(name));
    }

    @PutMapping("/update")
    public ResponseEntity<Pharmacy> updatePharmacy(@RequestBody Pharmacy pharmacy) {
        return ResponseEntity.ok(pharmacyService.updatePharmacy(pharmacy));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletePharmacy(@PathVariable int id) {
        Optional<Pharmacy> p = pharmacyService.getPharmacyById(id);
        if (p.isEmpty()) {
            throw new RecordNotFoundException("Cannot delete — ID " + id + " does not exist.");
        }
        pharmacyService.removePharmacy(p.get());
        return ResponseEntity.ok("Pharmacy deleted successfully.");
    }
}
