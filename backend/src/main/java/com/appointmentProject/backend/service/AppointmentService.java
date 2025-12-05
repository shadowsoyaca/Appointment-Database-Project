package com.appointmentProject.backend.service;

import com.appointmentProject.backend.exception.RecordNotFoundException;
import com.appointmentProject.backend.model.Appointment;
import com.appointmentProject.backend.repository.AppointmentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**********************************************************************************************
 * AppointmentService.java
 *
 * Service layer for handling business logic and database communication related to
 * Appointment records.
 *
 * Supports:
 *      - Insert (create)
 *      - Delete (remove)
 *      - Update (modify existing appointment data)
 *      - Select (retrieve all, by ID, by provider, by patient, etc.)
 *
 * @version 1.0
 * @since 12/04/2025
 * @author Alexis Patino
 **********************************************************************************************/

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepo;

    // CREATE
    public Appointment addAppointment(Appointment appt) {
        return appointmentRepo.save(appt);
    }

    // DELETE
    public void removeAppointment(int id) {
        appointmentRepo.deleteById(id);
    }

    // UPDATE
    public Appointment updateAppointment(Appointment update) {
        Appointment existing = appointmentRepo.findById(update.getId())
                .orElseThrow(() ->
                        new RecordNotFoundException("Appointment with ID " + update.getId() + " was not found.")
                );

        existing.setPatientId(update.getPatientId());
        existing.setProviderId(update.getProviderId());
        existing.setNurseId(update.getNurseId());
        existing.setPrescriptionId(update.getPrescriptionId());
        existing.setBillingId(update.getBillingId());
        existing.setLabOrderId(update.getLabOrderId());
        existing.setAppointmentDate(update.getAppointmentDate());
        existing.setAppointmentLength(update.getAppointmentLength());
        existing.setRoomNumber(update.getRoomNumber());
        existing.setStartTime(update.getStartTime());
        existing.setEndTime(update.getEndTime());
        existing.setReasonForVisiting(update.getReasonForVisiting());

        return appointmentRepo.save(existing);
    }

    // GET ALL
    public List<Appointment> getAllAppointments() {
        return appointmentRepo.findAll();
    }

    // GET BY ID
    public Optional<Appointment> getAppointmentById(int id) {
        return appointmentRepo.findById(id);
    }

    // GET BY PROVIDER
    public List<Appointment> getByProviderId(int providerId) {
        return appointmentRepo.findByProviderId(providerId);
    }
    

    // GET BY PATIENT
    public List<Appointment> getByPatientId(int patientId) {
        return appointmentRepo.findByPatientId(patientId);
    }

    // GET BY NURSE
    public List<Appointment> getByNurseId(Integer nurseId) {
        return appointmentRepo.findByNurseId(nurseId);
    }

    // GET BY DATE
    public List<Appointment> getByAppointmentDate(String datetime) {
        return appointmentRepo.findByAppointmentDate(datetime);
    }

    // GET BY ROOM
    public List<Appointment> getByRoomNumber(String roomNumber) {
        return appointmentRepo.findByRoomNumber(roomNumber);
    }
}
