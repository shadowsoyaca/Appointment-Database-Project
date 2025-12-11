package com.appointmentProject.backend.service;

import com.appointmentProject.backend.model.Appointment;
import com.appointmentProject.backend.repository.AppointmentRepository;
import com.appointmentProject.backend.repository.PatientRepository;
import com.appointmentProject.backend.repository.ProviderRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepo;
    private final PatientRepository patientRepo;
    private final ProviderRepository providerRepo;

    public AppointmentService(AppointmentRepository appointmentRepo,
                              PatientRepository patientRepo,
                              ProviderRepository providerRepo) {
        this.appointmentRepo = appointmentRepo;
        this.patientRepo = patientRepo;
        this.providerRepo = providerRepo;
    }

    // ---------- READ ----------

    public List<Appointment> getAllAppointments() {
        return appointmentRepo.findAll();
    }

    public Optional<Appointment> getAppointmentById(int id) {
        return appointmentRepo.findById(id);
    }

    // ---------- CREATE ----------

    public Appointment create(Appointment a) {
        validateAppointment(a, false);
        return appointmentRepo.save(a);
    }

    // ---------- UPDATE ----------

    public Appointment update(Appointment updated) {
        if (updated.getId() <= 0) {
            throw new IllegalArgumentException("APPOINTMENT_ID: Valid ID is required to update.");
        }

        Appointment existing = appointmentRepo.findById(updated.getId())
                .orElseThrow(() ->
                        new IllegalArgumentException("APPOINTMENT_ID: Appointment not found.")
                );

        // Copy editable fields
        existing.setPatientId(updated.getPatientId());
        existing.setProviderId(updated.getProviderId());
        existing.setBillingId(updated.getBillingId());
        existing.setNurseId(updated.getNurseId());
        existing.setPrescriptionId(updated.getPrescriptionId());
        existing.setLabOrderId(updated.getLabOrderId());
        existing.setAppointmentDate(updated.getAppointmentDate());
        existing.setRoomNumber(updated.getRoomNumber());
        existing.setReasonForVisiting(updated.getReasonForVisiting());
        existing.setStartTime(updated.getStartTime());
        existing.setEndTime(updated.getEndTime());

        validateAppointment(existing, true);
        return appointmentRepo.save(existing);
    }

    // ---------- DELETE ----------

    public void delete(int id) {
        if (!appointmentRepo.existsById(id)) {
            throw new IllegalArgumentException("APPOINTMENT_ID: Appointment not found.");
        }
        appointmentRepo.deleteById(id);
    }

    // ---------- VALIDATION ----------

    /**
     * Common validation used by both create & update.
     *
     * @param a        appointment to validate
     * @param isUpdate true if called from update()
     */
    private void validateAppointment(Appointment a, boolean isUpdate) {

        // ----- Required fields -----
        if (a.getPatientId() <= 0) {
            throw new IllegalArgumentException("PATIENT_ID: Patient ID is required and must be > 0.");
        }
        if (a.getProviderId() <= 0) {
            throw new IllegalArgumentException("PROVIDER_ID: Provider ID is required and must be > 0.");
        }
        if (a.getBillingId() <= 0) {
            throw new IllegalArgumentException("BILLING_ID: Billing ID is required and must be > 0.");
        }
        if (a.getAppointmentDate() == null) {
            throw new IllegalArgumentException("APPOINTMENT_DATE: Appointment date/time is required.");
        }
        if (a.getRoomNumber() == null || a.getRoomNumber().isBlank()) {
            throw new IllegalArgumentException("ROOM_NUMBER: Room number is required.");
        }
        if (a.getReasonForVisiting() == null || a.getReasonForVisiting().isBlank()) {
            throw new IllegalArgumentException("REASON: Reason for visiting is required.");
        }

        // ----- Time rules -----
        LocalDateTime dt = a.getAppointmentDate();
        LocalTime apptTime = dt.toLocalTime();
        LocalTime start = a.getStartTime();
        LocalTime end = a.getEndTime();

        if (start != null && start.isBefore(apptTime)) {
            throw new IllegalArgumentException(
                    "START_TIME: Start time cannot be earlier than the appointment date/time.");
        }

        if (start != null && end != null && start.isAfter(end)) {
            throw new IllegalArgumentException(
                    "START_TIME: Start time cannot be after end time.");
        }

        if (end != null) {
            if (end.isBefore(apptTime)) {
                throw new IllegalArgumentException(
                        "END_TIME: End time cannot be earlier than the appointment date/time.");
            }
            if (start != null && end.isBefore(start)) {
                throw new IllegalArgumentException(
                        "END_TIME: End time cannot be earlier than start time.");
            }
        }

        // ----- Uniqueness constraints -----
        if (!isUpdate) {
            // CREATE: any appointment at that slot is forbidden
            if (appointmentRepo.existsByProviderIdAndAppointmentDate(
                    a.getProviderId(), a.getAppointmentDate())) {
                throw new IllegalArgumentException(
                        "PROVIDER_ID: This provider already has an appointment at this date/time.");
            }

            if (appointmentRepo.existsByRoomNumberAndAppointmentDate(
                    a.getRoomNumber(), a.getAppointmentDate())) {
                throw new IllegalArgumentException(
                        "ROOM_NUMBER: This room is already booked at this date/time.");
            }
        } else {
            // UPDATE: any OTHER appointment at that slot is forbidden
            if (appointmentRepo.existsByProviderIdAndAppointmentDateAndIdNot(
                    a.getProviderId(), a.getAppointmentDate(), a.getId())) {
                throw new IllegalArgumentException(
                        "PROVIDER_ID: This provider already has another appointment at this date/time.");
            }

            if (appointmentRepo.existsByRoomNumberAndAppointmentDateAndIdNot(
                    a.getRoomNumber(), a.getAppointmentDate(), a.getId())) {
                throw new IllegalArgumentException(
                        "ROOM_NUMBER: Another appointment is already booked in this room at this date/time.");
            }
        }

        // ----- Existence checks -----
        patientRepo.findById(a.getPatientId())
                .orElseThrow(() -> new IllegalArgumentException(
                        "PATIENT_ID: No patient found with this ID."));

        providerRepo.findById(a.getProviderId())
                .orElseThrow(() -> new IllegalArgumentException(
                        "PROVIDER_ID: No provider found with this ID."));
    }
}
