package com.appointmentProject.backend.repository;

import com.appointmentProject.backend.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {

    // For CREATE (check any appointment at that slot)
    boolean existsByProviderIdAndAppointmentDate(int providerId, LocalDateTime appointmentDate);

    boolean existsByRoomNumberAndAppointmentDate(String roomNumber, LocalDateTime appointmentDate);

    // For UPDATE (check any OTHER appointment at that slot)
    boolean existsByProviderIdAndAppointmentDateAndIdNot(
            int providerId,
            LocalDateTime appointmentDate,
            int id
    );

    boolean existsByRoomNumberAndAppointmentDateAndIdNot(
            String roomNumber,
            LocalDateTime appointmentDate,
            int id
    );
}
