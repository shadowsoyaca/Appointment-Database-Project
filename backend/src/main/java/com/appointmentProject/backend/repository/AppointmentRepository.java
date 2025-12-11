package com.appointmentProject.backend.repository;

import com.appointmentProject.backend.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {

    boolean existsByProviderIdAndAppointmentDate(int providerId, java.time.LocalDateTime appointmentDate);

    boolean existsByRoomNumberAndAppointmentDate(String roomNumber, java.time.LocalDateTime appointmentDate);
}
