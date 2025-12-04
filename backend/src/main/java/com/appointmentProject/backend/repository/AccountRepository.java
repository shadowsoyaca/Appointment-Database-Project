package com.appointmentProject.backend.repository;

import com.appointmentProject.backend.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, String> {
    /**********************************************************************************************
     * AccountRepository.java
     *
     * Repository interface for the LabOrder entity.
     *
     * @author Matthew Kiyono
     * @since 12/3/2025
     * @version 1.0
     **********************************************************************************************/
    Optional<Account> findByUsername(String username);
}
