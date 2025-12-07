package com.appointmentProject.backend.repository;

import com.appointmentProject.backend.model.EmergencyContact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**********************************************************************************************
 * EmergencyContactRepository.java
 *
 * Repository interface for the EmergencyContact entity.
 *
 * @author Alexis Patino
 * @since 12/05/2025
 * @version 1.0
 **********************************************************************************************/
@Repository
public interface EmergencyContactRepository extends JpaRepository<EmergencyContact, Integer> {

    Optional<EmergencyContact> findByFirstNameAndLastName(String firstName, String lastName);
}
