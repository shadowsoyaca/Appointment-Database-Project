package com.appointmentProject.backend.repository;
import com.appointmentProject.backend.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**********************************************************************************************
 * AppointmentRepository.java
 *
 * Repository interface for the Appointment entity.
 *
 * @version 1.0
 * @since 12/04/2025
 * @author Alexis Patino
 **********************************************************************************************/

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {

    // Look up appointments by provider
    List<Appointment> findByProviderId(int providerId);

    // Look up appointments by patient
    List<Appointment> findByPatientId(int patientId);

    // Look up appointments by nurse (nullable FK)
    List<Appointment> findByNurseId(Integer nurseId);

    // Look up appointments by date
    List<Appointment> findByAppointmentDate(String datetime);

    // Look up appointments by room
    List<Appointment> findByRoomNumber(String roomNumber);
}
