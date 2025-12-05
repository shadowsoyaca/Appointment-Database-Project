package com.appointmentProject.backend.repository;

import com.appointmentProject.backend.model.Availability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**********************************************************************************************
 * AvailabilityRepository.java
 *
 * Repository interface for the Availability entity.
 *
 * Supports basic CRUD plus searches by staffId, staffType, and dayOfWeek.
 *
 * @author Alexis Patino
 * @since 12/05/2025
 * @version 1.0
 **********************************************************************************************/
@Repository
public interface AvailabilityRepository extends JpaRepository<Availability, Integer> {

    // Look up all availability entries for a specific staff member
    List<Availability> findByStaffId(int staffId);

    // Look up availability by staff type (Provider or Nurse)
    List<Availability> findByStaffType(String staffType);

    // Look up availability by day of week (Mon–Sun)
    List<Availability> findByDayOfWeek(String dayOfWeek);

    // Lookup by combination
    List<Availability> findByStaffIdAndDayOfWeek(int staffId, String dayOfWeek);
}
