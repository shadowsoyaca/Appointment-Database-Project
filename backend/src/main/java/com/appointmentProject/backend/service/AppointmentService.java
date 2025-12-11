package com.appointmentProject.backend.service;

import com.appointmentProject.backend.model.Appointment;
import com.appointmentProject.backend.repository.AppointmentRepository;
import com.appointmentProject.backend.repository.PatientRepository;
import com.appointmentProject.backend.repository.ProviderRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

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

    public List<Appointment> getAllAppointments() {
        return appointmentRepo.findAll();
    }

    public Appointment create(Appointment a) {

        // basic required checks (id is auto-generated, so we ignore it here)
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

        // optional fields already nullable in the model: nurseId, prescriptionId, labOrderId, startTime, endTime

        // TIME RULES (start_time / end_time are null at creation, but we keep the logic in case you reuse it later)
        LocalDateTime dt = a.getAppointmentDate();
        LocalTime apptTime = dt.toLocalTime();
        LocalTime start = a.getStartTime();
        LocalTime end = a.getEndTime();

        // start_time (if not null) cannot be earlier than appointment_date
        if (start != null && start.isBefore(apptTime)) {
            throw new IllegalArgumentException("START_TIME: Start time cannot be earlier than the appointment date/time.");
        }

        // start_time (if not null) cannot be later than end_time (if not null)
        if (start != null && end != null && start.isAfter(end)) {
            throw new IllegalArgumentException("START_TIME: Start time cannot be after end time.");
        }

        // end_time (if not null) cannot be earlier than appointment_date OR start_time (if not null)
        if (end != null) {
            if (end.isBefore(apptTime)) {
                throw new IllegalArgumentException("END_TIME: End time cannot be earlier than the appointment date/time.");
            }
            if (start != null && end.isBefore(start)) {
                throw new IllegalArgumentException("END_TIME: End time cannot be earlier than start time.");
            }
        }

        // provider_id and appointment_date are a unique combination
        if (appointmentRepo.existsByProviderIdAndAppointmentDate(a.getProviderId(), a.getAppointmentDate())) {
            throw new IllegalArgumentException("PROVIDER_ID: This provider already has an appointment at this date/time.");
        }

        // room_number and appointment_date are a unique combination
        if (appointmentRepo.existsByRoomNumberAndAppointmentDate(a.getRoomNumber(), a.getAppointmentDate())) {
            throw new IllegalArgumentException("ROOM_NUMBER: This room is already booked at this date/time.");
        }

        // (optional) confirm patient/provider exist
        patientRepo.findById(a.getPatientId())
                .orElseThrow(() -> new IllegalArgumentException("PATIENT_ID: No patient found with this ID."));
        providerRepo.findById(a.getProviderId())
                .orElseThrow(() -> new IllegalArgumentException("PROVIDER_ID: No provider found with this ID."));

        return appointmentRepo.save(a);
    }
}
