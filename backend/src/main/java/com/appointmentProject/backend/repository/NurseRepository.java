package com.appointmentProject.backend.repository;

import com.appointmentProject.backend.model.Nurse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**********************************************************************************************
 * LabOrderRepository.java
 *
 * Repository interface for the Nurse entity.
 *
 * @author Alex Patino
 * @since 12/3/2025
 * @version 1.0
 **********************************************************************************************/

@Repository
public interface NurseRepository extends JpaRepository<Nurse, Integer> {

    Optional<Nurse> findByFirstNameAndLastName(String firstName, String lastName);
}
