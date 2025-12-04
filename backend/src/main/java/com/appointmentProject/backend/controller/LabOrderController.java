package com.appointmentProject.backend.controller;

import com.appointmentProject.backend.exception.RecordNotFoundException;
import com.appointmentProject.backend.model.LabOrder;
import com.appointmentProject.backend.service.LabOrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/********************************************************************************************************
 * LabOrderController.java
 *
 *      This controller defines the API endpoints that the frontend (JavaFX client or others)
 *      will call for LabOrder CRUD operations.
 *
 * @author Matthew Kiyono
 * @version 1.0
 * @since 12/03/2025
 ********************************************************************************************************/
@RestController
@RequestMapping("/laborder")
@CrossOrigin(origins = "*")
public class LabOrderController {

    @Autowired
    LabOrderService labService;

    // CREATE
    @PostMapping("/add")
    public ResponseEntity<LabOrder> addLabOrder(@RequestBody LabOrder order) {
        return ResponseEntity.ok(labService.addLabOrder(order));
    }

    // GET ALL
    @GetMapping("/all")
    public ResponseEntity<List<LabOrder>> getAll() {
        return ResponseEntity.ok(labService.getAllLabOrders());
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<LabOrder> getById(@PathVariable int id) {
        return labService.getLabOrderById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() ->
                        new RecordNotFoundException("LabOrder with ID " + id + " was not found.")
                );
    }

    // GET BY APPOINTMENT
    @GetMapping("/appointment/{appointmentId}")
    public ResponseEntity<List<LabOrder>> getByAppointmentId(@PathVariable int appointmentId) {
        return ResponseEntity.ok(labService.getByAppointmentId(appointmentId));
    }

    // GET BY PATIENT
    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<LabOrder>> getByPatient(@PathVariable int patientId) {
        return ResponseEntity.ok(labService.getByPatientId(patientId));
    }

    // GET BY PROVIDER REQUESTER
    @GetMapping("/provider/requester/{providerId}")
    public ResponseEntity<List<LabOrder>> getByProviderRequester(@PathVariable int providerId) {
        return ResponseEntity.ok(labService.getByProviderRequesterId(providerId));
    }

    // GET BY PROVIDER RECEIVER
    @GetMapping("/provider/receiver/{providerId}")
    public ResponseEntity<List<LabOrder>> getByProviderReceiver(@PathVariable Integer providerId) {
        return ResponseEntity.ok(labService.getByProviderReceiverId(providerId));
    }

    // GET BY NURSE
    @GetMapping("/nurse/{nurseId}")
    public ResponseEntity<List<LabOrder>> getByNurse(@PathVariable Integer nurseId) {
        return ResponseEntity.ok(labService.getByNurseId(nurseId));
    }

    // GET BY TESTING PURPOSE
    @GetMapping("/purpose/{purpose}")
    public ResponseEntity<List<LabOrder>> getByPurpose(@PathVariable String purpose) {
        return ResponseEntity.ok(labService.getByTestingPurpose(purpose));
    }

    // UPDATE
    @PutMapping("/update")
    public ResponseEntity<LabOrder> updateLabOrder(@RequestBody LabOrder order) {
        return ResponseEntity.ok(labService.updateLabOrder(order));
    }

    // DELETE
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteLabOrder(@PathVariable int id) {
        return labService.getLabOrderById(id)
                .map(existing -> {
                    labService.removeLabOrder(existing);
                    return ResponseEntity.ok("LabOrder record removed successfully.");
                })
                .orElseThrow(() ->
                        new RecordNotFoundException("Cannot delete — ID " + id + " does not exist.")
                );
    }
}